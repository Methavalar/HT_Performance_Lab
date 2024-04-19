import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Неверное количество аргументов, необходимо передать один файл!");
            return;
        }

        Scanner scanner = new Scanner(new FileReader(args[0]));
        if (!scanner.hasNextInt()) {
            System.out.println("Файл пуст!");
            return;
        }
        var nums = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            nums.add(scanner.nextInt());
        }
        
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int meanNumber = (int) Math.round((sum * 1.0) / nums.size());

        int stepsCount = 0;
        for (int num : nums) {
            stepsCount += Math.abs(meanNumber - num);
        }
        System.out.println(stepsCount);
    }
}