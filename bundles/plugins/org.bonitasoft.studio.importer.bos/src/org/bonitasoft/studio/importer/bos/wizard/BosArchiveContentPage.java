/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.importer.bos.wizard;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.model.SmartImportFileStoreModel;
import org.bonitasoft.studio.importer.bos.provider.ArchiveTreeContentProvider;
import org.bonitasoft.studio.importer.bos.provider.ImportActionEditingSupport;
import org.bonitasoft.studio.importer.bos.provider.ImportModelLabelProvider;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TreeItem;

public class BosArchiveContentPage implements ControlSupplier {

    private static final int BUTTON_WIDTH = 80;

    protected TreeViewer viewer;
    protected ButtonWidget overwriteButton;
    protected ButtonWidget keepAllButton;
    private ArchiveTreeContentProvider provider;

    protected ImportArchiveModel archiveModel;
    protected Composite treeSection;
    protected Label descriptionLabel;
    private Color errorColor;
    private Color successColor;
    protected ImportActionSelector importActionSelector;

    protected IWizardContainer wizardContainer;
    protected ConflictStatus archiveStatus;
    private IObservableValue<ConflictStatus> archiveStatusObservable;

    private ImportBosArchivePage archiveModelSupplier;

    public BosArchiveContentPage(ImportBosArchivePage archiveModelSupplier) {
        this.archiveModelSupplier = archiveModelSupplier;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer container, DataBindingContext ctx) {
        this.wizardContainer = container;
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 25).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        final LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(),
                mainComposite);
        this.errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
        this.successColor = resourceManager.createColor(ColorConstants.SUCCESS_RGB);
        doCreateFileTree(mainComposite, ctx);

        return mainComposite;
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        IWizardPage selectedPage = (IWizardPage) event.getSelectedPage();
        if (Messages.bosArchiveContentTitle.equals(selectedPage.getTitle())) {
            Display.getDefault().asyncExec(() -> {
                archiveModel = archiveModelSupplier.get();
                if (archiveModel != null) {
                    importActionSelector.setArchiveModel(archiveModel);
                    viewer.setInput(archiveModel);
                    updateTree();
                }
            });
        }

    }

    private void doCreateFileTree(Composite parent, DataBindingContext dbc) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).create());
        createTreeHeader(composite, dbc);
        createTree(treeSection);
    }

    private void createTreeHeader(Composite parent, DataBindingContext ctx) {
        treeSection = new Composite(parent, SWT.NONE);
        treeSection.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.WIDGET_BACKGROUND_CLASS);
        treeSection.setLayout(GridLayoutFactory.fillDefaults().create());
        treeSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label detailsLabel = new Label(treeSection, SWT.NONE);
        detailsLabel.setText(Messages.importDetails);
        detailsLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        descriptionLabel = new Label(treeSection, SWT.WRAP);
        detailsLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .hint(700, SWT.DEFAULT).create());
        archiveStatusObservable = PojoProperties.value("archiveStatus", ConflictStatus.class).observe(this);

        ctx.bindValue(WidgetProperties.text().observe(descriptionLabel), archiveStatusObservable,
                neverUpdateValueStrategy().create(), updateValueStrategy().withValidator(this::archiveStatusValidator)
                        .withConverter(createArchiveStatusConverter()).create());
    }

    private IStatus archiveStatusValidator(Object value) {
        return Objects.equals(value, ConflictStatus.SAME_CONTENT)
                ? ValidationStatus.error("Archive content already exists.")
                : ValidationStatus.ok();
    }

    private Converter<ConflictStatus, String> createArchiveStatusConverter() {
        return new Converter<>(ConflictStatus.class, String.class) {

            @Override
            public String convert(ConflictStatus status) {
                return Objects.equals(status, ConflictStatus.CONFLICTING) ? getConflictMessage()
                        : Messages.noConflictMessage;
            }
        };
    }

    private Composite createTree(Composite parent) {
        final Composite fileTreeGroup = new Composite(parent, SWT.NONE);
        fileTreeGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 5).create());
        fileTreeGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        viewer = new TreeViewer(fileTreeGroup,
                SWT.VIRTUAL | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(600, SWT.DEFAULT).create());
        provider = new ArchiveTreeContentProvider(viewer);
        viewer.setContentProvider(provider);
        viewer.setUseHashlookup(true); // important for lazy behavior!
        viewer.getTree().setHeaderVisible(true);
        viewer.getTree().setLinesVisible(true);

        ColumnViewerToolTipSupport.enableFor(viewer);

        final TreeViewerColumn archiveColumn = new TreeViewerColumn(viewer, SWT.NONE);
        archiveColumn.getColumn().setText(Messages.archiveColumn);

        archiveColumn.setLabelProvider(new ImportModelLabelProvider());

        final TreeViewerColumn actionColumn = new TreeViewerColumn(viewer, SWT.NONE);
        actionColumn.getColumn().setText(Messages.actionColumn);
        actionColumn.setLabelProvider(new LabelProviderBuilder<>()
                .withTextProvider(this::getActionText)
                .createColumnLabelProvider());
        actionColumn.setEditingSupport(new ImportActionEditingSupport(viewer));

        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(6, true));
        layout.addColumnData(new ColumnWeightData(2, true));
        viewer.getTree().setLayout(layout);

        final Composite buttonsComposite = new Composite(fileTreeGroup, SWT.NONE);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(5, 2).create());
        buttonsComposite.setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.TOP).create());

        importActionSelector = new ImportActionSelector(viewer);
        overwriteButton = new ButtonWidget.Builder()
                .withLabel(Messages.overwriteAll)
                .alignLeft()
                .fill()
                .grabHorizontalSpace()
                .minimumWidth(BUTTON_WIDTH)
                .onClick(importActionSelector::selectOverwriteAll)
                .createIn(buttonsComposite);
        overwriteButton.disable();

        keepAllButton = new ButtonWidget.Builder()
                .withLabel(Messages.keepAll)
                .alignLeft()
                .fill()
                .minimumWidth(BUTTON_WIDTH)
                .grabHorizontalSpace()
                .onClick(importActionSelector::selectKeepAll)
                .createIn(buttonsComposite);
        keepAllButton.disable();

        return fileTreeGroup;
    }

    private String getActionText(Object element) {
        if (element instanceof AbstractFileModel) {
            AbstractFileModel fileModel = (AbstractFileModel) element;
            if (fileModel.isConflicting()) {
                return fileModel.getImportAction().toString();
            }
        } else if (element instanceof SmartImportableUnit) {
            SmartImportableUnit unit = (SmartImportableUnit) element;
            if (unit.getImportAction() != null
                    && Objects.equals(unit.getConflictStatus(), ConflictStatus.CONFLICTING)) {
                return unit.getImportAction().toString();
            }
        }
        return "";
    }

    private void updateTree() {
        archiveStatusObservable.setValue(archiveModel.getStatus());
        if (archiveModel.isConflicting()) {
            final TreeItem[] items = viewer.getTree().getItems();
            for (int i = 0; i < items.length; i++) {
                final TreeItem item = items[i];
                provider.updateElement(archiveModel, i);
                if (item.getData() instanceof AbstractFolderModel
                        && ((AbstractFolderModel) item.getData()).isConflicting()) {
                    openItem(item);
                }
                if (item.getData() instanceof SmartImportFileStoreModel
                        && Objects.equals(((SmartImportFileStoreModel) item.getData()).getImportAction(),
                                ImportAction.SMART_IMPORT)
                        && ((SmartImportFileStoreModel) item.getData()).isConflicting()) {
                    openItem(item);
                }
            }
            descriptionLabel.setForeground(errorColor);
            keepAllButton.enable();
            overwriteButton.enable();
        } else if (archiveModel.sameContentAsTarget()) {
            descriptionLabel.setText(getAlreadyPresentMessage());
            descriptionLabel.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY));
            keepAllButton.disable();
            overwriteButton.disable();
        } else {
            descriptionLabel.setText(Messages.noConflictMessage);
            descriptionLabel.setForeground(successColor);
            keepAllButton.disable();
            overwriteButton.disable();
        }
    }

    protected String getAlreadyPresentMessage() {
        return Messages.alreadyPresent;
    }

    protected String getConflictMessage() {
        return Messages.conflictMessage;
    }

    private void openItem(TreeItem item) {
        item.setExpanded(true);
        final AbstractFolderModel parent = (AbstractFolderModel) item.getData();
        parent.getFolders().stream().forEach(f -> provider.updateElement(parent, parent.getFolders().indexOf(f)));
        Stream.of(item.getItems())
                .filter(i -> i.getData() instanceof AbstractFolderModel)
                .filter(i -> ((AbstractFolderModel) i.getData()).isConflicting())
                .forEach(this::openItem);
    }

    public ConflictStatus getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(ConflictStatus archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

}
