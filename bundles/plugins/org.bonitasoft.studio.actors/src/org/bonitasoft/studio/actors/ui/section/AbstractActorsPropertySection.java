/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.ui.section;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.ActorFilterDefinitionWizardDialog;
import org.bonitasoft.studio.actors.ui.wizard.AddActorWizard;
import org.bonitasoft.studio.actors.ui.wizard.FilterWizard;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractActorsPropertySection extends AbstractBonitaDescriptionSection implements ISelectionChangedListener{

    protected ComboViewer actorComboViewer;
    protected EMFDataBindingContext emfDatabindingContext;
    protected Text filterText;
    protected ToolItem removeConnectorButton;
    protected Button updateConnectorButton;
    private StyledFilterLabelProvider filterLabelProvider;
    protected Button setButton;

    @Override
    protected void createContent(final Composite parent) {
        final TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();

        final Composite mainComposite = widgetFactory.createComposite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).extendedMargins(0, 25, 0, 25).spacing(10, 15).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createRadioComposite(widgetFactory, mainComposite);

        final Label actorsLabel = widgetFactory.createLabel(mainComposite, Messages.selectActor);
        actorsLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());

        createActorComboViewer(mainComposite);

        createAddActorButton(mainComposite);

        filterLabelProvider = new StyledFilterLabelProvider();
        createFiltersViewer(mainComposite);

        updateDatabinding();

    }


    private void createAddActorButton(final Composite mainComposite) {
        final Button addActor = new Button(mainComposite, SWT.FLAT);
        addActor.setText(Messages.addActor);
        addActor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                final AddActorWizard actorWizard = new AddActorWizard(getEObject(), getEditingDomain());
                final WizardDialog wizardDialog = new WizardDialog(Display.getCurrent().getActiveShell(), actorWizard);
                if(wizardDialog.open() == Dialog.OK){
                    if(actorWizard.getNewActor()!=null){
                        actorComboViewer.setSelection(new StructuredSelection(actorWizard.getNewActor()));
                    }
                }
            }
        });
    }

    private void createActorComboViewer(final Composite mainComposite) {
        actorComboViewer = new ComboViewer(mainComposite, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY) ;
        actorComboViewer.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        actorComboViewer.setContentProvider(new ArrayContentProvider()) ;
        actorComboViewer.setLabelProvider(new LabelProvider(){
            @Override
            public String getText(final Object element) {
                if(element instanceof Actor){
                    String doc = ((Actor) element).getDocumentation() ;
                    if(doc != null && !doc.isEmpty()){
                        doc = " -- "+ doc ;
                    }else{
                        doc = "" ;
                    }
                    return ((Actor) element).getName() + doc ;
                }
                return super.getText(element);
            }
        });
    }

    protected void createFiltersViewer(final Composite parent) {

        final Label actorFilters = getWidgetFactory().createLabel(parent, Messages.actorFilter, SWT.NONE) ;
        actorFilters.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;

        final Composite viewerComposite = getWidgetFactory().createPlainComposite(parent, SWT.NONE);
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create()) ;
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(0, 0).create()) ;


        setButton = createSetButton(viewerComposite);

        filterText = getWidgetFactory().createText(viewerComposite, "",
                GTKStyleHandler.replaceSingleWithWrap(
                        GTKStyleHandler.removeBorderFlag(SWT.BORDER | SWT.SINGLE | SWT.NO_FOCUS | SWT.READ_ONLY)));
        filterText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL,SWT.CENTER).grab(true,false).create());

        updateConnectorButton = createUpdateButton(viewerComposite);
        removeConnectorButton = createRemoveButton(viewerComposite);
    }

    protected EStructuralFeature getFilterFeature() {
        return ProcessPackage.Literals.ASSIGNABLE__FILTERS ;
    }

    protected ToolItem createRemoveButton(final Composite buttonsComposite) {
        final ToolBar toolBar = new ToolBar(buttonsComposite, SWT.FLAT);
        getWidgetFactory().adapt(toolBar);
        final ToolItem toolItem = new ToolItem(toolBar, SWT.FLAT);
        toolItem.setImage(Pics.getImage(PicsConstants.clear));
        toolItem.setToolTipText(Messages.remove);
        toolItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (MessageDialog.openConfirm(Display.getDefault().getActiveShell(), Messages.deleteDialogTitle, createMessage())) {
                    final Assignable assignable = (Assignable) getEObject();
                    final ActorFilter filter = assignable.getFilters().get(0);
                    getEditingDomain().getCommandStack().execute(new RemoveCommand(getEditingDomain(), getEObject(), getFilterFeature(), filter));
                    filterText.setText("");
                    updateButtons();
                }

            }

            public String createMessage() {
                final StringBuilder res = new StringBuilder(Messages.deleteDialogConfirmMessage);
                res.append(' ');
                final Assignable assignable = (Assignable) getEObject();
                res.append(assignable.getFilters().get(0).getName());

                res.append(" ?"); //$NON-NLS-1$
                return res.toString();
            }
        });
        return toolItem;
    }

    protected Button createUpdateButton(final Composite buttonsComposite) {
        final Button updateButton = getWidgetFactory().createButton(buttonsComposite, Messages.edit, SWT.FLAT);
        updateButton.setLayoutData(GridDataFactory.fillDefaults().hint(85,SWT.DEFAULT).create()) ;
        updateButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final Assignable assignable = (Assignable) getEObject() ;
                final ActorFilter filter = assignable.getFilters().get(0) ;
                final ActorFilterDefRepositoryStore defStore = RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class) ;
                final ConnectorDefinition def = defStore.getDefinition(filter.getDefinitionId(),filter.getDefinitionVersion()) ;
                if(def != null){
                    final WizardDialog wizardDialog = new ActorFilterDefinitionWizardDialog(Display.getCurrent().getActiveShell(), new FilterWizard(filter,getFilterFeature(),getFilterFeatureToCheckUniqueID()));
                    if(wizardDialog.open() == Dialog.OK){
                        final Assignable newAssignable = (Assignable) getEObject() ;
                        final ActorFilter newfilter = newAssignable.getFilters().get(0) ;
                        filterText.setText(filterLabelProvider.getText(newfilter) ) ;
                        updateButtons() ;
                    }
                }
            }

        });
        return updateButton;
    }

    protected Set<EStructuralFeature> getFilterFeatureToCheckUniqueID() {
        final Set<EStructuralFeature> res = new HashSet<EStructuralFeature>();
        res.add(getFilterFeature());
        return res;
    }

    protected Button createSetButton(final Composite buttonsComposite) {
        final Button setButton = getWidgetFactory().createButton(buttonsComposite, Messages.set, SWT.FLAT);
        setButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
        setButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final WizardDialog wizardDialog = new ActorFilterDefinitionWizardDialog(Display.getCurrent().getActiveShell(), new FilterWizard(getEObject(), getFilterFeature(), getFilterFeatureToCheckUniqueID()));
                if(wizardDialog.open() == Dialog.OK){
                    final Assignable assignable = (Assignable) getEObject() ;
                    if (assignable.getFilters().size()>1){
                        getEditingDomain().getCommandStack().execute(RemoveCommand.create(getEditingDomain(),assignable,assignable.getFilters(),assignable.getFilters().get(0)));
                    }
                    if (!assignable.getFilters().isEmpty()){
                        final ActorFilter filter = assignable.getFilters().get(0) ;
                        filterText.setText(filterLabelProvider.getText(filter)) ;
                    }
                    updateButtons() ;
                }
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

            }
        });
        return setButton ;
    }



    protected abstract void createRadioComposite(TabbedPropertySheetWidgetFactory widgetFactory,Composite mainComposite) ;


    @Override
    public void refresh() {
        super.refresh();
        if(filterText != null && !filterText.isDisposed()){
            final Assignable assignable = (Assignable) getEObject() ;
            if(assignable != null && !assignable.getFilters().isEmpty()){
                final ActorFilter filter = assignable.getFilters().get(0) ;
                filterText.setText(filterLabelProvider.getText(filter) ) ;
            }else{
                filterText.setText("") ;
            }
            updateButtons();
        }
        updateDatabinding() ;
    }


    protected void updateDatabinding() {
        final Assignable assignable = (Assignable) getEObject() ;
        if(assignable != null){
            if(emfDatabindingContext != null){
                emfDatabindingContext.dispose() ;
            }
            emfDatabindingContext = new EMFDataBindingContext() ;
            final AbstractProcess process = ModelHelper.getParentProcess(assignable) ;
            if(process != null) {
                emfDatabindingContext.bindValue(ViewersObservables.observeInput(actorComboViewer), EMFObservables.observeValue(process, ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS)) ;
                emfDatabindingContext.bindValue(ViewersObservables.observeSingleSelection(actorComboViewer), EMFEditObservables.observeValue(getEditingDomain(),assignable, ProcessPackage.Literals.ASSIGNABLE__ACTOR)) ; 
            }
        }
    }



    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        updateButtons() ;
    }



    private void updateButtons() {
        if (filterText != null) {
            final Assignable assignable = (Assignable) getEObject() ;
            ActorFilter filter = null ;
            if(!assignable.getFilters().isEmpty()){
                filter = assignable.getFilters().get(0) ;
            }

            //       if(!setButton.isDisposed()){
            //           setButton.setEnabled(filter == null) ;
            //       }

            if (!removeConnectorButton.isDisposed()) {
                removeConnectorButton.setEnabled(filter != null);
            }

            if(!updateConnectorButton.isDisposed()){
                if(filter != null){
                    final ActorFilterDefRepositoryStore connectorDefStore = RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class) ;
                    final ConnectorDefinition def = connectorDefStore.getDefinition(filter.getDefinitionId(),filter.getDefinitionVersion()) ;
                    updateConnectorButton.setEnabled(def!= null) ;
                }else{
                    updateConnectorButton.setEnabled(false) ;
                }

            }
        }
    }


    @Override
    public String getSectionDescription() {
        final EObject selectedEobject = getEObject();
        if(selectedEobject instanceof AbstractProcess){
            return Messages.addRemoveActors;
        } else if(selectedEobject instanceof Lane){
            return Messages.actorDescriptionLane;
        } else {
            return Messages.actorDescriptionTask;
        }
    }
}
