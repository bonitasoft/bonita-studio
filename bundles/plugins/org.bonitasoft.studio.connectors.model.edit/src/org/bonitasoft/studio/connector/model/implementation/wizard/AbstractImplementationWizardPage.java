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
package org.bonitasoft.studio.connector.model.implementation.wizard;

import java.util.List;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
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
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractImplementationWizardPage extends NewTypeWizardPage implements ISelectionChangedListener {

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;

    private final ConnectorImplementation implementation;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private Button removeButton;
    private final List<ConnectorImplementation> existingImpl;
    private final DefinitionResourceProvider messageProvider;
    private final List<ConnectorDefinition> definitions;

    private ISWTObservableValue observeClassText;

    private ISWTObservableValue observePackageText;

    public AbstractImplementationWizardPage(final ConnectorImplementation implementation,
            final List<ConnectorImplementation> existingImpl, final List<ConnectorDefinition> definitions,
            final SourceRepositoryStore<AbstractFileStore> sourceStore, final String pageTitle, final String pageDescription,
            final DefinitionResourceProvider messageProvider) {
        super(true, AbstractImplementationWizardPage.class.getName());
        setTitle(pageTitle);
        setDescription(pageDescription);
        this.implementation = implementation;
        this.existingImpl = existingImpl;
        this.messageProvider = messageProvider;
        this.definitions = definitions;
        try {
            final IPackageFragmentRoot root = RepositoryManager.getInstance().getCurrentRepository().getJavaProject()
                    .findPackageFragmentRoot(sourceStore.getResource().getFullPath());
            setPackageFragmentRoot(root, false);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {

        context = new EMFDataBindingContext();
        if (pageSupport == null) {
            pageSupport = WizardPageSupport.create(this, context);
        }

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).create());

        final Label definitionIdLabel = new Label(mainComposite, SWT.NONE);
        definitionIdLabel.setText(Messages.implementationId + " *");
        definitionIdLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Composite idComposite = new Composite(mainComposite, SWT.NONE);
        idComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        idComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());

        final Text idText = new Text(idComposite, SWT.BORDER);
        idText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        idText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.connector.model.implementation.wizard.idText");

        final UpdateValueStrategy idStrategy = new UpdateValueStrategy();
        idStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.implementationId));
        idStrategy.setBeforeSetValidator(value -> {
            if (!FileUtil.isValidName(NamingUtils.toConnectorImplementationFilename(value.toString(),
                    implementation.getImplementationVersion(), true))) {
                return ValidationStatus.error(Messages.invalidFileName);
            }
            return Status.OK_STATUS;
        });

        final Label definitionVersionLabel = new Label(idComposite, SWT.NONE);
        definitionVersionLabel.setText(Messages.versionLabel + " *");
        definitionVersionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text versionText = new Text(idComposite, SWT.BORDER);
        versionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        versionText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.connector.model.implementation.wizard.versionText");

        final UpdateValueStrategy versionStrategy = new UpdateValueStrategy();
        versionStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.versionLabel));
        versionStrategy.setBeforeSetValidator(value -> {
            if (value.toString().contains(" ")) {
                return ValidationStatus.error("Whitespace is not allowed in version id");
            }
            if (!FileUtil.isValidName(NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(),
                    value.toString(), true))) {
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
                    return ValidationStatus.error("Whitespace is not allowed in definition id");
                }
                final String implID = NamingUtils.toConnectorImplementationFilename(observableIdText.getValue().toString(),
                        observableVersionText.getValue().toString(), false);
                for (final ConnectorImplementation impl : existingImpl) {
                    final String existingId = NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(),
                            impl.getImplementationVersion(), false);
                    if (implID.equals(existingId)) {
                        return ValidationStatus.error(Messages.implementationAlreadyExists);
                    }
                }
                return ValidationStatus.ok();
            }
        };

        context.addValidationStatusProvider(definitionValidator);
        context.bindValue(observableIdText,
                EMFObservables.observeValue(implementation,
                        ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID),
                idStrategy, null);
        context.bindValue(observableVersionText,
                EMFObservables.observeValue(implementation,
                        ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION),
                versionStrategy, null);

        final Label descriptionLabel = new Label(mainComposite, SWT.NONE);
        descriptionLabel.setText(Messages.description);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());

        final Text descriptionText = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        descriptionText
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 50).span(2, 1).create());
        final UpdateValueStrategy descStrategy = new UpdateValueStrategy();
        descStrategy.setBeforeSetValidator(new InputLengthValidator(Messages.description, 255));
        context.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify), EMFObservables
                .observeValue(implementation, ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DESCRIPTION),
                descStrategy, null);

        createClassAndPackageName(mainComposite);
        createDependenciesViewer(mainComposite);

        final MultiValidator defValidator = new MultiValidator() {

            @Override
            protected IStatus validate() {
                final String className = (String) observeClassText.getValue();
                String packageName = (String) observePackageText.getValue();
                if (className != null && !className.isEmpty()) {
                    if (!packageName.isEmpty()) {
                        packageName = packageName + ".";
                    }
                    final String qualifiedClassName = packageName + className;
                    for (final ConnectorImplementation impl : existingImpl) {
                        if (qualifiedClassName.equals(impl.getImplementationClassname())) {
                            return ValidationStatus.error(qualifiedClassName + " "
                                    + Messages.bind(Messages.alreadyExistsForAnotherImplementation,
                                            NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(),
                                                    impl.getImplementationVersion(), false)));
                        }
                    }
                }
                return ValidationStatus.ok();
            }
        };
        context.addValidationStatusProvider(defValidator);

        updateButtons(new StructuredSelection());

        setControl(mainComposite);
    }

    protected abstract ITreeContentProvider getContentProvider();

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            if (implementation.getImplementationId() == null || implementation.getImplementationId().isEmpty()) {
                final String id = implementation.getDefinitionId() + "-impl";
                implementation.setImplementationId(id);
            }

            if (implementation.getDescription() == null || implementation.getDescription().isEmpty()) {
                for (final ConnectorDefinition def : definitions) {
                    if (def.getId().equals(implementation.getDefinitionId())
                            && def.getVersion().equals(implementation.getDefinitionVersion())) {
                        implementation.setDescription(messageProvider.getConnectorDefinitionDescription(def));
                        break;
                    }
                }
            }

            if (implementation.getImplementationClassname() == null
                    || implementation.getImplementationClassname().isEmpty()) {
                String id = implementation.getDefinitionId();
                id = String.valueOf(id.toCharArray()[0]).toUpperCase() + id.substring(1);
                while (id.indexOf("-") != -1) {
                    String oldId = id;
                    oldId = oldId.substring(oldId.indexOf("-") + 1, oldId.length());
                    oldId = String.valueOf(oldId.toCharArray()[0]).toUpperCase() + oldId.substring(1);
                    id = id.substring(0, id.indexOf("-")) + oldId;
                }
                final String className = id + "Impl";
                implementation.setImplementationClassname(className);
            }
            if (!implementation.getImplementationClassname().contains(".")) {
                observePackageText.setValue("org.mycompany.connector");
            }
            if (context != null) {
                context.updateTargets();
            }
        }
    }

    protected void createClassAndPackageName(final Composite mainComposite) {
        final Label classNameLabel = new Label(mainComposite, SWT.NONE);
        classNameLabel.setText(Messages.classNameLabel + " *");
        classNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text classNameText = new Text(mainComposite, SWT.BORDER);
        classNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        classNameText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.connector.model.implementation.wizard.classNameText");

        final Label packageLabel = new Label(mainComposite, SWT.NONE);
        packageLabel.setText(Messages.packageName + " *");
        packageLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text packageText = new Text(mainComposite, SWT.BORDER);
        packageText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());

        final UpdateValueStrategy packageTargetToModel = new UpdateValueStrategy();
        packageTargetToModel.setConverter(new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object from) {
                if (from != null) {
                    String packageName = from.toString();
                    if (!packageName.isEmpty()) {
                        packageName = packageName + ".";
                    }
                    if (classNameText != null && !classNameText.isDisposed()) {
                        return packageName + classNameText.getText();
                    }
                }
                return null;
            }
        });
        packageTargetToModel.setAfterGetValidator(
                value -> JavaConventions.validatePackageName(value.toString(), JavaCore.VERSION_1_6, JavaCore.VERSION_1_6));

        final UpdateValueStrategy packageModelToTarget = new UpdateValueStrategy();
        packageModelToTarget.setConverter(new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object from) {
                if (from != null) {
                    final String qualifiedClassname = from.toString();
                    if (qualifiedClassname.indexOf(".") != -1) {
                        final int i = qualifiedClassname.lastIndexOf(".");
                        return qualifiedClassname.subSequence(0, i);
                    } else {
                        return "";
                    }
                }
                return null;
            }
        });

        final UpdateValueStrategy classTargetToModel = new UpdateValueStrategy();
        classTargetToModel.setConverter(new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object from) {
                if (from != null) {
                    final String className = from.toString();
                    if (packageText != null && !packageText.isDisposed()) {
                        String packageName = packageText.getText().trim();
                        if (!packageName.isEmpty()) {
                            packageName = packageName + ".";
                        }
                        return packageName + className;
                    }
                }
                return null;
            }
        });
        classTargetToModel.setAfterGetValidator(value -> {
            if (value == null || value.toString().isEmpty()) {
                return ValidationStatus.error(Messages.missingImplemenationClass);
            }
            return JavaConventions.validateClassFileName(value.toString() + ".class", JavaCore.VERSION_1_6,
                    JavaCore.VERSION_1_6);
        });
        classTargetToModel.setBeforeSetValidator(value -> {
            if (implementation.getImplementationClassname() == null
                    || implementation.getImplementationClassname().toString().indexOf(".") == -1) {
                return JavaConventions.validatePackageName("", JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
            }
            return Status.OK_STATUS;
        });

        final UpdateValueStrategy classModelToTarget = new UpdateValueStrategy();
        classModelToTarget.setConverter(new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object from) {
                if (from != null) {
                    final String qualifiedClassname = from.toString();
                    if (qualifiedClassname.indexOf(".") != -1) {
                        final int i = qualifiedClassname.lastIndexOf(".");
                        return qualifiedClassname.subSequence(i + 1, qualifiedClassname.length());
                    } else {
                        return qualifiedClassname;
                    }
                }
                return null;
            }
        });

        observeClassText = SWTObservables.observeText(classNameText, SWT.Modify);
        observePackageText = SWTObservables.observeText(packageText, SWT.Modify);

        context.bindValue(observeClassText,
                EMFObservables.observeValue(implementation,
                        ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME),
                classTargetToModel, classModelToTarget);
        context.bindValue(observePackageText,
                EMFObservables.observeValue(implementation,
                        ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME),
                packageTargetToModel, packageModelToTarget);

        final Button browsePackagesButton = new Button(mainComposite, SWT.PUSH);
        browsePackagesButton.setText(Messages.browsePackages);
        browsePackagesButton.addListener(SWT.Selection, event -> {
            final IPackageFragment selectedPackage = AbstractImplementationWizardPage.this.choosePackage();
            if (selectedPackage != null) {
                packageText.setText(selectedPackage.getElementName());
            }
        });

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

    protected void createDependenciesViewer(final Composite mainComposite) {
        final Label dependencyLabel = new Label(mainComposite, SWT.NONE);
        dependencyLabel.setText(Messages.dependenciesLabel);
        dependencyLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());

        final TableViewer viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 75).create());
        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.addFilter(new ImplementationJarFilter(implementation));
        viewer.addSelectionChangedListener(this);
        viewer.setLabelProvider(new LabelProvider() {

            @Override
            public Image getImage(final Object element) {
                return Pics.getImage("jar.gif");
            }
        });

        context.bindValue(ViewersObservables.observeInput(viewer),
                EMFObservables.observeValue(implementation.getJarDependencies(),
                        ConnectorImplementationPackage.Literals.JAR_DEPENDENCIES__JAR_DEPENDENCY));

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addButton.setText(Messages.Add);
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final SelectJarsDialog dialog = new SelectJarsDialog(Display.getDefault().getActiveShell());
                if (dialog.open() == Dialog.OK) {
                    for (final IRepositoryFileStore jarFile : dialog.getSelectedJars()) {
                        final String jar = jarFile.getName();
                        if (!implementation.getJarDependencies().getJarDependency().contains(jar)) {
                            implementation.getJarDependencies().getJarDependency().add(jar);
                        }
                    }
                }
            }

        });

        removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        removeButton.setText(Messages.remove);
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
                for (final Object selected : selection.toList()) {
                    if (selected instanceof String) {
                        implementation.getJarDependencies().getJarDependency().remove(selected);
                    }
                }
                viewer.refresh();
            }
        });

    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        updateButtons(event.getSelection());
    }

    private void updateButtons(final ISelection selection) {
        if (removeButton != null && !removeButton.isDisposed()) {
            removeButton.setEnabled(!selection.isEmpty());
        }
    }

}
