/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.decorator;

import java.util.Objects;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.AbstractBDMFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IContributorResourceAdapter;
import org.eclipse.ui.PlatformUI;

public class BdmLightweightDecorator extends LabelProvider
        implements ILightweightLabelDecorator, IPropertyChangeListener, IResourceChangeListener {

    private RepositoryAccessor repositoryAccessor;

    public BdmLightweightDecorator() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        repositoryAccessor.getWorkspace().addResourceChangeListener(this);
        BusinessObjectPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this);
    }

    @Override
    public void decorate(Object element, IDecoration decoration) {
        if (!PlatformUI.isWorkbenchRunning() || ResourcesPlugin.getWorkspace() == null
                || !repositoryAccessor.hasActiveRepository() || !repositoryAccessor.getCurrentRepository().isLoaded()) {
            return;
        }

        IResource resource = getResource(element);
        if (resource != null && isBdmResource(resource)) {
            if (BusinessObjectPlugin.getDefault().getPreferenceStore()
                    .getBoolean(BusinessObjectModelFileStore.BDM_DEPLOY_REQUIRED_PROPERTY)) {
                decoration.addSuffix(" - " + Messages.bdmDeployMarker);
            }
        }
    }

    private boolean isBdmResource(IResource resource) {
        BusinessObjectModelRepositoryStore bdmRepositoryStore = getBdmRepositoryStore();
        AbstractBDMFileStore bdmFileStore = bdmRepositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME,
                false);
        if (bdmFileStore != null) {
            return Objects.equals(bdmRepositoryStore.getResource(), resource)
                    || Objects.equals(bdmFileStore.getResource(), resource);
        }
        return false;
    }

    private static IResource getResource(Object actElement) {
        Object element = actElement;
        if (element instanceof ResourceMapping) {
            element = ((ResourceMapping) element).getModelObject();
        }

        IResource resource = null;
        if (element instanceof IResource) {
            resource = (IResource) element;
        } else if (element instanceof IAdaptable) {
            final IAdaptable adaptable = (IAdaptable) element;
            resource = Adapters.adapt(adaptable, IResource.class);
            if (resource == null) {
                final IContributorResourceAdapter adapter = Adapters
                        .adapt(adaptable, IContributorResourceAdapter.class);
                if (adapter != null)
                    resource = adapter.getAdaptedResource(adaptable);
            }
        }

        return resource;
    }

    private BusinessObjectModelRepositoryStore getBdmRepositoryStore() {
        return repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    @Override
    public void dispose() {
        repositoryAccessor.getWorkspace().removeResourceChangeListener(this);
        BusinessObjectPlugin.getDefault().getPreferenceStore().removePropertyChangeListener(this);
        super.dispose();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (Objects.equals(event.getProperty(), BusinessObjectModelFileStore.BDM_DEPLOY_REQUIRED_PROPERTY)) {
            LabelProviderChangedEvent e = new LabelProviderChangedEvent(this);
            PlatformUI.getWorkbench().getDisplay().asyncExec(() -> fireLabelProviderChanged(e));
        }
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        try {
            if (event.getDelta() != null
                    && Objects.equals(event.getType(), IResourceChangeEvent.POST_CHANGE)
                    && !isDeployRequired()) {
                event.getDelta().accept(this::updateDeployRequiredProperty);
            }
        } catch (final CoreException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isDeployRequired() {
        if (BusinessObjectPlugin.getDefault() != null
                && BusinessObjectPlugin.getDefault().getPreferenceStore() != null) {
            return BusinessObjectPlugin.getDefault().getPreferenceStore()
                    .getBoolean(BusinessObjectModelFileStore.BDM_DEPLOY_REQUIRED_PROPERTY);
        }
        return false;
    }

    private boolean updateDeployRequiredProperty(IResourceDelta delta) {
        if (Objects.equals(delta.getResource().getName(), BusinessObjectModelFileStore.BOM_FILENAME)) {
            switch (delta.getKind()) {
                case IResourceDelta.CHANGED:
                case IResourceDelta.ADDED:
                    notifyDeployRequired(delta);
                    break;
                case IResourceDelta.REMOVED:
                    cleanDeployRequiredState();
                    break;
                default:
                    break;
            }
            return false;
        }
        return true;
    }

    private void cleanDeployRequiredState() {
        BusinessObjectPlugin.getDefault().getPreferenceStore()
                .setValue(BusinessObjectModelFileStore.BDM_DEPLOY_REQUIRED_PROPERTY, false);
    }

    private void notifyDeployRequired(IResourceDelta delta) {
        BusinessObjectPlugin.getDefault().getPreferenceStore()
                .setValue(BusinessObjectModelFileStore.BDM_DEPLOY_REQUIRED_PROPERTY, true);
        IPreferenceStore preferenceStore = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        if (Objects.equals(delta.getKind(), IResourceDelta.CHANGED)
                && preferenceStore.getBoolean(BonitaPreferenceConstants.NOTIFY_BDM_DEPLOYMENT_REQUIRED)) {
            BonitaNotificator.openNotification(Messages.bdmDeployRequiredTitle, Messages.bdmDeployRequired,
                    e -> {
                        BonitaPreferenceDialog dialog = new BonitaPreferenceDialog(new Shell(Display.getDefault()));
                        dialog.create();
                        dialog.setSelectedPreferencePage(BonitaPreferenceDialog.ADVANCED_PAGE_ID);
                        dialog.open();
                    });
        }
    }

}
