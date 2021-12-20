package co.codingnomads.spring.itemmicroservice.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue
    private Long itemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}