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
package org.bonitasoft.studio.common.editingdomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditorInput;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 *
 * Utility that allow to unload resource set if it's not in use
 *
 * @author Baptiste Mesta
 *
 */
public class EditingDomainResourcesDisposer {

    private static Resource getDiagramResource(final ResourceSet resourceSet, final IEditorInput editorInput) {
        Resource diagramResource = null;
        if (editorInput instanceof URIEditorInput) {
            final URI resourceURI = ((URIEditorInput) editorInput).getURI().trimFragment();
            diagramResource = resourceSet.getResource(resourceURI, false);
        } else if (editorInput instanceof FileEditorInput) {
            final URI resourceURI = URI.createPlatformResourceURI(((FileEditorInput) editorInput).getFile().getFullPath().toString(), true);
            diagramResource = resourceSet.getResource(resourceURI, false);
        } else if (editorInput instanceof IDiagramEditorInput) {
            final Diagram diagram = ((IDiagramEditorInput) editorInput).getDiagram();
            diagramResource = diagram.eResource();
        }
        return diagramResource;
    }

    /**
     * called to dispose the resource set when closing the editor in parameter.
     * The resource set will be unloaded only if it's not in use
     * @param resourceSet
     * @param editorInput
     */
    public static void disposeEditorInput(final ResourceSet resourceSet, final IEditorInput editorInput) {
        final EList<Resource> allResources = resourceSet.getResources();
        final List<Resource> resourcesToDispose = new ArrayList<Resource>(allResources);
        IEditorReference[] editorReferences;
        if(PlatformUI.isWorkbenchRunning()){
            final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            if(activePage != null){
                editorReferences = activePage.getEditorReferences();
            } else {
                return;
            }
        }else{
            return;
        }
        for (final IEditorReference editorRef : editorReferences) {
            try {
                final IEditorInput currentEditorInput = editorRef.getEditorInput();
                if (currentEditorInput != editorInput) {
                    final IEditorPart openEditor = editorRef.getEditor(false);
                    if (openEditor instanceof DiagramEditor) {
                        final DiagramEditor openDiagramEditor = (DiagramEditor) openEditor;
                        final ResourceSet diagramResourceSet = openDiagramEditor.getEditingDomain().getResourceSet();
                        if (diagramResourceSet == resourceSet) {
                            final Resource diagramResource = getDiagramResource(diagramResourceSet, currentEditorInput);
                            if(diagramResource != null){
                                resourcesToDispose.remove(diagramResource);
                                final Collection<?> imports = EMFCoreUtil.getImports(diagramResource);
                                resourcesToDispose.removeAll(imports);
                            }
                        }
                    }
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        for (final Resource resource : resourcesToDispose) {
            try {
                resource.unload();
                allResources.remove(resource);
            } catch (final Exception t) {
                BonitaStudioLog.error(t);
            }
        }
    }


    /**
     * called to dispose the resource set when closing the editor in parameter.
     * The resource set will be unloaded only if it's not in use
     * @param resourceSet
     * @param editorInput
     */
    public static boolean isResourceUsedElseWhere(final ResourceSet resourceSet, final IEditorInput editorInput) {
        IEditorReference[] editorReferences;
        if(PlatformUI.isWorkbenchRunning()){
            editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        }else{
            return false;
        }
        for (final IEditorReference editorRef : editorReferences) {
            try {
                final IEditorInput currentEditorInput = editorRef.getEditorInput();
                if (currentEditorInput != editorInput) {
                    final IEditorPart openEditor = editorRef.getEditor(false);
                    if (openEditor instanceof DiagramEditor) {
                        final DiagramEditor openDiagramEditor = (DiagramEditor) openEditor;
                        final ResourceSet diagramResourceSet = openDiagramEditor.getEditingDomain().getResourceSet();
                        if (diagramResourceSet == resourceSet) {
                            return true;
                        }
                    }
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        return false;
    }


}
