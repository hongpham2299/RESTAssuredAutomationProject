package eCommerce.config;

public interface ProductBaseEndPoints {

    String ALL_PRODUCTS = "/products";
    int TOTAL_NUMBER_OF_PRODUCTS = 100;
    String SINGLE_PRODUCT = "/products/{productId}";
    String SEARCH_PRODUCT = "/products/search";
    String ALL_PRODUCTS_CATEGORIES = "/products/categories";
    String SINGLE_PRODUCT_CATEGORY = "/products/category/{specificCategory}";
    String ADD_SINGLE_PRODUCT = "/products/add";

}
