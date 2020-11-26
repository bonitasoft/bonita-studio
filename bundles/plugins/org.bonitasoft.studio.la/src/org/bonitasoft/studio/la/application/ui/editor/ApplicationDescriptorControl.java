/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.business.application.ApplicationSearchDescriptor;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.page.PageSearchDescriptor;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.operation.ApplicationURLBuilder;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.core.ApplicationDependencyResolver;
import org.bonitasoft.studio.la.application.handler.DeployApplicationHandler;
import org.bonitasoft.studio.la.application.ui.editor.listener.CloneApplicationDescriptorListener;
import org.bonitasoft.studio.la.application.ui.editor.listener.DeleteApplicationDescriptorListener;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class ApplicationDescriptorControl {

    private final Image homeIcon = LivingApplicationPlugin.getImage("icons/home.png");
    private final Image warningIcon = JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
    private final ApplicationOverview overview;
    protected IObservableValue<String> tokenObservable;
    private Section control;
    private ApplicationNode application;

    public ApplicationDescriptorControl(Section applicationSection, ApplicationNode application,
            ApplicationFormPage formPage) {

        this.control = applicationSection;
        this.application = application;
        final DataBindingContext ctx = new DataBindingContext();

        final FormToolkit toolkit = formPage.getToolkit();
        final ToolBar toolBar = new ToolBar(applicationSection, SWT.FLAT | SWT.RIGHT);

        final ToolItem cloneToolItem = new ToolItem(toolBar, SWT.PUSH);
        cloneToolItem.setToolTipText(Messages.duplicateApplicationDescriptor);
        Image cloneImage = PreferenceUtil.isDarkTheme()
                ? Pics.getImage(PicsConstants.duplicate_16_dark)
                : Pics.getImage(PicsConstants.duplicate_16);
        cloneToolItem.setImage(cloneImage);
        cloneToolItem.addListener(SWT.Selection, new CloneApplicationDescriptorListener(application, formPage));
        cloneToolItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.la.ui.editor.clone." + application.getToken());
        final ToolItem deleteToolItem = new ToolItem(toolBar, SWT.PUSH);
        deleteToolItem.setToolTipText(Messages.deleteApplicationDescriptor);
        Image deleteImage = PreferenceUtil.isDarkTheme()
                ? Pics.getImage(PicsConstants.bin_16_dark)
                : Pics.getImage(PicsConstants.bin_16);
        deleteToolItem.setImage(deleteImage);
        deleteToolItem.addListener(SWT.Selection,
                new DeleteApplicationDescriptorListener(application, formPage));
        deleteToolItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.la.ui.editor.delete." + application.getToken());

        applicationSection.setTextClient(toolBar);

        tokenObservable = PojoProperties.<ApplicationNode, String> value("token").observe(application);
        ctx.bindValue(PojoProperties.value("text").observe(applicationSection), tokenObservable);
        applicationSection.setLayout(GridLayoutFactory.fillDefaults().create());
        applicationSection
                .setLayoutData(
                        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false).create());

        Composite client = toolkit.createComposite(applicationSection);
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 5).spacing(40, 0).create());
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Section overviewSection = toolkit.createSection(client, Section.EXPANDED);
        overviewSection.setLayout(GridLayoutFactory.fillDefaults().create());
        overviewSection
                .setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(480, SWT.DEFAULT).create());
        overviewSection.setText(Messages.overview);

        Composite previewComposite = toolkit.createComposite(overviewSection);
        previewComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        previewComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        Label image = toolkit.createLabel(previewComposite, "", SWT.None);

        IObservableValue<Image> imageObservable = PojoProperties.<Label, Image> value("image").observe(image);
        IObservableValue<String> homePageObservable = PojoProperties.<ApplicationNode, String> value("homePage")
                .observe(application);
        IObservableValue<String> imageToolTipObservable = PojoProperties.<Label, String> value("toolTipText")
                .observe(image);

        Link link = new Link(previewComposite, SWT.None);
        link.setText(getUrlToDisplay(application.getToken()));
        link.addListener(SWT.Selection, e -> preview(formPage, overviewSection, application));
        formPage.getToolkit().adapt(link, true, true);

        UpdateValueStrategy updateUrlStrategy = updateValueStrategy()
                .withConverter(ConverterBuilder.<String, String> newConverter()
                        .fromType(String.class)
                        .toType(String.class)
                        .withConvertFunction(this::getUrlToDisplay)
                        .create())
                .create();

        IObservableValue observeLinkText = PojoProperties.value("text").observe(link);
        observeLinkText.addValueChangeListener(e -> previewComposite.layout());
        ctx.bindValue(observeLinkText, tokenObservable, null, updateUrlStrategy);

        overviewSection.setDescriptionControl(previewComposite);
        overview = createApplicationOverview(application, formPage, overviewSection);
        overviewSection.setClient(overview);

        Section navigationSection = toolkit.createSection(client, Section.EXPANDED | Section.DESCRIPTION);
        navigationSection.setLayout(GridLayoutFactory.fillDefaults().create());
        navigationSection.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).hint(1000, SWT.DEFAULT).span(1, 2).create());
        navigationSection.setText(Messages.navigation);
        navigationSection.setDescription(Messages.navigationSectionDescription);
        ApplicationNavigation applicationNavigation = new ApplicationNavigation(navigationSection,
                formPage,
                application,
                toolkit,
                formPage.getRepositoryAccessor().getRepositoryStore(WebPageRepositoryStore.class),
                formPage.getRepositoryAccessor().getRepositoryStore(ThemeRepositoryStore.class),
                homePageObservable);
        navigationSection.setClient(applicationNavigation);

        applicationSection.setClient(client);

        final Section lookNFeelSection = toolkit.createSection(client, Section.EXPANDED);
        lookNFeelSection.setLayout(GridLayoutFactory.fillDefaults().create());
        lookNFeelSection
                .setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        lookNFeelSection.setText(Messages.lookNFeel);

        lookNFeelSection.setClient(new ApplicationLookNFeel(lookNFeelSection, toolkit, application, formPage));

        ctx.bindValue(imageObservable, homePageObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(ConverterBuilder.<String, Image> newConverter()
                                .fromType(String.class)
                                .toType(Image.class)
                                .withConvertFunction(
                                        s -> applicationNavigation.validateHomePageToken(s).isOK() ? homeIcon
                                                : warningIcon)
                                .create())
                        .create());
        ctx.bindValue(imageToolTipObservable, homePageObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(ConverterBuilder.<String, String> newConverter()
                                .fromType(String.class)
                                .toType(String.class)
                                .withConvertFunction(
                                        s -> applicationNavigation.validateHomePageToken(s).isOK() ? ""
                                                : Messages.previewWarning)
                                .create())
                        .create());
        ctx.bindValue(WidgetProperties.enabled().observe(link), homePageObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(ConverterBuilder.<String, Boolean> newConverter()
                                .fromType(String.class)
                                .toType(Boolean.class)
                                .withConvertFunction(s -> applicationNavigation.validateHomePageToken(s).isOK())
                                .create())
                        .create());
    }

    protected ApplicationOverview createApplicationOverview(ApplicationNode application, ApplicationFormPage formPage,
            Section overviewSection) {
        return new ApplicationOverview(overviewSection, formPage, application, tokenObservable);
    }

    public void updateTargets() {
        overview.updateTargets();
    }

    private void preview(ApplicationFormPage formPage, Section overviewSection, ApplicationNode application) {
        if (needsToBeDeployed(application)) {
            openDeployWizard(formPage, overviewSection);
        } else {
            new OpenBrowserOperation(toURL(application)).execute();
        }
    }

    protected void openDeployWizard(ApplicationFormPage formPage, Section overviewSection) {
        new DeployApplicationHandler().execute(formPage.getRepositoryAccessor(), overviewSection.getShell(),
                formPage.getEditorInput().getName(), Boolean.TRUE.toString(), new ApplicationDependencyResolver());
    }

    private boolean needsToBeDeployed(ApplicationNode application) {
        APISession apiSession = null;
        try {
            apiSession = BOSEngineManager.getInstance().loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
            ApplicationAPI applicationAPI = BOSEngineManager.getInstance().getApplicationAPI(apiSession);
            PageAPI pageAPI = BOSEngineManager.getInstance().getPageAPI(apiSession);
            return !(applicationAPI.searchApplications(new SearchOptionsBuilder(0, 1)
                    .filter(ApplicationSearchDescriptor.TOKEN, application.getToken()).done())
                    .getCount() > 0 &&
                    pageAPI.searchPages(new SearchOptionsBuilder(0, 1)
                            .filter(PageSearchDescriptor.NAME, resolveToken(application, application.getHomePage())).done())
                            .getCount() > 0);
        } catch (LoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException
                | SearchException e) {
            BonitaStudioLog.error(e);
        } finally {
            if (apiSession != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(apiSession);
            }
        }
        return true;
    }

    private String resolveToken(ApplicationNode applicationNode, String pageToken) {
        return applicationNode.getApplicationPages().stream()
                .filter(pageNode -> Objects.equals(pageNode.getToken(), pageToken))
                .map(ApplicationPageNode::getCustomPage)
                .findFirst()
                .orElse(null);
    }

    private String getUrlToDisplay(String token) {
        return String.format("<a>%s/bonita/apps/%s</a>", BOSWebServerManager.getInstance().generateUrlBase(), token);
    }

    public IObservableValue<String> getTokenObservable() {
        return tokenObservable;
    }

    private URL toURL(ApplicationNode application) {
        try {
            return new ApplicationURLBuilder(application.getToken()).toURL(new NullProgressMonitor());
        } catch (final MalformedURLException | UnsupportedEncodingException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Section getControl() {
        return control;
    }

    public ApplicationNode getApplication() {
        return application;
    }

}
