package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class ImportWorkspaceModel {

    private static final String VALID_SYMBOL = "\u2713 ";
    private static final String CROSS_SYMBOL = "\u2718 ";
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
            return sb.toString();
        }
        return CROSS_SYMBOL + status.getMessage();
    }

    private void appendMessage(final StringBuilder sb, IStatus repoStatus) {
        sb.append(repoStatus.isOK() ? VALID_SYMBOL : CROSS_SYMBOL);
        sb.append(repoStatus.getMessage());
        sb.append(System.lineSeparator());
    }

}
