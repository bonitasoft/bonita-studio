/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorImplementationItemProviderAdapterFactory extends ConnectorImplementationAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
    /**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ComposedAdapterFactory parentAdapterFactory;

    /**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected IChangeNotifier changeNotifier = new ChangeNotifier();

    /**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected Collection<Object> supportedTypes = new ArrayList<Object>();

    /**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorImplementationItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

    /**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation} instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ConnectorImplementationItemProvider connectorImplementationItemProvider;

    /**
	 * This creates an adapter for a {@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Adapter createConnectorImplementationAdapter() {
		if (connectorImplementationItemProvider == null) {
			connectorImplementationItemProvider = new ConnectorImplementationItemProvider(this);
		}

		return connectorImplementationItemProvider;
	}

    /**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot} instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected DocumentRootItemProvider documentRootItemProvider;

    /**
	 * This creates an adapter for a {@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Adapter createDocumentRootAdapter() {
		if (documentRootItemProvider == null) {
			documentRootItemProvider = new DocumentRootItemProvider(this);
		}

		return documentRootItemProvider;
	}

    /**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.connector.model.implementation.JarDependencies} instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected JarDependenciesItemProvider jarDependenciesItemProvider;

    /**
	 * This creates an adapter for a {@link org.bonitasoft.studio.connector.model.implementation.JarDependencies}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Adapter createJarDependenciesAdapter() {
		if (jarDependenciesItemProvider == null) {
			jarDependenciesItemProvider = new JarDependenciesItemProvider(this);
		}

		return jarDependenciesItemProvider;
	}

    /**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.connector.model.implementation.UnloadableConnectorImplementation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnloadableConnectorImplementationItemProvider unloadableConnectorImplementationItemProvider;

				/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.connector.model.implementation.UnloadableConnectorImplementation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUnloadableConnectorImplementationAdapter() {
		if (unloadableConnectorImplementationItemProvider == null) {
			unloadableConnectorImplementationItemProvider = new UnloadableConnectorImplementationItemProvider(this);
		}

		return unloadableConnectorImplementationItemProvider;
	}

				/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

    /**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

    /**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

    /**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

    /**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

    /**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

    /**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void dispose() {
		if (connectorImplementationItemProvider != null) connectorImplementationItemProvider.dispose();
		if (documentRootItemProvider != null) documentRootItemProvider.dispose();
		if (jarDependenciesItemProvider != null) jarDependenciesItemProvider.dispose();
		if (unloadableConnectorImplementationItemProvider != null) unloadableConnectorImplementationItemProvider.dispose();
	}

}
