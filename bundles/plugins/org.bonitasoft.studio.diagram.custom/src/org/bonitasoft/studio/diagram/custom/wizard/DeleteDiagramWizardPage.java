/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.wizard;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author aurelie zara
 */
public class DeleteDiagramWizardPage extends AbstractManageDiagramWizardPage {

    private final DiagramRepositoryStore diagramStore;

    /**
     * @param pageName
     */
    protected DeleteDiagramWizardPage(final String pageName, final DiagramRepositoryStore diagramRepositoryStore) {
        super(pageName, diagramRepositoryStore);
        setTitle(pageName);
        setDescription(Messages.DeleteDiagramWizardPage_desc);
        setImageDescriptor(Pics.getWizban());
        diagramStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.diagram.custom.wizard.AbstractManageDiagramWizardPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        super.createControl(parent);
    }

    @Override
    protected Composite doCreateControl(final Composite parent, final DataBindingContext context) {
        final Composite mainComposite = super.doCreateControl(parent, context);
        final Composite blank = new Composite(mainComposite, SWT.NONE);
        blank.setLayoutData(new GridData(SWT.DEFAULT, 40));
        return mainComposite;
    }

    @Override
    protected void defaultSelection(final IObservableList selectionObservable) {
        final MainProcess currentProc = getDiagramInEditor();
        if (currentProc != null) {
            final DiagramFileStore diagram = diagramStore.getDiagram(currentProc.getName(), currentProc.getVersion());
            if (diagram != null) {
                selectionObservable.add(diagram);
            }
        }
    }

    protected void defaultSelection() {

    }

    protected MainProcess getDiagramInEditor() {
        if (PlatformUI.getWorkbench().getWorkbenchWindows() == null || PlatformUI.getWorkbench().getWorkbenchWindows().length == 0) {
            return null;
        }
        final IEditorPart editor = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor();
        final boolean isADiagram = editor != null && editor instanceof DiagramEditor;
        if (isADiagram) {
            final EObject root = ((DiagramEditor) editor).getDiagramEditPart().resolveSemanticElement();
            final MainProcess mainProc = ModelHelper.getMainProcess(root);
            return mainProc;
        }

        return null;
    }

}
