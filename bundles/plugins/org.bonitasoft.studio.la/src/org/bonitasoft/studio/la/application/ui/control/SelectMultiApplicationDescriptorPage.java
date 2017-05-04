/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.control;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

public class SelectMultiApplicationDescriptorPage extends SelectApplicationDescriptorPage {

    public SelectMultiApplicationDescriptorPage(RepositoryAccessor repositoryAccessor) {
        super(repositoryAccessor);
    }

    @Override
    protected TableViewer createTableViewer(Composite mainComposite) {
        return new TableViewer(mainComposite);
    }

}
