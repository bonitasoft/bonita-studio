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

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RootContractInputGenerator;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.OperationCreationException;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @author aurelie
 */
public class GeneratedScriptPreviewPage extends WizardPage {

    private final WritableValue selectedDataObservable;
    private final WritableValue rootNameObservable;
    private final WritableList fieldToContractInputMappingsObservable;
    private final FieldToContractInputMappingOperationBuilder operationBuilder;
    private final RepositoryAccessor repositoryAccessor;
    private final FieldToContractInputMappingExpressionBuilder expressionBuilder;
    private IDocument document;
    private Expression generatedExpression;
    private final IPreferenceStore groovyStore;

    /**
     * @param pageName
     */
    public GeneratedScriptPreviewPage(final WritableValue rootNameObservable, final WritableList fieldToContactInputMappingsObservable,
            final WritableValue selectedDataObservable, final RepositoryAccessor repositoryAccessor, final IPreferenceStore groovyStore,
            final FieldToContractInputMappingOperationBuilder operationBuilder, final FieldToContractInputMappingExpressionBuilder expressionBuilder) {
        super(GeneratedScriptPreviewPage.class.getName());
        setTitle(Messages.generatedScriptPreviewTitle);
        this.rootNameObservable = rootNameObservable;
        fieldToContractInputMappingsObservable = fieldToContactInputMappingsObservable;
        this.selectedDataObservable = selectedDataObservable;
        this.operationBuilder = operationBuilder;
        this.expressionBuilder = expressionBuilder;
        this.repositoryAccessor = repositoryAccessor;
        this.groovyStore = groovyStore;
        generatedExpression = ExpressionFactory.eINSTANCE.createExpression();
    }

    public void setDescription() {
        if (selectedDataObservable.getValue() != null) {
            setDescription(Messages.bind(Messages.setGeneratedScriptPreviewPageDescription, ((Element) selectedDataObservable.getValue()).getName()));
            EMFObservables.observeDetailValue(Realm.getDefault(), selectedDataObservable, ProcessPackage.Literals.ELEMENT__NAME).addValueChangeListener(
                    new IValueChangeListener() {

                        @Override
                        public void handleValueChange(final ValueChangeEvent event) {
                            setDescription(Messages.bind(Messages.setGeneratedScriptPreviewPageDescription, event.diff.getNewValue()));
                        }
                    });
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#setTitle(java.lang.String)
     */
    @Override
    public void setTitle(final String title) {
        // TODO Auto-generated method stub
        super.setTitle(title);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
        mainComposite.setLayout(new FillLayout(SWT.VERTICAL));

        final GroovyViewer groovyViewer = new GroovyViewer(mainComposite, repositoryAccessor.getRepositoryStore(ProvidedGroovyRepositoryStore.class),
                groovyStore, true);
        groovyViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
        final SourceViewer sourceViewer = groovyViewer.getSourceViewer();
        sourceViewer.setEditable(false);
        document = groovyViewer.getDocument();
        document.set(generatedExpression.getContent());
        setControl(mainComposite);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        generateExpressionScript();
    }

    /**
     * @param dbc
     * @param viewer
     */
    protected void generateExpressionScript() {
        if (selectedDataObservable.getValue() != null) {
            final RootContractInputGenerator rootContractInputGenerator = createRootContractInputGenerator();
            if (!fieldToContractInputMappingsObservable.isEmpty()) {
                try {
                    rootContractInputGenerator.buildForInstanciation((BusinessObjectData) selectedDataObservable.getValue());
                    generatedExpression = rootContractInputGenerator.getInitialValueExpression();
                    document.set(generatedExpression.getContent());
                } catch (final OperationCreationException e) {
                    BonitaStudioLog.error("Failed to create Operations from contract", e);
                    new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.contractFromDataCreationErrorMessage, e).open();
                }
            }
        }
    }

    protected RootContractInputGenerator createRootContractInputGenerator() {
        final List<FieldToContractInputMapping> mappings = new ArrayList<FieldToContractInputMapping>();

        for (final Object mapping : fieldToContractInputMappingsObservable) {
            mappings.add((FieldToContractInputMapping) mapping);
        }

        return new RootContractInputGenerator((String) rootNameObservable.getValue(), mappings,
                repositoryAccessor, operationBuilder, expressionBuilder);
    }

}
