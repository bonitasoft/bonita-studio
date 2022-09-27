package org.bonitasoft.studio.theme;

import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.eclipse.core.resources.IProject;

public class ThemeFileStore extends CustomPageProjectFileStore<ThemeExtensionDescriptor> {

    public ThemeFileStore(String fileName, ThemeRepositoryStore parentStore) {
        super(fileName, parentStore);
    }

    @Override
    protected ThemeExtensionDescriptor doGetContent() throws ReadFileStoreException {
        final IProject project = getProject();
        if (!project.exists()) {
            throw new ReadFileStoreException(String.format("Project with name %s does not exist", project.getName()));
        }
        return new ThemeExtensionDescriptor(project);
    }

    @Override
    protected String getBuildFolder() {
        return "theme";
    }

}
