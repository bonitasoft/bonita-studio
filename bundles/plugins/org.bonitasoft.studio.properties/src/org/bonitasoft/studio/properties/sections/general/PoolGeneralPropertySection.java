/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.general;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.jface.databinding.validator.ForbiddenCharactersValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.UTF8InputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.data.ui.property.section.PoolAdaptableSelectionProvider;
import org.bonitasoft.studio.diagram.custom.refactoring.ProcessNamingTools;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.validator.LengthValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class PoolGeneralPropertySection extends AbstractBonitaDescriptionSection {

    private static final int MAX_NAME_LENGTH = 75;
    private static final int MAX_DESC_LENGTH = 255;
    private static final int MAX_VERSION_LENGTH = 50;
    private static final int MAX_DISPLAYNAME_LENGTH = 75;

    private EMFDataBindingContext context;
    private PoolAdaptableSelectionProvider selectionProvider;
    private IProgressService progressService;
    private ProcessNamingTools processNamingTools;
    private ExceptionDialogHandler exceptionDialogHandler;

    @Inject
    public PoolGeneralPropertySection(
            final PoolAdaptableSelectionProvider selectionProvider,
            final RepositoryAccessor repositoryAccessor,
            final ExceptionDialogHandler exceptionDialogHandler,
            final IProgressService progressService) {
        this.selectionProvider = selectionProvider;
        this.exceptionDialogHandler = exceptionDialogHandler;
        this.progressService = progressService;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#createContent(org.eclipse.swt.widgets.
     * Composite)
     */
    @Override
    protected void createContent(Composite parent) {
        context = new EMFDataBindingContext();
        TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();
        Composite container = widgetFactory.createComposite(parent, SWT.NONE);
        GridDataFactory.fillDefaults().grab(true, true).applyTo(container);
        GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 20).spacing(20, LayoutConstants.getSpacing().y)
                .applyTo(container);

        new TextWidget.Builder()
                .withLabel(Messages.GeneralSection_Name)
                .labelAbove()
                .transactionalEdit((oldName, newName) -> {
                    try {
                        progressService.run(false, false, mointor -> updateProcessName(oldName, newName));
                    } catch (InvocationTargetException | InterruptedException e) {
                        exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                                "Failed to remane pool", e); //$NON-NLS-1$
                    }
                })
                .withTootltip(Messages.technicalNameTooltip)
                .withTargetToModelStrategy(convertUpdateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(new EmptyInputValidator.Builder()
                                        .withMessage(Messages.aNameMustBeSet).create())
                                .havingValidators(new LengthValidator.Builder().maxLength(MAX_NAME_LENGTH)
                                        .withMessage(String.format(Messages.maxNameLength, MAX_NAME_LENGTH)).create())
                                .havingValidators(new UTF8InputValidator(Messages.name))
                                .havingValidators(new ForbiddenCharactersValidator(Messages.name, '#', '%', '$'))
                                .havingValidators(ValidatorFactory.reservedRESTAPIKeywordsValidator()).create())
                        .create())
                .widthHint(350)
                .bindTo(CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        ViewersObservables.observeSingleSelection(selectionProvider),
                        ProcessPackage.Literals.ELEMENT__NAME))
                .inContext(context)
                .adapt(widgetFactory)
                .createIn(container);

        new TextAreaWidget.Builder()
                .withLabel(Messages.GeneralSection_Description)
                .labelAbove()
                .alignTop()
                .heightHint(155)
                .grabHorizontalSpace()
                .fill()
                .verticalSpan(3)
                .bindTo(CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        ViewersObservables.observeSingleSelection(selectionProvider),
                        ProcessPackage.Literals.ELEMENT__DOCUMENTATION))
                .withTargetToModelStrategy(updateValueStrategy().withValidator(new MultiValidator.Builder()
                        .havingValidators(new LengthValidator.Builder().maxLength(MAX_DESC_LENGTH)
                                .withMessage(String.format(Messages.maxDescriptionLength, MAX_DESC_LENGTH)).create()))
                        .create())
                .inContext(context)
                .adapt(widgetFactory)
                .createIn(container);

        new TextWidget.Builder()
                .withLabel(Messages.GeneralSection_Version)
                .labelAbove()
                .transactionalEdit((oldVersion, newVersion) -> {
                    Pool pool = (Pool) selectionProvider.getAdapter(EObject.class);
                    processNamingTools.proceedForPools(pool, pool.getName(), pool.getName(), oldVersion, newVersion);
                })
                .alignTop()
                .withTargetToModelStrategy(convertUpdateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(new EmptyInputValidator.Builder()
                                        .withMessage(Messages.aVersionMustBeSet).create())
                                .havingValidators(new LengthValidator.Builder().maxLength(MAX_VERSION_LENGTH)
                                        .withMessage(String.format(Messages.maxVersionLength, MAX_VERSION_LENGTH))
                                        .create())))
                .widthHint(180)
                .bindTo(CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        ViewersObservables.observeSingleSelection(selectionProvider),
                        ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION))
                .inContext(context)
                .adapt(widgetFactory)
                .createIn(container);

        new TextWidget.Builder()
                .withLabel(Messages.displayName)
                .labelAbove()
                .alignTop()
                .widthHint(300)
                .withTootltip(Messages.displayNameTooltip)
                .withMessage(Messages.displayNameCaption)
                .withTargetToModelStrategy(updateValueStrategy().withValidator(new MultiValidator.Builder()
                        .havingValidators(new LengthValidator.Builder().maxLength(MAX_DISPLAYNAME_LENGTH)
                                .withMessage(String.format(Messages.maxDisplayNameLength, MAX_DISPLAYNAME_LENGTH)).create()))
                        .create())
                .bindTo(CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        ViewersObservables.observeSingleSelection(selectionProvider),
                        ProcessPackage.Literals.POOL__DISPLAY_NAME))
                .inContext(context)
                .adapt(widgetFactory)
                .createIn(container);

    }

    private void updateProcessName(String oldName, String newName) {
        Pool pool = (Pool) selectionProvider.getAdapter(EObject.class);
        processNamingTools.proceedForPools(pool, newName, oldName, pool.getVersion(), pool.getVersion());
        updateMessageEvents(pool);
        updatePropertyTabTitle(newName);
        Display.getDefault().asyncExec(() -> {
            DiagramEditor activeEditor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getActivePage().getActiveEditor();
            activeEditor.getDiagramGraphicalViewer().getContents().refresh();
        });
    }

    private void updateMessageEvents(final Pool element) {
        for (final AbstractCatchMessageEvent ev : ModelHelper.getAllCatchEvent(ModelHelper.getMainProcess(element))) {
            final Message eventObject = ModelHelper.findEvent(element, ev.getEvent());
            if (eventObject != null) {
                EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(eventObject.getTargetProcessExpression());
                Expression newExpression = ExpressionHelper.createConstantExpression(element.getName(),
                        String.class.getName());
                newExpression.setReturnTypeFixed(eventObject.getTargetProcessExpression().isReturnTypeFixed());
                updater.editWorkingCopy(newExpression);
                getEditingDomain().getCommandStack().execute(updater.createUpdateCommand(getEditingDomain()));
            }
        }
    }

    private void updatePropertyTabTitle(String poolName) {
        if (poolName != null) {
            TabbedPropertySheetPage tabbedPropertySheetPage = getTabbedPropertySheetPage();
            final TabbedPropertyComposite tabbedPropertyComposite = (TabbedPropertyComposite) tabbedPropertySheetPage
                    .getControl();
            final TabbedPropertyTitle title = tabbedPropertyComposite.getTitle();
            if (title != null) {
                title.setTitle(poolName, tabbedPropertySheetPage.getTitleImage(getSelection()));
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#setInput(org.eclipse.ui.
     * IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        selectionProvider.setSelection(selection);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#setEditingDomain(org.eclipse.emf
     * .transaction.
     * TransactionalEditingDomain)
     */
    @Override
    protected void setEditingDomain(TransactionalEditingDomain editingDomain) {
        super.setEditingDomain(editingDomain);
        processNamingTools = new ProcessNamingTools(editingDomain);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getSectionDescription()
     */
    @Override
    public String getSectionDescription() {
        return Messages.poolSectionDescription;
    }

}
