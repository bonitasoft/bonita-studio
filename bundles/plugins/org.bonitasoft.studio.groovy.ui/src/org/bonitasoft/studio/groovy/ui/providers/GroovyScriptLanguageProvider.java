/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui.providers;

import org.bonitasoft.engine.expression.ExpressionInterpreter;
import org.bonitasoft.studio.expression.editor.ExpressionEditorPlugin;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.scripting.extensions.IScriptLanguageProvider;
import org.eclipse.swt.graphics.Image;

public class GroovyScriptLanguageProvider implements IScriptLanguageProvider {

    private static final String LANGUAGE_ID = ExpressionInterpreter.GROOVY.name();

    @Override
    public String getLanguageId() {
        return LANGUAGE_ID;
    }

    @Override
    public String getLanguageName() {
        return getLanguageId();
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("function.png", ExpressionEditorPlugin.getDefault());
    }

    @Override
    public IExpressionEditor getExpressionEditor() {
        return new GroovyScriptExpressionEditor();
    }

}
