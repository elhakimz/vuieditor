package org.zhak.vuieditor.view;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.zhak.vuieditor.UiEditor;
import org.zhak.vuieditor.model.ComponentData;
import org.zhak.vuieditor.model.ComponentEventData;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/14/14.
 */
public class PropertyPane extends TabSheet {
    private UiEditor uiEditor;
    private static PropertyPane __instance;
    private final CssWrap vl1 = new CssWrap();
    private final CssWrap vl2 = new CssWrap();
    private final CssWrap vl3 = new CssWrap();
    private BeanItem<ComponentData> item;
    private BeanItem<ComponentEventData> eventDataBeanItem;
    private final Form propertyForm = new Form();
    private final Form eventForm = new Form();
    private final Tree layoutTree = new Tree();
    private Component currentComponent;

    private PropertyPane() {

        item = new BeanItem<>(new ComponentData());
        propertyForm.setVisibleItemProperties("caption", "description", "height", "width");
        propertyForm.setItemDataSource(item);
        eventDataBeanItem = new BeanItem<>(new ComponentEventData());
        eventForm.setVisibleItemProperties("onClick", "onRightClick");
        eventForm.setItemDataSource(eventDataBeanItem);
        addTab(vl1, "Properties");
        addTab(vl2, "Events");
        addTab(vl3, "Structure");
        vl1.addComponent(propertyForm);
        vl2.addComponent(eventForm);
        vl3.addComponent(layoutTree);
        setSizeFull();
        buildPropertyForm();
        buildEventForm();
    }

    public static PropertyPane getInstance() {
        if (__instance == null) __instance = new PropertyPane();
        return __instance;
    }

    public void buildPropertyForm() {

        item = new BeanItem<>(new ComponentData());
        propertyForm.setVisibleItemProperties("caption", "description", "height", "width");
        propertyForm.setItemDataSource(item);
        propertyForm.getLayout().addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        for(Object prop:propertyForm.getVisibleItemProperties()){
            Field field = propertyForm.getField(prop);
            try {
                field.addStyleName("small");
            } catch (Exception e) {
                e.printStackTrace();//TODO
            }
        }
    }

    public void buildEventForm() {
        eventDataBeanItem = new BeanItem<>(new ComponentEventData());
        eventForm.setVisibleItemProperties("onClick", "onRightClick");
        eventForm.setItemDataSource(eventDataBeanItem);
        eventForm.getLayout().addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        for(Object prop:eventForm.getVisibleItemProperties()){
            Field field = eventForm.getField(prop);
            try {
                field.addStyleName("small");
            } catch (Exception e) {
                e.printStackTrace();//TODO
            }
        }

    }

    public void updatePropertyView(final Component component) {
        if (component == null) {
            return;
        }

        ComponentData data = new ComponentData();
        data.setCaption(component.getCaption());
        data.setDescription(component.getDescription());
        data.setHeight(String.valueOf(component.getHeight()) + component.getHeightUnits().getSymbol());
        data.setWidth(String.valueOf(component.getWidth()) + component.getWidthUnits().getSymbol());
        item = new BeanItem<>(data);
        propertyForm.setItemDataSource(item);
        eventDataBeanItem = new BeanItem<>(new ComponentEventData());
        eventForm.setItemDataSource(eventDataBeanItem);
        currentComponent = component;

    }

    public void commitChange() {
        uiEditor.getUiEditorPane().commitChange();
    }

    public void updateLayoutTree() {
    }

    public UiEditor getUiEditor() {
        return uiEditor;
    }

    public void setUiEditor(UiEditor uiEditor) {
        this.uiEditor = uiEditor;
    }


    public class CssWrap extends CssLayout {
        @Override
        protected String getCss(Component c) {
            return "margin:10px";
        }

    }
}
