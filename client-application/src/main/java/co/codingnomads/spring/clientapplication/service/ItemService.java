package co.codingnomads.spring.clientapplication.service;

import co.codingnomads.spring.clientapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    public Item getItem(Long itemId) {
        ResponseEntity<Item> response = restTemplate.getForEntity("http://ITEM-MICROSERVICE/item/" + itemId, Item.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public List<Item> getAllItems() {
        ResponseEntity<Item[]> response = restTemplate.getForEntity("http://ITEM-MICROSERVICE/item", Item[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            return null;
        }
    }
}
