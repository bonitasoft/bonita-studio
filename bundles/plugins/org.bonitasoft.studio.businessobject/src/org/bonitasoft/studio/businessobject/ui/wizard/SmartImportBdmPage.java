/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.model.OverwriteImportBdmModel;
import org.bonitasoft.studio.businessobject.model.SmartImportBdmModel;
import org.bonitasoft.studio.businessobject.model.SmartImportBusinessObjectModel;
import org.bonitasoft.studio.businessobject.model.SmartImportPackageModel;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.SmartImportBdmTreeContentProvider;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.ImportBdmContentValidator;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.SmartImportBdmModelValidator;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.SmartImportBdmValidator;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ZipUtil;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableModel;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.page.AbstractImportPage;
import org.bonitasoft.studio.ui.styler.StylerBuilder;
import org.bonitasoft.studio.ui.viewer.ComboBoxEditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

public class SmartImportBdmPage extends AbstractImportPage {

    public static final String IMPORT_ACTION_COMBO_EDITOR_ID = "importBdmActionComboId";

    private BusinessObjectModelFileStore bdmFileStore;
    protected IObservableValue<SmartImportBdmModel> importBdmModelObservable = new WritableValue<>();
    private TreeViewer viewer;
    private Styler conflictingStyler;
    private Styler grayStyler;
    private LocalResourceManager resourceManager;
    protected BusinessObjectModelConverter converter;

