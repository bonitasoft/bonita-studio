package org.bonitasoft.studio.importer.bos.operation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.internal.jobs.Counter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ContainerHelper {

    public List<IFolder> getFolders(final IContainer container) throws ResourceImportException {
        IResource[] folders;
        try {
            folders = container.members(IContainer.FOLDER);
        } catch (final CoreException e) {
            throw new ResourceImportException("Failed to retrieve folders of resource " + container.getName(), e);
        }
        final List<IFolder> folderList = new ArrayList<IFolder>();
        if (folders != null) {
            for (final IResource res : folders) {
                if (res instanceof IFolder) {
                    folderList.add((IFolder) res);
                }
            }
        }
        return folderList;
    }

    /**
     * @return the number of file to import in the input folder list
     * @throws CoreException
     */
    public final Counter getNbFile(List<IFolder> folderList) throws CoreException {
        final Counter nbFileToImport = new Counter();
        for (final IFolder folder : folderList) {
            processContainer(folder, nbFileToImport);
        }
        return nbFileToImport;
    }

    private void processContainer(final IContainer container, final Counter nbFileToImport) throws CoreException {
        final IResource[] members = container.members();
        for (final IResource member : members) {
            if (member instanceof IFolder) {
                processContainer((IFolder) member, nbFileToImport);
            } else if (member instanceof IFile) {
                nbFileToImport.increment();
            }
        }
    }
}
