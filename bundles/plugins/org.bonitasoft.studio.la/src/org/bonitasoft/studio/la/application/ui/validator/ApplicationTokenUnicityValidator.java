/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.la.application.ui.validator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.jface.databinding.validator.UniqueValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.validator.ValidatorBuilder;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;

public class ApplicationTokenUnicityValidator extends UniqueValidator {

    public static class Builder implements ValidatorBuilder<UniqueValidator> {

        private final RepositoryAccessor repositoryAccessor;
        private ApplicationNodeContainer applicationWorkingCopy;
        private String filename;

        public Builder(RepositoryAccessor repositoryAccessor, ApplicationNodeContainer applicationWorkingCopy,
                String filename) {
            Objects.requireNonNull(repositoryAccessor);
            Objects.requireNonNull(applicationWorkingCopy);
            this.repositoryAccessor = repositoryAccessor;
            this.applicationWorkingCopy = applicationWorkingCopy;
            this.filename = filename;
        }

        @Override
        public ApplicationTokenUnicityValidator create() {
            return new ApplicationTokenUnicityValidator(repositoryAccessor, applicationWorkingCopy, filename);
        }

    }

    private final RepositoryAccessor repositoryAccessor;
    private Optional<IObservableValue<String>> currentTokenObservable;
    private ApplicationNodeContainer applicationWorkingCopy;
    private String filename;

    public ApplicationTokenUnicityValidator(RepositoryAccessor repositoryAccessor,
            ApplicationNodeContainer applicationWorkingCopy, String fileName) {
        this(repositoryAccessor, applicationWorkingCopy, fileName, null);
    }

    public ApplicationTokenUnicityValidator(RepositoryAccessor repositoryAccessor,
            ApplicationNodeContainer applicationWorkingCopy, String filename,
            IObservableValue<String> currentTokenObservable) {
        this.repositoryAccessor = repositoryAccessor;
        this.applicationWorkingCopy = applicationWorkingCopy;
        this.currentTokenObservable = Optional.ofNullable(currentTokenObservable);
        this.filename = filename;
    }

    public List<String> getTokenList() {
        final List<String> allTokens = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren()
                .stream()
                .filter(fStore -> !Objects.equals(fStore.getName(), filename))
                .map(fStore -> {
                    try {
                        return fStore.getContent();
                    } catch (final ReadFileStoreException e) {
                        BonitaStudioLog.debug(String.format("Failed to parse application descriptor file '%s'",
                                fStore.getName()), e, LivingApplicationPlugin.PLUGIN_ID);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .flatMap(container -> container.getApplications().stream())
                .map(ApplicationNode::getToken)
                .collect(Collectors.toList());
        applicationWorkingCopy.getApplications().stream()
                .map(ApplicationNode::getToken)
                .forEach(allTokens::add);

        applicationWorkingCopy.getApplications().stream()
                .filter(application -> Objects.equals(currentTokenObservable.orElse(new WritableValue<>()).getValue(),
                        application.getToken()))
                .map(ApplicationNode::getToken)
                .findFirst().ifPresent(allTokens::remove);
        return allTokens;
    }

    /**
     * @see org.bonitasoft.studio.common.jface.databinding.validator.UniqueValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(Object value) {
        setIterable(getTokenList());
        return super.validate(value);
    }

}
