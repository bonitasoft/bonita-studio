package org.bonitasoft.studio.common.repository;

import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ProjectIdValidator implements IValidator<String> {

    private static final String[] reservedChars = new String[] { "/", "\\", ":", "*", "?", "\"", "<", ">", ";", "|" };
    private BooleanSupplier newProjectSupplier;
    private Supplier<String> nameSuppiler;
    private Supplier<String> idSupplier;

    public ProjectIdValidator(Supplier<String> nameSuppiler, Supplier<String> idSupplier,
           BooleanSupplier newProjectSupplier) {
        this.nameSuppiler = nameSuppiler;
        this.idSupplier = idSupplier;
        this.newProjectSupplier = newProjectSupplier;
    }

    public String isValid(String newText) {
        if (newText == null || newText.trim().length() == 0) {
            newText = ProjectMetadata.toArtifactId(nameSuppiler.get());
        }
        final Optional<String> invalidChar = isValidFileName(newText);
        if (invalidChar.isPresent()) {
            return String.format(Messages.createNewProject_invalidCharacter, invalidChar.get());
        }

        var currentRepo = getRepositoryManager().getCurrentRepository().orElse(null);
        if (currentRepo != null) {
            if (newProjectSupplier.getAsBoolean() || !isCurrentName(newText)) {
                if (newText != null && !newText.isBlank() && getRepositoryManager().getRepository(newText) != null) {
                    return String.format(Messages.projectAlreadyExist, newText);
                }
                if (Stream.of(currentRepo
                        .getProject()
                        .getWorkspace()
                        .getRoot()
                        .getLocation()
                        .toFile()
                        .listFiles())
                        .map(File::getName)
                        .map(String::toLowerCase)
                        .anyMatch(newText.toLowerCase()::equals)) {
                    return String.format(Messages.projectAlreadyExist, newText);
                }
            }
        }
        return null;
    }

    private boolean isCurrentName(String newText) {
        return getRepositoryManager().getCurrentProject()
                .filter(project -> Objects.equals(newText, project.getId())).isPresent();
    }

    protected Optional<String> isValidFileName(final String newText) {
        for (final String reservedChar : reservedChars) {
            if (newText.contains(reservedChar)) {
                return Optional.of(reservedChar);
            }
        }
        return Optional.empty();
    }

    protected RepositoryManager getRepositoryManager() {
        return RepositoryManager.getInstance();
    }

    @Override
    public IStatus validate(String value) {
        String errorMessage = null;
        if(idSupplier == null) {
            errorMessage = isValid((String) value);
        }else  if (Strings.isNullOrEmpty(idSupplier.get()) && Strings.isNullOrEmpty(value)) {
            errorMessage = Messages.createNewProject_emptyText;
        } else if (Strings.isNullOrEmpty(idSupplier.get()) && !Strings.isNullOrEmpty(value)) {
            errorMessage =  isValid(ProjectMetadata.toArtifactId((String) value));
        }
        return errorMessage == null ? ValidationStatus.ok() : ValidationStatus.error(errorMessage);
    }

}
