/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.iteration.control;

import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.sections.iteration.MultiInstantiableAdaptableSelectionProvider;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class IteratorNameEditor extends TableViewer {

    private final TableViewerColumn tableViewerColumn;

    public IteratorNameEditor(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory) {
        super(parent, SWT.BORDER | SWT.SINGLE | SWT.NO_SCROLL);
        getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_ITERATOR_TABLE);
        final ObservableListContentProvider provider = new ObservableListContentProvider();
        setContentProvider(provider);
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        getTable().setLayout(tableLayout);
        tableViewerColumn = new TableViewerColumn(this, SWT.FILL);
        tableViewerColumn.setLabelProvider(
                new IteratorNameCellLabelProvider(new AdapterFactoryContentProvider(new ComposedAdapterFactory(
                        ComposedAdapterFactory.Descriptor.Registry.INSTANCE)), provider.getKnownElements()));

        widgetFactory.adapt(getTable());

    }

    public void bindControl(final EMFDataBindingContext context,
            final MultiInstantiableAdaptableSelectionProvider selectionProvider,
            final IProgressService progressService, final IMessageManager messageManager) {
        final IteratorNameObservableEditingSupport editingSupport = new IteratorNameObservableEditingSupport(this,
                messageManager, context,
                new IteratorRefactorOperationFactory(), progressService);
        editingSupport.setControlId(SWTBotConstants.SWTBOT_ID_ITERATOR_NAME_EDITOR);
        tableViewerColumn.setEditingSupport(editingSupport);
        context.bindValue(ViewersObservables.observeInput(this),
                EMFObservables.observeDetailValue(Realm.getDefault(),
                        ViewersObservables.observeSingleSelection(selectionProvider),
                        ProcessPackage.Literals.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION),
                neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(new Converter(Expression.class, IObservableList.class) {

                    @Override
                    public Object convert(final Object fromObject) {
                        return fromObject != null ? new WritableList(newArrayList(fromObject), Expression.class)
                                : new WritableList();
                    }
                }).create());
    }

}
