/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.operation;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationGroupViewer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 */
public class OperationsPropertySection extends AbstractBonitaDescriptionSection {

    protected OperationGroupViewer operationComposite;

    private final OperationContainerSelectionProvider selectionProvider;

    @Inject
    public OperationsPropertySection(OperationContainerSelectionProvider selectionProvider) {
        this.selectionProvider = selectionProvider;
    }

    @Override
    protected void createContent(final Composite parent) {
        final EMFDataBindingContext context = new EMFDataBindingContext();

        final Composite mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(15, 15).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        operationComposite = createViewer(mainComposite);
        operationComposite.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        operationComposite.setContext(context);
        IObservableValue diagramSelectionObservable = ViewersObservables.observeSingleSelection(selectionProvider);
        operationComposite.setInput(CustomEMFEditObservables.observeDetailList(Realm.getDefault(),
                diagramSelectionObservable, ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS));

        context.bindValue(diagramSelectionObservable, ViewersObservables.observeSingleSelection(operationComposite));

    }

    protected OperationGroupViewer createViewer(final Composite parent) {
        final AvailableExpressionTypeFilter actionFilter = new AvailableExpressionTypeFilter(
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.CONTRACT_INPUT_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.DOCUMENT_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.QUERY_TYPE);

        final AvailableExpressionTypeFilter dataFilter = new AvailableExpressionTypeFilter(
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SEARCH_INDEX_TYPE,
                ExpressionConstants.DOCUMENT_REF_TYPE);

        return new OperationGroupViewer(getTabbedPropertySheetPage(), parent, actionFilter, dataFilter);
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        selectionProvider.setSelection(selection);
    }

    /*
     * (non-Javadoc)
     * @seeorg.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (operationComposite != null) {
            operationComposite.dispose();
        }
    }

    @Override
    public String getSectionDescription() {
        return Messages.operationExplanation;
    }

}
