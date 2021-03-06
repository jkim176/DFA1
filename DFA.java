package MyDFA1;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

class DFA {
   
   private Set<Character> alphabet = new HashSet<>();
   private Set<State> states = new HashSet<>();
   private Set<State> acceptStates = new HashSet<>();
   private Transition transitionFunction = new Transition();
   private State startState;
   private String title;
   
   DFA() {
 
   }
   
   DFA(String title) {
      this.title = title;
   }
   
   String getTitle() {
      return title;
   }
   
   void setTitle(String title) {
      this.title = title;
   }
   
   State getStartState() {
      return startState;
   }
   
   void setStartState(State state) {
      startState = state;
   }
   
   Set<Character> getAlphabet() {
      return alphabet;
   }
   
   void addAlphabet(char character) {
      alphabet.add(character);
   }
   
   void printAlphabet() {
      System.out.print("Alphabet: ");
      for(char character: alphabet) {
         System.out.print(character + " ");
      }
      System.out.println();
   }
   
   Set<State> getStates() {
      return states;
   }
   
   void addState(State state) {
      states.add(state);
   }
   
   void printStates() {
      System.out.print("States: ");
      for(State state: states) {
         System.out.print(state.getName() + " ");
      }
      System.out.println();
   }
   
   Set<State> getAcceptStates() {
      return acceptStates;
   }
   
   void addAcceptState(State state) {
      acceptStates.add(state);
   }
   
   void printAcceptStates() {
      System.out.print("Accept States: ");
      for(State state: acceptStates) {
         System.out.print(state.getName() + " ");
      }
      System.out.println();
   }
   
   void addTransition(State source, char input, State target) {
      transitionFunction.addTransition(source, input, target);
   }
   
   void getDFAInfo() {
      System.out.println("DFA: " + getTitle());
      printAlphabet();
      printStates();
      printAcceptStates();
      transitionFunction.printTransition();
      System.out.println("Start State: " + startState.getName() + "\n");
   }
   
   void printSequence(ArrayList<State> sequence) {    // prints state sequence, called by solve method
      System.out.print("State Sequence:\n");
      for(int i = 0; i < sequence.size(); i++) {
         System.out.print(sequence.get(i).getName() + " "); 
      }
      System.out.println();
   }
   
   boolean verifyString(String userString) {    // verifies if string characters are contained in alphabet
      boolean isVerified = true;
      for(int i = 0; i < userString.length(); i++) {
         if(!alphabet.contains(userString.charAt(i))) {     // if character in user string is not in alphabet, return false
            isVerified = false;
            break;
         }
      }
      return isVerified;   
   }
   
   boolean solve(String userString) {     // solve for user string, returns true = accept/ false = reject
      if(!verifyString(userString)) {
         throw new IllegalArgumentException("IAException: String characters not in alphabet. ");
      }
      else {
         ArrayList<State> sequence = new ArrayList<>();     // sequence of states accessed using user string
         State source = startState;
         sequence.add(startState);     // add initial start state to sequence
         
         for(int i = 0; i < userString.length(); i++) {
            char currentChar = userString.charAt(i);
            try {
               State target = transitionFunction.getTarget(source, currentChar);    // find target State: given source State, current char
               
               sequence.add(target);      // add target State to sequence
               source = target;     // target State becomes source State, for next iteration
            }
            catch(NullPointerException ex) {
               System.out.println(ex.getMessage());
               System.exit(1);
            }
         }
         
         State lastState = sequence.get(sequence.size() - 1);
         if(acceptStates.contains(lastState)) {    // if last state is in accept states, return true
            printSequence(sequence);
            return true;
         }
         else {
            printSequence(sequence);
            return false;
         }
      }
   }
}
