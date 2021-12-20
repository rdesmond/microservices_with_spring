package co.codingnomads.spring.clientapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    private Long userId;
    private List<Item> items;
}
