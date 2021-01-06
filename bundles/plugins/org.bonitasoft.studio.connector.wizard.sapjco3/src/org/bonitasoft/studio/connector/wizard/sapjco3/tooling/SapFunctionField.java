/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.tooling;

import java.util.ArrayList;
import java.util.List;

import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoParameterField;

/**
 * @author Maxence Raoux
 * 
 */
public class SapFunctionField {

	private String name;
	private String defaultContent;
	private boolean isOptionnal;
	private Boolean isTable;
	private Boolean isStructure;
	private List<SapFunctionField> fieldsList;
	private SapFunctionField parent;
	private String inputType;

	public SapFunctionField(JCoField field, SapFunctionField parentField) {
		this.name = field.getName();
		this.isTable = field.isTable();
		this.isStructure = field.isStructure();
		this.parent = parentField;

		if (field.isTable()) {
			this.inputType = SapTool.TABLE;
		} else if (field.isStructure()) {
			this.inputType = SapTool.STRUCTURE;
		} else {
			this.inputType = SapTool.SINGLE;
		}

		if (field instanceof JCoParameterField) {
			final JCoParameterField paramField = (JCoParameterField) field;
			this.isOptionnal = paramField.isOptional();
			this.defaultContent = paramField.getDefault();
		} else {
			this.isOptionnal = true;
			this.defaultContent = "";
		}
	}

	public SapFunctionField getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	public String getDefaultContent() {
		return defaultContent;
	}

	public Boolean isTable() {
		return isTable;
	}

	public Boolean isOptionnal() {
		return isOptionnal;
	}

	public Boolean isStructure() {
		return isStructure;
	}

	public List<SapFunctionField> getFieldsList() {
		if(fieldsList==null){
			fieldsList = new ArrayList<SapFunctionField>();
		}
		return fieldsList;
	}

	public String getInputType() {
		return inputType;
	}

}
