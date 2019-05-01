package org.vaadin.switchinglabel.demo;

import org.vaadin.switchinglabel.SwitchingLabel;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("SwitchingLabel Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize SwitchingLabel components
        final SwitchingLabel label1 = new SwitchingLabel("First text","Second text");
        final SwitchingLabel label2 = new SwitchingLabel();
        label2.setContentMode(ContentMode.HTML);
        label2.setValue(VaadinIcons.ARROW_CIRCLE_UP.getHtml());
        label2.setSecondValue(VaadinIcons.ARROW_CIRCLE_UP_O.getHtml());
        label2.setSwitchingTime(500);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.addComponents(label1,label2);
        layout.setComponentAlignment(label1, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(label2, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
}
