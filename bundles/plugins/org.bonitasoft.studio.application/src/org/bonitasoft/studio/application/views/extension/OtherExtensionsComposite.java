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

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
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
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class OtherExtensionsComposite extends Composite {

    private Font subtitleFont;
    private IObservableValue<Dependency> selectionObservable;
    private DataBindingContext ctx;
    private DynamicButtonWidget deleteButton;
    private DynamicButtonWidget upgradeButton;
    private DynamicButtonWidget editMavenCoordinatesButton;
    private List<Dependency> localDependencies;
    private LocalDependenciesStore localDependencyStore;

    public OtherExtensionsComposite(Composite parent,
            List<Dependency> otherDependencies,
            Font subtitleFont,
            RemoveExtensionListener removeListener,
            UpdateExtensionListener updateListener,
            DataBindingContext ctx) {
        super(parent, SWT.NONE);
        this.subtitleFont = subtitleFont;
        this.ctx = ctx;
        localDependencyStore = RepositoryManager.getInstance().getCurrentRepository().getLocalDependencyStore();
        localDependencies = otherDependencies.stream()
                .filter(localDependencyStore::isLocalDependency)
                .collect(Collectors.toList());

        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        createTitle(this);
        createOtherExtensionViewer(this, otherDependencies, updateListener, removeListener);
    }

    private void createOtherExtensionViewer(Composite parent,
            List<Dependency> otherDependencies,
            UpdateExtensionListener updateListener, RemoveExtensionListener removeListener) {
        var viewerComposite = new Composite(parent, SWT.NONE);
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 10, 0)
                .spacing(LayoutConstants.getSpacing().x, 1).create());
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewerComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        createToolbar(viewerComposite, updateListener, removeListener);

        var viewer = new TableViewer(viewerComposite,
                SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        viewer.getTable().setHeaderVisible(true);
        ColumnViewerToolTipSupport.enableFor(viewer);
        viewer.getTable().setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        viewer.setUseHashlookup(true);

        createColumn(viewer, "Group ID", Dependency::getGroupId, this::getTooltip, this::getIcon);
        createColumn(viewer, "Artifact ID", Dependency::getArtifactId, null, null);
        createColumn(viewer, "Version", Dependency::getVersion, null, null);
        createColumn(viewer, "Type", Dependency::getType, null, null);
        createColumn(viewer, "Classifier", Dependency::getClassifier, null, null);

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
        ctx.bindValue(editMavenCoordinatesButton.observeEnable(), new ComputedValueBuilder<Boolean>()
                .withSupplier(this::canEditMavenCoordinates)
                .build());
    }

    private boolean canEditMavenCoordinates() {
        Dependency selection = selectionObservable.getValue();
        return selection != null
                && localDependencies.contains(selection)
                && DependencyLookup.readPomProperties(localDependencyStore
                        .dependencyPath(selection)
                        .resolve(LocalDependenciesStore.dependencyFileName(selection)).toFile())
                        .isEmpty();
    }

    private Image getIcon(Dependency dependency) {
        return localDependencies.contains(dependency)
                ? Pics.getImage(PicsConstants.local)
                : Pics.getImage(PicsConstants.remote);
    }

    private String getTooltip(Dependency dependency) {
        return localDependencies.contains(dependency)
                ? Messages.localDependencyTooltip
                : Messages.remoteDependencyTooltip;
    }

    private void createColumn(TableViewer viewer, String title, Function<Dependency, String> textProvider,
            Function<Dependency, String> tooltipProvider, Function<Dependency, Image> imageProvider) {
        var column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(title);
        column.setLabelProvider(new LabelProviderBuilder<Dependency>()
                .withTextProvider(textProvider)
                .withTooltipProvider(tooltipProvider)
                .withImageProvider(imageProvider)
                .createColumnLabelProvider());
    }

    protected void createToolbar(Composite parent,
            UpdateExtensionListener updateListener,
            RemoveExtensionListener removeListener) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        BonitaArtifactDependency bonitaDep = new BonitaArtifactDependency();
        bonitaDep.setArtifactType(ArtifactType.OTHER);
        bonitaDep.setFromMarketplace(false);

        editMavenCoordinatesButton = new DynamicButtonWidget.Builder()
                .withText(Messages.editMavenCoordinates)
                .withTooltipText(Messages.editMavenCoordinatesTooltip)
                .withImage(Pics.getImage(PicsConstants.edit_simple))
                .withHotImage(Pics.getImage(PicsConstants.edit_simple_hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> updateListener.updateGav(bonitaDep, selectionObservable.getValue()))
                .createIn(composite);

        upgradeButton = new DynamicButtonWidget.Builder()
                .withText(Messages.upgradeExtension)
                .withTooltipText(Messages.upgradeExtensionTooltip)
                .withImage(Pics.getImage(PicsConstants.updateDependency))
                .withHotImage(Pics.getImage(PicsConstants.updateDependencyHot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> updateListener.updateExtension(bonitaDep, selectionObservable.getValue()))
                .createIn(composite);

        deleteButton = new DynamicButtonWidget.Builder()
                .withText(Messages.delete)
                .withTooltipText(Messages.deleteUnknownTooltip)
                .withImage(Pics.getImage(PicsConstants.delete))
                .withHotImage(Pics.getImage(PicsConstants.delete_hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> removeListener.removeExtension(selectionObservable.getValue()))
                .createIn(composite);
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
