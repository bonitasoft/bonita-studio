/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.common;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.UTF8InputValidator;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class OpenNameAndVersionForDiagramDialog extends OpenNameAndVersionDialog {

    private final List<ProcessesNameVersion> pools = new ArrayList<OpenNameAndVersionForDiagramDialog.ProcessesNameVersion>();

    public class ProcessesNameVersion{

        protected AbstractProcess abstractProcess;
        protected String newName;
        protected String newVersion;

        public ProcessesNameVersion(final AbstractProcess pool) {
            abstractProcess = pool;
            newName = pool.getName();
            newVersion = pool.getVersion();
        }

        public AbstractProcess getAbstractProcess() {
            return abstractProcess;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(final String newName) {
            this.newName = newName;
        }

        public String getNewVersion() {
            return newVersion;
        }

        public void setNewVersion(final String newVersion) {
            this.newVersion = newVersion;
        }

    }

    /**
     * @param name
     * @param version
     */
    public OpenNameAndVersionForDiagramDialog(final Shell parentShell, final MainProcess diagram,final IRepositoryStore diagramStore) {
        super(parentShell, diagram,diagramStore);
        for(final AbstractProcess pool : ModelHelper.getAllProcesses(diagram)){
            pools.add(new ProcessesNameVersion(pool));
        }
    }

    public OpenNameAndVersionForDiagramDialog(final Shell parentShell, final MainProcess diagram,final IRepositoryStore diagramStore,final boolean diagramNameOrVersionChangeMandatory) {
        super(parentShell, diagram,diagramStore,diagramNameOrVersionChangeMandatory);
        for(final AbstractProcess pool : ModelHelper.getAllProcesses(diagram)){
            pools.add(new ProcessesNameVersion(pool));
        }
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final DataBindingContext dbc = new DataBindingContext();

        final Composite res = new Composite(parent, SWT.FILL);
        res.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        res.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());

        createDiagramComposite(res, dbc);
        createProcessesNameAndVersion(res, dbc);

        DialogSupport.create(this, dbc);
        return res;
    }

    private void createDiagramComposite(final Composite res, final DataBindingContext dbc) {
        final Group diagramGroup = new Group(res, SWT.NONE);
        diagramGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10,10).create());
        diagramGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        diagramGroup.setText(Messages.diagram);
        createDiagramNameAndVersion(diagramGroup, dbc);
    }

    void createPNVComposite(final Composite parent, final ProcessesNameVersion pnv, final DataBindingContext dbc) {
        final Composite pnvCompo = new Composite(parent, SWT.NONE);
        pnvCompo.setLayout(GridLayoutFactory.fillDefaults().equalWidth(false).numColumns(4).create());
        pnvCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label poolNameLabel = new Label(pnvCompo, SWT.NONE);
        poolNameLabel.setText(Messages.name);
        final Text poolNameText = new Text(pnvCompo, SWT.BORDER);
        poolNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).create());

        final ISWTObservableValue observePoolNameText = SWTObservables.observeText(poolNameText, SWT.Modify);

        bindPoolName(pnv, dbc, observePoolNameText);

        final Label poolVersion = new Label(pnvCompo, SWT.NONE);
        poolVersion.setText(Messages.version);
        final Text poolVersionText = new Text(pnvCompo, SWT.BORDER);
        poolVersionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(100, SWT.DEFAULT).create());

        final ISWTObservableValue observePoolVersionText = SWTObservables.observeText(poolVersionText, SWT.Modify);

        bindPoolVersion(pnv, dbc, observePoolVersionText);

        final MultiValidator caseValidator = new MultiValidator() {
            @Override
            protected IStatus validate() {
                final String poolName = observePoolNameText.getValue().toString();
                final String poolVersion = observePoolVersionText.getValue().toString();
                int countNewProcessWithSameName = 0;
                for (final ProcessesNameVersion pool : pools){
                    if (poolName.equals(pool.newName) && poolVersion.equals(pool.newVersion)) {
                        countNewProcessWithSameName ++;
                    }
                }
                if(countNewProcessWithSameName>1){
                    return ValidationStatus.error(Messages.bind(Messages.differentCaseSameNameError, typeLabel));
                }
                int countOldProcessWithSameName = 0;
                for (final ProcessesNameVersion pool : pools){
                    if (poolName.equals(pool.getAbstractProcess().getName()) && poolVersion.equals(pool.getAbstractProcess().getVersion())) {
                        countOldProcessWithSameName ++;
                    }
                }
                if(countOldProcessWithSameName==1){
                    if(diagramNameOrVersionChangeMandatory){
                        return ValidationStatus.error(Messages.bind(Messages.differentCaseSameNameError, typeLabel));
                    } else {
                        return ValidationStatus.ok();
                    }
                }
                for (final AbstractProcess process : processes) {
                    if (poolName.equals(process.getName()) && poolVersion.equals(process.getVersion())) {
                        return ValidationStatus.error(Messages.bind(Messages.differentCaseSameNameError, typeLabel));
                    }
                }
                return ValidationStatus.ok();
            }

        };
        dbc.addValidationStatusProvider(caseValidator);
        ControlDecorationSupport.create(caseValidator, SWT.LEFT);
    }

    private void bindPoolVersion(final ProcessesNameVersion pnv, final DataBindingContext dbc, final ISWTObservableValue observePoolVersionText) {
        final UpdateValueStrategy poolVersionTargetToModel = new UpdateValueStrategy();
        final EmptyInputValidator poolVersionEmptyValidator = new EmptyInputValidator(Messages.version);
        final InputLengthValidator poolVersionLenghtValidator = new InputLengthValidator(Messages.version,50);
        poolVersionTargetToModel.setAfterGetValidator(new IValidator() {
            @Override
            public IStatus validate(final Object value) {
                IStatus status = poolVersionEmptyValidator.validate(value);
                if(status.isOK()){
                    status = poolVersionLenghtValidator.validate(value);
                }
                return status;
            }
        });

        poolVersionTargetToModel.setBeforeSetValidator(new UTF8InputValidator(Messages.version));
        final IObservableValue observePoolVersionValue = PojoProperties.value("newVersion").observe(pnv);
        final Binding bindPoolVersionValue = dbc.bindValue(observePoolVersionText,
                observePoolVersionValue,
                poolVersionTargetToModel,
                null);
        ControlDecorationSupport.create(bindPoolVersionValue, SWT.LEFT);
    }

    private void bindPoolName(final ProcessesNameVersion pnv, final DataBindingContext dbc, final ISWTObservableValue observePoolNameText) {
        final UpdateValueStrategy poolNameTargetToModel = new UpdateValueStrategy();
        final EmptyInputValidator poolNameEmptyValidator = new EmptyInputValidator(Messages.name);
        final InputLengthValidator poolNamelenghtValidator = new InputLengthValidator(Messages.name,50);

        poolNameTargetToModel.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                IStatus status = poolNameEmptyValidator.validate(value);
                if(status.isOK()){
                    status = poolNamelenghtValidator.validate(value);
                }
                return status;
            }
        });
        poolNameTargetToModel.setBeforeSetValidator(new UTF8InputValidator(Messages.name));

        final IObservableValue observePoolNameValue = PojoProperties.value("newName").observe(pnv);
        final Binding bindPoolNameValue = dbc.bindValue(observePoolNameText,
                observePoolNameValue,
                poolNameTargetToModel,
                null);
        ControlDecorationSupport.create(bindPoolNameValue, SWT.LEFT);
    }

    protected void createProcessesNameAndVersion(final Composite res, final DataBindingContext dbc) {
        final Group poolGroup = new Group(res, SWT.NONE);
        poolGroup.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
        poolGroup.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        poolGroup.setText(Messages.pools);

        for(final ProcessesNameVersion pnv : pools){
            createPNVComposite(poolGroup, pnv, dbc);
        }
    }

    public List<ProcessesNameVersion> getPools() {
        return pools;
    }


}
