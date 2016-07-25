/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.general;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
/**
 * @author Mickael Istria
 *
 */
public class SubProcessPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    private CallActivity subprocess;
    private TransactionalEditingDomain editingDomain;
    private ExpressionViewer nameViewer;
    private ExpressionViewer versionViewer;

    private final DiagramRepositoryStore diagramStore;
    private EMFDataBindingContext dbc;
    private ProcessVersionsExpressionNatureProvider versionsNatureProvider;


    public SubProcessPropertySectionContribution(){
        diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class) ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.properties.sections.general.ExtensibleGridPropertySection)
     */
    @Override
    public void createControl(final Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {

        dbc = new EMFDataBindingContext();

        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        widgetFactory.createLabel(composite, Messages.name);

        nameViewer = new ExpressionViewer(composite, SWT.BORDER, widgetFactory, editingDomain, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME);
        nameViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        nameViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,ExpressionConstants.VARIABLE_TYPE,ExpressionConstants.SCRIPT_TYPE,ExpressionConstants.PARAMETER_TYPE}));
        nameViewer.setExpressionNatureProvider(new ProcessNamesExpressionNatureProvider()) ;
        nameViewer.setInput(subprocess);
        nameViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if(versionViewer != null){
                    versionViewer.updateAutocompletionProposals();
                }
            }
        });
        Expression nameExp = subprocess.getCalledActivityName();
        if(nameExp == null){
            nameExp = ExpressionFactory.eINSTANCE.createExpression();
            nameExp.setReturnTypeFixed(true);
            nameExp.setReturnType(String.class.getName());
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, subprocess, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME, nameExp));
        }

        dbc.bindValue(ViewersObservables.observeSingleSelection(nameViewer), EMFEditObservables.observeValue(editingDomain, subprocess, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME));

        widgetFactory.createLabel(composite, Messages.version);

        versionViewer = new ExpressionViewer(composite, SWT.BORDER, widgetFactory, editingDomain, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION);
        versionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        versionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,ExpressionConstants.VARIABLE_TYPE,ExpressionConstants.SCRIPT_TYPE,ExpressionConstants.PARAMETER_TYPE}));
        versionViewer.setMessage(Messages.calledProcessVersionHint,IStatus.INFO);
        versionsNatureProvider = new ProcessVersionsExpressionNatureProvider();
        versionViewer.setExpressionNatureProvider(versionsNatureProvider);
        versionViewer.setInput(subprocess);

        Expression versionExp = subprocess.getCalledActivityVersion();
        if(versionExp == null){
            versionExp = ExpressionFactory.eINSTANCE.createExpression();
            versionExp.setReturnTypeFixed(true);
            versionExp.setReturnType(String.class.getName());
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, subprocess, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION, versionExp));
        }

        dbc.bindValue(ViewersObservables.observeSingleSelection(versionViewer), EMFEditObservables.observeValue(editingDomain, subprocess, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION));
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.subprocess;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof CallActivity;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {
        if (subprocess == null ||nameViewer == null || nameViewer.getControl().isDisposed() ) {
            return;
        }

        //        // populate name combo
        //
        //        nameViewer.remove(0, nameViewer.getItemCount() - 1);
        //        versionCombo.remove(0, versionCombo.getItemCount() - 1);
        //        Set<String> values = new HashSet<String>();
        //        Set<String> versionValues = new HashSet<String>();
        //
        //        if (subprocess.getSubprocessName() != null) {
        //            currentSubprocessName = subprocess.getSubprocessName() ;
        //        }
        //
        //        currentSubprocessVersion = subprocess.getSubprocessVersion() ;
        //
        //        for (AbstractProcess process : diagramStore.getAllProcesses()) {
        //            if (process.getName() != null) {
        //                values.add(process.getName());
        //            }
        //        }


        //        List<String> sortedValues = new ArrayList<String>(values);
        //        Collections.sort(sortedValues);
        //        for (String processName : sortedValues) {
        //            nameViewer.add(processName);
        //        }

        //
        //        if (currentSubprocessName != null) {
        //            Point p = nameViewer.getSelection();
        //            nameViewer.setText(currentSubprocessName);
        //            nameViewer.setSelection(p);
        //        }
        //
        //        if(currentSubprocessName != null){
        //            versionValues.addAll(getAllVersionAvailableFor(currentSubprocessName));
        //        }
        //
        //        for (String version : versionValues) {
        //            versionCombo.add(version);
        //        }
        //
        //        versionCombo.add(Messages.latestLabel);
        //
        //        if (currentSubprocessVersion != null && currentSubprocessVersion.trim().length() >0) {
        //            Point p = versionCombo.getSelection();
        //            versionCombo.setText(currentSubprocessVersion);
        //            versionCombo.setSelection(p);
        //        }else{
        //            Point p = versionCombo.getSelection();
        //            versionCombo.setText(Messages.latestLabel);
        //            versionCombo.setSelection(p);
        //        }

    }

    //    private List<String> getAllVersionAvailableFor(String subprocessName) {
    //        List<String> values = new ArrayList<String>() ;
    //
    //        for(AbstractProcess process : diagramStore.getAllProcesses()){
    //            if(process.getName().equals(subprocessName)){
    //                values.add(process.getVersion());
    //            }
    //        }
    //        Collections.sort(values);
    //
    //        return values;
    //    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(EObject object) {
        subprocess =  (CallActivity) object;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    @Override
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(ISelection selection) {
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {

    }



}
