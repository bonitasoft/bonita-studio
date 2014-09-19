/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools.tree;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.PropertySectionWithTabs;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.PageFlowTransition;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaProcessTreeSelectionProvider {


    private static BonitaProcessTreeSelectionProvider INSTANCE ;


    private BonitaProcessTreeSelectionProvider(){

    }

    public static BonitaProcessTreeSelectionProvider getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BonitaProcessTreeSelectionProvider();
        }
        return INSTANCE ;
    }

    public void fireSelectionChanged(final IGraphicalEditPart ep ,final EObject element){
        TabbedPropertySheetPage page;
        try {
            page = getTabbedPropertySheetPage(element,ep);
            if(page != null){
                page.setSelectedTab(getTabIdForElement(element)) ;
                for(final ISection s : page.getCurrentTab().getSections()){
                    if(s instanceof PropertySectionWithTabs){
                        if(isTransientData(element)){
                            ((PropertySectionWithTabs)s).setSelectedTab(1) ;
                        }else{
                            ((PropertySectionWithTabs)s).setSelectedTab(0) ;
                        }
                    }
                }
            }
        } catch (final PartInitException e) {
            BonitaStudioLog.error(e) ;
        }
    }

    private String getTabIdForElement(final EObject element) {
        if(element instanceof FlowElement || element instanceof Connection || element instanceof AbstractProcess && !(element instanceof Pool)){
            return "tab.general" ;
        }else if(element instanceof Pool){
            return "tab.pool" ;
        }else if(element instanceof Lane){
            return "tab.lane" ;
        }else if(element instanceof AssociatedFile){
            if(((AssociatedFile)element).getPath() != null && ((AssociatedFile)element).getPath().endsWith(".html")){
                return "tab.lookandfeel" ;
            }else{
                return "tab.resource" ;
            }
        }else if(element instanceof ResourceFile || element instanceof ResourceFolder){
            return "tab.resource" ;
        } else if ((element instanceof Connector || element instanceof ConnectorParameter)
                && !element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getAssignable_Filters())) {
            return "tab.connectors" ;
        }else if(element instanceof Actor || element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getAssignable_Filters())){
            return "tab.actors" ;
        }else if(element instanceof Data){
            if(isTransientData(element)){
                if(element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getRecapFlow_RecapTransientData())){
                    return "tab.forms.overview" ;
                }else if(element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getViewPageFlow_ViewTransientData())){
                    return "tab.forms.view" ;
                }else if(element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getPageFlow_TransientData())){
                    return "tab.forms.entry" ;
                }
            }else{
                return "tab.data" ;
            }
        } else if(element instanceof Parameter){
            return "tab.parameter";
        } else if(element instanceof AbstractKPIBinding){
            return "tab.kpi" ;
        }else if(element instanceof ViewForm){
            if(((ViewForm)element).eContainingFeature().equals(ProcessPackage.eINSTANCE.getViewPageFlow_ViewForm())){
                return "tab.forms.view" ;
            }else if(((ViewForm)element).eContainingFeature().equals(ProcessPackage.eINSTANCE.getRecapFlow_RecapForms())){
                return "tab.forms.overview" ;
            }
        }else if(element instanceof Form){
            return "tab.forms.entry" ;
        }else if(element instanceof PageFlowTransition){
            if(element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getViewPageFlow_ViewPageFlowTransitions())){
                return "tab.forms.view" ;
            }else if(element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getRecapFlow_RecapPageFlowTransitions())){
                return "tab.forms.overview" ;
            }else if(element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getPageFlowTransition())){
                return "tab.forms" ;
            }
        } else if(element instanceof Document){
            return "tab.document";
        } else if(element instanceof SearchIndex){
            return "tab.index";
        }
        return "tab.general";
    }

    private TabbedPropertySheetPage getTabbedPropertySheetPage(final EObject element,final IGraphicalEditPart ep) throws PartInitException {
        IViewPart viewPart = null ;
        final boolean inApplicationView = displayApplicationTab(element);
        for(final IViewReference vr : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences() ){
            final IWorkbenchPart part = vr.getPart(true);
            final TabbedPropertySheetPage propertyPage =  (TabbedPropertySheetPage) part.getAdapter(TabbedPropertySheetPage.class);
            if(propertyPage != null){
                propertyPage.selectionChanged(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor(), new StructuredSelection(ep)) ;
            }
            if(inApplicationView){
                if(vr.getId().equals("org.bonitasoft.studio.views.properties.application")){
                    viewPart = (IViewPart) part;
                }
            }else if(!inApplicationView){
                if(vr.getId().equals("org.bonitasoft.studio.views.properties.process.general")){
                    viewPart = (IViewPart) part;
                }
            }
        }
        if(viewPart != null){
            viewPart.getViewSite().getWorkbenchWindow().getActivePage().showView(viewPart.getSite().getId()) ;
            return (TabbedPropertySheetPage) viewPart.getAdapter(TabbedPropertySheetPage.class);
        }
        return null;
    }

    private boolean displayApplicationTab(final EObject element) {
        return element.eClass().getEPackage().getName().equals(FormPackage.eINSTANCE.getName())
                || element instanceof Data && isTransientData(element) || element instanceof PageFlowTransition || element instanceof AssociatedFile || element instanceof ResourceFolder || element instanceof ResourceFile;
    }

    private boolean isTransientData(final EObject element) {
        return element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getRecapFlow_RecapTransientData()) ||
                element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getViewPageFlow_ViewTransientData()) ||
                element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getPageFlow_TransientData()) ;
    }
}
