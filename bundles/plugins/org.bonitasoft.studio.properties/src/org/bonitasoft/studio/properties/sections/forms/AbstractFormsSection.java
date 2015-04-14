/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.jface.EMFListFeatureTreeContentProvider;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.PropertySectionUtil;
import org.bonitasoft.studio.properties.sections.forms.commands.RemoveFormCommand;
import org.bonitasoft.studio.properties.sections.forms.wizard.SelectFormWizard;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Aurelien Pupier
 *         Provides the filteredTree and the buttons aside it
 */
public abstract class AbstractFormsSection extends AbstractBonitaDescriptionSection {

    protected FilteredTree tree;
    private Element pageFlow;
    protected EMFDataBindingContext context;
    private Button editButton;
    private Button upButton;
    private Button downButton;
    private Button removeButton;
    protected MagicComposite contentComposite;

    protected Composite pageFlowComposite;
    private Composite mainComposite;
    protected Composite radioComposite;
    private IObservableList observeFormList;
    private IChangeListener formListener;
    private IObservableList observeFormListName;

    @Override
    protected void createContent(final Composite parent) {
        mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(new GridLayout(1, false));
        radioComposite = createRadioButtons(mainComposite);

        contentComposite = new MagicComposite(mainComposite, SWT.NONE);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        contentComposite.setBackground(mainComposite.getBackground());

        pageFlowComposite = createPageFlowComposite(contentComposite);
    }

    /**
     *
     */
    protected Composite createPageFlowComposite(final Composite parent) {
        final Composite mainComposite = getWidgetFactory().createComposite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Composite pageFlowComposite = getWidgetFactory().createComposite(mainComposite, SWT.NONE);
        pageFlowComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        pageFlowComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());

