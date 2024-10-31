import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class JsonManager {

    // Создание JSON-файла и запись объекта
    public static void writeJsonFile(String filePath, Person person) {
        try (FileWriter writer = new FileWriter(filePath)) {
            String json = "{ \"name\": \"" + person.name + "\", \"age\": " + person.age + "}";
            writer.write(json);
            System.out.println("Запись JSON выполнена успешно.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Чтение JSON-файла
    public static void readJsonFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            int i;
            while ((i = reader.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

