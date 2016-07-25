/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.tests.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.console.test.util.ProcessRegistry;
import org.bonitasoft.studio.exporter.form.FormsExporterService;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EntryPageFlowType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author Aurelien Pupier
 * 
 */
public class TestFormsExporter extends TestCase {

    /**
     * Test method for
     * {@link org.bonitasoft.studio.console.export.FormsExporter#createXmlForms(org.bonitasoft.studio.model.process.AbstractProcess)}
     * .
     * @throws Exception
     * 
     * @throws InvalidFormDefinitionException
     */
    @Test
    public void testCreateXmlForms() throws Exception {
        MainProcess mainProc = ProcessRegistry.createBasicProcess() ;
        File file = FormsExporterService.getInstance().getFormsExporter().createXmlForms(mainProc,false,Collections.<EObject>emptySet());
        assertNotNull(file);
    }


    /**
     * Test method for
     * {@link org.bonitasoft.studio.console.export.FormsExporter#createXmlForms(org.bonitasoft.studio.model.process.AbstractProcess)}
     * .
     * 
     * @throws IOException
     * @throws InvalidFormDefinitionException
     */
    @Test
    public void testCreateXmlFormsFromFull() throws Exception {
        AbstractProcess mainProc = ProcessRegistry.getTestExampleProcess("BaseTestForForm_1_1.proc") ;
        File file = FormsExporterService.getInstance().getFormsExporter().createXmlForms(mainProc,false,Collections.<EObject>emptySet());
        assertNotNull(file);
    }


    /**
     * Test method for
     * {@link org.bonitasoft.studio.console.export.FormsExporter#createXmlForms(org.bonitasoft.studio.model.process.AbstractProcess)}
     * .
     * 
     * @throws IOException
     * @throws InvalidFormDefinitionException
     */
    @Test
    public void testFormsXmlActions() throws Exception {
        MainProcess testExampleProcess = ProcessRegistry.getTestExampleProcess("TestProcessForActions_1_0.proc");
        AbstractProcess pool = null;
        for (Element element : testExampleProcess.getElements()) {
            if(element instanceof AbstractProcess){
                pool = (AbstractProcess) element;
                break;
            }
        }
        assertNotNull(pool);
        File file = FormsExporterService.getInstance().getFormsExporter().createXmlForms(pool,false,Collections.<EObject>emptySet());
        assertNotNull("forms.xml for TestProcessForActions_1_0.proc was null",file);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        FileInputStream is = new FileInputStream(file);
        Document document = builder.parse(is);
        is.close();
        XPath xpathEvaluator = XPathFactory.newInstance().newXPath();

        Object result = xpathEvaluator.evaluate("//pages/page[@id='Step1']/actions/action/variable",document,XPathConstants.STRING);
        assertEquals("plop", result);
        result = xpathEvaluator.evaluate("//pages/page[@id='Step1']/actions/action/expression/name",document,XPathConstants.STRING);
        assertEquals("field_plop", result);
        result = xpathEvaluator.evaluate("//pages/page[@id='Step1']/actions/action/expression/expression-content",document,XPathConstants.STRING);
        assertEquals("field_plop", result);
        result = xpathEvaluator.evaluate("//pages/page[@id='Step1']/actions/action/expression/expression-type",document,XPathConstants.STRING);
        assertEquals(ExpressionConstants.FORM_FIELD_TYPE, result);
        result = xpathEvaluator.evaluate("//pages/page[@id='Step1']/actions/action/expression/return-type",document,XPathConstants.STRING);
        assertEquals(String.class.getName(), result);
        result = xpathEvaluator.evaluate("//pages/page[@id='Step1']/actions/action/@type",document,XPathConstants.STRING);
        assertEquals("ASSIGNMENT", result);

    }


