package co.codingnomads.spring.cartmicroservice.models;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Integer amount;
}