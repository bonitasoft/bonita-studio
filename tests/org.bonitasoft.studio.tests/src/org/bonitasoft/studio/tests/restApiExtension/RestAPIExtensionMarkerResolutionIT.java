/*******************************************************************************
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.restApiExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.rest.api.extension.core.validation.RestAPIDependencyVersionToUpdateFinder;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.ide.IDE;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RestAPIExtensionMarkerResolutionIT {

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void init() throws Exception {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        cleanRestApiRepositoryStore();
    }

    @After
    public void clean() {
        cleanRestApiRepositoryStore();
    }

    private void cleanRestApiRepositoryStore() {
        repositoryAccessor.getRepositoryStore(RestAPIExtensionRepositoryStore.class).getChildren()
                .forEach(IRepositoryFileStore::delete);
    }

    @Test
    public void should_import_rest_api_with_dependency_to_update() throws Exception {
        File bosArchiveFile = new File(
                FileLocator.toFileURL(RestAPIExtensionMarkerResolutionIT.class.getResource("/RestAPIExtension.bos")).getFile());
        ImportBosArchiveOperation importBosArchiveOperation = new ImportBosArchiveOperation(repositoryAccessor);
        importBosArchiveOperation.setArchiveFile(bosArchiveFile.getAbsolutePath());
        importBosArchiveOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        importBosArchiveOperation.openFilesToOpen();

        RestAPIExtensionRepositoryStore restAPIExtensionRepositoryStore = repositoryAccessor
                .getRepositoryStore(RestAPIExtensionRepositoryStore.class);
        assertThat(restAPIExtensionRepositoryStore.getChildren()).hasSize(1);

        RestAPIExtensionFileStore restAPIExtensionFileStore = restAPIExtensionRepositoryStore.getChildren().get(0);

        checkProjectMarkers(restAPIExtensionFileStore.getProject());
    }

    private AssertionCondition doesNotcontainStatusMessage(IResource resource, String message) {
        return new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                IMarker[] markers = resource.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ONE);
                assertThat(Stream.of(markers).map(m -> {
                    try {
                        return m.getAttribute(IMarker.MESSAGE);
                    } catch (CoreException e) {
                        return null;
                    }
                })).doesNotContain(message);
            }
        };
    }

    private IMarker findMarkerWithMessage(IResource resource, String message) throws CoreException {
        IMarker[] markers = resource.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ONE);
        IMarker marker = Stream.of(markers).filter(m -> {
            try {
                return Objects.equals(message, m.getAttribute(IMarker.MESSAGE));
            } catch (CoreException e) {
                return false;
            }
        }).findFirst().orElse(null);
        assertThat(marker).isNotNull();
        assertThat(IDE.getMarkerHelpRegistry().hasResolutions(marker)).isTrue();
        return marker;
    }

    private void checkProjectMarkers(IResource project) throws CoreException {
        checkMarker(RestAPIDependencyVersionToUpdateFinder.GROOVY_GROUP_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ALL_ARTIFACT_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ALL_MIN_VERSION, project);
        checkMarker(RestAPIDependencyVersionToUpdateFinder.GROOVY_GROUP_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_BATCH_ARTIFACT_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_BATCH_MIN_VERSION, project);
        checkMarker(RestAPIDependencyVersionToUpdateFinder.GROOVY_GROUP_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_COMPILER_ARTIFACT_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_COMPILER_MIN_VERSION, project);
    }

    private void checkMarker(String groupId, String artifactId,
            String minVersion, IResource project) throws CoreException {
        String artifactName = String.format("%s:%s", groupId, artifactId);
        String rawMessage = String.format(Messages.updateVersionForJava11Compliance, artifactName, minVersion);
        IMarker marker = findMarkerWithMessage(project, rawMessage);
        //Apply quickfix
        IDE.getMarkerHelpRegistry().getResolutions(marker)[0].run(marker);
        //Check marker is no more present
        doesNotcontainStatusMessage(project, rawMessage);
    }

}
