package test;

import application.Application;
import generators.UserDataFileGenerator;
import model.InputUserData;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static helpers.DataHelper.readUsersFromFile;
import static java.lang.Runtime.getRuntime;

public class TestBase {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public static Application app;

    public static final Random random = new Random();

    public static List<InputUserData> users;

    @BeforeAll
    public static void start() {

        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        tlApp.set(app);

        getRuntime().addShutdownHook(
                new Thread(() -> app = null)
        );
    }

    @BeforeAll
    public static void setup() throws IOException {
        String[] args = {"-c", "4", "-f", "src/test/resources/newUsers.json"};
        UserDataFileGenerator.main(args);

        users = readUsersFromFile("src/test/resources/newUsers.json");
    }
}
