/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercise.validsentence;

/**
 *
 * @author nward
 */
public class ValidateQuotationMarks implements ValidationPlugin {
    
    StringBuilder s;
    
    public boolean validate(String str) {
        s = new StringBuilder();
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
