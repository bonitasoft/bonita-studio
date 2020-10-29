/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.GroupIdValidator;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.CustomPageArchetypeConfiguration;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.validator.ArtifactIdValidator;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
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

public class NewCustomPageArtifactConfigurationPage extends WizardPage {

    private final CustomPageArchetypeConfiguration configuration;
    private final WidgetFactory widgetFactory;
    private final MavenProjectConfiguration projectConfiguration;
    private final IWorkspace workspace;
    private String helpLinkURL;

    public NewCustomPageArtifactConfigurationPage(WidgetFactory widgetFactory,
            CustomPageArchetypeConfiguration configuration,
            MavenProjectConfiguration projectConfiguration,
            IWorkspace workspace) {
        super(NewCustomPageArtifactConfigurationPage.class.getName());
        this.configuration = configuration;
        this.widgetFactory = widgetFactory;
        this.projectConfiguration = projectConfiguration;
        this.workspace = workspace;
    }

    @Override
    public void createControl(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final DataBindingContext context = new DataBindingContext();
        WizardPageSupport.create(this, context);

        if (helpLinkURL != null) {
            Link link = new Link(mainComposite, SWT.NONE);
            link.setText(String.format(Messages.getMoreInfoInDocumentation, configuration.getArtifactLabel()));
            link.addListener(SWT.Selection, event -> openBrowser(helpLinkURL));
        }

        createPortalGroup(mainComposite, context);
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
        mavenGroup.setText("Project");
        mavenGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());
        mavenGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final IObservableValue groupIdObservable = createGroupIdControl(mavenGroup, context);
        final IObservableValue apiNameObservable = createArtifactIdControl(mavenGroup, context);
        final IObservableValue vesionObservable = createVersionControl(mavenGroup, context);
        
        if(configuration instanceof RestAPIExtensionArchetypeConfiguration) {
            createLanguageControl(mavenGroup, context);
        }
        
        context.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                final Model model = new Model();
                model.setArtifactId((String) apiNameObservable.getValue());
                model.setGroupId((String) groupIdObservable.getValue());
                model.setVersion((String) vesionObservable.getValue());
                return projectConfiguration.validateProjectName(model);
            }

        });
    }

    protected void createPortalGroup(final Composite mainComposite, final DataBindingContext context) {
        final Group portalGroup = new Group(mainComposite, SWT.NONE);
        portalGroup.setText(Messages.portal);
        portalGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());
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
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(descriptionText),
                PojoProperties.value("pageDescription").observe(configuration),
                updateValueStrategy().withValidator(new EmptyInputValidator(Messages.description)).create(),
                null);
    }

    protected void createNameControl(final Composite mainComposite, final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.name);
        final Text nameText = widgetFactory.newText(mainComposite);
        nameText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.rest.api.extension.ui.wizard.nameText");
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(nameText),
                PojoProperties.value("pageDisplayName").observe(configuration),
                updateValueStrategy().withValidator(new EmptyInputValidator(Messages.name)).create(),
                null);
    }

    protected IObservableValue createVersionControl(final Composite mainComposite, final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.version);
        final Text versionText = widgetFactory.newText(mainComposite);
        final IObservableValue vesionObservable = PojoProperties.value("version").observe(configuration);
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(versionText),
                vesionObservable,
                updateValueStrategy().withValidator(mandatoryValidator(Messages.version)).create(),
                null);
        return vesionObservable;
    }

    protected IObservableValue createArtifactIdControl(final Composite mainComposite,
            final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.projectName);
        final Text nameText = widgetFactory.newText(mainComposite);
        nameText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.rest.api.extension.ui.wizard.artifactIdText");
        widgetFactory.createHintDecorator(nameText, SWT.LEFT, Messages.projectNameHint);

        final IObservableValue apiNameObservable = PojoProperties.value("pageName").observe(configuration);
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(nameText),
                apiNameObservable,
                updateValueStrategy().withValidator(new ArtifactIdValidator(workspace, Messages.projectName)).create(),
                null);
        return apiNameObservable;
    }

    protected IObservableValue createGroupIdControl(final Composite mainComposite, final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.groupId);
        final Text groupIdText = widgetFactory.newText(mainComposite);
        groupIdText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.rest.api.extension.ui.wizard.groupIdText");
        widgetFactory.createHintDecorator(groupIdText, SWT.LEFT, Messages.groupIdHint);

        final IObservableValue groupIdObservable = PojoProperties.value("groupId").observe(configuration);
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(groupIdText),
                groupIdObservable,
                updateValueStrategy().withValidator(new GroupIdValidator(workspace)).create(),
                null);
        return groupIdObservable;
    }
    
    private void createLanguageControl(Composite mainComposite, DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.language);
        final Composite radioGroup = new Composite(mainComposite, SWT.NONE);
        radioGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        radioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        
        Button javaButton = new Button(radioGroup, SWT.RADIO);
        javaButton.setText(Messages.java);
        Button groovyButton = new Button(radioGroup, SWT.RADIO);
        groovyButton.setText(Messages.groovy);
        
        SelectObservableValue<String> languageSelectObservable = new SelectObservableValue<>();
        languageSelectObservable.addOption(RestAPIExtensionArchetypeConfiguration.JAVA_LANGUAGE, WidgetProperties.buttonSelection().observe(javaButton));
        languageSelectObservable.addOption(RestAPIExtensionArchetypeConfiguration.GROOVY_LANGUAGE, WidgetProperties.buttonSelection().observe(groovyButton));
       
        context.bindValue(languageSelectObservable, PojoProperties.value("language", String.class).observe(configuration));
    }

    public void setHelpLinkURL(String helpLinkURL) {
        this.helpLinkURL = helpLinkURL;
    }

}
