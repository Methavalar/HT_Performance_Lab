import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class task2 {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов, необходимо передать два файла!");
            return;
        }
        Scanner scanner1 = new Scanner(new FileReader(args[0]));
        Scanner scanner2 = new Scanner(new FileReader(args[1]));
        if (!scanner1.hasNextFloat() || !scanner2.hasNextFloat()) {
            System.out.println("Файлы пусты!");
            return;
        }
        float circleCenterX = scanner1.nextFloat();
        float circleCenterY = scanner1.nextFloat();
        float circleRadius = scanner1.nextFloat();
        int i = 0;
        while (scanner2.hasNextFloat() && i < 100) {
            float x = scanner2.nextFloat();
            float y = scanner2.nextFloat();
            int position = getPosition(circleCenterX, circleCenterY, circleRadius, x, y);
            System.out.println(position);
            i++;
        }
    }
    private static int getPosition(float circleCenterX, float circleCenterY, float circleRadius , float x, float y) {
        float distance;
        distance = (float) Math.sqrt(Math.pow(circleCenterX - x, 2) + Math.pow(circleCenterY - y, 2));
        if (Math.abs(distance - circleRadius) < 1e-9) {
            return 0;
        } else if (distance < circleRadius) {
            return 1;
        } else {
            return 2;
        }
    }
}