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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependencyConverter;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaMarketplace;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
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
    private static final String IMPORT_EXTENSION_COMMAND = "org.bonitasoft.studio.application.import.extension.command";

    private RepositoryAccessor repositoryAccessor;
    private MavenProjectHelper mavenHelper;
    private CommandExecutor commandExecutor;
    private List<BonitaArtifactDependency> allDependencies;
    private Font titleFont;
    private Font subtitleFont;
    private Font gavFont;
    private Cursor cursorHand;
    private Cursor cursorArrow;
    private ScrolledComposite scrolledComposite;
    private Composite cardComposite;
    private IThemeEngine engine;
    private DataBindingContext ctx;
    private BonitaArtifactDependencyConverter bonitaArtifactDependencyConverter;

    private IObservableList<Dependency> unknownDepSelectionObservable;

    public ProjectExtensionEditorPart() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        mavenHelper = new MavenProjectHelper();
        commandExecutor = new CommandExecutor();
        engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
        ctx = new DataBindingContext();
        bonitaArtifactDependencyConverter = new BonitaArtifactDependencyConverter(
                repositoryAccessor.getCurrentRepository().getProjectDependenciesStore());
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
        initVariables(parent);
        parent.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite mainComposite = createComposite(parent, SWT.NONE);
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
            List<Dependency> userDependencies = mavenHelper
                    .getMavenModel(repositoryAccessor.getCurrentRepository().getProject())
                    .getDependencies();
            List<Dependency> unknownDependencies = new ArrayList<>();
            userDependencies.forEach(dep -> {
                Optional<BonitaArtifactDependency> bonitaDependency = allDependencies.stream()
                        .filter(bonitaDep -> sameDependency(dep, bonitaDep))
                        .findFirst();
                if (bonitaDependency.isPresent()) {
                    createCard(parent, dep, bonitaDependency.get());
                } else if (dep.getScope() == null || Objects.equals(dep.getScope(), "compile")) {
                    BonitaArtifactDependency bonitaDep = bonitaArtifactDependencyConverter.toBonitaArtifactDependency(dep);
                    if (Objects.equals(bonitaDep.getArtifactType(), ArtifactType.UNKNOWN)) {
                        unknownDependencies.add(dep);
                    } else {
                        createCard(parent, dep, bonitaDep);
                    }
                }
            });

            if (!unknownDependencies.isEmpty()) {
                Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
                separator.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

                createUnknownExtensionsSection(parent, unknownDependencies);
            }
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }
    }

    private void createUnknownExtensionsSection(Composite parent, List<Dependency> unknownDependencies) {
        Composite unknownExtensionsComposite = createComposite(parent, SWT.NONE);
        unknownExtensionsComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        unknownExtensionsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());

        Label title = new Label(unknownExtensionsComposite, SWT.WRAP);
        title.setLayoutData(GridDataFactory.fillDefaults().create());
        title.setText(Messages.unknownExtensionsTitle);
        title.setFont(subtitleFont);
        title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        title.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        Label description = new Label(unknownExtensionsComposite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().create());
        description.setText(Messages.unknownExtensionsDescription);
        description.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        createUnknowExtensionViewer(unknownExtensionsComposite, unknownDependencies);
    }

    private void createUnknowExtensionViewer(Composite parent, List<Dependency> unknownDependencies) {
        Composite viewerComposite = createComposite(parent, SWT.NONE);
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 10, 0)
                .spacing(LayoutConstants.getSpacing().x, 1).create());
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        DynamicButtonWidget deleteButton = createToolbar(viewerComposite);

        TableViewer viewer = new TableViewer(viewerComposite,
                SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        layout.addColumnData(new ColumnWeightData(1, true));
        layout.addColumnData(new ColumnWeightData(1, true));
        layout.addColumnData(new ColumnWeightData(1, true));
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);
        viewer.setUseHashlookup(true);

        createColumn(viewer, "Group ID", dep -> dep.getGroupId());
        createColumn(viewer, "Artifact ID", dep -> dep.getArtifactId());
        createColumn(viewer, "Version", dep -> dep.getVersion());
        createColumn(viewer, "Type", dep -> dep.getType());
        createColumn(viewer, "Classifier", dep -> dep.getClassifier());

        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setInput(unknownDependencies);
        unknownDepSelectionObservable = ViewerProperties.<TableViewer, Dependency> multipleSelection()
                .observe(viewer);
        ctx.bindValue(deleteButton.observeEnable(), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> !unknownDepSelectionObservable.isEmpty())
                .build());
    }

    protected DynamicButtonWidget createToolbar(Composite parent) {
        Composite composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).create());

        return new DynamicButtonWidget.Builder()
                .withText(Messages.delete)
                .withTooltipText(Messages.deleteUnknownTooltip)
                .withImage(Pics.getImage(PicsConstants.delete))
                .withHotImage(Pics.getImage(PicsConstants.delete_hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> {
                    String depList = unknownDepSelectionObservable.stream()
                            .map(dep -> String.format("%s:%s:%s", dep.getGroupId(), dep.getArtifactId(), dep.getVersion()))
                            .reduce("", (dep1, dep2) -> dep1.isEmpty() ? dep2 : String.format("%s\n%s", dep1, dep2));
                    if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                            Messages.removeExtensionConfirmationTitle,
                            String.format(Messages.removeExtensionsConfirmation, depList))) {
                        removeExtensions(unknownDepSelectionObservable.toArray(new Dependency[] {}));
                    }
                })
                .createIn(composite);
    }

    private void createColumn(TableViewer viewer, String title, Function<Dependency, String> textProvider) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(title);
        column.setLabelProvider(new LabelProviderBuilder<Dependency>()
                .withTextProvider(textProvider)
                .createColumnLabelProvider());
    }

    private void createCard(Composite parent, Dependency dep, BonitaArtifactDependency bonitaDep) {
        Composite cardComposite = new Composite(parent, SWT.BORDER);
        cardComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        cardComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        cardComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        Composite titleComposite = new Composite(cardComposite, SWT.NONE);
        titleComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(1, 2).create());
        titleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        CLabel titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleLabel.setText(bonitaDep.getName());
        titleLabel.setFont(subtitleFont);
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        CLabel gav = new CLabel(titleComposite, SWT.WRAP);
        gav.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 0).create());
        gav.setText(String.format("%s:%s:%s", dep.getGroupId(), dep.getArtifactId(), dep.getVersion()));
        gav.setFont(gavFont);
        gav.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TOOLBAR_TEXT_COLOR);

        Label type = new Label(cardComposite, SWT.WRAP);
        type.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.BEGINNING).create());
        type.setText(bonitaDep.getArtifactType().getName());
        type.setFont(gavFont);
        type.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TOOLBAR_TEXT_COLOR);

        Label iconLabel = new Label(cardComposite, SWT.NONE);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.FILL)
                .span(1, 2)
                .hint(BonitaMarketplacePage.ICON_SIZE, BonitaMarketplacePage.ICON_SIZE)
                .create());
        iconLabel.setImage(bonitaDep.getIconImage());

        Label description = new Label(cardComposite, SWT.WRAP);
        description.setLayoutData(
                GridDataFactory.fillDefaults().indent(0, 10).grab(true, false).hint(SWT.DEFAULT, 50).create());
        description.setText(bonitaDep.getDescription() != null ? bonitaDep.getDescription() : "");

        Label separator = new Label(cardComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        ToolBar toolBar = new ToolBar(cardComposite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        toolBar.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).align(SWT.END, SWT.FILL).create());
        toolBar.setLayout(GridLayoutFactory.fillDefaults().create());

        ToolItem deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setHotImage(Pics.getImage(PicsConstants.delete_hot));
        deleteItem.setToolTipText(Messages.removeExtensionTooltip);
        addMouseHandCursorBehavior(toolBar);
        deleteItem.addListener(SWT.Selection, e -> {
            if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.removeExtensionConfirmationTitle,
                    String.format(Messages.removeExtensionConfirmation, bonitaDep.getName()))) {
                removeExtensions(dep);
            }
        });

        titleLabel.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseEnter(MouseEvent e) {
                titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                        BonitaThemeConstants.CARD_HIGHLIGHT_TITLE_COLOR);
                engine.applyStyles(titleLabel, false);
            }

            @Override
            public void mouseExit(MouseEvent e) {
                titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
                engine.applyStyles(titleLabel, false);
            }
        });
    }

    private void addMouseHandCursorBehavior(Control control) {
        control.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseEnter(MouseEvent e) {
                control.setCursor(cursorHand);
            }

            @Override
            public void mouseExit(MouseEvent e) {
                control.setCursor(cursorArrow);
            }
        });
    }

    private void removeExtensions(Dependency... deps) {
        for (Dependency dep : deps) {
            RemoveDependencyOperation operation = new RemoveDependencyOperation(dep);
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                    try {
                        operation.run(monitor);
                    } catch (CoreException e) {
                        throw new InvocationTargetException(e);
                    }
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        refreshCards();
    }

    private void refreshCards() {
        Display.getDefault().asyncExec(() -> {
            Arrays.asList(cardComposite.getChildren()).forEach(Control::dispose);
            createExtensionCards(cardComposite);
            cardComposite.layout();
            scrolledComposite.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
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
        createImportButton(toolbarsComposite);
    }

    private void createImportButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withText(Messages.importExtensionButtonLabel)
                .withMaxTextWidth(150)
                .withTooltipText(Messages.importExtension)
                .withImage(Pics.getImage(PicsConstants.import32))
                .withHotImage(Pics.getImage(PicsConstants.import32Hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> {
                    commandExecutor.executeCommand(IMPORT_EXTENSION_COMMAND, null);
                    refreshCards();
                })
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
                .onClick(e -> {
                    commandExecutor.executeCommand(OPEN_MARKETPLACE_COMMAND, null);
                    refreshCards();
                })
                .createIn(parent);
    }

    private void initVariables(Composite parent) {
        titleFont = new Font(Display.getDefault(), "titleFont", 30, SWT.BOLD);
        subtitleFont = new Font(Display.getDefault(), "subtitleFont", 20, SWT.BOLD);
        gavFont = new Font(Display.getDefault(), "gavFont", 12, SWT.ITALIC);
        cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
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
        if (titleFont != null) {
            titleFont.dispose();
        }
        if (subtitleFont != null) {
            subtitleFont.dispose();
        }
        if (gavFont != null) {
            gavFont.dispose();
        }
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
