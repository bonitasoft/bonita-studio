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
package org.bonitasoft.studio.groovy.ui.viewer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.codehaus.groovy.eclipse.editor.GroovyColorManager;
import org.codehaus.groovy.eclipse.editor.GroovyConfiguration;
import org.eclipse.jdt.groovy.core.util.ReflectionUtils;
import org.eclipse.jdt.internal.ui.text.ContentAssistPreference;
import org.eclipse.jdt.internal.ui.text.java.CompletionProposalCategory;
import org.eclipse.jdt.internal.ui.text.java.ContentAssistProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Romain Bioteau
 */
public class BonitaGroovyConfiguration extends GroovyConfiguration {

    private static final Set<String> ALLOWED_CATEGORIES = new HashSet<>();
    static {
        ALLOWED_CATEGORIES.add("org.codehaus.groovy.eclipse.codeassist.category");
        ALLOWED_CATEGORIES.add("org.codehaus.groovy.eclipse.codeassist.templates.category");
    }

    public BonitaGroovyConfiguration(final GroovyColorManager colorManager, final IPreferenceStore preferenceSource,
            final ITextEditor editor) {
        super(colorManager, preferenceSource, editor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IContentAssistant getContentAssistant(final ISourceViewer sourceViewer) {
        // returns only Groovy-approved completion proposal categories
        ContentAssistant assistant = (ContentAssistant) super.getContentAssistant(sourceViewer);
        assistant.setStatusLineVisible(false);

        // retain only relevant categories
        IContentAssistProcessor processor = assistant.getContentAssistProcessor(IDocument.DEFAULT_CONTENT_TYPE);

        List<CompletionProposalCategory> categories = (List<CompletionProposalCategory>) ReflectionUtils
                .getPrivateField(ContentAssistProcessor.class, "fCategories", processor);

        ReflectionUtils.setPrivateField(ContentAssistProcessor.class, "fCategories", processor, categories.stream()
                .filter(category -> ALLOWED_CATEGORIES.contains(category.getId()))
                .collect(Collectors.toList()));

        ContentAssistPreference.configure(assistant, fPreferenceStore);

        return assistant;
    }

}
