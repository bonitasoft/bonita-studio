/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.designer.core.resources.APIStatus;
import org.bonitasoft.studio.designer.core.resources.WorkspaceServerResource;
import org.junit.Before;
import org.junit.Test;
import org.restlet.resource.Finder;
import org.restlet.routing.Router;
import org.restlet.routing.TemplateRoute;

/**
 * @author Romain Bioteau
 */
public class WorkspaceApplicationTest {

    private WorkspaceApplication workspaceApplication;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        workspaceApplication = new WorkspaceApplication();
    }

    @Test
    public void should_attach_inbound_route_to_application() throws Exception {
        final Router inboundRoot = (Router) workspaceApplication.createInboundRoot();

        assertThat(inboundRoot).isNotNull();
        assertThat(inboundRoot.getRoutes()).hasSize(3);
        final TemplateRoute route1 = (TemplateRoute) inboundRoot.getRoutes().get(0);
        assertThat(route1.getTemplate().getPattern()).isEqualTo("/workspace/{filePath}/{action}");
        assertThat(((Finder) route1.getNext()).getTargetClass()).isEqualTo(WorkspaceServerResource.class);
        final TemplateRoute route2 = (TemplateRoute) inboundRoot.getRoutes().get(1);
        assertThat(route2.getTemplate().getPattern()).isEqualTo("/workspace/{action}");
        assertThat(((Finder) route2.getNext()).getTargetClass()).isEqualTo(WorkspaceServerResource.class);
        final TemplateRoute route3 = (TemplateRoute) inboundRoot.getRoutes().get(2);
        assertThat(route3.getTemplate().getPattern()).isEqualTo("/workspace/status/");
        assertThat(((Finder) route3.getNext()).getTargetClass()).isEqualTo(APIStatus.class);
    }

}
