package org.zhak.vuieditor.event;

import com.vaadin.ui.Component;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/16/14.
 */
public interface ICommand {
    int execute(Component obj);
}
