/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercise.validsentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nward
 */
public class ValidateNumberUsage implements ValidationPlugin {
    
    StringBuilder s;
    int lowest_allowed_numeric = 13;
    
    public boolean validate(String str) {
        s = new StringBuilder();
        boolean outcome = true;
        
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(str);
        while (m.find()) {
            if (Integer.parseInt(m.group()) < lowest_allowed_numeric){
                outcome = false;
                s.append(", invalid content \'" + m.group() + "\' at position " 
                        + (m.start()+1)
                        + " - numbers less than " + lowest_allowed_numeric 
                        + " must be spelled out");
            }
        }
            
        return outcome;
    }
    
    public String getResult(){
        return s.toString();
    }
    
}
