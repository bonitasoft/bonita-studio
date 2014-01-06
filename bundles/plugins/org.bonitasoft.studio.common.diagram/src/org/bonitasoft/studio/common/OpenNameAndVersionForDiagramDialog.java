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
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
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

        public ProcessesNameVersion(AbstractProcess pool) {
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

        public void setNewName(String newName) {
            this.newName = newName;
        }

        public String getNewVersion() {
            return newVersion;
        }

        public void setNewVersion(String newVersion) {
            this.newVersion = newVersion;
        }

    }

    /**
     * @param name
     * @param version
     */
    public OpenNameAndVersionForDiagramDialog(Shell parentShell, MainProcess diagram,IRepositoryStore diagramStore) {
        super(parentShell, diagram,diagramStore);
        for(AbstractProcess pool : ModelHelper.getAllProcesses(diagram)){
            pools.add(new ProcessesNameVersion(pool));
        }
    }
    
    public OpenNameAndVersionForDiagramDialog(Shell parentShell, MainProcess diagram,IRepositoryStore diagramStore,boolean diagramNameOrVersionChangeMandatory) {
        super(parentShell, diagram,diagramStore,diagramNameOrVersionChangeMandatory);
        for(AbstractProcess pool : ModelHelper.getAllProcesses(diagram)){
            pools.add(new ProcessesNameVersion(pool));
        }
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final DataBindingContext dbc = new DataBindingContext();

        Composite res = new Composite(parent, SWT.FILL);
        res.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        res.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());

        createDiagramComposite(res, dbc);
        createProcessesNameAndVersion(res, dbc);

        DialogSupport.create(this, dbc);
        return res;
    }

    private void createDiagramComposite(Composite res, final DataBindingContext dbc) {
        Group diagramGroup = new Group(res, SWT.NONE);
        diagramGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10,10).create());
        diagramGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        diagramGroup.setText(Messages.diagram);
        createDiagramNameAndVersion(diagramGroup, dbc);
    }

    void createPNVComposite(Composite parent, final ProcessesNameVersion pnv, DataBindingContext dbc) {
        final Composite pnvCompo = new Composite(parent, SWT.NONE);
        pnvCompo.setLayout(GridLayoutFactory.fillDefaults().equalWidth(false).numColumns(4).create());
        pnvCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label poolLabel = new Label(pnvCompo, SWT.NONE);
        poolLabel.setText(Messages.name);
        final Text nameText = new Text(pnvCompo, SWT.BORDER);
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).create());

        UpdateValueStrategy nameTargetToModel = new UpdateValueStrategy();
        final EmptyInputValidator emptyValidator = new EmptyInputValidator(Messages.name);
        final InputLengthValidator lenghtValidator = new InputLengthValidator(Messages.name,50);
       
        nameTargetToModel.setAfterGetValidator(new IValidator() {

            public IStatus validate(Object value) {
                IStatus status = emptyValidator.validate(value);
                if(status.isOK()){
                    status = lenghtValidator.validate(value);
                }
                return status;
            }
        });
        nameTargetToModel.setBeforeSetValidator(new UTF8InputValidator(Messages.name));
        ControlDecorationSupport.create(dbc.bindValue(SWTObservables.observeText(nameText, SWT.Modify), PojoProperties.value("newName").observe(pnv),nameTargetToModel,null), SWT.LEFT);

        final Label poolVersion = new Label(pnvCompo, SWT.NONE);
        poolVersion.setText(Messages.version);
        final Text versionText = new Text(pnvCompo, SWT.BORDER);
        versionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(100, SWT.DEFAULT).create());

        UpdateValueStrategy versionTargetToModel = new UpdateValueStrategy();
        final EmptyInputValidator verisonEmptyValidator = new EmptyInputValidator(Messages.version);
        final InputLengthValidator versionLenghtValidator = new InputLengthValidator(Messages.version,50);
        versionTargetToModel.setAfterGetValidator(new IValidator() {
            public IStatus validate(Object value) {
                IStatus status = verisonEmptyValidator.validate(value);
                if(status.isOK()){
                    status = versionLenghtValidator.validate(value);
                }
                return status;
            }
        });
		
        versionTargetToModel.setBeforeSetValidator(new UTF8InputValidator(Messages.version));
        ControlDecorationSupport.create(dbc.bindValue(SWTObservables.observeText(versionText, SWT.Modify), PojoProperties.value("newVersion").observe(pnv),versionTargetToModel,null), SWT.LEFT);
   
        
        final ISWTObservableValue observeNameText = SWTObservables.observeText(nameText, SWT.Modify);
		final ISWTObservableValue observeVersionText = SWTObservables.observeText(versionText, SWT.Modify);
		final MultiValidator caseValidator = new MultiValidator() {
			@Override
			protected IStatus validate() {
				final String name = observeNameText.getValue().toString();
				final String version = observeVersionText.getValue().toString();
				int countNewProcessWithSameName = 0;
				for (ProcessesNameVersion pool : pools){
					if (name.equals(pool.newName) && version.equals(pool.newVersion)) {
						countNewProcessWithSameName ++;
					}
				}
				if(countNewProcessWithSameName>1){
					return ValidationStatus.error(Messages.bind(Messages.differentCaseSameNameError, typeLabel));
				} 
				int countOldProcessWithSameName = 0;
				for (ProcessesNameVersion pool : pools){
					if (name.equals(pool.getAbstractProcess().getName()) && version.equals(pool.getAbstractProcess().getVersion())) {
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
				for (AbstractProcess process : processes) {
					if (name.equals(process.getName()) && version.equals(process.getVersion())) {
						return ValidationStatus.error(Messages.bind(Messages.differentCaseSameNameError, typeLabel));
					}
				}
			return ValidationStatus.ok();
			}
			
		};
		dbc.addValidationStatusProvider(caseValidator);
		ControlDecorationSupport.create(caseValidator, SWT.LEFT);
 }

    protected void createProcessesNameAndVersion(Composite res, final DataBindingContext dbc) {
        Group parent = new Group(res, SWT.NONE);
        parent.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
        parent.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        parent.setText(Messages.pools);

        for(ProcessesNameVersion pnv : pools){
            createPNVComposite(parent, pnv, dbc);
        }
    }

    public List<ProcessesNameVersion> getPools() {
        return pools;
    }


}
