import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Добро пожаловать! Введите команду для работы с файлами и архивами.");
        System.out.println("Доступные команды:");
        System.out.println("1. showDiskInfo - Показать информацию о дисках");
        System.out.println("2. createTextFile - Создать текстовый файл и записать строку");
        System.out.println("3. readTextFile - Прочитать текстовый файл");
        System.out.println("4. deleteFile - Удалить файл");
        System.out.println("5. createJsonFile - Создать JSON файл с объектом");
        System.out.println("6. readJsonFile - Прочитать JSON файл");
        System.out.println("7. createXml - Создать новый XML файл с одним объектом Person");
        System.out.println("8. addXml - Добавить новый объект Person в XML файл");
        System.out.println("9. readXml - Прочитать и вывести содержимое XML файла");
        System.out.println("10. createZip - Создать ZIP архив и добавить файл");
        System.out.println("11. unzipFile - Распаковать ZIP архив");
        System.out.println("12. exit - Выйти из программы");
        System.out.println("13. deleteJsonFile - Удалить JSON файл");
        System.out.println("14. deleteXmlFile - Удалить XML файл");
        System.out.println("15. deleteZipFile - Удалить ZIP файл");

        while (true) {
            System.out.print("\nВведите команду: ");
            command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "showdiskinfo":
                    FileManager.showDiskInfo();
                    break;

                case "createtextfile":
                    System.out.print("Введите имя файла: ");
                    String textFilePath = scanner.nextLine();
                    System.out.print("Введите текст для записи в файл: ");
                    String textContent = scanner.nextLine();
                    FileManager.createAndWriteTextFile(textFilePath, textContent);
                    break;

                case "readtextfile":
                    System.out.print("Введите имя файла для чтения: ");
                    String readTextFilePath = scanner.nextLine();
                    FileManager.readTextFile(readTextFilePath);
                    break;

                case "deletefile":
                    System.out.print("Введите имя файла для удаления: ");
                    String deleteFilePath = scanner.nextLine();
                    FileManager.deleteFile(deleteFilePath);
                    break;

                case "createjsonfile":
                    System.out.print("Введите имя JSON файла: ");
                    String jsonFilePath = scanner.nextLine();
                    System.out.print("Введите имя для объекта: ");
                    String personName = scanner.nextLine();
                    System.out.print("Введите возраст для объекта: ");
                    int personAge = Integer.parseInt(scanner.nextLine());
                    Person person = new Person(personName, personAge);
                    JsonManager.writeJsonFile(jsonFilePath, person);
                    break;

                case "readjsonfile":
                    System.out.print("Введите имя JSON файла для чтения: ");
                    String readJsonFilePath = scanner.nextLine();
                    JsonManager.readJsonFile(readJsonFilePath);
                    break;

                case "deletejsonfile":
                    System.out.print("Введите имя JSON файла для удаления: ");
                    String jsonDeletePath = scanner.nextLine();
                    deleteFile(jsonDeletePath);
                    break;

                case "createxml":
                    // Запрос данных для нового XML файла
                    System.out.print("Введите имя файла: ");
                    readTextFilePath = scanner.nextLine();
                    person = createPersonFromConsole(scanner);
                    XMLManager.createXMLWithPerson(readTextFilePath, person);
                    break;

                case "addxml":
                    // Запрос данных и добавление в существующий XML файл
                    System.out.print("Введите имя файла: ");
                    readTextFilePath = scanner.nextLine();
                    Person newPerson = createPersonFromConsole(scanner);
                    XMLManager.addPersonToXMLFile(readTextFilePath, newPerson);
                    break;

                case "readxml":
                    // Чтение и вывод содержимого XML файла
                    System.out.print("Введите имя файла: ");
                    readTextFilePath = scanner.nextLine();
                    XMLManager.readXML(readTextFilePath);
                    break;

                case "readxmlfile":
                    System.out.print("Введите имя XML файла для чтения: ");
                    String readXmlFilePath = scanner.nextLine();
                    XMLManager.readXML(readXmlFilePath);
                    break;

                case "deletexmlfile":
                    System.out.print("Введите имя XML файла для удаления: ");
                    String xmlDeletePath = scanner.nextLine();
                    deleteFile(xmlDeletePath);
                    break;

                case "createzip":
                    System.out.print("Введите имя ZIP архива: ");
                    String zipFilePath = scanner.nextLine();
                    System.out.print("Введите имя файла для добавления в архив: ");
                    String fileToZip = scanner.nextLine();
                    ZipManager.createZip(zipFilePath, fileToZip);
                    break;

                case "unzipfile":
                    System.out.print("Введите имя ZIP архива для распаковки: ");
                    String unzipFilePath = scanner.nextLine();
                    System.out.print("Введите каталог для распаковки: ");
                    String destDir = scanner.nextLine();
                    ZipManager.unzipFile(unzipFilePath, destDir);
                    break;

                case "deletezipfile":
                    System.out.print("Введите имя ZIP файла для удаления: ");
                    String zipDeletePath = scanner.nextLine();
                    deleteFile(zipDeletePath);
                    break;

                case "exit":
                    System.out.println("Выход из программы...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверная команда. Повторите попытку.");
            }
        }
    }
    private static Person createPersonFromConsole(Scanner scanner) {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        return new Person(name, age);
    }
    private static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("Файл успешно удален: " + filePath);
        } else {
            System.out.println("Ошибка при удалении файла: " + filePath);
        }
    }
}
