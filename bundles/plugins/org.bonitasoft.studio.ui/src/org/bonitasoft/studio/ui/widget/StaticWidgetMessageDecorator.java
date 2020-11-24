/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.widget;

import java.util.Optional;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

// Use a composite instead of a Collapsable composite
public class StaticWidgetMessageDecorator extends WidgetMessageDecorator {

    public StaticWidgetMessageDecorator(Composite parent, Optional<String> defaultMessage) {
        super(parent, defaultMessage);
    }

    @Override
    protected void createComposite(Composite parent) {
        this.composite = new Composite(parent, SWT.NONE);
        this.composite.setLayout(GridLayoutFactory.fillDefaults().create());
        this.composite.setLayoutData(GridDataFactory.fillDefaults().create());
    }

    @Override
    protected void updateExpandState() {
        if (messageLabel.getText() != null && !messageLabel.getText().isEmpty()) {
            composite.setVisible(true);
            ((GridData) composite.getLayoutData()).exclude = false;
        } else {
            composite.setVisible(false);
            ((GridData) composite.getLayoutData()).exclude = true;
        }
        composite.layout();
        composite.getParent().getParent().layout();
    }

}