        createButtons(pageFlowComposite, tree);
        tree = createFilteredTree(pageFlowComposite);
        return mainComposite;
    }

    /**
     * To be overriden when necessary
     *
     * @param mainComposite
     * @override
     */
    protected Composite createRadioButtons(final Composite mainComposite) {
        return null;
    }

    protected abstract EReference getPageFlowFormFeature();

    protected abstract EReference getPageFlowTransitionsFeature();

    protected abstract EAttribute getPageFlowTypeFeature();

    protected abstract EReference getPageFlowRedirectionURLFeature();

    private void createTableBinding() {
        if (getEObject() != null) {
            final EMFListFeatureTreeContentProvider contentProvider = new EMFListFeatureTreeContentProvider(getPageFlowFormFeature());
            tree.getViewer().setContentProvider(contentProvider);

            if (observeFormList != null && formListener != null && observeFormListName != null) {
                observeFormList.removeChangeListener(formListener);
                observeFormListName.removeChangeListener(formListener);
                observeFormList.dispose();
                observeFormListName.dispose();
            }

            final TreeViewer viewer = tree.getViewer();
            final IEMFEditListProperty list = EMFEditProperties.list(getEditingDomain(), getPageFlowFormFeature());
            observeFormList = list.observe(getEObject());
            observeFormListName = list.values(ProcessPackage.Literals.ELEMENT__NAME).observe(getEObject());

            formListener = new IChangeListener() {

                @Override
                public void handleChange(final ChangeEvent event) {
                    refreshFormTree();
                }
            };

            observeFormList.addChangeListener(formListener);
            observeFormListName.addChangeListener(formListener);

            if (getEObject() instanceof Lane) {
                viewer.setInput(getEObject().eContainer());
            } else {
                viewer.setInput(getEObject());
            }
        }
    }

    @Override
    protected EObject getEObject() {
        if (super.getEObject() instanceof Lane) {
            return super.getEObject().eContainer();
        } else {
            return super.getEObject();
        }
    }

    protected void refreshFormTree() {
        if (getEObject() != null && tree != null && tree.getViewer() != null && tree.getViewer().getContentProvider() != null) {
            tree.getViewer().setInput(getEObject());
        }
    }

    protected FilteredTree createFilteredTree(final Composite mainComposite) {
        tree = new FilteredTree(mainComposite, SWT.BORDER | SWT.MULTI, new PatternFilter(), true);
        tree.getViewer().getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.APPLICATION_SECTION_FORMS_SELECTION_TREE);
        getWidgetFactory().adapt(tree, false, false);
        tree.setLayoutData(createPageFlowTreeGridData());

        tree.getViewer().setLabelProvider(new FormLabelProvider());
        tree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                updateButtons();
            }
        });
        tree.getViewer().addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent event) {
                editFormInTree();
            }
        });

        // would be better to transfer the form
        final Transfer[] types = new Transfer[] { TextTransfer.getInstance() };

        final DragSource source = new DragSource(tree.getViewer().getTree(), SWT.Move);
        source.setTransfer(types);
        final Form[] dragSourceForm = new Form[1];
        source.addDragListener(new DragSourceListener() {

            @Override
            public void dragStart(final DragSourceEvent event) {

                final Form selection = (Form) ((IStructuredSelection) tree.getViewer().getSelection()).getFirstElement();
                if (selection != null) {
                    event.doit = true;
                    dragSourceForm[0] = selection;
                } else {
                    event.doit = false;
                }
            };

            @Override
            public void dragSetData(final DragSourceEvent event) {
                // put the index here
                final String index = "" + ((List<?>) pageFlow.eGet(getPageFlowFormFeature()) //$NON-NLS-1$
                        ).indexOf(dragSourceForm[0]);
                event.data = index;
            }

            @Override
            public void dragFinished(final DragSourceEvent event) {
                if (event.detail == DND.DROP_MOVE) {
                    // nothing to do

                }
            }
        });

        final DropTarget target = new DropTarget(tree.getViewer().getTree(), DND.DROP_MOVE);
        target.setTransfer(types);
        target.addDropListener(new DropTargetAdapter() {

            @Override
            public void dragOver(final DropTargetEvent event) {
                event.feedback = DND.FEEDBACK_EXPAND | DND.FEEDBACK_SCROLL;
                if (event.item != null) {
                    final TreeItem item = (TreeItem) event.item;
                    final Point pt = Display.getCurrent().map(null, tree.getViewer().getTree(), event.x, event.y);
                    final Rectangle bounds = item.getBounds();
                    if (pt.y < bounds.y + bounds.height / 2) {
                        event.feedback |= DND.FEEDBACK_INSERT_BEFORE;
                    } else {
                        event.feedback |= DND.FEEDBACK_INSERT_AFTER;
                    }
                }
            }

            @Override
            public void drop(final DropTargetEvent event) {
                if (event.data == null) {
                    event.detail = DND.DROP_NONE;
                    return;
                }
                final String movedForm = (String) event.data;
                if (event.item == null) {

                } else {
                    final TreeItem item = (TreeItem) event.item;
                    final Point pt = Display.getCurrent().map(null, tree.getViewer().getTree(), event.x, event.y);
                    final Rectangle bounds = item.getBounds();
                    final TreeItem parent = item.getParentItem();
                    if (parent != null) {
                        final TreeItem[] items = parent.getItems();
                        int index = 0;
                        for (int i = 0; i < items.length; i++) {
                            if (items[i] == item) {
                                index = i;
                                break;
                            }
                        }
                        if (pt.y < bounds.y + bounds.height / 2) {
                            moveForm(movedForm, getPageFlowFormFeature(), index);
                        } else {
                            moveForm(movedForm, getPageFlowFormFeature(), index + 1);
                        }

                    } else {
                        final TreeItem[] items = tree.getViewer().getTree().getItems();
                        int index = 0;
                        for (int i = 0; i < items.length; i++) {
                            if (items[i] == item) {
                                index = i;
                                break;
                            }
                        }
                        if (pt.y < bounds.y + bounds.height / 2) {
                            moveForm(movedForm, getPageFlowFormFeature(), index);
                        } else {
                            moveForm(movedForm, getPageFlowFormFeature(), index + 1);
                        }
                    }

                }
            }

            private void moveForm(final String from, final EStructuralFeature feature, final int to) {
                final Integer fromInt = Integer.valueOf(from);
                getEditingDomain().getCommandStack().execute(
                        new MoveCommand(getEditingDomain(), (EList<?>) pageFlow.eGet(feature), ((List<?>) pageFlow.eGet(feature)).get(fromInt),
                                to > fromInt ? to - 1 : to));
                tree.getViewer().refresh();
            }
        });
        return tree;
    }

    /**
     * @return
     */
    public static GridData createPageFlowTreeGridData() {
        return GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, PropertySectionUtil.LIST_HEIGHT).create();
    }

    /**
     * @param mainComposite
     * @param consultationFilteredTree2
     */
    private void createButtons(final Composite mainComposite, final FilteredTree tree) {
        final Composite buttonsComposite = getWidgetFactory().createPlainComposite(mainComposite, SWT.NONE);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        createAddFormButton(buttonsComposite);
        editButton = createEditFormButton(buttonsComposite);
        upButton = createUpFormButton(buttonsComposite);
        downButton = createDownFormButton(buttonsComposite);
        removeButton = createRemoveFormButton(buttonsComposite);
    }

    protected Button createAddFormButton(final Composite buttonsComposite) {
        final Button addButton = getWidgetFactory().createButton(buttonsComposite, Messages.addForm, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                new WizardDialog(AbstractFormsSection.this.getPart().getSite().getShell(), new SelectFormWizard(getPageFlow(), getPageFlowFormFeature(),
                        getEditingDomain(), RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class))).open();
                tree.getViewer().refresh();
            }
        });
        return addButton;
    }

    private Button createEditFormButton(final Composite buttonsComposite) {
        final Button editButton = getWidgetFactory().createButton(buttonsComposite, Messages.editForm, SWT.FLAT);
        editButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        editButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                editFormInTree();
            }
        });
        editButton.setEnabled(false);
        return editButton;
    }

    private Button createDownFormButton(final Composite buttonsComposite) {
        downButton = getWidgetFactory().createButton(buttonsComposite, Messages.formDown, SWT.FLAT);
        downButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        downButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                if (tree != null && ((ITreeSelection) tree.getViewer().getSelection()).size() > 0) {
                    final Form form = (Form) ((ITreeSelection) tree.getViewer().getSelection()).getFirstElement();
                    final EList<Form> list = (EList<Form>) pageFlow.eGet(getPageFlowFormFeature());
                    final int indexOf = list.indexOf(form);
                    if (indexOf < list.size() - 1) {
                        list.move(indexOf + 1, form);
                    }
                    tree.getViewer().refresh();
                }
            }
        });
        downButton.setEnabled(false);
        return downButton;

    }

    private Button createUpFormButton(final Composite buttonsComposite) {
        upButton = getWidgetFactory().createButton(buttonsComposite, Messages.formUp, SWT.FLAT);
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        upButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final Form form = (Form) ((ITreeSelection) tree.getViewer().getSelection()).getFirstElement();
                final EList<Form> list = (EList<Form>) pageFlow.eGet(getPageFlowFormFeature());
                final int indexOf = list.indexOf(form);
                if (indexOf > 0) {
                    list.move(indexOf - 1, form);
                }
                tree.getViewer().refresh();
            }
        });
        upButton.setEnabled(false);
        return upButton;
    }

    private Button createRemoveFormButton(final Composite buttonsComposite) {
        final Button removeButton = getWidgetFactory().createButton(buttonsComposite, Messages.removeForm, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (tree != null) {
                    final TreeViewer viewer = tree.getViewer();
                    final ISelection iSelection = viewer.getSelection();
                    final ArrayList<Form> toRemove = new ArrayList<Form>();
                    for (final Object selectedElement : ((IStructuredSelection) iSelection).toList()) {
                        if (selectedElement instanceof Form) {
                            toRemove.add((Form) selectedElement);
                        }
                    }
                    if (toRemove.size() > 0) {
                        try {
                            OperationHistoryFactory.getOperationHistory().execute(new RemoveFormCommand(getEditingDomain(), pageFlow, toRemove, viewer),
                                    new NullProgressMonitor(), null);
                        } catch (final ExecutionException e1) {
                            BonitaStudioLog.error(e1);
                        }
                    }
                    // Listeners should be best:
                    viewer.refresh();
                }
            }

        });
        removeButton.setEnabled(false);
        return removeButton;
    }

    public Element getPageFlow() {
        return pageFlow;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        final ISelection old = getSelection();
        super.setInput(part, selection);
        if (old == getSelection()) {
            return;
        }

        if (context != null) {
            context.dispose();
        }
        Element tempPageFlow = null;
        if (getEObject() instanceof Lane) {
            tempPageFlow = (Element) getEObject().eContainer();
        }
        if (getEObject() instanceof PageFlow || getEObject() instanceof ViewPageFlow) {
            tempPageFlow = (Element) getEObject();
        }
        pageFlow = tempPageFlow;

        context = new EMFDataBindingContext();
        createTableBinding();
    }

    /**
     * @param tree
     */
    private void editFormInTree() {
        if (tree != null && !tree.isDisposed()) {
            final ITreeSelection iTreeSelection = (ITreeSelection) tree.getViewer().getSelection();
            if (tree != null && iTreeSelection.size() > 0) {
                final Object selection = iTreeSelection.getFirstElement();
                if (selection instanceof Form) {
                    FormsUtils.openDiagram((Form) selection, getEditingDomain());
                }
            }
        }
    }

    private void updateButtons() {
        final TreeViewer viewer = tree.getViewer();
        final ITreeSelection selection = (ITreeSelection) viewer.getSelection();
        removeButton.setEnabled(selection.size() > 0);
        final boolean isOnlyOneElementSelected = selection.size() == 1;
        final boolean hasMoreThanOneItemInTheList = viewer.getTree().getItems().length > 1;
        upButton.setEnabled(isOnlyOneElementSelected && hasMoreThanOneItemInTheList);
        downButton.setEnabled(isOnlyOneElementSelected && hasMoreThanOneItemInTheList);
        editButton.setEnabled(isOnlyOneElementSelected);
    }

    /**
     *
     */
    protected void hideAllContentComposite() {
        if (pageFlowComposite != null) {
            pageFlowComposite.setVisible(false);
            pageFlowComposite.setData(MagicComposite.HIDDEN, true);
        }
    }

    /**
     *
     */
    protected void showOrHideComposite(final Composite target, final boolean visible) {
        target.setVisible(visible);
        target.setData(MagicComposite.HIDDEN, !visible);
        refresh();
    }

    @Override
    public void refresh() {
        mainComposite.layout(true, true);
        super.refresh();
    }

    @Override
    public void dispose() {
        super.dispose();

        if (context != null) {
            context.dispose();
        }

    }

}
