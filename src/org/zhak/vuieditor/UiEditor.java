package org.zhak.vuieditor;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.zhak.vuieditor.view.PropertyPane;
import org.zhak.vuieditor.view.ToolbarPane;
import org.zhak.vuieditor.view.ToolboxPane;
import org.zhak.vuieditor.view.UiEditorPane;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/14/14.
 */
public class UiEditor extends Panel {

    private final PropertyPane propertyPane = PropertyPane.getInstance();
    private final ToolboxPane toolboxPane = ToolboxPane.getInstance();
    private final UiEditorPane uiEditorPane = UiEditorPane.getInstance();
    private final HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
    private final HorizontalSplitPanel inHsplit = new HorizontalSplitPanel();
    private final HorizontalLayout hlayout = new HorizontalLayout();
    private final ToolbarPane toolbar = ToolbarPane.getInstance();

    public UiEditor() {
        uiEditorPane.setUiEditor(this);
        propertyPane.setUiEditor(this);
        setSizeFull();
        setContent(buildContent());
    }

    public VerticalLayout buildContent() {
        VerticalLayout vl = new VerticalLayout(toolbar, hsplit);
        vl.setExpandRatio(hsplit, 1);
        hsplit.setFirstComponent(toolboxPane);
        hsplit.setSecondComponent(inHsplit);
        hsplit.setSplitPosition(120, Unit.PIXELS);
        hsplit.setLocked(true);
        inHsplit.setFirstComponent(uiEditorPane);
        inHsplit.setSecondComponent(propertyPane);
        inHsplit.setSplitPosition(200, Unit.PIXELS, true);
        vl.setSizeFull();
        return vl;
    }

    public final PropertyPane getPropertyPane() {
        return propertyPane;
    }

    public final ToolboxPane getToolboxPane() {
        return toolboxPane;
    }

    public final UiEditorPane getUiEditorPane() {
        return uiEditorPane;
    }

    public final HorizontalSplitPanel getHsplit() {
        return hsplit;
    }

    public final HorizontalSplitPanel getInHsplit() {
        return inHsplit;
    }

    public final HorizontalLayout getHlayout() {
        return hlayout;
    }

    public final ToolbarPane getToolbar() {
        return toolbar;
    }

}
