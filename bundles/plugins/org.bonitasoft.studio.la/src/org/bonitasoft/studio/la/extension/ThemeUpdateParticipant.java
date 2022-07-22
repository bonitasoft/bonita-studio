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

import org.bonitasoft.plugin.analyze.report.model.Artifact;
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

    public ThemeUpdateParticipant(List<DependencyUpdate> dependenciesUpdates,
            ThemeArtifactProvider themeArtifactProvider,
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

        List<Theme> newThemes = dependenciesUpdates.stream()
                .map(DependencyUpdate::getUpdatedDependency)
                .filter(Objects::nonNull)
                .map(themeArtifactProvider::findTheme)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        for (var currentTheme : currentThemes) {
            if (hasBeenRemoved(currentTheme, newThemes)) {
                var appsToRefactor = applicationCollector.findApplications(currentTheme.getName());
                if (!appsToRefactor.isEmpty()) {
                    previewResult.addChange(new ThemeRemovedChange(currentTheme, appsToRefactor));
                }
            } else {
                newThemes.stream()
                        .filter(t -> existsInSameVersion(t.getArtifact(), currentTheme.getArtifact()) || existsInAnotherVersion(t.getArtifact(), currentTheme.getArtifact()))
                        .findFirst()
                        .filter(t -> !Objects.equals(t.getName(), currentTheme.getName()))
                        .ifPresent(theme -> {
                            var appsToRefactor = applicationCollector.findApplications(currentTheme.getName());
                            if (!appsToRefactor.isEmpty()) {
                                previewResult.addChange(new ThemeUpdateChange(currentTheme, theme, appsToRefactor));
                            }
                        });
            }
        }
        return previewResult;
    }

    private boolean hasBeenRemoved(Theme currentTheme, List<Theme> newThemes) {
        return newThemes.stream().map(Theme::getArtifact)
                .noneMatch(a -> existsInSameVersion(a, currentTheme.getArtifact())
                        || existsInAnotherVersion(a, currentTheme.getArtifact()));
    }

    private boolean existsInAnotherVersion(Artifact updatedArtifact, Artifact currentArtifact) {
        return sameGAC(updatedArtifact, currentArtifact)
                && !currentArtifact.getVersion().equals(updatedArtifact.getVersion());
    }

    private boolean existsInSameVersion(Artifact updatedArtifact, Artifact currentArtifact) {
        return sameGAC(updatedArtifact, currentArtifact)
                && currentArtifact.getVersion().equals(updatedArtifact.getVersion());
    }

    private boolean sameGAC(Artifact updatedArtifact, Artifact currentArtifact) {
        return currentArtifact.getGroupId().equals(updatedArtifact.getGroupId())
                && currentArtifact.getArtifactId().equals(updatedArtifact.getArtifactId())
                && Objects.equals(currentArtifact.getClassifier(), updatedArtifact.getClassifier());
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
