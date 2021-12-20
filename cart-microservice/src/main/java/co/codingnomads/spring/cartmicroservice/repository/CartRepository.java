package co.codingnomads.spring.cartmicroservice.repository;

import co.codingnomads.spring.cartmicroservice.models.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Cart findByUserId(Long userId);
}
