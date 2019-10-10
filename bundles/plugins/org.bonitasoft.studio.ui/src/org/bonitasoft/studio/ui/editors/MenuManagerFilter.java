/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.swt.AbstractPartRenderer;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MPopupMenu;
import org.eclipse.e4.ui.workbench.renderers.swt.MenuManagerRenderer;
import org.eclipse.e4.ui.workbench.swt.factories.IRendererFactory;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.internal.PartSite;

import com.google.common.collect.Iterables;

public class MenuManagerFilter {

    public void filter(IWorkbenchPartSite site) {
        final MPart part = ((PartSite) site).getModel();
        if (!part.getMenus().isEmpty()) {
            final List<MMenu> popupMenus = newArrayList(Iterables.filter(part.getMenus(), instanceOf(MPopupMenu.class)));
            for (final MenuManager mm : getMenuManagers(part.getContext(), popupMenus)) {
                mm.addMenuListener(new PopupMenuFilterListener(site.getSelectionProvider()));
            }
        }
    }

    private List<MenuManager> getMenuManagers(final IEclipseContext modelContext, final List<MMenu> popupMenus) {
        final List<MenuManager> result = new ArrayList<>();
        if (modelContext != null) {
            final IRendererFactory factory = modelContext.get(IRendererFactory.class);
            if (factory != null) {
                for (final MMenu pMenu : popupMenus) {
                    final AbstractPartRenderer renderer = factory.getRenderer(pMenu, null);
                    if (renderer instanceof MenuManagerRenderer) {
                        final MenuManager manager = ((MenuManagerRenderer) renderer).getManager(pMenu);
                        if (manager != null) {
                            result.add(manager);
                        }
                    }
                }
            }
        }
        return result;
    }

}
