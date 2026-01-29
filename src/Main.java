import ru.stepup.exceptioncast.CastomException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
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


            int countLine = 0;
            int maxLengthLine = Integer.MIN_VALUE;
            int minLengthLine = Integer.MAX_VALUE;
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    countLine++;
                    if (length > maxLengthLine) {
                        maxLengthLine = length;
                    }
                    if (length < minLengthLine) {
                        minLengthLine = length;
                    }

                    if (length > 1024) {
                        throw new CastomException("Кол-во символов в строке не может быть больше 1024 символов");
                    }

//                    if (length > 1024) {
//                        throw new RuntimeException("Кол-во символов в строке не может быть больше 1024 символов");
//                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Общее кол-во строк в файле: " + countLine);
            System.out.println("Максимальная длина строки в файле: " + maxLengthLine);
            System.out.println("Минимальная длина строки в файле: " + minLengthLine);
        }
    }
}
