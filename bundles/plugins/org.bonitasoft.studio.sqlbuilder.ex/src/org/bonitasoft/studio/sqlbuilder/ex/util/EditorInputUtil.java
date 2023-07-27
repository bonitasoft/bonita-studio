/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.sqlbuilder.ex.util;

import java.io.IOException;
import java.io.InputStream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderFileEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderStorageEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLBuilderEditorInputUtil;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage;
import org.eclipse.ui.XMLMemento;

public class EditorInputUtil {

	/**
	 * Utility function which runs SQLBuilderEditorInputUtil through its paces.
	 * Creates a SQLBuilderStorageEditorInput from a file, saves this to an XMLMemento then
	 * serializes the XMLMemento to a string. Finally, it creates a new SQLBuilderStorageEditorInput
	 * from the string.
	 * 
	 * @param file
	 * @return
	 */
	public static SQLBuilderStorageEditorInput createSQLBuilderStorageEditorInputFromStringViaFile(IFile input) {

		/*
		 * Create SQLBuilderStorageEditorInput from a file
		 */
		/*
		 * Get SQLStatement, ConnectionInfo and OmitSchemaInfo from file.
		 */
		SQLBuilderFileEditorInput fileEditorInput = new SQLBuilderFileEditorInput(input);
		InputStream is = null;
		String sSQL = null;
		try {
			is = input.getContents();
			sSQL = org.eclipse.datatools.sqltools.sqlbuilder.util.FileUtil.getContents(is);
		} catch (IOException e) {
			BonitaStudioLog.error(e);
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
		}
		ISQLEditorConnectionInfo connectionInfo = fileEditorInput.getConnectionInfo();
		IOmitSchemaInfo omitSchemaInfo = fileEditorInput.getOmitSchemaInfo();
		
		/*
		 * Create SQLBuilderStorageEditorInput and put the SQL, ConnectionInfo and OmitSchemaInfo in it
		 */
		SQLEditorStorage storage = new SQLEditorStorage(input.getName(), sSQL);
		SQLBuilderStorageEditorInput storageEditorInput = new SQLBuilderStorageEditorInput(storage);
		storageEditorInput.setConnectionInfo(connectionInfo);
		storageEditorInput.setOmitSchemaInfo(omitSchemaInfo);

		/*
		 * Save SQLBuilderStorageEditorInput to an XMLMemento
		 */
		XMLMemento memento = SQLBuilderEditorInputUtil.saveSQLBuilderStorageEditorInput(storageEditorInput);

		/*
		 *  Write out the XMLMemento to a string
		 */
		String sMemento = SQLBuilderEditorInputUtil.writeXMLMementoToString(memento);

		/*
		 * Create a new XMLMemento from the string just created
		 */
		return SQLBuilderEditorInputUtil.createSQLBuilderStorageEditorInput(sMemento);

	}

	public static SQLBuilderStorageEditorInput createSQLBuilderStorageEditorFromString(String input) {

		/*
		 * Create SQLBuilderStorageEditorInput from a string
		 */
		SQLBuilderStorageEditorInput storageEditorInput = SQLBuilderEditorInputUtil.createSQLBuilderStorageEditorInput(input);

		/*
		 * Save SQLBuilderStorageEditorInput to an XMLMemento
		 */
		XMLMemento memento = SQLBuilderEditorInputUtil.saveSQLBuilderStorageEditorInput(storageEditorInput);

		/*
		 *  Write out the XMLMemento to a string
		 */
		String sMemento = SQLBuilderEditorInputUtil.writeXMLMementoToString(memento);

		/*
		 * Create a new XMLMemento from the string just created
		 */
		return SQLBuilderEditorInputUtil.createSQLBuilderStorageEditorInput(sMemento);

	}

	/**
	 * Creates a SQLBuilderEditorInput based on a file which was created for the
	 * SQL File Editor
	 * 
	 * @param file
	 * @return
	 */
	public static SQLBuilderEditorInput createSQLBuilderEditorInputFromFile(
			IFile file) {
		/*
		 * Create SQLBuilderEditorInput from a file
		 */
		SQLBuilderEditorInput editorInput = SQLBuilderEditorInputUtil.createSQLBuilderEditorInput(file);
		return editorInput;
	}

	/**
	 * Creates a SQLBuilderEditorInput based on a file which was created for the
	 * SQL File Editor, but with no SQL statement, and a specified statementType.
	 * This is used for creating new statements
	 * 
	 * @param file
	 * @param statementType
	 * @return
	 */
	public static SQLBuilderEditorInput createEmptySQLBuilderEditorInputFromFile(
			IFile file, int statementType) {
		SQLBuilderEditorInput editorInput = SQLBuilderEditorInputUtil.createSQLBuilderEditorInput(file);

		// Set the SQLStatementInfo to be null
		editorInput.setSQLStatementInfo(null);

		// Set the statement type
		editorInput.setStatementType(statementType);

		return editorInput;
	}

	public static SQLBuilderStorageEditorInput createSQLBuilderEditorInput(
			String currentQuery) {
		return  SQLBuilderEditorInputUtil.createSQLBuilderStorageEditorInput( currentQuery);
	}


}
