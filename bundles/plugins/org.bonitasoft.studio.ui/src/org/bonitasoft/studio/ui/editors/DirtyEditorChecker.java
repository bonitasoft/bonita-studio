package org.bonitasoft.studio.ui.editors;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

@Creatable
public class DirtyEditorChecker {

    public boolean checkDirtyState(IRunnableContext runnableCtx, boolean allowNoSave) {
        if (PlatformUI.isWorkbenchRunning()
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            List<IEditorPart> dirtyEditors = Arrays
                    .asList(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences())
                    .stream()
                    .map(ref -> ref.getEditor(false))
                    .filter(Objects::nonNull)
                    .filter(IEditorPart::isDirty)
                    .collect(Collectors.toList());
            if (!dirtyEditors.isEmpty()) {
                SaveStrategy strategy = saveDirtyEditors(allowNoSave);
                if (Objects.equals(strategy, SaveStrategy.CANCEL)) {
                    return false;
                } else if (Objects.equals(strategy, SaveStrategy.SAVE)) {
                    try {
                        runnableCtx.run(false, false, monitor -> {
                            monitor.beginTask(Messages.savingEditors, dirtyEditors.size());
                            dirtyEditors.forEach(editor -> {
                                editor.doSave(monitor);
                                monitor.worked(1);
                            });
                            monitor.done();
                        });
                    } catch (InvocationTargetException | InterruptedException e) {
                        throw new RuntimeException("An error occured while saving editors", e);
                    }
                }
            }
        }
        return true;
    }
    
    private SaveStrategy saveDirtyEditors(boolean allowNoSave) {
        int choice = SaveBeforeDeployDialog.open(Messages.saveOpenedEditorsTitle,
                Messages.saveOpenedEditors,
                allowNoSave ? new String[] {IDialogConstants.CANCEL_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.YES_LABEL} : 
                    new String[] {IDialogConstants.CANCEL_LABEL, IDialogConstants.YES_LABEL});
        switch (choice) {
            case 0:
                return SaveStrategy.CANCEL;
            case 1:
                return allowNoSave ? SaveStrategy.DONT_SAVE : SaveStrategy.SAVE;
            default:
                return SaveStrategy.SAVE;
        }
    }

    enum SaveStrategy {
        SAVE, DONT_SAVE, CANCEL
    }

    
    
}
