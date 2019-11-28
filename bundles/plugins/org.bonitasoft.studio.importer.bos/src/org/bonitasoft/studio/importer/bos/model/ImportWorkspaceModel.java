package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class ImportWorkspaceModel {

    private static final String VALID_SYMBOL = "\u2713 ";
    private static final String CROSS_SYMBOL = "\u2718 ";
    private static final String WARN_SYMBOL = "\u26A0 ";
    private static final String INFO_SYMBOL = "\u26A0";
    private final List<ImportRepositoryModel> repositories = new ArrayList<>();
    private final String worksapceFolder;
    private IStatus status = Status.OK_STATUS;

    public ImportWorkspaceModel(String worksapceFolder) {
        this.worksapceFolder = worksapceFolder;
    }

    public String getWorksapceFolder() {
        return worksapceFolder;
    }

    public ImportRepositoryModel addRepository(ImportRepositoryModel repositoryModel) {
        repositories.add(repositoryModel);
        return repositoryModel;
    }

    public Stream<ImportRepositoryModel> getRepositories() {
        return repositories.stream();
    }

    public boolean isEmpty() {
        return repositories.isEmpty();
    }

    public void setStatus(IStatus status) {
        this.status = status;
    }

    public IStatus getStatus() {
        return status;
    }

    public String buildReport() {
        if (status.isOK()) {
            final StringBuilder sb = new StringBuilder();
            repositories.stream()
                    .map(ImportRepositoryModel::getStatus)
                    .forEach(repoStatus -> {
                        if (repoStatus.isMultiStatus()) {
                            Stream.of(repoStatus.getChildren())
                                    .forEach(cStatus -> appendMessage(sb, cStatus));
                        } else {
                            appendMessage(sb, repoStatus);
                        }
                    });

            if (repositories.stream().anyMatch(repo -> ProductVersion.isBefore780Version(repo.getVersion()))) {
                appendMessage(sb, ValidationStatus.info(Messages.legacyFormsNotImportedFromWorkspace));
            }
            return sb.toString();
        }
        return CROSS_SYMBOL + status.getMessage();
    }

    private void appendMessage(final StringBuilder sb, IStatus repoStatus) {
        sb.append(messageStatus(repoStatus));
        sb.append(repoStatus.getMessage());
        sb.append(System.lineSeparator());
    }

    private String messageStatus(IStatus repoStatus) {
        switch (repoStatus.getSeverity()) {
            case IStatus.ERROR:
                return CROSS_SYMBOL;
            case IStatus.WARNING:
                return WARN_SYMBOL;
            case IStatus.INFO:
                return INFO_SYMBOL;
            default:
                return VALID_SYMBOL;
        }
    }

}
