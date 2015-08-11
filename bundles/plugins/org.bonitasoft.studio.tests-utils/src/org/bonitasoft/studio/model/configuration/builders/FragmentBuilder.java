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

/**
 * @author aurelie
 */
public class FragmentBuilder implements Buildable<Fragment> {

    private final Fragment fragment;

    private FragmentBuilder(final Fragment fragment) {
        this.fragment = fragment;
    }

    public static FragmentBuilder aFragment() {
        return new FragmentBuilder(ConfigurationFactory.eINSTANCE.createFragment());
    }

    public FragmentBuilder withKey(final String key) {
        fragment.setKey(key);
        return this;
    }

    public FragmentBuilder withValue(final String value) {
        fragment.setValue(value);
        return this;
    }

    public FragmentBuilder withType(final String type) {
        fragment.setType(type);
        return this;
    }

    public FragmentBuilder exported() {
        fragment.setExported(true);
        return this;
    }

    public FragmentBuilder notExported() {
        fragment.setExported(false);
        return this;
    }

    @Override
    public Fragment build() {
        return fragment;
    }

}
