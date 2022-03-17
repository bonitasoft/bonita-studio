/**
 * Copyright (C) 2012 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy.ui.viewer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.commands.contexts.Context;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.INestableKeyBindingService;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.LegacyHandlerSubmissionExpression;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.KeyBindingService;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.actions.CommandAction;
import org.eclipse.ui.internal.handlers.CommandLegacyActionWrapper;

/**
 * Fork KeyBindingService to avoid handler conflict in Groovy expression dialog
 */
public class CustomKeyBindingService implements INestableKeyBindingService {
    /**
     * Whether this key binding service has been disposed. A disposed key binding
     * service should not be used again.
     */
    private boolean disposed;

    private final Map<IWorkbenchSite, IKeyBindingService> nestedServices = new HashMap<>();

    /**
     * The set of context identifiers enabled in this key binding service (not
     * counting any nested services). This set may be empty, but it is never
     * <code>null</code>.
     */
    private Set<String> enabledContextIds = Collections.EMPTY_SET;

    /**
     * The site within the workbench at which this service is provided. This value
     * should not be <code>null</code>.
     */
    private IWorkbenchPartSite workbenchPartSite;

    private CustomKeyBindingService parent;

    private IKeyBindingService activeService;

    private Map<IAction, IHandlerActivation> actionToProxy = new HashMap<>();

    /**
     * Constructs a new instance of <code>KeyBindingService</code> on a given
     * workbench site. This instance is not nested.
     *
     * @param workbenchPartSite The site for which this service will be responsible;
     *                          should not be <code>null</code>.
     */
    public CustomKeyBindingService(IWorkbenchPartSite workbenchPartSite) {
        this.workbenchPartSite = workbenchPartSite;
    }

    /**
     * Constructs a new instance of <code>KeyBindingService</code> on a given
     * workbench site.
     *
     * @param workbenchPartSite The site for which this service will be responsible;
     *                          should not be <code>null</code>.
     * @param parent            The parent key binding service, if any;
     *                          <code>null</code> if none.
     */
    CustomKeyBindingService(IWorkbenchPartSite workbenchPartSite, CustomKeyBindingService parent) {
        this.workbenchPartSite = workbenchPartSite;
        this.parent = parent;
    }

    @Override
    public boolean activateKeyBindingService(IWorkbenchSite nestedSite) {
        if (disposed) {
            return false;
        }

        // Check if we should do a deactivation.
        if (nestedSite == null) {
            // We should do a deactivation, if there is one active.
            if (activeService == null) {
                // There is no active service. Do no work.
                return false;
            }
            // Deactivate the currently active nested service.
            deactivateNestedService();
            return true;
        }

        // Attempt to activate a service.
        final IKeyBindingService service = nestedServices.get(nestedSite);

        if (service == activeService) {
            // The service is already active, or already null
            return false;
        }

        deactivateNestedService();
        if (service != null) {
            activateNestedService(service);
        }
        return true;
    }

    private void activateNestedService(IKeyBindingService service) {
        /*
         * If I have a parent, and I'm the active service, then deactivate so that I can
         * make changes.
         */
        boolean active = false;
        boolean haveParent = (parent != null);
        if (haveParent) {
            active = (parent.activeService == this);
            if (active) {
                parent.deactivateNestedService();
            }
        }

        // Update the active service.
        activeService = service;

        // Check to see that the service isn't null.
        if (service == null) {
            return;
        }

        if (haveParent) {
            if (active) {
                parent.activateNestedService(this);
            }

        } else if (activeService instanceof KeyBindingService) {
            // add all the nested context ids.

            EContextService cs = ((CustomKeyBindingService) activeService).workbenchPartSite
                    .getService(EContextService.class);
            for (String id : ((CustomKeyBindingService) activeService).enabledContextIds) {
                cs.activateContext(id);
            }
            /*
             * add all of the nested handler submissions.
             */
            IHandlerService hs = ((CustomKeyBindingService) activeService).workbenchPartSite
                    .getService(IHandlerService.class);
            Iterator<Entry<IAction, IHandlerActivation>> i = ((CustomKeyBindingService) activeService).actionToProxy
                    .entrySet().iterator();
            while (i.hasNext()) {
                Entry<IAction, IHandlerActivation> entry = i.next();
                hs.activateHandler(entry.getValue());
            }
        }
    }

    private void deactivateNestedService() {
        if (disposed) {
            return;
        }

        // Don't do anything if there is no active service.
        if (activeService == null) {
            return;
        }

        // Check to see if there is a parent.
        boolean active = false;
        if (parent != null) {
            // Check if I'm the active service.
            if (parent.activeService == this) {
                active = true;
                // Deactivate myself so I can make changes.
                parent.deactivateNestedService();
            }

        } else if (activeService instanceof KeyBindingService) {
            // Remove all the nested context ids.

            EContextService cs = ((CustomKeyBindingService) activeService).workbenchPartSite
                    .getService(EContextService.class);
            for (String id : ((CustomKeyBindingService) activeService).enabledContextIds) {
                cs.deactivateContext(id);
            }
            /*
             * Remove all of the nested handler submissions. The handlers here weren't
             * created by this instance (but by the nest instance), and hence can't be
             * disposed here.
             */
            IHandlerService hs = ((CustomKeyBindingService) activeService).workbenchPartSite
                    .getService(IHandlerService.class);
            hs.deactivateHandlers(((CustomKeyBindingService) activeService).actionToProxy.values());
        }

        // Clear our reference to the active service.
        activeService = null;

        // If necessary, let my parent know that changes have occurred.
        if (active) {
            parent.activateNestedService(this);
        }
    }

