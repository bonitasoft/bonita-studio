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
package org.bonitasoft.studio.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.model.process.diagram.part.ValidateAction;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class BatchValidationOperation implements IRunnableWithProgress {

	private Set<Diagram> toValidate;
	private List<Shell> toDispose = new ArrayList<Shell>();
	private List<IFile> fileProcessed = new ArrayList<IFile>(); //Avoid duplicate

	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
		Assert.isNotNull(toValidate);
		monitor.beginTask(Messages.validating, IProgressMonitor.UNKNOWN);
	//	RepositoryManager.getInstance().getCurrentRepository().refresh(monitor);
		Display.getDefault().syncExec(new Runnable() {

			public void run() {	
				if(!toValidate.isEmpty()){
					Iterator<Diagram> it = toValidate.iterator();
					while( it.hasNext() ){
						Diagram d = it.next();
						DiagramEditPart de = getDiagramEditPart(d);
						if(de != null){
							EObject resolvedSemanticElement = de.resolveSemanticElement();
							if(resolvedSemanticElement instanceof Form){
								IFile target = d.eResource() != null ? WorkspaceSynchronizer.getFile(d.eResource()) : null;
								if (target != null) {
									ProcessMarkerNavigationProvider.deleteMarkers(target);
									break;
								}
							}
						}
					}
				}
				for(Diagram d : toValidate){
					DiagramEditPart de = getDiagramEditPart(d);
					if( de != null ){
						EObject resolvedSemanticElement = de.resolveSemanticElement();
						if(resolvedSemanticElement instanceof MainProcess ){
							ValidateAction.runValidation(de,d);
						}else if(resolvedSemanticElement instanceof Form){
							org.bonitasoft.studio.model.process.diagram.form.part.ValidateAction.runValidation(de, d);
						}
					}
				}
				dispose();
			}
		});


		monitor.done();
	}


	private void dispose() {
		for(Shell s : toDispose){
			s.dispose();
		}
	}


	private DiagramEditPart getDiagramEditPart(Diagram d) {
		if(PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
			for(IEditorPart ep : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditors()){
				if(ep instanceof DiagramEditor){
					if(((DiagramEditor) ep).getDiagram().equals(d)){
						return ((DiagramEditor) ep).getDiagramEditPart();
					}
				}
			}
		}
		if(d != null && d.eResource() != null){
			final ResourceSet rSet = d.eResource().getResourceSet() ;
			if(GMFEditingDomainFactory.getInstance().getEditingDomain(rSet) == null){
				GMFEditingDomainFactory.getInstance().createEditingDomain(rSet) ;
			}
			EObject element = d.getElement();
			DiagramEditPart	diagramEp = null;
			if(element != null){
				final Shell shell = new Shell() ;
				diagramEp = OffscreenEditPartFactory.getInstance().createDiagramEditPart(d,shell) ;
				toDispose.add(shell);
			}
			return diagramEp;
		}
		return null;
	}


	public IStatus getResult() {
		final MultiStatus result = new MultiStatus(Activator.PLUGIN_ID, IStatus.OK, "", null);
		fileProcessed.clear();
		for(Diagram d : toValidate){
			EObject element = d.getElement();
			if(element != null){
				IFile target = d.eResource() != null ? WorkspaceSynchronizer.getFile(d.eResource()) : null;
				if(target != null && !fileProcessed.contains(target)){
					try {
						buildMultiStatus(target,result) ;
					} catch (CoreException e) {
						BonitaStudioLog.error(e);
					}
				}
			}
		}
		return result;
	}


	private void buildMultiStatus(IFile target,MultiStatus result) throws CoreException {
		String fileName = target.getName();
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		for(IMarker m : target.findMarkers(ProcessMarkerNavigationProvider.MARKER_TYPE, true, IResource.DEPTH_ZERO)){
			int severity = (Integer) m.getAttribute(IMarker.SEVERITY);
			String location =  (String) m.getAttribute(IMarker.LOCATION);
			if(severity == IMarker.SEVERITY_WARNING || severity == IMarker.SEVERITY_INFO || severity == IMarker.SEVERITY_ERROR){
				String message = (String) m.getAttribute(IMarker.MESSAGE);
				String fullMessage = fileName + ":" +location +" : " + message;
				if(!statusExists(result,fullMessage)){
					result.add(new Status(toStatusSeverity(severity), Activator.PLUGIN_ID, fullMessage));
				}
			}
		}
		for(IMarker m : target.findMarkers(org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider.MARKER_TYPE, true, IResource.DEPTH_ZERO)){
			int severity = (Integer) m.getAttribute(IMarker.SEVERITY);
			if(severity == IMarker.SEVERITY_WARNING || severity == IMarker.SEVERITY_INFO || severity == IMarker.SEVERITY_ERROR){
				String message = (String) m.getAttribute(IMarker.MESSAGE);
				String location =  (String) m.getAttribute(IMarker.LOCATION);
				String fullMessage = fileName + ":" +location +" : " + message;
				if(!statusExists(result, fullMessage)){
					result.add(new Status(toStatusSeverity(severity), Activator.PLUGIN_ID, fullMessage));
				}
			}
		}
		fileProcessed.add(target);
	}

	private int toStatusSeverity(int markerSeverity) {
		switch (markerSeverity) {
		case IMarker.SEVERITY_INFO: return IStatus.INFO;
		case IMarker.SEVERITY_WARNING:return IStatus.WARNING;
		case IMarker.SEVERITY_ERROR:return IStatus.ERROR;
		default: return IStatus.INFO;
		}
	}


	private boolean statusExists(MultiStatus multi, String message){
		for(IStatus s : multi.getChildren()){
			if(s.getMessage().equals(message)){
				return true;
			}
		}
		return false;
	}


	public void setDiagramToValidate(Set<Diagram> toValidate) {
		this.toValidate = toValidate;
	}

}
