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
public class ValidateCapitalLetter implements ValidationPlugin {
    
    StringBuilder s;
    
    public boolean validate(String str) {
        s = new StringBuilder();
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
