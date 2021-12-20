package co.codingnomads.spring.itemmicroservice.repository;

import co.codingnomads.spring.itemmicroservice.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
