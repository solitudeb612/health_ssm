import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int s;
        int y;
        int sum = 0;
        boolean flag = true;
        while (flag){
            y = i % 10;
            s = i / 10;
            if(y == 0 && s == 0){
                flag = false;
            }
            sum += y;
            i = s;
        }
        System.out.println(sum);
    }
}
