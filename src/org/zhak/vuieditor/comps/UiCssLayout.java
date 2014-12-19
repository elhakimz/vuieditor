package org.zhak.vuieditor.comps;

import com.vaadin.event.LayoutEvents;
import com.vaadin.event.dd.DragAndDropEvent;
import fi.jasoft.dragdroplayouts.DDCssLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.drophandlers.DefaultCssLayoutDropHandler;
import org.zhak.vuieditor.event.DropHandlerPerformer;
import org.zhak.vuieditor.event.ICommand;
import org.zhak.vuieditor.view.UiEditorPane;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/16/14.
 */
public final class UiCssLayout extends DDCssLayout {
    public UiCssLayout() {
        setDragMode(LayoutDragMode.CLONE);
        setDropHandler(new LayoutDropHandler(this));
        final DDCssLayout layout = this;

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

    public class LayoutDropHandler extends DefaultCssLayoutDropHandler {
        public LayoutDropHandler(DDCssLayout layout) {
            this.layout = layout;
        }

        @Override
        public void drop(DragAndDropEvent event) {
            super.drop(event);
            DropHandlerPerformer performer = new DropHandlerPerformer(layout);
            performer.drop(event);
        }

        public DDCssLayout getLayout() {
            return layout;
        }

        public void setLayout(DDCssLayout layout) {
            this.layout = layout;
        }

        private DDCssLayout layout;
    }
}
