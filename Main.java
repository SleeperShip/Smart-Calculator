import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int sum = 0;
        Scanner scanner = new Scanner(System.in);
       
        while (true) {
            String userInput = scanner.nextLine();
            String[] stringArray = userInput.trim().split("\\s+");;
           
            if (userInput.isEmpty()) {
                continue;
            }
           
            if (stringArray.length == 1) {
                if (stringArray[0].equals("/exit")) {
                    System.out.println("Bye!");
                    break;
                }
               
                if (stringArray[0].equals("/help")) {
                    System.out.println("The program calculates the sum of numbers");
                    continue;
                }
               
                try {
                    System.out.println(Integer.parseInt(stringArray[0]));
                } catch(NumberFormatException e) {
                    System.out.println("Invalid input. Integers only.");
                    break;
                }
                continue;
            }
           
            String expr = userInput.trim();
            
            expr = getExpression(expr);
            String[] numbers = expr.split("\\+");
            //System.out.println("Split among plus signs, with new simplificatinos: " );
            
            for (int i = 0; i < numbers.length; i++) {
                sum += Integer.parseInt(numbers[i]);
            }
            
            System.out.println(sum);
            sum = 0;
        }
    }
    
    public static String getExpression(String s) {
        return s.replaceAll("\\s", "")
                .replaceAll("\\b-", "+-")
                .replaceAll("\\+{2,}", "+")
                .replaceAll("--", "");
    }
}
