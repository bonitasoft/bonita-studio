/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.databinding.validator.MultiValidatorFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.ArchetypeConfigurationWithClassName;
import org.bonitasoft.studio.maven.model.ArchetypeConfigurationWithLanguage;
import org.bonitasoft.studio.maven.model.CustomPageArchetypeConfiguration;
import org.bonitasoft.studio.maven.model.ExtensionProjectArchetypeConfiguration;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.validator.ArtifactIdValidator;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

public class NewExtensionProjectArtifactConfigurationPage extends WizardPage {

    /** SWT Bot ID for the class name text field */
    public static final String SWTBOT_CLASS_NAME_TEXT = "org.bonitasoft.studio.rest.api.extension.ui.wizard.classNameText";
    /** SWT Bot ID for the package text field */
    public static final String SWTBOT_PACKAGE_TEXT = "org.bonitasoft.studio.rest.api.extension.ui.wizard.packageText";
    /** SWT Bot ID for the name text field */
    public static final String SWTBOT_NAME_TEXT = "org.bonitasoft.studio.rest.api.extension.ui.wizard.nameText";
    /** SWT Bot ID for the artifact ID text field */
    public static final String SWTBOT_ARTIFACT_ID_TEXT = "org.bonitasoft.studio.rest.api.extension.ui.wizard.artifactIdText";
    private final ExtensionProjectArchetypeConfiguration configuration;
    private final WidgetFactory widgetFactory;
    private final MavenProjectConfiguration projectConfiguration;
    private final IWorkspace workspace;
    private String helpLinkURL;

    public NewExtensionProjectArtifactConfigurationPage(WidgetFactory widgetFactory,
            ExtensionProjectArchetypeConfiguration configuration,
            MavenProjectConfiguration projectConfiguration,
            IWorkspace workspace) {
        super(NewExtensionProjectArtifactConfigurationPage.class.getName());
        this.configuration = configuration;
        this.widgetFactory = widgetFactory;
        this.projectConfiguration = projectConfiguration;
        this.workspace = workspace;
    }

