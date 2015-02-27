/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
<<<<<<< HEAD
 *
=======
>>>>>>> refs/remotes/origin/master
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
<<<<<<< HEAD
 *
=======
>>>>>>> refs/remotes/origin/master
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
<<<<<<< HEAD
 *
=======
>>>>>>> refs/remotes/origin/master
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.ui.wizard.page;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationsComposite;
import org.bonitasoft.studio.expression.editor.operation.WizardPageOperationsComposite;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorOutputWizardPage extends AbstractConnectorOutputWizardPage {

    private OperationsComposite lineComposite;
    private ScrolledComposite scrolledComposite;

    @Override
    protected Control doCreateControl(final Composite parent, final EMFDataBindingContext context) {
        scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
        scrolledComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        scrolledComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final AvailableExpressionTypeFilter leftFilter = new AvailableExpressionTypeFilter(new String[] {
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.DOCUMENT_REF_TYPE });
        final AvailableExpressionTypeFilter rightFilter = new ConnectorOutputAvailableExpressionTypeFilter();

        final Composite mainComposite = new Composite(scrolledComposite, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        lineComposite = new WizardPageOperationsComposite(null, mainComposite, rightFilter, leftFilter, isPageFlowContext());
        lineComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 280).create());
        final IExpressionNatureProvider storageExpressionProvider = getStorageExpressionProvider();
        if (storageExpressionProvider != null) {
            lineComposite.setStorageExpressionNatureContentProvider(storageExpressionProvider);
        }
        lineComposite.setContext(context);
        lineComposite.setContext(getElementContainer());
        lineComposite.setEObject(getConnector());
        lineComposite.fillTable();

        scrolledComposite.setContent(mainComposite);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setMinSize(lineComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        return mainComposite;
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            scrolledComposite.getParent().layout(true, true);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }

}
