// Plugin validator to ensure the input string begins with a capital letter [A-Z]

package com.exercise.validsentence;

/**
 *
 * @author nward
 */
public class ValidateCapitalLetter implements ValidationPlugin {
    
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
        
        if ( Character.isUpperCase(str.charAt(0))){
            return true;
        } else {
            s.append("sentence does not begin with a capital letter");
            return false;
        }
    }
    
    public String getResult(){
        return s.toString();
    }
    
}
