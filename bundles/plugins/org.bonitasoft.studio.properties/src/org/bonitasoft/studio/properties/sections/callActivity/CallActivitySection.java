/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.callActivity;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.general.ProcessNamesExpressionNatureProvider;
import org.bonitasoft.studio.properties.sections.general.ProcessVersionsExpressionNatureProvider;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.internal.e4.compatibility.CompatibilityView;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


public class CallActivitySection extends AbstractBonitaDescriptionSection {

    @Inject
    private CallActivitySelectionProvider selectionProvider;

    private EMFDataBindingContext context;

    @Inject
    private EPartService partService;

    @Override
    public String getSectionDescription() {
        return null;
    }

    @Override
    public void dispose() {
        if (context != null) {
            context.dispose();
        }
        super.dispose();
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        selectionProvider.setSelection(selection);
    }


    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#createContent(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createContent(Composite composite) {
        context = new EMFDataBindingContext();
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 20).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();
        GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).applyTo(widgetFactory.createLabel(composite, Messages.name));

        final ExpressionViewer nameViewer = new ExpressionViewer(composite, SWT.BORDER, widgetFactory);
        nameViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        nameViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SCRIPT_TYPE, ExpressionConstants.PARAMETER_TYPE }));
        nameViewer.setExpressionNatureProvider(new ProcessNamesExpressionNatureProvider());

        IObservableValue diagramSelection = ViewersObservables.observeSingleSelection(selectionProvider);
        diagramSelection.addValueChangeListener(event -> {
            Object value = (EObject) event.diff.getNewValue();
            if (value instanceof CallActivity) {
                nameViewer.setInput(value);
                nameViewer.setSelection(new StructuredSelection(((CallActivity) value).getCalledActivityName()));
            }
        });

        GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).applyTo(widgetFactory.createLabel(composite, Messages.version));

        final ExpressionViewer versionViewer = new ExpressionViewer(composite, SWT.BORDER, widgetFactory);
        versionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        versionViewer.addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SCRIPT_TYPE, ExpressionConstants.PARAMETER_TYPE));
        versionViewer.setMessage(Messages.calledProcessVersionHint);
        versionViewer.setExpressionNatureProvider(new ProcessVersionsExpressionNatureProvider());

        diagramSelection.addValueChangeListener(event -> {
            Object value = (EObject) event.diff.getNewValue();
            if (value instanceof CallActivity) {
                versionViewer.setInput(value);
                versionViewer.setSelection(new StructuredSelection(((CallActivity) value).getCalledActivityVersion()));
            }
        });

        nameViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (versionViewer != null) {
                    versionViewer.updateAutocompletionProposals();
                }
            }
        });

        final Hyperlink dataToPassLink = widgetFactory.createHyperlink(composite, Messages.configureDataToSend, SWT.NONE);
        GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).span(2, 1)
                .applyTo(dataToPassLink);
        dataToPassLink.addHyperlinkListener(new HyperlinkAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.ui.forms.events.HyperlinkAdapter#linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent)
             */
            @Override
            public void linkActivated(HyperlinkEvent e) {
                final MPart dataPart = partService.showPart("org.bonitasoft.studio.views.properties.process.execution", PartState.ACTIVATE);
                final TabbedPropertySheetPage page = (TabbedPropertySheetPage) ((CompatibilityView) dataPart.getObject()).getPart()
                        .getAdapter(TabbedPropertySheetPage.class);
                if (page != null) {
                    page.setSelectedTab("tab.callActivityMapping.input");
                }
            }
        });
        
        
    }

}
