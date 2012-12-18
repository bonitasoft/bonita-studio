package org.bonitasoft.studio.exporter.tests.application;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.bonitasoft.studio.console.test.util.ProcessRegistry;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.exporter.application.HtmlTemplateGenerator;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;

public class TestHtmlTemplateGenerator extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	/**
	 * Test method for
	 * {@link org.bonitasoft.studio.exporter.application#createXhtmlTemplate(org.bonitasoft.studio.model.form.Form)}
	 * 
	 * 
	 */
	public void testcreateXhtmlTemplate(){
		AbstractProcess process = ProcessRegistry.createBasicProcessWithForm();
		String file = ((HtmlTemplateGenerator) ExporterService.getInstance().getExporterService(SERVICE_TYPE.HtmlTemplateGenerator)).createXhtmlTemplate(process.getForm().get(0),false,null,false);
			// this.copier(file, new File(file.getParentFile().getAbsolutePath()
			// + "save" + file.getName()));
		File htmlFile = new File(file);
		assertTrue(htmlFile.exists());
		try {
			Source pageTemplateSource = new Source(new FileInputStream(file));
			Element el;
			for(Widget w :process.getForm().get(0).getWidgets()){
				el = pageTemplateSource.getElementById(w.getName());
				if(el == null){
					fail("missing an element in template (with name:"+w.getName()+")");
				}
			}
		} catch (FileNotFoundException e1) {
			fail(e1.getMessage());
		} catch (IOException e1) {
			fail(e1.getMessage());
		}
		
	}

}
