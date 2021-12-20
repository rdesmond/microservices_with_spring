package co.codingnomads.spring.clientapplication.controller;

import co.codingnomads.spring.clientapplication.model.Cart;
import co.codingnomads.spring.clientapplication.model.User;
import co.codingnomads.spring.clientapplication.service.CartService;
import co.codingnomads.spring.clientapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public String displayCart(Model model, Principal principal) {
        User user = userService.getUser(principal.getName());
        Cart cart = cartService.getCart(user.getId());
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("userId", user.getId());
        return "cart";
    }

    @GetMapping("/cart/{userId}/{cartItemId}")
    public String removeCartItem(@PathVariable("userId") Long userId,
                                 @PathVariable("cartItemId") Long cartItemId, Model model) {
        Cart cart = cartService.removeCartItem(userId, cartItemId);
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("userId", userId);
        return "cart";
    }

    @PostMapping("/cart/{userId}/{itemId}")
    public String addCartItem(@PathVariable("userId") Long userId,
                              @PathVariable("itemId") Long itemId, Model model) {
        Cart cart = cartService.addCartItem(userId, itemId);
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("userId", userId);
        return "cart";
    }

    @GetMapping("/item/{userId}/{cartItemId}/{amount}")
    public String changeItemAmount(@PathVariable("userId") Long userId,
                                   @PathVariable("cartItemId") Long cartItemId,
                                   @PathVariable("amount") Integer amount, Model model) {
        Cart cart;
        if (amount == 0) {
            cart = cartService.removeCartItem(userId, cartItemId);
        } else {
            cart = cartService.updateItemAmount(userId, cartItemId, amount);
        }
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("userId", userId);
        return "cart";
    }
}