    /**
     * Test method for
     * {@link org.bonitasoft.studio.console.export.FormsExporter#createXmlForms(org.bonitasoft.studio.model.process.AbstractProcess)}
     * .
     * 
     * @throws IOException
     * @throws InvalidFormDefinitionException
     */
    @Test
    public void testFormsXmlVariableBound() throws Exception {
        MainProcess testExampleProcess = ProcessRegistry.getTestExampleProcess("TestVariableBoundDiagram_1_0.proc");
        AbstractProcess pool = null;
        for (Element element : testExampleProcess.getElements()) {
            if(element instanceof AbstractProcess){
                pool = (AbstractProcess) element;
                break;
            }
        }
        assertNotNull(pool);
        File file = FormsExporterService.getInstance().getFormsExporter().createXmlForms(pool,false,Collections.<EObject>emptySet());
        assertNotNull("forms.xml for TestProcessForActions_1.0.proc was null",file);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        FileInputStream is = new FileInputStream(file);
        Document document = builder.parse(is);
        is.close();
        XPath xpathEvaluator = XPathFactory.newInstance().newXPath();

        Object result = xpathEvaluator.evaluate("//pages/page[@id='Step1']/actions/action[variable-type='"+ExpressionConstants.LEFT_OPERAND_DOCUMENT+"']/variable",document,XPathConstants.STRING);
        assertEquals("myDocument", result);

        is.close();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testSkipForms() throws Exception {

        MainProcess testExampleProcess = ProcessRegistry.getTestExampleProcess("TestSkipForms_1_0.proc");
        AbstractProcess pool = null;
        for (Element element : testExampleProcess.getElements()) {
            if(element instanceof AbstractProcess){
                pool = (AbstractProcess) element;
                break;
            }
        }
        assertNotNull(pool);
        assertEquals(EntryPageFlowType.SKIP, pool.getEntryPageFlowType());
        File file = FormsExporterService.getInstance().getFormsExporter().createXmlForms(pool,false,Collections.<EObject>emptySet());
        assertNotNull("forms.xml for TestSkipForms_1.0.proc was null",file);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        FileInputStream is = new FileInputStream(file);
        Document document = builder.parse(is);
        is.close();
        XPath xpathEvaluator = XPathFactory.newInstance().newXPath();

        //pageflow is present and empty
        Object result = xpathEvaluator.evaluate("//forms/form[@id='testSkipForms--1.0$entry']",document,XPathConstants.NODE);
        Node pageflow = (Node) result;
        assertNotNull(pageflow);
        assertTrue("PageFlow for testSkipForms--1.0$entry has "+pageflow.getChildNodes().getLength() +"children but shouldn't",pageflow.getChildNodes().getLength()==5);//1 for permission + 1 for fomr-type + 3 for th return to line

        //viewpageflow is present and not empty
//        result = xpathEvaluator.evaluate("//form[@id='testSkipForms--1.0$view']",document,XPathConstants.NODE);
//        Node viewPageFlow = (Node) result;
//        assertNotNull(viewPageFlow);
//        assertTrue(viewPageFlow.getChildNodes().getLength()>5);//1 for permission + 1 for fomr-type + 3 for th return to line

        //overView pageflow is present and not empty
        result = xpathEvaluator.evaluate("//form[@id='testSkipForms--1.0$recap']",document,XPathConstants.NODE);
        Node overViewPageFlow = (Node) result;
        assertNotNull(overViewPageFlow);
        assertTrue(overViewPageFlow.getChildNodes().getLength()>5);//1 for permission + 1 for fomr-type + 3 for th return to line



        //pageflow of step is present and empty
        result = xpathEvaluator.evaluate("//forms/form[@id='testSkipForms--1.0--Step1$entry']",document,XPathConstants.NODE);
        pageflow = (Node) result;
        assertNotNull(pageflow);
        assertTrue(pageflow.getChildNodes().getLength()==5);//1 for permission + 1 for fomr-type + 3 for th return to line

        //viewpageflow of step is present and not empty
//        result = xpathEvaluator.evaluate("//form[@id='testSkipForms--1.0--Step1$view']",document,XPathConstants.NODE);
//        viewPageFlow = (Node) result;
//        assertNotNull(viewPageFlow);
//        assertTrue(viewPageFlow.getChildNodes().getLength()>5);///1 for permission + 1 for fomr-type + 3 for th return to line


    }



}
