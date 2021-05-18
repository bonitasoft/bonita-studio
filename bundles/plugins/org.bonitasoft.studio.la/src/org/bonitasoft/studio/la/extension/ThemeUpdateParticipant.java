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
package org.bonitasoft.studio.la.extension;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.application.operation.extension.participant.preview.PreviewResultImpl;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipant;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewMessageProvider;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewResult;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;

public class ThemeUpdateParticipant implements ExtensionUpdateParticipant {

    private List<DependencyUpdate> dependenciesUpdates;
    private ThemeArtifactProvider themeArtifactProvider;
    private List<Theme> currentThemes;
    private ApplicationCollector applicationCollector;
    private PreviewResultImpl previewResult;

    public ThemeUpdateParticipant(List<DependencyUpdate> dependenciesUpdates, ThemeArtifactProvider themeArtifactProvider,
            ApplicationCollector applicationCollector) {
        this.dependenciesUpdates = dependenciesUpdates;
        this.themeArtifactProvider = themeArtifactProvider;
        this.applicationCollector = applicationCollector;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.updatingApplications, IProgressMonitor.UNKNOWN);
        previewResult.getChanges().stream()
                .filter(ApplicationChange.class::isInstance)
                .map(ApplicationChange.class::cast)
                .forEach(ApplicationChange::apply);
    }

    @Override
    public PreviewResult runPreview(IProgressMonitor monitor) {
        monitor.beginTask(Messages.themeUpdatePreview, IProgressMonitor.UNKNOWN);

        previewResult = new PreviewResultImpl();

        List<String> newThemeIds = dependenciesUpdates.stream()
                .map(DependencyUpdate::getUpdatedDependency)
                .filter(Objects::nonNull)
                .map(themeArtifactProvider::findTheme)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Theme::getName)
                .collect(Collectors.toList());

        for (var currentTheme : currentThemes) {
            if (!newThemeIds.contains(currentTheme.getName())) {
                var appsToRefactor = applicationCollector.findApplications(currentTheme.getName());
                if (!appsToRefactor.isEmpty()) {
                    previewResult.addChange(new ThemeRemovedChange(currentTheme, appsToRefactor));
                }
            }
        }

        return previewResult;
    }

    @Override
    public PreviewResult getPreviewResult() {
        return previewResult;
    }

    @Override
    public void preUpdate(IProgressMonitor monitor) {
        monitor.beginTask(Messages.preparingThemeUpdate, IProgressMonitor.UNKNOWN);

        currentThemes = dependenciesUpdates.stream()
                .map(DependencyUpdate::getCurrentDependency)
                .map(themeArtifactProvider::findTheme)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Resource> getModifiedResources() {
        return Collections.emptyList();
    }

    @Override
    public PreviewMessageProvider getPreviewMessageProvider() {
        return new ApplicationPreviewMessageProvider();
    }

}
