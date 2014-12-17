package org.zhak.vuieditor.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/14/14.
 */
public class ToolbarPane extends CssLayout {
    private ToolbarPane() {

        addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        btnPreview.addStyleName(ValoTheme.BUTTON_SMALL);
        btnPreview.setIcon(FontAwesome.TRY);
        addComponent(btnPreview);
    }

    public static ToolbarPane getInstance() {
        if (__instance == null) __instance = new ToolbarPane();
        return __instance;
    }

    @Override
    protected String getCss(Component c) {
        return "margin:5px";
    }

    private static ToolbarPane __instance;
    private Button btnPreview = new Button("Preview");
}
