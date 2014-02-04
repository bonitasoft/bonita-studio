/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.custom.migration.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

/**
 * @author Romain Bioteau
 *
 */
public class PatternExpressionMigration extends CustomMigration {

	private Map<String, String> expressions = new HashMap<String, String>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance expression : model.getAllInstances("expression.Expression")){
			if(ExpressionConstants.PATTERN_TYPE.equals(expression.get("type"))){
				final String content = expression.get("content");
				if(content != null && !content.isEmpty()){
					expressions.put(expression.getUuid(),content);
				}
			}
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance expression : model.getAllInstances("expression.Expression")){
			if(expressions.containsKey(expression.getUuid())){
				String content = replaceSpecialCharacter(expressions.get(expression.getUuid()));
				expression.set("content", addDelimiters(content,expression));
			}
		}
	}

	/**Replace all character defined by "&nbsp;" by a whitespace
	 * Without the replace, this character is shown as a sharp (#) instead of a whitespace.
	 * @param content
	 * @return 
	 */
	private String replaceSpecialCharacter(String content) {
		return content.replaceAll("&nbsp;", " ");
	}

	private String addDelimiters(String content, Instance expression) {
		IDocument document = new Document();
		document.set(content);
		StringBuilder patternExpressionContent = new StringBuilder();
		FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		int lenght = content.length();
		int i = 0;
		List<Instance> dependencies = expression.get("referencedElements");
		for (final Instance dep : dependencies) {
			try {
				String depName  = dep.get("name");
				IRegion index = null;
				index = finder.find(i, depName, true, true, true, false);
				while(index != null && index.getOffset() <  lenght){
					if(isNotEscapeWord(content, index.getOffset())){
						patternExpressionContent.append(content.substring(i, index.getOffset()));
						if(alreadyDep(content, index.getLength(), index.getOffset())){
							patternExpressionContent.append(depName);
						}else{
							patternExpressionContent.append("${");
							patternExpressionContent.append(depName);
							patternExpressionContent.append("}");

						}
					}else{
						patternExpressionContent.append(content.substring(i, index.getOffset()-1));
						patternExpressionContent.append(content.substring(index.getOffset(),index.getOffset()+index.getLength()));
					}
					i = index.getOffset() + index.getLength();
					if(i < lenght){
						index = finder.find(i, depName, true, true, true, false);
					}else{
						index = null;
					}
				}
			} catch (BadLocationException e) {
				// Ignore
			}
			if(i < lenght){
				patternExpressionContent.append(content.substring(i, lenght));
			}
			content = patternExpressionContent.toString();
			lenght = content.length();
			i = 0;
			patternExpressionContent = new StringBuilder();
			document.set(content);
			finder = new FindReplaceDocumentAdapter(document);
		}
		return content;
	}

	private boolean isNotEscapeWord(String content, int offset) {
		if(offset-1>-1){
			return content.charAt(offset-1) != '\\';
		}
		return true;
	}
	
	private boolean alreadyDep(String content, int lenghtName, int offset){
		int start = offset-2;
		int end = offset+lenghtName;
		if(start>-1 && end<content.length()){
			return (content.substring(start).startsWith("${") && content.substring(end).startsWith("}")); 
		}
		return false;
		
	}

}
