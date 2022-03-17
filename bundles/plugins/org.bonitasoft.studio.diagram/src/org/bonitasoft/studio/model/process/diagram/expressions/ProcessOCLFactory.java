/*
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
package org.bonitasoft.studio.model.process.diagram.expressions;

import java.util.Collections;
import java.util.Map;

import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.EcoreFactory;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.ecore.Variable;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.options.ParsingOptions;

/**
 * @generated
 */
public class ProcessOCLFactory {

	/**
	* @generated
	*/
	private final ProcessAbstractExpression[] expressions;

	/**
	* @generated
	*/
	private final String[] expressionBodies;

	/**
	* @generated
	*/
	protected ProcessOCLFactory() {
		this.expressions = new ProcessAbstractExpression[1];
		this.expressionBodies = new String[] { "\'1.0\'", //$NON-NLS-1$
		};
	}

	/**
	* @generated
	*/
	private static ProcessOCLFactory getInstance() {
		ProcessOCLFactory instance = ProcessDiagramEditorPlugin.getInstance().getProcessOCLFactory();
		if (instance == null) {
			ProcessDiagramEditorPlugin.getInstance().setProcessOCLFactory(instance = new ProcessOCLFactory());
		}
		return instance;
	}

	/**
	* @generated
	*/
	public static String getExpressionBody(int index) {
		return getInstance().expressionBodies[index];
	}

	/**
	* @generated
	*/
	public static ProcessAbstractExpression getExpression(int index, EClassifier context,
			Map<String, EClassifier> environment) {
		ProcessOCLFactory cached = getInstance();
		if (index < 0 || index >= cached.expressions.length) {
			throw new IllegalArgumentException();
		}
		if (cached.expressions[index] == null) {
			cached.expressions[index] = getExpression(cached.expressionBodies[index], context,
					environment == null ? Collections.<String, EClassifier>emptyMap() : environment);
		}
		return cached.expressions[index];
	}

	/**
	* This is factory method, callers are responsible to keep reference to the return value if they want to reuse parsed expression
	* @generated
	*/
	public static ProcessAbstractExpression getExpression(String body, EClassifier context,
			Map<String, EClassifier> environment) {
		return new Expression(body, context, environment);
	}

	/**
	* This method will become private in the next release
	* @generated
	*/
	public static ProcessAbstractExpression getExpression(String body, EClassifier context) {
		return getExpression(body, context, Collections.<String, EClassifier>emptyMap());
	}

	/**
	* @generated
	*/
	private static class Expression extends ProcessAbstractExpression {

		/**
		* @generated
		*/
		private final org.eclipse.ocl.ecore.OCL oclInstance;

		/**
		* @generated
		*/
		private OCLExpression oclExpression;

		/**
		* @generated
		*/
		public Expression(String body, EClassifier context, Map<String, EClassifier> environment) {
			super(body, context);
			oclInstance = org.eclipse.ocl.ecore.OCL.newInstance();
			initCustomEnv(oclInstance.getEnvironment(), environment);
			Helper oclHelper = oclInstance.createOCLHelper();
			oclHelper.setContext(context());
			try {
				oclExpression = oclHelper.createQuery(body());
				setStatus(IStatus.OK, null, null);
			} catch (ParserException e) {
				setStatus(IStatus.ERROR, e.getMessage(), e);
			}
		}

		/**
		* @generated
		*/
		@SuppressWarnings("rawtypes")
		protected Object doEvaluate(Object context, Map env) {
			if (oclExpression == null) {
				return null;
			}
			// on the first call, both evalEnvironment and extentMap are clear, for later we have finally, below.
			EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = oclInstance.getEvaluationEnvironment();
			// initialize environment
			for (Object nextKey : env.keySet()) {
				evalEnv.replace((String) nextKey, env.get(nextKey));
			}
			try {
				Object result = oclInstance.evaluate(context, oclExpression);
				return oclInstance.isInvalid(result) ? null : result;
			} finally {
				evalEnv.clear();
				oclInstance.setExtentMap(null); // clear allInstances cache, and get the oclInstance ready for the next call
			}
		}

		/**
		* @generated
		*/
		private static void initCustomEnv(Environment<?, EClassifier, ?, ?, ?, EParameter, ?, ?, ?, ?, ?, ?> ecoreEnv,
				Map<String, EClassifier> environment) {
			// Use EObject as implicit root class for any object, to allow eContainer() and other EObject operations from OCL expressions
			ParsingOptions.setOption(ecoreEnv, ParsingOptions.implicitRootClass(ecoreEnv),
					EcorePackage.eINSTANCE.getEObject());
			for (String varName : environment.keySet()) {
				EClassifier varType = environment.get(varName);
				ecoreEnv.addElement(varName, createVar(ecoreEnv, varName, varType), false);
			}
		}

		/**
		* @generated
		*/
		private static Variable createVar(Environment<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> ecoreEnv,
				String name, EClassifier type) {
			Variable var = EcoreFactory.eINSTANCE.createVariable();
			var.setName(name);
			var.setType(ecoreEnv.getUMLReflection().getOCLType(type));
			return var;
		}
	}
}
