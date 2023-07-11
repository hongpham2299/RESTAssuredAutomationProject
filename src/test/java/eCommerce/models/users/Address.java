package eCommerce.models.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {

    private String address;
    private String city;
    private Coordinates coordinates;
    private String postalCode;
    private String state;

}
