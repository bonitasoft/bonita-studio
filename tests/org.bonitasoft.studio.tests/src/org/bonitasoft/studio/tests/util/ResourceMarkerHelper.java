/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ResourceMarkerHelper {

    public static List<String> findErrors(IResource resource) {
        try {
            IMarker[] markers = resource.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
            if (markers != null) {
                return Stream.of(markers)
                        .filter(m -> {
                            try {
                                return Integer.valueOf(IMarker.SEVERITY_ERROR).equals(m.getAttribute(IMarker.SEVERITY));
                            } catch (CoreException e) {
                                BonitaStudioLog.error(e);
                                return false;
                            }
                        })
                        .map(m -> {
                            try {
                                return (String) m.getAttribute(IMarker.MESSAGE);
                            } catch (CoreException e) {
                                BonitaStudioLog.error(e);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return Collections.emptyList();
    }

}
