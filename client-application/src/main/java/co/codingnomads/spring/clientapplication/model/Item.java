package co.codingnomads.spring.clientapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item {

    @JsonProperty("id")
    private Long cartItemId;
    private Long itemId;
    private String name;
    private String description;
    private Integer amount;
}