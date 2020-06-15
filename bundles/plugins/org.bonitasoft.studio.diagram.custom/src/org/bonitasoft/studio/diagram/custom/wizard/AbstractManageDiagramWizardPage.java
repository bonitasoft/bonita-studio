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

import static org.eclipse.jface.layout.GridLayoutFactory.fillDefaults;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author aurelie zara
 */
public abstract class AbstractManageDiagramWizardPage extends WizardPage implements IWizardPage, SWTBotConstants {

    private FilteredTree diagramTree;

    private DataBindingContext context;
    private List<DiagramFileStore> selectedDiagrams = new ArrayList<>();

    private final DiagramRepositoryStore diagramRepositoryStore;

    /**
     * @param pageName
     */
    protected AbstractManageDiagramWizardPage(final String pageName, final DiagramRepositoryStore diagramRepositoryStore) {
        super(pageName);
        this.diagramRepositoryStore = diagramRepositoryStore;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        context = new DataBindingContext();
        setControl(doCreateControl(parent, context));
        WizardPageSupport.create(this, context);
    }

    protected Composite doCreateControl(final Composite parent, final DataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(fillDefaults().numColumns(2).equalWidth(true).create());

        diagramTree = new FilteredTree(mainComposite, SWT.MULTI | SWT.BORDER, new PatternFilter(), false);
        final TreeViewer treeViewer = diagramTree.getViewer();
        treeViewer.getTree().setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_OPEN_DIAGRAM_TREE_ID);
        diagramTree
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).hint(SWT.DEFAULT, 250).create());

        treeViewer.setContentProvider(
                new ObservableListTreeContentProvider(diagramListObservableFactory(), diagramTreeStructure()));
        treeViewer.setLabelProvider(new DiagramLabelProvider(new FileStoreLabelProvider()));

        final IObservableList selectionObservable = PojoObservables.observeList(this, "selectedDiagrams");
        context.bindList(ViewersObservables.observeMultiSelection(diagramTree.getViewer()),
                selectionObservable);
        context.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                return selectionObservable.isEmpty() ? ValidationStatus.error(Messages.noDiagramSelected)
                        : ValidationStatus.ok();
            }
        });
        treeViewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent arg0) {
                final IWizard wizard = getWizard();
                if (wizard.canFinish() && wizard.performFinish() && wizard.getContainer() instanceof WizardDialog) {
                    ((WizardDialog) wizard.getContainer()).close();
                }
            }
        });
        treeViewer.setInput(diagramRepositoryStore);
        treeViewer.getTree().setFocus();
        defaultSelection(selectionObservable);
        return mainComposite;
    }

    private TreeStructureAdvisor diagramTreeStructure() {
        return new TreeStructureAdvisor() {
        };
    }

    private IObservableFactory diagramListObservableFactory() {
        return new IObservableFactory() {

            @Override
            public IObservable createObservable(final Object target) {
                if (target instanceof DiagramRepositoryStore) {
                    return new WritableList(((DiagramRepositoryStore) target).getChildren(), DiagramFileStore.class);
                }
                return new WritableList();
            }
        };
    }

    protected void defaultSelection(final IObservableList selectionObservable) {
        final DiagramRepositoryStore diagramRepositoryStore = (DiagramRepositoryStore) getViewer().getInput();
        if (!diagramRepositoryStore.isEmpty()) {
            selectionObservable.add(diagramRepositoryStore.getChildren().get(0));
        }
    }

    protected TreeViewer getViewer() {
        return diagramTree.getViewer();
    }

    /**
     * @return the diagramTree
     */
    public FilteredTree getDiagramTree() {
        return diagramTree;
    }

    public List<DiagramFileStore> getSelectedDiagrams() {
        return selectedDiagrams;
    }

    public void setSelectedDiagrams(final List<DiagramFileStore> selectedDiagrams) {
        this.selectedDiagrams = selectedDiagrams;
    }

}
