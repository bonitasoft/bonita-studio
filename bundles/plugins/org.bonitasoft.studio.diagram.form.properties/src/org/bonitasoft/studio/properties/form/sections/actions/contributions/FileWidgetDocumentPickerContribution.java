/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.jface.databinding.converter.BooleanInverserConverter;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetDownloadType;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


/**
 * @author aurelie
 *
 */
public class FileWidgetDocumentPickerContribution implements IExtensibleGridPropertySectionContribution {

    private FileWidget fileWidget;
    private TransactionalEditingDomain editingDomain;
    private Button both;
    private Button url;
    private Button browse;
    private TabbedPropertySheetWidgetFactory widgetFactory;
    private DataBindingContext dataBindingContext;
    private Composite mainComposite;
    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof FileWidget;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#refresh()
     */
    public void refresh() {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
     */
    public String getLabel() {
        return Messages.documentPickerLabel;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
     */
    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory,
            final ExtensibleGridPropertySection extensibleGridPropertySection) {
        mainComposite = composite;
        this.widgetFactory = widgetFactory;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createDownloadTypeComposite(composite);
    }

    private void createDownloadTypeComposite(final Composite parent) {
        final Composite composite = widgetFactory.createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(3, 0).create());

        url = widgetFactory.createButton(composite, Messages.urlRadio, SWT.RADIO);
        browse = widgetFactory.createButton(composite, Messages.BrowseRadio, SWT.RADIO);
        both = widgetFactory.createButton(composite, Messages.both, SWT.RADIO);

        final ControlDecoration decoDoc = new ControlDecoration(url, SWT.LEFT);
        decoDoc.setDescriptionText(Messages.fileWidgetTypeToolTip);
        decoDoc.setImage(Pics.getImage(PicsConstants.hint));
        decoDoc.setMarginWidth(0);
        decoDoc.setShowOnlyOnFocus(false);

        createResourceObservable();

        updateBinding();
    }

    private void updateBinding() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
        dataBindingContext = new EMFDataBindingContext();

        bindEnableButtons();

        bindDownloadType();
    }

    private IObservableValue createResourceObservable() {
        final IObservableValue resourceObservable = EMFObservables.observeValue(fileWidget, FormPackage.Literals.FILE_WIDGET__INPUT_TYPE);
        resourceObservable.addValueChangeListener(new IValueChangeListener() {

            public void handleValueChange(final ValueChangeEvent event) {
                final FileWidgetInputType inputType = (FileWidgetInputType) ((IObservableValue) event.getSource()).getValue();
                if (FileWidgetInputType.RESOURCE.equals(inputType) && url.getSelection()) {
                    browse.setSelection(true);
                    url.setSelection(false);
                    both.setSelection(false);
                    final Command c = SetCommand.create(editingDomain, fileWidget, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_TYPE,
                            FileWidgetDownloadType.BROWSE);
                    if (c.canExecute()) {
                        editingDomain.getCommandStack().execute(c);
                    }
                }
            }
        });
        return resourceObservable;
    }

    private void bindDownloadType() {
        final SelectObservableValue downLoadTypeObservable = new SelectObservableValue(FileWidgetDownloadType.class);
        downLoadTypeObservable.addOption(FileWidgetDownloadType.URL, SWTObservables
                .observeSelection(url));
        downLoadTypeObservable.addOption(FileWidgetDownloadType.BROWSE, SWTObservables
                .observeSelection(browse));
        downLoadTypeObservable.addOption(FileWidgetDownloadType.BOTH, SWTObservables
                .observeSelection(both));
        dataBindingContext
                .bindValue(downLoadTypeObservable, EMFObservables.observeValue(fileWidget, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_TYPE));
    }

    private void bindEnableButtons() {
        final UpdateValueStrategy strategy = createEnabledStrategy();
        final UpdateValueStrategy strategyForUrl = createEnabledStrategyForDownloadOnlyURLCase();
        dataBindingContext.bindValue(SWTObservables.observeEnabled(url),
                EMFObservables.observeValue(fileWidget, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), strategyForUrl, strategyForUrl);
        dataBindingContext.bindValue(SWTObservables.observeEnabled(url), EMFObservables.observeValue(fileWidget, FormPackage.Literals.FILE_WIDGET__INPUT_TYPE),
                null, createEnabledUrlWhenInputIsResourceStrategyTargetToModel());
        dataBindingContext.bindValue(SWTObservables.observeEnabled(browse),
                EMFObservables.observeValue(fileWidget, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), strategy, strategy);
        dataBindingContext.bindValue(SWTObservables.observeEnabled(both),
                EMFObservables.observeValue(fileWidget, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), strategy, strategy);
    }

    protected UpdateValueStrategy createEnabledUrlWhenInputIsResourceStrategyTargetToModel() {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setConverter(new Converter(FileWidgetInputType.class, Boolean.class) {

            public Object convert(final Object arg0) {
                if (FileWidgetInputType.RESOURCE.equals(arg0) || fileWidget.isDownloadOnly()) {
                    return false;
                }
                return true;
            }
        });
        return strategy;
    }

    protected UpdateValueStrategy createEnabledStrategyForDownloadOnlyURLCase() {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setConverter(new Converter(Boolean.class, Boolean.class) {

            public Object convert(final Object arg0) {
                if (FileWidgetInputType.RESOURCE.equals(fileWidget.getInputType())) {
                    return false;
                }
                return !((Boolean) arg0);
            }
        });
        return strategy;
    }

    protected UpdateValueStrategy createEnabledStrategy() {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setConverter(new BooleanInverserConverter());
        return strategy;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setEObject(final EObject object) {
        fileWidget = (FileWidget) object;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(final ISelection selection) {

    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain
     * )
     */
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    public void dispose() {
    }

}
