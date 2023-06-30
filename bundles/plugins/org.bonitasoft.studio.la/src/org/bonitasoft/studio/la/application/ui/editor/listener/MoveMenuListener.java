/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.listener;

import org.bonitasoft.studio.la.application.ui.editor.ApplicationNavigation;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class MoveMenuListener implements Listener {

    private final IViewerObservableValue menuSelectionObservable;
    private final WritableList menuInputList;
    private final ApplicationNavigation applicationNavigation;
    private final int indexChange;

    public MoveMenuListener(int indexChange, IViewerObservableValue menuSelectionObservable, WritableList menuInputList,
            ApplicationNavigation applicationNavigation) {
        this.indexChange = indexChange;
        this.menuSelectionObservable = menuSelectionObservable;
        this.menuInputList = menuInputList;
        this.applicationNavigation = applicationNavigation;
    }

    @Override
    public void handleEvent(Event event) {
        final NavigationPageNode selectedNode = (NavigationPageNode) menuSelectionObservable.getValue();
        if (menuInputList.indexOf(selectedNode) != -1) {
            final int currentIndex = menuInputList.indexOf(selectedNode);
            menuSelectionObservable.setValue(menuInputList.move(currentIndex, currentIndex + indexChange));
            applicationNavigation.getMenuTreeViewer().refresh();
        } else {
            final NavigationPageNode father = selectedNode.getParent().get();
            final int currentIndex = father.getChildren().indexOf(selectedNode);
            final NavigationPageNode previousElement = father.getChildren().set(currentIndex + indexChange, selectedNode);
            father.getChildren().set(currentIndex, previousElement);

            applicationNavigation.valueChangeListener(null);
            applicationNavigation.getMenuTreeViewer().refresh(father);
        }
        updateFocus();
    }

    protected void updateFocus() {
        applicationNavigation.getMenuTreeViewer().getControl().setFocus();
    }

}
