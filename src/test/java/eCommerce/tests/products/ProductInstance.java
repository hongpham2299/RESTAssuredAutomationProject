package eCommerce.tests.products;

import eCommerce.models.products.Product;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductInstance {

    public static Product createProductInstance(){

        List<String> images = new ArrayList<>();
        images.add("https://i.dummyjson.com/data/products/101/image1.jpg");
        images.add("https://i.dummyjson.com/data/products/101/image2.jpg");
        images.add("https://i.dummyjson.com/data/products/101/image3.jpg");

        Product product = new Product("Leather Shoes", "An Awesome Leather Shoes", 40.59, 10, 4.6, 50,
                "Aldo", "shoes", "https://i.dummyjson.com/data/products/101/thumbnail.jpg", images);
        return product;
    }

}
