import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int sum = 0;
        Scanner scanner = new Scanner(System.in);
       
        while(true) {
            String userInput = scanner.nextLine();
            String[] stringArray = userInput.trim().split("\\s+");
           
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
            expr = expr.replaceAll("\\s", "");
            expr = simplifyPlusSymbols(expr); //1
            System.out.println("1 " + expr);
            expr = simplifyMinusSymbols(expr); //2
            System.out.println("2 " + expr);
            expr = simplifyNegatives(expr); //3
            System.out.println("3 " + expr);
            expr = simplifyPlusSymbols(expr); //4
            System.out.println("4 " + expr);
            expr = simplifyMinusSymbols(expr); //5
            System.out.println("5 " + expr);
            expr = simplifyNegatives(expr);
           
            System.out.println(expr.toString());
            /*
            try {
                for (int i = 0; i < stringArray.length; i++) {
                    sum += Integer.parseInt(stringArray[i]);
                }
            } catch(NumberFormatException e) {
                System.exit(0);
            }
           
            System.out.println(sum);
            sum = 0;
            */
           
                       
        }
    }
    
    private static String simplifyNegatives(String localExpr) {
        String newExpr = localExpr.replaceAll("(-\\+|\\+-)", "-");
        return newExpr;
    }
   
    private static String simplifyPlusSymbols(String localExpr) {
        String newExpr = localExpr.replaceAll("[+]{2,}", "+");
        return newExpr;
    }
   
    private static String simplifyMinusSymbols(String localExpr) {
        String newExpr = localExpr.replaceAll("[-]{2}", "+");
        return newExpr;
    }
}
