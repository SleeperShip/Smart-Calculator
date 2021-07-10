import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int sum = 0;
        int term1 = 0;
        int term2 = 0;
        char op = ' ';
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
            expr = simplifySymbols(expr);
            String[] exprArray = expr.split("[+-/*]");
            
            for (int i = 0; i < exprArray.length; i++) {
                System.out.print(exprArray[i] + " ");
            }
            //System.out.println(expr.toString());
            
            /*
            try {
                for (int i = 2; i < stringArray.length; i++) {
                    term1 = Integer.parseInt(stringArray[i-2]);
                    op = 
                }
            } catch(NumberFormatException e) {
                System.exit(0);
            }
           
            System.out.println(sum);
            sum = 0;
            */
           
        }
    }
    
    private static String simplifySymbols(String localExpr) {
            String newExpr = simplifyPlusSymbols(localExpr); 
            newExpr = simplifyMinusSymbols(newExpr); 
            newExpr = simplifyNegatives(newExpr); 
            newExpr = simplifyPlusSymbols(newExpr); 
            newExpr = simplifyMinusSymbols(newExpr); 
            newExpr = simplifyNegatives(newExpr);
            return newExpr;
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
