/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.jface;

import java.util.concurrent.CancellationException;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class FileActionDialog {

    protected static boolean YES_TO_ALL = false;
    protected static boolean NO_TO_ALL = false;
    protected static boolean YES_NO_TO_ALL = false;
    protected static boolean DISABLE_POPUP = false; //Tests purpose
    private static boolean answer;
    private static int returnCode;
    private static boolean THROW_EXCEPTION_ON_CANCEL = false;

    public static void setDisablePopup(final boolean disablePopup) {
        DISABLE_POPUP = disablePopup;
    }

    public static void setThrowExceptionOnCancel(final boolean throwExceptionOnCancel) {
        THROW_EXCEPTION_ON_CANCEL = throwExceptionOnCancel;
    }

    public static boolean overwriteQuestion(final String fileName) {
        if (DISABLE_POPUP && NO_TO_ALL) {
            return false;
        } else if (DISABLE_POPUP) {
            return true;
        } else {
            if (YES_NO_TO_ALL) {
                if (YES_TO_ALL) {
                    return true;
                }
                if (NO_TO_ALL) {
                    return false;
                }
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        returnCode = new YesNoToAllDialog(Display.getDefault().getActiveShell(), Messages.overwriteTitle,
                                Messages.bind(
                                        Messages.overwriteMessage, fileName)).open();
                    }
                });
                if (returnCode == YesNoToAllDialog.YES_TO_ALL) {
                    YES_TO_ALL = true;
                }
                if (returnCode == YesNoToAllDialog.NO_TO_ALL) {
                    NO_TO_ALL = true;
                }
                return returnCode == YesNoToAllDialog.YES || returnCode == YesNoToAllDialog.YES_TO_ALL;
            } else {

                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        answer = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), Messages.overwriteTitle,
                                Messages.bind(Messages.overwriteMessage, fileName));
                    }
                });
                if (THROW_EXCEPTION_ON_CANCEL && !answer) {
                    throw new CancellationException();
                }
                return answer;
            }
        }
    }

    public static boolean getDisablePopup() {
        return DISABLE_POPUP;
    }

    public static boolean confirmDeletionQuestion(final String fileName) {
        if (DISABLE_POPUP && NO_TO_ALL) {
            return false;
        } else if (DISABLE_POPUP) {
            return true;
        } else {
            if (YES_NO_TO_ALL) {
                if (YES_TO_ALL) {
                    return true;
                }
                if (NO_TO_ALL) {
                    return false;
                }
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        returnCode = new YesNoToAllDialog(Display.getDefault().getActiveShell(),
                                Messages.deleteConfirmationTitle,
                                String.format(Messages.deleteConfirmationMsg, fileName)).open();
                    }
                });

                if (returnCode == YesNoToAllDialog.YES_TO_ALL) {
                    YES_TO_ALL = true;
                }
                if (returnCode == YesNoToAllDialog.NO_TO_ALL) {
                    NO_TO_ALL = true;
                }
                return returnCode == YesNoToAllDialog.YES || returnCode == YesNoToAllDialog.YES_TO_ALL;
            } else {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        answer = MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                                Messages.deleteConfirmationTitle,
                                String.format(Messages.deleteConfirmationMsg, fileName));
                    }
                });
                return answer;
            }
        }

    }

    public static boolean confirmDeletionQuestionWithCustomMessage(final String message) {
        if (DISABLE_POPUP && NO_TO_ALL) {
            return false;
        } else if (DISABLE_POPUP) {
            return true;
        } else {
            if (YES_NO_TO_ALL) {
                if (YES_TO_ALL) {
                    return true;
                }
                if (NO_TO_ALL) {
                    return false;
                }
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        returnCode = new YesNoToAllDialog(Display.getDefault().getActiveShell(),
                                Messages.deleteConfirmationTitle,
                                message).open();
                    }
                });

                if (returnCode == YesNoToAllDialog.YES_TO_ALL) {
                    YES_TO_ALL = true;
                }
                if (returnCode == YesNoToAllDialog.NO_TO_ALL) {
                    NO_TO_ALL = true;
                }
                return returnCode == YesNoToAllDialog.YES || returnCode == YesNoToAllDialog.YES_TO_ALL;
            } else {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        final String[] buttonLabels = { Messages.delete, IDialogConstants.CANCEL_LABEL };
                        final MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(),
                                Messages.deleteConfirmationTitle, null, message,
                                MessageDialog.QUESTION, buttonLabels, 0);
                        answer = dialog.open() == 0;
                    }
                });
                return answer;
            }
        }

    }

    public static void activateYesNoToAll() {
        YES_NO_TO_ALL = true;
    }

    public static void deactivateYesNoToAll() {
        YES_NO_TO_ALL = false;
        YES_TO_ALL = false;
        NO_TO_ALL = false;
    }

    public static void setNoToAll() {
        NO_TO_ALL = true;
        YES_TO_ALL = false;
    }

    public static void setYesToAll() {
        NO_TO_ALL = false;
        YES_TO_ALL = true;
    }

}
