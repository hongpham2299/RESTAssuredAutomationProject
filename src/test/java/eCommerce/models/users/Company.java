package eCommerce.models.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Company {

    private CompanyAddress address;
    private String department;
    private String name;
    private String title;

}
