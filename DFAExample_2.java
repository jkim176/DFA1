package MyDFA1;

import java.util.Scanner;

public class DFAExample_2 {
   public static void main(String[] args) {
   
      Scanner input = new Scanner(System.in);
      
      DFA dfa = new DFA("0(00)*(11)*");
      
      dfa.addAlphabet('0');     
      dfa.addAlphabet('1');
      
      State q0 = new State("Q0");
      State q1 = new State("Q1");
      State q2 = new State("Q2");
      State q3 = new State("Q3");
      State q4 = new State("Q4");
      
      dfa.addState(q0);
      dfa.addState(q1);
      dfa.addState(q2);
      dfa.addState(q3);
      dfa.addState(q4);
      
      dfa.setStartState(q0);
      
      dfa.addAcceptState(q1);
      dfa.addAcceptState(q3);
      
      dfa.addTransition(q0, '0', q1);
      dfa.addTransition(q0, '1', q4);
      dfa.addTransition(q1, '0', q0);
      dfa.addTransition(q1, '1', q2);
      dfa.addTransition(q2, '0', q4);
      dfa.addTransition(q2, '1', q3);
      dfa.addTransition(q3, '0', q4);
      dfa.addTransition(q3, '1', q2);
      dfa.addTransition(q4, '0', q4);
      dfa.addTransition(q4, '1', q4);
      
      String userString;
      boolean restart = true;
      while(restart){      // program restart loop
         System.out.println("Enter a string of 0's and 1's: ");
         userString = input.next();
         
         //dfa.getDFAInfo();
         
         try {
            boolean acceptance = dfa.solve(userString);
         
            if(acceptance)
               System.out.println(userString + " is accepted by machine: " + dfa.getTitle() + "\n");
            else
               System.out.println(userString + " is rejected by machine: " + dfa.getTitle() + "\n");
         }
         catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
         }
         
         System.out.println("To restart, press any key.  To exit, enter 0.");
         String restartString = input.next();
         if(restartString.charAt(0) == '0'){
            restart = false;
            System.out.println("Program has terminated. ");
         }
         else
            input.nextLine();
      }
   }
}
