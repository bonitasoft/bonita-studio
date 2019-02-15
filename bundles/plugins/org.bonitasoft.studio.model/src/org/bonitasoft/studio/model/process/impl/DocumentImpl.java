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
package org.bonitasoft.studio.model.process.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getTextAnnotationAttachment <em>Text Annotation Attachment</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getDefaultValueIdOfDocumentStore <em>Default Value Id Of Document Store</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getMimeType <em>Mime Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getDocumentType <em>Document Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getInitialMultipleContent <em>Initial Multiple Content</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.DocumentImpl#getContractInput <em>Contract Input</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentImpl extends EObjectImpl implements Document {
	/**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
	protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
	protected String documentation = DOCUMENTATION_EDEFAULT;

	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The cached value of the '{@link #getTextAnnotationAttachment() <em>Text Annotation Attachment</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTextAnnotationAttachment()
     * @generated
     * @ordered
     */
	protected EList<TextAnnotationAttachment> textAnnotationAttachment;

	/**
     * The default value of the '{@link #getDefaultValueIdOfDocumentStore() <em>Default Value Id Of Document Store</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultValueIdOfDocumentStore()
     * @generated
     * @ordered
     */
	protected static final String DEFAULT_VALUE_ID_OF_DOCUMENT_STORE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDefaultValueIdOfDocumentStore() <em>Default Value Id Of Document Store</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultValueIdOfDocumentStore()
     * @generated
     * @ordered
     */
	protected String defaultValueIdOfDocumentStore = DEFAULT_VALUE_ID_OF_DOCUMENT_STORE_EDEFAULT;

	/**
     * The cached value of the '{@link #getMimeType() <em>Mime Type</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMimeType()
     * @generated
     * @ordered
     */
	protected Expression mimeType;

	/**
     * The cached value of the '{@link #getUrl() <em>Url</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUrl()
     * @generated
     * @ordered
     */
	protected Expression url;

	/**
     * The default value of the '{@link #getDocumentType() <em>Document Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocumentType()
     * @generated
     * @ordered
     */
	protected static final DocumentType DOCUMENT_TYPE_EDEFAULT = DocumentType.NONE;

	/**
     * The cached value of the '{@link #getDocumentType() <em>Document Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocumentType()
     * @generated
     * @ordered
     */
	protected DocumentType documentType = DOCUMENT_TYPE_EDEFAULT;

	/**
     * The cached value of the '{@link #getInitialMultipleContent() <em>Initial Multiple Content</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInitialMultipleContent()
     * @generated
     * @ordered
     */
	protected Expression initialMultipleContent;

	/**
     * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isMultiple()
     * @generated
     * @ordered
     */
	protected static final boolean MULTIPLE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isMultiple()
     * @generated
     * @ordered
     */
	protected boolean multiple = MULTIPLE_EDEFAULT;

	/**
     * The cached value of the '{@link #getContractInput() <em>Contract Input</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getContractInput()
     * @generated
     * @ordered
     */
	protected ContractInput contractInput;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DocumentImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.DOCUMENT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDocumentation() {
        return documentation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__DOCUMENTATION, oldDocumentation, documentation));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<TextAnnotationAttachment> getTextAnnotationAttachment() {
        if (textAnnotationAttachment == null) {
            textAnnotationAttachment = new EObjectContainmentWithInverseEList<TextAnnotationAttachment>(TextAnnotationAttachment.class, this, ProcessPackage.DOCUMENT__TEXT_ANNOTATION_ATTACHMENT, ProcessPackage.TEXT_ANNOTATION_ATTACHMENT__TARGET);
        }
        return textAnnotationAttachment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDefaultValueIdOfDocumentStore() {
        return defaultValueIdOfDocumentStore;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDefaultValueIdOfDocumentStore(String newDefaultValueIdOfDocumentStore) {
        String oldDefaultValueIdOfDocumentStore = defaultValueIdOfDocumentStore;
        defaultValueIdOfDocumentStore = newDefaultValueIdOfDocumentStore;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE, oldDefaultValueIdOfDocumentStore, defaultValueIdOfDocumentStore));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getMimeType() {
        return mimeType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMimeType(Expression newMimeType, NotificationChain msgs) {
        Expression oldMimeType = mimeType;
        mimeType = newMimeType;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__MIME_TYPE, oldMimeType, newMimeType);
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
	public void setMimeType(Expression newMimeType) {
        if (newMimeType != mimeType) {
            NotificationChain msgs = null;
            if (mimeType != null)
                msgs = ((InternalEObject)mimeType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.DOCUMENT__MIME_TYPE, null, msgs);
            if (newMimeType != null)
                msgs = ((InternalEObject)newMimeType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.DOCUMENT__MIME_TYPE, null, msgs);
            msgs = basicSetMimeType(newMimeType, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__MIME_TYPE, newMimeType, newMimeType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getUrl() {
        return url;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUrl(Expression newUrl, NotificationChain msgs) {
        Expression oldUrl = url;
        url = newUrl;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__URL, oldUrl, newUrl);
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
	public void setUrl(Expression newUrl) {
        if (newUrl != url) {
            NotificationChain msgs = null;
            if (url != null)
                msgs = ((InternalEObject)url).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.DOCUMENT__URL, null, msgs);
            if (newUrl != null)
                msgs = ((InternalEObject)newUrl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.DOCUMENT__URL, null, msgs);
            msgs = basicSetUrl(newUrl, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__URL, newUrl, newUrl));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DocumentType getDocumentType() {
        return documentType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDocumentType(DocumentType newDocumentType) {
        DocumentType oldDocumentType = documentType;
        documentType = newDocumentType == null ? DOCUMENT_TYPE_EDEFAULT : newDocumentType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__DOCUMENT_TYPE, oldDocumentType, documentType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getInitialMultipleContent() {
        return initialMultipleContent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInitialMultipleContent(Expression newInitialMultipleContent, NotificationChain msgs) {
        Expression oldInitialMultipleContent = initialMultipleContent;
        initialMultipleContent = newInitialMultipleContent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT, oldInitialMultipleContent, newInitialMultipleContent);
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
	public void setInitialMultipleContent(Expression newInitialMultipleContent) {
        if (newInitialMultipleContent != initialMultipleContent) {
            NotificationChain msgs = null;
            if (initialMultipleContent != null)
                msgs = ((InternalEObject)initialMultipleContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT, null, msgs);
            if (newInitialMultipleContent != null)
                msgs = ((InternalEObject)newInitialMultipleContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT, null, msgs);
            msgs = basicSetInitialMultipleContent(newInitialMultipleContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT, newInitialMultipleContent, newInitialMultipleContent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isMultiple() {
        return multiple;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMultiple(boolean newMultiple) {
        boolean oldMultiple = multiple;
        multiple = newMultiple;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__MULTIPLE, oldMultiple, multiple));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ContractInput getContractInput() {
        if (contractInput != null && contractInput.eIsProxy()) {
            InternalEObject oldContractInput = (InternalEObject)contractInput;
            contractInput = (ContractInput)eResolveProxy(oldContractInput);
            if (contractInput != oldContractInput) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.DOCUMENT__CONTRACT_INPUT, oldContractInput, contractInput));
            }
        }
        return contractInput;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ContractInput basicGetContractInput() {
        return contractInput;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setContractInput(ContractInput newContractInput) {
        ContractInput oldContractInput = contractInput;
        contractInput = newContractInput;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.DOCUMENT__CONTRACT_INPUT, oldContractInput, contractInput));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.DOCUMENT__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getTextAnnotationAttachment()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.DOCUMENT__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<?>)getTextAnnotationAttachment()).basicRemove(otherEnd, msgs);
            case ProcessPackage.DOCUMENT__MIME_TYPE:
                return basicSetMimeType(null, msgs);
            case ProcessPackage.DOCUMENT__URL:
                return basicSetUrl(null, msgs);
            case ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT:
                return basicSetInitialMultipleContent(null, msgs);
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
            case ProcessPackage.DOCUMENT__DOCUMENTATION:
                return getDocumentation();
            case ProcessPackage.DOCUMENT__NAME:
                return getName();
            case ProcessPackage.DOCUMENT__TEXT_ANNOTATION_ATTACHMENT:
                return getTextAnnotationAttachment();
            case ProcessPackage.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE:
                return getDefaultValueIdOfDocumentStore();
            case ProcessPackage.DOCUMENT__MIME_TYPE:
                return getMimeType();
            case ProcessPackage.DOCUMENT__URL:
                return getUrl();
            case ProcessPackage.DOCUMENT__DOCUMENT_TYPE:
                return getDocumentType();
            case ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT:
                return getInitialMultipleContent();
            case ProcessPackage.DOCUMENT__MULTIPLE:
                return isMultiple();
            case ProcessPackage.DOCUMENT__CONTRACT_INPUT:
                if (resolve) return getContractInput();
                return basicGetContractInput();
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
            case ProcessPackage.DOCUMENT__DOCUMENTATION:
                setDocumentation((String)newValue);
                return;
            case ProcessPackage.DOCUMENT__NAME:
                setName((String)newValue);
                return;
            case ProcessPackage.DOCUMENT__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                getTextAnnotationAttachment().addAll((Collection<? extends TextAnnotationAttachment>)newValue);
                return;
            case ProcessPackage.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE:
                setDefaultValueIdOfDocumentStore((String)newValue);
                return;
            case ProcessPackage.DOCUMENT__MIME_TYPE:
                setMimeType((Expression)newValue);
                return;
            case ProcessPackage.DOCUMENT__URL:
                setUrl((Expression)newValue);
                return;
            case ProcessPackage.DOCUMENT__DOCUMENT_TYPE:
                setDocumentType((DocumentType)newValue);
                return;
            case ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT:
                setInitialMultipleContent((Expression)newValue);
                return;
            case ProcessPackage.DOCUMENT__MULTIPLE:
                setMultiple((Boolean)newValue);
                return;
            case ProcessPackage.DOCUMENT__CONTRACT_INPUT:
                setContractInput((ContractInput)newValue);
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
            case ProcessPackage.DOCUMENT__DOCUMENTATION:
                setDocumentation(DOCUMENTATION_EDEFAULT);
                return;
            case ProcessPackage.DOCUMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ProcessPackage.DOCUMENT__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                return;
            case ProcessPackage.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE:
                setDefaultValueIdOfDocumentStore(DEFAULT_VALUE_ID_OF_DOCUMENT_STORE_EDEFAULT);
                return;
            case ProcessPackage.DOCUMENT__MIME_TYPE:
                setMimeType((Expression)null);
                return;
            case ProcessPackage.DOCUMENT__URL:
                setUrl((Expression)null);
                return;
            case ProcessPackage.DOCUMENT__DOCUMENT_TYPE:
                setDocumentType(DOCUMENT_TYPE_EDEFAULT);
                return;
            case ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT:
                setInitialMultipleContent((Expression)null);
                return;
            case ProcessPackage.DOCUMENT__MULTIPLE:
                setMultiple(MULTIPLE_EDEFAULT);
                return;
            case ProcessPackage.DOCUMENT__CONTRACT_INPUT:
                setContractInput((ContractInput)null);
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
            case ProcessPackage.DOCUMENT__DOCUMENTATION:
                return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
            case ProcessPackage.DOCUMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ProcessPackage.DOCUMENT__TEXT_ANNOTATION_ATTACHMENT:
                return textAnnotationAttachment != null && !textAnnotationAttachment.isEmpty();
            case ProcessPackage.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE:
                return DEFAULT_VALUE_ID_OF_DOCUMENT_STORE_EDEFAULT == null ? defaultValueIdOfDocumentStore != null : !DEFAULT_VALUE_ID_OF_DOCUMENT_STORE_EDEFAULT.equals(defaultValueIdOfDocumentStore);
            case ProcessPackage.DOCUMENT__MIME_TYPE:
                return mimeType != null;
            case ProcessPackage.DOCUMENT__URL:
                return url != null;
            case ProcessPackage.DOCUMENT__DOCUMENT_TYPE:
                return documentType != DOCUMENT_TYPE_EDEFAULT;
            case ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT:
                return initialMultipleContent != null;
            case ProcessPackage.DOCUMENT__MULTIPLE:
                return multiple != MULTIPLE_EDEFAULT;
            case ProcessPackage.DOCUMENT__CONTRACT_INPUT:
                return contractInput != null;
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", defaultValueIdOfDocumentStore: "); //$NON-NLS-1$
        result.append(defaultValueIdOfDocumentStore);
        result.append(", documentType: "); //$NON-NLS-1$
        result.append(documentType);
        result.append(", multiple: "); //$NON-NLS-1$
        result.append(multiple);
        result.append(')');
        return result.toString();
    }

} //DocumentImpl
