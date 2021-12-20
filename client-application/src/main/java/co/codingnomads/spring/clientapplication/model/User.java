package co.codingnomads.spring.clientapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String email;
}
