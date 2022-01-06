/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.provider;

import org.bonitasoft.studio.common.jface.ArrayTreeContentProvider;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;

public class NavigationPageTreeContentProvider extends ArrayTreeContentProvider {

    @Override
    public Object[] getChildren(Object parentElement) {
        return parentElement instanceof NavigationPageNode
                ? ((NavigationPageNode) parentElement).getChildren().toArray()
                : null;
    }

    @Override
    public Object getParent(Object element) {
        return element instanceof NavigationPageNode
                ? ((NavigationPageNode) element).getParent().orElse(null)
                : null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return element instanceof NavigationPageNode ? !((NavigationPageNode) element).getChildren().isEmpty() : false;
    }

}
