package co.codingnomads.spring.itemmicroservice.service;

import co.codingnomads.spring.itemmicroservice.models.Item;
import co.codingnomads.spring.itemmicroservice.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        Iterable<Item> results = itemRepository.findAll();
        List<Item> itemList = new ArrayList<>();
        results.forEach(itemList::add);
        return itemList;
    }

    public Item getItemById(Long id) {
        Optional<Item> optional;
        if ((optional = itemRepository.findById(id)).isEmpty()) {
            return null;
        } else {
            return optional.get();
        }
    }

    public Item insertNewItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        Optional<Item> optional;
        if ((optional = itemRepository.findById(item.getItemId())).isEmpty()) {
            throw new IllegalStateException("No item found with id: " + item.getItemId());
        } else {
            Item updateItem = optional.get();
            BeanUtils.copyProperties(item, updateItem);
            return itemRepository.save(updateItem);
        }
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}