package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CreateUserResponse;
import model.InputUserData;
import model.UserCreateFailResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static application.Application.createUser;
import static application.Application.getMaxId;
import static generators.UserDataForRequestGenerator.generateUserdata;
import static helpers.DataHelper.getUserdataForRequest;
import static org.junit.jupiter.api.Assertions.*;

public class CreateUserTests extends TestBase{

    public static final String USER_SUCCESSFULLY_CREATED = "User Successully created";
    public static final String THIS_USERNAME_IS_TAKEN_TRY_ANOTHER = "This username is taken. Try another.";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";

    public static final String PASSWORD_IS_REQUIRED = "A password for the user";
    public static final String USERNAME_IS_REQUIRED = "A username is required";

    public static final String EMAIL_IS_REQUIRED = "An Email is required";

    public static final String PASSWORD = "^&%PASt5";

    @Test
    @DisplayName("Create new user")
    @Tag("create_user_positive")
    public void addNewUserTest() {
        InputUserData userData = users.get(3);
        Integer expectedId = getMaxId() + 1;

        Response response = createUser(userData);
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
        CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);

        assertNotNull(createUserResponse.getDetails().getId());
        Integer newUserId = createUserResponse.getDetails().getId();
        String hashPassword = createUserResponse.getDetails().getPassword();
        assertEquals(newUserId, expectedId);
        assertTrue(createUserResponse.getSuccess());
        assertEquals(createUserResponse.getMessage(), USER_SUCCESSFULLY_CREATED);
        assertEquals(createUserResponse.getDetails().getUsername(), userData.username);
        assertEquals(createUserResponse.getDetails().getEmail(), userData.email);
        assertNotEquals(hashPassword, userData.password);
        assertEquals(hashPassword.length(), 60);
        assertEquals(hashPassword.substring(0, 7), "$2a$10$");
        assertNotNull(createUserResponse.getDetails().getCreatedAt());
        assertNotNull(createUserResponse.getDetails().getUpdatedAt());
    }

    @Test
    @DisplayName("Create new user with the existing username")
    @Tag("create_user_negative")
    public void addNewUserWithExistingUsernameTest() {
        InputUserData userData = generateUserdata();
        createUser(userData);

        String email = String.format("mymail%d@edu.com", random.nextInt(500, 9999));
        InputUserData nextUserData = getUserdataForRequest(userData.username, email, PASSWORD);
        Response response = createUser(nextUserData);
        response.then()
                .statusCode(400)
                .contentType(ContentType.JSON);
        UserCreateFailResponse userCreateFailResponse = response.as(UserCreateFailResponse.class);
        assertFalse(userCreateFailResponse.getSuccess());
        assertEquals(getExpectedMessage(userCreateFailResponse), THIS_USERNAME_IS_TAKEN_TRY_ANOTHER);
    }

    @Test
    @DisplayName("Create new user with the existing email")
    @Tag("create_user_negative")
    public void addNewUserWithExistingEmailTest() {
        InputUserData userData = generateUserdata();
        createUser(userData);

        String username = String.format("test%duser", random.nextInt(500, 9999));
        String password = String.format("p%dasd", random.nextInt(0, 10));
        InputUserData nextUserData = getUserdataForRequest(username, userData.email, password);
        Response response = createUser(nextUserData);
        response.then()
                .statusCode(400)
                .contentType(ContentType.JSON);
        UserCreateFailResponse userCreateFailResponse = response.as(UserCreateFailResponse.class);
        assertFalse(userCreateFailResponse.getSuccess());
        assertEquals(getExpectedMessage(userCreateFailResponse), EMAIL_ALREADY_EXISTS);
    }

    @Test
    @DisplayName("Create new user with the empty password")
    @Tag("create_user_negative")
    public void addNewUserWithEmptyPasswordTest() {
        String username = String.format("mymail%d@edu.com", random.nextInt(500, 9999));
        String email = String.format("user%duser", random.nextInt(500, 9999));
        InputUserData userData = getUserdataForRequest(username, email, "");
        Response response = createUser(userData);
        response.then()
                .statusCode(400)
                .contentType(ContentType.JSON);
        UserCreateFailResponse userCreateFailResponse = response.as(UserCreateFailResponse.class);
        assertFalse(userCreateFailResponse.getSuccess());
        assertEquals(getExpectedMessage(userCreateFailResponse), PASSWORD_IS_REQUIRED);
    }

    @Test
    @DisplayName("Create new user with the empty username")
    @Tag("create_user_negative")
    public void addNewUserWithEmptyUsernameTest() {
        String username = "";
        String email = String.format("mymail%d@edu.com", random.nextInt(500, 9999));
        InputUserData userData = getUserdataForRequest(username, email, PASSWORD);
        Response response = createUser(userData);
        response.then()
                .statusCode(400)
                .contentType(ContentType.JSON);
        UserCreateFailResponse userCreateFailResponse = response.as(UserCreateFailResponse.class);
        assertFalse(userCreateFailResponse.getSuccess());
        assertEquals(getExpectedMessage(userCreateFailResponse), USERNAME_IS_REQUIRED);
    }

    @Test
    @DisplayName("Create new user with the empty email")
    @Tag("create_user_negative")
    public void addNewUserWithEmptyEmailTest() {
        String username = String.format("mymail%d@edu.com", random.nextInt(500, 9999));
        String email = "";
        InputUserData userData = getUserdataForRequest(username, email, PASSWORD);
        Response response = createUser(userData);
        response.then()
                .statusCode(400)
                .contentType(ContentType.JSON);
        UserCreateFailResponse userCreateFailResponse = response.as(UserCreateFailResponse.class);
        assertFalse(userCreateFailResponse.getSuccess());
        assertEquals(getExpectedMessage(userCreateFailResponse), EMAIL_IS_REQUIRED);
    }

    private static String getExpectedMessage(UserCreateFailResponse userCreateFailResponse) {
        return userCreateFailResponse.getMessage().get(0);
    }
}
