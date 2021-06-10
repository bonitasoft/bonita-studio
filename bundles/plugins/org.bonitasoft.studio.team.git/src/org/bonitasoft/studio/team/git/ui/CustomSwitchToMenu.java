/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.ui;

import java.lang.reflect.Field;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.egit.ui.internal.actions.SwitchToMenu;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.services.IServiceLocator;

public class CustomSwitchToMenu extends SwitchToMenu {

    private IHandlerService handlerService;

    public CustomSwitchToMenu(IHandlerService handlerService) {
        this.handlerService = handlerService;
    }

    @Override
    public void initialize(IServiceLocator serviceLocator) {
        super.initialize(serviceLocator);
        try {
            Field field = SwitchToMenu.class.getDeclaredField("handlerService");
            field.setAccessible(true);
            field.set(this, handlerService);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            BonitaStudioLog.error(e);
        }

    }
}
