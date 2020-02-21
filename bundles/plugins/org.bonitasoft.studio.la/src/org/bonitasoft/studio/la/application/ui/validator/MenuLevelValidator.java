/**
 * Copyright (C) 2017 Bonitasoft S.A.
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationMenuNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.validation.AbstractValidator;
import org.eclipse.wst.validation.ValidationResult;
import org.eclipse.wst.validation.ValidationState;
import org.eclipse.wst.validation.ValidatorMessage;
import org.xml.sax.SAXException;

import com.google.common.io.ByteStreams;

public class MenuLevelValidator extends AbstractValidator {

    private ApplicationNodeContainerConverter converter;

    @Override
    public ValidationResult validate(IResource resource, int kind, ValidationState state, IProgressMonitor monitor) {
        if (!shouldValidate()) {
            return new ValidationResult();
        }
        if(converter == null) {
            converter = getConverter();
            if(converter == null) {
                return null;
            }
        }
        if (resource.getType() != IResource.FILE) {
            return null;
        }
        final ValidationResult validationResult = new ValidationResult();

        final Optional<ApplicationNodeContainer> appContainer = Optional
                .ofNullable(toApplicationContainer((IFile) resource));

        appContainer.map(ApplicationNodeContainer::getApplications)
                .map(Collection::stream).orElse(Stream.empty())
                .map(ApplicationNode::getApplicationMenus)
                .flatMap(Collection::stream)
                .map(ApplicationMenuNode::getApplicationMenus)
                .flatMap(Collection::stream)
                .filter(menuNode -> !menuNode.getApplicationMenus().isEmpty())
                .map(ApplicationMenuNode::getApplicationMenus)
                .flatMap(Collection::stream)
                .map(menuNode -> createMessage(resource))
                .forEach(validationResult::add);

        return validationResult;
    }

    protected ApplicationNodeContainerConverter getConverter() {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        if(repositoryManager.hasActiveRepository() && 
                repositoryManager.getCurrentRepository().isLoaded()) {
            return repositoryManager
                    .getRepositoryStore(ApplicationRepositoryStore.class).getConverter();
        }
        return null;
    }

    protected boolean shouldValidate() {
        return RepositoryManager.getInstance().hasActiveRepository();
    }

    protected ValidatorMessage createMessage(IResource resource) {
        final ValidatorMessage warningMessage = ValidatorMessage
                .create(Messages.menuLevelWarning, resource);
        warningMessage.setAttribute(IMarker.LOCATION, resource.getName());
        warningMessage.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
        warningMessage.setAttribute(IMarker.CHAR_START, 0);
        warningMessage.setAttribute(IMarker.CHAR_END, 0);
        return warningMessage;
    }

    protected ApplicationNodeContainer toApplicationContainer(IFile resource) {
        try (InputStream is = resource.getContents()) {
            return converter.unmarshallFromXML(ByteStreams.toByteArray(is));
        } catch (IOException | CoreException | JAXBException | SAXException e) {
            return null;
        }
    }

}
