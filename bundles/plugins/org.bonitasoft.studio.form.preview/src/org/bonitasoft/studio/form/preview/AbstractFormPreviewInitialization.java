/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.form.preview;

import java.util.List;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.form.preview.i18n.Messages;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;

/**
 *@Author Aur�lie Zara
 *
 *
 */
public abstract class AbstractFormPreviewInitialization {


	protected Form form;
	protected Form formCopy;
	protected ApplicationLookNFeelFileStore lookNFeel;
	protected IBrowserDescriptor browser;
	public static final String VERSION ="1.0";
	private static final String EMPTY_LIST = "empty_list";
	private static final String GROOVY_SCRIPT_EMPTY_LIST= "[]";
	protected boolean isOnTask = false;
	protected boolean canPreview = true;




	public AbstractFormPreviewInitialization(Form form,ApplicationLookNFeelFileStore lookNFeel,IBrowserDescriptor browser) {
		this.form = form;
		this.lookNFeel = lookNFeel;
		this.browser = browser;
		initializeForm();
		initializeAllWidgets(formCopy);
	}


	public AbstractProcess createAbstractProcess(Configuration configuration){
		AbstractProcess proc = ProcessFactory.eINSTANCE.createPool();
		proc.setName(form.getName()+" preview");
		proc.setVersion(VERSION);
		if (lookNFeel !=null && lookNFeel.getName()!=null){
			proc.setBasedOnLookAndFeel(lookNFeel.getName());
		}
		Element parent =ModelHelper.getParentFlowElement(form);
		if (parent ==null){
			proc.getForm().add(formCopy);

		} else {
			if (parent instanceof Task){
				initializeTask(parent,proc,configuration);
			}
		}
		configuration.setUsername(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
		configuration.setPassword("bpm");
		return proc;
	}


	private void initializeTask(Element parent,AbstractProcess proc, Configuration configuration){
		AbstractProcess parentProc = ModelHelper.getParentProcess(parent);
		Task task= ProcessFactory.eINSTANCE.createTask();
		task.setName(((Task)parent).getName());
		task.getForm().add(formCopy);
		copyActors(parentProc, proc);
		if (((Task)parent).getActor()!=null){
			addActorToTask(task,((Task)parent).getActor());
		} else {
			if(ModelHelper.getParentContainer(parent) instanceof Lane){
				Lane lane = ModelHelper.getParentLane(parent);
				Actor actorCopy=addActorToTask(task,lane.getActor());
				proc.getActors().add(actorCopy);
			} else {
				canPreview=false;
				MessageDialog.openError(Display.getCurrent().getActiveShell(), Messages.noActorDefinedTitle, Messages.bind(Messages.noActorDefined, ((Task)parent).getName()));
			}

		}
		setActorMapping(parentProc, configuration);
		proc.setByPassFormsGeneration(true);
		proc.getElements().add(task);
		isOnTask = true;
	}


	private Actor addActorToTask(Task task,Actor actor){
		Actor actorCopy = (Actor)EcoreUtil.copy(actor);
		actorCopy.setInitiator(true);
		task.setActor(actorCopy);
		return actorCopy;
	}


	private void setActorMapping(AbstractProcess proc,Configuration previewConfiguration){
		ProcessConfigurationRepositoryStore configurationStore =  (ProcessConfigurationRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
		String id = ModelHelper.getEObjectID(proc);
		ProcessConfigurationFileStore configurationFileStore = configurationStore.getChild(id+".conf");
		Configuration configuration = configurationFileStore.getContent();
		ActorMappingsType actorMapping = EcoreUtil.copy(configuration.getActorMappings());
		if (actorMapping==null){
			//MessageDialog.openError(Display.getCurrent().getActiveShell(),Messages.noActorMappingDefinedTitle ,  Messages.noActorMappingDefined);
			//canPreview = false;
			actorMapping = ActorMappingFactory.eINSTANCE.createActorMappingsType();
			ActorMapping newMapping = ActorMappingFactory.eINSTANCE.createActorMapping();
			newMapping.setName(proc.getActors().get(0).getName());
			Users users = ActorMappingFactory.eINSTANCE.createUsers();
			users.getUser().add(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
			newMapping.setUsers(users);
			newMapping.setGroups(ActorMappingFactory.eINSTANCE.createGroups());
			newMapping.setMemberships(ActorMappingFactory.eINSTANCE.createMembership());
			newMapping.setRoles(ActorMappingFactory.eINSTANCE.createRoles());
			actorMapping.getActorMapping().add(newMapping);
		} else {
			for(ActorMapping mapping : actorMapping.getActorMapping()){
				mapping.getMemberships().getMembership().clear();
				mapping.getGroups().getGroup().clear();
				mapping.getUsers().getUser().clear();
				mapping.getRoles().getRole().clear();
				mapping.getUsers().getUser().add(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
			}
		}
		previewConfiguration.setActorMappings(actorMapping);

	}

	private void initializeForm(){
		formCopy = EcoreUtil.copy(form);
		List<Expression> exprs = ModelHelper.getAllItemsOfType(formCopy, ExpressionPackage.Literals.EXPRESSION);
		for (Expression expr:exprs){
			expr = initializeExpression(form,expr);
		}
		formCopy.getData().clear();
		formCopy.getKpis().clear();
		formCopy.getValidators().clear();
		formCopy.getActions().clear();
		formCopy.getConnectors().clear();
	}

	protected abstract void initializeAllWidgets(Form formCopy);

	private Expression initializeExpression(Form form,Expression expr){
		if (ExpressionConstants.VARIABLE_TYPE.equals(expr.getType())){
			handleVariableExpression(form, expr);
		} else if (ExpressionConstants.PARAMETER_TYPE.equals(expr.getType())){
			handleParameterExpression(expr);
		} else if (ExpressionConstants.SCRIPT_TYPE.equals(expr.getType())){
			handleScriptExpression(expr);
		}
		else if(!ExpressionConstants.CONSTANT_TYPE.equals(expr.getType())){
			toEmptyConstantExpression(expr);
		}
		return expr;
	}

	protected void toEmptyConstantExpression(Expression expr) {
		expr.setType(ExpressionConstants.CONSTANT_TYPE);
		expr.setContent("");
		expr.setName("");
		expr.getReferencedElements().clear();
	}


	protected void handleParameterExpression(Expression expr) {
		Parameter parameter = (Parameter) expr.getReferencedElements().get(0);
		expr.setType(ExpressionConstants.CONSTANT_TYPE);
		expr.setContent(parameter.getValue());
	}

	
	protected void handleScriptExpression(Expression expr){
		if (expr.getReferencedElements()==null || expr.getReferencedElements().isEmpty()){
			if (EMPTY_LIST.equals(expr.getName()) && GROOVY_SCRIPT_EMPTY_LIST.equals(expr.getContent())){
				toEmptyConstantExpression(expr);
			}
		} if (expr.getReferencedElements()!=null && !expr.getReferencedElements().isEmpty()){
			toEmptyConstantExpression(expr);
		}
	}

	protected void handleVariableExpression(Form form, Expression expr) {
		Data data = getReferencedData(form, expr);
		if (data !=null && data.getDefaultValue()!=null && data.getDefaultValue().getContent()!=null && !data.getDefaultValue().getContent().isEmpty()){
			if (data.getDataType() instanceof JavaType) {
				expr.setType(ExpressionConstants.SCRIPT_TYPE);
				expr.setInterpreter(ExpressionConstants.GROOVY);
				if (data.getDefaultValue().getReferencedElements().isEmpty()){
					expr.setContent(data.getDefaultValue().getContent());
					expr.getReferencedElements().clear();
				} else {
					toEmptyConstantExpression(expr);
				}
			} else if (data.getDataType() instanceof XMLData){
				toEmptyConstantExpression(expr);
			} else {
				String defaultValueType = data.getDefaultValue().getType();
				if (defaultValueType.equals(ExpressionConstants.SCRIPT_TYPE) || defaultValueType.equals(ExpressionConstants.CONSTANT_TYPE)  ){
					expr.setType(data.getDefaultValue().getType());
					expr.setInterpreter(data.getDefaultValue().getInterpreter());
					expr.setContent(data.getDefaultValue().getContent());
					expr.getReferencedElements().clear();
				} else {
					toEmptyConstantExpression(expr);
				}
			}
		} else {
			toEmptyConstantExpression(expr);
		}
	}

	private Data getReferencedData(Form form,Expression expr){
		List<Data> datas = ModelHelper.getAccessibleData(form, true);
		for (Data data:datas){
			if (data.getName().equals(expr.getName())){
				return data;
			}
		}
		return null;
	}


	private void copyActors(AbstractProcess proc,AbstractProcess procCopy){
		List<Actor> actors = proc.getActors();
		for (Actor actor:actors){
			procCopy.getActors().add((Actor)EcoreUtil.copy(actor));
		}
	}

	protected void deleteAllOperations(Widget widget){
		List<Operation> operations = ModelHelper.getAllItemsOfType(widget, ExpressionPackage.Literals.OPERATION);
		for (Operation operation:operations){
			EcoreUtil.delete(operation);
		}
	}

	/**
	 * @return the form
	 */
	public Form getForm() {
		return form;
	}


	/**
	 * @return the formCopy
	 */
	public Form getFormCopy() {
		return formCopy;
	}


	/**
	 * @return the lookNFeel
	 */
	public ApplicationLookNFeelFileStore getLookNFeel() {
		return lookNFeel;
	}


	/**
	 * @return the browser
	 */
	public IBrowserDescriptor getBrowser() {
		return browser;
	}


	/**
	 * @return the isOnTask
	 */
	public boolean isOnTask() {
		return isOnTask;
	}


	/**
	 * @return the canPreview
	 */
	public boolean isCanPreview() {
		return canPreview;
	}



}
