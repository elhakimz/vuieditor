package org.zhak.vuieditor.comps;

import com.vaadin.event.LayoutEvents;
import com.vaadin.event.dd.DragAndDropEvent;
import fi.jasoft.dragdroplayouts.DDAbsoluteLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.drophandlers.DefaultAbsoluteLayoutDropHandler;
import org.zhak.vuieditor.event.DropHandlerPerformer;
import org.zhak.vuieditor.event.ICommand;
import org.zhak.vuieditor.view.UiEditorPane;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/16/14.
 */
public class UiAbsoluteLayout extends DDAbsoluteLayout {
    public UiAbsoluteLayout() {
        setDragMode(LayoutDragMode.CLONE);
        setDropHandler(new LayoutDropHandler(this));
        final DDAbsoluteLayout layout = this;
        addListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                UiEditorPane.getInstance().updatePropertyView(layout);
            }
        });
    }

    public ICommand getOnClick() {
        return onClick;
    }

    public void setOnClick(ICommand onClick) {
        this.onClick = onClick;
    }

    private ICommand onClick;

    public class LayoutDropHandler extends DefaultAbsoluteLayoutDropHandler {
        public LayoutDropHandler(DDAbsoluteLayout layout) {
            this.layout = layout;
        }

        @Override
        public void drop(DragAndDropEvent event) {
            super.drop(event);
            DropHandlerPerformer performer = new DropHandlerPerformer(layout);
            performer.drop(event);
        }

        public DDAbsoluteLayout getLayout() {
            return layout;
        }

        public void setLayout(DDAbsoluteLayout layout) {
            this.layout = layout;
        }

        private DDAbsoluteLayout layout;
    }
}
