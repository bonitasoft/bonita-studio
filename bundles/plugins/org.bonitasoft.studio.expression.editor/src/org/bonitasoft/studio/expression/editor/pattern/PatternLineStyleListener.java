/**
 * Copyright (C) 2016 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.pattern;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.eclipse.GroovyPlugin;
import org.codehaus.groovy.eclipse.editor.GroovyColorManager;
import org.codehaus.groovy.eclipse.editor.GroovyTextTools;
import org.codehaus.groovy.eclipse.preferences.PreferenceConstants;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

public class PatternLineStyleListener implements LineStyleListener {

    private final IDocument document;

    private static Font groovyTextFont;

    public PatternLineStyleListener(IDocument document) {
        this.document = document;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.custom.LineStyleListener#lineGetStyle(org.eclipse.swt.custom.LineStyleEvent)
     */
    @Override
    public void lineGetStyle(LineStyleEvent event) {
        if (document == null)
            return;
        final IDocumentPartitioner partitioner = document.getDocumentPartitioner();
        if (partitioner == null)
            return;

        final ITypedRegion[] regions = partitioner.computePartitioning(event.lineOffset, event.lineText.length());
        final List<ITypedRegion> groovyPartions = new ArrayList<>();
        for (int i = 0; i < regions.length; i++) {
            final ITypedRegion partition = regions[i];
            if (PatternExpressionViewer.GROOVY_EXPRESSION_CONTENT_TYPE.equals(partition.getType())) {
                groovyPartions.add(partition);
            }
        }
        final GroovyTextTools textTools = GroovyPlugin.getDefault().getTextTools();
        final GroovyColorManager colorManager = textTools.getColorManager();
        final StyleRange[] styles = new StyleRange[groovyPartions.size()];
        for (int i = 0; i < groovyPartions.size(); i++) {
            final ITypedRegion partition = groovyPartions.get(i);

            final StyleRange styleRange = new StyleRange(partition.getOffset(), partition.getLength(),
                    colorManager.getColor(PreferenceConstants.GROOVY_EDITOR_DEFAULT_COLOR),
                    Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
            styleRange.font = getGroovyTextFont(event.getSource());
            styleRange.fontStyle = SWT.BOLD;
            styles[i] = styleRange;
        }
        event.styles = styles;
    }

    private Font getGroovyTextFont(Object object) {
        if (groovyTextFont == null) {
            final IPreferenceStore preferenceStore = JavaPlugin.getDefault().getPreferenceStore();
            final FontData data = PreferenceConverter.getFontData(preferenceStore, JFaceResources.TEXT_FONT);
            if (data != null) {
                groovyTextFont = new Font(((Widget) object).getDisplay(), data);
            }
        }
        return groovyTextFont;

    }

}
