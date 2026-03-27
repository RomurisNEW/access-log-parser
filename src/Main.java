import ru.courses.project.LogEntry;
import ru.courses.project.Statistics;
import ru.stepup.exceptioncast.CastomException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
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

            Statistics stats = new Statistics();
            int countLine = 0;
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;


                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    countLine++;

                    try {
                        LogEntry entry = new LogEntry(line);
                        stats.addEntry(entry);
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Пропущена строка: " + line);
                    }

                    if (length > 1024) {
                        throw new CastomException("Кол-во символов в строке не может быть больше 1024 символов");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Общее кол-во строк в файле: " + countLine);
            System.out.println("Средний трафик за час: "  + stats.getTrafficRate());
            System.out.println("Min Time: " + stats.getMinTime());
            System.out.println("Max Time: " + stats.getMaxTime());

//            System.out.println("Список найденых страниц:");
//            for(String page : stats.getPages()){
//                System.out.println(page);
//            }

//            System.out.println("Список НЕнайденых страниц:");
//            for(String notFoundPage : stats.getNotFoundPages()){
//                System.out.println(notFoundPage);
//            }
//

            System.out.println("Статистика BROWSER:");
            for(Map.Entry<String, Double> entry : stats.getBrowserStatistics().entrySet()){
                System.out.println(entry.getKey() + " = " + String.format("%.6f", entry.getValue()));
            }

            System.out.println("");
            System.out.println("Статистика ОС:");
            for(Map.Entry<String, Double> entry : stats.getOsStatistics().entrySet()){
                System.out.println(entry.getKey() + " = " + String.format("%.6f", entry.getValue()));
            }

            System.out.println("");
            System.out.println("Среднее количество посещений в час:");
            System.out.println(String.format("%.2f", stats.getVisitInHour()));

            System.out.println("Среднее количество ошибок в час:");
            System.out.println(String.format("%.2f", stats.getErrorsInHour()));

            System.out.println("Средняя посещаемость на пользователя:");
            System.out.println(String.format("%.2f", stats.getAverageVisitsPerUser()));

        }
    }
}
