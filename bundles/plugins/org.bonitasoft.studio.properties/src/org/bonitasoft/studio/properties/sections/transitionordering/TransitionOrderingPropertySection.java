/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.transitionordering;

import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Aurelien Pupier
 *
 */
public class TransitionOrderingPropertySection extends AbstractBonitaDescriptionSection {

    private EMFDataBindingContext databindingContext;
    private ListViewer listViewer;
    private Button upButton;
    private Button downButton;

    public TransitionOrderingPropertySection() {
    }

    @Override
    protected void createContent(final Composite parent) {
        final Composite mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(2).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createButtons(mainComposite);
        createList(mainComposite);
    }

    protected void createExplanationLabel(final Composite mainComposite) {
        final Label explanationLabel = getWidgetFactory().createLabel(mainComposite, Messages.transitionOrderingExplanation_Short,SWT.WRAP);
        explanationLabel.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).span(2, 1).create());
        final ControlDecoration cd = new ControlDecoration(explanationLabel, SWT.RIGHT);
        final FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION);
        cd.setImage(fieldDecoration.getImage());
        cd.setDescriptionText(Messages.transitionOrderingExplanation);
    }

    protected void createList(final Composite mainComposite) {
        final List list = getWidgetFactory().createList(mainComposite, SWT.BORDER | SWT.V_SCROLL);
        list.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 90).create());
        listViewer = new ListViewer(list);
        listViewer.setContentProvider(new ObservableListContentProvider<Element>());
        listViewer.setLabelProvider(new LabelProvider(){
            @Override
            public String getText(final Object element) {
                if(element != null && element instanceof Connection){
                    final String transitionName = ((Connection) element).getName();
                    return transitionName +" -- "+((Connection) element).getTarget().getName();
                }
                return super.getText(element);
            }
        });
        listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                updateButtonsEnablement();
            }
        });
    }

    protected void updateButtonsEnablement() {
        if(listViewer.getSelection().isEmpty()){
            upButton.setEnabled(false);
            downButton.setEnabled(false);
        } else {
            final Object selectedConnection = ((IStructuredSelection) listViewer.getSelection()).getFirstElement();
            final EList<Connection> outgoingConnections = getSourceElement().getOutgoing();
            final int indexSelected = outgoingConnections.indexOf(selectedConnection);
            upButton.setEnabled(indexSelected != 0);
            downButton.setEnabled(indexSelected < outgoingConnections.size() -1);
        }
    }

    private void createButtons(final Composite mainComposite) {
        final Composite buttonComposite = getWidgetFactory().createComposite(mainComposite);
        final RowLayout layout = new RowLayout(SWT.VERTICAL);
        layout.fill =true;
        buttonComposite.setLayout(layout);

        createButtonUp(buttonComposite);
        createButtonDown(buttonComposite);
    }

    private void createButtonUp(final Composite buttonComposite) {
        upButton = getWidgetFactory().createButton(buttonComposite, Messages.up, SWT.FLAT);
        upButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                final java.util.List<?> selectedConnections = ((IStructuredSelection) listViewer.getSelection()).toList();
                final CompoundCommand command = new CompoundCommand("Up elements in transition ordering");
                for (final Object selectedConnection : selectedConnections) {
                    final int oldIndex = getSourceElement().getOutgoing().indexOf(selectedConnection);
                    if(oldIndex != -1){//should never happened
                        final int newIndex = Math.min(getSourceElement().getOutgoing().size() - 1, oldIndex -1);
                        command.append(MoveCommand.create(getEditingDomain(), getSourceElement(), ProcessPackage.Literals.SOURCE_ELEMENT__OUTGOING, selectedConnection, newIndex));
                    }
                }
                getEditingDomain().getCommandStack().execute(command);
                listViewer.setSelection(new StructuredSelection(selectedConnections));
                listViewer.refresh();
            }
        });

    }

    private void createButtonDown(final Composite buttonComposite) {
        downButton = getWidgetFactory().createButton(buttonComposite, Messages.down, SWT.FLAT);
        downButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                final java.util.List<?> selectedConnections = ((IStructuredSelection) listViewer.getSelection()).toList();
                final CompoundCommand command = new CompoundCommand("Down elements in transition ordering");
                for (final Object selectedConnection : selectedConnections) {
                    final int oldIndex = getSourceElement().getOutgoing().indexOf(selectedConnection);
                    if(oldIndex != -1){//should never happened
                        final int sizeOfOutgoingList = getSourceElement().getOutgoing().size();
                        final int newIndex = Math.min(sizeOfOutgoingList-1, oldIndex +1);
                        command.append(MoveCommand.create(getEditingDomain(), getSourceElement(), ProcessPackage.Literals.SOURCE_ELEMENT__OUTGOING, selectedConnection, newIndex));
                    }
                }
                getEditingDomain().getCommandStack().execute(command);
                listViewer.setSelection(new StructuredSelection(selectedConnections));
                listViewer.refresh();
            }
        });

    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        bindList();
        updateButtonsEnablement();
    }


    protected void bindList() {
        final SourceElement sourceElement = getSourceElement();
        final IObservableList outgoingListObserved = EMFEditProperties.list(getEditingDomain(), ProcessPackage.Literals.SOURCE_ELEMENT__OUTGOING).observe(sourceElement);
        listViewer.setInput(outgoingListObserved);
    }

    @Override
    public void dispose() {
        super.dispose();
        if(databindingContext != null){
            databindingContext.dispose();
        }
    }

    private SourceElement getSourceElement(){
        return (SourceElement) getEObject();
    }

    @Override
    public String getSectionDescription() {
        return Messages.transitionOrderingExplanation;
    }

}
