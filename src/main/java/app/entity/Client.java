package app.entity;

import lombok.Data;

@Data
public class Client extends Entity {

    private String idClient;
    private String firstName;
    private String lastName;
    private String idAccount;
}
