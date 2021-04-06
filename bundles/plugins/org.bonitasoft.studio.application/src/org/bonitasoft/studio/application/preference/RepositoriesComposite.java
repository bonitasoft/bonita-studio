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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.settings.Activation;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.RepositoryPolicy;
import org.apache.maven.settings.Settings;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class RepositoriesComposite extends Composite {

    private static final String BONITA_PROFILE_ID = "Bonita";
    private static final String DEFAULT_REPOSITORY_NAME = "myRepository";

    private TableViewer viewer;
    private IObservableValue<Profile> selectedProfileObservable = new WritableValue<>();
    private IObservableList<Repository> repositoriesObservable;
    private IViewerObservableValue<Repository> selectionObservable;
    private DataBindingContext ctx;
    private ToolItem deleteItem;

    private Settings settings;

    public RepositoriesComposite(Composite parent, Settings settings) {
        super(parent, SWT.NONE);

        this.settings = settings;
        updateRepositories();
        this.repositoriesObservable = PojoProperties.list(Profile.class, "repositories", Repository.class)
                .observeDetail(selectedProfileObservable);
        this.ctx = new DataBindingContext();

        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());

        var label = new Label(this, SWT.WRAP);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        label.setText(Messages.repositoriesDescription);

        var separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        createProfileCombo(this);

        createRepositoryListComposite(this);
        createRepositoryDetailsComposite(this);

        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());
    }

    private void createProfileCombo(RepositoriesComposite parent) {
        Profile bonitaProfile = retrieveBonitaProfile();

        var profileComposite = new Composite(parent, SWT.NONE);
        profileComposite
                .setLayout(
                        GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        profileComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        var profileLabel = new Label(profileComposite, SWT.NONE);
        profileLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.FILL).create());
        profileLabel.setText(Messages.mavenProfile);

        ControlDecoration controlDecoration = new ControlDecoration(profileLabel, SWT.RIGHT);
        controlDecoration.setDescriptionText(Messages.mavenProfileHint);
        controlDecoration.setMarginWidth(5);
        controlDecoration.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        controlDecoration.show();

        var profileCombo = new ComboViewer(profileComposite, SWT.READ_ONLY | SWT.BORDER);
        profileCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        profileCombo.setContentProvider(new ArrayContentProvider());
        profileCombo.setInput(settings.getProfiles());
        profileCombo.setLabelProvider(
                new LabelProviderBuilder<Profile>().withTextProvider(Profile::getId).createLabelProvider());
        ctx.bindValue(ViewerProperties.singleSelection().observe(profileCombo), selectedProfileObservable);

        selectedProfileObservable.setValue(bonitaProfile);
        selectedProfileObservable.addValueChangeListener(e -> viewer.refresh());
    }

    private Profile retrieveBonitaProfile() {
        return settings.getProfiles().stream()
                .filter(profile -> {
                    return Objects.equals(profile.getId(), BONITA_PROFILE_ID);
                })
                .findFirst().orElseGet(() -> createBonitaProfile());
    }

    private Profile createBonitaProfile() {
        Profile profile = new Profile();
        profile.setId(BONITA_PROFILE_ID);
        Activation activation = new Activation();
        activation.setActiveByDefault(true);
        profile.setActivation(activation);
        settings.getProfiles().add(profile);
        return profile;
    }

    private void createRepositoryDetailsComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(
                GridLayoutFactory.fillDefaults().margins(5, 5).numColumns(2).equalWidth(true).spacing(15, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        new TextWidget.Builder()
                .withLabel(Messages.id)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("id", String.class).observeDetail(selectionObservable))
                .withValidator(new EmptyInputValidator(Messages.id))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.name)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("name", String.class).observeDetail(selectionObservable))
                .withValidator(new EmptyInputValidator(Messages.name))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.url)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .horizontalSpan(2)
                .bindTo(PojoProperties.value("url", String.class).observeDetail(selectionObservable))
                .withValidator(new EmptyInputValidator(Messages.url))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        IObservableValue<RepositoryPolicy> releases = PojoProperties.value("releases", RepositoryPolicy.class)
                .observeDetail(selectionObservable);
        createRepositoryPolicySection(composite, Messages.releases, releases);

        IObservableValue<RepositoryPolicy> snapshots = PojoProperties.value("snapshots", RepositoryPolicy.class)
                .observeDetail(selectionObservable);
        createRepositoryPolicySection(composite, Messages.snapshots, snapshots);

        ctx.bindValue(WidgetProperties.visible().observe(composite), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());
    }

    private void createRepositoryPolicySection(Composite parent, String title,
            IObservableValue<RepositoryPolicy> repositoryPolicy) {
        var group = new Group(parent, SWT.NONE);
        group.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        group.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.BEGINNING).create());
        group.setText(title);

        var enabledButton = new ButtonWidget.Builder()
                .withLabel(Messages.enabled)
                .withStyle(SWT.CHECK)
                .fill()
                .grabHorizontalSpace()
                .createIn(group);
        ctx.bindValue(WidgetProperties.buttonSelection().observe(enabledButton.getButton()),
                PojoProperties.value("enabled", Boolean.class).observeDetail(repositoryPolicy));

        var updatePolicyComposite = new Composite(group, SWT.NONE);
        updatePolicyComposite
                .setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        updatePolicyComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var updatePolicyLabel = new Label(updatePolicyComposite, SWT.NONE);
        updatePolicyLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        updatePolicyLabel.setText(Messages.updatePolicy);

        var updatePolicyCombo = new ComboViewer(updatePolicyComposite, SWT.READ_ONLY | SWT.BORDER);
        updatePolicyCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        updatePolicyCombo.setContentProvider(new ArrayContentProvider());
        updatePolicyCombo.setInput(Arrays.asList("always", "daily", "never")); // interval:X (where X is an integer in minutes) -> not supported.
        ctx.bindValue(ViewerProperties.singleSelection().observe(updatePolicyCombo),
                PojoProperties.value("updatePolicy", String.class).observeDetail(repositoryPolicy));

        var checksumComposite = new Composite(group, SWT.NONE);
        checksumComposite
                .setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        checksumComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var checksumLabel = new Label(checksumComposite, SWT.NONE);
        checksumLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        checksumLabel.setText(Messages.checksumPolicy);

        var checksumCombo = new ComboViewer(checksumComposite, SWT.READ_ONLY | SWT.BORDER);
        checksumCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        checksumCombo.setContentProvider(new ArrayContentProvider());
        checksumCombo.setInput(Arrays.asList("ignore", "fail", "warn"));
        ctx.bindValue(ViewerProperties.singleSelection().observe(checksumCombo),
                PojoProperties.value("checksumPolicy", String.class).observeDetail(repositoryPolicy));
    }

    private void createRepositoryListComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(200, SWT.DEFAULT).create());

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
        addItem.setToolTipText(Messages.addRepositoryTooltip);
        addItem.addListener(SWT.Selection, e -> addRepository());

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setText(Messages.delete);
        deleteItem.setToolTipText(Messages.deleteRepositoryTooltip);
        deleteItem.addListener(SWT.Selection, e -> removeRepository());
    }

    private void removeRepository() {
        if (MessageDialog.openQuestion(getShell(), Messages.removeRepositoryConfirmationTitle,
                String.format(Messages.removeRepositoryConfirmation, selectionObservable.getValue().getName()))) {
            repositoriesObservable.remove(selectionObservable.getValue());
            refreshViewer();
        }
    }

    private void addRepository() {
        var repository = new CustomRepository();
        String name = StringIncrementer.getNextIncrement(DEFAULT_REPOSITORY_NAME,
                repositoriesObservable.stream().map(Repository::getId).collect(Collectors.toList()));
        repository.setId(name);
        repository.setName(name);
        repository.setReleases(new RepositoryPolicy());
        repository.setSnapshots(new RepositoryPolicy());
        repositoriesObservable.add(repository);
        selectionObservable.setValue(repository);
        refreshViewer();
    }

    private void refreshViewer() {
        getDisplay().asyncExec(() -> viewer.refresh());
    }

    protected void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        ColumnViewerToolTipSupport.enableFor(viewer);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);
        viewer.setUseHashlookup(true);
        createRepositoryColumn();
        viewer.setContentProvider(new ObservableListContentProvider<Repository>());
        viewer.setInput(repositoriesObservable);
        selectionObservable = ViewerProperties.singleSelection(Repository.class).observe(viewer);
    }

    private void createRepositoryColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.setLabelProvider(new LabelProviderBuilder<Repository>()
                .withTextProvider(Repository::getName)
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
    }

    private void updateRepositories() {
        settings.getProfiles().forEach(profile -> {
            List<Repository> repoList = new ArrayList<>();
            profile.getRepositories().stream().map(repo -> {
                CustomRepository newRepo = new CustomRepository();
                newRepo.setId(repo.getId());
                newRepo.setName(repo.getName());
                newRepo.setUrl(repo.getUrl());
                newRepo.setLayout(repo.getLayout());
                newRepo.setReleases(repo.getReleases());
                newRepo.setSnapshots(repo.getSnapshots());
                return newRepo;
            }).forEach(repoList::add);
            profile.setRepositories(repoList);
        });
    }

}

/**
 * We extend the maven Repository class because their equals method doesn't perform null check on the input. Or eclipse
 * observables perform some equals on null objects (oldValue / newValue after a delete).
 * This extended class avoid NPE without changing the behavior.
 */
class CustomRepository extends Repository {

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Repository) {
            return super.equals(obj);
        }
        return false;
    }

}
