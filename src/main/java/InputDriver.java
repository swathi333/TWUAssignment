import java.util.Scanner;

//It takes input
public class InputDriver {

    public int readMenuInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }

    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
