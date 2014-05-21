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

package org.bonitasoft.studio.groovy.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.groovy.Messages;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;


/**
 * @author Romain Bioteau
 *
 */
public class GroovyFunction implements IFunction {

	private IMethod methodNode;
	private String documentation;

	public GroovyFunction(IMethod m) {
		methodNode = m ;
		addDocumentation();	
	}

	public GroovyFunction(IMethod m,IFunctionCategory category) {
		methodNode = m ;
		addDocumentation();	
		addToLibrary(category);
	}

	private void addDocumentation() {
		try {
			String javadoc = ""; //$NON-NLS-1$
			if(methodNode.getSource()!= null){
				IDocument source =  new Document(methodNode.getSource());
				if(methodNode.getJavadocRange() != null){
					javadoc = methodNode.getAttachedJavadoc(Repository.NULL_PROGRESS_MONITOR);
					if(javadoc == null){
						javadoc = source.get(0,methodNode.getJavadocRange().getLength());
						javadoc = javadoc.replace("/*", "");
						javadoc = javadoc.replace("*/", "");
						javadoc = javadoc.replace("*", "");
						javadoc = javadoc.replace("@", "<br/>@");
					}
				}else{
					javadoc = Messages.noDocumentationAvailable ; //$NON-NLS-1$
				}
				setDocumentation(javadoc);	
			}
		}catch (Exception e) {
			BonitaStudioLog.error(e);
			setDocumentation("");	 //$NON-NLS-1$
		}

	}

	private void addToLibrary(IFunctionCategory category) {
		boolean isStringFunction = false ;
		boolean isNumberFunction = false ;
		boolean isColletionFunction = false ;
		if(category != null){
			if(category.equals(FunctionsRepositoryFactory.getUserFunctionCatgory())){
				category.addFunction(this);
				return ;
			}else if(category.equals(FunctionsRepositoryFactory.getBonitaFunctionCatgory())){
				category.addFunction(this);
				return ;
			}
		}
		for(String type : methodNode.getParameterTypes()){
			if(!isColletionFunction && !isNumberFunction && !isStringFunction){
				if(isString(type)){
					isStringFunction = true ;
					break;
				}else if(isCollection(type)){
					isColletionFunction = true ;
					break;
				}else if(isNumber(type)){
					isNumberFunction = true ;
					break;
				}
			}
		}

		if(isStringFunction){
			FunctionsRepositoryFactory.getStringFunctionCatgory().addFunction(this);
		}else if(isColletionFunction){
			FunctionsRepositoryFactory.getCollectionFunctionCatgory().addFunction(this);
		}else if(isNumberFunction){
			FunctionsRepositoryFactory.getNumberFunctionCatgory().addFunction(this);
		}else{
			FunctionsRepositoryFactory.getOtherFunctionCatgory().addFunction(this);
		}

	}


	private boolean isString(String type) {
		return type.equals(Signature.createTypeSignature(Character.class.getName(), true)) 
				|| type.equals(Signature.createTypeSignature(CharSequence.class.getName(), true)) 
				|| type.equals(Signature.createTypeSignature(String.class.getName(), true)) ;
	}


	private boolean isCollection(String type) {
		return type.equals(Signature.createTypeSignature(Collection.class.getName(), true)) 
				|| type.equals(Signature.createTypeSignature(Iterator.class.getName(), true)) ;
	}

	private boolean isNumber(String type) {
		return type.equals(Signature.createTypeSignature(Number.class.getName(), true)) 
				|| type.equals(Signature.createTypeSignature(Integer.class.getName(), true)) 
				|| type.equals(Signature.createTypeSignature(Float.class.getName(), true))
				|| type.equals(Signature.createTypeSignature(Long.class.getName(), true))
				|| type.equals(Signature.createTypeSignature(Double.class.getName(), true));
	}

	public String getName() {
		return methodNode.getElementName();
	}

	public String getOwner() {
		return methodNode.getDeclaringType().getFullyQualifiedName();
	}

	public String getReturnedType() {
		try {
			return methodNode.getReturnType();
		} catch (JavaModelException e) {
			BonitaStudioLog.error(e);
			return ""; //$NON-NLS-1$
		}
	}

	public IMethod getFunction() {
		return methodNode;
	}


	public String getDocumentation() {
		if(documentation == null)
			return ""; //$NON-NLS-1$
		return documentation;
	}

	public void setDocumentation(String doc) {
		this.documentation=doc ;
	}

	public int getParametersCount() {
		return methodNode.getNumberOfParameters();
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof GroovyFunction){
			GroovyFunction other = (GroovyFunction)obj;
			try {
				return methodNode.getSignature().equals(other.methodNode.getSignature()) && methodNode.getElementName().equals(other.methodNode.getElementName()) && methodNode.getResource() != null &&  ((GroovyFunction)other).methodNode.getResource() != null &&  methodNode.getResource().equals(((GroovyFunction)other).methodNode.getResource());
			} catch (Exception ex) {
				BonitaStudioLog.error(ex);
				return false;
			}
		}else{
			return false;
		}

	}

	/**
	 * Beware: This method is very time-consuming, avoid using it in loops and so on
	 */
	public String getSignature() {
		try {
			return Signature.toString(methodNode.getSignature(),methodNode.getElementName(),methodNode.getParameterNames(),false,true) ;
		} catch (JavaModelException e) {
			BonitaStudioLog.error(e);
			return ""; //$NON-NLS-1$
		}
	}

	public String getContent() {
		return "" ; //$NON-NLS-1$
	}


	public String getParameters() {
		List<String> paramNames = new ArrayList<String>();
		String result = "" ; //$NON-NLS-1$

		try {
			for(String s : methodNode.getParameterNames())
				paramNames.add(s);
		} catch (JavaModelException e) {
			BonitaStudioLog.error(e);
		}

		for(String name : paramNames){
			result = result.concat(name+","); //$NON-NLS-1$
		}

		result = result.substring(0, result.lastIndexOf(",")); //$NON-NLS-1$


		return  result ;

	}

	public String getId() {
		return getSignature()+" "+getOwner(); //$NON-NLS-1$
	}

	public boolean isStatic() {
		try {

			return Flags.isStatic(methodNode.getFlags()) && !getOwner().equals((DefaultGroovyMethods.class.getSimpleName()));
		} catch (JavaModelException e) {
			BonitaStudioLog.error(e);
			return false ;
		}
	}


}
