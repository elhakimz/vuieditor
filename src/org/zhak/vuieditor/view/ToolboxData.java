package org.zhak.vuieditor.view;

import com.vaadin.ui.*;

import org.reflections.Reflections;

import java.util.*;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/18/14.
 */
public class ToolboxData {

    public static final Map<String,String> TOOLBOX_GROUP=new LinkedHashMap<>();
    public static final Map<String,Set<Class>> TOOLBOX_COMP_MAP=new LinkedHashMap<>();

    public static void init(){
        TOOLBOX_GROUP.put("Base","com.vaadin.ui");
        TOOLBOX_GROUP.put("Field","com.vaadin.ui");
        TOOLBOX_GROUP.put("Layout","com.vaadin.ui");
        TOOLBOX_GROUP.put("Addons","com.vaadin.ui");
        TOOLBOX_GROUP.put("Data","com.vaadin.ui");

        //init base
        TOOLBOX_COMP_MAP.put("Base",getComponentClasses("Base",AbstractComponent.class));

        //init field
        TOOLBOX_COMP_MAP.put("Field",getComponentClasses("Field", AbstractField.class));

        //init layout
        TOOLBOX_COMP_MAP.put("Layout",getComponentClasses("Layout", AbstractComponentContainer.class));

        //init addons
    }

    /**
     * scans a package for component or container
     * @param groupName
     * @return
     */
    private static Set<Class> getComponentClasses(String groupName, Class type){

        Reflections reflections = new Reflections(TOOLBOX_GROUP.get(groupName));

        Set<Class> subTypes =reflections.getSubTypesOf(type);

        return subTypes;
    }

}
