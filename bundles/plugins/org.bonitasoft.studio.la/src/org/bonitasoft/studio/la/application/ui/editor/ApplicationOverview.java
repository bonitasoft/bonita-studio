/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor;

import static org.bonitasoft.studio.la.application.ui.validator.ApplicationDescriptorValidators.appTokenValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.Application;
import org.bonitasoft.engine.business.application.ApplicationSearchDescriptor;
import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.la.application.core.ApplicationDescriptorNotFoundException;
import org.bonitasoft.studio.la.application.core.DeployApplicationDescriptorOperation;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationDescriptorValidators;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationTokenUnicityValidator;
import org.bonitasoft.studio.la.application.ui.validator.ProfileValidator;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class ApplicationOverview extends Composite implements IValueChangeListener {

    private static final int WIDGET_WIDTH_HINT = 400;
    protected ApplicationNodeContainerConverter applicationNodeContainerConverter = RepositoryManager.getInstance()
            .getRepositoryStore(ApplicationRepositoryStore.class).getConverter();

    private DataBindingContext ctx;
    protected ApplicationFormPage formPage;

    public ApplicationOverview(Composite parent, ApplicationFormPage formPage, ApplicationNode application,
            IObservableValue<String> tokenObservable) {
        super(parent, SWT.NONE);
        this.formPage = formPage;
        formPage.getToolkit().adapt(this);
        ctx = new DataBindingContext();
        setLayout(GridLayoutFactory.swtDefaults().extendedMargins(0, 0, 0, 25).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        buildToken(ctx, application, tokenObservable);
        buildDisplayName(ctx, application);
        buildVersion(ctx, application);
        buildProfile(ctx, application);
        buildDescription(ctx, application);
    }

    private void buildToken(final DataBindingContext ctx, ApplicationNode application,
            IObservableValue<String> tokenObservable) {
        ApplicationTokenUnicityValidator applicationTokenUnicityValidator = new ApplicationTokenUnicityValidator(
                formPage.getRepositoryAccessor(), formPage.getWorkingCopy(), formPage.getEditor().getEditorInput().getName(),
                tokenObservable);

        tokenObservable.addValueChangeListener(e -> {
            formPage.makeDirty();
            formPage.getEditor().doSave(new NullProgressMonitor());
        });

        new TextWidget.Builder()
                .withLabel(Messages.applicationToken)
                .labelAbove()
                .withMessage(Messages.applicationTokenMessage)
                .fill()
                .grabHorizontalSpace()
                .transactionalEdit(this::deleteAndDeployOldApp)
                .bindTo(tokenObservable)
                .withTargetToModelStrategy(UpdateStrategyFactory.convertUpdateValueStrategy())
                .withModelToTargetStrategy(UpdateStrategyFactory.updateValueStrategy())
                .withValidator(appTokenValidator(applicationTokenUnicityValidator))
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(this)
                .setText(application.getToken());
    }

    private void buildDisplayName(DataBindingContext ctx, ApplicationNode application) {
        IObservableValue<String> nameModelObservable = PojoProperties.<ApplicationNode, String> value("displayName")
                .observe(application);
        nameModelObservable.addValueChangeListener(this);

        new TextWidget.Builder()
                .withLabel(Messages.displayName)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(nameModelObservable)
                .withValidator(ApplicationDescriptorValidators.appDisplayNameValidator())
                .withDelay(500)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(this);

    }

    private void buildVersion(DataBindingContext ctx, ApplicationNode application) {
        IObservableValue<String> versionModelObservable = PojoProperties.<ApplicationNode, String> value("version")
                .observe(application);
        versionModelObservable.addValueChangeListener(this);

        new TextWidget.Builder()
                .withLabel(Messages.version)
                .labelAbove()
                .widthHint(100)
                .bindTo(versionModelObservable)
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(new EmptyInputValidator.Builder()
                                .withMessage(org.bonitasoft.studio.ui.i18n.Messages.required)
                                .create()))
                .withDelay(500)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(this);
    }

    private void buildProfile(DataBindingContext ctx, ApplicationNode application) {
        IObservableValue<String> profileModelObservable = PojoProperties.<ApplicationNode, String> value("profile")
                .observe(application);
        profileModelObservable.addValueChangeListener(this);

        Composite profileComposite = formPage.getToolkit().createComposite(this);
        profileComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        profileComposite.setLayoutData(GridDataFactory.fillDefaults().hint(WIDGET_WIDTH_HINT, SWT.DEFAULT).create());

        new TextWidget.Builder()
                .withLabel(Messages.profile)
                .labelAbove()
                .withMessage(Messages.messageProfile)
                .fill()
                .grabHorizontalSpace()
                .withProposalProvider(formPage.getApplicationEditorProviders().getProfileProposalProvider())
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy().withValidator(getProfileValidator()))
                .bindTo(profileModelObservable)
                .withDelay(500)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(profileComposite);

        createAddProfileButton(profileModelObservable, profileComposite);
    }

    protected ProfileValidator getProfileValidator() {
        return new ProfileValidator();
    }

    protected void createAddProfileButton(IObservableValue<String> profileModelObservable,
            Composite profileComposite) {
        new Label(profileComposite, SWT.NONE); // Filler, add button is only available in sp editions
    }

    private void buildDescription(DataBindingContext ctx, ApplicationNode application) {
        IObservableValue<String> descriptionModelObservable = PojoProperties
                .<ApplicationNode, String> value("description").observe(application);
        descriptionModelObservable.addValueChangeListener(this);

        new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .heightHint(100)
                .bindTo(descriptionModelObservable)
                .withDelay(500)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(this);
    }

    private void deleteAndDeployOldApp(String oldToken, String newToken) {
        if (!Objects.equals(oldToken, newToken)) {
            IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            GetApiSessionOperation apiSessionOperation = new GetApiSessionOperation();
            try {
                APISession apiSession = apiSessionOperation.execute();
                ApplicationAPI applicationAPI = BOSEngineManager.getInstance().getApplicationAPI(apiSession);
                Application application = applicationAPI.searchApplications(
                        new SearchOptionsBuilder(0, 1).filter(ApplicationSearchDescriptor.TOKEN, oldToken).done())
                        .getResult().stream()
                        .findFirst()
                        .orElseThrow(ApplicationDescriptorNotFoundException::new);

                if (MessageDialog.openQuestion(Display.getCurrent().getActiveShell(), Messages.redeployAfterRenameTitle,
                        String.format(Messages.redeployAfterRenameMessages, application.getDisplayName()))) {
                    formPage.getEditor().doSave(new NullProgressMonitor());
                    try {
                        applicationAPI.deleteApplication(application.getId());
                    } catch (DeletionException e) {
                        new ExceptionDialogHandler().openErrorDialog(Display.getCurrent().getActiveShell(),
                                Messages.undeployFailed, e);
                    }
                    ApplicationFileStore appFileStore = formPage.getRepositoryAccessor()
                            .getRepositoryStore(ApplicationRepositoryStore.class)
                            .getChild(formPage.getEditor().getEditorInput().getName(), true);
                    appFileStore.getContent().getApplications().stream()
                            .filter(appNode -> Objects.equals(appNode.getToken(), newToken))
                            .map(appNode -> {
                                ApplicationNodeContainer applicationNodeContainer = new ApplicationNodeContainer();
                                applicationNodeContainer.addApplication(appNode);
                                return applicationNodeContainer;
                            })
                            .map(appNode -> createDeployApplicationOperation(applicationAPI, appNode))
                            .forEach(op -> {
                                try {
                                    progressService.run(true, false, op);
                                    MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
                                            Messages.redeployDoneTitle,
                                            String.format(Messages.redeployDoneMessage, application.getDisplayName()));
                                } catch (InvocationTargetException | InterruptedException e) {
                                    new ExceptionDialogHandler().openErrorDialog(Display.getCurrent().getActiveShell(),
                                            org.bonitasoft.studio.la.i18n.Messages.deployFailedTitle, e);
                                }
                            });

                }
            } catch (SearchException | ApplicationDescriptorNotFoundException | BonitaHomeNotSetException
                    | ServerAPIException | UnknownAPITypeException | ReadFileStoreException e) {
                // app not deployed yet, nothing to do
            } finally {
                apiSessionOperation.logout();
            }
        }
    }

    protected DeployApplicationDescriptorOperation createDeployApplicationOperation(ApplicationAPI applicationAPI,
            ApplicationNodeContainer appNode) {
        return new DeployApplicationDescriptorOperation(applicationAPI, appNode, applicationNodeContainerConverter);
    }

    public void updateTargets() {
        ctx.updateTargets();
    }

    @Override
    public void handleValueChange(ValueChangeEvent event) {
        formPage.makeDirty();
    }

}
