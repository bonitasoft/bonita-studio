package org.bonitasoft.studio.importer.bos.provider;

import java.util.stream.Collectors;

import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.AbstractImportModel;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
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
        return ((AbstractImportModel) element).getParent().orElse(null);
    }

    @Override
    public void updateChildCount(Object element, int currentChildCount) {
        if (element instanceof ImportArchiveModel) {
            viewer.setChildCount(element, childCount((ImportArchiveModel) element));
        } else if (element instanceof AbstractFolderModel) {
            viewer.setChildCount(element, childCount((AbstractFolderModel) element));
        }
    }

    private int childCount(ImportArchiveModel element) {
        return element.getStores().size();
    }

    private int childCount(AbstractFolderModel element) {
        return element.getChildren().length;
    }

    @Override
    public void updateElement(Object parent, int index) {
        if (parent instanceof AbstractFolderModel) {
            final Object child = getChild((AbstractFolderModel) parent, index);
            viewer.replace(parent, index, child);
            updateChildCount(child, -1);
        } else if (parent instanceof ImportArchiveModel) {
            final Object child = getChild((ImportArchiveModel) parent, index);
            viewer.replace(parent, index, child);
            updateChildCount(child, -1);
        }
    }

    private Object getChild(ImportArchiveModel parent, int index) {
        return parent.getStores().get(index);
    }

    private Object getChild(AbstractFolderModel parent, int index) {
        return parent.getChildren()[index];
    }
}
