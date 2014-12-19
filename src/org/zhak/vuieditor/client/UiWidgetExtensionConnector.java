package org.zhak.vuieditor.client;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/18/14.
 */
@Connect(UiWidgetExtension.class)
public class UiWidgetExtensionConnector extends AbstractExtensionConnector {
    @Override
    protected void extend(ServerConnector target) {
        final Widget widget = ((ComponentConnector) target).getWidget();
        widget.addDomHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                System.out.println("MouseDownEvent event = [" + event + "]");
            }
        },MouseDownEvent.getType());
    }
}
