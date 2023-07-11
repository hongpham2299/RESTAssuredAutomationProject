package eCommerce.tests.products;

import eCommerce.utility.FilesReader;
import eCommerce.config.ProductBaseEndPoints;
import eCommerce.utility.BaseSetup;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;


public class ProductTests extends BaseSetup {

    private String jsonFileForAddProduct = "src/test/resources/products/addProduct.json";
    private String jsonFileForUpdateProduct = "src/test/resources/products/updateProduct.json";


    @Test
    public void getAllProducts(){
        given()
                .when()
                .get(ProductBaseEndPoints.ALL_PRODUCTS)
                .then()
                .body("total", equalTo(ProductBaseEndPoints.TOTAL_NUMBER_OF_PRODUCTS));
    }

    @Test
    public void getSingleProduct(){
        given()
                .pathParam("productId", 1)
                .when()
                .get(ProductBaseEndPoints.SINGLE_PRODUCT)
                .then()
                .body("title", equalTo("iPhone 9"));
    }

    @Test
    public void searchProducts(){
        given()
                .queryParam("q", "phone")
                .when()
                .get(ProductBaseEndPoints.SEARCH_PRODUCT)
                .then()
                .body("products[0].title", equalTo("iPhone 9"))
                .body("products[1].title", equalTo("iPhone X"))
                .body("products[2].title", equalTo("Women Shoulder Bags"))
                .body("products.title", hasItem("Bluetooth Aux"));
    }

    @Test
    public void limitAndSkipProducts(){
        given()
                .queryParam("limit", "10")
                .queryParam("skip", "10")
                .queryParam("select", "title,price")
                .when()
                .get(ProductBaseEndPoints.ALL_PRODUCTS)
                .then()
                .body("products.title", hasItems("perfume Oil", "Brown Perfume", "Fog Scent Xpressio Perfume",
                                            "Non-Alcoholic Concentrated Perfume Oil", "Tree Oil 30ml"));
    }

    @Test
    public void getAllProductCategories(){
        given()
                .when()
                .get(ProductBaseEndPoints.ALL_PRODUCTS_CATEGORIES)
                .then()
                .body(Matchers.containsString("smartphones"))
                .body(Matchers.containsString("laptops"))
                .body(Matchers.containsString("fragrances"))
                .body(Matchers.containsString("sunglasses"));
    }

    @Test
    public void getProductsOfACategory(){
        given()
                .pathParam("specificCategory", "smartphones")
                .when()
                .get(ProductBaseEndPoints.SINGLE_PRODUCT_CATEGORY)
                .then()
                .body("products.title", hasItems("iPhone 9", "iPhone X", "Samsung Universe 9", "OPPOF19", "Huawei P30"))
                .body("total", equalTo(5));
    }

    @Test
    public void addNewProductByJSON(){
        given()
                .body(FilesReader.processFiles(jsonFileForAddProduct))
                .when()
                .post(ProductBaseEndPoints.ADD_SINGLE_PRODUCT)
                .then()
                .body("id", equalTo(101));
    }

    @Test
    public void addNewProductByPOJO(){
        given()
                .body(ProductInstance.createProductInstance())
                .when()
                .post(ProductBaseEndPoints.ADD_SINGLE_PRODUCT)
                .then()
                .body("id", equalTo(101));
    }

    @Test
    public void updateProduct(){
        given()
                .pathParam("productId", 1)
                .body(FilesReader.processFiles(jsonFileForUpdateProduct))
                .when()
                .put(ProductBaseEndPoints.SINGLE_PRODUCT)
                .then()
                .body("title", equalTo("iPhone Galaxy +1"))
                .body("price", equalTo(765.41F))
                .body("stock", equalTo(23));
    }

    @Test
    public void deleteProduct(){
        given()
                .pathParam("productId", 1)
                .when()
                .delete(ProductBaseEndPoints.SINGLE_PRODUCT)
                .then()
                .body("isDeleted", equalTo(true));
    }

}
