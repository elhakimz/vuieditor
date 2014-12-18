package org.zhak.vuieditor.view;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.*;

/**
 * Purpose:
 *  Toolbox Pane
 * @author abilhakim
 *         Date: 12/14/14.
 */
public class ToolboxPane extends VerticalLayout {

    private static ToolboxPane __instance;
    private final Accordion accordion = new Accordion();


    public static ToolboxPane getInstance() {
        if (__instance == null) __instance = new ToolboxPane();
        return __instance;
    }

    private ToolboxPane() {
        ToolboxData.init();
        setSizeFull();
        accordion.setSizeFull();
        buildContent();
        addComponent(accordion);
    }

    private void buildContent() {

        for(String group:ToolboxData.TOOLBOX_COMP_MAP.keySet()){
            final VerticalLayout layout = new VerticalLayout();
            layout.setCaption(group);
            accordion.addComponent(layout);

            Set<Class> classes = ToolboxData.TOOLBOX_COMP_MAP.get(group);
            for(Class cls:classes){
                String name=cls.getName();
                String cap = name.substring(name.lastIndexOf(".")+1);

                if(cap.startsWith("Abstract")){
                    continue;
                }

                Button button = new Button();
                button.addStyleName(ValoTheme.BUTTON_TINY);
                button.setWidth(115, Unit.PIXELS);
                button.setData(name);
                button.setDescription(cap);
                button.setCaption(cap);
                final DragAndDropWrapper buttonWrap = new DragAndDropWrapper(button);
                buttonWrap.setDragStartMode(DragAndDropWrapper.DragStartMode.COMPONENT);
                buttonWrap.setSizeUndefined();
                layout.addComponent(buttonWrap);
                layout.setComponentAlignment(buttonWrap, Alignment.MIDDLE_CENTER);

            }
        }
    }

    public final Accordion getAccordion() {
        return accordion;
    }


}
