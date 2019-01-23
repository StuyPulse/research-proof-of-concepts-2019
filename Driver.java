import java.util.Scanner;

public class Driver {
        public static void main(String[] args) {
                Keyboard keyboard = new Keyboard();
                String put = "";
                Scanner scan = new Scanner(System.in);
                do {
                        put = scan.nextLine();
                        System.out.println("This is the pressed key: " + keyboard.getPressed());
                        System.out.println("This is the released key: " + keyboard.getReleased());
                        System.out.println("This is the typed key: " + keyboard.getTyped());
                }
                while(put != "end");
                scan.close();
        }
}
