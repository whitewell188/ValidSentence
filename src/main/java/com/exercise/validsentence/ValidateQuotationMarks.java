// Plugin validator to ensure an even number of quotation marks are present
// in a string

package com.exercise.validsentence;

/**
 *
 * @author nward
 */
public class ValidateQuotationMarks implements ValidationPlugin {
    
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
        boolean outcome = true;
        
        int num_quotation_marks = 0;
        for ( int i = 0; i < str.length(); i ++){
            if ( str.charAt(i) == '"'){
                num_quotation_marks++;
            }
        }
        
        if ( num_quotation_marks % 2 != 0 ){
            outcome = false;
            s.append("there are " + num_quotation_marks 
                    + " quotation marks when only an even number is allowed");
        }
            
        return outcome;
    }
    
    public String getResult(){
        return s.toString();
    }
    
}
