package co.codingnomads.spring.clientapplication.service;

import co.codingnomads.spring.clientapplication.model.Cart;
import co.codingnomads.spring.clientapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @Autowired
    ItemService itemService;

    public Cart getCart(Long userId) {
        ResponseEntity<Cart> response = restTemplate.getForEntity
                ("http://CART-MICROSERVICE/cart/" + userId, Cart.class);
        return checkStatus(response);
    }

    public Cart addCartItem(Long userId, Long itemId) {
        ResponseEntity<Cart> response = restTemplate.postForEntity
                ("http://CART-MICROSERVICE/cart/" + userId + "?item-id=" + itemId, null, Cart.class);
        return checkStatus(response);
    }

    public Cart removeCartItem(Long userId, Long cartItemId) {
        ResponseEntity<Cart> response = restTemplate.exchange
                ("http://CART-MICROSERVICE/cart/" + userId + "/" + cartItemId,
                        HttpMethod.DELETE, null, Cart.class);
        return checkStatus(response);
    }

    public Cart updateItemAmount(Long userId, Long cartItemId, Integer amount) {
        ResponseEntity<Cart> response = restTemplate.exchange
                ("http://CART-MICROSERVICE/cart/" + userId + "/" + cartItemId + "?amount=" + amount,
                        HttpMethod.PATCH, null, Cart.class);
        return checkStatus(response);
    }

    public Cart processCartItems(Cart cart) {
        if (cart.getItems() != null) {
            for (Item cartItem : cart.getItems()) {
                Item item = itemService.getItem(cartItem.getItemId());
                cartItem.setName(item.getName());
                cartItem.setDescription(item.getDescription());
            }
        } else {
            cart.setItems(new ArrayList<>());
        }
        return cart;
    }

    public Cart checkStatus(ResponseEntity<Cart> response) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return processCartItems(response.getBody());
        } else {
            return new Cart();
        }
    }
}
