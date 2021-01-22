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
package org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.provider;

import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class QueryStatementLabelProvider extends LabelProvider implements
		IContentProvider {

	
	@Override
	public String getText(Object element) {
		if((Integer)element == StatementHelper.STATEMENT_TYPE_SELECT){
			return "Select" ;
		}else if((Integer)element == StatementHelper.STATEMENT_TYPE_INSERT){
			return "Insert" ;
		}else if((Integer)element == StatementHelper.STATEMENT_TYPE_UPDATE){
			return "Update" ;
		}else if((Integer)element == StatementHelper.STATEMENT_TYPE_DELETE){
			return "Delete" ;
		}
		return super.getText(element);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
