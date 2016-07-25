/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.extension;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 */
public class ExtensionContextInjectionFactory {

    private static IEclipseContext HEADLESS_CONTEXT;

    public <T> T make(final IConfigurationElement element, final String classNameAttribute, final Class<T> extension) throws ClassNotFoundException {
        return make(element, classNameAttribute, extension, workbenchContext());
    }

    private Bundle getDeclaringBundle(final IConfigurationElement element) {
        return Platform.getBundle(element.getDeclaringExtension().getNamespaceIdentifier());
    }

    public static IEclipseContext workbenchContext() {
        if (PlatformUI.isWorkbenchRunning()) {
            final Workbench workbench = (Workbench) PlatformUI.getWorkbench();
            if (workbench != null) {
                final IEclipseContext context = workbench.getContext();
                checkNotNull(context, "Workbench eclipse context is null");
                return context;
            }
            throw new IllegalStateException("No workbench available");
        }
        return headlessContext();
    }

    private static synchronized IEclipseContext headlessContext() {
        if (HEADLESS_CONTEXT == null) {
            HEADLESS_CONTEXT = EclipseContextFactory.create("headlessContext");
        }
        return HEADLESS_CONTEXT;
    }

    @SuppressWarnings("unchecked")
    public <T> T make(final IConfigurationElement element, final String classNameAttribute, final Class<T> extension, final IEclipseContext context)
            throws ClassNotFoundException {
        final String className = element.getAttribute(classNameAttribute);
        final Class<T> loadClass = (Class<T>) getDeclaringBundle(element).loadClass(className);
        return ContextInjectionFactory.make(loadClass, context);
    }
}
