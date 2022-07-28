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
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;


/**
 * 
 * @author mistria, Romain Bioteau
 *
 */
public class UpdateDataCommand extends AbstractTransactionalCommand {

	private Data data;
	private Element element;
	private String name;
	private String description;
	private String datatype;
	private Expression defaultValue;
	private AbstractProcess process;
	private String barPath;
	private String javaTypeClass;
	private boolean generateData = true;
	private String xmlNamespace;
	private String xmlElement;
	private boolean multiplicity;
	private EReference feature;
	private boolean dataIsTransient;


	public UpdateDataCommand(TransactionalEditingDomain editingDomain,Data data, String name, String description, String datatype, boolean dataMultiplicity, Expression defaultValue, String barPath, String javaTypeClass, String xmlNamespace, String xmlElement, boolean dataIsTransient) {
		super(editingDomain, UpdateDataCommand.class.getName(), getWorkspaceFiles(data));
		this.name = name;
		this.description = description;
		this.datatype = datatype;
		this.multiplicity = dataMultiplicity;
		this.defaultValue = defaultValue;
		this.data = data ;
		this.element = (Element) data.eContainer();
		this.process = ModelHelper.getMainProcess(data);
		this.barPath = barPath ;
		this.javaTypeClass = javaTypeClass;
		this.xmlNamespace = xmlNamespace;
		this.xmlElement = xmlElement;
		this.feature = data.eContainmentFeature();
	}
	
	public UpdateDataCommand(TransactionalEditingDomain editingDomain,Data data, String name, String description, String datatype, boolean dataMultiplicity, Expression defaultValue, String barPath, String javaTypeClass,String xmlNamespace, String xmlElement, boolean generateData, boolean dataIsTransient) {
		this(editingDomain,data, name, description, datatype, dataMultiplicity, defaultValue, barPath, javaTypeClass, xmlNamespace, xmlElement, dataIsTransient);
		this.generateData = generateData ;
		this.dataIsTransient = dataIsTransient;
	}


	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		DataType t = searchDataType();	

		preHandleJavaObjectAndXMLData(t);

		data.setMultiple(multiplicity);
		data.setTransient(dataIsTransient);
		data.setDefaultValue(defaultValue);
		data.setDataType(t);
		data.setName(name);
		data.setDocumentation(description);
		data.setGenerated(generateData);

		((List) element.eGet(feature)).add(data);
		ECollections.sort(((EList<?>) element.eGet(feature)),new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				return ((Element) o1).getName().compareTo(((Element) o2).getName());
			}
		});
		return CommandResult.newOKCommandResult();
	}

    protected void preHandleJavaObjectAndXMLData(DataType t) {
        if(data instanceof JavaObjectData){
			if(t.equals(data.getDataType())){
				((JavaObjectData)data).setClassName(javaTypeClass) ;
			}else{
				((List<?>) element.eGet(feature)).remove(data);
				data = ProcessFactory.eINSTANCE.createData() ;
			}
		}
		if(data instanceof XMLData){
			if(t.equals(data.getDataType())){
				((XMLData)data).setNamespace(xmlNamespace) ;
				((XMLData)data).setType(xmlElement);
			}else{
				((List<?>) element.eGet(feature)).remove(data);
				data = ProcessFactory.eINSTANCE.createData() ;
			}
		}

		if(t instanceof JavaType && !(data instanceof JavaObjectData)){
			((List<?>) element.eGet(feature)).remove(data);
			data = ProcessFactory.eINSTANCE.createJavaObjectData() ;
			((JavaObjectData)data).setClassName(javaTypeClass);
		}
		if(t instanceof XMLType && !(data instanceof XMLData)){
			((List<?>) element.eGet(feature)).remove(data);
			data = ProcessFactory.eINSTANCE.createXMLData();
			((XMLData)data).setNamespace(xmlNamespace) ;
			((XMLData)data).setType(xmlElement);
		}
    }

    protected DataType searchDataType() {
        DataType t = null ;

		for(DataType type : process.getDatatypes()){
			if(type.getName().equals(datatype)){
				t = type ; 
			}
		}
        return t;
    }

}
