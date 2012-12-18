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
package org.bonitasoft.studio.groovy.ui;

import org.bonitasoft.studio.common.repository.extension.IPostInitRepositoryJobContribution;


/**
 * @author Romain Bioteau
 *
 */
public class InitGroovyEditorPostStartup implements IPostInitRepositoryJobContribution {


    public InitGroovyEditorPostStartup() {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.IPostStartupContribution#execute()
     */
    @Override
    public void execute() {
        //        //Initialize groovy classes
        //        if(PlatformUtil.isHeadless()){
        //            return ;
        //        }
        //        UIJob job =  new UIJob("Init groovy") {
        //
        //            @Override
        //            public IStatus runInUIThread(IProgressMonitor arg0) {
        //                try{
        //                    GroovyPlugin plugin = GroovyPlugin.getDefault();
        //                    BonitaGroovyEditor editor = new BonitaGroovyEditor(plugin.getPreferenceStore());
        //                    editor.dispose();
        //                }catch (NullPointerException e) {
        //
        //                }
        //                return Status.OK_STATUS;
        //            }
        //        };
        //        job.schedule();
    }

}
