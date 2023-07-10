package eCommerce.tests.products;

import eCommerce.config.ProductBaseEndPoints;
import eCommerce.utility.BaseSetup;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class ProductJsonSchemaValidation extends BaseSetup {


    private File fileSchema;
    private String allProductSchemaPath = "src/test/resources/products/getAllProductSchema.json";
    private String addProductSchemaPath = "src/test/resources/products/addProductSchema.json";
    private String updateProductSchemaPath = "src/test/resources/products/updateProductSchema.json";

    @Test
    public void validateProductSchema(){

        fileSchema = new File(allProductSchemaPath);

        given()
                .pathParam("productId", 26)
                .when()
                .get(ProductBaseEndPoints.SINGLE_PRODUCT)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(fileSchema));
    }

    @Test
    public void validateAddNewProductSchema(){
        fileSchema = new File(addProductSchemaPath);

        given()
                .when()
                .post(ProductBaseEndPoints.ADD_SINGLE_PRODUCT)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(fileSchema));
    }

    @Test
    public void validateUpdateProductSchema(){
        fileSchema = new File(updateProductSchemaPath);
        given()
                .pathParam("productId", 1)
                .when()
                .put(ProductBaseEndPoints.SINGLE_PRODUCT)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(fileSchema));
    }
}
