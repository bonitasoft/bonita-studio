/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.Artifact;
import org.bonitasoft.studio.application.ui.control.model.ArtifactVersion;
import org.bonitasoft.studio.application.ui.control.model.BusinessObjectModelArtifact;
import org.bonitasoft.studio.application.ui.control.model.OrganizationArtifact;
import org.bonitasoft.studio.application.ui.control.model.ProcessArtifact;
import org.bonitasoft.studio.application.ui.control.model.ProcessVersion;
import org.bonitasoft.studio.application.ui.control.model.RepositoryModel;
import org.bonitasoft.studio.application.ui.control.model.RepositoryStore;
import org.bonitasoft.studio.application.ui.control.model.VersionedArtifact;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.ui.viewer.CheckboxRepositoryTreeViewer;
import org.bonitasoft.studio.common.repository.ui.viewer.FileStoreObservableFactory;
import org.bonitasoft.studio.common.repository.ui.viewer.FileStoreTreeStructureAdvisor;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.extension.IEnvironmentProvider;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator.Builder;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class SelectArtifactToDeployPage implements ControlSupplier {

    private static final String DEPLOY_DEFAULT_SELECTION = "deployDefaultSelection";
    private static final String CLEAN_BDM_DEFAULT_SELECTION = "cleanBDMDefaultSelection";
    private static final String VALIDATE_DEFAULT_SELECTION = "RunValidation";
    private static final String ONLY_LATEST_PROCESS_VERSION_SELECTION = "onlyLatestProcessVersion";

    private CheckboxRepositoryTreeViewer fileStoreViewer;
    protected IObservableSet<Object> checkedElementsObservable; // Bounded to the viewer -> doesn't contain filtered elements
    protected Set<Object> allCheckedElements = new HashSet<>(); // Contains all checked elements, even those not in the viewer anymore (filtered) 
    private IObservableValue<String> searchObservableValue = new WritableValue<>();
    protected List<Object> toFilter = new ArrayList<>();
    private Button cleanDeployOption;
    private String defaultUsername;
    private String environment;
    private boolean cleanBDM;
    private IObservableValue<String> usernameObservable;
    private SimpleContentProposalProvider usernameProposalProvider;
    private TextWidget defaultUserTextWidget;
    private IEnvironmentProvider environmentProvider;
    private Set<Object> defaultSelectedElements;
    private RepositoryModel repositoryModel;
    private Button validateProcessOption;
    private boolean validate = true;
    private Label countArtifactLabel;
    private boolean filtering = false;
    private boolean latestVersionOnly = true;
    private ComboWidget environmentComboWidget;

    public SelectArtifactToDeployPage(RepositoryModel repositoryModel, IEnvironmentProvider environmentProvider) {
        this.repositoryModel = repositoryModel;
        this.environmentProvider = environmentProvider;
    }

    @Override
    public void loadSettings(IDialogSettings settings) {
        IDialogSettings section = settings.getSection(repositoryModel.getName());
        if (section != null && section.getArray(DEPLOY_DEFAULT_SELECTION) != null) {
            if (defaultSelectedElements == null || defaultSelectedElements.isEmpty()) {
                defaultSelectedElements = fromStrings(section.getArray(DEPLOY_DEFAULT_SELECTION));
            }
            cleanBDM = section.getBoolean(CLEAN_BDM_DEFAULT_SELECTION);
            validate = section.getBoolean(VALIDATE_DEFAULT_SELECTION);
            latestVersionOnly = section.getBoolean(ONLY_LATEST_PROCESS_VERSION_SELECTION);
        }
        String activeEnvironment = ConfigurationPlugin.getDefault().getPreferenceStore()
                .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        environment = environmentProvider.getEnvironment().contains(activeEnvironment) ? activeEnvironment
                : ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON;
    }

    public void setDefaultSelectedElements(Set<Object> defaultSelectedElements) {
        this.defaultSelectedElements = defaultSelectedElements;
    }

    @Override
    public void saveSettings(IDialogSettings settings) {
        mergeSets();
        IDialogSettings section = settings.getSection(repositoryModel.getName());
        if (section == null) {
            section = settings.addNewSection(repositoryModel.getName());
        }
        section.put(DEPLOY_DEFAULT_SELECTION, stringify(allCheckedElements));
        section.put(CLEAN_BDM_DEFAULT_SELECTION, cleanBDM);
        section.put(VALIDATE_DEFAULT_SELECTION, validate);
        section.put(ONLY_LATEST_PROCESS_VERSION_SELECTION, latestVersionOnly);
        ConfigurationPlugin.getDefault().getPreferenceStore()
                .setValue(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION, environment);
    }

    private String[] stringify(Set<Object> selectedElements) {
        return selectedElements.stream()
                .map(elem -> {
                    if (elem instanceof VersionedArtifact && ((VersionedArtifact) elem).hasSingleVersion()) {
                        return ((VersionedArtifact) elem).getLatestVersion().toString();
                    } else if (elem instanceof Artifact && !(elem instanceof VersionedArtifact)) {
                        return elem.toString();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toArray(String[]::new);
    }

    private Set<Object> fromStrings(String[] selectedElements) {
        Collection<String> selection = Arrays.asList(selectedElements);
        return repositoryModel.getArtifacts().stream()
                .filter(artifact -> selection.contains(artifact.toString()))
                .map(singleVersionProcess())
                .collect(Collectors.toSet());
    }

    private Function<Object, Object> singleVersionProcess() {
        return object -> {
            if (object instanceof ArtifactVersion && ((ArtifactVersion) object).getParent().hasSingleVersion()) {
                return ((ArtifactVersion) object).getParent();
            }
            return object;
        };
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite mainComposite = new Composite(parent, SWT.INHERIT_FORCE);
        mainComposite.setLayout(GridLayoutFactory.swtDefaults()
                .create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Composite viewerAndButtonsComposite = new Composite(mainComposite, SWT.INHERIT_FORCE);
        viewerAndButtonsComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1).create());
        viewerAndButtonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        new Label(viewerAndButtonsComposite, SWT.NONE); // column filler
        createSearchAndCollapseComposite(ctx, viewerAndButtonsComposite);
        createSelectButtonComposite(viewerAndButtonsComposite);
        createViewer(ctx, viewerAndButtonsComposite);
        createArtifactCounter(viewerAndButtonsComposite);
        createDeployOptions(ctx, viewerAndButtonsComposite);

        checkedElementsObservable = fileStoreViewer.checkedElementsObservable();
        defaultSelection();
        searchObservableValue.addValueChangeListener(e -> applySearch(e.diff.getNewValue()));
        checkedElementsObservable.addSetChangeListener(event -> {
            if (!filtering) {
                mergeSets();
                updateCleanDeployEnablement();
                updateUserProposals();
                updateEnvironmentEnablement();
            }
        });
        mergeSets();
        updateUserProposals();
        updateCleanDeployEnablement();
        updateEnvironmentEnablement();

        return mainComposite;
    }

    private void updateEnvironmentEnablement() {
        if (environmentComboWidget != null) {
            setWidgetEnabled(environmentComboWidget.getControl().getParent(),
                    allCheckedElements.stream().anyMatch(isProcessArtifact().or(isProcessVersion())));
        }
    }

    private Predicate<? super Object> isProcessArtifact() {
        return ProcessArtifact.class::isInstance;
    }

    private Predicate<? super Object> isProcessVersion() {
        return ProcessVersion.class::isInstance;
    }

    private void createArtifactCounter(Composite parent) {
        countArtifactLabel = new Label(parent, SWT.NONE);
        countArtifactLabel
                .setLayoutData(GridDataFactory.fillDefaults().span(2, 1).align(SWT.END, SWT.END).indent(0, 5).create());
    }

    private void updateCount() {
        long checkedArtifacts = allCheckedElements.stream().filter(filterVersionedArtifact()).count();
        long totalArtifacts = repositoryModel.getArtifacts().stream()
                .filter(artifact -> !(artifact instanceof ProcessArtifact)).count();
        String text = String.format(Messages.artifactCounter, checkedArtifacts, totalArtifacts);
        if (checkedArtifacts == totalArtifacts) {
            text = Messages.allArtifactSelected;
        } else if (checkedArtifacts == 0) {
            text = Messages.noArtifactSelected;
        }
        countArtifactLabel.setText(text);
    }

    private Predicate<? super Object> filterVersionedArtifact() {
        return artifact -> artifact instanceof VersionedArtifact ? ((VersionedArtifact) artifact).hasSingleVersion()
                : artifact instanceof ArtifactVersion ? !((ArtifactVersion) artifact).getParent().hasSingleVersion()
                        : artifact instanceof Artifact;
    }

    private void updateCleanDeployEnablement() {
        cleanDeployOption.setEnabled(
                allCheckedElements.stream().anyMatch(BusinessObjectModelArtifact.class::isInstance));
    }

    protected void mergeSets() {
        allCheckedElements.addAll(checkedElementsObservable.stream()
                .filter(object -> object instanceof Artifact)
                .collect(Collectors.toSet()));
        List<Object> toRemove = allCheckedElements.stream()
                .filter(fileStore -> !checkedElementsObservable.contains(fileStore)
                        && !toFilter.contains(fileStore))
                .collect(Collectors.toList());
        allCheckedElements.removeAll(toRemove);
        updateCount();
    }

    private void createSelectButtonComposite(Composite parent) {
        Composite buttonComposite = new Composite(parent, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        new ButtonWidget.Builder()
                .withLabel(Messages.selectAll)
                .fill()
                .onClick(e -> checkAllElements())
                .createIn(buttonComposite);

        new ButtonWidget.Builder()
                .withLabel(Messages.selectNone)
                .fill()
                .onClick(e -> checkedElementsObservable.clear())
                .createIn(buttonComposite);
    }

    private void createViewer(DataBindingContext ctx, Composite parent) {
        fileStoreViewer = new CheckboxRepositoryTreeViewer(parent);
        fileStoreViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        fileStoreViewer.setContentProvider(new ObservableListTreeContentProvider(observableFactory(),
                treeStructureAdvisor()));
        fileStoreViewer.setLabelProvider(new DeployTreeLabelProvider());
        fileStoreViewer.bindTree(ctx, new WritableList(repositoryModel.getStores(), RepositoryStore.class));
        fileStoreViewer.setComparator(new ViewerComparator() {

            @Override
            public int compare(Viewer viewer, Object e1, Object e2) {
                if (e1 instanceof ArtifactVersion && e2 instanceof ArtifactVersion) {
                    return ((ArtifactVersion) e1).compareTo((ArtifactVersion) e2);
                }
                if (e1 instanceof RepositoryStore && e2 instanceof RepositoryStore) {
                    return ((RepositoryStore) e1).compareTo((RepositoryStore) e2);
                }
                return super.compare(viewer, e1, e2);
            }
        });
        fileStoreViewer.addDoubleClickListener(
                e -> {
                    if (e.getSelection() instanceof TreeSelection) {
                        TreeSelection selection = (TreeSelection) e.getSelection();
                        fileStoreViewer.setExpandedState(selection.getFirstElement(),
                                !fileStoreViewer.getExpandedState(selection.getFirstElement()));
                    }
                });
        fileStoreViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof RepositoryStore) {
                    return ((RepositoryStore) element).getArtifacts().stream()
                            .anyMatch(fileStore -> !toFilter.contains(fileStore));
                }
                return !toFilter.contains(element);
            }
        });
        fileStoreViewer.addCheckStateListener(event -> {
            if (isOrganizationRepositoryStore(event.getElement()) && event.getChecked()) {
                uniqueOrganizationSelection(getDefaultOrganization());
            }
            if (event.getElement() instanceof OrganizationArtifact && countSelectedOrganizations() > 1) {
                uniqueOrganizationSelection((OrganizationArtifact) event.getElement());
            }
            if (latestVersionOnly && event.getChecked()) {
                if (isVersionedArtifact(event.getElement())) {
                    selectLatestVersion(getVersionedArtifact(event.getElement()));
                } else if (event.getElement() instanceof ArtifactVersion
                        && !((ArtifactVersion) event.getElement()).isLatest()) {
                    checkedElementsObservable.remove(event.getElement());
                }
            }
        });
    }

    private void selectLatestVersion(VersionedArtifact selectedArtifact) {
        checkedElementsObservable.add(selectedArtifact.getLatestVersion());
        checkedElementsObservable
                .removeIf(element -> element instanceof ArtifactVersion
                        && !((ArtifactVersion) element).isLatest());
    }

    private boolean isVersionedArtifact(Object element) {
        if (element instanceof RepositoryStore) {
            return ((RepositoryStore) element).getArtifacts().get(0) instanceof VersionedArtifact;
        }
        return element instanceof VersionedArtifact;
    }

    private VersionedArtifact getVersionedArtifact(Object element) {
        if (element instanceof RepositoryStore) {
            return (VersionedArtifact) ((RepositoryStore) element).getArtifacts().get(0);
        }
        return (VersionedArtifact) element;
    }

    private boolean isOrganizationRepositoryStore(Object element) {
        if (element instanceof RepositoryStore) {
            return ((RepositoryStore) element).getArtifacts().get(0) instanceof OrganizationArtifact;
        }
        return false;
    }

    private FileStoreTreeStructureAdvisor treeStructureAdvisor() {
        return new FileStoreTreeStructureAdvisor() {

            @Override
            public Boolean hasChildren(Object element) {
                if (element instanceof VersionedArtifact) {
                    return !((VersionedArtifact) element).hasSingleVersion();
                }
                return element instanceof RepositoryStore || super.hasChildren(element);
            }

            @Override
            public Object getParent(Object element) {
                if (element instanceof Artifact) {
                    return ((Artifact) element).getParent();
                }
                return super.getParent(element);
            }

        };
    }

    private FileStoreObservableFactory observableFactory() {
        return new FileStoreObservableFactory() {

            @Override
            public IObservable createObservable(Object target) {
                if (target instanceof RepositoryStore) {
                    return new WritableList(((RepositoryStore) target).getArtifacts(), Artifact.class);
                }
                if (target instanceof VersionedArtifact) {
                    return new WritableList(((VersionedArtifact) target).getVersions(), VersionedArtifact.class);
                }
                return super.createObservable(target);
            }
        };
    }

    private Artifact getDefaultOrganization() {
        return repositoryModel.getArtifacts().stream()
                .filter(OrganizationArtifact.class::isInstance)
                .filter(artifact -> Objects.equals(new ActiveOrganizationProvider().getActiveOrganizationFileName(),
                        artifact.getName()))
                .findFirst()
                .orElse(repositoryModel.getArtifacts().stream()
                        .filter(OrganizationArtifact.class::isInstance)
                        .findFirst()
                        .orElse(null));
    }

    private void uniqueOrganizationSelection(Artifact selectedOrganizationArtifact) {
        checkedElementsObservable.add(selectedOrganizationArtifact);
        checkedElementsObservable
                .removeIf(element -> (element instanceof OrganizationArtifact)
                        && !Objects.equals(element, selectedOrganizationArtifact));
        updateUserProposals();
    }

    private long countSelectedOrganizations() {
        return checkedElementsObservable.stream()
                .filter(OrganizationArtifact.class::isInstance)
                .count();
    }

    private void createSearchAndCollapseComposite(DataBindingContext ctx, Composite parent) {
        Composite composite = new Composite(parent, SWT.INHERIT_FORCE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createSearch(ctx, composite);
        createToolbarComposite(ctx, composite);
    }

    private void createToolbarComposite(DataBindingContext ctx, Composite parent) {
        Composite composite = new Composite(parent, SWT.INHERIT_FORCE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(0, 0, 8, 0).create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).grab(true, false).create());

        Button onlyLatestProcessVersionButton = new Button(composite, SWT.CHECK);
        onlyLatestProcessVersionButton.setText(Messages.selectLatestVersion);

        IObservableValue<Boolean> latestOnlyObservable = PojoProperties
                .<SelectArtifactToDeployPage, Boolean> value("latestVersionOnly").observe(this);
        ctx.bindValue(WidgetProperties.buttonSelection().observe(onlyLatestProcessVersionButton),
                latestOnlyObservable);
        latestOnlyObservable.addValueChangeListener(e -> {
            if (latestVersionOnly) {
                checkLatestOnly();
            }
        });

        ToolBar toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);

        new ToolItem(toolBar, SWT.SEPARATOR | SWT.VERTICAL);

        ToolItem expandAll = new ToolItem(toolBar, SWT.PUSH);
        expandAll.setImage(Pics.getImage(PicsConstants.expandAll));
        expandAll.setToolTipText(Messages.expandAll);
        expandAll.addListener(SWT.Selection, e -> fileStoreViewer.expandAll());

        ToolItem collapseAll = new ToolItem(toolBar, SWT.PUSH);
        collapseAll.setImage(Pics.getImage(PicsConstants.collapseAll));
        collapseAll.setToolTipText(Messages.collapseAll);
        collapseAll.addListener(SWT.Selection, e -> fileStoreViewer.collapseAll());
    }

    private void checkLatestOnly() {
        checkedElementsObservable.removeIf(this::isOldVersion);
        mergeSets();
        allCheckedElements.removeIf(this::isOldVersion);
    }

    private boolean isOldVersion(Object element) {
        return element instanceof ArtifactVersion && !((ArtifactVersion) element).isLatest();
    }

    private void createSearch(DataBindingContext ctx, Composite parent) {
        Composite searchComposite = new Composite(parent, SWT.NONE);
        searchComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        searchComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BOTTOM).create());

        new SearchWidget.Builder()
                .useNativeRender()
                .labelAbove()
                .fill()
                .widthHint(400)
                .withPlaceholder(Messages.searchArtifact)
                .bindTo(searchObservableValue)
                .withDelay(500)
                .inContext(ctx)
                .createIn(searchComposite);
    }

    private void createDeployOptions(DataBindingContext ctx, Composite parent) {
        new Label(parent, SWT.NONE);

        Group deployOptionGroup = new Group(parent, SWT.NONE);
        deployOptionGroup
                .setLayout(GridLayoutFactory.swtDefaults().numColumns(1).extendedMargins(10, 0, 0, 0).create());
        deployOptionGroup.setLayoutData(GridDataFactory.fillDefaults().create());
        deployOptionGroup.setText(Messages.deployOptions);

        if (!environmentProvider.getEnvironment().isEmpty()) {
            environmentComboWidget = new ComboWidget.Builder()
                    .withLabel(Messages.environment)
                    .labelAbove()
                    .widthHint(400)
                    .useNativeRender()
                    .readOnly()
                    .withItems(environmentProvider.getEnvironment().toArray(new String[] {}))
                    .bindTo(PojoProperties.value("environment").observe(this))
                    .inContext(ctx)
                    .createIn(deployOptionGroup);

            ControlDecoration controlDecoration = new ControlDecoration(
                    environmentComboWidget.getControl().getParent().getChildren()[0], SWT.RIGHT);
            controlDecoration.setDescriptionText(Messages.environmentTootltip);
            controlDecoration.setMarginWidth(5);
            controlDecoration.setImage(FieldDecorationRegistry.getDefault()
                    .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
            controlDecoration.show();
        }

        createDefaultUserTextWidget(ctx, deployOptionGroup);

        cleanDeployOption = new Button(deployOptionGroup, SWT.CHECK);
        cleanDeployOption.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        cleanDeployOption.setText(Messages.cleanBDMDatabase);

        ctx.bindValue(WidgetProperties.buttonSelection().observe(cleanDeployOption),
                PojoProperties.value("cleanBDM").observe(this));

        validateProcessOption = new Button(deployOptionGroup, SWT.CHECK);
        validateProcessOption.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        validateProcessOption.setText(Messages.validateProcess);

        ControlDecoration controlDecoration = new ControlDecoration(validateProcessOption, SWT.RIGHT);
        controlDecoration.setDescriptionText(Messages.validateHint);
        controlDecoration.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        controlDecoration.show();

        ctx.bindValue(WidgetProperties.buttonSelection().observe(validateProcessOption),
                PojoProperties.value("validate").observe(this));
    }

    private Organization getSelectedOrganization() {
        return allCheckedElements != null
                ? allCheckedElements.stream()
                        .filter(OrganizationArtifact.class::isInstance)
                        .map(OrganizationArtifact.class::cast)
                        .map(artifact -> artifact.getModel())
                        .findFirst()
                        .orElse(null)
                : null;
    }

    private void createDefaultUserTextWidget(DataBindingContext ctx, final Composite mainComposite) {
        usernameObservable = PojoProperties.<SelectArtifactToDeployPage, String> value("defaultUsername").observe(this);
        usernameProposalProvider = new SimpleContentProposalProvider();
        usernameProposalProvider.setFiltering(true);
        defaultUserTextWidget = new TextWidget.Builder()
                .useNativeRender()
                .labelAbove()
                .widthHint(530)
                .withLabel(org.bonitasoft.studio.identity.i18n.Messages.defaultUser)
                .withTootltip(org.bonitasoft.studio.identity.i18n.Messages.defaultUserTooltip)
                .bindTo(usernameObservable)
                .withProposalProvider(usernameProposalProvider)
                .withValidator(defaultUserValidator().create())
                .inContext(ctx)
                .createIn(mainComposite);
    }

    private void updateUserProposals() {
        usernameObservable.setValue("");
        Organization selectedOrganization = getSelectedOrganization();
        if (selectedOrganization == null && getDefaultOrganization() != null) {
            selectedOrganization = ((OrganizationArtifact) getDefaultOrganization()).getModel();
        }
        if (selectedOrganization == null) {
            setWidgetEnabled(defaultUserTextWidget, false);
            return;
        }
        String[] proposals = usernames(selectedOrganization);
        boolean enableDefaultUser = proposals != null && proposals.length > 0;
        setWidgetEnabled(defaultUserTextWidget, enableDefaultUser);
        usernameProposalProvider.setProposals(proposals);
        if (usernameObservable.getValue() == null || usernameObservable.getValue().isEmpty()) {
            if (proposals != null && proposals.length > 0) {
                ActiveOrganizationProvider activeOrganizationProvider = new ActiveOrganizationProvider();
                if (Objects.equals(activeOrganizationProvider.getActiveOrganization(),
                        selectedOrganization.getName())) {
                    usernameObservable.setValue(activeOrganizationProvider.getDefaultUser());
                } else {
                    usernameObservable.setValue(proposals[0]);
                }
            }
        }
    }

    private void setWidgetEnabled(Control control, boolean enabled) {
        control.setEnabled(enabled);
        if (control instanceof CLabel) {
            control.setForeground(enabled ? Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND)
                    : Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY));
        }
        if (control instanceof Composite) {
            Stream.of(((Composite) control).getChildren()).forEach(c -> setWidgetEnabled(c, enabled));
        }
    }

    private IStatus userExistsInOrganization(Object user) {
        Organization selectedOrganization = getSelectedOrganization();
        if (selectedOrganization == null && getDefaultOrganization() != null) {
            selectedOrganization = ((OrganizationArtifact) getDefaultOrganization()).getModel();
        }
        if (selectedOrganization != null) {
            IStatus status = new EmptyInputValidator(org.bonitasoft.studio.identity.i18n.Messages.defaultUser)
                    .validate(user);
            if (!status.isOK()) {
                return status;
            }
            if (Stream.of(usernames(selectedOrganization)).noneMatch(user::equals)) {
                return ValidationStatus
                        .error(Messages.bind(org.bonitasoft.studio.identity.i18n.Messages.UserDoesntExistError, user));
            }
        }
        return ValidationStatus.ok();
    }

    private String[] usernames(Organization selectedOrganization) {
        return selectedOrganization == null
                ? new String[0]
                : selectedOrganization
                        .getUsers()
                        .getUser()
                        .stream()
                        .map(User::getUserName)
                        .toArray(String[]::new);
    }

    protected Builder defaultUserValidator() {
        return new MultiValidator.Builder()
                .havingValidators(this::userExistsInOrganization);
    }

    private void applySearch(String searchValue) {
        Display.getDefault().asyncExec(() -> {
            filtering = true;
            mergeSets();
            updateArtifactsToFilter(searchValue);
            fileStoreViewer.refresh();
            checkedElementsObservable.clear();
            checkedElementsObservable.addAll(allCheckedElements.stream()
                    .filter(fileStore -> !toFilter.contains(fileStore))
                    .collect(Collectors.toList()));
            filtering = false;
        });
    }

    protected void updateArtifactsToFilter(String searchValue) {
        toFilter.clear();
        repositoryModel.getArtifacts().stream()
                .filter(artifact -> !artifact.getName().toLowerCase().contains(searchValue.toLowerCase()))
                .filter(artifact -> !Objects.equals(searchValue, artifact.getParent().toString()))
                .forEach(toFilter::add);
    }

    private void defaultSelection() {
        if (defaultSelectedElements != null && !defaultSelectedElements.isEmpty()) {
            checkedElementsObservable.addAll(defaultSelectedElements);
        } else {
            checkAllElements();
        }
    }

    private void checkAllElements() {
        if (latestVersionOnly) {
            checkLatestElements();
        } else {
            checkedElementsObservable.addAll(repositoryModel.getArtifacts().stream()
                    //Only one organization can be selected at a time
                    .filter(artifact -> OrganizationArtifact.class.isInstance(artifact)
                            ? Objects.equals(getDefaultOrganization(), artifact) : true)
                    .filter(artifact -> ArtifactVersion.class.isInstance(artifact)
                            ? !((ArtifactVersion) artifact).getParent().hasSingleVersion() : true)
                    .collect(Collectors.toList()));
        }
    }

    private void checkLatestElements() {
        checkedElementsObservable.clear();
        checkedElementsObservable.addAll(repositoryModel.getArtifacts().stream()
                //Only one organization can be selected at a time
                .filter(artifact -> OrganizationArtifact.class.isInstance(artifact)
                        ? Objects.equals(getDefaultOrganization(), artifact) : true)
                .filter(artifact -> VersionedArtifact.class.isInstance(artifact)
                        ? ((VersionedArtifact) artifact).hasSingleVersion() : true)
                .filter(artifact -> ArtifactVersion.class.isInstance(artifact)
                        ? !((ArtifactVersion) artifact).getParent().hasSingleVersion() : true)
                .filter(artifact -> ArtifactVersion.class.isInstance(artifact) ? ((ArtifactVersion) artifact).isLatest()
                        : true)
                .collect(Collectors.toList()));
    }

    public Collection<Artifact> getSelectedArtifacts() {
        return allCheckedElements.stream()
                .filter(Artifact.class::isInstance)
                .map(Artifact.class::cast)
                .map(artifact -> artifact instanceof VersionedArtifact
                        && ((VersionedArtifact) artifact).hasSingleVersion()
                                ? ((VersionedArtifact) artifact).getLatestVersion()
                                : artifact)
                .distinct()
                .collect(Collectors.toList());
    }

    public String getDefaultUsername() {
        return defaultUsername;
    }

    public void setDefaultUsername(String defaultUsername) {
        this.defaultUsername = defaultUsername;
    }

    public boolean isCleanBDM() {
        return cleanBDM;
    }

    public void setCleanBDM(boolean cleanBDM) {
        this.cleanBDM = cleanBDM;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public boolean isLatestVersionOnly() {
        return latestVersionOnly;
    }

    public void setLatestVersionOnly(boolean latestVersionOnly) {
        this.latestVersionOnly = latestVersionOnly;
    }

}
