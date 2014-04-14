/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.SpecialCharactersValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.UTF8InputValidator;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
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
import org.eclipse.gmf.runtime.diagram.core.view.factories.DiagramFactory;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.osgi.storagemanager.StorageManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 * 
 */
public class OpenNameAndVersionDialog extends Dialog {

	protected String diagramName;
	protected String diagramVersion;
	protected String srcName;
	protected String srcVersion;
	private final IRepositoryStore diagramStore;
	protected final String typeLabel;
	private boolean isDiagram = false;
	protected boolean diagramNameOrVersionChangeMandatory = false;
	private HashSet<String> existingFileNames;
	public List<AbstractProcess> processes;
	

	protected OpenNameAndVersionDialog(Shell parentShell, MainProcess diagram, IRepositoryStore diagramStore) {
		super(parentShell);
		isDiagram = true;
		diagramName = diagram.getName();
		diagramVersion = diagram.getVersion();
		srcName = diagram.getName();
		srcVersion = diagram.getVersion();
		this.diagramStore = diagramStore;
		typeLabel = Messages.diagram.toLowerCase();
		listExistingFileNames(diagramStore);
		listExistingAbstractProcess(diagramStore);
	}

	public OpenNameAndVersionDialog(Shell parentShell, MainProcess diagram, String poolName, String versionName, IRepositoryStore diagramStore) {
		super(parentShell);
		diagramName = poolName;
		diagramVersion = versionName;
		srcName = poolName;
		srcVersion = versionName;
		this.diagramStore = diagramStore;
		typeLabel = Messages.Pool_title.toLowerCase();
		listExistingFileNames(diagramStore);
		listExistingAbstractProcess(diagramStore);
	}

	public OpenNameAndVersionDialog(Shell parentShell, MainProcess diagram, IRepositoryStore diagramStore, boolean diagramNameOrVersionChangeMandatory) {
		super(parentShell);
		isDiagram = true;
		diagramName = diagram.getName();
		diagramVersion = diagram.getVersion();
		srcName = diagram.getName();
		srcVersion = diagram.getVersion();
		this.diagramStore = diagramStore;
		typeLabel = Messages.Pool_title.toLowerCase();
		this.diagramNameOrVersionChangeMandatory = diagramNameOrVersionChangeMandatory;
		listExistingFileNames(diagramStore);
		listExistingAbstractProcess(diagramStore);
	}

