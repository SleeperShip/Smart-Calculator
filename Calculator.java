import java.util.Scanner;

public class Calculator {
    
    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        
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
            String[] numbers = getExpression(expr);
            //Split among plus signs, with new simplifications
            
            sum = getAnswer(numbers, sum);
            System.out.println(sum);
            sum = 0;
        }
    }
    
    
    
    private int getAnswer(String[] numbers, int sum) {
        for (int i = 0; i < numbers.length; i++) {
                sum += Integer.parseInt(numbers[i]);
            }
        return sum;
    }
    
    private String[] getExpression(String s) {
        return s.replaceAll("\\s", "")
                .replaceAll("\\b-", "+-")
                .replaceAll("\\+{2,}", "+")
                .replaceAll("--", "")
                .split("\\+");
    }
}
