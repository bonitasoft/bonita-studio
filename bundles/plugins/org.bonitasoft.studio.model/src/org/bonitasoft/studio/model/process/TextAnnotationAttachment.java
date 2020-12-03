/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.process;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text Annotation Attachment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.TextAnnotationAttachment#getSource <em>Source</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.TextAnnotationAttachment#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getTextAnnotationAttachment()
 * @model
 * @generated
 */
public interface TextAnnotationAttachment extends EObject {
	/**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(TextAnnotation)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getTextAnnotationAttachment_Source()
     * @model
     * @generated
     */
	TextAnnotation getSource();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.TextAnnotationAttachment#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
	void setSource(TextAnnotation value);

	/**
     * Returns the value of the '<em><b>Target</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.studio.model.process.Element#getTextAnnotationAttachment <em>Text Annotation Attachment</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' container reference.
     * @see #setTarget(Element)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getTextAnnotationAttachment_Target()
     * @see org.bonitasoft.studio.model.process.Element#getTextAnnotationAttachment
     * @model opposite="textAnnotationAttachment" transient="false"
     * @generated
     */
	Element getTarget();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.TextAnnotationAttachment#getTarget <em>Target</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' container reference.
     * @see #getTarget()
     * @generated
     */
	void setTarget(Element value);

} // TextAnnotationAttachment
