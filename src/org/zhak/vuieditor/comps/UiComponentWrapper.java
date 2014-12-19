package org.zhak.vuieditor.comps;

import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.zhak.vuieditor.view.UiEditorPane;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/18/14.
 */
public class UiComponentWrapper extends CustomComponent {
    Component component;
    NativeButton btn = new NativeButton();
    CssLayout wrapper=new CssLayout();
    public UiComponentWrapper(final Component component) {
        this.component = component;
        setCompositionRoot(wrapper);
        wrapper.addComponent(this.component);
        btn.setStyleName(ValoTheme.BUTTON_TINY);
        btn.addStyleName("wrapped");
        btn.setWidth("5px");
        btn.setHeight("5px");
        btn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                wrapper.addStyleName("inwrapped");
                UiEditorPane.getInstance().updatePropertyView(component);
            }
        });

        btn.addBlurListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
              wrapper.removeStyleName("inwrapped");
            }
        });
        setSizeUndefined();
        wrapper.addComponent(btn);
    }

    public Component getComponent() {
        return component;
    }
}
