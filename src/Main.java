import ru.stepup.exceptioncast.CastomException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int countPath = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Укажите путь к файлу без кавычек в начале и конце: ");
            String path = scanner.nextLine();


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
                System.out.println("Файла не существует");
                continue;
            }

            System.out.println("Путь указан верно");
            countPath++;
            System.out.println("Это файл номер: " + countPath);


            int countLine = 0;
            int yandexBotCount = 0;
            int googleBotCount = 0;
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;


                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    countLine++;

                    int start = line.indexOf("(compatible");

                    if (start == -1) {
                        continue;
                    }

                    String rightName = "";
                    String firstBrackets = line.substring(start + 1);
                    String[] parts = firstBrackets.split(";");
                    if (parts.length >= 2) {
                        String fragment = parts[1];
                        rightName = fragment.split("/")[0].trim();
                    }

                    if (rightName.equals("YandexBot")){
                        yandexBotCount++;
                    } else if (rightName.equals("Googlebot")){
                        googleBotCount++;
                    }

//                    if(line.contains("YandexBot")){
//                        yandexBotCount++;
//                    } else if (line.contains("Googlebot")){
//                        googleBotCount++;
//                    }

                    if (length > 1024) {
                        throw new CastomException("Кол-во символов в строке не может быть больше 1024 символов");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Общее кол-во строк в файле: " + countLine);
            System.out.println("Кол-во YandexBot встречаемых в первых скобках: " + yandexBotCount + ". Доля запросов " +
                    "от YandexBot = " + String.format("%.3f", (double) yandexBotCount/countLine));
            System.out.println("Кол-во Googlebot встречаемых в первых скобках: " + googleBotCount + ". Доля запросов " +
                    "от Googlebot = " + String.format("%.3f", (double) googleBotCount/countLine));
        }
    }
}
