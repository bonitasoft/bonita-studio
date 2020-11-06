/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.section;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.ui.section.editingsupport.ActorDescripitonEditingSupport;
import org.bonitasoft.studio.actors.ui.section.editingsupport.ActorNameEditingSupport;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.CellEditorValidationStatusListener;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class ProcessActorsPropertySection extends AbstractBonitaDescriptionSection
        implements ISelectionChangedListener, IDoubleClickListener {

    private TableViewer actorsViewer;
    private Button removeButton;
    private ActorNameEditingSupport nameEditingSupport;
    private ActorDescripitonEditingSupport descripitonEditingSupport;
    private Button initiatorButton;
    private EMFDataBindingContext context;
    private Listener setAsInitiatorListener;
    private Listener removeInitiatorListener;

    @Override
    protected void createContent(final Composite parent) {
        final TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();
        final Composite mainComposite = widgetFactory.createComposite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).extendedMargins(0, 20, 5, 15)
                .spacing(5, 2).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 180).create());

        widgetFactory.createCLabel(mainComposite, "", SWT.NONE);
        final CLabel statusControl = widgetFactory.createCLabel(mainComposite, "", SWT.NONE);
        statusControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Composite buttonsComposite = widgetFactory.createComposite(mainComposite, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        buttonsComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(false).margins(0, 0).spacing(0, 3).create());

        createAddButton(buttonsComposite, widgetFactory);
        initiatorButton = createInitiatorButton(buttonsComposite, widgetFactory);
        removeButton = createRemoveButton(buttonsComposite, widgetFactory);

        createTable(widgetFactory, mainComposite, statusControl);

        widgetFactory.createLabel(mainComposite, "");
        widgetFactory.createLabel(mainComposite, Messages.initiatorExplanation);

        updateButtons();

    }

    protected void createTable(final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite mainComposite, final CLabel statusControl) {
        actorsViewer = new TableViewer(mainComposite,
                GTKStyleHandler.removeBorderFlag(SWT.FULL_SELECTION | SWT.BORDER | SWT.MULTI | SWT.V_SCROLL));
        widgetFactory.adapt(actorsViewer.getTable(), false, false);
        actorsViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        actorsViewer.setContentProvider(new ArrayContentProvider());
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(30));
        tableLayout.addColumnData(new ColumnWeightData(67));
        actorsViewer.getTable().setLayout(tableLayout);

        actorsViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                updateButtons();
            }
        });

        final TableViewerColumn initiatorIconViewer = new TableViewerColumn(actorsViewer, SWT.NONE);
        initiatorIconViewer.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return null;
            }

            @Override
            public String getToolTipText(final Object element) {
                if (((Actor) element).isInitiator()) {
                    return Messages.processInitiator;
                }
                return null;
            }

            @Override
            public int getToolTipTimeDisplayed(final Object object) {
                return 4000;
            }

            @Override
            public int getToolTipDisplayDelayTime(final Object object) {
                return 300;
            }

            @Override
            public Point getToolTipShift(final Object object) {
                return new Point(5, 5);
            }

            @Override
            public Image getImage(final Object element) {
                if (((Actor) element).isInitiator()) {
                    return Pics.getImage("initiator.png", ActorsPlugin.getDefault());
                }
                return null;
            }
        });

        final TableViewerColumn columnNameViewer = new TableViewerColumn(actorsViewer, SWT.NONE);
        columnNameViewer.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((Actor) element).getName();
            }
        });

        final CellEditorValidationStatusListener listener = new CellEditorValidationStatusListener(statusControl);
        nameEditingSupport = new ActorNameEditingSupport(columnNameViewer.getViewer(), getEditingDomain(), listener);
        columnNameViewer.setEditingSupport(nameEditingSupport);
        final TableColumn column = columnNameViewer.getColumn();
        column.setText(Messages.name);

        final TableViewerColumn columnDescriptionViewer = new TableViewerColumn(actorsViewer, SWT.NONE);
        columnDescriptionViewer.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((Actor) element).getDocumentation();
            }
        });

        descripitonEditingSupport = new ActorDescripitonEditingSupport(columnDescriptionViewer.getViewer(),
                getEditingDomain());
        columnDescriptionViewer.setEditingSupport(descripitonEditingSupport);
        final TableColumn column3 = columnDescriptionViewer.getColumn();
        column3.setText(Messages.description);

        actorsViewer.getTable().setHeaderVisible(true);
        actorsViewer.getTable().setLinesVisible(true);
        ColumnViewerToolTipSupport.enableFor(actorsViewer);

        final TableColumnSorter sorter = new TableColumnSorter(actorsViewer);
        sorter.setColumn(column);
    }

    private Button createRemoveButton(final Composite buttonsComposite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Button removeButton = widgetFactory.createButton(buttonsComposite, Messages.remove, SWT.PUSH);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                removeSelected();
            }
        });
        return removeButton;
    }

    protected void removeSelected() {
        if (!actorsViewer.getSelection().isEmpty()) {
            if (MessageDialog.openConfirm(Display.getDefault().getActiveShell(), Messages.deleteActorsTitle,
                    Messages.deleteActorsTitleMessage)) {
                final List<?> actors = ((IStructuredSelection) actorsViewer.getSelection()).toList();
                getEditingDomain().getCommandStack().execute(DeleteCommand.create(getEditingDomain(), actors));
                refresh();
            }
        }
    }

    protected Button createAddButton(final Composite buttonsComposite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Button addButton = widgetFactory.createButton(buttonsComposite, Messages.add, SWT.PUSH);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addSelected();
            }
        });
        return addButton;
    }

    protected Button createInitiatorButton(final Composite buttonsComposite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Button addButton = widgetFactory.createButton(buttonsComposite, Messages.setAsProcessInitiator, SWT.PUSH);
        addButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.actors.ui.section.initiatorButton");
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        setAsInitiatorListener = createSetAsInitatiorListener();
        removeInitiatorListener = createUnsetAsInitiatorListener();
        return addButton;
    }

    private Listener createUnsetAsInitiatorListener() {
        return e -> {
            final Actor selectedActor = (Actor) ((IStructuredSelection) actorsViewer.getSelection()).getFirstElement();
            final CompoundCommand cc = new CompoundCommand();
            cc.append(SetCommand.create(getEditingDomain(), selectedActor, ProcessPackage.Literals.ACTOR__INITIATOR, false));
            getEditingDomain().getCommandStack().execute(cc);
            Display.getDefault().asyncExec(() -> {
                if (actorsViewer != null && !actorsViewer.getControl().isDisposed()) {
                    actorsViewer.refresh();
                    updateButtons();
                }
            });
        };
    }

    private Listener createSetAsInitatiorListener() {
        return e -> {
            final Actor selectedActor = (Actor) ((IStructuredSelection) actorsViewer.getSelection()).getFirstElement();
            final CompoundCommand cc = new CompoundCommand();
            final AbstractProcess process = (AbstractProcess) getEObject();
            for (final Actor a : process.getActors()) {
                cc.append(SetCommand.create(getEditingDomain(), a, ProcessPackage.Literals.ACTOR__INITIATOR, false));
            }
            cc.append(SetCommand.create(getEditingDomain(), selectedActor, ProcessPackage.Literals.ACTOR__INITIATOR, true));
            getEditingDomain().getCommandStack().execute(cc);
            Display.getDefault().asyncExec(() -> {
                if (actorsViewer != null && !actorsViewer.getControl().isDisposed()) {
                    actorsViewer.refresh();
                    updateButtons();
                }
            });

        };
    }

    protected void addSelected() {
        final AbstractProcess process = (AbstractProcess) getEObject();
        final Actor actor = ProcessFactory.eINSTANCE.createActor();
        actor.setName(generateActorName(process));
        getEditingDomain().getCommandStack().execute(
                AddCommand.create(getEditingDomain(), process, ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS, actor));
        refresh();
        actorsViewer.editElement(actor, 0);
    }

    private String generateActorName(final AbstractProcess process) {
        final Set<String> actorsName = new HashSet<String>();
        for (final Actor a : process.getActors()) {
            actorsName.add(a.getName());
        }

        return NamingUtils.generateNewName(actorsName, Messages.defaultActorName, 1);
    }

    private void bindActorList() {
        if (context != null) {
            context.dispose();
        }
        context = new EMFDataBindingContext();
        context.bindValue(ViewersObservables.observeInput(actorsViewer), EMFEditObservables.observeValue(getEditingDomain(),
                getEObject(), ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS));
    }

    @Override
    public void refresh() {
        super.refresh();
        if (getEObject() != null) {
            updateButtons();
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent arg0) {
        updateButtons();
    }

    private void updateButtons() {
        if (removeButton != null && !removeButton.isDisposed()) {
            removeButton.setEnabled(!actorsViewer.getSelection().isEmpty());
        }

        if (initiatorButton != null && !initiatorButton.isDisposed()) {
            if (!actorsViewer.getSelection().isEmpty()) {
                initiatorButton.setEnabled(true);
                final Actor selectedActor = (Actor) ((IStructuredSelection) actorsViewer.getSelection()).getFirstElement();
                if (selectedActor.isInitiator()) {
                    removeInitiatorMode();
                } else {
                    setInitiatorMode();
                }
            } else {
                setInitiatorMode();
                initiatorButton.setEnabled(false);
            }
        }
    }

    private void removeInitiatorMode() {
        initiatorButton.setText(Messages.removeProcessInitiator);
        initiatorButton.addListener(SWT.Selection, removeInitiatorListener);
        initiatorButton.removeListener(SWT.Selection, setAsInitiatorListener);
        initiatorButton.getParent().layout();
    }

    private void setInitiatorMode() {
        initiatorButton.setText(Messages.setAsProcessInitiator);
        initiatorButton.removeListener(SWT.Selection, removeInitiatorListener);
        initiatorButton.addListener(SWT.Selection, setAsInitiatorListener);
        initiatorButton.getParent().layout();
    }

    @Override
    protected void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        super.setEditingDomain(editingDomain);
        if (nameEditingSupport != null) {
            nameEditingSupport.setTransactionalEditingDomain(editingDomain);
            descripitonEditingSupport.setTransactionalEditingDomain(editingDomain);
        }
    }

    @Override
    public void doubleClick(final DoubleClickEvent arg0) {

    }

    protected EStructuralFeature getActorFeature() {
        return ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS;
    }

    @Override
    public String getSectionDescription() {
        return Messages.addRemoveActors;
    }

    @Override
    public void setEObject(final EObject object) {
        super.setEObject(object);
        bindActorList();
    }
}
