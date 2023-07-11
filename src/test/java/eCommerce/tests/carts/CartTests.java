package eCommerce.tests.carts;

import eCommerce.config.CartBaseEndPoints;
import eCommerce.utility.BaseSetup;
import eCommerce.utility.FilesReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class CartTests extends BaseSetup {

    private String addCartFilePath = "src/test/resources/carts/addCart.json";
    private String updateCartFilePath = "src/test/resources/carts/updateCart.json";

    @Test
    public void updateCart(){
        Response response = given()
                .pathParam("cartId",1)
                .body(FilesReader.processFiles(updateCartFilePath))
                .when()
                .put(CartBaseEndPoints.SINGLE_CART)
                .then()
                .extract().response();

        String responseToString = response.asString();

        JsonPath js = new JsonPath(responseToString);
        int count = js.getInt("products.size()");

        int sum = 0;
        for(int i=0; i<count; i++){
            int price = js.getInt("products["+i+"].price");
            int quantity = js.getInt("products["+i+"].quantity");
            int totalAmountPerProduct = price * quantity;
            sum = sum + totalAmountPerProduct;
        }

        int purchasedAmount = js.getInt("total");
        Assert.assertEquals(sum, purchasedAmount);

    }

    @Test
    public void getAllCarts(){
        given()
                .when()
                .get(CartBaseEndPoints.ALL_CARTS)
                .then()
                .body("carts.size()", equalTo(CartBaseEndPoints.TOTAL_NUMBER_OF_CARTS));
    }

    @Test
    public void getSingleCart(){
        given()
                .pathParam("cartId", 1)
                .when()
                .get(CartBaseEndPoints.SINGLE_CART)
                .then()
                .body("products.id", hasItems(59,88,18,95,39));
    }

    @Test
    public void getCartsOfUser(){
        given()
                .pathParam("type", "user")
                .pathParam("userId", 5)
                .when()
                .get(CartBaseEndPoints.GET_CART_BY_USERID)
                .then()
                .body("carts[0].totalProducts", equalTo(5));
    }

    @Test
    public void addNewCart(){
        given()
                .body(FilesReader.processFiles(addCartFilePath))
                .when()
                .post(CartBaseEndPoints.ADD_CART)
                .then()
                .body("products[0].title", equalTo("iPhone 9"))
                .body("products[1].title", equalTo("Women Shoes"))
                .body("totalProducts", equalTo(2));
    }

    @Test
    public void deleteCart(){
        given()
                .pathParam("cartId", 1)
                .when()
                .delete(CartBaseEndPoints.SINGLE_CART)
                .then()
                .body("isDeleted", equalTo(true));
    }


}
