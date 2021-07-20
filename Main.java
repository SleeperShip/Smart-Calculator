import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int sum = 0;
        int term1 = 0;
        int term2 = 0;
        char op = ' ';
        Scanner scanner = new Scanner(System.in);
       
        while (true) {
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
            System.out.println("infix : " + expr.toString());
            expr = infixToPostfix(expr);
            //int ans = evaluatePostfix(expr);
            System.out.println("postfix : " + expr.toString());
            //System.out.println("answer = " + ans);
            
            /*
            String[] exprArray = expr.split("[+-/*]");
            String[] symArray = expr.split("\\d+");
            */
            
            /*
            for (int i = 0; i < expr.length; i++) {
                System.out.print(expr[i] + " ");
            }
            
        
            for (int i = 0; i < exprArray.length; i++) {
                System.out.print(exprArray[i] + " ");
            }
            
            for (int i = 0; i < symArray.length; i++) {
                System.out.print(symArray[i] + " ");
            }
            */
            
            
            
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
    
    // A utility function to return
    // precedence of a given operator
    // Higher returned value means
    // higher precedence
    static int Prec(char ch) {
        switch (ch) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        case '^':
            return 3;
        }
        return -1;
    }
      
    // The main method that converts
    // given infix expression
    // to postfix expression.
    static String infixToPostfix(String exp) {
        // initializing empty String for result
        String result = new String("");
         
        // initializing empty stack
        Stack<Character> stack = new Stack<>();
         
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
             
            if (Character.isLetterOrDigit(c)) {   // If the scanned character is an
                                                // operand, add it to output.
                result += c;
            } else if (c == '(') {   // If the scanned character is an '(',
                                     // push it to the stack.
                stack.push(c);
            } else if (c == ')') {              //  If the scanned character is an ')',
                                                // pop and output from the stack
                                                // until an '(' is encountered.
                    while (!stack.isEmpty() &&
                        stack.peek() != '(') {
                            result += stack.pop();
                    }
                 
                    stack.pop();
            } else { // an operator is encountered
                while (!stack.isEmpty() && Prec(c)
                    <= Prec(stack.peek())) {
                   
                    result += stack.pop();
             }
                stack.push(c);
            }
      
        }
      
        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if(stack.peek() == '(') {
                return "Invalid Expression";
            }
            result += stack.pop();
         }
        return result;
    }
    
    static int evaluatePostfix(String exp){
        //create a stack
        Stack<Integer> stack = new Stack<>();
          
        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
              
            // If the scanned character is an operand (number here),
            // push it to the stack.
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            }        
            else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                  
                switch (c) {
                    case '+':
                    stack.push(val2+val1);
                    break;
                      
                    case '-':
                    stack.push(val2- val1);
                    break;
                      
                    case '/':
                    stack.push(val2/val1);
                    break;
                      
                    case '*':
                    stack.push(val2*val1);
                    break;
              }
            }
        }
        return stack.pop();    
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
