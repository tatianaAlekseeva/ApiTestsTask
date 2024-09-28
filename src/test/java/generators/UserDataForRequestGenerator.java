package generators;

import model.InputUserData;

import java.util.Random;

public  class UserDataForRequestGenerator {
    private static final Random random = new Random();
    public static InputUserData generateUserdata() {
       InputUserData userData = new InputUserData().setUsername(String.format("User%d", random.nextInt(500, 9999)))
                    .setEmail(String.format("test%d@rep.com", random.nextInt(500, 9999)))
                    .setPassword("1tEest");
        return userData;
    }
}