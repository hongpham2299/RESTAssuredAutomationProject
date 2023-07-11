package eCommerce.config;

public interface CartBaseEndPoints {

    String ALL_CARTS = "/carts";
    int TOTAL_NUMBER_OF_CARTS = 20;
    String SINGLE_CART = "/carts/{cartId}";
    String GET_CART_BY_USERID = "carts/{type}/{userId}";
    String ADD_CART = "/carts/add";


}
