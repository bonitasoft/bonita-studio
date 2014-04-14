package org.bonitasoft.studio.engine.export;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.Arrays;

import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;


public class EngineExpressionUtilTest {



	private Expression createStudioExpression(String name, String content,
			String returnType, String type, EObject...dependencies) {
		Expression studioExpression = ExpressionFactory.eINSTANCE.createExpression();
		studioExpression.setType(type);
		studioExpression.setReturnType(returnType);
		studioExpression.setContent(content);
		studioExpression.setName(name);
		studioExpression.getReferencedElements().addAll(Arrays.asList(dependencies));
		return studioExpression;
	}
	
	@Test
	public void should_createExpression_document() throws Exception {
		//given
		Expression studioExpression = createStudioExpression("document1", "document1", "org.bonitasoft.engine.bpm.document.DocumentValue", ExpressionConstants.DOCUMENT_TYPE);
		
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		assertEquals(new ExpressionBuilder().createDocumentReferenceExpression("document1"), engineExpression);
	}
	
	@Test
	public void should_createExpression_constant() throws Exception {
		//given
		Expression studioExpression = createStudioExpression("12", "12", Long.class.getName(), ExpressionConstants.CONSTANT_TYPE);
		
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		assertEquals(new ExpressionBuilder().createConstantLongExpression(12), engineExpression);
	}
	
	@Test
	public void should_createExpression_groovy() throws Exception {
		//given
		Expression studioExpression = createStudioExpression("myScript", "return 12", Long.class.getName(), ExpressionConstants.SCRIPT_TYPE);
		studioExpression.setInterpreter(ExpressionConstants.GROOVY);
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		assertEquals(new ExpressionBuilder().createGroovyScriptExpression("myScript", "return 12", Long.class.getName()), engineExpression);
	}

	@Test
	public void should_createExpression_data() throws Exception {
		//given

		Data stringData = createData("myData", false, DatasourceConstants.BOS_DATASOURCE);
		Expression studioExpression = createStudioExpression("a", "a", String.class.getName(), ExpressionConstants.VARIABLE_TYPE,stringData);
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		assertEquals(new ExpressionBuilder().createDataExpression("a", String.class.getName()), engineExpression);
	}
	@Test
	public void should_createExpression_form_transient_data() throws Exception {
		//given

		Data stringData = createData("myData", false, DatasourceConstants.PAGEFLOW_DATASOURCE);
		Expression studioExpression = createStudioExpression("a", "a", String.class.getName(), ExpressionConstants.VARIABLE_TYPE,stringData);
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		assertEquals(new ExpressionBuilder().createInputExpression("a", String.class.getName()), engineExpression);
	}
	@Test
	public void should_createExpression_transient_data() throws Exception {
		//given

		Data stringData = createData("myData", true, DatasourceConstants.IN_MEMORY_DATASOURCE);
		Expression studioExpression = createStudioExpression("a", "a", String.class.getName(), ExpressionConstants.VARIABLE_TYPE,stringData);
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		assertEquals(new ExpressionBuilder().createTransientDataExpression("a", String.class.getName()), engineExpression);
	}
	
	@Test
	public void should_createExpression_groovy_with_data_dep() throws Exception {
		//given

		Data stringData = createData("myData", false, DatasourceConstants.BOS_DATASOURCE);
		Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'", String.class.getName(), ExpressionConstants.SCRIPT_TYPE,stringData );
		studioExpression.setInterpreter(ExpressionConstants.GROOVY);
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		org.bonitasoft.engine.expression.Expression excpectedExpression = new ExpressionBuilder().createGroovyScriptExpression("myScript", "return myData+'plop'", String.class.getName(), new ExpressionBuilder().createDataExpression("myData", String.class.getName()));
		assertEquals(excpectedExpression, engineExpression);
	}

	
	@Test
	public void should_createExpression_groovy_with_transient_data_dep() throws Exception {
		//given

		Data stringData = createData("myData", true, DatasourceConstants.BOS_DATASOURCE);
		Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'", String.class.getName(), ExpressionConstants.SCRIPT_TYPE,stringData );
		studioExpression.setInterpreter(ExpressionConstants.GROOVY);
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		org.bonitasoft.engine.expression.Expression excpectedExpression = new ExpressionBuilder().createGroovyScriptExpression("myScript", "return myData+'plop'", String.class.getName(), new ExpressionBuilder().createTransientDataExpression("myData", String.class.getName()));
		assertEquals(excpectedExpression, engineExpression);
	}

	
	@Test
	public void should_createExpression_groovy_with_form_transient_data_dep() throws Exception {
		//given
		Data stringData = createData("myData", false, DatasourceConstants.PAGEFLOW_DATASOURCE);
		Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'", String.class.getName(), ExpressionConstants.SCRIPT_TYPE,stringData );
		studioExpression.setInterpreter(ExpressionConstants.GROOVY);
		//when
		org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);
		
		//then
		org.bonitasoft.engine.expression.Expression excpectedExpression = new ExpressionBuilder().createGroovyScriptExpression("myScript", "return myData+'plop'", String.class.getName(), new ExpressionBuilder().createInputExpression("myData", String.class.getName()));
		assertEquals(excpectedExpression, engineExpression);
	}

	private Data createData(String name, boolean isTransient, String datasource ) {
		Data stringData = ProcessFactory.eINSTANCE.createData();
		stringData.setDatasourceId(datasource);
		stringData.setDataType(ProcessFactory.eINSTANCE.createStringType());
		stringData.setName(name);
		stringData.setTransient(isTransient);
		return stringData;
	}

}
