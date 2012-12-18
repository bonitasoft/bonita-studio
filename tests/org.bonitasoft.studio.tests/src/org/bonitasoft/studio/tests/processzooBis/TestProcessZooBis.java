package org.bonitasoft.studio.tests.processzooBis;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.tests.processzoo.TestProcessZoo;

/**
 * @author Aurelien Pupier
 * A second zoo for processes to avoid OOM
 */
public class TestProcessZooBis extends TestProcessZoo {
	
	@Override
	protected String getPackage() {
		return "/org/bonitasoft/studio/tests/processzooBis";
	}
	
	protected List<URL> getEntries() {
		List<URL> res = new ArrayList<URL>();
		String[] nameForEntry = new String[]{
			//	"bonita4/OrdDispGen.bar"
				
		};
		
		for(String name : nameForEntry){
			res.add(this.getClass().getResource(name));
		}	
		return res;
	}
	
}
