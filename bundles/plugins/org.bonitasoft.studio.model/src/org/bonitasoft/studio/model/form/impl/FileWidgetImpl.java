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
package org.bonitasoft.studio.model.form.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetDownloadType;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormPackage;

import org.bonitasoft.studio.model.process.Document;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Widget</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#isDownloadOnly <em>Download Only</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#isUsePreview <em>Use Preview</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#getDocument <em>Document</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#getInitialResourcePath <em>Initial Resource Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#getOutputDocumentName <em>Output Document Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#isUpdateDocument <em>Update Document</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#getIntialResourceList <em>Intial Resource List</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#getInputType <em>Input Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#getOutputDocumentListExpression <em>Output Document List Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl#getDownloadType <em>Download Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FileWidgetImpl extends SingleValuatedFormFieldImpl implements FileWidget {
	/**
     * The default value of the '{@link #isDownloadOnly() <em>Download Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDownloadOnly()
     * @generated
     * @ordered
     */
	protected static final boolean DOWNLOAD_ONLY_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isDownloadOnly() <em>Download Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDownloadOnly()
     * @generated
     * @ordered
     */
	protected boolean downloadOnly = DOWNLOAD_ONLY_EDEFAULT;

	/**
     * The default value of the '{@link #isUsePreview() <em>Use Preview</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUsePreview()
     * @generated
     * @ordered
     */
	protected static final boolean USE_PREVIEW_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isUsePreview() <em>Use Preview</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUsePreview()
     * @generated
     * @ordered
     */
	protected boolean usePreview = USE_PREVIEW_EDEFAULT;

	/**
     * The cached value of the '{@link #getDocument() <em>Document</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocument()
     * @generated
     * @ordered
     */
	protected Document document;

	/**
     * The default value of the '{@link #getInitialResourcePath() <em>Initial Resource Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInitialResourcePath()
     * @generated
     * @ordered
     */
	protected static final String INITIAL_RESOURCE_PATH_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getInitialResourcePath() <em>Initial Resource Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInitialResourcePath()
     * @generated
     * @ordered
     */
	protected String initialResourcePath = INITIAL_RESOURCE_PATH_EDEFAULT;

	/**
     * The default value of the '{@link #getOutputDocumentName() <em>Output Document Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOutputDocumentName()
     * @generated
     * @ordered
     */
	protected static final String OUTPUT_DOCUMENT_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getOutputDocumentName() <em>Output Document Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOutputDocumentName()
     * @generated
     * @ordered
     */
	protected String outputDocumentName = OUTPUT_DOCUMENT_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #isUpdateDocument() <em>Update Document</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUpdateDocument()
     * @generated
     * @ordered
     */
	protected static final boolean UPDATE_DOCUMENT_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isUpdateDocument() <em>Update Document</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUpdateDocument()
     * @generated
     * @ordered
     */
	protected boolean updateDocument = UPDATE_DOCUMENT_EDEFAULT;

	/**
     * The cached value of the '{@link #getIntialResourceList() <em>Intial Resource List</em>}' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIntialResourceList()
     * @generated
     * @ordered
     */
	protected EList<String> intialResourceList;

	/**
     * The default value of the '{@link #getInputType() <em>Input Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInputType()
     * @generated
     * @ordered
     */
	protected static final FileWidgetInputType INPUT_TYPE_EDEFAULT = FileWidgetInputType.URL;

	/**
     * The cached value of the '{@link #getInputType() <em>Input Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInputType()
     * @generated
     * @ordered
     */
	protected FileWidgetInputType inputType = INPUT_TYPE_EDEFAULT;

	/**
     * The cached value of the '{@link #getOutputDocumentListExpression() <em>Output Document List Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOutputDocumentListExpression()
     * @generated
     * @ordered
     */
	protected Expression outputDocumentListExpression;

	/**
     * The default value of the '{@link #getDownloadType() <em>Download Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDownloadType()
     * @generated
     * @ordered
     */
	protected static final FileWidgetDownloadType DOWNLOAD_TYPE_EDEFAULT = FileWidgetDownloadType.BOTH;

	/**
     * The cached value of the '{@link #getDownloadType() <em>Download Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDownloadType()
     * @generated
     * @ordered
     */
	protected FileWidgetDownloadType downloadType = DOWNLOAD_TYPE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FileWidgetImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.FILE_WIDGET;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isDownloadOnly() {
        return downloadOnly;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDownloadOnly(boolean newDownloadOnly) {
        boolean oldDownloadOnly = downloadOnly;
        downloadOnly = newDownloadOnly;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__DOWNLOAD_ONLY, oldDownloadOnly, downloadOnly));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isUsePreview() {
        return usePreview;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUsePreview(boolean newUsePreview) {
        boolean oldUsePreview = usePreview;
        usePreview = newUsePreview;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__USE_PREVIEW, oldUsePreview, usePreview));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Document getDocument() {
        if (document != null && document.eIsProxy()) {
            InternalEObject oldDocument = (InternalEObject)document;
            document = (Document)eResolveProxy(oldDocument);
            if (document != oldDocument) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.FILE_WIDGET__DOCUMENT, oldDocument, document));
            }
        }
        return document;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Document basicGetDocument() {
        return document;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDocument(Document newDocument) {
        Document oldDocument = document;
        document = newDocument;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__DOCUMENT, oldDocument, document));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getInitialResourcePath() {
        return initialResourcePath;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setInitialResourcePath(String newInitialResourcePath) {
        String oldInitialResourcePath = initialResourcePath;
        initialResourcePath = newInitialResourcePath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__INITIAL_RESOURCE_PATH, oldInitialResourcePath, initialResourcePath));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getOutputDocumentName() {
        return outputDocumentName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setOutputDocumentName(String newOutputDocumentName) {
        String oldOutputDocumentName = outputDocumentName;
        outputDocumentName = newOutputDocumentName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_NAME, oldOutputDocumentName, outputDocumentName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isUpdateDocument() {
        return updateDocument;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUpdateDocument(boolean newUpdateDocument) {
        boolean oldUpdateDocument = updateDocument;
        updateDocument = newUpdateDocument;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__UPDATE_DOCUMENT, oldUpdateDocument, updateDocument));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<String> getIntialResourceList() {
        if (intialResourceList == null) {
            intialResourceList = new EDataTypeUniqueEList<String>(String.class, this, FormPackage.FILE_WIDGET__INTIAL_RESOURCE_LIST);
        }
        return intialResourceList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public FileWidgetInputType getInputType() {
        return inputType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setInputType(FileWidgetInputType newInputType) {
        FileWidgetInputType oldInputType = inputType;
        inputType = newInputType == null ? INPUT_TYPE_EDEFAULT : newInputType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__INPUT_TYPE, oldInputType, inputType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getOutputDocumentListExpression() {
        return outputDocumentListExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetOutputDocumentListExpression(Expression newOutputDocumentListExpression, NotificationChain msgs) {
        Expression oldOutputDocumentListExpression = outputDocumentListExpression;
        outputDocumentListExpression = newOutputDocumentListExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION, oldOutputDocumentListExpression, newOutputDocumentListExpression);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setOutputDocumentListExpression(Expression newOutputDocumentListExpression) {
        if (newOutputDocumentListExpression != outputDocumentListExpression) {
            NotificationChain msgs = null;
            if (outputDocumentListExpression != null)
                msgs = ((InternalEObject)outputDocumentListExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION, null, msgs);
            if (newOutputDocumentListExpression != null)
                msgs = ((InternalEObject)newOutputDocumentListExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION, null, msgs);
            msgs = basicSetOutputDocumentListExpression(newOutputDocumentListExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION, newOutputDocumentListExpression, newOutputDocumentListExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public FileWidgetDownloadType getDownloadType() {
        return downloadType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDownloadType(FileWidgetDownloadType newDownloadType) {
        FileWidgetDownloadType oldDownloadType = downloadType;
        downloadType = newDownloadType == null ? DOWNLOAD_TYPE_EDEFAULT : newDownloadType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FILE_WIDGET__DOWNLOAD_TYPE, oldDownloadType, downloadType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION:
                return basicSetOutputDocumentListExpression(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FormPackage.FILE_WIDGET__DOWNLOAD_ONLY:
                return isDownloadOnly();
            case FormPackage.FILE_WIDGET__USE_PREVIEW:
                return isUsePreview();
            case FormPackage.FILE_WIDGET__DOCUMENT:
                if (resolve) return getDocument();
                return basicGetDocument();
            case FormPackage.FILE_WIDGET__INITIAL_RESOURCE_PATH:
                return getInitialResourcePath();
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_NAME:
                return getOutputDocumentName();
            case FormPackage.FILE_WIDGET__UPDATE_DOCUMENT:
                return isUpdateDocument();
            case FormPackage.FILE_WIDGET__INTIAL_RESOURCE_LIST:
                return getIntialResourceList();
            case FormPackage.FILE_WIDGET__INPUT_TYPE:
                return getInputType();
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION:
                return getOutputDocumentListExpression();
            case FormPackage.FILE_WIDGET__DOWNLOAD_TYPE:
                return getDownloadType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FormPackage.FILE_WIDGET__DOWNLOAD_ONLY:
                setDownloadOnly((Boolean)newValue);
                return;
            case FormPackage.FILE_WIDGET__USE_PREVIEW:
                setUsePreview((Boolean)newValue);
                return;
            case FormPackage.FILE_WIDGET__DOCUMENT:
                setDocument((Document)newValue);
                return;
            case FormPackage.FILE_WIDGET__INITIAL_RESOURCE_PATH:
                setInitialResourcePath((String)newValue);
                return;
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_NAME:
                setOutputDocumentName((String)newValue);
                return;
            case FormPackage.FILE_WIDGET__UPDATE_DOCUMENT:
                setUpdateDocument((Boolean)newValue);
                return;
            case FormPackage.FILE_WIDGET__INTIAL_RESOURCE_LIST:
                getIntialResourceList().clear();
                getIntialResourceList().addAll((Collection<? extends String>)newValue);
                return;
            case FormPackage.FILE_WIDGET__INPUT_TYPE:
                setInputType((FileWidgetInputType)newValue);
                return;
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION:
                setOutputDocumentListExpression((Expression)newValue);
                return;
            case FormPackage.FILE_WIDGET__DOWNLOAD_TYPE:
                setDownloadType((FileWidgetDownloadType)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case FormPackage.FILE_WIDGET__DOWNLOAD_ONLY:
                setDownloadOnly(DOWNLOAD_ONLY_EDEFAULT);
                return;
            case FormPackage.FILE_WIDGET__USE_PREVIEW:
                setUsePreview(USE_PREVIEW_EDEFAULT);
                return;
            case FormPackage.FILE_WIDGET__DOCUMENT:
                setDocument((Document)null);
                return;
            case FormPackage.FILE_WIDGET__INITIAL_RESOURCE_PATH:
                setInitialResourcePath(INITIAL_RESOURCE_PATH_EDEFAULT);
                return;
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_NAME:
                setOutputDocumentName(OUTPUT_DOCUMENT_NAME_EDEFAULT);
                return;
            case FormPackage.FILE_WIDGET__UPDATE_DOCUMENT:
                setUpdateDocument(UPDATE_DOCUMENT_EDEFAULT);
                return;
            case FormPackage.FILE_WIDGET__INTIAL_RESOURCE_LIST:
                getIntialResourceList().clear();
                return;
            case FormPackage.FILE_WIDGET__INPUT_TYPE:
                setInputType(INPUT_TYPE_EDEFAULT);
                return;
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION:
                setOutputDocumentListExpression((Expression)null);
                return;
            case FormPackage.FILE_WIDGET__DOWNLOAD_TYPE:
                setDownloadType(DOWNLOAD_TYPE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case FormPackage.FILE_WIDGET__DOWNLOAD_ONLY:
                return downloadOnly != DOWNLOAD_ONLY_EDEFAULT;
            case FormPackage.FILE_WIDGET__USE_PREVIEW:
                return usePreview != USE_PREVIEW_EDEFAULT;
            case FormPackage.FILE_WIDGET__DOCUMENT:
                return document != null;
            case FormPackage.FILE_WIDGET__INITIAL_RESOURCE_PATH:
                return INITIAL_RESOURCE_PATH_EDEFAULT == null ? initialResourcePath != null : !INITIAL_RESOURCE_PATH_EDEFAULT.equals(initialResourcePath);
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_NAME:
                return OUTPUT_DOCUMENT_NAME_EDEFAULT == null ? outputDocumentName != null : !OUTPUT_DOCUMENT_NAME_EDEFAULT.equals(outputDocumentName);
            case FormPackage.FILE_WIDGET__UPDATE_DOCUMENT:
                return updateDocument != UPDATE_DOCUMENT_EDEFAULT;
            case FormPackage.FILE_WIDGET__INTIAL_RESOURCE_LIST:
                return intialResourceList != null && !intialResourceList.isEmpty();
            case FormPackage.FILE_WIDGET__INPUT_TYPE:
                return inputType != INPUT_TYPE_EDEFAULT;
            case FormPackage.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION:
                return outputDocumentListExpression != null;
            case FormPackage.FILE_WIDGET__DOWNLOAD_TYPE:
                return downloadType != DOWNLOAD_TYPE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (downloadOnly: "); //$NON-NLS-1$
        result.append(downloadOnly);
        result.append(", usePreview: "); //$NON-NLS-1$
        result.append(usePreview);
        result.append(", initialResourcePath: "); //$NON-NLS-1$
        result.append(initialResourcePath);
        result.append(", outputDocumentName: "); //$NON-NLS-1$
        result.append(outputDocumentName);
        result.append(", updateDocument: "); //$NON-NLS-1$
        result.append(updateDocument);
        result.append(", intialResourceList: "); //$NON-NLS-1$
        result.append(intialResourceList);
        result.append(", inputType: "); //$NON-NLS-1$
        result.append(inputType);
        result.append(", downloadType: "); //$NON-NLS-1$
        result.append(downloadType);
        result.append(')');
        return result.toString();
    }

} //FileWidgetImpl
