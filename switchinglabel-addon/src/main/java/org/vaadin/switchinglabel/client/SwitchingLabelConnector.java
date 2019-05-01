package org.vaadin.switchinglabel.client;

import org.vaadin.switchinglabel.SwitchingLabel;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.user.client.Timer;
import com.vaadin.client.WidgetUtil;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VLabel;
import com.vaadin.client.ui.label.LabelConnector;
import com.vaadin.shared.ui.Connect;

@Connect(SwitchingLabel.class)
public class SwitchingLabelConnector extends LabelConnector {
	Timer timer = null;
	String currentText = "";
	
    public SwitchingLabelConnector() {
        setTimer();
    }
    
    private void setText(String text) {
    	currentText = text;
        VLabel widget = getWidget();
        boolean sinkOnloads = false;
        switch (getState().contentMode) {
        case PREFORMATTED:
            PreElement preElement = Document.get().createPreElement();
            preElement.setInnerText(text);
            // clear existing content
            widget.setHTML("");
            // add preformatted text to dom
            widget.getElement().appendChild(preElement);
            break;

        case TEXT:
            widget.setText(text);
            break;

        case HTML:
            sinkOnloads = true;
            widget.setHTML(text);
            break;
        }    	
        if (sinkOnloads) {
            WidgetUtil.sinkOnloadForImages(widget.getElement());
        }
        
    }

	private void setTimer() {
		timer = new Timer() {
			@Override
			public void run() {
				if (!getState().text2.isEmpty()) {
					if (currentText.equals(getState().text)) {
						setText(getState().text2);
					} else {
						setText(getState().text);
					}
				}
			}
		};
		timer.scheduleRepeating(getState().switchingTime);
	}    
    
	@Override
	public void onUnregister() {
		super.onUnregister();
	    if (timer != null) timer.cancel();
    }
	   
    @Override
    public VLabel getWidget() {
        return (VLabel) super.getWidget();
    }

    @Override
    public SwitchingLabelState getState() {
        return (SwitchingLabelState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        if (stateChangeEvent.hasPropertyChanged("switchingTime")) {
        	if (timer != null) timer.cancel();
        	setTimer();
        }
        if (currentText.isEmpty()) {
        	currentText = getState().text;
        }
    }
}