    @Override
    public void createControl(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).create());

        final DataBindingContext context = new DataBindingContext();
        WizardPageSupport.create(this, context);

        if (helpLinkURL != null) {
            Link link = new Link(mainComposite, SWT.NONE);
            link.setText(String.format(Messages.getMoreInfoInDocumentation, configuration.getArtifactLabel()));
            link.addListener(SWT.Selection, event -> openBrowser(helpLinkURL));
        }

        if (configuration instanceof CustomPageArchetypeConfiguration) {
            createPortalGroup(mainComposite, context);
        }
        createMavenGroup(mainComposite, context);

        setControl(mainComposite);
    }

    private void openBrowser(String url) {
        try {
            new OpenBrowserOperation(new URL(url)).run();
        } catch (MalformedURLException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected void createMavenGroup(final Composite mainComposite, final DataBindingContext context) {
        final Group mavenGroup = new Group(mainComposite, SWT.NONE);
        mavenGroup.setText(Messages.project);
        mavenGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        mavenGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createArtifactIdControl(mavenGroup, context);
        createPackageControl(mavenGroup, context);

        if (configuration instanceof ArchetypeConfigurationWithClassName) {
            createClassNameControl(mavenGroup, context);
        }

        if (configuration instanceof ArchetypeConfigurationWithLanguage) {
            createLanguageControl(mavenGroup, context);
        }
    }

    protected void createPortalGroup(final Composite mainComposite, final DataBindingContext context) {
        final Group portalGroup = new Group(mainComposite, SWT.NONE);
        portalGroup.setText(Messages.portal);
        portalGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        portalGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Label descriptionLabel = widgetFactory.newLabel(portalGroup, Messages.portalDescription);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        createNameControl(portalGroup, context);
        createDescriptionControl(portalGroup, context);
    }

    protected void createDescriptionControl(final Composite mainComposite, final DataBindingContext context) {
        final Label descriptionLabel = widgetFactory.newLabel(mainComposite, Messages.description);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.TOP).create());
        final Text descriptionText = widgetFactory.newText(mainComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        descriptionText.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).minSize(SWT.DEFAULT, 60).indent(10, 0).create());
        descriptionText.addTraverseListener(e -> {
            if (e.detail == SWT.TRAVERSE_TAB_NEXT || e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
                e.doit = true;
            }
        });
        IObservableValue<String> descriptionObservable = PojoProperties
                .value(CustomPageArchetypeConfiguration.class,
                        CustomPageArchetypeConfiguration.PAGE_DESCRIPTION_ATTRIBUTE, String.class)
                .observe((CustomPageArchetypeConfiguration) configuration);
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(descriptionText),
                descriptionObservable,
                updateValueStrategy().withValidator(new EmptyInputValidator(Messages.description)).create(),
                null);
    }

    protected void createNameControl(final Composite mainComposite, final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.name);
        final Text nameText = widgetFactory.newText(mainComposite);
        nameText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBOT_NAME_TEXT);
        IObservableValue<String> displayNameObservable = PojoProperties
                .value(CustomPageArchetypeConfiguration.class,
                        CustomPageArchetypeConfiguration.PAGE_DISPLAY_NAME_ATTRIBUTE, String.class)
                .observe((CustomPageArchetypeConfiguration) configuration);
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(nameText),
                displayNameObservable,
                updateValueStrategy().withValidator(new EmptyInputValidator(Messages.name)).create(),
                null);
    }

    protected IObservableValue<String> createArtifactIdControl(final Composite mainComposite,
            final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.projectName);
        final Text nameText = widgetFactory.newText(mainComposite);
        nameText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBOT_ARTIFACT_ID_TEXT);
        widgetFactory.createHintDecorator(nameText, SWT.LEFT, Messages.projectNameHint);

        final IObservableValue<String> projectNameObservable = PojoProperties
                .value(ExtensionProjectArchetypeConfiguration.class,
                        ExtensionProjectArchetypeConfiguration.PROJECT_NAME_ATTRIBUTE, String.class)
                .observe(configuration);
        var multivalidator = MultiValidatorFactory.multiValidator()
                .addValidator(new ArtifactIdValidator(workspace, Messages.projectName))
                .addValidator(value -> {
                    final Model model = new Model();
                    model.setArtifactId(value.toString());
                    model.setGroupId(configuration.getGroupId());
                    model.setVersion(configuration.getVersion());
                    return projectConfiguration.validateProjectName(model);
                }).create();
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(nameText),
                projectNameObservable,
                updateValueStrategy().withValidator(multivalidator).create(),
                null);
        return projectNameObservable;
    }

    protected IObservableValue<String> createPackageControl(final Composite mainComposite,
            final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.packageLabel);
        final Text groupIdText = widgetFactory.newText(mainComposite);
        groupIdText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBOT_PACKAGE_TEXT);

        final IObservableValue<String> packageObservable = PojoProperties
                .value(ExtensionProjectArchetypeConfiguration.class,
                        ExtensionProjectArchetypeConfiguration.JAVA_PACKAGE_ATTRIBUTE, String.class)
                .observe(configuration);
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(groupIdText),
                packageObservable,
                updateValueStrategy().withValidator(
                        p -> JavaConventions.validatePackageName((String) p, JavaCore.VERSION_17, JavaCore.VERSION_17))
                        .create(),
                null);
        return packageObservable;
    }

    protected IObservableValue<String> createClassNameControl(final Composite mainComposite,
            final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.classNameLabel);
        final Text groupIdText = widgetFactory.newText(mainComposite);
        groupIdText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBOT_CLASS_NAME_TEXT);

        final IObservableValue<String> packageObservable = PojoProperties
                .value(ArchetypeConfigurationWithClassName.class,
                        ArchetypeConfigurationWithClassName.CLASS_NAME_ATTRIBUTE, String.class)
                .observe((ArchetypeConfigurationWithClassName) configuration);
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(groupIdText),
                packageObservable,
                updateValueStrategy().withValidator(
                        c -> {
                            if (((String) c).contains(".")) {
                                return Status.error(Messages.classNameMustNotBeQualified);
                            } else {
                                return JavaConventions.validateJavaTypeName((String) c, JavaCore.VERSION_17,
                                        JavaCore.VERSION_17, JavaCore.DISABLED);
                            }
                        })
                        .create(),
                null);
        return packageObservable;
    }

    private void createLanguageControl(Composite mainComposite, DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.language);
        final Composite radioGroup = new Composite(mainComposite, SWT.NONE);
        radioGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        radioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());

        Button groovyButton = new Button(radioGroup, SWT.RADIO);
        groovyButton.setText(Messages.groovy);
        Button javaButton = new Button(radioGroup, SWT.RADIO);
        javaButton.setText(Messages.java);

        SelectObservableValue<String> languageSelectObservable = new SelectObservableValue<>();
        languageSelectObservable.addOption(RestAPIExtensionArchetypeConfiguration.GROOVY_LANGUAGE,
                WidgetProperties.buttonSelection().observe(groovyButton));
        languageSelectObservable.addOption(RestAPIExtensionArchetypeConfiguration.JAVA_LANGUAGE,
                WidgetProperties.buttonSelection().observe(javaButton));

        context.bindValue(languageSelectObservable,
                PojoProperties
                        .value(ArchetypeConfigurationWithLanguage.class,
                                ArchetypeConfigurationWithLanguage.LANGUAGE_ATTRIBUTE, String.class)
                        .observe((ArchetypeConfigurationWithLanguage) configuration));
    }

    public void setHelpLinkURL(String helpLinkURL) {
        this.helpLinkURL = helpLinkURL;
    }

}
