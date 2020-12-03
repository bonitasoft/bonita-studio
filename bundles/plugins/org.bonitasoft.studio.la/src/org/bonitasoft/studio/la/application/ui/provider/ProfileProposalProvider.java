/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.provider;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.profile.xml.ProfileNode;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

public class ProfileProposalProvider implements IContentProposalProvider {

    protected RepositoryAccessor repositoryAccessor;

    // The proposals provided.
    protected Collection<ProfileNode> proposals;

    /*
     * The proposals mapped to IContentProposal. Cached for speed in the case
     * where filtering is not used.
     */
    protected Optional<IContentProposal[]> contentProposals = Optional.empty();

    protected boolean filterProposals = false;

    public ProfileProposalProvider(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        setProposals(getProfiles());
    }

    protected Collection<ProfileNode> getProfiles() {
        return Arrays.asList(new ProfileNode(ApplicationFormPage.DEFAULT_USER_ID, true),
                new ProfileNode(ApplicationFormPage.DEFAULT_ADMINISTRATOR_ID, true));
    }

    @Override
    public IContentProposal[] getProposals(String contents, int position) {
        if (filterProposals) {
            List<String> profilesSelected = proposals.stream()
                    .filter(profile -> profile.getName().length() >= contents.length())
                    .filter(profile -> profile.getName().substring(0, contents.length())
                            .equalsIgnoreCase(contents))
                    .map(ProfileNode::getName)
                    .collect(Collectors.toList());

            return profilesSelected.stream()
                    .distinct()
                    .map(profileName -> new ContentProposal(profileName,
                            profilesSelected.stream().filter(profileName::equals).count() > 1
                                    ? String.format("%s\n%s", profileName,
                                            String.format(Messages.profileWithSeveralDefinition, profileName, profileName))
                                    : profileName))
                    .toArray(IContentProposal[]::new);
        }
        if (!contentProposals.isPresent()) {
            contentProposals = Optional.of(proposals.stream()
                    .filter(profile -> profile.getName() != null)
                    .map(profile -> new ContentProposal(profile.getName(), profile.getName(), null))
                    .toArray(IContentProposal[]::new));
        }
        return contentProposals.get();
    }

    public void setProposals(Collection<ProfileNode> items) {
        this.proposals = items;
        contentProposals = Optional.empty();
    }

    public void setFiltering(Boolean filterProposals) {
        this.filterProposals = filterProposals;
        contentProposals = Optional.empty();
    }

}
