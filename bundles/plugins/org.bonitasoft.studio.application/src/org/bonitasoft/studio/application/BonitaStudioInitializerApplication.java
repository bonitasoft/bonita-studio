/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * <<<<<<< HEAD
 *
 * =======
 *
 * >>>>>>> origin/master
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * <<<<<<< HEAD
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * =======
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * >>>>>>> origin/master
 */
package org.bonitasoft.studio.application;

import org.bonitasoft.studio.application.advisor.InitWorkspaceAdvisor;
import org.eclipse.equinox.app.IApplication;

/**
 * @author Romain Bioteau
 */
public class BonitaStudioInitializerApplication extends BonitaStudioApplication implements IApplication {

    @Override
    protected BonitaStudioWorkbenchAdvisor createWorkbenchAdvisor() {
        return new InitWorkspaceAdvisor();
    }

}
