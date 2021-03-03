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
package org.bonitasoft.studio.application.views.extension;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaMarketplace;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

public class ProjectExtensionEditorPart extends EditorPart {

    public static final String ID = "org.bonitasoft.studio.application.extension.editor";

    private static final String OPEN_MARKETPLACE_COMMAND = "org.bonitasoft.studio.application.marketplace.command";

    private RepositoryAccessor repositoryAccessor;
    private MavenProjectHelper mavenHelper;
    private CommandExecutor commandExecutor;
    private List<BonitaArtifactDependency> allDependencies;
    private Font titleFont;
    private Font subtitleFont;
    private Font gavFont;
    private Composite cardComposite;
    private IThemeEngine engine;

    public ProjectExtensionEditorPart() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        mavenHelper = new MavenProjectHelper();
        commandExecutor = new CommandExecutor();
        engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof ProjectExtensionEditorInput)) {
            throw new PartInitException("Invalid Input: Must be ProjectExtensionEditorInput");
        }
        setSite(site);
        setInput(input);
    }

    @Override
    public void createPartControl(Composite parent) {
        initVariables();

        Composite mainComposite = createComposite(parent, SWT.NONE); // TODO scroll composite ?
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 20).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 800).create());

        createTitleAndToolbar(mainComposite);

        Label separator = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createExtensionSection(mainComposite);
    }

    private void createExtensionSection(Composite parent) {
        cardComposite = createComposite(parent, SWT.NONE);
        cardComposite.setLayout(GridLayoutFactory.fillDefaults().margins(40, 20)
                .spacing(20, 20).numColumns(2).equalWidth(true).create());
        cardComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createExtensionCards(cardComposite);
    }

    private void createExtensionCards(Composite parent) {
        try {
            List<Dependency> userDependencies = mavenHelper
                    .getMavenModel(repositoryAccessor.getCurrentRepository().getProject())
                    .getDependencies();
            userDependencies.forEach(dep -> {
                allDependencies.stream()
                        .filter(bonitaDep -> sameDependency(dep, bonitaDep))
                        .findFirst().ifPresent(bonitaDep -> createCard(parent, dep, bonitaDep));
            });
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }
    }

    private void createCard(Composite parent, Dependency dep, BonitaArtifactDependency bonitaDep) {
        Composite cardComposite = new Composite(parent, SWT.BORDER);
        cardComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        cardComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        cardComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        Composite titleComposite = new Composite(cardComposite, SWT.NONE);
        titleComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        Label iconLabel = new Label(cardComposite, SWT.NONE);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER)
                .hint(BonitaMarketplacePage.ICON_SIZE, BonitaMarketplacePage.ICON_SIZE)
                .span(1, 3)
                .create());
        iconLabel.setImage(bonitaDep.getIconImage());

        Label title = new Label(titleComposite, SWT.WRAP);
        title.setLayoutData(GridDataFactory.fillDefaults().create());
        title.setText(bonitaDep.getName());
        title.setFont(subtitleFont);
        title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        Label gav = new Label(titleComposite, SWT.WRAP);
        gav.setLayoutData(GridDataFactory.fillDefaults().indent(5, 0).create());
        gav.setText(String.format("%s:%s:%s", dep.getGroupId(), dep.getArtifactId(), dep.getVersion()));
        gav.setFont(gavFont);
        gav.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TOOLBAR_TEXT_COLOR);

        Label description = new Label(titleComposite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().indent(0, 10).grab(true, false).create());
        description.setText(bonitaDep.getDescription());

        Label separator = new Label(cardComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        ToolBar toolBar = new ToolBar(cardComposite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        toolBar.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).align(SWT.END, SWT.FILL).create());
        toolBar.setLayout(GridLayoutFactory.fillDefaults().create());

        ToolItem deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setHotImage(Pics.getImage(PicsConstants.delete_hot));
        deleteItem.setToolTipText(Messages.removeExtensionTooltip);
        deleteItem.addListener(SWT.Selection, e -> removeExtension(dep, bonitaDep));

        MouseTrackAdapter cardMouseTracker = new MouseTrackAdapter() {

            @Override
            public void mouseEnter(MouseEvent e) {
                title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.CARD_HIGHLIGHT_TITLE_COLOR);
                engine.applyStyles(title, false);
            }

            @Override
            public void mouseExit(MouseEvent e) {
                title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
                engine.applyStyles(title, false);
            }
        };

        cardComposite.addMouseTrackListener(cardMouseTracker);
        titleComposite.addMouseTrackListener(cardMouseTracker);
        title.addMouseTrackListener(cardMouseTracker);
        gav.addMouseTrackListener(cardMouseTracker);
        description.addMouseTrackListener(cardMouseTracker);
        iconLabel.addMouseTrackListener(cardMouseTracker);
    }

    private void removeExtension(Dependency dep, BonitaArtifactDependency bonitaDep) {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.removeExtensionConfirmationTitle,
                String.format(Messages.removeExtensionConfirmation, bonitaDep.getName()))) {
            RemoveDependencyOperation operation = new RemoveDependencyOperation(dep.getGroupId(), dep.getArtifactId(),
                    dep.getVersion());
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                    try {
                        operation.run(monitor);
                        refreshCards();
                    } catch (CoreException e) {
                        throw new InvocationTargetException(e);
                    }
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void refreshCards() {
        Display.getDefault().asyncExec(() -> {
            Arrays.asList(cardComposite.getChildren()).forEach(Control::dispose);
            createExtensionCards(cardComposite);
            cardComposite.layout();
        });
    }

    private boolean sameDependency(Dependency dep, BonitaArtifactDependency bonitaDep) {
        return Objects.equals(dep.getGroupId(), bonitaDep.getGroupId())
                && Objects.equals(dep.getArtifactId(), bonitaDep.getArtifactId());
    }

    private void createTitleAndToolbar(Composite parent) {
        Composite composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Label title = new Label(composite, SWT.NONE);
        title.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        title.setText(repositoryAccessor.getCurrentRepository().getName());
        title.setFont(titleFont);
        title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        title.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        Composite toolbarsComposite = createComposite(composite, SWT.NONE);
        toolbarsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        toolbarsComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        createMarketplaceButton(toolbarsComposite);
    }

    private void createMarketplaceButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withText(Messages.openMarketplace)
                .withMaxTextWidth(150)
                .withTooltipText(Messages.openMarketplaceTooltip)
                .withImage(Pics.getImage(PicsConstants.openMarketplace))
                .withHotImage(Pics.getImage(PicsConstants.openMarketplaceHot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> {
                    commandExecutor.executeCommand(OPEN_MARKETPLACE_COMMAND, null);
                    refreshCards();
                })
                .createIn(parent);
    }

    private void initVariables() {
        titleFont = new Font(Display.getDefault(), "titleFont", 30, SWT.BOLD);
        subtitleFont = new Font(Display.getDefault(), "subtitleFont", 20, SWT.BOLD);
        gavFont = new Font(Display.getDefault(), "gavFont", 10, SWT.ITALIC);

        allDependencies = BonitaMarketplace.getInstance().getDependencies();
    }

    private Composite createComposite(Composite parent, int style) {
        Composite composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        return composite;
    }

    @Override
    public void setFocus() {
    }

    @Override
    public void dispose() {
        titleFont.dispose();
        subtitleFont.dispose();
        gavFont.dispose();
        super.dispose();
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // do nothing
    }

    @Override
    public void doSaveAs() {
        // do nothing
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public Image getTitleImage() {
        return Pics.getImage(PicsConstants.openExtensions);
    }

}
