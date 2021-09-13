package org.bonitasoft.studio.groovy.ui.providers;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.groovy.eclipse.quickfix.GroovyQuickFixPlugin;
import org.codehaus.groovy.eclipse.quickfix.templates.GroovyContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateProposal;

public class TemplateOperator {

    private String label;
    private String description;
    private String prefix;
    
    private static final TemplateContextType CONTEXT_TYPE = GroovyQuickFixPlugin.getDefault()
            .getTemplateContextRegistry()
            .getContextType(GroovyQuickFixPlugin.GROOVY_CONTEXT_TYPE);

    public TemplateOperator(String label, String description, String prefix) {
        this.label = label;
        this.description = description;
        this.prefix = prefix;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }


    public void apply(GroovyEditor editor) {
        GroovyContext templateContext = new GroovyContext(CONTEXT_TYPE, editor.getViewer().getDocument(), editor.getCaretOffset(), 0, editor.getGroovyCompilationUnit());
        templateContext.setForceEvaluation(true);
        templateContext.setVariable("selection", "");
        try {
            List<ICompletionProposal> proposals = computeCompletionProposals(templateContext, prefix);
            if(!proposals.isEmpty()) {
              ((TemplateProposal) proposals.get(0)).apply(editor.getViewer(), '.', 0, editor.getViewer().getSelectedRange().x);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    private List<ICompletionProposal> computeCompletionProposals(GroovyContext context, String prefix) throws BadLocationException {
        List<ICompletionProposal> templates = new ArrayList<>();
        Region region = new Region(context.getCompletionOffset(), context.getCompletionLength());
        for (Template template : GroovyQuickFixPlugin.getDefault().getTemplateStore().getTemplates()) {
            if (template.getName().startsWith(prefix)) {
                templates.add(new TemplateProposal(template, context, region, null));
            }
        }
        return templates;
    }

}
