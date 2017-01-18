package org.bonitasoft.studio.importer.bos.provider;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ArchiveTreeContentProvider implements ITreeContentProvider {

    @Override
    public Object[] getElements(Object inputElement) {
        return ((ImportArchiveModel) inputElement).getStores().toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        List<Object> res = new ArrayList<Object>();
        if (parentElement instanceof AbstractFolderModel) {
            ((AbstractFolderModel) parentElement).getFiles().stream().forEach(son -> res.add(son));
            ((AbstractFolderModel) parentElement).getFolders().stream().forEach(son -> res.add(son));
        }
        return res.toArray();
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof AbstractFolderModel) {
            return ((AbstractFolderModel) element).getParent();
        }
        return ((AbstractFileModel) element).getParent();
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof AbstractFolderModel) {
            return !(((AbstractFolderModel) element).getFiles().isEmpty()
                    && ((AbstractFolderModel) element).getFolders().isEmpty());
        }
        return false;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub
    }
}
