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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependencyConverter;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaMarketplace;
import org.bonitasoft.studio.application.views.extension.card.ExtensionCard;
import org.bonitasoft.studio.application.views.extension.card.ExtensionCardFactory;
import org.bonitasoft.studio.application.views.extension.card.zoom.IZoomable;
import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class ProjectExtensionEditorPart extends EditorPart implements IResourceChangeListener {

    public static final String ID = "org.bonitasoft.studio.application.extension.editor";

    public static final String BOLD_20_FONT_ID = "bold20_bonita";
    public static final String BOLD_8_FONT_ID = "bold8_bonita";
    public static final String BOLD_4_FONT_ID = "bold4_bonita";
    public static final String BOLD_0_FONT_ID = "bold0_bonita";
    public static final String ITALIC_2_FONT_ID = "italic2_bonita";
    public static final String ITALIC_0_FONT_ID = "italic0_bonita";
    public static final String NORMAL_10_FONT_ID = "normal10_bonita";
    public static final String NORMAL_4_FONT_ID = "normal4_bonita";

    public static final String OPEN_MARKETPLACE_COMMAND = "org.bonitasoft.studio.application.marketplace.command";
    public static final String IMPORT_EXTENSION_COMMAND = "org.bonitasoft.studio.application.import.extension.command";
    private static final String EDIT_PROJECT_COMMAND = "org.bonitasoft.studio.application.edit.project.command";

    private RepositoryAccessor repositoryAccessor;
    private MavenProjectHelper mavenHelper;
    private CommandExecutor commandExecutor;
    private List<BonitaArtifactDependency> allDependencies;
    private Cursor cursorHand;
    private Cursor cursorArrow;
    private ScrolledComposite scrolledComposite;
    private Composite cardComposite;
    private DataBindingContext ctx;
    private BonitaArtifactDependencyConverter bonitaArtifactDependencyConverter;
    private Label description;
    private StyledText title;
    private LocalResourceManager localResourceManager;
    private Composite mainComposite;
    private Composite toolbarComposite;
    private Composite titleComposite;
    private ExceptionDialogHandler errorHandler;
    private UpdateExtensionListener upadateExtensionListener;

    private RemoveExtensionListener removeExtensionListener;

    public ProjectExtensionEditorPart() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(Display.getDefault()));
        mavenHelper = new MavenProjectHelper();
        ctx = new DataBindingContext();
        bonitaArtifactDependencyConverter = new BonitaArtifactDependencyConverter(
                repositoryAccessor.getCurrentRepository().getProjectDependenciesStore());
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof ProjectExtensionEditorInput)) {
            throw new PartInitException("Invalid Input: Must be ProjectExtensionEditorInput");
        }
        var eclipseContext = EclipseContextFactory.create();
        errorHandler = ContextInjectionFactory
                .make(ExceptionDialogHandler.class, eclipseContext);
        commandExecutor = ContextInjectionFactory
                .make(CommandExecutor.class, eclipseContext);
        upadateExtensionListener = ContextInjectionFactory
                .make(UpdateExtensionListener.class, eclipseContext);
        removeExtensionListener = ContextInjectionFactory
                .make(RemoveExtensionListener.class, eclipseContext);

        allDependencies = BonitaMarketplace.getInstance(AbstractRepository.NULL_PROGRESS_MONITOR).getDependencies();
        setSite(site);
        setInput(input);
    }

    @Override
    public void createPartControl(Composite parent) {
        initVariables(parent);
        parent.setLayout(GridLayoutFactory.fillDefaults().create());

        mainComposite = createComposite(parent, SWT.NONE);
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 20).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createTitleAndToolbar(mainComposite);

        Label separator = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        scrolledComposite = createExtensionSection(mainComposite);
    }

    private ScrolledComposite createExtensionSection(Composite parent) {
        ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL);
        sc.setLayout(GridLayoutFactory.fillDefaults().create());
        sc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        cardComposite = createComposite(sc, SWT.NONE);
        cardComposite.setLayout(GridLayoutFactory.fillDefaults().margins(40, 20)
                .spacing(20, 20).numColumns(2).equalWidth(true).create());
        cardComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createExtensionCards(cardComposite);

        sc.setContent(cardComposite);
        sc.setExpandVertical(true);
        sc.setExpandHorizontal(true);
        sc.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        return sc;
    }

    private void createExtensionCards(Composite parent) {
        try {
            List<Dependency> modelDependencies = mavenHelper
                    .getMavenModel(repositoryAccessor.getCurrentRepository().getProject())
                    .getDependencies();
            List<Dependency> otherDependencies = new ArrayList<>();
            modelDependencies.forEach(dep -> {
                Optional<BonitaArtifactDependency> bonitaDependency = allDependencies.stream()
                        .filter(bonitaDep -> sameDependency(dep, bonitaDep))
                        .findFirst();
                if (bonitaDependency
                        .filter(d -> !Objects.equals(d.getArtifactType(), ArtifactType.UNKNOWN))
                        .isPresent()) {
                    createCard(parent, dep, bonitaDependency.get());
                } else if (!ProjectDefaultConfiguration.isInternalDependency(dep) && !isBDMDependency(dep)) {
                    BonitaArtifactDependency bonitaDep = bonitaArtifactDependencyConverter
                            .toBonitaArtifactDependency(dep);
                    if (Objects.equals(bonitaDep.getArtifactType(), ArtifactType.UNKNOWN)) {
                        otherDependencies.add(dep);
                    } else {
                        createCard(parent, dep, bonitaDep);
                    }
                }
            });

            if (!otherDependencies.isEmpty()) {
                if (parent.getChildren().length > 0) {
                    Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
                    separator.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
                }
                new OtherExtensionsComposite(parent,
                        otherDependencies,
                        JFaceResources.getFont(BOLD_8_FONT_ID),
                        removeExtensionListener,
                        upadateExtensionListener,
                        ctx);
            }

            if (cardComposite.getChildren().length == 0) {
                toolbarComposite.setVisible(false);
                createEmptyExtensionComposite(cardComposite);
            } else {
                toolbarComposite.setVisible(true);
            }
        } catch (CoreException e) {
            errorHandler.openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
        }
    }

    private void createCard(Composite parent, Dependency dep, BonitaArtifactDependency bonitaDep) {
        ExtensionCard card = ExtensionCardFactory.createExtensionCard(parent, dep, bonitaDep);
        card.addUpdateExtensionListener(upadateExtensionListener);
        card.addRemoveExtensionListener(removeExtensionListener);
        if (card instanceof IZoomable) {
            ((IZoomable) card).addZoomListener(new ZoomListener() {

                @Override
                public void zoom(Event e) {
                    Arrays.asList(cardComposite.getChildren()).forEach(Control::dispose);
                    ((IZoomable) card).createZoomedControl(cardComposite);
                    cardComposite.layout();
                    scrolledComposite.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
                }

                @Override
                public void deZoom(Event e) {
                    refreshContent();
                }

            });
        }
    }

    private boolean isBDMDependency(Dependency dep) {
        var businessObjectModelRepositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore businessObjectModelFileStore = (BusinessObjectModelFileStore) businessObjectModelRepositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (businessObjectModelFileStore != null) {
            try {
                return Objects.equals(new GAV(businessObjectModelFileStore.getClientMavenDependency()), new GAV(dep));
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
                return false;
            }
        }
        return false;
    }

    private void createEmptyExtensionComposite(Composite parent) {
        Composite composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().span(2, 1).grab(true, true).align(SWT.CENTER, SWT.CENTER).create());

        Label label = new Label(composite, SWT.WRAP | SWT.CENTER);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.CENTER).create());
        label.setText(Messages.enhanceProject);
        label.setFont(JFaceResources.getFont(NORMAL_10_FONT_ID));
        label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        label.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        Composite buttonComposite = createComposite(composite, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).create());

        createMarketplaceBigButton(buttonComposite);
        createImportBigButton(buttonComposite);
    }

    private void refreshContent() {
        Display.getDefault().asyncExec(() -> {
            if (title.isDisposed()) {
                return;
            }
            boolean layoutMainCompsite = false;
            try {
                Model mavenModel = mavenHelper
                        .getMavenModel(repositoryAccessor.getCurrentRepository().getProject());
                String name = mavenModel.getName();
                String version = mavenModel.getVersion();
                String descriptionContent = mavenModel.getDescription();
                title.setText(String.format("%s %s", name, version));

                StyleRange titleStyle = new StyleRange(0, name.length(), title.getForeground(), title.getBackground());
                titleStyle.font = JFaceResources.getFontRegistry().get(BOLD_20_FONT_ID);
                StyleRange versionStyle = new StyleRange(name.length() + 1, version.length(), title.getForeground(),
                        title.getBackground());
                versionStyle.font = JFaceResources.getFont(ITALIC_0_FONT_ID);
                title.setStyleRanges(new StyleRange[] { titleStyle, versionStyle });

                layoutMainCompsite = refreshDescription(descriptionContent);
            } catch (CoreException e) {
                errorHandler.openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
            }

            Arrays.asList(cardComposite.getChildren()).forEach(Control::dispose);
            createExtensionCards(cardComposite);
            if (layoutMainCompsite) {
                mainComposite.layout();
            }
            cardComposite.layout();
            scrolledComposite.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        });
    }

    private boolean refreshDescription(String descriptionContent) {
        if (descriptionContent != null && !descriptionContent.isBlank()
                && (description == null || !Objects.equals(description.getText(), descriptionContent))) {
            if (description == null || description.isDisposed()) {
                createDescriptionLabel(titleComposite);
            }
            description.setText(descriptionContent);
            return true;
        } else if (description != null && (descriptionContent == null || descriptionContent.isBlank())) {
            description.dispose();
            description = null;
            return true;
        } else {
            titleComposite.getParent().layout();
            return false;
        }
    }

    private boolean sameDependency(Dependency dep, BonitaArtifactDependency bonitaDep) {
        return Objects.equals(dep.getGroupId(), bonitaDep.getGroupId())
                && Objects.equals(dep.getArtifactId(), bonitaDep.getArtifactId());
    }

    private void createTitleAndToolbar(Composite parent) {
        Composite composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        titleComposite = createComposite(composite, SWT.NONE);
        titleComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 3).create());
        titleComposite
                .setLayoutData(
                        GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.FILL).create());

        Composite labelComposite = createComposite(titleComposite, SWT.NONE);
        labelComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(2, LayoutConstants.getSpacing().y).create());
        labelComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());

        title = new StyledText(labelComposite, SWT.NONE);
        title.setEditable(false);
        title.setEnabled(false);
        title.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.END).create());

        // Create all the font used in the page once, using the default font from the first label created
        initFonts(title.getFont());

        title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        title.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        createEditButton(titleComposite);

        refreshContent();

        toolbarComposite = createComposite(composite, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).create());

        createMarketplaceButton(toolbarComposite);
        createImportButton(toolbarComposite);
    }

    public void createDescriptionLabel(Composite titleComposite) {
        description = new Label(titleComposite, SWT.WRAP);
        description.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.BEGINNING).span(2, 1)
                        .create());
        description.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        description.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
    }

    private void createEditButton(Composite parent) {
        Label editLabel = new Label(parent, SWT.NONE);
        editLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
        editLabel.setImage(Pics.getImage(PicsConstants.editProject));
        editLabel.setFont(JFaceResources.getFont(BOLD_20_FONT_ID));
        editLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        editLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseUp(MouseEvent e) {
                Rectangle bounds = editLabel.getBounds();
                if (e.x >= 0 && e.x <= bounds.width && e.y >= 0 && e.y <= bounds.height) {
                    commandExecutor.executeCommand(EDIT_PROJECT_COMMAND, null);
                }
            }
        });

        editLabel.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseExit(MouseEvent e) {
                editLabel.setImage(Pics.getImage(PicsConstants.editProject));
                editLabel.setCursor(cursorArrow);
            }

            @Override
            public void mouseEnter(MouseEvent e) {
                editLabel.setImage(Pics.getImage(PicsConstants.editProjectHot));
                editLabel.setCursor(cursorHand);
            }
        });
    }

    private void createImportButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withText(Messages.importExtensionButtonLabel)
                .withMaxTextWidth(150)
                .withTooltipText(Messages.importExtension)
                .withImage(Pics.getImage(PicsConstants.import32))
                .withHotImage(Pics.getImage(PicsConstants.import32Hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> commandExecutor.executeCommand(IMPORT_EXTENSION_COMMAND, null))
                .createIn(parent);
    }

    private void createImportBigButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withText(Messages.importExtensionButtonLabel)
                .withMaxTextWidth(200)
                .withTooltipText(Messages.importExtension)
                .withImage(Pics.getImage(PicsConstants.import64))
                .withHotImage(Pics.getImage(PicsConstants.import64Hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .withFont(JFaceResources.getFont(NORMAL_4_FONT_ID))
                .onClick(e -> commandExecutor.executeCommand(IMPORT_EXTENSION_COMMAND, null))
                .createIn(parent);
    }

    private void createMarketplaceButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withText(Messages.openMarketplace)
                .withMaxTextWidth(150)
                .withTooltipText(Messages.openMarketplaceTooltip)
                .withImage(Pics.getImage(PicsConstants.openMarketplace))
                .withHotImage(Pics.getImage(PicsConstants.openMarketplaceHot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> commandExecutor.executeCommand(OPEN_MARKETPLACE_COMMAND, null))
                .createIn(parent);
    }

    private void createMarketplaceBigButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withText(Messages.openMarketplace)
                .withMaxTextWidth(200)
                .withTooltipText(Messages.openMarketplaceTooltip)
                .withImage(Pics.getImage(PicsConstants.openMarketplace64))
                .withHotImage(Pics.getImage(PicsConstants.openMarketplace64Hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .withFont(JFaceResources.getFont(NORMAL_4_FONT_ID))
                .onClick(e -> commandExecutor.executeCommand(OPEN_MARKETPLACE_COMMAND, null))
                .createIn(parent);
    }

    private void initFonts(Font defaultFont) {
        createFont(BOLD_20_FONT_ID, defaultFont, 20, SWT.BOLD);
        createFont(BOLD_8_FONT_ID, defaultFont, 8, SWT.BOLD);
        createFont(BOLD_4_FONT_ID, defaultFont, 4, SWT.BOLD);
        createFont(BOLD_0_FONT_ID, defaultFont, 0, SWT.BOLD);
        createFont(ITALIC_0_FONT_ID, defaultFont, 0, SWT.ITALIC);
        createFont(ITALIC_2_FONT_ID, defaultFont, 2, SWT.ITALIC);
        createFont(NORMAL_10_FONT_ID, defaultFont, 10, SWT.NORMAL);
        createFont(NORMAL_4_FONT_ID, defaultFont, 4, SWT.NORMAL);
    }

    private void createFont(String id, Font initialFont, int increaseHeight, int style) {
        if (!JFaceResources.getFontRegistry().hasValueFor(id) || JFaceResources.getFontRegistry().get(id).isDisposed()) {
            FontDescriptor descriptor = FontDescriptor.createFrom(initialFont).setStyle(style)
                    .increaseHeight(increaseHeight);
            JFaceResources.getFontRegistry().put(id, localResourceManager.createFont(descriptor).getFontData());
        }
    }

    private void initVariables(Composite parent) {
        cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
    }

    private Composite createComposite(Composite parent, int style) {
        Composite composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        return composite;
    }

    @Override
    public void setFocus() {
        // do nothing
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

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        try {
            event.getDelta().accept(delta -> {
                IResource r = delta.getResource();
                if (Objects.equals(r, repositoryAccessor.getCurrentRepository().getProject()
                        .getFile(IMavenConstants.POM_FILE_NAME))) {
                    refreshContent();
                    return false;
                }
                return true;
            });
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

}
