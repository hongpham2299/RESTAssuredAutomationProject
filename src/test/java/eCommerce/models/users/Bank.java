package eCommerce.models.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Bank {

    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;

}
