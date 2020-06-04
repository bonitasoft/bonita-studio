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
package org.bonitasoft.studio.properties.sections.general.activities;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.diagram.custom.BonitaNodesElementTypeResolver;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Mickael Istria
 *
 */
public class ActivityTypeSelectionGridPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    public static final String ACTIVITY_TYPE__TASK = Messages.activityType_task;
    public static final String ACTIVITY_TYPE__CALL_ACTVITY = Messages.activityType_callActivity;
    private static final String ACTIVITY_TYPE__SYSTEM = Messages.activityType_abstractTask;
    private static final String ACTIVITY_TYPE__RECEIVE_TASK = Messages.activityType_receiveTask;
    private static final String ACTIVITY_TYPE__SEND_TASK = Messages.activityType_sendTask;
    private static final String ACTIVITY_TYPE__SERVICE_TASK = Messages.activityType_serviceTask;
    private static final String ACTIVITY_TYPE__SCRIPT_TASK = Messages.activityType_scriptTask;
    private ComboViewer combo;
    private Activity activity;
    private GraphicalEditPart node;

    public ActivityTypeSelectionGridPropertySectionContribution(TabbedPropertySheetPage tabbedPropertySheetPage) {

    }

    @Override
    public void refresh() {
        if (activity != null && combo != null) {
            EClass eClass = activity.eClass();
            if (eClass.equals(ProcessPackage.Literals.TASK)) {
                combo.setSelection(new StructuredSelection(ACTIVITY_TYPE__TASK));
            } else if (eClass.equals(ProcessPackage.Literals.CALL_ACTIVITY)) {
                combo.setSelection(new StructuredSelection(ACTIVITY_TYPE__CALL_ACTVITY));
            } else if (eClass.equals(ProcessPackage.Literals.ACTIVITY)) {
                combo.setSelection(new StructuredSelection(ACTIVITY_TYPE__SYSTEM));
            }else if (eClass.equals(ProcessPackage.Literals.RECEIVE_TASK)) {
                combo.setSelection(new StructuredSelection(ACTIVITY_TYPE__RECEIVE_TASK));
            }else if (eClass.equals(ProcessPackage.Literals.SEND_TASK)) {
                combo.setSelection(new StructuredSelection(ACTIVITY_TYPE__SEND_TASK));
            }else if (eClass.equals(ProcessPackage.Literals.SERVICE_TASK)) {
                combo.setSelection(new StructuredSelection(ACTIVITY_TYPE__SERVICE_TASK));
            }else if (eClass.equals(ProcessPackage.Literals.SCRIPT_TASK)) {
                combo.setSelection(new StructuredSelection(ACTIVITY_TYPE__SCRIPT_TASK));
            }
        }
    }

    private void createActivitySelectionCombo(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
        combo = new ComboViewer(parent, SWT.READ_ONLY);
        combo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        combo.setContentProvider(new ArrayContentProvider());
        combo.setLabelProvider(new LabelProvider());
        combo.setInput(new String[]{ACTIVITY_TYPE__TASK,ACTIVITY_TYPE__SYSTEM,ACTIVITY_TYPE__CALL_ACTVITY,ACTIVITY_TYPE__RECEIVE_TASK,ACTIVITY_TYPE__SEND_TASK,ACTIVITY_TYPE__SERVICE_TASK,ACTIVITY_TYPE__SCRIPT_TASK});
        combo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent arg0) {
                if (toBeConverted()) {
                    EClass targetEClass = getTargetEClass();
                    IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
                    if(editor instanceof ProcessDiagramEditor){
                        node = (GraphicalEditPart) ((IStructuredSelection)((ProcessDiagramEditor)editor).getDiagramGraphicalViewer().getSelection()).getFirstElement() ;
						GMFTools.convert(targetEClass, node, new BonitaNodesElementTypeResolver(),GMFTools.PROCESS_DIAGRAM);
						for(IViewReference vr : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences() ){
							if(vr.getId().startsWith("org.bonitasoft.studio.views.properties")){
								IViewPart viewPart = vr.getView(false) ;
								if(viewPart != null){
									IPropertySheetPage page = (IPropertySheetPage) viewPart.getAdapter(IPropertySheetPage.class);
									if(page != null){
										page.selectionChanged(editor, ((ProcessDiagramEditor)editor).getDiagramGraphicalViewer().getSelection());
									}
								}
							}
						}
                      }
                }

            }
        });


    }

    private boolean toBeConverted() {
        return ! activity.eClass().equals(getTargetEClass());
    }

    private EClass getTargetEClass() {
        String text = (String) ((IStructuredSelection) combo.getSelection()).getFirstElement();
        if (text.equals(ACTIVITY_TYPE__CALL_ACTVITY)) {
            return ProcessPackage.Literals.CALL_ACTIVITY;
        } else if (text.equals(ACTIVITY_TYPE__TASK)) {
            return ProcessPackage.Literals.TASK;
        } else if (text.equals(ACTIVITY_TYPE__SYSTEM)) {
            return ProcessPackage.Literals.ACTIVITY;
        } else if(text.equals(ACTIVITY_TYPE__RECEIVE_TASK)){
            return ProcessPackage.Literals.RECEIVE_TASK;
        } else if (text.equals(ACTIVITY_TYPE__SEND_TASK)){
            return ProcessPackage.Literals.SEND_TASK;
        }else if (text.equals(ACTIVITY_TYPE__SERVICE_TASK)){
            return ProcessPackage.Literals.SERVICE_TASK;
        }else if (text.equals(ACTIVITY_TYPE__SCRIPT_TASK)){
            return ProcessPackage.Literals.SCRIPT_TASK;
        }
        return ProcessPackage.Literals.ACTIVITY;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory)
     */
    @Override
    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection page) {
        createActivitySelectionCombo(composite, widgetFactory);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.activityType;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof Activity && !(eObject instanceof SubProcessEvent);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(EObject object) {
        activity = (Activity)object;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    @Override
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(ISelection selection) {
        if(((IStructuredSelection)selection).getFirstElement() instanceof GraphicalEditPart) {
            node = (GraphicalEditPart) ((IStructuredSelection)selection).getFirstElement();
        }
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {

    }

}