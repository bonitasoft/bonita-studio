/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.simulation.properties.contributions;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.DynamicAddRemoveLineComposite;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.simulation.DataChange;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.wizards.AddSimulationDataWizard;
import org.eclipse.core.databinding.Binding;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public class DataChangesContribution  extends AbstractPropertySectionContribution{

    List<Combo> combos = new ArrayList<Combo>();
    List<Button> createButtons = new ArrayList<Button>();
    List<Composite> composites = new ArrayList<Composite>();
    List<Binding> bindings = new ArrayList<Binding>();
    private DynamicAddRemoveLineComposite dynamicComposite;
    private EMFDataBindingContext context;
    private final List<DataChange> dataChanges = new ArrayList<DataChange>();


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof SimulationActivity;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.Data;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
     */
    @Override
    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory, final ExtensibleGridPropertySection extensibleGridPropertySection) {
        composite.setLayout(new GridLayout(1,false));
        context = new EMFDataBindingContext();
        dynamicComposite = new DynamicAddRemoveLineComposite(composite,SWT.NONE){

            @Override
            protected org.eclipse.swt.widgets.Button createAddButton(final Composite parent) {
                final org.eclipse.swt.widgets.Button addButton = new Button(parent, SWT.FLAT);
                addButton.setText(Messages.add);
                return addButton;
            };

            @Override
            protected Control createLineComposite(final Composite parent,final Object object) {
                final DataChange dataChange;
                if(object instanceof DataChange){
                    dataChange = (DataChange) object;
                }else{
                    dataChange = SimulationFactory.eINSTANCE.createDataChange();
                    final AddCommand addCommand = new AddCommand(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_ACTIVITY__DATA_CHANGE, dataChange);
                    editingDomain.getCommandStack().execute(addCommand);
                }
                final Composite lineComposite = getWidgetFactory().createComposite(parent);
                composites.add(lineComposite);
                lineComposite.setLayout(new GridLayout(5,false));

                getWidgetFactory().createLabel(lineComposite, Messages.Data);

                final Combo combo = new Combo(lineComposite, SWT.READ_ONLY);
                combo.setLayoutData(GridDataFactory.swtDefaults().hint(85, SWT.DEFAULT).create());
                final ComboViewer viewer = new ComboViewer(combo);
                viewer.setLabelProvider(new LabelProvider(){
                    @Override
                    public String getText(final Object element) {
                        return ((SimulationData)element).getName();
                    };
                });
                viewer.setContentProvider(ArrayContentProvider.getInstance());
                viewer.setInput(ModelHelper.getParentProcess(eObject).getSimulationData());
                if(dataChange.getData() != null){
                    viewer.setSelection(new StructuredSelection(dataChange.getData()));
                }
                getWidgetFactory().createLabel(lineComposite, Messages.Expression);

                final ExpressionViewer expressionText = new ExpressionViewer(lineComposite, SWT.BORDER,widgetFactory,editingDomain, SimulationPackage.Literals.DATA_CHANGE__VALUE);
                expressionText.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,ExpressionConstants.SIMULATION_VARIABLE_TYPE,ExpressionConstants.SCRIPT_TYPE}));
                expressionText.getControl().setLayoutData(GridDataFactory.swtDefaults().hint(250, SWT.DEFAULT).create());
                
                Expression selection = dataChange.getValue() ;
                if(selection == null){
                    selection = ExpressionFactory.eINSTANCE.createExpression() ;
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, dataChange, SimulationPackage.Literals.DATA_CHANGE__VALUE, selection)) ;
                }
                context.bindValue(ViewerProperties.singleSelection().observe(expressionText),
                		EMFEditProperties.value(editingDomain, SimulationPackage.Literals.DATA_CHANGE__VALUE).observe(dataChange));
                expressionText.setInput(dataChange) ;
                //expressionText.setSelection(new StructuredSelection(selection)) ;


                final Button createDataButton = new Button(lineComposite,SWT.FLAT) ;
                createDataButton.setText(Messages.createSimulationData) ;
                createDataButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        final AddSimulationDataWizard wiz = new AddSimulationDataWizard(ModelHelper.getParentProcess(eObject), editingDomain) ;
                        if(new WizardDialog(Display.getDefault().getActiveShell(), wiz).open() == WizardDialog.OK){
                            viewer.setInput(ModelHelper.getParentProcess(eObject).getSimulationData());
                            viewer.setSelection(new StructuredSelection(wiz.getCreatedData())) ;
                            //                            expressionText.reset() ;
                            //                            expressionText.setText(GroovyUtil.GROOVY_PREFIX + wiz.getCreatedData().getName() + GroovyUtil.GROOVY_SUFFIX);
                        }

                    }

                }) ;

                createButtons.add(createDataButton) ;
                combos.add(combo);
                dataChanges.add(dataChange);
                bindings.add(context.bindValue(ViewersObservables.observeSingleSelection(viewer), EMFEditObservables.observeValue(editingDomain, dataChange, SimulationPackage.Literals.DATA_CHANGE__DATA)));



                return lineComposite;
            }
            @Override
            protected void lineRemoved(final int i) {
                composites.remove(i);
                createButtons.remove(i) ;
                combos.remove(i);
                context.removeBinding(bindings.get(i));
                bindings.remove(i);
                final DataChange dataChange = dataChanges.get(i);
                final RemoveCommand removeCommand = new RemoveCommand(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_ACTIVITY__DATA_CHANGE, dataChange);
                editingDomain.getCommandStack().execute(removeCommand);
                dataChanges.remove(i);
                extensibleGridPropertySection.getTabbedPropertySheetPage().resizeScrolledComposite();
            }
            @Override
            protected void lineAdded(final int i) {
                extensibleGridPropertySection.getTabbedPropertySheetPage().resizeScrolledComposite();
            }
            @Override
            protected TabbedPropertySheetWidgetFactory getWidgetFactory() {
                return widgetFactory;
            }
            @Override
            protected Composite getTopComposite() {
                return (Composite) extensibleGridPropertySection.getTabbedPropertySheetPage().getControl();
            }


        };
        dynamicComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());


        fillDynamicWidget();


    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {
        if(context != null) {
            context.dispose();
        }
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(final EObject object) {
        super.setEObject(object);
    }

    /**
     * 
     */
    private void fillDynamicWidget() {
        dataChanges.clear();
        for (final DataChange dataChange : ((SimulationActivity) eObject).getDataChange()) {
            dynamicComposite.addLine(dataChange);
        }
    }


}
