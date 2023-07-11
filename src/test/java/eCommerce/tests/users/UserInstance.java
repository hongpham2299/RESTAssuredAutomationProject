package eCommerce.tests.users;

import eCommerce.models.users.*;

import java.time.LocalDate;

public class UserInstance {

    public static User createUserInstance(){

        Hair hair = new Hair("Black","Strands");

        Coordinates coordinates = new Coordinates(38.867033, -76.979235);

        Address address = new Address("1745 T Street Southeast", "Washington", coordinates, "20020", "DC");

        Bank bank = new Bank("06/22", "50380955204220685", "maestro", "USD", "NO17 0695 2754 967");

        CompanyCoordinates companyCoordinates = new CompanyCoordinates(36.208114,-86.58621);

        CompanyAddress companyAddress = new CompanyAddress("629 Debbie Drive", "Nashville", companyCoordinates, "37076", "TN");

        Company company = new Company(companyAddress, "Marketing", "Blanda-O'Keefe", "Help Desk Operator");

        User user = new User("Alister", "Freeman", "Young", 32, "male", "atuny0@sohu.com",
                "+63 791 675 8914", "alstr", "9uQFF1Lh", LocalDate.of(2000, 12,25),
                "https://robohash.org/hicveldicta.png", "A−", 189, 75.4, "Green",
                hair, "slashdot.org", "117.29.86.254", address, "13:69:BA:56:A3:74", "Capitol University",
                bank, company, "20-9487066", "661-64-2976", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/12.0.702.0 Safari/534.24");

        return user;
    }
}
