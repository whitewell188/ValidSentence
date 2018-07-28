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
public class ValidateFullStops implements ValidationPlugin {
    
    StringBuilder s;
    
    public boolean validate(String str) {
        s = new StringBuilder();
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
