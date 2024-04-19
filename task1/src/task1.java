import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n <= 0 || m <= 0) {
                System.out.println("Ошибка ввода значений n и m.\nm и n должны быть больше нуля");
                throw new RuntimeException();
            }

            var mas = new int[n];
            for (int i = 0; i < n; i++) {
                mas[i] = i + 1;
            }

            if (m > n) {
                m = m - n * (m / n);
            }

            int startNumber = 0;
            int endNumber;
            do {
                System.out.print(mas[startNumber]);
                endNumber = startNumber + m - 1;
                startNumber = endNumber < n ? endNumber : endNumber - n;
            } while (startNumber != 0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}