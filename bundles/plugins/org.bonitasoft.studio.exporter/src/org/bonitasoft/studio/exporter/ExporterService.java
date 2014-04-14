/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.exporter.application.HtmlTemplateGenerator;
import org.bonitasoft.studio.exporter.preview.PreviewForm;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;


/**
 * 
 * 
 * Contains extensible services of bonita studio for export related tasks
 * 
 * @author Baptiste Mesta
 *
 */
public class ExporterService {

    public enum SERVICE_TYPE {HtmlTemplateGenerator,FormPreview};

    private static final String CLASS = "class";//$NON-NLS-1$

    private static ExporterService instance;

    private Map<SERVICE_TYPE,Object> map ;

    public static ExporterService getInstance() {
        if (instance == null) {
            instance = new ExporterService();
        }
        return instance;
    }

    private ExporterService() {

    }

    public Object getExporterService(SERVICE_TYPE type) {
        // exporterExtension
        if (map == null) {
            map = new HashMap<SERVICE_TYPE,Object>();

            IConfigurationElement[] cfgElts = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.exporter.exporterExtension");
            for (IConfigurationElement cfgElt : cfgElts) {
                try {
                    String name = cfgElt.getName();
                    map.put(SERVICE_TYPE.valueOf(name), cfgElt.createExecutableExtension(CLASS));
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        //no extension we init the default one
        if(map.get(type) == null){
            switch (type) {
                case HtmlTemplateGenerator:
                    map.put(type, new HtmlTemplateGenerator());
                    break;
                case FormPreview:
                    map.put(type, new PreviewForm());
                    break;

                default:
                    break;
            }
        }
        return map.get(type);
    }

}
