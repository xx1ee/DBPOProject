import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileManager {

    // Получение информации о дисках
    public static void showDiskInfo() {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("Диск: " + root.getAbsolutePath());
            System.out.println("Объем: " + root.getTotalSpace() / 1024 / 1024 + " МБ");
            System.out.println("Свободное пространство: " + root.getFreeSpace() / 1024 / 1024 + " МБ");
            System.out.println("Тип файловой системы: " + System.getProperty("os.name"));
            System.out.println();
        }
    }

    // Создание и запись строки в текстовый файл
    public static void createAndWriteTextFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
            System.out.println("Запись выполнена успешно.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Чтение содержимого текстового файла
    public static void readTextFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Не удается найти указанный файл");
        }
    }

    // Удаление текстового файла
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("Файл удален.");
        } else {
            System.out.println("Не удалось удалить файл.");
        }
    }
}

