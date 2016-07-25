/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation.util;

import org.bonitasoft.studio.connector.model.implementation.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage
 * @generated
 */
public class ConnectorImplementationAdapterFactory extends AdapterFactoryImpl {
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static ConnectorImplementationPackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorImplementationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ConnectorImplementationPackage.eINSTANCE;
		}
	}

    /**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
    @Override
    public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

    /**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ConnectorImplementationSwitch<Adapter> modelSwitch =
        new ConnectorImplementationSwitch<Adapter>() {
			@Override
			public Adapter caseConnectorImplementation(ConnectorImplementation object) {
				return createConnectorImplementationAdapter();
			}
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseJarDependencies(JarDependencies object) {
				return createJarDependenciesAdapter();
			}
			@Override
			public Adapter caseUnloadableConnectorImplementation(UnloadableConnectorImplementation object) {
				return createUnloadableConnectorImplementationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

    /**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
    @Override
    public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation <em>Connector Implementation</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation
	 * @generated
	 */
    public Adapter createConnectorImplementationAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.implementation.DocumentRoot
	 * @generated
	 */
    public Adapter createDocumentRootAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.implementation.JarDependencies <em>Jar Dependencies</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.implementation.JarDependencies
	 * @generated
	 */
    public Adapter createJarDependenciesAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.implementation.UnloadableConnectorImplementation <em>Unloadable Connector Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.implementation.UnloadableConnectorImplementation
	 * @generated
	 */
	public Adapter createUnloadableConnectorImplementationAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
    public Adapter createEObjectAdapter() {
		return null;
	}

} //ConnectorImplementationAdapterFactory
