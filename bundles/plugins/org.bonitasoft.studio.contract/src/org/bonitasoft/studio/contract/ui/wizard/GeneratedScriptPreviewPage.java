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

import java.lang.reflect.InvocationTargetException;
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
import org.bonitasoft.studio.groovy.ui.dialog.GroovyHelpLinkFactory;
import org.bonitasoft.studio.groovy.ui.viewer.GroovySourceViewerFactory;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IEventConsumer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

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
    private final GroovySourceViewerFactory sourceViewerFactory;
    private RootContractInputGenerator rootContractInputGenerator;
    private Text scriptNameText;

    /**
     * @param pageName
     */
    public GeneratedScriptPreviewPage(final WritableValue rootNameObservable,
            final WritableList fieldToContactInputMappingsObservable,
            final WritableValue selectedDataObservable, final RepositoryAccessor repositoryAccessor,
            final FieldToContractInputMappingOperationBuilder operationBuilder,
            final FieldToContractInputMappingExpressionBuilder expressionBuilder,
            final GroovySourceViewerFactory sourceViewerFactory) {
        super(GeneratedScriptPreviewPage.class.getName());
        setTitle(Messages.generatedScriptPreviewTitle);
        this.rootNameObservable = rootNameObservable;
        fieldToContractInputMappingsObservable = fieldToContactInputMappingsObservable;
        this.selectedDataObservable = selectedDataObservable;
        this.operationBuilder = operationBuilder;
        this.expressionBuilder = expressionBuilder;
        this.repositoryAccessor = repositoryAccessor;
        this.sourceViewerFactory = sourceViewerFactory;
        generatedExpression = ExpressionFactory.eINSTANCE.createExpression();
    }

    public void setDescription() {
        if (selectedDataObservable.getValue() != null) {
            setDescription(Messages.bind(Messages.setGeneratedScriptPreviewPageDescription,
                    ((Element) selectedDataObservable.getValue()).getName()));
            EMFObservables
                    .observeDetailValue(Realm.getDefault(), selectedDataObservable, ProcessPackage.Literals.ELEMENT__NAME)
                    .addValueChangeListener(
                            e -> setDescription(Messages.bind(Messages.setGeneratedScriptPreviewPageDescription,
                                    e.diff.getNewValue())));
        }
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(15, 15).margins(10, 10).create());
        createGroovyHelpLinkComposite(mainComposite);
        createScriptNameComposite(mainComposite);
        createScriptPreviewComposite(mainComposite);
        setControl(mainComposite);
    }

    protected void createScriptNameComposite(final Composite mainComposite) {
        final Label scriptNameLabel = new Label(mainComposite, SWT.NONE);
        scriptNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        scriptNameLabel.setText(Messages.scriptNameLabel);
        scriptNameText = new Text(mainComposite, SWT.BORDER);
        scriptNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        scriptNameText.setEditable(false);
    }

    private void createGroovyHelpLinkComposite(final Composite mainComposite) {
        final GroovyHelpLinkFactory groovyHelpLinkFactory = new GroovyHelpLinkFactory();
        final Composite groovyHelpComposite = new Composite(mainComposite, SWT.NONE);
        groovyHelpComposite.setLayout(new FillLayout(SWT.VERTICAL));
        groovyHelpComposite.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).span(2, 0).align(SWT.END, SWT.CENTER).create());
        groovyHelpLinkFactory.createGroovyHelpLink(groovyHelpComposite);
    }

    protected void createScriptPreviewComposite(final Composite mainComposite) {
        final Composite previewComposite = new Composite(mainComposite, SWT.NONE);
        previewComposite.setLayout(new FillLayout(SWT.VERTICAL));
        previewComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 0).create());

        final GroovyViewer groovyViewer = sourceViewerFactory.createSourceViewer(previewComposite, true);
        groovyViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
        final SourceViewer sourceViewer = groovyViewer.getSourceViewer();
        sourceViewer.setEditable(false);
        sourceViewer.setEventConsumer(new IEventConsumer() {

            @Override
            public void processEvent(final VerifyEvent event) {
                event.doit = false;
            }
        });
        document = groovyViewer.getDocument();
        document.set(generatedExpression.getContent());
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        final BusinessObjectData data = (BusinessObjectData) selectedDataObservable.getValue();
        if (data != null) {
            try {
                getContainer().run(false, false, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        generateExpressionScript(data, monitor);
                    }
                });
                document.set(generatedExpression.getContent());
                if (generatedExpression.getName() != null) {
                    scriptNameText.setText(generatedExpression.getName());
                }
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error("Failed to create Operations from contract", e);
                new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.contractFromDataCreationErrorMessage, e)
                        .open();
            }
        }
    }

    protected void generateExpressionScript(BusinessObjectData data, IProgressMonitor monitor)
            throws InvocationTargetException {
        rootContractInputGenerator = createRootContractInputGenerator();
        if (!fieldToContractInputMappingsObservable.isEmpty()) {
            try {
                rootContractInputGenerator.buildForInstanciation(data, monitor);
                generatedExpression = rootContractInputGenerator.getInitialValueExpression();
            } catch (OperationCreationException e) {
                throw new InvocationTargetException(e);
            }
        }
    }

    protected RootContractInputGenerator createRootContractInputGenerator() {
        final List<FieldToContractInputMapping> mappings = new ArrayList<>();
        for (final Object mapping : fieldToContractInputMappingsObservable) {
            mappings.add((FieldToContractInputMapping) mapping);
        }
        return new RootContractInputGenerator((String) rootNameObservable.getValue(), mappings,
                repositoryAccessor, operationBuilder, expressionBuilder);
    }

    public RootContractInputGenerator getRootContractInputGenerator() {
        return rootContractInputGenerator;
    }

}
