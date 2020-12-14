/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages;

import java.util.List;

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sapjco3.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.sapjco3.providers.AutoCompleteExpressionProvider;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapFunction;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapTool;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Maxence Raoux
 *
 */
public class SapFonctionPageComponentSwitch extends PageComponentSwitch {

    private ExpressionViewer repositoryViewer;
    private ExpressionViewer functionNameViewer;
    private org.eclipse.swt.widgets.Text descriptionLabel;
    private final SapTool sapTool;

    public SapFonctionPageComponentSwitch(final IWizardContainer iWizardContainer,
            final Composite parent, final EObject container,
            final ConnectorDefinition definition,
            final ConnectorConfiguration connectorConfiguration,
            final EMFDataBindingContext context,
            final DefinitionResourceProvider messageProvider,
            final AvailableExpressionTypeFilter connectorExpressionContentTypeFilter,
            final SapTool sapTool) {
        super(iWizardContainer, parent, container, definition,
                connectorConfiguration, context, messageProvider,
                connectorExpressionContentTypeFilter);
        this.sapTool = sapTool;
    }

    @Override
    protected ExpressionViewer createTextControl(final Composite composite,
            final Text object) {
        if ("repository".equals(object.getInputName())) {
            createRepositoryViewer(composite, object);
            return repositoryViewer;
        }

        else if ("functionName".equals(object.getInputName())) {
            createGroupViewer(composite);
            createFunctionNameViwer(composite, object);
            createDescription(composite);
            return functionNameViewer;
        } else {
            return super.createTextControl(composite, object);
        }
    }

    private void createDescription(final Composite composite) {
        final Label l = new Label(composite, SWT.NONE);
        l.setText(Messages.functionFilterLabel);
        l.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).align(SWT.RIGHT, SWT.CENTER).create());
        l.setText(Messages.functionDescriptionTitle);

        final Composite functionComposite = new Composite(composite, SWT.NONE);
        functionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        functionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        descriptionLabel = new org.eclipse.swt.widgets.Text(functionComposite,SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().indent(17, 0).align(SWT.LEFT, SWT.CENTER).grab(true, false).create());
        descriptionLabel.setText(Messages.functionDescriptionError);
    }

    private void createFunctionNameViwer(final Composite composite, final Text object) {
        functionNameViewer = super.createTextControl(composite, object);
        makeAutoCompletion(functionNameViewer, sapTool.getFonctionsNames());
        functionNameViewer
        .addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final String selectedFunction = ((org.eclipse.swt.widgets.Text) functionNameViewer.getTextControl()).getText();
                if (sapTool.isFunction(selectedFunction)) {
                    sapTool.selectedFunction = selectedFunction;
                    final SapFunction function = sapTool.getFunction(selectedFunction);
                    descriptionLabel.setText(function.getDescription());
                } else {
                    descriptionLabel.setText(Messages.functionDescriptionError);
                }
                descriptionLabel.redraw();
                descriptionLabel.update();
            }
        });
    }

    private void createGroupViewer(final Composite composite) {
        final Label l = new Label(composite, SWT.NONE);
        l.setText(Messages.functionFilterLabel);
        l.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).align(SWT.RIGHT, SWT.CENTER).create());
        final ExpressionViewer groupViewer = new ExpressionViewer(composite, SWT.BORDER);
        final Expression groupSelected = ExpressionFactory.eINSTANCE.createExpression();
        groupViewer.setSelection(new StructuredSelection(groupSelected));
        groupViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        makeAutoCompletion(groupViewer, sapTool.getAllGroups());
        groupViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                groupViewerListener(groupSelected);
            }
        });
    }

    private void groupViewerListener(final Expression groupSelected) {
        final String groupName = groupSelected.getContent();
        if (sapTool.isGroup(groupName)) {
            makeAutoCompletion(functionNameViewer, sapTool.getFonctionsNamesByGroupe(groupName));
            ((org.eclipse.swt.widgets.Text) functionNameViewer.getTextControl()).setText("");
            functionNameViewer.refresh();
        } else {
            makeAutoCompletion(functionNameViewer, sapTool.getFonctionsNames());
        }
    }


    private void createRepositoryViewer(final Composite composite, final Text object) {
        repositoryViewer = super.createTextControl(composite, object);
        repositoryViewer
        .addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final String repositoryName = ((org.eclipse.swt.widgets.Text) repositoryViewer.getTextControl()).getText();
                sapTool.setDistanationName(repositoryName);
            }
        });
    }

    private void makeAutoCompletion(final ExpressionViewer expressionViewer,
            final List<String> additionalItems) {
        final AutoCompleteExpressionProvider provider = new AutoCompleteExpressionProvider(additionalItems);
        expressionViewer.setExpressionNatureProvider(provider);
        expressionViewer.updateAutocompletionProposals();

    }

}
