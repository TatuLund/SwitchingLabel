package org.vaadin.switchinglabel;

import org.vaadin.switchinglabel.client.SwitchingLabelState;

import com.vaadin.ui.Label;

/**
 * SwitchingLabel is a Label with two values which are alternated with
 * given switching time interval.
 * 
 * @author Tatu Lund
 *
 */
public class SwitchingLabel extends Label {

    /**
     * Creates an empty SwitchingLabel.
     */
    public SwitchingLabel() {
    	super();
    }
    
    /**
     * Creates a SwitchingLabel with two alternating values, null is converted to an empty string
     * 
     * @param value1 The first value
     * @param value2 The second value
     */
    public SwitchingLabel(String value1, String value2) {
    	super(value1);
    	setSecondValue(value2);
    }

    /**
     * Creates a SwitchingLabel with two alternating values, null is converted to an empty string
     * 
     * @param value1 The first value
     * @param value2 The second value
     * @param switchingTime Switching interval in milliseconds 
     */
    public SwitchingLabel(String value1, String value2, int switchingTime) {
    	super(value1);
    	setSecondValue(value2);
    	setSwitchingTime(switchingTime);
    }
    
    /**
     * Sets the two alternating values, null is converted to an empty string
     * 
     * @param value1 The first value
     * @param value2 The second value
     */
    public void setValues(String value1, String value2) {
    	setValue(value1);
    	setSecondValue(value2);
    }
    
    /**
     * Set the second value, null is converted to an empty string
     * 
     * @param value The second value used in the SwitchingLabel
     */
    public void setSecondValue(String value) {
        if (value == null) {
            getState().text2 = "";
        } else {
            getState().text2 = value;
        }    	    	
    }
    
    /**
     * Get the currently set second value
     * 
     * @return The second value as String
     */
    public String getSecondValue() {
    	return getState().text2;
    }
    
    /**
     * Set the switching interval in milliseconds (default 5000)
     * 
     * @param switchingTime Switching interval in milliseconds 
     */
    public void setSwitchingTime(int switchingTime) {
    	getState().switchingTime = switchingTime;
    }
    
    /**
     * Get the current switching interval in milliseconds
     * 
     * @return Current switching interval in milliseconds
     */
    public int getSwitchingTime() {
    	return getState().switchingTime;
    }
    
    // We must override getState() to cast the state to SwitchingLabelState
    @Override
    protected SwitchingLabelState getState() {
        return (SwitchingLabelState) super.getState();
    }
    
}