    /**
     * Disposes this key binding service. This clears out all of the submissions
     * held by this service, and its nested services.
     */
    public void dispose() {
        if (!disposed) {
            disposed = true;
            deactivateNestedService();
            EContextService cs = workbenchPartSite.getService(EContextService.class);
            for (String id : enabledContextIds) {
                cs.deactivateContext(id);
            }
            enabledContextIds.clear();
            /*
             * Remove all of the nested handler submissions. The handlers here weren't
             * created by this instance (but by the nest instance), and hence can't be
             * disposed here.
             */
            IHandlerService hs = workbenchPartSite.getService(IHandlerService.class);
            hs.deactivateHandlers(actionToProxy.values());
            actionToProxy.clear();
        }

    }

    @Override
    public IKeyBindingService getKeyBindingService(IWorkbenchSite nestedSite) {
        if (disposed) {
            return null;
        }

        if (nestedSite == null) {
            return null;
        }

        IKeyBindingService service = nestedServices.get(nestedSite);
        if (service == null) {
            // TODO the INestedKeyBindingService API should be based on
            // IWorkbenchPartSite..
            if (nestedSite instanceof IWorkbenchPartSite) {
                service = new CustomKeyBindingService((IWorkbenchPartSite) nestedSite, this);
            } else {
                service = new CustomKeyBindingService(null, this);
            }

            nestedServices.put(nestedSite, service);
        }

        return service;
    }

    @Override
    public String[] getScopes() {
        if (disposed) {
            return null;
        }

        // Build the list of active scopes
        final Set<String> activeScopes = new HashSet<>();
        activeScopes.addAll(enabledContextIds);
        if (activeService instanceof KeyBindingService) {
            activeScopes.addAll(((CustomKeyBindingService) activeService).enabledContextIds);
        }

        return activeScopes.toArray(new String[activeScopes.size()]);
    }

    @Override
    public void registerAction(IAction action) {
        if (disposed) {
            return;
        }

        if (action instanceof CommandLegacyActionWrapper) {
            // this is a registration of a fake action for an already
            // registered handler
            WorkbenchPlugin.log("Cannot register a CommandLegacyActionWrapper back into the system"); //$NON-NLS-1$
            return;
        }

        if (action instanceof CommandAction) {
            // we unfortunately had to allow these out into the wild, but they
            // still must not feed back into the system
            return;
        }

        unregisterAction(action);
        
        IWorkbenchPartSite partSite = workbenchPartSite;
        if (parent != null) {
            CustomKeyBindingService currentParent = parent;
            while (currentParent != null) {
                partSite = currentParent.workbenchPartSite;
                currentParent = currentParent.parent;
            }
        }

        String commandId = action.getActionDefinitionId();
        if (commandId != null) {
            for (IAction registeredAction : actionToProxy.keySet()) {
                // we also need to unregister any other action that may have
                // been registered with the same definition id
                if (commandId.equals(registeredAction.getActionDefinitionId())) {
                    unregisterAction(registeredAction);
                    break;
                }
            }
            IHandlerService hs = workbenchPartSite.getService(IHandlerService.class);
            actionToProxy.put(action, hs.activateHandler(commandId, new ActionHandler(action),
                    new LegacyHandlerSubmissionExpression(null, partSite.getShell(), partSite)));

//          IHandlerService hs = workbenchPartSite.getService(IHandlerService.class);
//          actionToProxy.put(action, hs.activateHandler(commandId, new ActionHandler(action)));
        }
    }

    @Override
    public boolean removeKeyBindingService(IWorkbenchSite nestedSite) {
        if (disposed) {
            return false;
        }

        final IKeyBindingService service = nestedServices.remove(nestedSite);
        if (service == null) {
            return false;
        }

        if (service.equals(activeService)) {
            deactivateNestedService();
        }

        return true;
    }

    @Override
    public void setScopes(String[] scopes) {
        if (disposed) {
            return;
        }
        Set<String> oldContextIds = enabledContextIds;
        enabledContextIds = new HashSet<>(Arrays.asList(scopes));
        EContextService cs = workbenchPartSite.getService(EContextService.class);
        addParents(cs, scopes);

        for (String id : oldContextIds) {
            if (!enabledContextIds.contains(id)) {
                cs.deactivateContext(id);
            }
        }
        for (String id : enabledContextIds) {
            if (!oldContextIds.contains(id)) {
                cs.activateContext(id);
            }
        }
    }

    /**
     * @param cs
     * @param scopes
     */
    private void addParents(EContextService cs, String[] scopes) {
        for (String id : scopes) {
            try {
                Context current = cs.getContext(id);
                String parentId = current.getParentId();
                while (parentId != null && !enabledContextIds.contains(parentId)) {
                    enabledContextIds.add(parentId);
                    current = cs.getContext(parentId);
                    parentId = current.getParentId();
                }
            } catch (NotDefinedException e) {
                WorkbenchPlugin.log(e);
            }
        }
    }

    @Override
    public void unregisterAction(IAction action) {
        if (disposed) {
            return;
        }

        if (action instanceof CommandLegacyActionWrapper) {
            // this is a registration of a fake action for an already
            // registered handler
            WorkbenchPlugin.log("Cannot unregister a CommandLegacyActionWrapper out of the system"); //$NON-NLS-1$
            return;
        }

        IHandlerActivation activation = actionToProxy.remove(action);
        if (activation == null) {
            return;
        }
        IHandlerService hs = workbenchPartSite.getService(IHandlerService.class);
        hs.deactivateHandler(activation);
    }
}
