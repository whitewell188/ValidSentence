

package com.exercise.validsentence;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author nward
 */
public class ValidateCapitalLetterTest {
    
    @Test
    public void testCapitalLetterExists(){
        
        ValidateCapitalLetter testObj = new ValidateCapitalLetter();
        
        String testString = new String("Sentence with capital letter");
        
        Assert.assertTrue(testObj.validate(testString));
        
    }
    
    @Test
    public void testCapitalLetterDoesNotExists(){
        
        ValidateCapitalLetter testObj = new ValidateCapitalLetter();
        
        String testString = new String("sentence without capital letter");
        
        Assert.assertFalse(testObj.validate(testString));
        
    }
    
    @Test
    public void testEmptyString(){
        
        ValidateCapitalLetter testObj = new ValidateCapitalLetter();
        
        String testString = new String();
        
        Assert.assertFalse(testObj.validate(testString));
        
    }
}
