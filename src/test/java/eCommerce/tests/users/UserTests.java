package eCommerce.tests.users;

import eCommerce.config.UserBaseEndPoints;
import eCommerce.utility.BaseSetup;
import eCommerce.utility.FilesReader;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class UserTests extends BaseSetup {

    private String addUserJsonFile = "src/test/resources/users/addUser.json";
    private String updateUserJsonFile = "src/test/resources/users/updateUser.json";

    @Test
    public void getAllUsers(){
        given()
                .when()
                .get(UserBaseEndPoints.ALL_USERS)
                .then()
                .body("total", equalTo(UserBaseEndPoints.TOTAL_NUMBER_OF_USERS));
    }

    @Test
    public void getSingleUser(){
        given()
                .pathParam("userId", 1)
                .when()
                .get(UserBaseEndPoints.SINGLE_USER)
                .then()
                .body("firstName", equalTo("Terry"))
                .body("address.address", equalTo("1745 T Street Southeast"));
    }

    @Test
    public void searchUser(){
        given()
                .queryParam("q", "John")
                .when()
                .get(UserBaseEndPoints.SEARCH_USER)
                .then()
                .body("users[0].id", equalTo(50))
                .body("total", equalTo(1));
    }

    @Test
    public void filterUsers(){
        given()
                .queryParam("key", "hair.color")
                .queryParam("value", "Brown")
                .when()
                .get(UserBaseEndPoints.FILTER_USER)
                .then()
                .body("users.size()", equalTo(26))
                .body("users.id", hasItems(5,9,18,23,24,25,28,31,34,100));
    }

    @Test
    public void limitsAndSkipUsers(){
        given()
                .queryParam("limit", 5)
                .queryParam("skip", 10)
                .queryParam("select", "firstName,age")
                .when()
                .get(UserBaseEndPoints.ALL_USERS)
                .then()
                .body("users.firstName", hasItems("Marcel", "Assunta", "Trace","Enoch","Jeanne"));
    }

    @Test
    public void getUserCartByUserId(){
        given()
                .pathParam("userId", 5)
                .pathParam("specialty", "carts")
                .when()
                .get(UserBaseEndPoints.GET_USER_BY_SPECIALTY)
                .then()
                .body("carts[0].total", equalTo(2492));
    }
    @Test
    public void getUserPostByUserId(){
        given()
                .pathParam("userId", 5)
                .pathParam("specialty", "posts")
                .when()
                .get(UserBaseEndPoints.GET_USER_BY_SPECIALTY)
                .then()
                .body("posts.size()", equalTo(3))
                .body("posts.id", hasItems(17,44,75));
    }

    @Test
    public void getUserTodoByUserId(){
        given()
                .pathParam("userId", 5)
                .pathParam("specialty", "todos")
                .when()
                .get(UserBaseEndPoints.GET_USER_BY_SPECIALTY)
                .then()
                .body("todos.size()", equalTo(3))
                .body("todos.todo", hasItems("Create a compost pile", "Make a budget", "Go to a local thrift shop"));
    }

    @Test
    public void addNewUserByJSONFile(){
        given()
                .body(FilesReader.processFiles(addUserJsonFile))
                .when()
                .post(UserBaseEndPoints.ADD_USER)
                .then()
                .body("id", equalTo(101));
    }

    @Test
    public void addNewUserByPOJO(){
        given()
                .body(UserInstance.createUserInstance())
                .when()
                .post(UserBaseEndPoints.ADD_USER)
                .then()
                .body("id", equalTo(101));
    }

    @Test
    public void updateUser(){
        given()
                .pathParam("userId", 1)
                .body(FilesReader.processFiles(updateUserJsonFile))
                .when()
                .put(UserBaseEndPoints.SINGLE_USER)
                .then()
                .body("lastName", equalTo("Owais"))
                .body("email", equalTo("atuny20@sohu.com"))
                .body("phone", equalTo("+63 791 675 8917"));
    }

    @Test
    public void deleteUser(){
        given()
                .pathParam("userId", 1)
                .when()
                .delete(UserBaseEndPoints.SINGLE_USER)
                .then()
                .body("isDeleted", equalTo(true));
    }
}
