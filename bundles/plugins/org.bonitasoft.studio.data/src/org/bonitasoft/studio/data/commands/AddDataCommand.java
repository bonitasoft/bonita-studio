/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.data.commands;

import java.util.Comparator;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.model.process.XMLType;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * 
 * @author mistria, Romain Bioteau
 */
public class AddDataCommand extends AbstractTransactionalCommand {

	private Data data;
	private Element container;
	private EObject process ;
	private String name;
	private String description;
	private String datatype;
	private Expression defaultValue;
	private String barPath;
	private String javaClassName;
	private boolean generateData = true;
	private String xmlNamespace;
	private String xmlElement;
	private boolean multiplicity;
	private final EStructuralFeature feature;
	private boolean isTransient = false;

	public AddDataCommand(TransactionalEditingDomain editingDomain, Element element, EStructuralFeature feature, String name, String label,String description, String datatype, boolean dataMultiplicity, Expression defaultValue, String barPath, String javaClassName, String xmlNamespace, String xmlElement, boolean isTransient) {
		super(editingDomain, Messages.addDataCommandLabel, getWorkspaceFiles(element));
		this.container = element;
		this.feature = feature;
		this.isTransient = isTransient;
		this.process = ModelHelper.getMainProcess(element) ;
		this.name = name;
		this.description = description;	
		this.datatype = datatype;
		this.multiplicity = dataMultiplicity;
		this.defaultValue = defaultValue;
		this.barPath = barPath ;
		this.javaClassName = javaClassName;
		this.xmlNamespace = xmlNamespace;
		this.xmlElement = xmlElement;
	}

	public AddDataCommand(TransactionalEditingDomain editingDomain, Element element, EStructuralFeature feature, String name,String label,String description, String datatype, boolean dataMultiplicity, Expression defaultValue, String barPath, String javaClassName, String xmlNamespace, String xmlElement, boolean generateData, boolean isTransient) {
		this(editingDomain, element, feature, name, label,description, datatype, dataMultiplicity, defaultValue, barPath, javaClassName, xmlNamespace, xmlElement,isTransient);
		this.generateData = generateData ;
	}
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		DataType t = null ;
		t = ModelHelper.getDataTypeForID((Element) process, datatype);

		if (t instanceof JavaType) {
			data = ProcessFactory.eINSTANCE.createJavaObjectData();
			((JavaObjectData)data).setClassName(javaClassName);			
		} else if (t instanceof XMLType) {
			data = ProcessFactory.eINSTANCE.createXMLData();
			((XMLData)data).setNamespace(xmlNamespace);
			((XMLData)data).setType(xmlElement);
		} else {
			data = ProcessFactory.eINSTANCE.createData();
		}

		data.setMultiple(multiplicity);
		data.setDataType(t);
		data.setDefaultValue(defaultValue);
		data.setName(name);
		data.setDocumentation(description);
		data.setGenerated(generateData) ;
		data.setTransient(isTransient);

		((EList) container.eGet(feature)).add(data);
		ECollections.sort((EList<?>)container.eGet(feature),new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				return ((Element) o1).getName().compareTo(((Element) o2).getName());
			}
		});
		return CommandResult.newOKCommandResult(data);
	}
}
