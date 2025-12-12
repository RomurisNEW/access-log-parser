import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int countPath = 0;
        while (true) {
            System.out.println("Укажите путь к файлу без кавычек в начале и конце: ");
            String path = new Scanner(System.in).nextLine();

            File file = new File(path);
            boolean fileExist = file.exists();

            boolean isDirectory = file.isDirectory();

            if (isDirectory) {
                System.out.println("Указанный путь ведет к папке, а не файлу");
                continue;
            }

            if (fileExist) {
                System.out.print("Файл существует. ");
            } else {
                System.out.println("По данному пути нет файла");
                continue;
            }

            System.out.println("Путь указан верно");
            countPath++;
            System.out.println("Это файл номер: " + countPath);
        }
    }
}
