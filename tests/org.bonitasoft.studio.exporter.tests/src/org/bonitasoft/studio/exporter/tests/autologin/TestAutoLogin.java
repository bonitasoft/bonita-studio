/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.tests.autologin;

import java.util.Collections;
import java.util.Map;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.studio.console.test.util.ProcessRegistry;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Florine Boudin
 *
 */
public class TestAutoLogin extends TestCase {
    private static final String CONF_NAME = "TestConfiguration";
    private static AbstractProcess pool;
	@Override
	protected void setUp() throws Exception {
        if(pool == null){
            NewDiagramCommandHandler newDiagram =  new NewDiagramCommandHandler();
            AbstractProcess p = (AbstractProcess) newDiagram.execute(null).getContent().getElements().get(0);
            pool = EcoreUtil.copy(p);
            Configuration conf = ConfigurationFactory.eINSTANCE.createConfiguration();
            
            conf.setName(CONF_NAME);
            pool.getConfigurations().add(conf);
            
            conf.setAnonymousPassword("bpm");
            conf.setAnonymousUserName("anonymous");
            
            pool.setAutoLogin(true);
        }
        
	}
	
	
    @Test
    public void testGenerateSecurityConfigFile() throws Exception {

    	String securityFileName = "security-config.properties";
    	String securityFilePath = "resources/forms/";
    	Configuration conf=null;
    	
    	for(Configuration tmpConf : pool.getConfigurations()){
    		if(tmpConf.getName().equals(CONF_NAME)){
    			conf=tmpConf;
    		}
    	}

    	if(conf!=null){
    		BusinessArchive bar = BarExporter.getInstance().createBusinessArchive(pool, conf.getName(), Collections.EMPTY_SET);
    		Map<String, byte[]> map = bar.getResources();
    		boolean hasSecurityFile = false;
    		for(String s : map.keySet()){
    			if(s.equals(securityFilePath+securityFileName)){
    				hasSecurityFile = true;
    				break;
    			}
    		}
    		
    		Assert.assertTrue("The "+securityFileName+" file was not generated in the path "+securityFilePath, hasSecurityFile);
    	}

    }
}
