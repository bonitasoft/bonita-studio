/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.helper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.ui.util.StringIncrementer;

public class PackageHelper {

    private PackageHelper() {

    }

    public static String getPackageName(BusinessObject bo) {
        return NamingUtils.getPackageName(bo.getQualifiedName());
    }

    public static List<String> getAllPackages(BusinessObjectModel model) {
        return model.getBusinessObjects().stream()
                .map(PackageHelper::getPackageName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static boolean packageAlreadyExists(BusinessObjectModel model, String packageName) {
        return getAllPackages(model).contains(packageName);
    }

    public static String getNextPackageName(BusinessObjectModel model) {
        List<String> allPackages = getAllPackages(model);
        return StringIncrementer.getNextIncrement(defaultPackageName(), allPackages);
    }

    public static List<BusinessObject> getAllBusinessObjects(BusinessObjectModel model, String packageName) {
        return model.getBusinessObjects().stream()
                .filter(bo -> Objects.equals(getPackageName(bo), packageName))
                .collect(Collectors.toList());
    }

    public static String defaultPackageName() {
        if (CommonRepositoryPlugin.getDefault() == null) {
            return RepositoryPreferenceConstant.DEFAULT_GROUPID_VALUE + ".model";
        }
        return CommonRepositoryPlugin.getDefault().getPreferenceStore()
                .getString(RepositoryPreferenceConstant.DEFAULT_GROUPID) + ".model";
    }

}
