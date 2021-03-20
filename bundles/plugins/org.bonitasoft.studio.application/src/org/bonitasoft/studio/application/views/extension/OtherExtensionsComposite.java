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
package org.bonitasoft.studio.application.views.extension;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class OtherExtensionsComposite extends Composite {

    private Font subtitleFont;
    private IObservableValue<Dependency> selectionObservable;
    private Consumer<Dependency> removeExtensionsOperation;
    private DataBindingContext ctx;
    private DynamicButtonWidget deleteButton;
    private DynamicButtonWidget upgradeButton;
    private CommandExecutor commandExecutor;
    private RepositoryAccessor repositoryAccessor;

    public OtherExtensionsComposite(Composite parent, List<Dependency> otherDependencies, Font subtitleFont,
            Consumer<Dependency> removeExtensionsOperation, DataBindingContext ctx, RepositoryAccessor repositoryAccessor) {
        super(parent, SWT.NONE);
        this.subtitleFont = subtitleFont;
        this.removeExtensionsOperation = removeExtensionsOperation;
        this.ctx = ctx;
        this.repositoryAccessor = repositoryAccessor;
        this.commandExecutor = new CommandExecutor();

        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        createTitle(this);
        createOtherExtensionViewer(this, otherDependencies);
    }

    private void createOtherExtensionViewer(Composite parent, List<Dependency> otherDependencies) {
        var viewerComposite = new Composite(parent, SWT.NONE);
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 10, 0)
                .spacing(LayoutConstants.getSpacing().x, 1).create());
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewerComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        createToolbar(viewerComposite);

        var viewer = new TableViewer(viewerComposite,
                SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        viewer.setUseHashlookup(true);

        createColumn(viewer, "Group ID", dep -> dep.getGroupId());
        createColumn(viewer, "Artifact ID", dep -> dep.getArtifactId());
        createColumn(viewer, "Version", dep -> dep.getVersion());
        createColumn(viewer, "Type", dep -> dep.getType());
        createColumn(viewer, "Classifier", dep -> dep.getClassifier());

        var layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(3, true));
        layout.addColumnData(new ColumnWeightData(3, true));
        layout.addColumnData(new ColumnWeightData(2, true));
        layout.addColumnData(new ColumnWeightData(1, true));
        layout.addColumnData(new ColumnWeightData(2, true));
        viewer.getTable().setLayout(layout);

        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.setInput(otherDependencies);
        selectionObservable = ViewerProperties.<TableViewer, Dependency> singleSelection()
                .observe(viewer);

        ComputedValue<Boolean> selectionNotEmptyObservable = new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build();
        ctx.bindValue(deleteButton.observeEnable(), selectionNotEmptyObservable);
        ctx.bindValue(upgradeButton.observeEnable(), selectionNotEmptyObservable);
    }

    private void createColumn(TableViewer viewer, String title, Function<Dependency, String> textProvider) {
        var column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(title);
        column.setLabelProvider(new LabelProviderBuilder<Dependency>()
                .withTextProvider(textProvider)
                .createColumnLabelProvider());
    }

    protected void createToolbar(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        upgradeButton = new DynamicButtonWidget.Builder()
                .withText(Messages.upgradeExtension)
                .withTooltipText(Messages.upgradeExtensionTooltip)
                .withImage(Pics.getImage(PicsConstants.updateDependency))
                .withHotImage(Pics.getImage(PicsConstants.updateDependencyHot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> upgradeExtension())
                .createIn(composite);

        deleteButton = new DynamicButtonWidget.Builder()
                .withText(Messages.delete)
                .withTooltipText(Messages.deleteUnknownTooltip)
                .withImage(Pics.getImage(PicsConstants.delete))
                .withHotImage(Pics.getImage(PicsConstants.delete_hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> {
                    Dependency dependencySelected = selectionObservable.getValue();
                    String depText = String.format("%s:%s:%s", dependencySelected.getGroupId(),
                            dependencySelected.getArtifactId(), dependencySelected.getVersion());
                    if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                            Messages.removeExtensionConfirmationTitle,
                            String.format(Messages.removeExtensionConfirmation, depText))) {
                        removeExtensionsOperation.accept(dependencySelected);
                    }
                })
                .createIn(composite);
    }

    private void upgradeExtension() {
        boolean localExtension = repositoryAccessor.getCurrentRepository().getLocalDependencyStore()
                .isLocalDependency(selectionObservable.getValue());
        var parameters = new HashMap<String, Object>();
        parameters.put("groupId", selectionObservable.getValue().getGroupId());
        parameters.put("artifactId", selectionObservable.getValue().getArtifactId());
        parameters.put("type", selectionObservable.getValue().getType());
        parameters.put("classifier", selectionObservable.getValue().getClassifier());
        parameters.put("isLocal", String.valueOf(localExtension));
        commandExecutor.executeCommand(ProjectExtensionEditorPart.IMPORT_EXTENSION_COMMAND, parameters);
    }

    private void createTitle(Composite parent) {
        var title = new Label(parent, SWT.WRAP);
        title.setLayoutData(GridDataFactory.fillDefaults().create());
        title.setText(Messages.unknownExtensionsTitle);
        title.setFont(subtitleFont);
        title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        title.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
    }

}
