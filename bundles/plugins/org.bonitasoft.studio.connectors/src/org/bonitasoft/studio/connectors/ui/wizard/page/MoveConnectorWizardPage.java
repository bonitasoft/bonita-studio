/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.connectors.ui.wizard.page;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;

import java.util.Objects;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidator;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Romain Bioteau
 */
public class MoveConnectorWizardPage extends WizardPage implements IWizardPage {

    private final AbstractProcess sourceProcess;
    private final ComposedAdapterFactory adapterFactory;
    private boolean copy = false;
    private final WritableValue targetLocationObservable;
    private final ConnectableElement originalLocation;
    private final IObservableValue connectorEventObservable;

    public MoveConnectorWizardPage(final AbstractProcess sourceProcess,
            final WritableValue targetLocationObservable,
            final WritableValue connectorEventObservable) {
        super(MoveConnectorWizardPage.class.getName(), Messages.switchContainerConnectorTitle, Pics.getWizban());
        setDescription(Messages.switchContainerConnectorMessage);
        this.sourceProcess = sourceProcess;
        this.targetLocationObservable = targetLocationObservable;
        this.connectorEventObservable = connectorEventObservable;
        originalLocation = (ConnectableElement) targetLocationObservable.getValue();
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final EMFDataBindingContext dbc = new EMFDataBindingContext();

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        createActionControl(dbc, mainComposite);
        createProcessTreeControl(dbc, mainComposite);
        final Label seprator = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        seprator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        createExecutionEventControl(dbc, mainComposite);

        WizardPageSupport.create(this, dbc);
        setControl(mainComposite);
    }

    private void createProcessTreeControl(final EMFDataBindingContext dbc, final Composite parent) {
        final Label label = new Label(parent, SWT.NONE);
        label.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).create());
        label.setText(Messages.chooseTargetStepOrProcess);

        final TreeViewer processTreeViewer = new FilteredTree(parent, SWT.BORDER | SWT.SINGLE, new PatternFilter(), true).getViewer();
        processTreeViewer.getControl().getParent().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).hint(SWT.DEFAULT, 300).create());
        processTreeViewer.setLabelProvider(new ElementLabelProvider(adapterFactory));
        processTreeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        processTreeViewer.addFilter(new ConnectableElementViewerFilter(sourceProcess));
        processTreeViewer.setInput(ModelHelper.getMainProcess(sourceProcess));
        processTreeViewer.expandAll();
        dbc.bindValue(ViewersObservables.observeSingleSelection(processTreeViewer), targetLocationObservable,
                updateValueStrategy().withValidator(selectionValidator()).create(), null);
    }

    private void createActionControl(final EMFDataBindingContext dbc, final Composite parent) {
        final Label actionLabel = new Label(parent, SWT.NONE);
        actionLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).create());
        actionLabel.setText(Messages.selectMoveOrCopyAction);

        final Composite actionRadioGroup = new Composite(parent, SWT.NONE);
        actionRadioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        actionRadioGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(20, 0, 0, 5).create());

        final Button moveRadio = createRadioButton(actionRadioGroup, Messages.move);
        final Button copyRadio = createRadioButton(actionRadioGroup, Messages.copy);

        final SelectObservableValue actionObservable = new SelectObservableValue(Boolean.class);
        actionObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(moveRadio));
        actionObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(copyRadio));
        dbc.bindValue(actionObservable, PojoObservables.observeValue(this, "copy"));
    }

    private void createExecutionEventControl(final EMFDataBindingContext dbc, final Composite parent) {
        final Label eventLabel = new Label(parent, SWT.NONE);
        eventLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).create());
        eventLabel.setText(Messages.connectorEventLabel);

        final Composite eventRadioGroup = new Composite(parent, SWT.NONE);
        eventRadioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        eventRadioGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(20, 0, 0, 15).create());

        final Button inRadio = createRadioButton(eventRadioGroup, Messages.bind(Messages.connectorInChoice, ConnectorEvent.ON_ENTER.name()));
        final Button outRadio = createRadioButton(eventRadioGroup, Messages.bind(Messages.connectorOutChoice, ConnectorEvent.ON_FINISH.name()));

        final SelectObservableValue eventObservable = new SelectObservableValue(String.class);
        eventObservable.addOption(ConnectorEvent.ON_ENTER.name(), SWTObservables.observeSelection(inRadio));
        eventObservable.addOption(ConnectorEvent.ON_FINISH.name(), SWTObservables.observeSelection(outRadio));
        dbc.bindValue(eventObservable, connectorEventObservable);
    }

    private Button createRadioButton(final Composite radioGroup, final String label) {
        final Button radioButton = new Button(radioGroup, SWT.RADIO);
        radioButton.setLayoutData(GridDataFactory.fillDefaults().create());
        radioButton.setText(label);
        return radioButton;
    }

    protected MultiValidator selectionValidator() {
        return multiValidator()
                .addValidator(new IValidator() {

                    @Override
                    public IStatus validate(final Object value) {
                        if (value == null || !(value instanceof ConnectableElement)) {
                            return ValidationStatus.error(Messages.invalidTargetLocationMessages);
                        }
                        if (originalLocation instanceof Activity) {
                            return !Objects.equals(originalLocation, value) ? ValidationStatus.warning(Messages.warningLocalVariableinConnector)
                                    : ValidationStatus.ok();
                        }
                        return ValidationStatus.ok();
                    }
                }).create();
    }

    public boolean isCopy() {
        return copy;
    }

    public void setCopy(final boolean copy) {
        this.copy = copy;
    }

    protected class ElementLabelProvider extends AdapterFactoryLabelProvider {

        public ElementLabelProvider(final AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        @Override
        public String getText(final Object object) {
            return object instanceof Element ? ((Element) object).getName() : super.getText(object);
        }

    }
}
