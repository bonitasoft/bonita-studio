package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Version;

public class ImportWorkspaceModel {

    private static final Version VERSION_7_8_0 = new Version("7.8.0");
    private static final String VALID_SYMBOL = "\u2713 ";
    private static final String CROSS_SYMBOL = "\u2718 ";
    private static final String WARN_SYMBOL = "\u26A0 ";
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

    public String buildReport(boolean onlyDefault) {
        if (status.isOK()) {
            final StringBuilder sb = new StringBuilder();
            repositories.stream()
                    .filter(repo -> onlyDefault ? "default".equals(repo.getName()) : true)
                    .map(ImportRepositoryModel::getStatus)
                    .forEach(repoStatus -> {
                        if (repoStatus.isMultiStatus()) {
                            Stream.of(repoStatus.getChildren())
                                    .forEach(cStatus -> appendMessage(sb, cStatus));
                        } else {
                            appendMessage(sb, repoStatus);
                        }
                    });

            if (repositories.stream().anyMatch(repo -> VERSION_7_8_0.compareTo(asVersion(repo)) > 0)) {
                appendMessage(sb, ValidationStatus.warning(Messages.legacyFormsNotImportedFromWorkspace));
            }
            return sb.toString();
        }
        return CROSS_SYMBOL + status.getMessage();
    }

    private Version asVersion(ImportRepositoryModel repo) {
        try {
            return new Version(repo.getVersion());
        } catch (IllegalArgumentException e) {
            return new Version("6.0.0");
        }
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
            default:
                return VALID_SYMBOL;
        }
    }

}
