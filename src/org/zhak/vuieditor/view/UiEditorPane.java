package org.zhak.vuieditor.view;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.ui.*;
import fi.jasoft.dragdroplayouts.DDAbsoluteLayout;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.details.AbsoluteLayoutTargetDetails;
import fi.jasoft.dragdroplayouts.drophandlers.DefaultAbsoluteLayoutDropHandler;

import org.zhak.vuieditor.UiEditor;
import org.zhak.vuieditor.comps.*;
import org.zhak.vuieditor.event.ICommand;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/14/14.
 */
public class UiEditorPane extends CssLayout {

    private UiEditor uiEditor;
    private static UiEditorPane __instance;
    private DDAbsoluteLayout layout = new DDAbsoluteLayout();

    private UiEditorPane() {
        setSizeFull();
        layout.setSizeFull();
        layout.setDragMode(LayoutDragMode.CLONE);
        layout.setId("layout");
        layout.setDropHandler(new MyDropHandler());
        addComponent(layout);
        layout.addComponent(new DDVerticalLayout(), "top:10px;left:10px");
        layout.addStyleName("uigrid");
    }

    public static UiEditorPane getInstance() {
        if (__instance == null) __instance = new UiEditorPane();
        return __instance;
    }

    @Override
    protected String getCss(Component c) {
        return "margin:10px;background-color:#dddddd;overflow:auto";
    }

    public void updatePropertyView(Component component) {
        uiEditor.getPropertyPane().updatePropertyView(component);
    }

    public void commitChange() {

    }

    public UiEditor getUiEditor() {
        return uiEditor;
    }

    public void setUiEditor(UiEditor uiEditor) {
        this.uiEditor = uiEditor;
    }


    public class MyDropHandler extends DefaultAbsoluteLayoutDropHandler {
        @Override
        public void drop(DragAndDropEvent event) {
            super.drop(event);
            Component cmp = (Component) event.getTransferable().getData("component");
            if (event.getTransferable().getSourceComponent() instanceof DragAndDropWrapper) {

                Component cmp2;
                ComponentContainer cmpLayout = new VerticalLayout();
                boolean isLayout = false;
                Object data = ((Button) cmp).getData();
                cmp2 = new Button("Button");

                    try {
                        Class aClass = Class.forName(String.valueOf(data));
                        cmp2 = ((Component) aClass.newInstance());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    String caption = String.valueOf(data).replace("com.vaadin.ui.", "");

                    if (cmp2 instanceof ComponentContainer) {
                        isLayout = true;
                        if (cmp2 instanceof AbsoluteLayout) {
                            cmpLayout = new UiAbsoluteLayout();
                            ((UiAbsoluteLayout) cmpLayout).setOnClick(new ICommand() {
                                @Override
                                public int execute(Component obj) {
                                    updatePropertyView(obj);
                                    return 1;
                                }

                            });
                        } else if (cmp2 instanceof HorizontalLayout) {
                            cmpLayout = new UiHorizontalLayout();
                            ((UiHorizontalLayout) cmpLayout).setOnClick(new ICommand() {
                                @Override
                                public int execute(Component obj) {
                                    updatePropertyView(obj);
                                    return 1;
                                }

                            });
                        } else if (cmp2 instanceof VerticalLayout) {
                            cmpLayout = new UiVerticalLayout();
                            ((UiVerticalLayout) cmpLayout).setOnClick(new ICommand() {
                                @Override
                                public int execute(Component obj) {
                                    updatePropertyView(obj);
                                    return 1;
                                }

                            });

                        } else if (cmp2 instanceof CssLayout) {
                            cmpLayout = new UiCssLayout();
                            ((UiCssLayout) cmpLayout).setOnClick(new ICommand() {
                                @Override
                                public int execute(Component obj) {
                                    updatePropertyView(obj);
                                    return 1;
                                }

                            });
                        } else if (cmp2 instanceof FormLayout) {
                            cmpLayout = new UiFormLayout();
                            ((UiFormLayout) cmpLayout).setOnClick(new ICommand() {
                                @Override
                                public int execute(Component obj) {
                                    updatePropertyView(obj);
                                    return 1;
                                }

                            });
                        } else {
                            cmpLayout = (ComponentContainer) cmp2;
                        }
                        cmpLayout.setHeight(100, Unit.PIXELS);
                        cmpLayout.setWidth(100, Unit.PIXELS);
                        cmpLayout.addStyleName("wrapped");
                        System.out.println("data = " + data);
                    } else {
                        cmp2.setCaption(caption);
                    }


                final int x = ((AbsoluteLayoutTargetDetails) event.getTargetDetails()).getRelativeLeft();
                final int y = ((AbsoluteLayoutTargetDetails) event.getTargetDetails()).getRelativeTop();

                if (isLayout) {
                    ((DDAbsoluteLayout) event.getTargetDetails().getTarget()).addComponent(cmpLayout, "left:" + String.valueOf(x) + "px;top:" + String.valueOf(y) + "px");
                } else {
                    addListenerToComp(cmp2);
                    ((DDAbsoluteLayout) event.getTargetDetails().getTarget()).addComponent(cmp2, "left:" + String.valueOf(x) + "px;top:" + String.valueOf(y) + "px");
                }

            }

        }

        public void addListenerToComp(Component comp) {
            if (comp instanceof AbstractTextField) {
                ((AbstractTextField) comp).addListener(new FieldEvents.FocusListener() {
                    @Override
                    public void focus(FieldEvents.FocusEvent focusEvent) {
                        updatePropertyView(focusEvent.getComponent());
                    }

                });
            }

        }

    }
}
