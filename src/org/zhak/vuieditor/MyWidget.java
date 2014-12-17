package org.zhak.vuieditor;

import org.zhak.vuieditor.client.MyWidgetClientRpc;
import org.zhak.vuieditor.client.MyWidgetServerRpc;
import org.zhak.vuieditor.client.MyWidgetState;
import com.vaadin.shared.AbstractComponentState;
import com.vaadin.ui.AbstractComponent;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/17/14.
 */
public class MyWidget extends AbstractComponent {
    public MyWidget() {
        registerRpc(new MyWidgetServerRpc() {
            private MyWidgetClientRpc getClientRpc() {
                return getRpcProxy(MyWidgetClientRpc.class);
            }
        });
    }

    @Override
    protected MyWidgetState getState() {
        return (MyWidgetState) super.getState();
    }
}
