package MyDFA1;

import java.util.Scanner;

public class DFAExample_1 {
   public static void main(String[] args) {
   
      Scanner input = new Scanner(System.in);
      
      DFA dfa = new DFA("0*1*");
      
      dfa.addAlphabet('0');
      dfa.addAlphabet('1');
      
      State q1 = new State("Q1");
      State q2 = new State("Q2");
      
      dfa.addState(q1);
      dfa.addState(q2);
      
      dfa.addAcceptState(dfa.getStartState());
      dfa.addAcceptState(q1);
      
      dfa.addTransition(dfa.getStartState(), '0', dfa.getStartState());
      dfa.addTransition(dfa.getStartState(), '1', q1);
      dfa.addTransition(q1, '0', q2);
      dfa.addTransition(q1, '1', q1);
      dfa.addTransition(q2, '0', q2);
      dfa.addTransition(q2, '1', q2);
      
      String userString;
      boolean restart = true;
      while(restart){      // program restart loop
         System.out.println("Enter a string of 0's and 1's: ");
         userString = input.next();
         
         //dfa.getDFAInfo();
         
         try {
            boolean acceptance = dfa.solve(userString);
         
            if(acceptance)
               System.out.println(userString + " is accepted by machine: " + dfa.getName());
            else
               System.out.println(userString + " is rejected by machine: " + dfa.getName());
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
