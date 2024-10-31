import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class ZipManager {

    // Создание Zip-архива
    public static void createZip(String zipFilePath, String fileToAdd) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            File file = new File(fileToAdd);
            try (FileInputStream fis = new FileInputStream(file)) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }
                System.out.println("Файл добавлен в архив.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Распаковка Zip-архива
    public static void unzipFile(String zipFilePath, String destDir) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                Path newPath = Paths.get(destDir, zipEntry.getName());
                Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                zipEntry = zis.getNextEntry();
            }
            System.out.println("Архив распакован.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

