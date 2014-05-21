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
package org.bonitasoft.studio.configuration.ui.wizard;

import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class WizardPageLabelProvider extends LabelProvider {

    private final IWizardContainer container;


    public WizardPageLabelProvider(IWizardContainer container){
        this.container = container ;
    }

    @Override
    public String getText(Object element) {
        if(container.getCurrentPage() != null){
            IWizardPage page = (IWizardPage) element ;
            return page.getTitle();
        }
        return null ;
    }



    @Override
    public Image getImage(Object element) {
        if(container.getCurrentPage() != null){
            IProcessConfigurationWizardPage page = (IProcessConfigurationWizardPage) element ;
            return page.getConfigurationImage();
        }
        return null;
    }

}
