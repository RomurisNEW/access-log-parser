import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите первое число: ");
        int number1 = new Scanner(System.in).nextInt();
        System.out.print("Введите второе число: ");
        int number2 = new Scanner(System.in).nextInt();

        int x = number1;
        int y = number2;
        System.out.println("Сумма: "+(x+y));
        System.out.println("Разность: "+(x-y));
        System.out.println("Произведение: "+(x*y));
        System.out.println("Частное: "+((double)x/(double)y));
    }
}
