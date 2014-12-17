package com;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.zhak.vuieditor.UiEditor;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/17/14.
 */
@Theme("dashboard")
@Widgetset("org.zhak.vuieditor.WidgetSet")
@Title("Vaadin UI Editor")
public class MyVaadinApplication extends UI {
    UiEditor uiEditor = new UiEditor();
    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);
        uiEditor.setSizeFull();
        layout.addComponent(uiEditor);
    }
}
