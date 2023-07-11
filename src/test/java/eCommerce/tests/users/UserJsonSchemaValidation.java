package eCommerce.tests.users;
import eCommerce.config.UserBaseEndPoints;
import eCommerce.utility.BaseSetup;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class UserJsonSchemaValidation extends BaseSetup {

    private File fileSchema;

    private String userSchemaFilePath = "src/test/resources/users/userSchema.json";

    @Test
    public void validateUserSchema(){
        fileSchema = new File(userSchemaFilePath);

        given()
                .pathParam("userId", 1)
                .when()
                .get(UserBaseEndPoints.SINGLE_USER)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(fileSchema));
    }
}
