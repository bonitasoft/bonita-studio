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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.groovy.contentassist.ExtendedJavaCompletionProcessor;
import org.codehaus.groovy.eclipse.editor.GroovyColorManager;
import org.codehaus.groovy.eclipse.editor.GroovyConfiguration;
import org.codehaus.groovy.eclipse.editor.GroovyPartitionScanner;
import org.eclipse.jdt.groovy.core.util.ReflectionUtils;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.text.ContentAssistPreference;
import org.eclipse.jdt.internal.ui.text.java.CompletionProposalCategory;
import org.eclipse.jdt.internal.ui.text.java.ContentAssistProcessor;
import org.eclipse.jdt.internal.ui.text.javadoc.JavadocCompletionProcessor;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Romain Bioteau
 */
public class BonitaGroovyConfiguration extends GroovyConfiguration {

    public BonitaGroovyConfiguration(final GroovyColorManager colorManager, final IPreferenceStore preferenceSource, final ITextEditor editor) {
        super(colorManager, preferenceSource, editor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IContentAssistant getContentAssistant(final ISourceViewer sourceViewer) {
        if (getEditor() != null) {

            final ContentAssistant assistant = new ContentAssistant();
            assistant.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

            assistant.setRestoreCompletionProposalSize(getSettings("completion_proposal_size")); //$NON-NLS-1$

            final IContentAssistProcessor javaProcessor = new ExtendedJavaCompletionProcessor(getEditor(), assistant, IDocument.DEFAULT_CONTENT_TYPE);
            assistant.setContentAssistProcessor(javaProcessor, IDocument.DEFAULT_CONTENT_TYPE);

            final ContentAssistProcessor singleLineProcessor = new ExtendedJavaCompletionProcessor(getEditor(), assistant,
                    IJavaPartitions.JAVA_SINGLE_LINE_COMMENT);
            assistant.setContentAssistProcessor(singleLineProcessor, IJavaPartitions.JAVA_SINGLE_LINE_COMMENT);

            final ContentAssistProcessor stringProcessor = new ExtendedJavaCompletionProcessor(getEditor(), assistant, IJavaPartitions.JAVA_STRING);
            assistant.setContentAssistProcessor(stringProcessor, IJavaPartitions.JAVA_STRING);

            final ContentAssistProcessor multiLineProcessor = new ExtendedJavaCompletionProcessor(getEditor(), assistant,
                    IJavaPartitions.JAVA_MULTI_LINE_COMMENT);
            assistant.setContentAssistProcessor(multiLineProcessor, IJavaPartitions.JAVA_MULTI_LINE_COMMENT);

            final ContentAssistProcessor javadocProcessor = new JavadocCompletionProcessor(getEditor(), assistant);
            assistant.setContentAssistProcessor(javadocProcessor, IJavaPartitions.JAVA_DOC);

            ContentAssistPreference.configure(assistant, fPreferenceStore);

            assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
            assistant.setInformationControlCreator(new IInformationControlCreator() {

                @Override
                public IInformationControl createInformationControl(final Shell parent) {
                    return new DefaultInformationControl(parent, JavaPlugin.getAdditionalInfoAffordanceString());
                }
            });

            assistant.enableAutoActivation(true);
            assistant.setStatusLineVisible(false);
            final ContentAssistProcessor multiLineStringProcessor = new ExtendedJavaCompletionProcessor(getEditor(), assistant,
                    GroovyPartitionScanner.GROOVY_MULTILINE_STRINGS);
            assistant.setContentAssistProcessor(multiLineStringProcessor, GroovyPartitionScanner.GROOVY_MULTILINE_STRINGS);

            // remove Java content assist processor category
            // do a list copy so as not to disturb globally shared list.
            final IContentAssistProcessor processor = assistant.getContentAssistProcessor(IDocument.DEFAULT_CONTENT_TYPE);
            final List<CompletionProposalCategory> categories = (List<CompletionProposalCategory>) ReflectionUtils.getPrivateField(ContentAssistProcessor.class,
                    "fCategories", processor);
            final List<CompletionProposalCategory> newCategories = new ArrayList<>();
            for (final CompletionProposalCategory category : categories) {
                if (!category.getId().equals("org.eclipse.jdt.ui.javaTypeProposalCategory")
                        && !category.getId().equals("org.eclipse.jdt.ui.javaNoTypeProposalCategory")
                        && !category.getId().equals("org.eclipse.jdt.ui.javaAllProposalCategory")
                        && !category.getId().equals("org.eclipse.mylyn.java.ui.javaAllProposalCategory")
                        /*
                         * && !category.getId().equals("org.eclipse.jdt.ui.templateProposalCategory")
                         * /* && !category.getId().equals("org.eclipse.jdt.ui.defaultProposalCategory")
                         */
                        && !category.getId().equals("org.eclipse.jdt.ui.textProposalCategory")
                        && !category.getId().equals("org.eclipse.jdt.ui.swtProposalCategory")
                        && !category.getId().equals("org.eclipse.jst.ws.jaxws.ui.jaxwsProposalComputer")
                        && !category.getId().equals("org.eclipse.pde.api.tools.ui.apitools_proposal_category")) {
                    newCategories.add(category);
                }
            }

            ReflectionUtils.setPrivateField(ContentAssistProcessor.class, "fCategories", processor, newCategories);
            return assistant;
        }
        return null;
    }

    private IDialogSettings getSettings(final String sectionName) {
        IDialogSettings settings = JavaPlugin.getDefault().getDialogSettings().getSection(sectionName);
        if (settings == null) {
            settings = JavaPlugin.getDefault().getDialogSettings().addNewSection(sectionName);
        }

        return settings;
    }
}
