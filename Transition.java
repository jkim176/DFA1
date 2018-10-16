package MyDFA1;

import java.util.Map;
import java.util.HashMap;

class Transition {
   private Map<State, Map<Character, State>> transition = new HashMap<>();
   
   Transition() {
   
   }
   
   Map<State, Map<Character, State>> getTransitionFunction() {
      return transition;
   }
   
   void addTransition(State source, char input, State target) {
      if(!transition.containsKey(source)) {     // if key source is not in transition, add new Map
         Map<Character, State> value = new HashMap<>();
         transition.put(source, value);
         
         value.put(input, target);     // put into nested Map
      }
      else {
         Map<Character, State> value = transition.get(source);    // else retrieve nested map
         
         value.put(input, target);
      }
   }
   
   State getTarget(State source, char input) {
      State target = transition.get(source).get(input);
      if(target == null) {
         throw new NullPointerException("NPException: Transition not found for source: " + source.getName() + " input: " + input);
      }
      
      else
         return target;
   }
   
   void printTransition() {
      System.out.print("Transition Function: \n");
      for(Map.Entry<State, Map<Character, State>> entry: transition.entrySet()) {  // for each entry in transitions map
         State source = entry.getKey();     // get key, source State
         Map<Character, State> value = entry.getValue();       // get value, single-entry Map(input Character, target State)
         
         for(Map.Entry<Character, State> e: value.entrySet()) {     // for each entry(1) in nested Map...
            char input = e.getKey();
            State target = e.getValue();
            System.out.print("\tSource State " + source.getName() + " --> input " + input + " --> Target State " + target.getName() + "\n");
         }       
      }
   }
}
