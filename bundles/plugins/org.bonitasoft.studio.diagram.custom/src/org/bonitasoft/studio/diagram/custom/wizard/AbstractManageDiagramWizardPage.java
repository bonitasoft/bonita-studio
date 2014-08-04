/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.wizard;

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;


/**
 * @author aurelie zara
 */
public abstract class AbstractManageDiagramWizardPage extends WizardPage implements IWizardPage {

    private FilteredTree diagramTree;
    private Composite mainComposite;

    private final ProcessConfigurationRepositoryStore processConfStore;
    private final ApplicationResourceRepositoryStore applicationResourceStore;

    /**
     * @param pageName
     */
    protected AbstractManageDiagramWizardPage(final String pageName) {
        super(pageName);
        processConfStore = RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        applicationResourceStore = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
    }


    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        createDiagramTreeViewer(parent);
    }


    public void createDiagramTreeViewer(final Composite parent) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(new GridLayout(2, true));
        //Composite repositoryComposite = new Composite(composite, SWT.NONE);
        diagramTree = new FilteredTree(mainComposite, SWT.MULTI | SWT.BORDER, new PatternFilter(), false);
        diagramTree.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).hint(SWT.DEFAULT, 250).create());
        diagramTree.getViewer().setContentProvider(new DiagramTreeContentProvider());

        final LabelProvider fileStoreLabelProvider = new FileStoreLabelProvider();
        diagramTree.getViewer().setLabelProvider(new DiagramLabelProvider(fileStoreLabelProvider));
        diagramTree.getViewer().addSelectionChangedListener(diagramTreeSelectionChangeListener());
        diagramTree.getViewer().addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent arg0) {
                if (getWizard().canFinish()) {
                    if (getWizard().performFinish()) {
                        ((WizardDialog) getContainer()).close();
                    }
                }

            }
        });
    }

    public abstract ISelectionChangedListener diagramTreeSelectionChangeListener();

    /**
     * @return the diagramTree
     */
    public FilteredTree getDiagramTree() {
        return diagramTree;
    }

    /**
     * @param diagramTree the diagramTree to set
     */
    public void setDiagramTree(final FilteredTree diagramTree) {
        this.diagramTree = diagramTree;
    }

    /**
     * @return the mainComposite
     */
    public Composite getMainComposite() {
        return mainComposite;
    }

    /**
     * @param mainComposite the mainComposite to set
     */
    public void setMainComposite(final Composite mainComposite) {
        this.mainComposite = mainComposite;
    }


    public List<DiagramFileStore> getDiagrams() {
        if (!getDiagramTree().getViewer().getSelection().isEmpty()) {
            return ((ITreeSelection) getDiagramTree().getViewer().getSelection()).toList();
        }
        return null;
    }

    /**
     * @return the processConfStore
     */
    public ProcessConfigurationRepositoryStore getProcessConfStore() {
        return processConfStore;
    }

    /**
     * @return the applicationResourceStore
     */
    public ApplicationResourceRepositoryStore getApplicationResourceStore() {
        return applicationResourceStore;
    }

    protected void setWorkspaceThingsEnabled(final boolean enabled) {
        getDiagramTree().getFilterControl().setEnabled(enabled);
        getDiagramTree().getViewer().getTree().setEnabled(enabled);
    }

}
