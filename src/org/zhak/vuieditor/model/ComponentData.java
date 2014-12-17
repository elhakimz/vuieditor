package org.zhak.vuieditor.model;

/**
 * Purpose:
 *
 * @author abilhakim
 *         Date: 12/16/14.
 */
public class ComponentData {
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    private String caption;
    private String description;
    private String styleName;
    private String width;
    private String height;
}
