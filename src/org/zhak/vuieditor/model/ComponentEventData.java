package org.zhak.vuieditor.model;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/16/14.
 */
public class ComponentEventData {
    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getOnRightClick() {
        return onRightClick;
    }

    public void setOnRightClick(String onRightClick) {
        this.onRightClick = onRightClick;
    }

    private String onClick;
    private String onRightClick;
}
