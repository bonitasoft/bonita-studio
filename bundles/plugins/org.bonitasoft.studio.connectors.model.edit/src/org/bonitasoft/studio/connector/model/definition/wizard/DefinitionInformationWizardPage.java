/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.dialog.DefinitionCategoryContentProvider;
import org.bonitasoft.studio.connector.model.definition.dialog.NewCategoryDialog;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class DefinitionInformationWizardPage extends WizardPage implements ISelectionChangedListener {

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;

    private final ConnectorDefinition definition;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private Image iconImage;
    private File iconImageFile;
    private final Properties messages;
    private String displayName;
    private String definitionDescription;
    private Button removeJarButton;
    private final List<ConnectorDefinition> existingDefinitions;
    private final Image defaultImage;
    private final DefinitionResourceProvider messageProvider;

    //private Button removeCategoryButton;

    public DefinitionInformationWizardPage(ConnectorDefinition definition, Properties messages,
            List<ConnectorDefinition> existingDefinitions, Image defaultImage, DefinitionResourceProvider messageProvider) {
        super(DefinitionInformationWizardPage.class.getName());
        setTitle(Messages.definitionTitle);
        setDescription(Messages.definitionDescription);
        this.definition = definition;
        this.messages = messages;
        this.existingDefinitions = existingDefinitions;
        this.defaultImage = defaultImage;
        this.messageProvider = messageProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        context = new EMFDataBindingContext();

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).create());

        final Label definitionIdLabel = new Label(mainComposite, SWT.NONE);
        definitionIdLabel.setText(Messages.definitionIdLabel + " *");
        definitionIdLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Composite idComposite = new Composite(mainComposite, SWT.NONE);
        idComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        idComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());

        final Text idText = new Text(idComposite, SWT.BORDER);
        idText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        idText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.connector.model.definition.wizard.idText");

        final Label definitionVersionLabel = new Label(idComposite, SWT.NONE);
        definitionVersionLabel.setText(Messages.versionLabel + " *");
        definitionVersionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text versionText = new Text(idComposite, SWT.BORDER);
        versionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        versionText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.connector.model.definition.wizard.versionText");

        final UpdateValueStrategy versionStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
        versionStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.versionLabel));
        versionStrategy.setBeforeSetValidator(value -> {
            if (!FileUtil.isValidName(
                    NamingUtils.toConnectorDefinitionFilename(definition.getId(), value.toString(), true))) {
                return ValidationStatus.error(Messages.invalidFileName);
            }

            return Status.OK_STATUS;
        });

        final ISWTObservableValue observableIdText = SWTObservables.observeText(idText, SWT.Modify);
        final ISWTObservableValue observableVersionText = SWTObservables.observeText(versionText, SWT.Modify);
        final MultiValidator definitionValidator = new MultiValidator() {

            @Override
            protected IStatus validate() {
                if (observableIdText.getValue().toString().contains(" ")) {
                    return ValidationStatus.error(Messages.whitespaceInDefinitionIDNotAllowed);
                }
                if (observableVersionText.getValue().toString().contains(" ")) {
                    return ValidationStatus.error(Messages.whitespaceInDefinitionIDNotAllowed);
                }

                String defID = NamingUtils.toConnectorDefinitionFilename(observableIdText.getValue().toString(),
                        observableVersionText.getValue().toString(), false);
                for (ConnectorDefinition def : existingDefinitions) {
                    String existingId = NamingUtils.toConnectorDefinitionFilename(def.getId(), def.getVersion(), false);
                    if (defID.equals(existingId)) {
                        return ValidationStatus.error(Messages.definitionAlreadyExists);
                    }
                }
                return ValidationStatus.ok();
            }
        };

        context.addValidationStatusProvider(definitionValidator);

        final UpdateValueStrategy idStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
        idStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.definitionIdLabel));
        idStrategy.setBeforeSetValidator(value -> {
            if (!FileUtil.isValidName(
                    NamingUtils.toConnectorDefinitionFilename(value.toString(), definition.getVersion(), true))) {
                return ValidationStatus.error(Messages.invalidFileName);
            }
            return Status.OK_STATUS;
        });

        context.bindValue(definitionValidator.observeValidatedValue(observableIdText),
                EMFObservables.observeValue(definition, ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__ID),
                idStrategy, null);
        context.bindValue(definitionValidator.observeValidatedValue(observableVersionText),
                EMFObservables.observeValue(definition, ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__VERSION),
                versionStrategy, null);

        final Label displayNameLabel = new Label(mainComposite, SWT.NONE);
        displayNameLabel.setText(Messages.displayName);
        displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text displayNameText = new Text(mainComposite, SWT.BORDER);
        displayNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        displayNameText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.connector.model.definition.wizard.displayNameText");

        context.bindValue(SWTObservables.observeText(displayNameText, SWT.Modify),
                PojoProperties.value(DefinitionInformationWizardPage.class, "displayName").observe(this));

        final Label descriptionNameLabel = new Label(mainComposite, SWT.NONE);
        descriptionNameLabel.setText(Messages.description);
        descriptionNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());

        final Text descriptionText = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        descriptionText
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 60).span(2, 1).create());
        UpdateValueStrategy descStrategy = new UpdateValueStrategy();
        descStrategy.setBeforeSetValidator(new InputLengthValidator(Messages.description, 255));
        context.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify),
                PojoProperties.value(DefinitionInformationWizardPage.class, "definitionDescription").observe(this),
                descStrategy, null);

        final Label iconLabel = new Label(mainComposite, SWT.NONE);
        iconLabel.setText(Messages.iconLabel);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Composite iconComposite = new Composite(mainComposite, SWT.NONE);
        iconComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        iconComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        final Label icon = new Label(iconComposite, SWT.NONE);
        icon.setLayoutData(GridDataFactory.fillDefaults().create());

        if (definition.getIcon() == null || definition.getIcon().isEmpty()) {
            iconImage = defaultImage;
        } else {
            iconImage = messageProvider.getDefinitionIcon(definition);
        }
        icon.setImage(iconImage);

        final Button iconButton = new Button(iconComposite, SWT.PUSH);
        iconButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
        iconButton.setText("...");
        iconButton.addListener(SWT.Selection, event -> {
            FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
            dialog.setFilterExtensions(new String[] { "*.jpg;*.jpeg;*.gif;*.png;*.bmp;" });
            dialog.setFilterPath(System.getProperty("user.home"));
            String res = dialog.open();
            if (res != null) {
                try (FileInputStream is = new FileInputStream(res)) {
                    iconImageFile = new File(res);
                    setIconName(iconImageFile.getName());
                    if (iconImage != null) {
                        iconImage.dispose();
                    }
                    iconImage = new Image(Display.getDefault(), new ImageData(is).scaledTo(16, 16));
                    icon.setImage(iconImage);
                    icon.getParent().layout(true, true);
                } catch (Exception ex) {
                    BonitaStudioLog.error(ex);
                }
            }
        });

        createCategoryViewer(mainComposite);

        createDependenciesViewer(mainComposite);

        updateButtons(new StructuredSelection());

        pageSupport = WizardPageSupport.create(this, context);
        setControl(mainComposite);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (pageSupport != null) {
            pageSupport.dispose();
        }
        if (context != null) {
            context.dispose();
        }
    }

    protected void createCategoryViewer(Composite mainComposite) {
        Label categoryLabel = new Label(mainComposite, SWT.NONE);
        categoryLabel.setText(Messages.categoryLabel);
        categoryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());

        final TreeViewer categoryViewer = new TreeViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE);
        categoryViewer.getTree()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 50).create());
        categoryViewer.addSelectionChangedListener(this);
        categoryViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return element instanceof Category && !org.bonitasoft.studio.common.repository.Messages.uncategorized.equals(((Category) element).getId());
            }
        });
        categoryViewer.setContentProvider(new DefinitionCategoryContentProvider());
        categoryViewer.setLabelProvider(new ConnectorDefinitionTreeLabelProvider(messageProvider) {

            @Override
            public String getText(Object element) {
                String label = super.getText(element);
                if (label.equals(((Category) element).getId())) {
                    label = messageProvider.getCategoryLabel(messages, (Category) element);
                }
                return label;
            }
        });
        final List<Category> allCategories = messageProvider.getAllCategories();
        categoryViewer.setInput(allCategories);
        final Category selectedCategory = getSelectedCategory(allCategories);
        if (selectedCategory != null) {
            categoryViewer.setSelection(new StructuredSelection(selectedCategory));
        }
        categoryViewer.addSelectionChangedListener(event -> {
            if (!((IStructuredSelection) event.getSelection()).isEmpty()) {
                Category selection = (Category) ((IStructuredSelection) event.getSelection()).getFirstElement();
                definition.getCategory().clear();
                definition.getCategory().add(selection);
                List<Category> categories = (List<Category>) categoryViewer.getInput();
                List<Category> parentCategories = new ArrayList<>();
                getParentCategories(parentCategories, categories, selection);
                definition.getCategory().addAll(parentCategories);
            }
        });

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());

        final Button createButton = new Button(buttonComposite, SWT.FLAT);
        createButton.setText(Messages.newCategory);
        createButton.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        createButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                HashSet<String> existingCatIds = new HashSet<>();
                existingCatIds.addAll(messageProvider.getProvidedCategoriesIds());
                existingCatIds.addAll(messageProvider.getUserCategoriesIds());
                List<Category> input = (List<Category>) categoryViewer.getInput();
                for (Category category : input) {
                    existingCatIds.add(category.getId());
                }
                NewCategoryDialog dialog = new NewCategoryDialog(Display.getDefault().getActiveShell(), existingCatIds);
                if (dialog.open() == Dialog.OK) {
                    File imageFile = dialog.getIconImageFile();
                    Category newCategory = dialog.getCategory();
                    String displayName = dialog.getDisplayName();
                    messageProvider.setCategoryLabel(messages, newCategory.getId(), displayName);
                    if (imageFile != null) {
                        messageProvider.createIcon(imageFile, newCategory.getIcon());
                    }
                    input = (List<Category>) categoryViewer.getInput();
                    input.add(newCategory);
                    categoryViewer.setInput(input);
                }
            }
        });
    }

    protected Category getSelectedCategory(List<Category> allCategories) {
        if (definition != null) {
            for (Category c : definition.getCategory()) {
                if (!new DefinitionCategoryContentProvider(allCategories).hasChildren(c)) {
                    return c;
                }
            }
        }
        return null;
    }

    protected void getParentCategories(List<Category> parentCategories, List<Category> allCategories, Category selection) {
        for (Category c : allCategories) {
            if (selection.getParentCategoryId() != null && selection.getParentCategoryId().equals(c.getId())) {
                parentCategories.add(c);
                getParentCategories(parentCategories, allCategories, c);
            }
        }
    }

    protected void createDependenciesViewer(Composite mainComposite) {
        Label dependencyLabel = new Label(mainComposite, SWT.NONE);
        dependencyLabel.setText(Messages.dependenciesLabel);
        dependencyLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());

        final TableViewer viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.addSelectionChangedListener(this);
        viewer.setLabelProvider(new LabelProvider() {

            @Override
            public Image getImage(Object element) {
                return Pics.getImage("jar.gif");
            }
        });

        context.bindValue(ViewersObservables.observeInput(viewer), EMFObservables.observeValue(definition,
                ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__JAR_DEPENDENCY));

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());

        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addButton.setText(Messages.Add);
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                SelectJarsDialog dialog = new SelectJarsDialog(Display.getDefault().getActiveShell());
                if (dialog.open() == Dialog.OK) {
                    for (IRepositoryFileStore jarFile : dialog.getSelectedJars()) {
                        String jar = jarFile.getName();
                        if (!definition.getJarDependency().contains(jar)) {
                            definition.getJarDependency().add(jar);
                        }
                    }
                }
            }

        });

        removeJarButton = new Button(buttonComposite, SWT.FLAT);
        removeJarButton.setLayoutData(GridDataFactory.fillDefaults().hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        removeJarButton.setText(Messages.remove);
        removeJarButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
                for (Object selected : selection.toList()) {
                    if (selected instanceof String) {
                        definition.getJarDependency().remove(selected);
                    }
                }
                viewer.refresh();
            }
        });

        new Label(mainComposite, SWT.NONE); //FILLER
        final Label dependenciesHintLabel = new Label(mainComposite, SWT.NONE);
        dependenciesHintLabel.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());
        dependenciesHintLabel.setText(Messages.dependenciesInfo);
    }

    public File getIconImageFile() {
        return iconImageFile;
    }

    public String getIconName() {
        return definition.getIcon();
    }

    public void setIconName(String iconName) {
        definition.setIcon(iconName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        updateButtons(event.getSelection());
    }

    private void updateButtons(ISelection selection) {
        if (removeJarButton != null && !removeJarButton.isDisposed()) {
            removeJarButton.setEnabled(((IStructuredSelection) selection).getFirstElement() instanceof String);
        }
        //		if(removeCategoryButton != null  && !removeCategoryButton.isDisposed()){
        //			removeCategoryButton.setEnabled(((IStructuredSelection) selection).getFirstElement() instanceof Category) ;
        //		}
    }

    public String getDefinitionDescription() {
        return definitionDescription;
    }

    public void setDefinitionDescription(String definitionDescription) {
        this.definitionDescription = definitionDescription;
    }

}
