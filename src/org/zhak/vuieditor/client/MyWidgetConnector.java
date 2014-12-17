package org.zhak.vuieditor.client;

import org.zhak.vuieditor.MyWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/17/14.
 */
@Connect(MyWidget.class)
public class MyWidgetConnector extends AbstractComponentConnector {
    private final MyWidgetServerRpc serverRpc = RpcProxy.create(MyWidgetServerRpc.class, this);

    public MyWidgetConnector() {
        registerRpc(MyWidgetClientRpc.class, new MyWidgetClientRpc() {
        });
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(MyWidgetWidget.class);
    }

    @Override
    public MyWidgetWidget getWidget() {
        return (MyWidgetWidget) super.getWidget();
    }

    @Override
    public MyWidgetState getState() {
        return (MyWidgetState) super.getState();
    }
}
