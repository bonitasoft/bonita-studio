/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.coolbar;

import java.util.Collections;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.handlers.DirtyStateTracker;

public class SaveCoolbarItem extends ContributionItem
        implements IBonitaContributionItem, ISelectionChangedListener, IPartListener {

    private ToolItem item;
    private DirtyStateTracker dirtyStateTracker;

    private Command getCommand() {
        final ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
        return service.getCommand("org.eclipse.ui.file.save");
    }

    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.save";
    }

    @Override
    public boolean isEnabled() {
        final Command cmd = getCommand();
        return cmd.isEnabled();
    }

    @Override
    public void fill(final ToolBar toolbar, final int index, final int iconSize) {
        item = new ToolItem(toolbar, SWT.PUSH);
        item.setToolTipText(Messages.SaveProcessButtonLabel);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_SAVE_EDITOR);
        if (iconSize < 0) {
            item.setImage(Pics.getImage(PicsConstants.coolbar_save_32));
            item.setHotImage(Pics.getImage(PicsConstants.coolbar_save_hot_32));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_save_disabled_32));
        } else {
            item.setImage(Pics.getImage(PicsConstants.coolbar_save_24));
            item.setHotImage(Pics.getImage(PicsConstants.coolbar_save_hot_24));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_save_disabled_24));
        }
        item.setEnabled(false);
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .getActiveEditor();
                if (editor != null) {
                    if (editor instanceof DiagramEditor) {
                        final IHandlerService handlerService = PlatformUI.getWorkbench()
                                .getService(IHandlerService.class);
                        final Command command = getCommand();
                        final ExecutionEvent executionEvent = new ExecutionEvent(command, Collections.EMPTY_MAP, null,
                                handlerService.getClass());
                        try {
                            command.executeWithChecks(executionEvent);
                        } catch (final Exception e1) {
                            BonitaStudioLog.error(e1);
                        }

                    } else {
                        editor.doSave(null);
                    }
                }
            }
        });
    }

    public DirtyStateTracker getDirtyStateTracker() {
        if (dirtyStateTracker == null) {
            createDirtyStateTracker();
        }
        return dirtyStateTracker;
    }

    public void createDirtyStateTracker() {
        dirtyStateTracker = new DirtyStateTracker() {

            @Override
            public void propertyChanged(final Object source, final int propID) {
                IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .getActiveEditor();
                if (activeEditor instanceof ISaveablePart && propID == ISaveablePart.PROP_DIRTY) {
                    update();
                    if (item != null && !item.isDisposed()) {
                        item.setEnabled(((ISaveablePart) activeEditor).isDirty());
                    }
                }
            }
        };
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        if (item != null && !item.isDisposed()) {
            item.getDisplay().asyncExec(new Runnable() {

                @Override
                public void run() {
                    if (item != null && !item.isDisposed()) {
                        item.setEnabled(getCommand().isEnabled());
                    }
                }
            });

        }
    }

    @Override
    public void partActivated(IWorkbenchPart part) {
        if (part instanceof ISaveablePart) {
            final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker();
            dirtyStateTracker.partActivated(part);
        }
    }

    @Override
    public void partBroughtToTop(IWorkbenchPart part) {
        if (part instanceof ISaveablePart) {
            final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker();
            dirtyStateTracker.partBroughtToTop(part);
        }
    }

    @Override
    public void partClosed(IWorkbenchPart part) {
        if (part instanceof ISaveablePart) {
            final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker();
            dirtyStateTracker.partClosed(part);
        }
    }

    @Override
    public void partDeactivated(IWorkbenchPart part) {
        if (part instanceof ISaveablePart) {
            final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker();
            dirtyStateTracker.partDeactivated(part);
        }
    }

    @Override
    public void partOpened(IWorkbenchPart part) {
        if (part instanceof ISaveablePart) {
            final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker();
            dirtyStateTracker.partOpened(part);
        }
    }

    @Override
    public String getText() {
        return Messages.SaveProcessButtonLabel;
    }

}
