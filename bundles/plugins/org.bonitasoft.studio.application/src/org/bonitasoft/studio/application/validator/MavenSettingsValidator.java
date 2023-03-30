/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.validator;

import java.util.Collection;

import org.apache.maven.settings.Mirror;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.Settings;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.Strings;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class MavenSettingsValidator implements IValidator<Settings> {

    @Override
    public IStatus validate(Settings settings) {
        MultiStatus status = new MultiStatus(ApplicationPlugin.PLUGIN_ID, 0, "");

        settings.getProfiles().stream()
                .map(Profile::getRepositories)
                .flatMap(Collection::stream)
                .forEach(repository -> validateRepository(repository, status));

        settings.getProxies().forEach(proxy -> validateProxy(proxy, status));

        settings.getMirrors().forEach(mirror -> validateMirror(mirror, status));

        return status;
    }

    protected void validateMirror(Mirror mirror, MultiStatus status) {
        if (!Strings.hasText(mirror.getId())) {
            status.add(ValidationStatus.error(Messages.mirrorIdMandatory));
        } else if (!Strings.hasText(mirror.getUrl())) {
            status.add(ValidationStatus.error(String.format(Messages.mirrorUrlMandatory, mirror.getId())));
        }
    }

    protected void validateProxy(Proxy proxy, MultiStatus status) {
        if (!Strings.hasText(proxy.getId())) {
            status.add(ValidationStatus.error(Messages.proxyIdMandatory));
        } else {
            if (!Strings.hasText(proxy.getHost())) {
                status.add(ValidationStatus.error(String.format(Messages.proxyHostMandatory, proxy.getId())));
            }
        }
    }

    protected void validateRepository(Repository repository, MultiStatus status) {
        if (!Strings.hasText(repository.getId())) {
            status.add(ValidationStatus.error(Messages.repositoryIdMandatory));
        } else if (!Strings.hasText(repository.getUrl())) {
            status.add(ValidationStatus.error(String.format(Messages.repositoryUrlMandatory, repository.getId())));
        }
    }

}
