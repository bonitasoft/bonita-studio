package org.bonitasoft.studio.importer.bos.provider;

import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.ConflictStatus;
import org.bonitasoft.studio.importer.bos.model.ImportAction;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;

public class ImportActionEditingSupport extends EditingSupport {

    private final TreeViewer viewer;
    private final ComboBoxViewerCellEditor editor;

    public ImportActionEditingSupport(TreeViewer viewer) {
        super(viewer);
        this.viewer = viewer;
        this.editor = new ComboBoxViewerCellEditor(viewer.getTree(), SWT.BORDER | SWT.READ_ONLY);
        editor.setActivationStyle(ComboBoxViewerCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION);
        editor.setContentProvider(ArrayContentProvider.getInstance());
        editor.setLabelProvider(new LabelProvider());
        editor.setInput(ImportAction.values());
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return editor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return element instanceof AbstractFileModel
                && ((AbstractFileModel) element).getStatus() == ConflictStatus.CONFLICTING;
    }

    @Override
    protected Object getValue(Object element) {
        if (element instanceof AbstractFileModel) {
            return ((AbstractFileModel) element).getImportAction();
        }
        return ImportAction.OVERWRITE;
    }

    @Override
    protected void setValue(Object element, Object value) {
        if (element instanceof AbstractFileModel) {
            ((AbstractFileModel) element)
                    .setImportAction((ImportAction) value);
            viewer.update(element, null);
        }
    }
}
