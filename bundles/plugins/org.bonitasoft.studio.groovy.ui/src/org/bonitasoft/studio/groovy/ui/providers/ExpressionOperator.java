package org.bonitasoft.studio.groovy.ui.providers;

import org.eclipse.swt.custom.StyledText;


public  class ExpressionOperator {
    
    private String label;
    private String content;
    private String description;

    public ExpressionOperator(String label,String description, String content) {
        this.label = label;
        this.description = description;
        this.content = content;
    }


    public String getLabel() {
        return label;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getContent() {
        return content;
    }

    public void apply(StyledText textControl) {
        textControl.insert(content);
        textControl.setCaretOffset(textControl.getCaretOffset() + content.length());
    }

}
