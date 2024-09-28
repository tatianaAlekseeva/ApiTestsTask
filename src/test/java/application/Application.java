package application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import model.InputUserData;
import model.Users;
import services.Specifications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;
import static services.EndPoints.createUser;
import static services.EndPoints.getUsers;

public class Application {

    private static final Specifications spec = new Specifications();

    public static Response getUsers() {
        return given()
                .spec(spec.requestGetSpec)
                .get(getUsers);
    }

    public static Response createUser(InputUserData userData) {
        return given()
                .spec(spec.requestPostSpec)
                .body(userData)
                .post(createUser);
    }

    public static int getMaxId(){
        Response response = getUsers();
        Users savedUserData = response.as(Users.class);
        int maxId = 0;
        if (savedUserData.size() > 0) {
           maxId = savedUserData.get(savedUserData.size()-1).getId();
        }
        return maxId;
    }
}
