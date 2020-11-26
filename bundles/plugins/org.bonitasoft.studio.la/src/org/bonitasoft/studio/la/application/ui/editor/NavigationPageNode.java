/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class NavigationPageNode {

    private String menuLabel;
    private String applicationPage;
    private String applicationToken;
    private boolean orphan;
    private List<NavigationPageNode> children = new ArrayList<>();
    private Optional<NavigationPageNode> parent = Optional.empty();
    private boolean topMenu;

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public NavigationPageNode(String menuLabel, String applicationPage, String applicationToken) {
        this.menuLabel = menuLabel;
        this.applicationPage = applicationPage;
        this.applicationToken = applicationToken;
    }

    public void addChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removeChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public NavigationPageNode(String menuLabel) {
        this(menuLabel, null, null);
        setTopMenu(true);
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        firePropertyChange("menuLabel", this.menuLabel, this.menuLabel = menuLabel);
    }

    public String getApplicationPage() {
        return applicationPage;
    }

    public void setApplicationPage(String applicationPage) {
        firePropertyChange("applicationPage", this.applicationPage, this.applicationPage = applicationPage);
    }

    public String getApplicationToken() {
        return applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        firePropertyChange("applicationToken", this.applicationToken, this.applicationToken = applicationToken);
    }

    public List<NavigationPageNode> getChildren() {
        return children;
    }

    public void setChildren(List<NavigationPageNode> children) {
        this.children = children;
    }

    public void addChild(NavigationPageNode child) {
        if (!children.contains(child)) {
            child.setParent(this);
            children.add(child);
            firePropertyChange("children", Collections.emptyList(), children); // small trick to fire the event..
        }
    }

    public void removeChild(NavigationPageNode child) {
        children.remove(child);
        firePropertyChange("children", Collections.emptyList(), children);
    }

    public Optional<NavigationPageNode> getParent() {
        return parent;
    }

    public void setParent(NavigationPageNode parent) {
        this.parent = Optional.ofNullable(parent);
    }

    public boolean isOrphan() {
        return orphan;
    }

    public void setOrphan(boolean orphan) {
        this.orphan = orphan;
    }

    public void setTopMenu(boolean topMenu) {
        this.topMenu = topMenu;
    }

    public boolean isTopMenu() {
        return topMenu;
    }

    public Stream<NavigationPageNode> flattened() {
        return Stream.concat(Stream.of(this), children.stream().flatMap(NavigationPageNode::flattened));
    }

}
