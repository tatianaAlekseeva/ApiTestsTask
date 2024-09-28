package test;

import io.restassured.response.Response;
import model.CreateUserResponse;
import model.InputUserData;
import model.SavedUserData;
import model.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static application.Application.createUser;
import static application.Application.getUsers;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUsersTest extends TestBase {


    @Test
    @DisplayName("Get users")
    @Tag("get_users")
    public void getUsersTest() {
        InputUserData firstUserData = users.get(0);
        Response createResponse = createUser(firstUserData);
        CreateUserResponse createFirstUserResponse = createResponse.as(CreateUserResponse.class);

        InputUserData secondUserData = users.get(1);
        Response nextCreateResponse = createUser(secondUserData);
        CreateUserResponse createSecondUserResponse = nextCreateResponse.as(CreateUserResponse.class);

        Response getResponse = getUsers();
        Users usersList = getResponse.as(Users.class);

        AtomicReference<SavedUserData> foundFirst = new AtomicReference<>();
        usersList.stream().filter(x -> Objects.equals(x.getId(), createFirstUserResponse.getDetails().getId())).findFirst().ifPresent(foundFirst::set);

        SavedUserData newFirstUser = foundFirst.get();
        assertEquals(newFirstUser, createFirstUserResponse.getDetails());

        AtomicReference<SavedUserData> foundSecond = new AtomicReference<>();
        usersList.stream().filter(x -> Objects.equals(x.getId(), createSecondUserResponse.getDetails().getId())).findFirst().ifPresent(foundSecond::set);

        SavedUserData newSecondUser = foundSecond.get();
        assertEquals(newSecondUser, createSecondUserResponse.getDetails());
    }
}
