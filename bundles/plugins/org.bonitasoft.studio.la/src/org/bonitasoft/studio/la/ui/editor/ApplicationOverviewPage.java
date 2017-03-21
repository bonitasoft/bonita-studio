/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.editor;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.browser.operation.OpenBrowserOperation;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.ui.editor.theme.ThemeDescriptor;
import org.bonitasoft.studio.la.ui.validator.ApplicationTokenUnicityValidator;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.RegExpValidator;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.xml.sax.SAXException;

public class ApplicationOverviewPage extends FormPage {

    private FormToolkit toolkit;
    private ScrolledForm form;
    private ApplicationNodeContainer applicationWorkingCopy;
    private IDocument document;
    private final ApplicationNodeContainerConverter applicationNodeContainerConverter;
    private final RepositoryAccessor repositoryAccessor;

    public ApplicationOverviewPage(String id, String title) {
        super(id, title);
        applicationNodeContainerConverter = new ApplicationNodeContainerConverter();
        this.repositoryAccessor = repositoryAccessor();
    }

    public void init(ApplicationNodeContainer applicationWorkingCopy, IDocument document) {
        this.applicationWorkingCopy = applicationWorkingCopy;
        this.document = document;
    }

    protected RepositoryAccessor repositoryAccessor() {
        final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        return repositoryAccessor;
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        toolkit = managedForm.getToolkit();
        form = managedForm.getForm();
        toolkit.decorateFormHeading(form.getForm());
        form.getBody().setLayout(GridLayoutFactory.swtDefaults().create());
        form.getBody().setLayoutData(GridDataFactory.fillDefaults().create());
        form.setText(getEditorInput().getName());
        form.getToolBarManager().add(new DeployContributionItem(getEditor()));
        form.getToolBarManager().add(new DeleteContributionItem(getEditor()));
        form.getToolBarManager().update(true);

        applicationWorkingCopy.getApplications().stream()
                .forEach(application -> buildApplicationComposite(application, form.getBody()));
    }

    public void update() {
        if (form != null) {
            Stream.of(form.getBody().getChildren()).forEach(Control::dispose);
            applicationWorkingCopy.getApplications().stream()
                    .forEach(application -> buildApplicationComposite(application, form.getBody()));
        }
    }

    protected void buildApplicationComposite(ApplicationNode application, Composite parent) {
        final Section applicationSection = toolkit.createSection(parent, Section.TWISTIE | Section.TITLE_BAR);
        final Menu menu = new Menu(applicationSection);
        final MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
        menuItem.setText("Open in browser");
        applicationSection.setMenu(menu);

        final ToolBar toolBar = new ToolBar(applicationSection, SWT.RIGHT);
        final ToolItem toolItem = new ToolItem(toolBar, SWT.PUSH);
        toolItem.setToolTipText("Open in browser");
        toolItem.setImage(LivingApplicationPlugin.getImage("icons/applicationStore.gif"));
        toolItem.addListener(SWT.Selection, openBrowser(application));
        applicationSection.setTextClient(toolBar);
        applicationSection.setText(application.getToken());
        applicationSection.setExpanded(true);

        applicationSection.setLayout(GridLayoutFactory.fillDefaults().create());
        applicationSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        applicationSection.setClient(createApplicationClient(applicationSection, application));
    }

    private Listener openBrowser(ApplicationNode application) {
        return event -> new OpenBrowserOperation(toURL(application)).execute();
    }

