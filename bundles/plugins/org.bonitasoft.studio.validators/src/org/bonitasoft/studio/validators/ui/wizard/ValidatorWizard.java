/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.validators.ui.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.forms.server.validator.IFormFieldValidator;
import org.bonitasoft.forms.server.validator.IFormPageValidator;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorFactory;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorType;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Aurelien Pupier
 *
 */
public class ValidatorWizard extends Wizard {

	protected ValidatorWizardPage page;
	/**
	 * The interface that will be implemented, depending if it is a Page Validator or a FormField Validator.
	 */
	private Class<?> interfacee;
	private IRepositoryFileStore fileStore;
	private final IRepositoryStore validatorDescriptorStore;
	private final IRepositoryStore validatorSourceStore;
	private String validatorClassName;
	private final ValidatorDescriptor validator;
	private final boolean editMode;
	private ValidatorDescriptor orginalValidator;

	public ValidatorWizard() {
		setWindowTitle(Messages.createValidator);
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Pics.getWizban()) ;
		validatorDescriptorStore = RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;
		validatorSourceStore =  RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class) ;
		validator = ValidatorFactory.eINSTANCE.createValidatorDescriptor() ;
		editMode = false ;
	}

	public ValidatorWizard(ValidatorDescriptor validator) {
		setWindowTitle(Messages.editValidator);
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(Pics.getWizban()) ;
		validatorDescriptorStore = RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;
		validatorSourceStore =  RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class) ;
		fileStore = validatorDescriptorStore.getChild(validator.getName()+"."+ValidatorDescriptorRepositoryStore.VALIDATOR_EXT) ;
		orginalValidator = validator ;
		this.validator = EcoreUtil.copy(validator) ;
		editMode = true ;
	}


	public ValidatorDescriptor getOrginalValidator() {
		return orginalValidator;
	}

	@Override
	public void addPages() {
		page = new ValidatorWizardPage(validator,editMode);
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		if(validator.getType() == ValidatorType.PAGE_VALIDATOR){
			interfacee = IFormPageValidator.class;
		} else {
			interfacee = IFormFieldValidator.class;
		}
		IRunnableWithProgress runnable = new IRunnableWithProgress(){

			@Override
			public void run(IProgressMonitor monitor){
				monitor.beginTask(Messages.createValidatorWizardPage_generateValidator, 10);
				generateValidator(validator, interfacee, monitor);
				String fileName = validator.getName()+"."+ValidatorDescriptorRepositoryStore.VALIDATOR_EXT ;
				if(editMode){              
					if(!fileStore.getName().equals(fileName)){
						fileStore.delete() ;
					}
				}
				fileStore = validatorDescriptorStore.createRepositoryFileStore(fileName) ;
				fileStore.save(validator) ;

				//refactor validator reference in model
				if(orginalValidator != null && orginalValidator.getClassName() != null && validator != null && !orginalValidator.getClassName().equals(validator.getClassName())){
					DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
					for(AbstractProcess p :  store.getAllProcesses()){
						List<Validator> validators = ModelHelper.getAllItemsOfType(p, FormPackage.Literals.VALIDATOR);
						for(Validator v : validators){
							if(v.getValidatorClass().equals(orginalValidator.getClassName())){
								EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(v);
								domain.getCommandStack().execute(SetCommand.create(domain, v, FormPackage.Literals.VALIDATOR__VALIDATOR_CLASS, validator.getClassName()));
								try {
									v.eResource().save(ProcessDiagramEditorUtil.getSaveOptions());
								} catch (IOException e) {
									BonitaStudioLog.error(e);
								}
							}
						}
					}
				}

				ValidatorSourceRepositorySotre sourceStore = (ValidatorSourceRepositorySotre) RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class) ;
				SourceFileStore sourceFile = (SourceFileStore) sourceStore.getChild(validator.getClassName()) ;
				if(sourceFile != null){
					sourceFile.open() ;
				}
			}
		};

		try {
			/*Use the wizard container to run the runnable*/
			getContainer().run(false, false, runnable);
		} catch (InvocationTargetException e) {
			BonitaStudioLog.error(e);
		} catch (InterruptedException e) {
			BonitaStudioLog.error(e);
		}
		return true;
	}


	public IType generateValidator(ValidatorDescriptor validator, Class<?> baseClass, IProgressMonitor progressMonitor){
		IType classType;
		try {
			final String qualifiedClassname = validator.getClassName() ;
			String packageName = "" ;
			String className = qualifiedClassname ;

			if(qualifiedClassname.indexOf(".") != -1){
				packageName = qualifiedClassname.substring(0,qualifiedClassname.lastIndexOf(".")) ;
				className = qualifiedClassname.substring(qualifiedClassname.lastIndexOf(".")+1,qualifiedClassname.length()) ;
			}
			if(orginalValidator != null){
				if(!orginalValidator.getClassName().equals(qualifiedClassname)){
					SourceFileStore sourceFile = (SourceFileStore) validatorSourceStore.getChild(orginalValidator.getClassName()) ;
					sourceFile.rename(qualifiedClassname) ;
				}
			}
			classType = RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findType(qualifiedClassname) ;

			if(classType == null){
				/*Generate the validator class*/
				classType = generateValidatorClass(packageName,
						className,
						baseClass,
						progressMonitor);
			}
			return classType ;
		} catch (Exception e) {
			BonitaStudioLog.error(e);
			return null;
		}
	}


	/**
	 * @param packageName
	 * @param className
	 * @param baseClass
	 * @param fields
	 * @param progressMonitor
	 * @return
	 * @throws Exception
	 */
	private IType generateValidatorClass(String packageName, String className, Class<?> baseClass,IProgressMonitor progressMonitor) throws Exception {
		/*Use the NawClassWIzardPage to create the calss without pop up it*/
		NewClassWizardPage classWizard = new NewClassWizardPage();
		List<String> interfaces = new ArrayList<String>();
		progressMonitor.worked(1);
		interfaces.add(baseClass.getName());
		classWizard.setSuperInterfaces(interfaces, false);
		//	classWizard.setSuperClass(AbstractFormValidator.class.getName(), false);
		progressMonitor.worked(1);
		classWizard.setAddComments(true, false);
		classWizard.setModifiers(classWizard.F_PUBLIC, false);
		classWizard.setTypeName(className, false);
		classWizard.setMethodStubSelection(false, false, true, false);

		IResource srcFolder = validatorSourceStore.getResource() ;
		IJavaProject javaProject = (IJavaProject) srcFolder.getProject().getNature(JavaCore.NATURE_ID) ;
		IPackageFragmentRoot packageFragmentRoot = javaProject.getPackageFragmentRoot(srcFolder);
		classWizard.setPackageFragmentRoot(packageFragmentRoot, false);
		IPackageFragment packageFragment = packageFragmentRoot.getPackageFragment(packageName == null ? "" : packageName);
		if (! packageFragment.exists()) {
			packageFragment = packageFragmentRoot.createPackageFragment(packageName, true, progressMonitor);
		}
		classWizard.setPackageFragment(packageFragment, false);
		progressMonitor.worked(1);
		classWizard.createType(progressMonitor);

		IType classType = classWizard.getCreatedType();

		/*modify the generated class to implement the displayname method*/
		progressMonitor.worked(1);
		//		IMethod displayNameMethod = classType.getMethod("getDisplayName", new String[]{});
		//		if(displayNameMethod!=null && displayNameMethod.exists()){
		//			displayNameMethod.delete(true, progressMonitor);
		//		}
		//
		//		String contents = generateDisplayNameContent(displayName);
		//		classType.createMethod(contents, null, true, progressMonitor);

		return classType;
	}

	public String getValidatorId(){
		return validatorClassName;
	}

}
