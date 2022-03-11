/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.configuration.extension;

import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public interface IProcessConfigurationWizardPage extends IWizardPage {

    void updatePage(AbstractProcess process,Configuration configuration) ;

    String isConfigurationPageValid(Configuration conf) ;

    Image getConfigurationImage();

    /**
     * 
     * @return true if this page should be displayed in simple configuration mode
     */
    boolean isDefault();

}