    public SmartImportBdmPage(RepositoryAccessor repositoryAccessor) {
        super(repositoryAccessor);
        bdmFileStore = getBdmFileStore();
        converter = new BusinessObjectModelConverter();
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        super.createControl(composite, wizardContainer, ctx);
        Composite importComposite = createImportComposite(composite);
        filePathObservable.addValueChangeListener(e -> parseInput());
        ctx.bindValue(WidgetProperties.visible().observe(importComposite), new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return filePathObservable.getValue() != null;
            }
        });

        ctx.bindValue(new WritableValue(), importBdmModelObservable, // Purpose: disable on finish button when input isn't correct
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(new SmartImportBdmModelValidator())
                        .create());

        return composite;
    }

    private Composite createImportComposite(Composite parent) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createHeader(composite);
        
        
        Composite viewerComposite = new Composite(composite, SWT.None);
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 0).create());
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        
        CLabel previewDescLabel = new CLabel(viewerComposite, SWT.None);
        previewDescLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        Image icon = JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
        previewDescLabel.setImage(icon);
        previewDescLabel.setText(Messages.previewDesc);
        
        createMergeViewer(viewerComposite);

        return composite;
    }

    private void createHeader(Composite parent) {
        Group composite = new Group(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).extendedMargins(0, 0, -5, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        CLabel warning = new CLabel(composite, SWT.WRAP);
        warning.setLayoutData(GridDataFactory.fillDefaults().exclude(true).create());
        Image warningIcon = JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
        warning.setImage(warningIcon);

        CLabel info1 = new CLabel(composite, SWT.WRAP);
        info1.setLayoutData(GridDataFactory.fillDefaults().exclude(true).create());
        info1.setImage(BusinessObjectPlugin.getImage("icons/arrow16.png"));

        CLabel info2 = new CLabel(composite, SWT.WRAP);
        info2.setLayoutData(GridDataFactory.fillDefaults().exclude(true).create());
        info2.setImage(BusinessObjectPlugin.getImage("icons/arrow16.png"));

        importBdmModelObservable.addValueChangeListener(e -> {
            SmartImportBdmModel newModel = e.diff.getNewValue();
            if (newModel == null) {
                updateCLabel(warning, "", false);
                updateCLabel(info1, "", false);
                updateCLabel(info2, "", false);
            } else if (bdmFileStore == null) {
                updateCLabel(warning, "", false);
                updateCLabel(info1, "", false);
                updateCLabel(info2, Messages.newBdmImport, true);
            } else if (isSameContent(newModel)) {
                updateCLabel(warning, "", false);
                updateCLabel(info1, "", false);
                updateCLabel(info2, Messages.globalSkipped, true);
            } else if (isConflicting(newModel)) {
                if (newModel instanceof OverwriteImportBdmModel) {
                    updateCLabel(warning, Messages.smartImportImpossible, true);
                    updateCLabel(info1, Messages.overwriteImportDesc, true);
                    updateCLabel(info2, "", false);
                } else {
                    updateCLabel(warning, Messages.smartImportConflict, true);
                    updateCLabel(info1, Messages.selectPackageToKeep, true);
                    updateCLabel(info2, Messages.smartImportDesc, true);
                }
            } else {
                updateCLabel(warning, "", false);
                updateCLabel(info1, "", false);
                updateCLabel(info2, Messages.smartImportDesc, true);
            }
            parent.layout(true);
        });
    }

    private void updateCLabel(CLabel clabel, String text, boolean visible) {
        clabel.setText(text);
        clabel.setVisible(visible);
        ((GridData) clabel.getLayoutData()).exclude = !visible;
        clabel.layout(true);
    }

    private Composite createMergeViewer(Composite parent) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        viewer = new TreeViewer(composite,
                SWT.VIRTUAL | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.setUseHashlookup(true);
        ColumnViewerToolTipSupport.enableFor(viewer);
        viewer.getTree().setHeaderVisible(true);
        viewer.getTree().setLinesVisible(true);
        viewer.setContentProvider(new SmartImportBdmTreeContentProvider());

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(6, true));
        layout.addColumnData(new ColumnWeightData(2, true));
        viewer.getTree().setLayout(layout);

        createModelColumn();
        createActionColumn();

        IViewerObservableValue singleSelectionObservable = ViewerProperties.singleSelection().observe(viewer);
        viewer.addDoubleClickListener(e -> viewer.setExpandedState(singleSelectionObservable.getValue(),
                !viewer.getExpandedState(singleSelectionObservable.getValue())));
        return composite;
    }

    private void createActionColumn() {
        TreeViewerColumn actionColumn = new TreeViewerColumn(viewer, SWT.NONE);
        actionColumn.getColumn().setText(Messages.importAction);
        actionColumn.setLabelProvider(new LabelProviderBuilder<SmartImportableUnit>()
                .withStyledStringProvider(this::getActionStyledString)
                .createStyledCellLabelProvider());
        actionColumn.setEditingSupport(
                new ComboBoxEditingSupportBuilder<SmartImportableUnit, ImportAction>(actionColumn.getViewer())
                        .withCanEditProvider(
                                element -> importBdmModelObservable.getValue() instanceof OverwriteImportBdmModel
                                        ? false
                                        : element instanceof SmartImportPackageModel && isConflicting(element))
                        .withValueProvider(SmartImportableUnit::getImportAction)
                        .withValueUpdater((element, value) -> {
                            element.setImportAction(value);
                            importBdmModelObservable.getValue()
                                    .updateSmartImportPackageModel((SmartImportPackageModel) element);
                            viewer.refresh(element);
                        })
                        .withInput(new ImportAction[] { ImportAction.OVERWRITE, ImportAction.KEEP })
                        .withData(IMPORT_ACTION_COMBO_EDITOR_ID)
                        .create());
    }

    private void createModelColumn() {
        TreeViewerColumn modelColumn = new TreeViewerColumn(viewer, SWT.NONE);
        modelColumn.getColumn().setText(Messages.businessDataModelPreview);
        modelColumn.setLabelProvider(new LabelProviderBuilder<SmartImportableUnit>()
                .withStyledStringProvider(this::styledStringProvider)
                .withTooltipProvider(SmartImportableUnit::getToolTipText)
                .createStyledCellLabelProvider());
    }

    private StyledString getActionStyledString(SmartImportableUnit element) {
        if (element instanceof SmartImportPackageModel
                && !(importBdmModelObservable.getValue() instanceof OverwriteImportBdmModel)) {
            SmartImportPackageModel packageModel = (SmartImportPackageModel) element;
            if (isConflicting(packageModel)) {
                return new StyledString(packageModel.getImportAction().toString());
            }
            if (!isSameContent(packageModel)) {
                return new StyledString(Messages.importActionName, grayStyler);
            }
        }
        return new StyledString();
    }

    private StyledString styledStringProvider(SmartImportableUnit element) {
        initStylers();
        if (isConflicting(element)) {
            if(element instanceof SmartImportBusinessObjectModel ) {
                if(!Objects.equals(((SmartImportBusinessObjectModel) element).getBusinessObject().getQualifiedName(),element.getConflictingObjectName())){
                    return new StyledString(String.format(Messages.objectAlreadyExistsInAnotherPackage, element.getName(), NamingUtils.getPackageName(element.getConflictingObjectName())), conflictingStyler);
                }else {
                    return new StyledString(String.format(Messages.conflictingWithSameObject, element.getName()), conflictingStyler);
                }
            }
            return new StyledString(element.getName(), conflictingStyler);
        }
        if (isSameContent(element)) {
            String text = element.getParent() != null && isSameContent(element.getParent())
                    ? element.getName()
                    : String.format("%s (%s)", element.getName(), Messages.skipped);
            return new StyledString(text, grayStyler);
        }
        return new StyledString(element.getName());
    }

    private void initStylers() {
        if (conflictingStyler == null) {
            Color errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
            conflictingStyler = new StylerBuilder().withColor(errorColor).create();

            Color sameContentColor = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
            grayStyler = new StylerBuilder().withColor(sameContentColor).create();
        }
    }

    protected void parseInput() {
        if (filePath != null) {
            try {
                File fileToImport = retrieveFileToImport();
                SmartImportBdmValidator validator = new SmartImportBdmValidator(bdmFileStore, converter);
                SmartImportBdmModel importBdmModel = new SmartImportBdmModel(bdmFileStore, converter, validator);
                if (importBdmModel.validateSmartImportable(fileToImport).isOK()) {
                    importBdmModel.buildSmartImportModel(fileToImport);
                } else {
                    importBdmModel = new OverwriteImportBdmModel(bdmFileStore, converter, validator);
                    importBdmModel.buildSmartImportModel(fileToImport);
                }
                this.importBdmModelObservable.setValue(importBdmModel);
                updateInput(importBdmModel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void updateInput(SmartImportBdmModel importBdmModel) {
        viewer.setInput(importBdmModel);
        importBdmModel.getSmartImportableUnits().stream()
                .filter(packageUnit -> Objects.equals(packageUnit.getConflictStatus(), ConflictStatus.CONFLICTING))
                .forEach(packageUnit -> viewer.setExpandedState(packageUnit, true));
    }

    protected File retrieveFileToImport() throws IOException {
        return ZipUtil.unzip(new File(filePath))
                .resolve(BusinessObjectModelFileStore.BOM_FILENAME)
                .toFile();
    }

    @Override
    protected List<IValidator> getValidators() {
        List<IValidator> validators = super.getValidators();
        validators.add(new ImportBdmContentValidator(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)));
        return validators;
    }

    @Override
    protected String[] getExtensions() {
        return new String[] { "*.zip" };
    }

    private boolean isConflicting(SmartImportableUnit unit) {
        return Objects.equals(unit.getConflictStatus(), ConflictStatus.CONFLICTING);
    }

    private boolean isConflicting(SmartImportableModel model) {
        return Objects.equals(model.getConflictStatus(), ConflictStatus.CONFLICTING);
    }

    private boolean isSameContent(SmartImportableUnit unit) {
        return Objects.equals(unit.getConflictStatus(), ConflictStatus.SAME_CONTENT);
    }

    private boolean isSameContent(SmartImportableModel model) {
        return Objects.equals(model.getConflictStatus(), ConflictStatus.SAME_CONTENT);
    }

    protected BusinessObjectModelFileStore getBdmFileStore() {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore fileStore = repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        return fileStore;
    }

    public boolean canPerformSmartImport() {
        return !(importBdmModelObservable.getValue() instanceof OverwriteImportBdmModel);
    }

    public SmartImportBdmModel getImportBdmModel() {
        return importBdmModelObservable.getValue();
    }

}
