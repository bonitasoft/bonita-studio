/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.model.configuration.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;

/**
 * @author aurelie
 */
public class FragmentContainerBuilder implements Buildable<FragmentContainer> {

    private final FragmentContainer fragmentContainer;

    private FragmentContainerBuilder(final FragmentContainer fragmentContainer) {
        this.fragmentContainer = fragmentContainer;
    }

    public static FragmentContainerBuilder aFragmentContainer(final String id) {
        final FragmentContainer container = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        container.setId(id);
        return new FragmentContainerBuilder(container);
    }

    @SafeVarargs
    public final FragmentContainerBuilder havingChildren(final Buildable<? extends FragmentContainer>... containers) {
        for (final Buildable<? extends FragmentContainer> container : containers) {
            fragmentContainer.getChildren().add(container.build());
        }
        return this;
    }

    @SafeVarargs
    public final FragmentContainerBuilder havingFragments(final Buildable<? extends Fragment>... fragments) {
        for (final Buildable<? extends Fragment> fragment : fragments) {
            fragmentContainer.getFragments().add(fragment.build());
        }
        return this;
    }

    @Override
    public FragmentContainer build() {
        return fragmentContainer;
    }

}
