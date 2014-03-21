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
package org.bonitasoft.studio.diagram.form.custom.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class WidgetMapping {


	protected static List<EClass> singleValuatedTextWidgetType ;
	static{
		singleValuatedTextWidgetType = new ArrayList<EClass>();
		singleValuatedTextWidgetType.add(FormPackage.Literals.TEXT_FORM_FIELD);
		singleValuatedTextWidgetType.add(FormPackage.Literals.PASSWORD_FORM_FIELD);
		singleValuatedTextWidgetType.add(FormPackage.Literals.TEXT_AREA_FORM_FIELD);
		singleValuatedTextWidgetType.add(FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD);
		singleValuatedTextWidgetType.add(FormPackage.Literals.TEXT_INFO);
		singleValuatedTextWidgetType.add(FormPackage.Literals.MESSAGE_INFO);
	}
	
	private boolean isGenerated;

	private boolean isMandatory;
	
	private boolean isReadOnly;
	
	private Object modelElement;

	private Widget widgetType;
	
	private List<WidgetMapping> children;
	
	private WidgetMapping parent;

	public WidgetMapping(Object modelElement){
		Assert.isNotNull(modelElement);
		this.modelElement = modelElement;
		this.children = new ArrayList<WidgetMapping>();
		setWidgetType(initializeWidgetType());
	}

	/**
	 * @return
	 */
	private Widget initializeWidgetType() {
		if(modelElement instanceof Document){
			return FormFactory.eINSTANCE.createFileWidget();
		}
		return initializeWidgetTypeForData();
	}

	protected Widget initializeWidgetTypeForData() {
		List<EClass> widgetType = getCompatibleWidgetTypes();
		if(!widgetType.isEmpty()){
			EClass eClass = widgetType.get(0);
			return (Widget) FormFactory.eINSTANCE.create(eClass);
		}
		return null;
	}

	public boolean isGenerated() {
		return isGenerated;
	}

	public void setGenerated(boolean isGenerated) {
		this.isGenerated = isGenerated;
	}

	public Object getModelElement() {
		return modelElement;
	}

	public void setModelElement(EObject modelElement) {
		this.modelElement = modelElement;
	}

	public Widget getWidgetType() {
		return widgetType;
	}

	public void setWidgetType(Widget widgetType) {
		Assert.isNotNull(widgetType);
		Assert.isLegal(getCompatibleWidgetTypes().contains(widgetType.eClass()),widgetType.eClass().getName() + " is not compatible with "+ modelElement);
		this.widgetType = widgetType;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}


	public List<EClass> getCompatibleWidgetTypes() {
		if(modelElement instanceof Data){
			return getCompatibleWidgetTypesForData((Data) modelElement);	
		}else if(modelElement instanceof Document){
			return Collections.<EClass>singletonList(FormPackage.Literals.FILE_WIDGET);
		}
		return Collections.<EClass>emptyList();
	}

	protected List<EClass> getCompatibleWidgetTypesForData(Data currentData) {
		DataType dataType = currentData.getDataType();
		if(dataType == null){
			throw new IllegalStateException(currentData+" should have a DataType");
		}
		if(currentData.isMultiple()){
			if(dataType instanceof BooleanType){
				return Collections.<EClass>singletonList(FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD);
			}
			return Collections.<EClass>singletonList(FormPackage.Literals.LIST_FORM_FIELD);
		}else{
			return getWidgetTypeForSingleData(dataType);
		}
	}

	protected List<EClass> getWidgetTypeForSingleData(DataType dataType) {
		return new ProcessSwitch<List<EClass>>(){

			public List<EClass> caseDateType(org.bonitasoft.studio.model.process.DateType object) {
				return Collections.<EClass>singletonList(FormPackage.Literals.DATE_FORM_FIELD);
			}

			public List<EClass> caseBooleanType(org.bonitasoft.studio.model.process.BooleanType object) {
				return Collections.<EClass>singletonList(FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD);
			}

			public List<EClass> caseEnumType(org.bonitasoft.studio.model.process.EnumType object) {
				List<EClass> result = new ArrayList<EClass>();
				result.add(FormPackage.Literals.RADIO_FORM_FIELD);
				result.add(FormPackage.Literals.SELECT_FORM_FIELD);
				return result;
			}

			public java.util.List<EClass> caseStringType(org.bonitasoft.studio.model.process.StringType object) {
				return singleValuatedTextWidgetType;
			}
			
			public List<EClass> defaultCase(org.eclipse.emf.ecore.EObject object) {
				return Collections.<EClass>singletonList(FormPackage.Literals.TEXT_FORM_FIELD);
			}

		}.doSwitch(dataType);
	}

	public List<? extends WidgetMapping> getChildren() {
		return children;
	}

	public void addChild(WidgetMapping child) {
		if(!children.contains(child)){
			child.setParent(this);
			children.add(child);
		}
	}

	public WidgetMapping getParent() {
		return parent;
	}

	public void setParent(WidgetMapping parent) {
		this.parent = parent;
	}

}
