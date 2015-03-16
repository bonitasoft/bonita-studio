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
package org.bonitasoft.studio.businessobject.ui.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * @author Romain Bioteau
 */
public class IndexFieldsSelectionDialog extends SelectionDialog {

    protected ILabelProvider labelProvider;

    protected IContentProvider contentProvider;

    protected List<Field> values;

    protected List<Field> choiceOfValues;

    protected List<Field> result;

    protected boolean unique;

    public IndexFieldsSelectionDialog(final Shell parentShell,
            final ILabelProvider labelProvider,
            final List<Field> currentValues,
            final List<Field> choiceOfValues,
            final boolean sortChoices,
            final boolean unique) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
        this.labelProvider = labelProvider;
        this.choiceOfValues = choiceOfValues;
        this.unique = unique;
        values = new ArrayList<Field>(currentValues);
        contentProvider = ArrayContentProvider.getInstance();
        if (sortChoices && choiceOfValues != null) {
            this.choiceOfValues = new ArrayList<Field>(choiceOfValues);
        }
    }

    @Override
    protected Label createMessageArea(final Composite composite) {
        final Label label = new Label(composite, SWT.WRAP);
        if (getMessage() != null) {
            label.setText(getMessage());
        }
        label.setFont(composite.getFont());
        return label;
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite contents = (Composite) super.createDialogArea(parent);

        final Label messageLabel = createMessageArea(contents);
        messageLabel.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).span(3, 1).hint(300, SWT.DEFAULT).create());

        final GridLayout contentsGridLayout = (GridLayout) contents.getLayout();
        contentsGridLayout.numColumns = 3;

        final GridData contentsGridData = (GridData) contents.getLayoutData();
        contentsGridData.horizontalAlignment = SWT.FILL;
        contentsGridData.verticalAlignment = SWT.FILL;

        final Composite choiceComposite = new Composite(contents, SWT.NONE);
        {
            final GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
            data.horizontalAlignment = SWT.END;
            choiceComposite.setLayoutData(data);

            final GridLayout layout = new GridLayout();
            data.horizontalAlignment = SWT.FILL;
            layout.marginHeight = 0;
            layout.marginWidth = 0;
            layout.numColumns = 1;
            choiceComposite.setLayout(layout);
        }

        final Label choiceLabel = new Label(choiceComposite, SWT.NONE);
        choiceLabel.setText(Messages.availableAttributes);
        final GridData choiceLabelGridData = new GridData();
        choiceLabelGridData.verticalAlignment = SWT.FILL;
        choiceLabelGridData.horizontalAlignment = SWT.FILL;
        choiceLabel.setLayoutData(choiceLabelGridData);

        final Table choiceTable = choiceOfValues == null ? null : new Table(choiceComposite, SWT.MULTI | SWT.BORDER);
        if (choiceTable != null)
        {
            final GridData choiceTableGridData = new GridData();
            choiceTableGridData.widthHint = 200;
            choiceTableGridData.heightHint = 100;
            choiceTableGridData.verticalAlignment = SWT.FILL;
            choiceTableGridData.horizontalAlignment = SWT.FILL;
            choiceTableGridData.grabExcessHorizontalSpace = true;
            choiceTableGridData.grabExcessVerticalSpace = true;
            choiceTable.setLayoutData(choiceTableGridData);
        }

        final TableViewer choiceTableViewer = choiceOfValues == null ? null : new TableViewer(choiceTable);
        if (choiceTableViewer != null)
        {
            choiceTableViewer.setContentProvider(ArrayContentProvider.getInstance());
            choiceTableViewer.setLabelProvider(labelProvider);
            final PatternFilter filter =
                    new PatternFilter()
                    {

                        @Override
                        protected boolean isParentMatch(final Viewer viewer, final Object element)
                        {
                            return viewer instanceof AbstractTreeViewer && super.isParentMatch(viewer, element);
                        }
                    };
            choiceTableViewer.addFilter(filter);
            if (unique) {
                choiceTableViewer.addFilter(new ViewerFilter() {

                    @Override
                    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                        return !values.contains(element);
                    }
                });
            }
            choiceTableViewer.setInput(choiceOfValues);
        }

        final Composite controlButtons = new Composite(contents, SWT.NONE);
        final GridData controlButtonsGridData = new GridData();
        controlButtonsGridData.verticalAlignment = SWT.FILL;
        controlButtonsGridData.horizontalAlignment = SWT.FILL;
        controlButtons.setLayoutData(controlButtonsGridData);

        final GridLayout controlsButtonGridLayout = new GridLayout();
        controlButtons.setLayout(controlsButtonGridLayout);

        new Label(controlButtons, SWT.NONE);

        final Button addButton = new Button(controlButtons, SWT.PUSH);
        addButton.setText(Messages.add);
        final GridData addButtonGridData = new GridData();
        addButtonGridData.verticalAlignment = SWT.FILL;
        addButtonGridData.horizontalAlignment = SWT.FILL;
        addButton.setLayoutData(addButtonGridData);

        final Button removeButton = new Button(controlButtons, SWT.PUSH);
        removeButton.setText(Messages.Remove);
        final GridData removeButtonGridData = new GridData();
        removeButtonGridData.verticalAlignment = SWT.FILL;
        removeButtonGridData.horizontalAlignment = SWT.FILL;
        removeButton.setLayoutData(removeButtonGridData);

        final Label spaceLabel = new Label(controlButtons, SWT.NONE);
        final GridData spaceLabelGridData = new GridData();
        spaceLabelGridData.verticalSpan = 2;
        spaceLabel.setLayoutData(spaceLabelGridData);

        final Button upButton = new Button(controlButtons, SWT.PUSH);
        upButton.setText(Messages.up);
        final GridData upButtonGridData = new GridData();
        upButtonGridData.verticalAlignment = SWT.FILL;
        upButtonGridData.horizontalAlignment = SWT.FILL;
        upButton.setLayoutData(upButtonGridData);

        final Button downButton = new Button(controlButtons, SWT.PUSH);
        downButton.setText(Messages.down);
        final GridData downButtonGridData = new GridData();
        downButtonGridData.verticalAlignment = SWT.FILL;
        downButtonGridData.horizontalAlignment = SWT.FILL;
        downButton.setLayoutData(downButtonGridData);

        final Composite featureComposite = new Composite(contents, SWT.NONE);
        {
            final GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
            data.horizontalAlignment = SWT.END;
            featureComposite.setLayoutData(data);

            final GridLayout layout = new GridLayout();
            data.horizontalAlignment = SWT.FILL;
            layout.marginHeight = 0;
            layout.marginWidth = 0;
            layout.numColumns = 1;
            featureComposite.setLayout(layout);
        }

        final Label selectedFieldLabel = new Label(featureComposite, SWT.NONE);
        selectedFieldLabel.setText(Messages.indexedAttributes);
        final GridData featureLabelGridData = new GridData();
        featureLabelGridData.horizontalSpan = 2;
        featureLabelGridData.horizontalAlignment = SWT.FILL;
        featureLabelGridData.verticalAlignment = SWT.FILL;
        selectedFieldLabel.setLayoutData(featureLabelGridData);

        final Table selectedFieldTable = new Table(featureComposite, SWT.MULTI | SWT.BORDER);
        final GridData featureTableGridData = new GridData();
        featureTableGridData.widthHint = 200;
        featureTableGridData.heightHint = 100;
        featureTableGridData.verticalAlignment = SWT.FILL;
        featureTableGridData.horizontalAlignment = SWT.FILL;
        featureTableGridData.grabExcessHorizontalSpace = true;
        featureTableGridData.grabExcessVerticalSpace = true;
        selectedFieldTable.setLayoutData(featureTableGridData);

        final TableViewer selectedFieldTableViewer = new TableViewer(selectedFieldTable);
        selectedFieldTableViewer.setContentProvider(contentProvider);
        selectedFieldTableViewer.setLabelProvider(labelProvider);
        selectedFieldTableViewer.setInput(values);
        final List<Field> children = values;
        if (!values.isEmpty())
        {
            selectedFieldTableViewer.setSelection(new StructuredSelection(children.get(0)));
        }

        if (choiceTableViewer != null)
        {
            choiceTableViewer.addDoubleClickListener(new IDoubleClickListener()
            {

                @Override
                public void doubleClick(final DoubleClickEvent event)
                {
                    if (addButton.isEnabled())
                    {
                        addButton.notifyListeners(SWT.Selection, null);
                    }
                }
            });

            selectedFieldTableViewer.addDoubleClickListener(new IDoubleClickListener()
            {

                @Override
                public void doubleClick(final DoubleClickEvent event)
                {
                    if (removeButton.isEnabled())
                    {
                        removeButton.notifyListeners(SWT.Selection, null);
                    }
                }
            });
        }

        upButton.addSelectionListener(
                new SelectionAdapter()
                {

                    @Override
                    public void widgetSelected(final SelectionEvent event)
                    {
                        final IStructuredSelection selection = (IStructuredSelection) selectedFieldTableViewer.getSelection();
                        int minIndex = 0;
                        for (final Iterator<?> i = selection.iterator(); i.hasNext();)
                        {
                            final Object value = i.next();
                            final int index = children.indexOf(value);
                            Collections.swap(children, Math.max(index - 1, minIndex++), index);
                        }
                        selectedFieldTableViewer.refresh();
                    }
                });

        downButton.addSelectionListener(
                new SelectionAdapter()
                {

                    @Override
                    public void widgetSelected(final SelectionEvent event)
                    {
                        final IStructuredSelection selection = (IStructuredSelection) selectedFieldTableViewer.getSelection();
                        int maxIndex = children.size() - 1;
                        final List<?> objects = selection.toList();
                        for (final ListIterator<?> i = objects.listIterator(objects.size()); i.hasPrevious();)
                        {
                            final Object value = i.previous();
                            final int index = children.indexOf(value);
                            Collections.swap(children, Math.min(index + 1, maxIndex--), index);
                        }
                        selectedFieldTableViewer.refresh();
                    }
                });

        addButton.addSelectionListener(
                new SelectionAdapter()
                {

                    // event is null when choiceTableViewer is double clicked
                    @Override
                    public void widgetSelected(final SelectionEvent event)
                    {
                        if (choiceTableViewer != null) {
                            final IStructuredSelection selection = (IStructuredSelection) choiceTableViewer.getSelection();
                            for (final Iterator<?> i = selection.iterator(); i.hasNext();)
                            {
                                final Field value = (Field) i.next();
                                if (!unique || !children.contains(value))
                                {
                                    children.add(value);
                                }
                            }
                            selectedFieldTableViewer.refresh();
                            selectedFieldTableViewer.setSelection(selection);
                            choiceTableViewer.refresh();
                        }

                    }
                });

        removeButton.addSelectionListener(
                new SelectionAdapter()
                {

                    // event is null when featureTableViewer is double clicked
                    @Override
                    public void widgetSelected(final SelectionEvent event)
                    {
                        final IStructuredSelection selection = (IStructuredSelection) selectedFieldTableViewer.getSelection();
                        Object firstValue = null;
                        for (final Iterator<?> i = selection.iterator(); i.hasNext();)
                        {
                            final Object value = i.next();
                            if (firstValue == null)
                            {
                                firstValue = value;
                            }
                            children.remove(value);
                        }

                        if (!children.isEmpty())
                        {
                            selectedFieldTableViewer.setSelection(new StructuredSelection(children.get(0)));
                        }
                        selectedFieldTableViewer.refresh();
                        if (choiceTableViewer != null)
                        {
                            choiceTableViewer.refresh();
                            choiceTableViewer.setSelection(selection);
                        }
                    }
                });

        return contents;
    }

    @Override
    protected void okPressed() {
        setResult(values);
        super.okPressed();
    }

}
