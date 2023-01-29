package com.dataemission.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@ToString

@Document(collection  = "newUser")
public class NewUser {

    @Id
    private int id;
    private String username;
    private String password;
    private String age;


}
