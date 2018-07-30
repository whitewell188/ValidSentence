// Plugin validator to examine the period character(s) in a string.
// Only one period character is valid and it must be the last character
package com.exercise.validsentence;

/**
 *
 * @author nward
 * 
 */
public class ValidateFullStops implements ValidationPlugin {
    
    StringBuilder s;
    
    /**
     *
     * @return true if validation rule is met, false otherwise
     * 
     */
    public boolean validate(String str) {
        s = new StringBuilder();
        if ( str == null || str.length() == 0 ){
            s.append("string is not valid");
            return false;
        }
        if ( str == null || str.length() == 0 ){
            s.append("string is not valid");
            return false;
        }
        boolean outcome = true;
        if ( str.endsWith(".") == false ) {
            outcome = false;
            s.append("invalid character \'" + str.charAt(str.length()-1) + 
                    "\' at position " 
                    + str.length()
                    + " when \'.\' expected");
        }
        
        int num_full_stops = 0;
        for ( int i = 0; i < str.length(); i ++){
            if ( str.charAt(i) == '.'){
                num_full_stops++;
            }
        }
        
        if ( num_full_stops > 1 ){
            outcome = false;
            s.append(", invalid number of periods [" + num_full_stops 
                    + "] - only one is allowed, at end of sentence");
        }
            
        return outcome;
    }
    
    public String getResult(){
        return s.toString();
    }
    
}
