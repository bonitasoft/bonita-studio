/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.notification;

import java.util.Optional;

import org.bonitasoft.studio.ui.UIPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;

public class BonitaNotificator {

    public static enum NOTIFICATION_LEVEL {
        INFO, WARNING, ERROR
    }

    public static void openInfoNotification(String title, String content) {
        openInfoNotification(title, content, null);
    }

    public static void openWarningNotification(String title, String content) {
        openWarningNotification(title, content, null);
    }

    public static void openErrorNotification(String title, String content) {
        openErrorNotification(title, content, null);
    }

    public static void openInfoNotification(String title, String content, Listener selectionListener) {
        openNotification(title, content, NOTIFICATION_LEVEL.INFO, selectionListener);
    }

    public static void openWarningNotification(String title, String content, Listener selectionListener) {
        openNotification(title, content, NOTIFICATION_LEVEL.WARNING, selectionListener);
    }

    public static void openErrorNotification(String title, String content, Listener selectionListener) {
        openNotification(title, content, NOTIFICATION_LEVEL.ERROR, selectionListener);
    }

    private static void openNotification(String title, String content, NOTIFICATION_LEVEL level,
            Listener selectionListener) {
        if (UIPlugin.getDefault().isNotificationEnabled()) {
            Display display = Display.getDefault();
            display.asyncExec(() -> new BonitaNotificationPopup(display, title, content, level,
                    Optional.ofNullable(selectionListener)).open());
        }
    }

}
