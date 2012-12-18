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
package org.bonitasoft.studio.common.exporter;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.eclipse.m2m.qvt.oml.util.Log;

/**
 * 
 * Utility class used to execute QVT transformations
 * 
 * @author Baptiste Mesta
 * 
 */
public class QvtTransformationExecutor {

	/**
	 * 
	 * execute the transformation and pu the result in a list of EObject
	 * 
	 * @param qvtTransfURI
	 *            the transformation to use
	 * @param object
	 *            the input EObject
	 * @return the outputs objects
	 * @throws RuntimeException
	 *             thrown when transformation is unsuccessful
	 */
	public static List<EObject> executeQvtTransformation(URI qvtTransfURI, List<?> objects,boolean copy) throws RuntimeException {

		TransformationExecutor executor = new TransformationExecutor(qvtTransfURI);

		ModelExtent[] tabArgs = new ModelExtent[objects.size() + 1];
		// define the transformation input
		ArrayList<EObject> inObjects = null;
		for (int i = 0; i < objects.size(); i++) {
			inObjects = new ArrayList<EObject>();
			if (objects.get(i) instanceof List<?>) {
				for (Object eObject : (List<?>) objects.get(i)) {
					if(copy)
						inObjects.add(EcoreUtil.copy((EObject) eObject));
					else
						inObjects.add((EObject) eObject);
						
				}
			} else {
				if(copy)
					inObjects.add(EcoreUtil.copy((EObject) objects.get(i)));
				else
					inObjects.add((EObject) objects.get(i));
			}
			ModelExtent input = new BasicModelExtent(inObjects);
			tabArgs[i] = input;

		}

		// create the input extent with its initial contents
		// ModelExtent input = new BasicModelExtent(inObjects);
		// // create an empty extent to catch the output
		ModelExtent output = new BasicModelExtent();
		tabArgs[objects.size()] = output;
		// qvtArgs.add(output);

		// setup the execution environment details ->
		// configuration properties, logger, monitor object etc.
		ExecutionContextImpl context = new ExecutionContextImpl();
		context.setConfigProperty("keepModeling", true); //$NON-NLS-1$

		context.setLog(new Log() {

			public void log(int level, String message, Object param) {
				BonitaStudioLog.log("[" + level + "]" + message + ": " + param.toString()); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

			}

			public void log(String message, Object param) {
				BonitaStudioLog.log(message + ": " + param.toString()); //$NON-NLS-1$

			}

			public void log(int level, String message) {
				BonitaStudioLog.log("[" + level + "]" + message); //$NON-NLS-1$ //$NON-NLS-2$

			}

			public void log(String message) {
				BonitaStudioLog.log(message);

			}
		});

		// run the transformation assigned to the executor with the given
		// input and output and execution context -> transform(in, out)
		// Remark: variable arguments count is supported

		ExecutionDiagnostic result = executor.execute(context, tabArgs);

		// check the result for success
		if (result.getSeverity() == Diagnostic.OK) {
			// the output objects got captured in the output extent
			List<EObject> outObjects = output.getContents();
			return outObjects;

		} else {
			System.err.println(result.toString());
			// turn the result diagnostic into status and send it to error log
			IStatus status = BasicDiagnostic.toIStatus(result);
			// Activator.getDefault().getLog().log(status);
			throw new RuntimeException(status.toString());
		}
	}

	public static List<EObject> executeQvtTransformation(URI qvtTransfURI, EObject object) throws RuntimeException {
		ArrayList<EObject> list = new ArrayList<EObject>();
		list.add(object);
		return executeQvtTransformation(qvtTransfURI, list);
	}

	public static List<EObject> executeQvtTransformation(URI qvtTransfURI, List<EObject> list) {
		
		return executeQvtTransformation(qvtTransfURI, list, true);
	}
	


}