    private URL toURL(ApplicationNode application) {
        try {
            return new URL("http://localhost:8080/bonita/apps/" + application.getToken()); //TODO retireve host and port from preferences
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private Control createApplicationClient(Section applicationGroup, ApplicationNode application) {
        final DataBindingContext ctx = new DataBindingContext();

        final ApplicationTokenUnicityValidator applicationTokenUnicityValidator = new ApplicationTokenUnicityValidator(
                repositoryAccessor, application.getToken());

        final Composite container = toolkit.createComposite(applicationGroup);
        container.setLayout(GridLayoutFactory.swtDefaults().create());
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final IObservableValue nameModelObservable = PojoObservables.observeValue(application, "displayName");
        nameModelObservable.addValueChangeListener(this::updateFile);

        final IObservableValue versionModelObservable = PojoObservables.observeValue(application, "version");
        versionModelObservable.addValueChangeListener(this::updateFile);

        final IObservableValue descriptionModelObservable = PojoObservables.observeValue(application, "description");
        descriptionModelObservable.addValueChangeListener(this::updateFile);

        final IObservableValue profileModelObservable = PojoObservables.observeValue(application, "profile");
        profileModelObservable.addValueChangeListener(this::updateFile);

        final IObservableValue urlModelObservable = PojoObservables.observeValue(application, "token");
        urlModelObservable.addValueChangeListener(e -> {
            updateFile(e);
            applicationGroup.setText(application.getToken());
            applicationTokenUnicityValidator.setCurrentToken(application.getToken());
        });

        final IObservableValue layoutModelObservable = PojoObservables.observeValue(application, "layout");
        layoutModelObservable.addValueChangeListener(this::updateFile);

        final IObservableValue themeModelObservable = PojoObservables.observeValue(application, "theme");
        themeModelObservable.addValueChangeListener(this::updateFile);

        final Composite urlComposite = new Composite(container, SWT.NONE);
        urlComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 0).create());
        urlComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        final Label urlLabel = toolkit.createLabel(urlComposite, Messages.url);
        urlLabel.setLayoutData(GridDataFactory.fillDefaults().create());

        new TextWidget.Builder()
                .withLabel("../apps/")
                .withMessage(Messages.applicationTokenMessage)
                .widthHint(500)
                .bindTo(urlModelObservable)
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(new MultiValidator.Builder().havingValidators(
                                new EmptyInputValidator.Builder().withMessage(Messages.required).create(),
                                new RegExpValidator.Builder().matches("^[a-zA-Z0-9]+$")
                                        .withMessage(Messages.tokenValidatorMessage).create(),
                                applicationTokenUnicityValidator)))
                .withModelToTargetStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(
                                new MultiValidator.Builder().havingValidators(
                                        new EmptyInputValidator.Builder().withMessage(Messages.required).create(),
                                        new RegExpValidator.Builder().matches("^[a-zA-Z0-9]+$")
                                                .withMessage(Messages.tokenValidatorMessage).create(),
                                        applicationTokenUnicityValidator)))
                .inContext(ctx)
                .createIn(urlComposite)
                .adapt(toolkit)
                .setLabelColor(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY));

        new TextWidget.Builder()
                .withLabel(Messages.displayName)
                .labelAbove()
                .widthHint(500)
                .bindTo(nameModelObservable)
                .withDelay(500)
                .inContext(ctx)
                .createIn(container)
                .adapt(toolkit);

        new TextWidget.Builder()
                .withLabel(Messages.version)
                .labelAbove()
                .widthHint(100)
                .bindTo(versionModelObservable)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new EmptyInputValidator.Builder().withMessage(Messages.required).create()))
                .withDelay(500)
                .inContext(ctx)
                .createIn(container)
                .adapt(toolkit);

        final String[] profiles = { "Process manager", "User", "Administrator" };

        new ComboWidget.Builder()
                .withLabel(Messages.profile)
                .labelAbove()
                .withMessage(Messages.customProfile)
                .widthHint(400)
                .horizontalSpan(2)
                .withItems(profiles)
                .bindTo(profileModelObservable)
                .inContext(ctx)
                .createIn(container)
                .adapt(toolkit);

        new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .widthHint(500)
                .heightHint(100)
                .bindTo(descriptionModelObservable)
                .withDelay(500)
                .inContext(ctx)
                .createIn(container)
                .adapt(toolkit);

        final Group lookNFeelGroup = new Group(container, SWT.NONE);
        lookNFeelGroup.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).spacing(10, 10).create());
        lookNFeelGroup.setLayoutData(GridDataFactory.fillDefaults().create());
        lookNFeelGroup.setText(Messages.lookNFeel);

        final String[] layouts = { "custompage_defaultlayout" };

        new ComboWidget.Builder()
                .withLabel(Messages.layout)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .withItems(layouts)
                .bindTo(layoutModelObservable)
                .inContext(ctx)
                .createIn(lookNFeelGroup)
                .adapt(toolkit);

        new ComboWidget.Builder()
                .withLabel(Messages.theme)
                .withMessage(Messages.themeMessage)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .withItems(availableThemes())
                .bindTo(themeModelObservable)
                .withModelToTargetStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<String, String> newConverter()
                                .fromType(String.class)
                                .toType(String.class)
                                .withConvertFunction(ThemeDescriptor::fromIdToName)
                                .create()))
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<String, String> newConverter()
                                .fromType(String.class)
                                .toType(String.class)
                                .withConvertFunction(ThemeDescriptor::fromNameToId)
                                .create()))
                .inContext(ctx)
                .createIn(lookNFeelGroup)
                .adapt(toolkit);

        return container;
    }

    private String[] availableThemes() {
        return ThemeDescriptor.DEFAULT_THEMES.stream()
                .map(ThemeDescriptor::getDisplayName)
                .toArray(size -> new String[size]);
    }

    private void updateFile(ValueChangeEvent e) {
        try {
            document.set(new String(applicationNodeContainerConverter.marshallToXML(applicationWorkingCopy)));
        } catch (JAXBException | IOException | SAXException e1) {
            throw new RuntimeException("Fail to update the document", e1);
        }
    }

    public void reflow() {
        form.reflow(true);
    }

}
