/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.preference;

import java.util.stream.Collectors;

import org.apache.maven.settings.Mirror;
import org.apache.maven.settings.Settings;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.browser.OpenSystemBrowserListener;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class MirrorsComposite extends Composite {

    public static final String MIRRORS_VIEWER_ID = "mirrorsViewer";
    public static final String ADD_MIRROR_BUTTON_ID = "addMirror";
    public static final String REMOVE_MIRROR_BUTTON_ID = "removeMirror";
    public static final String DEFAULT_MIRROR_NAME = "mirrorName";

    private IObservableList<Mirror> mirrorsObservable;
    private DataBindingContext ctx;
    private ToolItem deleteItem;

    private TableViewer viewer;

    private IViewerObservableValue<Mirror> selectionObservable;

    public MirrorsComposite(Composite parent, IObservableValue<Settings> settingsObservable) {
        super(parent, SWT.NONE);

        this.mirrorsObservable = PojoProperties.list(Settings.class, "mirrors", Mirror.class)
                .observeDetail(settingsObservable);
        this.ctx = new DataBindingContext();

        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 10).create());

        createMirrorsComposite(this);

        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());
    }

    private void createMirrorsComposite(MirrorsComposite parent) {
        var mirrorsComposite = new Composite(parent, SWT.NONE);
        mirrorsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        mirrorsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var link = new Link(mirrorsComposite, SWT.NONE);
        link.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        link.setText(Messages.mirrorsLink);
        link.addListener(SWT.Selection,
                new OpenSystemBrowserListener("https://maven.apache.org/guides/mini/guide-mirror-settings.html"));

        createMirrorListComposite(mirrorsComposite);
        createMirrorDetailsComposite(mirrorsComposite);
    }

    private void createMirrorDetailsComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        new TextWidget.Builder()
                .withLabel(Messages.id)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("id", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        IObservableValue<String> nameObservable = PojoProperties.value("name", String.class)
                .observeDetail(selectionObservable);
        new TextWidget.Builder()
                .withLabel(Messages.name)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(nameObservable)
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);
        nameObservable.addValueChangeListener(e -> refreshViewer());

        new TextWidget.Builder()
                .withLabel(Messages.url)
                .labelAbove()
                .withTootltip(Messages.mirrorUrlTooltip)
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("url", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.mirrorOf)
                .labelAbove()
                .withTootltip(Messages.mirrorOfTooltip)
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("mirrorOf", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        ctx.bindValue(WidgetProperties.visible().observe(composite), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());
    }

    private void createMirrorListComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(250, SWT.DEFAULT).create());

        createToolbar(composite);
        createViewer(composite);
    }

    private void createToolbar(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());

        var toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);

        var addItem = new ToolItem(toolBar, SWT.PUSH);
        addItem.setImage(Pics.getImage(PicsConstants.add_simple));
        addItem.setText(Messages.add);
        addItem.setToolTipText(Messages.addMirrorTooltip);
        addItem.addListener(SWT.Selection, e -> addMirror());
        addItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_MIRROR_BUTTON_ID);

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setToolTipText(Messages.deleteMirrorTooltip);
        deleteItem.setText(Messages.delete);
        deleteItem.addListener(SWT.Selection, e -> removeMirror());
        deleteItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_MIRROR_BUTTON_ID);
    }

    private void removeMirror() {
        if (MessageDialog.openQuestion(getShell(), Messages.removeMirrorConfirmationTitle,
                String.format(Messages.removeMirrorConfirmation, selectionObservable.getValue().getId()))) {
            mirrorsObservable.remove(selectionObservable.getValue());
            refreshViewer();
        }
    }

    private void addMirror() {
        var mirror = new Mirror();
        String name = StringIncrementer.getNextIncrement(DEFAULT_MIRROR_NAME,
                mirrorsObservable.stream().map(Mirror::getName).collect(Collectors.toList()));
        mirror.setName(name);
        mirrorsObservable.add(mirror);
        selectionObservable.setValue(mirror);
        refreshViewer();
    }

    protected void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, MIRRORS_VIEWER_ID);

        ColumnViewerToolTipSupport.enableFor(viewer);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);
        viewer.setUseHashlookup(true);
        createMirrorColumn(viewer);
        viewer.setContentProvider(new ObservableListContentProvider<Mirror>());
        viewer.setInput(mirrorsObservable);
        selectionObservable = ViewerProperties.singleSelection(Mirror.class).observe(viewer);
    }

    private void createMirrorColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.setLabelProvider(new LabelProviderBuilder<Mirror>()
                .withTextProvider(Mirror::getName)
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
    }

    private void refreshViewer() {
        getDisplay().asyncExec(() -> viewer.refresh());
    }

    public void refresh() {
        refreshViewer();
    }

}
