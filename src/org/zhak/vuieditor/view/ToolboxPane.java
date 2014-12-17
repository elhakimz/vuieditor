package org.zhak.vuieditor.view;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Purpose:
 *  Toolbox Pane
 * @author abilhakim
 *         Date: 12/14/14.
 */
public class ToolboxPane extends VerticalLayout {

    private static ToolboxPane __instance;
    private final Accordion accordion = new Accordion();
    private ArrayList<String> compgroup = new ArrayList<>(Arrays.asList("Common", "Field", "Layout", "Extension"));
    {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(2);
        map.put("button", "com.vaadin.ui.Button");
        map.put("label", "com.vaadin.ui.Label");
        commonGroup = map;
    }

    private LinkedHashMap<String, String> commonGroup;
    {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(6);
        map.put("textfield", "com.vaadin.ui.TextField");
        map.put("textarea", "com.vaadin.ui.TextArea");
        map.put("select", "com.vaadin.ui.Select");
        map.put("nselect", "com.vaadin.ui.NativeSelect");
        map.put("listselect", "com.vaadin.ui.ListSelect");
        map.put("combobox", "com.vaadin.ui.ComboBox");
        fieldGroup = map;
    }

    private LinkedHashMap<String, String> fieldGroup;

    {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(5);
        map.put("hlayout", "com.vaadin.ui.HorizontalLayout");
        map.put("vlayout", "com.vaadin.ui.VerticalLayout");
        map.put("csslayout", "com.vaadin.ui.CssLayout");
        map.put("abslayout", "com.vaadin.ui.AbsoluteLayout");
        map.put("formlayout", "com.vaadin.ui.FormLayout");
        layoutGroup = map;
    }

    private LinkedHashMap<String, String> layoutGroup;
    private LinkedHashMap extensionGroup = new LinkedHashMap();

    public static ToolboxPane getInstance() {
        if (__instance == null) __instance = new ToolboxPane();
        return __instance;
    }

    private ToolboxPane() {
        setSizeFull();
        accordion.setSizeFull();
        buildContent();
        addComponent(accordion);
    }

    private void buildContent() {
        for(String group:compgroup){
            final VerticalLayout grid = new VerticalLayout();
            grid.setCaption(group);
            accordion.addComponent(grid);

            Map<String,String> comps;
            if (group.equals("Common")) {
                comps = commonGroup;
            } else if (group.equals("Field")) {
                comps = fieldGroup;
            } else if (group.equals("Layout")) {
                comps = layoutGroup;
            } else {
                comps = new LinkedHashMap<>();
            }

            for(String k:comps.keySet()){
                String v = comps.get(k);
                String cap = v.replace("com.vaadin.ui.", "");
                Button button = new Button();
                button.addStyleName(ValoTheme.BUTTON_TINY);
                button.setWidth(115, Unit.PIXELS);
                button.setData(v);
                button.setDescription(cap);
                button.setCaption(cap);
                final DragAndDropWrapper buttonWrap = new DragAndDropWrapper(button);
                buttonWrap.setDragStartMode(DragAndDropWrapper.DragStartMode.COMPONENT);
                buttonWrap.setSizeUndefined();
                grid.addComponent(buttonWrap);
                grid.setComponentAlignment(buttonWrap, Alignment.MIDDLE_CENTER);
            }
        }
    }

    public final Accordion getAccordion() {
        return accordion;
    }


}
