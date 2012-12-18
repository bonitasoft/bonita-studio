/**
 * Copyright 2003-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bonitasoft.studio.groovy.ui.viewer;

import org.codehaus.groovy.eclipse.core.GroovyCore;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.groovy.eclipse.refactoring.actions.FormatKind;
import org.codehaus.groovy.eclipse.refactoring.formatter.DefaultGroovyFormatter;
import org.codehaus.groovy.eclipse.refactoring.formatter.FormatterPreferences;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.actions.SelectionDispatchAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.ui.IWorkbenchSite;

/**
 * @author Andrew Eisenberg
 * @created Sep 24, 2010
 *
 */
public class BonitaFormatGroovyAction extends SelectionDispatchAction {


	private final FormatKind kind;
	private GroovyCompilationUnit unit;
	private GroovyEditor editor;

	public BonitaFormatGroovyAction(IWorkbenchSite site, FormatKind kind, GroovyEditor editor ,GroovyCompilationUnit unit) {
		super(site);
		this.kind = kind;
		this.unit = unit ;
		this.editor = editor ;
		if (kind == FormatKind.INDENT_ONLY) {
			setText("Indent");
			setToolTipText("Indent Groovy file");
			setDescription("Indent selection in Groovy file");
		} else if (kind == FormatKind.FORMAT) {
			setText("Format");
			setToolTipText("Format Groovy file");
			setDescription("Format selection in Groovy file");
		}
	}

	/* (non-Javadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	@Override
	public void run(ITextSelection selection) {
		IDocument doc =editor.getDocumentProvider().getDocument(editor.getEditorInput());
		if (doc != null && unit != null) {
			DefaultGroovyFormatter formatter = new DefaultGroovyFormatter(selection, doc, new FormatterPreferences(unit),
					kind == FormatKind.INDENT_ONLY);
			TextEdit edit = formatter.format();
			try {
				unit.applyTextEdit(edit, new NullProgressMonitor());
			} catch (MalformedTreeException e) {
				GroovyCore.logException("Exception when formatting", e);
			} catch (JavaModelException e) {
				GroovyCore.logException("Exception when formatting", e);
			}
		}

	}


}
