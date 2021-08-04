import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                
                if (stringArray[0].charAt(0) == '/') {
                    System.out.println("Unknown command");
                    continue;
                }
               
                try {
                    if (stringArray[0].matches("[+]+\\d+")) {
                        String positiveNumber = stringArray[0].replaceAll("\\+", "");
                        System.out.println(Integer.parseInt(positiveNumber));
                        continue;
                    } else {
                        System.out.println(Integer.parseInt(stringArray[0]));
                    }
                } catch(NumberFormatException e) {
                    System.out.println("Invalid expression");
                    break;
                }
                continue;
            }
            
            String expr = userInput.trim();
            expr = getExpression(expr);
            String[] numbers = getValuesArray(expr);
            //Split among plus signs, with new simplifications
            //System.out.println(expr);
            
            if (!validateInput(expr)) {
                sum = getAnswer(numbers, sum);
                System.out.println(sum);
            } else {
                System.out.println("Invalid expression");
            }
            sum = 0;
        }
    }
    
    private boolean validateInput(String expr) {   //leave as void return type for now, possible boolean later
        //final Pattern noLetters = Pattern.compile(".*[a-zA-Z].*");
        final Pattern incomplete = Pattern.compile("\\d+[+\\-*/]+");
        final Pattern noOp = Pattern.compile("\\d+\\s+\\d+");
        
        //System.out.println("Contains letters: " + noLetters.matcher(expr).matches());
        //System.out.println("Incomplete expression: " + incomplete.matcher(expr).matches());
        //System.out.println("Operator absent: " + noOp.matcher(expr).matches());
        
        return incomplete.matcher(expr).matches() && noOp.matcher(expr).matches();
    }
    
    private int getAnswer(String[] numbers, int sum) {
        for (int i = 0; i < numbers.length; i++) {
                sum += Integer.parseInt(numbers[i]);
            }
        return sum;
    }
    
    private String getExpression(String s) {
        return s.replaceAll("\\s{2,}", " ")
                .replaceAll("\\+{2,}", "+")
                .replaceAll("--", "");
    }
    
    private String[] getValuesArray(String s) {
        return s.replaceAll("\\s", "")
                .replaceAll("\\b-", "+-")
                .replaceAll("\\+{2,}", "+")
                .replaceAll("--", "")
                .split("\\+");
    }
    
}
