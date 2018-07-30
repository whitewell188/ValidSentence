// Interface class that defines the functions plugins need to fulfill
package com.exercise.validsentence;

/**
 *
 * @author nward
 */
public interface ValidationPlugin {
    
    /**
     *
     * @return true if validation rule is met, false otherwise
     * 
     */
    boolean validate(String str);
    
    /**
     *
     * @return any error strings created during validation
     * 
     */
    String getResult();
    
}
