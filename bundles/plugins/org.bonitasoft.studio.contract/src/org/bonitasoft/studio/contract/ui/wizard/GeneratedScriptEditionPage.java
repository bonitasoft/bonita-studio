/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RootContractInputGenerator;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.OperationCreationException;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.GroovyOnlyExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author aurelie
 */
public class GeneratedScriptEditionPage extends WizardPage {

    private final WritableValue selectedDataObservable;
    private final WritableValue rootNameObservable;
    private final WritableList fieldToContractInputMappingsObservable;
    private final FieldToContractInputMappingOperationBuilder operationBuilder;
    private final RepositoryAccessor repositoryAccessor;
    private final FieldToContractInputMappingExpressionBuilder expressionBuilder;
    private ContractInput rootContractInput;
    private ExpressionViewer viewer;

    /**
     * @param pageName
     */
    public GeneratedScriptEditionPage(final WritableValue rootNameObservable, final WritableList fieldToContactInputMappingsObservable,
            final WritableValue selectedDataObservable, final RepositoryAccessor repositoryAccessor,
            final FieldToContractInputMappingOperationBuilder operationBuilder, final FieldToContractInputMappingExpressionBuilder expressionBuilder) {
        super(GeneratedScriptEditionPage.class.getName());
        this.rootNameObservable = rootNameObservable;
        fieldToContractInputMappingsObservable = fieldToContactInputMappingsObservable;
        this.selectedDataObservable = selectedDataObservable;
        this.operationBuilder = operationBuilder;
        this.expressionBuilder = expressionBuilder;
        this.repositoryAccessor = repositoryAccessor;
        rootContractInput = ProcessFactory.eINSTANCE.createContractInput();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        viewer = new GroovyOnlyExpressionViewer(composite, SWT.BORDER);

        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        viewer.addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.SCRIPT_TYPE, ExpressionConstants.QUERY_TYPE,
                ExpressionConstants.CONTRACT_INPUT_TYPE, ExpressionConstants.PARAMETER_TYPE));
        viewer.addEditorFilter(ExpressionConstants.CONTRACT_INPUT_TYPE, ExpressionConstants.PARAMETER_TYPE);
        generateExpressionScript(viewer);

        fieldToContractInputMappingsObservable.addListChangeListener(new IListChangeListener() {

            @Override
            public void handleListChange(final ListChangeEvent event) {
                generateExpressionScript(viewer);
                viewer.refresh();
            }
        });

        setControl(composite);
    }

    /**
     * @param dbc
     * @param viewer
     */
    protected void generateExpressionScript(final ExpressionViewer viewer) {
        if (selectedDataObservable.getValue() != null) {
            final EObject container = ((Element) selectedDataObservable.getValue()).eContainer();
            viewer.setContext(container);
            viewer.setInput(container);
            final RootContractInputGenerator rootContractInputGenerator = createRootContractInputGenerator();
            rootContractInput = rootContractInputGenerator.getRootContractInput();
            if (!fieldToContractInputMappingsObservable.isEmpty()) {
                try {
                    rootContractInputGenerator.buildForInstanciation((BusinessObjectData) selectedDataObservable.getValue());
                    setViewerSelection(viewer, rootContractInputGenerator);
                } catch (final OperationCreationException e) {
                    BonitaStudioLog.error("Failed to create Operations from contract", e);
                    new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.contractFromDataCreationErrorMessage, e).open();
                }
            }
        }
    }

    /**
     * @param viewer
     * @param rootContractInputGenerator
     */
    protected void setViewerSelection(final ExpressionViewer viewer, final RootContractInputGenerator rootContractInputGenerator) {
        viewer.setSelection(new StructuredSelection(rootContractInputGenerator.getInitialValueExpression()));
    }

    protected RootContractInputGenerator createRootContractInputGenerator() {
        final List<FieldToContractInputMapping> mappings = new ArrayList<FieldToContractInputMapping>();

        for (final Object mapping : fieldToContractInputMappingsObservable) {
            mappings.add((FieldToContractInputMapping) mapping);
        }
        return new RootContractInputGenerator((String) rootNameObservable.getValue(), mappings,
                repositoryAccessor, operationBuilder, expressionBuilder);
    }

    public ContractInput getRootContractInput() {
        return rootContractInput;
    }

    public Expression getGeneratedScript() {
        final StructuredSelection selection = (StructuredSelection) viewer.getSelection();
        return (Expression) selection.getFirstElement();
    }

}
