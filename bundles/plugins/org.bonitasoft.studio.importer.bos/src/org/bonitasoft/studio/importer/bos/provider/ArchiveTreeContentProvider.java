package org.bonitasoft.studio.importer.bos.provider;

import java.util.Objects;

import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.AbstractImportModel;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.model.SmartImportFileStoreModel;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

public class ArchiveTreeContentProvider implements ILazyTreeContentProvider {

    private final TreeViewer viewer;

    public ArchiveTreeContentProvider(TreeViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void dispose() {
        //NOTHING TO DISPOSE
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        //NOTHING TO DO
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof AbstractImportModel) {
            return ((AbstractImportModel) element).getParent().orElse(null);
        } else if (element instanceof SmartImportableUnit) {
            return ((SmartImportableUnit) element).getParentModel();
        }
        return null;
    }

    @Override
    public void updateChildCount(Object element, int currentChildCount) {
        if (element instanceof ImportArchiveModel) {
            viewer.setChildCount(element, childCount((ImportArchiveModel) element));
        } else if (element instanceof AbstractFolderModel) {
            viewer.setChildCount(element, childCount((AbstractFolderModel) element));
        } else if (element instanceof SmartImportFileStoreModel
                && ((SmartImportFileStoreModel) element).isSmartImportable()) {
            viewer.setChildCount(element, childCount((SmartImportFileStoreModel) element));
        }
    }

    private int childCount(ImportArchiveModel element) {
        return element.getStores().size();
    }

    private int childCount(AbstractFolderModel element) {
        return element.getChildren().length;
    }

    private int childCount(SmartImportFileStoreModel element) {
        return Objects.equals(element.getImportAction(), ImportAction.SMART_IMPORT)
                ? element.getChildren().size()
                : 0;
    }

    @Override
    public void updateElement(Object parent, int index) {
        if (parent instanceof AbstractFolderModel) {
            Object child = getChild((AbstractFolderModel) parent, index);
            viewer.replace(parent, index, child);
            updateChildCount(child, -1);
        } else if (parent instanceof ImportArchiveModel) {
            Object child = getChild((ImportArchiveModel) parent, index);
            viewer.replace(parent, index, child);
            updateChildCount(child, -1);
        } else if (parent instanceof SmartImportFileStoreModel) {
            Object child = getChild((SmartImportFileStoreModel) parent, index);
            viewer.replace(parent, index, child);
        }
    }

    private Object getChild(ImportArchiveModel parent, int index) {
        return parent.getStores().get(index);
    }

    private Object getChild(AbstractFolderModel parent, int index) {
        return parent.getChildren()[index];
    }

    private Object getChild(SmartImportFileStoreModel parent, int index) {
        return parent.getChildren().get(index);
    }
}
