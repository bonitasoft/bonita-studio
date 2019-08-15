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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.views.ProjectExplorerViewerComparator;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.ui.viewer.CheckboxRepositoryTreeViewer;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator.Builder;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class SelectArtifactToDeployPage implements ControlSupplier {

    private RepositoryAccessor repositoryAccessor;
    private CheckboxRepositoryTreeViewer fileStoreViewer;
    protected IObservableSet<Object> checkedElementsObservable; // Bounded to the viewer -> doesn't contain filtered elements
    protected Set<IRepositoryFileStore> allCheckedElements; // Contains all checked elements, even those not in the viewer anymore (filtered) 
    private IObservableValue<String> searchObservableValue;
    protected List<Object> toFilter;
    private IObservableList<IRepositoryStore<? extends IRepositoryFileStore>> input;
    private List<IRepositoryFileStore> allFileStores;
    private Button cleanDeployOption;
    private Text defaultUsernameText;
    private AutoCompleteField userAutoCompleteField;
    private Label defaultUsernameLabel;
    private String defaultUsername;
    private boolean cleanBDM;
    private Binding defaultUserBinding;
    private IObservableValue<String> usernameObservable;

    public SelectArtifactToDeployPage(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        allCheckedElements = new HashSet<>();
        searchObservableValue = new WritableValue<>();
        toFilter = new ArrayList<>();
        input = new WritableList<>();

        findDeployableRepositoryStore().forEach(input::add);
        allFileStores = input.stream()
                .map(IRepositoryStore::getChildren)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    SelectArtifactToDeployPage() {

    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 20).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label warningDependencyLabel = new Label(mainComposite, SWT.WRAP);
        warningDependencyLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        warningDependencyLabel.setText(Messages.warningMissingDependency);

        Composite viewerAndButtonsComposite = new Composite(mainComposite, SWT.NONE);
        viewerAndButtonsComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1).create());
        viewerAndButtonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        new Label(viewerAndButtonsComposite, SWT.NONE); // column filler
        createSearchAndCollapseComposite(ctx, viewerAndButtonsComposite);
        createSelectButtonComposite(viewerAndButtonsComposite);
        createViewer(ctx, viewerAndButtonsComposite);

        checkedElementsObservable = fileStoreViewer.checkedElementsObservable();
        checkAllElements();
        searchObservableValue.addValueChangeListener(e -> applySearch(e.diff.getNewValue()));
        checkedElementsObservable.addSetChangeListener(event -> {
            updateCleanDeployEnablement();
            updateUserProposals();
        });

        updateUserProposals();
        updateCleanDeployEnablement();

        return mainComposite;
    }

    private void updateCleanDeployEnablement() {
        cleanDeployOption.setEnabled(
                checkedElementsObservable.stream().anyMatch(BusinessObjectModelFileStore.class::isInstance));
    }

    protected void mergeSets() {
        checkedElementsObservable.stream()
                .filter(IRepositoryFileStore.class::isInstance)
                .map(IRepositoryFileStore.class::cast)
                .forEach(allCheckedElements::add);
        List<IRepositoryFileStore> toRemove = allCheckedElements.stream()
                .filter(fileStore -> !checkedElementsObservable.contains(fileStore)
                        && !toFilter.contains(fileStore))
                .collect(Collectors.toList());
        allCheckedElements.removeAll(toRemove);
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
        fileStoreViewer.bindTree(ctx, input);
        fileStoreViewer.setComparator(new ProjectExplorerViewerComparator());
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
                if (element instanceof IRepositoryStore) {
                    return ((IRepositoryStore) element).getChildren().stream()
                            .anyMatch(fileStore -> !toFilter.contains(fileStore));
                }
                return !toFilter.contains(element);
            }
        });
    }

    private void createSearchAndCollapseComposite(DataBindingContext ctx, Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createSearch(ctx, composite);
        createToolbarComposite(composite);
    }

    private void createToolbarComposite(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 8, 0).create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).grab(true, false).create());

        ToolBar toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS);

        ToolItem expandAll = new ToolItem(toolBar, SWT.PUSH);
        expandAll.setImage(Pics.getImage(PicsConstants.expandAll));
        expandAll.setToolTipText(Messages.expandAll);
        expandAll.addListener(SWT.Selection, e -> fileStoreViewer.expandAll());

        ToolItem collapseAll = new ToolItem(toolBar, SWT.PUSH);
        collapseAll.setImage(Pics.getImage(PicsConstants.collapseAll));
        collapseAll.setToolTipText(Messages.collapseAll);
        collapseAll.addListener(SWT.Selection, e -> fileStoreViewer.collapseAll());
    }

    @SuppressWarnings("unchecked")
    private void createSearch(DataBindingContext ctx, Composite parent) {
        Composite searchComposite = new Composite(parent, SWT.NONE);
        searchComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).create());
        searchComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        new SearchWidget.Builder()
                .labelAbove()
                .fill()
                .widthHint(300)
                .withPlaceholder(Messages.searchArtifact)
                .bindTo(searchObservableValue)
                .withDelay(500)
                .inContext(ctx)
                .createIn(searchComposite);

        cleanDeployOption = new Button(searchComposite, SWT.CHECK);
        cleanDeployOption.setLayoutData(GridDataFactory.fillDefaults().create());
        cleanDeployOption.setText(Messages.cleanBDMDatabase);
        cleanDeployOption.setSelection(false);
        ctx.bindValue(WidgetProperties.selection().observe(cleanDeployOption), PojoProperties.value("cleanBDM").observe(this));
        createDefaultUserTextWidget(ctx, searchComposite);
    }

    private Organization getSelectedOrganization() {
        return checkedElementsObservable != null ? checkedElementsObservable.stream()
                .filter(OrganizationFileStore.class::isInstance)
                .map(OrganizationFileStore.class::cast)
                .map(OrganizationFileStore::getContent)
                .findFirst()
                .orElse(null) : null;
    }

    private void createDefaultUserTextWidget(DataBindingContext ctx, final Composite mainComposite) {
        defaultUsernameLabel = new Label(mainComposite, SWT.NONE);
        defaultUsernameLabel.setLayoutData(GridDataFactory.swtDefaults().create());
        defaultUsernameLabel.setText(org.bonitasoft.studio.actors.i18n.Messages.defaultUser);

        defaultUsernameText = new Text(mainComposite, SWT.BORDER);
        defaultUsernameText.setLayoutData(GridDataFactory.swtDefaults().indent(15, 0).hint(200, SWT.DEFAULT).create());
        userAutoCompleteField = new AutoCompleteField(defaultUsernameText, new TextContentAdapter());

        ControlDecoration controlDecoration = new ControlDecoration(defaultUsernameLabel, SWT.RIGHT, mainComposite);
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
        controlDecoration.setDescriptionText(org.bonitasoft.studio.actors.i18n.Messages.defaultUserTooltip);
        controlDecoration.show();

        usernameObservable = PojoProperties.value("defaultUsername").observe(this);
        defaultUserBinding = ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(defaultUsernameText),
                usernameObservable,
                UpdateStrategyFactory.updateValueStrategy().withValidator(defaultUserValidator()).create(),
                null);

        defaultUserBinding.getValidationStatus().addValueChangeListener(event -> {
            if (!event.diff.getNewValue().isOK()) {
                controlDecoration.setDescriptionText(event.diff.getNewValue().getMessage());
                controlDecoration.setMarginWidth(3);
                controlDecoration.setImage(Pics.getImage(PicsConstants.error));
            } else {
                controlDecoration.setDescriptionText(org.bonitasoft.studio.actors.i18n.Messages.defaultUserTooltip);
                controlDecoration.setMarginWidth(0);
                controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
            }
        });
    }

    private void updateUserProposals() {
        String[] proposals = usernames();
        defaultUsernameLabel.setEnabled(proposals != null && proposals.length > 0);
        defaultUsernameText.setEnabled(proposals != null && proposals.length > 0);
        userAutoCompleteField.setProposals(proposals);
        if (usernameObservable.getValue() == null || usernameObservable.getValue().isEmpty()) {
            if (proposals != null && proposals.length > 0) {
                ActiveOrganizationProvider activeOrganizationProvider = new ActiveOrganizationProvider();
               if( Objects.equals(activeOrganizationProvider.getActiveOrganization(),getSelectedOrganization().getName())){
                   usernameObservable.setValue(activeOrganizationProvider.getDefaultUser());
               } else {
                   usernameObservable.setValue(proposals[0]);
               }
            }
        }
        defaultUserBinding.validateTargetToModel();
    }

    private IStatus userExistsInOrganization(Object user) {
        if (getSelectedOrganization() != null) {
            IStatus status = new EmptyInputValidator(org.bonitasoft.studio.actors.i18n.Messages.defaultUser)
                    .validate(user);
            if (!status.isOK()) {
                return status;
            }
            if (Stream.of(usernames()).noneMatch(user::equals)) {
                return ValidationStatus
                        .error(Messages.bind(org.bonitasoft.studio.actors.i18n.Messages.UserDoesntExistError, user));
            }
        }
        return ValidationStatus.ok();
    }

    private String[] usernames() {
        Organization selectedOrganization = getSelectedOrganization();
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
            mergeSets();
            updateArtifactsToFilter(searchValue);
            fileStoreViewer.refresh();
            checkedElementsObservable.clear();
            allCheckedElements.stream()
                    .filter(fileStore -> !toFilter.contains(fileStore))
                    .forEach(checkedElementsObservable::add);
        });
    }

    protected void updateArtifactsToFilter(String searchValue) {
        toFilter.clear();
        getAllFileStores().stream()
                .filter(fileStore -> !fileStore.getDisplayName().toLowerCase().contains(searchValue.toLowerCase()))
                .filter(fileStore -> !Objects.equals(searchValue, fileStore.getParentStore().getDisplayName()))
                .forEach(toFilter::add);
    }

    private void checkAllElements() {
        input.stream()
                .map(IRepositoryStore::getChildren)
                .flatMap(Collection::stream)
                .forEach(checkedElementsObservable::add);
    }

    private List<IRepositoryStore<? extends IRepositoryFileStore>> findDeployableRepositoryStore() {
        return repositoryAccessor.getCurrentRepository()
                .getAllStores()
                .stream()
                .filter(repositoryStore -> repositoryStore.getChildren().stream()
                        .anyMatch(IDeployable.class::isInstance))
                .collect(Collectors.toList());
    }

    /**
     * Only visible checked elements are returned: If the user filters on `Diagrams` and click ok, only checked diagrams will
     * be returned.
     */
    public Collection<IRepositoryFileStore> getElements() {
        return checkedElementsObservable.stream()
                .filter(IRepositoryFileStore.class::isInstance)
                .map(IRepositoryFileStore.class::cast)
                .collect(Collectors.toList());
    }

    // for test purpose
    protected List<IRepositoryFileStore> getAllFileStores() {
        return allFileStores;
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

}
