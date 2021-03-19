package org.bonitasoft.studio.application.handler;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.SetProjectMetadataOperation;
import org.bonitasoft.studio.application.ui.control.ProjectMetadataPage;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;

public class NewProjectHandler extends AbstractProjectMetadataHandler {

    protected String getWizardDescription() {
        return Messages.newProjectWizardDescription;
    }

    protected String getWizardTitle() {
        return Messages.newProjectWizardTitle;
    }

    protected SetProjectMetadataOperation createOperation(MavenProjectHelper mavenProjectHelper,
            ProjectMetadataPage page, RepositoryAccessor repositoryAccessor) {
        return new SetProjectMetadataOperation(page.getMetadata(), repositoryAccessor, mavenProjectHelper)
                .createNewProject();
    }

    @Override
    protected boolean isNewProject() {
        return true;
    }
    
    @Override
    protected ProjectMetadata initialMetadata(AbstractRepository currentRepository) {
        ProjectMetadata metadata = ProjectMetadata.defaultMetadata();
        metadata.setName("");
        metadata.setArtifactId("");
        return metadata;
    }
    
    @Override
    public String getFinishLabel() {
        return Messages.create;
    }


}
