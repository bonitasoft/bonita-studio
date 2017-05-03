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

package org.bonitasoft.studio.la.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.fakes.IResourceFakesBuilder.anIFile;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;

import org.bonitasoft.engine.profile.xml.ProfileNode;
import org.bonitasoft.engine.profile.xml.ProfilesNode;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.junit.rules.FileResource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.junit.Rule;
import org.junit.Test;

public class ApplicationProfileFileStoreTest {

    @Rule
    public FileResource profileResource = new FileResource("/myProfile.xml");

    @Test
    public void should_retrieve_model_from_profile_xml_file() throws Exception {
        final ApplicationProfileFileStore applicationProfileFileStore = spy(
                new ApplicationProfileFileStore("myProfile.xml", mock(IRepositoryStore.class)));
        doReturn(anIFile().withName("myProfile.xml")
                .withContent(profileResource.getInputStream()).build())
                        .when(applicationProfileFileStore)
                        .getResource();

        final ProfilesNode profilesNode = applicationProfileFileStore.getContent();
        assertThat(profilesNode.getProfiles())
                .extracting("name", "description")
                .containsExactly(tuple("myProfile", "test profile"));
    }

    @Test
    public void should_create_a_new_profile_xml_file_from_model() throws Exception {
        final ApplicationProfileFileStore applicationProfileFileStore = spy(
                new ApplicationProfileFileStore("myProfile.xml", mock(IRepositoryStore.class)));
        final IFile resource = anIFile().withName("myProfile.xml").build();
        doReturn(resource)
                .when(applicationProfileFileStore)
                .getResource();

        final ProfilesNode profilesNode = new ProfilesNode();
        profilesNode.getProfiles().add(new ProfileNode("aName", false));
        applicationProfileFileStore.save(profilesNode);

        verify(resource).create(notNull(ByteArrayInputStream.class), eq(IResource.FORCE),
                eq(Repository.NULL_PROGRESS_MONITOR));
    }

    @Test
    public void should_update_profile_xml_file_from_model() throws Exception {
        final ApplicationProfileFileStore applicationProfileFileStore = spy(
                new ApplicationProfileFileStore("myProfile.xml", mock(IRepositoryStore.class)));
        final IFile resource = anIFile().withName("myProfile.xml").exists().build();
        doReturn(resource)
                .when(applicationProfileFileStore)
                .getResource();

        final ProfilesNode profilesNode = new ProfilesNode();
        profilesNode.getProfiles().add(new ProfileNode("aName", false));
        applicationProfileFileStore.save(profilesNode);

        verify(resource).setContents(notNull(ByteArrayInputStream.class), eq(IResource.FORCE | IResource.KEEP_HISTORY),
                eq(Repository.NULL_PROGRESS_MONITOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_saving_unsupported_content() throws Exception {
        final ApplicationProfileFileStore applicationProfileFileStore = new ApplicationProfileFileStore("myProfile.xml",
                null);
        applicationProfileFileStore.doSave(null);
    }

}
