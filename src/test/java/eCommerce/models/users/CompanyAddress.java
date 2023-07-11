package eCommerce.models.users;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CompanyAddress {

    private String address;
    private String city;
    private CompanyCoordinates coordinates;
    private String postalCode;
    private String state;

}
