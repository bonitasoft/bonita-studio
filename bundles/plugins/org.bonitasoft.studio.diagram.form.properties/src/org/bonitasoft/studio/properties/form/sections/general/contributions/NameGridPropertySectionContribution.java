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
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.databinding.MultiValidator;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.OpenNameDialog;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractNamePropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Mickael Istria
 * @author Baptiste Mesta
 * @author Aurelien Pupier - refactored, use Abstract class
 * @author Bioteau Romain - migration to Databinding
 */
public class NameGridPropertySectionContribution extends AbstractNamePropertySectionContribution {

	private UpdateValueStrategy labelTargetToModelUpdate;
	private IObservableValue nameObserver;
	private Element workingCopy;

	/**
	 * @param tabbedPropertySheetPage
	 * @param extensibleGridPropertySection
	 */
	public NameGridPropertySectionContribution(TabbedPropertySheetPage tabbedPropertySheetPage,
			ExtensibleGridPropertySection extensibleGridPropertySection) {
		super(tabbedPropertySheetPage, extensibleGridPropertySection);
	}



	@Override
	protected void createBinding(EMFDataBindingContext context) {
		
		Converter convertToId = new Converter(String.class,String.class) {

			public Object convert(final Object fromObject) {
				updatePropertyTabTitle();
				/*Update the tab of the editor if the form name change*/
				if(element instanceof Form){
					FormDiagramEditor editor = (FormDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					editor.setPartName(text.getText());
				}

				return fromObject;
			}
		};
		labelTargetToModelUpdate = new UpdateValueStrategy();
		labelTargetToModelUpdate.setConverter(convertToId);
		List<IValidator> validators = new ArrayList<IValidator>();
		validators.add(new InputLengthValidator(Messages.name, 50));
		validators.add(new GroovyReferenceValidator(Messages.name, true,false));
		validators.add(createUniqueWidgetIdValidator());
		MultiValidator multiValidation = new MultiValidator(validators);
		labelTargetToModelUpdate.setAfterGetValidator(multiValidation) ;
		ISWTObservableValue observable = SWTObservables.observeDelayedValue(400, SWTObservables.observeText(text, SWT.Modify));
		ControlDecorationSupport.create(context.bindValue(observable, EMFEditObservables.observeValue(editingDomain, element, ProcessPackage.Literals.ELEMENT__NAME),labelTargetToModelUpdate,null),SWT.LEFT);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#getLabel()
	 */
	public String getLabel() {
		return Messages.GeneralSection_Name;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#refresh()
	 */
	@Override
	public void refresh() {

	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
	 */
	public void setEObject(EObject object) {
		element = (Element)object;
		workingCopy = EcoreUtil.copy(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution
	 * #setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.AbstractNamePropertySectionContribution#editProcessNameAndVersion()
	 */
	@Override
	protected void editProcessNameAndVersion() {
		// change the id of the form
		//   if (element instanceof Widget && element.eContainer() instanceof Form && ModelHelper.formIsCustomized((Form) element.eContainer())) {
		if ((element instanceof Widget && element.eContainer() instanceof Form)){
			Form form = (Form) element.eContainer();
			if (!workingCopy.getName().equals(element.getName())){
				workingCopy.setName(element.getName());
			}
			OpenNameDialog dialog = new OpenNameDialog(Display.getDefault().getActiveShell(),Messages.editWidgetNameTitle, element.getName());
			dialog.setMessage(Messages.widgetEditMessage);
			nameObserver = EMFEditObservables.observeValue(editingDomain, workingCopy, ProcessPackage.Literals.ELEMENT__NAME);
			dialog.setBinding(nameObserver, labelTargetToModelUpdate, null);
			if (dialog.open() == Dialog.OK) {
				if( element instanceof Widget){
					IProgressService service = PlatformUI.getWorkbench().getProgressService();
					try {
						RefactorWidgetOperation operation =new RefactorWidgetOperation((Widget)element,dialog.getNewName());
						operation.setCompoundCommand(new CompoundCommand());
						operation.refactorReferencesInScripts();
						service.busyCursorWhile(operation);
					} catch (InvocationTargetException e) {
						BonitaStudioLog.error(e);
					} catch (InterruptedException e) {
						BonitaStudioLog.error(e);
					}
				} 
				if (element instanceof Widget && element.eContainer() instanceof Form && ModelHelper.formIsCustomized((Form) element.eContainer())){
					String srcName = dialog.getSrcName();
					String name = dialog.getNewName();
					CompoundCommand cc = new CompoundCommand();
					cc.append(SetCommand.create(editingDomain, element, ProcessPackage.eINSTANCE.getElement_Name(), NamingUtils.convertToId(name)));
					editingDomain.getCommandStack().execute(cc);


					// change the template
					FormsUtils.changeIdInTemplate(form, srcName, name);
				}
			}
		}
	}

	private IValidator createUniqueWidgetIdValidator(){
		return new IValidator(){

			public IStatus validate(Object value) {
				if (element instanceof Widget){
					Widget widget = (Widget) element;
					List<Widget> widgets = ModelHelper.getAllWidgetInsidePageFlow((PageFlow) ModelHelper.getPageFlow(widget));
					for (Widget wd:widgets){
						if (!wd.equals(widget) && wd.getName().equals((String)value)){
							return ValidationStatus.error(Messages.bind(Messages.widgetNameAllreadyExistError,wd.getName()));
						}
					}
				}
				return ValidationStatus.ok();
			}

		};
	}

}
