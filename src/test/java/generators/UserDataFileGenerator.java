package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.InputUserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDataFileGenerator {
    @Parameter(names = "-c", description = "User count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        UserDataFileGenerator generator = new UserDataFileGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    public void run() throws IOException {
        List<InputUserData> users = generateUsers(count);
        saveAsJson(users, new File(file));
    }

    private void saveAsJson(List<InputUserData> users, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private static final Random random = new Random();

    private List<InputUserData> generateUsers(int count) {
        List<InputUserData> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new InputUserData().setUsername(String.format("User%d", i + random.nextInt(0, 999)))
                    .setEmail(String.format("test%d@rep.com", i + random.nextInt(0, 999)))
                    .setPassword("12345"));
        }
        return users;
    }
}
