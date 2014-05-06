/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
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
package org.bonitasoft.studio.registration;

import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;

public class RegistrationContribution implements IPostStartupContribution {

    public RegistrationContribution() {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.application.contribution.IPostStartupContribution#execute()
     */
    public void execute() {
    	final String productId = Platform.getProduct().getId();
		if(!"org.bonitasoft.studio.workspaceRecovery".equals(productId)
    			&& !"org.bonitasoft.studio.initializer".equals(productId)){
    		Display.getDefault().syncExec(new Runnable() {

    			public void run() {
    				new BonitaRegistrationDialog( Display.getDefault().getActiveShell(), new BonitaRegisterWizard()).open();
    			}
    		});
    	}
    }


}
