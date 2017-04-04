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
package org.bonitasoft.studio.la.ui.validator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.studio.common.jface.databinding.validator.UniqueValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.validator.ValidatorBuilder;
import org.eclipse.core.runtime.IStatus;

public class ApplicationTokenUnicityValidator extends UniqueValidator {

    public static class Builder implements ValidatorBuilder<UniqueValidator> {

        private final RepositoryAccessor repositoryAccessor;

        public Builder(RepositoryAccessor repositoryAccessor) {
            this.repositoryAccessor = repositoryAccessor;
        }

        @Override
        public ApplicationTokenUnicityValidator create() {
            return new ApplicationTokenUnicityValidator(repositoryAccessor);
        }

    }

    private final RepositoryAccessor repositoryAccessor;
    private Optional<String> currentToken;

    public ApplicationTokenUnicityValidator(RepositoryAccessor repositoryAccessor) {
        this(repositoryAccessor, null);
    }

    public ApplicationTokenUnicityValidator(RepositoryAccessor repositoryAccessor, String currentToken) {
        this.repositoryAccessor = repositoryAccessor;
        this.currentToken = Optional.ofNullable(currentToken);
    }

    public List<String> getTokenList() {
        final List<String> allTokens = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren()
                .stream()
                .map(fStore -> {
                    try {
                        return fStore.getContent();
                    } catch (final ReadFileStoreException e) {
                        BonitaStudioLog.error(String.format("Failed to parse application descriptor file '%s'",
                                fStore.getName()), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .flatMap(container -> container.getApplications().stream())
                .map(ApplicationNode::getToken)
                .collect(Collectors.toList());
        currentToken.ifPresent(allTokens::remove);
        return allTokens;
    }

    public void setCurrentToken(String currentToken) {
        this.currentToken = Optional.ofNullable(currentToken);
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
