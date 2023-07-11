package eCommerce.config;

public interface UserBaseEndPoints {

    String ALL_USERS = "/users";
    int TOTAL_NUMBER_OF_USERS = 100;
    String SINGLE_USER = "/users/{userId}";
    String SEARCH_USER = "/users/search";
    String FILTER_USER = "/users/filter";
    String GET_USER_BY_SPECIALTY = "/users/{userId}/{specialty}";
    String ADD_USER = "/users/add";

}
