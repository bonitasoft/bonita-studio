/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.editingdomain;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class BonitaEditingDomainUtil {

    private static final String BASE_ID = "org.bonitasoft.studio.diagram.EditingDomain";

	private static final class ModificationAdapter implements Adapter {
		private final NotificationFilter notifactionFilter;
		private Notifier target;

		private ModificationAdapter(final NotificationFilter notifactionFilter) {
			this.notifactionFilter = notifactionFilter;
		}

		@Override
        public void notifyChanged(final Notification notification) {
			if (notifactionFilter.matches(notification)) {
				final Object value = notification.getNewValue();
				if (value instanceof Resource) {
					((Resource) value).setTrackingModification(true);
				}
			}
		}

		@Override
        public void setTarget(final Notifier newTarget) {
			target = newTarget;
		}

		@Override
        public boolean isAdapterForType(final Object type) {
			return type == ModificationAdapter.class;
		}

		@Override
        public Notifier getTarget() {
			return target;
		}

	}

	private static void addResourceTracking(final TransactionalEditingDomain editingDomain) {
		final EList<Adapter> adapters = editingDomain.getResourceSet().eAdapters();
		final Adapter adapter = EcoreUtil.getAdapter(adapters, ModificationAdapter.class);
		if (adapter == null) {
			final NotificationFilter diagramResourceModifiedFilter = NotificationFilter.createNotifierFilter(editingDomain.getResourceSet())
					.and(NotificationFilter.createEventTypeFilter(Notification.ADD))
					.and(NotificationFilter.createFeatureFilter(ResourceSet.class, ResourceSet.RESOURCE_SET__RESOURCES));
			adapters.add(new ModificationAdapter(diagramResourceModifiedFilter));
		}
	}

	public static TransactionalEditingDomain getSharedEditingDomain(final String id) {
		final TransactionalEditingDomain editingDomain = BonitaEditingDomainRegistry.INSTANCE.getEditingDomain(id);
		addResourceTracking(editingDomain);
		BonitaResourceSetInfoDelegate.adapt(editingDomain);
		return editingDomain;
	}

    public static TransactionalEditingDomain getSharedEditingDomain(final URI uri) {
        org.eclipse.core.runtime.Assert.isLegal(uri != null);
        final String filename = URI.decode(uri.lastSegment());
        return getSharedEditingDomain(BASE_ID + "." + filename);
    }

    public static void cleanEditingDomainRegistry() {
        BonitaEditingDomainRegistry.INSTANCE.removeAll();
    }

}