	protected void listExistingFileNames(IRepositoryStore diagramStore) {
		existingFileNames = new HashSet<String>();
		String[] files = diagramStore.getResource().getLocation().toFile().list(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.endsWith(".proc");
			}
		});
		for (String f : files) {
			existingFileNames.add(f.toLowerCase());
		}
	}

	public void listExistingAbstractProcess(IRepositoryStore diagramStore) {
		processes = new ArrayList<AbstractProcess>();
		final List<IRepositoryFileStore> l = diagramStore.getChildren();
		for (IRepositoryFileStore irepStore : l) {
			final MainProcess m = (MainProcess) irepStore.getContent();
			processes.addAll(ModelHelper.getAllProcesses(m));
		}		
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.openNameAndVersionDialogTitle);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final DataBindingContext dbc = new DataBindingContext();

		Composite res = new Composite(parent, SWT.FILL);
		res.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		res.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());

		createDiagramNameAndVersion(res, dbc);

		DialogSupport.create(this, dbc);
		return res;
	}

	protected void createDiagramNameAndVersion(Composite res, final DataBindingContext dbc) {
		final Label nameLabel = new Label(res, SWT.NONE);
		nameLabel.setText(Messages.name);
		nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

		final Text nameText = new Text(res, SWT.BORDER);
		nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).create());

		final Label versionLabel = new Label(res, SWT.NONE);
		versionLabel.setText(Messages.version);

		final Text versionText = new Text(res, SWT.BORDER);
		versionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

		final ISWTObservableValue observeNameText = SWTObservables.observeText(nameText, SWT.Modify);
		final ISWTObservableValue observeVersionText = SWTObservables.observeText(versionText, SWT.Modify);
		final MultiValidator caseValidator = new MultiValidator() {

			@Override
			protected IStatus validate() {
				final String name = observeNameText.getValue().toString();
				final String version = observeVersionText.getValue().toString();
				
				return validateModification(name, version);				
			}
		};

		dbc.addValidationStatusProvider(caseValidator);
		ControlDecorationSupport.create(caseValidator, SWT.LEFT);
		final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		final EmptyInputValidator emptyValidator = new EmptyInputValidator(Messages.name);
		final InputLengthValidator lenghtValidator = new InputLengthValidator(Messages.name, 50);
		final SpecialCharactersValidator specialCharValidator = new SpecialCharactersValidator();
		targetToModel.setAfterGetValidator(new IValidator() {

			public IStatus validate(Object value) {
				IStatus status = emptyValidator.validate(value);
				if (status.isOK()) {
					status = lenghtValidator.validate(value);
					if (status.isOK()) {
						status = specialCharValidator.validate(value);
					}
				}
				return status;
			}
		});

		targetToModel.setBeforeSetValidator(new UTF8InputValidator(Messages.name));
		ControlDecorationSupport.create(
				dbc.bindValue(observeNameText, PojoProperties.value("diagramName").observe(this), targetToModel, null),
				SWT.LEFT);

		final UpdateValueStrategy versionTargetToModel = new UpdateValueStrategy();
		final EmptyInputValidator verisonEmptyValidator = new EmptyInputValidator(Messages.version);
		final InputLengthValidator versionLenghtValidator = new InputLengthValidator(Messages.version, 50);
		versionTargetToModel.setAfterGetValidator(new IValidator() {

			public IStatus validate(Object value) {
				IStatus status = verisonEmptyValidator.validate(value);
				if (status.isOK()) {
					status = versionLenghtValidator.validate(value);
				}
				return status;
			}
		});
		versionTargetToModel.setBeforeSetValidator(new UTF8InputValidator(Messages.version));
		ControlDecorationSupport.create(dbc.bindValue(observeVersionText,
				PojoProperties.value("diagramVersion").observe(this), versionTargetToModel, null), SWT.LEFT);

	}
	
	public IStatus validateModification(String name, String version){
		if (isDiagram) {
			final String newDiagramFilename = NamingUtils.toDiagramFilename(name, version);
			IRepositoryFileStore fileStore = diagramStore.getChild(newDiagramFilename);
			if (diagramNameOrVersionChangeMandatory && srcName.equals(name) && srcVersion.equals(version)) {
				return ValidationStatus.error(Messages.diagramNameOrVersionMustBeChanged);
			}
			if (fileStore != null && !srcName.equals(name) && !srcVersion.equals(version)) {
				return ValidationStatus.error(Messages.bind(Messages.diagramAlreadyExists, typeLabel));
			}

			for (String existingFileName : existingFileNames) {
				if (!NamingUtils.toDiagramFilename(srcName, srcVersion).equals(newDiagramFilename)
						&& existingFileName.equals(newDiagramFilename.toLowerCase())) {
					return ValidationStatus.error(Messages.bind(Messages.differentCaseSameNameError, typeLabel));
				}
			}		
		} else {
			if (name.equals(srcName) && version.equals(srcVersion)) {
				return ValidationStatus.ok();
			}
			for (AbstractProcess process : processes) {
				if (name.equals(process.getName()) && version.equals(process.getVersion())) {
					return ValidationStatus.error(Messages.bind(Messages.differentCaseSameNameError, typeLabel));
				}
			}
		}

		return ValidationStatus.ok();
	}

	public String getDiagramName() {
		return diagramName;
	}

	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}

	public String getDiagramVersion() {
		return diagramVersion;
	}

	public void setDiagramVersion(String diagramVersion) {
		this.diagramVersion = diagramVersion;
	}

}
