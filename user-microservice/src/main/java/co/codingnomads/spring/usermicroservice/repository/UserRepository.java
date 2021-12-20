package co.codingnomads.spring.usermicroservice.repository;

import co.codingnomads.spring.usermicroservice.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
