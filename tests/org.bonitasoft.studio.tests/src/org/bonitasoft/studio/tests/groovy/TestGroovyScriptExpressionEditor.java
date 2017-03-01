/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.groovy;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.providers.GroovyScriptExpressionEditor;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Maxence Raoux
 */
public class TestGroovyScriptExpressionEditor extends TestCase {

    @Test
    public void testComboSorterFunctionTwoVariablesSameCategory() {
        GroovyScriptExpressionEditor tester = new GroovyScriptExpressionEditor();
        ScriptVariable variable1 = new ScriptVariable("aaa", "type");
        variable1.setCategory(ExpressionConstants.VARIABLE_TYPE);
        ScriptVariable variable2 = new ScriptVariable("bbb", "type");
        variable2.setCategory(ExpressionConstants.VARIABLE_TYPE);
        int res = tester.comboSorterFunction((Object) variable1, (Object) variable2);
        assertTrue("Error with two ScriptVariables with same category", res < 0);
        res = tester.comboSorterFunction((Object) variable2, (Object) variable1);
        assertTrue("Error with two ScriptVariables with same category", res > 0);
    }

    @Test
    public void testComboSorterFunctionTwoVariablesDifferentCategory() {
        GroovyScriptExpressionEditor tester = new GroovyScriptExpressionEditor();
        ScriptVariable variable1 = new ScriptVariable("aaaa", "type");
        variable1.setCategory(ExpressionConstants.FORM_FIELD_TYPE);
        ScriptVariable variable2 = new ScriptVariable("aaaa", "type");
        variable2.setCategory(ExpressionConstants.VARIABLE_TYPE);
        int res = tester.comboSorterFunction((Object) variable1, (Object) variable2);
        assertTrue("Error with two ScriptVariables with different category", res < 0);
        res = tester.comboSorterFunction((Object) variable2, (Object) variable1);
        assertTrue("Error with two ScriptVariables with same category", res > 0);
    }

    @Test
    public void testComboSorterFunctionVariablesWithItsCategory() {
        GroovyScriptExpressionEditor tester = new GroovyScriptExpressionEditor();
        Object object1 = ExpressionConstants.FORM_FIELD_TYPE;
        ScriptVariable variable2 = new ScriptVariable("a", "type");
        variable2.setCategory(ExpressionConstants.FORM_FIELD_TYPE);
        int res = tester.comboSorterFunction(object1, (Object) variable2);
        assertTrue("Error with two ScriptVariables and its category", res < 0);
        res = tester.comboSorterFunction((Object) variable2, object1);
        assertTrue("Error with two ScriptVariables and its category", res > 0);
    }

    @Test
    public void testComboSorterFunctionVariablesWithCategory() {
        GroovyScriptExpressionEditor tester = new GroovyScriptExpressionEditor();
        Object object1 = ExpressionConstants.FORM_FIELD_TYPE;
        ScriptVariable variable2 = new ScriptVariable("a", "type");
        variable2.setCategory(ExpressionConstants.VARIABLE_TYPE);
        int res = tester.comboSorterFunction(object1, (Object) variable2);
        assertTrue("Error with two ScriptVariables and a category", res < 0);
        res = tester.comboSorterFunction((Object) variable2, object1);
        assertTrue("Error with two ScriptVariables and a category", res > 0);
    }

    @Test
    public void testComboSorterFunctionTwoCategory() {
        GroovyScriptExpressionEditor tester = new GroovyScriptExpressionEditor();
        Object object1 = ExpressionConstants.FORM_FIELD_TYPE;
        Object object2 = ExpressionConstants.VARIABLE_TYPE;
        int res = tester.comboSorterFunction(object1, object2);
        assertTrue("Error with two category labels", res < 0);
        res = tester.comboSorterFunction(object2, object1);
        assertTrue("Error with two category labels", res > 0);
    }
}
