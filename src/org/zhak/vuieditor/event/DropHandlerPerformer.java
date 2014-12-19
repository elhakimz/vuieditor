package org.zhak.vuieditor.event;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import org.zhak.vuieditor.comps.*;
import org.zhak.vuieditor.view.UiEditorPane;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/16/14.
 */
public class DropHandlerPerformer {
    public DropHandlerPerformer(ComponentContainer layout) {
        this.layout = layout;
    }

    public void drop(DragAndDropEvent event) {
        Component cmp = (Component) event.getTransferable().getData("component");
        if (event.getTransferable().getSourceComponent() instanceof DragAndDropWrapper) {
            Component cmp2;
            ComponentContainer cmpLayout = new VerticalLayout();
            boolean isLayout = false;
            Object data = ((Button) cmp).getData();
                cmp2 = new Button("Error");
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
                                UiEditorPane.getInstance().updatePropertyView(obj);
                                return 1;
                            }

                        });
                    } else if (cmp2 instanceof HorizontalLayout) {
                        cmpLayout = new UiHorizontalLayout();
                        ((UiHorizontalLayout) cmpLayout).setOnClick(new ICommand() {
                            @Override
                            public int execute(Component obj) {
                                UiEditorPane.getInstance().updatePropertyView(obj);
                                return 1;
                            }

                        });
                    } else if (cmp2 instanceof VerticalLayout) {
                        cmpLayout = new UiVerticalLayout();
                        ((UiVerticalLayout) cmpLayout).setOnClick(new ICommand() {
                            @Override
                            public int execute(Component obj) {
                                UiEditorPane.getInstance().updatePropertyView(obj);
                                return 1;
                            }

                        });

                    } else if (cmp2 instanceof CssLayout) {
                        cmpLayout = new UiCssLayout();
                        ((UiCssLayout) cmpLayout).setOnClick(new ICommand() {
                            @Override
                            public int execute(Component obj) {
                                UiEditorPane.getInstance().updatePropertyView(obj);
                                return 1;
                            }

                        });
                    } else if (cmp2 instanceof FormLayout) {
                        cmpLayout = new UiFormLayout();
                        ((UiFormLayout) cmpLayout).setOnClick(new ICommand() {
                            @Override
                            public int execute(Component obj) {
                                UiEditorPane.getInstance().updatePropertyView(obj);
                                return 1;
                            }

                        });
                    } else {
                        cmpLayout = (ComponentContainer) cmp2;

                    }

                    //UiLayoutWrapper layoutWrapper = new UiLayoutWrapper(cmpLayout)
                    cmpLayout.setHeight(100, Sizeable.Unit.PIXELS);
                    cmpLayout.setWidth(100 , Sizeable.Unit.PIXELS);
                    cmpLayout.addStyleName("wrapped");
                    System.out.println("data = " + data);
                } else {
                    ((AbstractComponent) cmp2).setDescription(caption);
                }

            if (isLayout) {
                this.layout.addComponent(cmpLayout);
                this.layout.setSizeUndefined();
            } else {
                if(cmp2 instanceof Button || cmp2 instanceof Panel) cmp2.setCaption(caption);
                UiComponentWrapper wrapper = new UiComponentWrapper(cmp2);
                this.layout.addComponent(wrapper);
                this.layout.setSizeUndefined();
            }

        }else{
            this.layout.setSizeUndefined();
        }

    }

    public void addListenerToComp(Component comp) {
        if (comp instanceof AbstractTextField) {
            ((AbstractTextField) comp).addListener(new FieldEvents.FocusListener() {
                @Override
                public void focus(FieldEvents.FocusEvent focusEvent) {
                    UiEditorPane.getInstance().updatePropertyView(focusEvent.getComponent());
                }

            });
        }

    }

    public ComponentContainer getLayout() {
        return layout;
    }

    public void setLayout(ComponentContainer layout) {
        this.layout = layout;
    }

    private ComponentContainer layout;
}
