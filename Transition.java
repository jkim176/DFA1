package MyDFA1;

import java.util.Map;
import java.util.HashMap;

class Transition {
   private Map<Map<State, Character>, State> transition = new HashMap<>();
   
   Transition() {
   
   }
   
   Map<Map<State, Character>, State> getTransitionFunction() {
      return transition;
   }
   
   void addTransition(State source, char input, State target) {
      Map<State, Character> temp = new HashMap<>();
      temp.put(source, input);
      transition.put(temp, target);
   }
   
   State getTarget(State source, char input) {
      for(Map.Entry<Map<State, Character>, State> entry: transition.entrySet()) {
         Map<State, Character> key = entry.getKey();
         State target = entry.getValue();
         
         if(key.containsKey(source) && key.containsValue(input)) {
            return target;
         }
      }
      throw new IllegalArgumentException("IAException: No transition found for source: " + source + " char: " + input + "\n");    // if no target is returned, transition does not exist
   }
   
   void printTransition() {
      System.out.print("Transition Function: \n");
      for(Map.Entry<Map<State, Character>, State> entry: transition.entrySet()) {  // for each entry in transitions map
         Map<State, Character> key = entry.getKey();     // get key, which is a single-entry nested Map(source state, input char)
         State target = entry.getValue();       // get value, target State
         
         for(Map.Entry<State, Character> e: key.entrySet()) {     // for each entry(1) in nested Map...
            State source = e.getKey();
            char input = e.getValue();
            System.out.print("\tSource State " + source.getName() + " --> input " + input + " --> Target State " + target.getName() + "\n");
         }       
      }
   }
}
