/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BMW Car IT - Initial API and implementation
 *     Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.internal.migration.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

/**
 * A validator to validate OCL constraints.
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 096149C737E5B0E96753B668A60D11A6
 */
public class ModelValidator extends EObjectValidator {

	/** Source URI for OCL annotations. */
	private static final String OCL_SOURCE_URI = "http://www.eclipse.org/ocl/examples/OCL";

	/** OCL helper class. */
	private final OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;

	/** Constructor */
	@SuppressWarnings("unchecked")
	public ModelValidator(Model model) {
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		ocl.setExtentMap((Map) model.createExtentMap());
	}

	/** {@inheritDoc} */
	@Override
	public boolean validate_EveryDefaultConstraint(EObject object,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = super.validate_EveryDefaultConstraint(object,
				diagnostics, context);
		if (object instanceof Instance) {
			if (result || diagnostics != null) {
				result &= validate_EveryInvariant((Instance) object,
						diagnostics, context);
			}
		}
		return result;
	}

	/**
	 * Validate OCL constraints and return true if OCL constraint holds, false
	 * otherwise.
	 */
	private boolean validate_EveryInvariant(Instance instance,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		EClass eClass = instance.getEClass();
		EAnnotation annotation = eClass.getEAnnotation(OCL_SOURCE_URI);
		if (annotation != null) {
			for (Entry<String, String> entry : annotation.getDetails()) {
				try {
					String expression = entry.getValue();
					if (!(Boolean) evaluate(instance, expression)) {
						if (diagnostics != null) {
							diagnostics.add(new BasicDiagnostic(
									Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
									EcorePlugin.INSTANCE.getString(
											"_UI_GenericConstraint_diagnostic",
											new Object[] {
													entry.getKey(),
													getObjectLabel(
															(EObject) instance,
															context) }),
									new Object[] { instance }));
						}
						return false;
					}
				} catch (ParserException e) {
					System.out.println(e);
				}
			}
		}
		return true;
	}

	/** Evaluate an OCL expression. */
	private Object evaluate(Instance instance, String expression)
			throws ParserException {
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();

		helper.setContext(((EObject) instance).eClass());
		OCLExpression<EClassifier> query = helper.createQuery(expression);

		// create a Query to evaluate our query expression
		Query<EClassifier, EClass, EObject> queryEval = ocl.createQuery(query);
		Object result = queryEval.evaluate(instance);

		return result;
	}

}
