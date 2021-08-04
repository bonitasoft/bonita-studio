package org.bonitasoft.studio.groovy.ui.providers;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class OperatorsToolBar {

    public OperatorsToolBar(Composite parent, GroovyScriptExpressionEditor groovyScriptExpressionEditor) {
        Group group = new Group(parent, SWT.NONE);
        group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        group.setLayout(GridLayoutFactory.fillDefaults().create());
        group.setText("Quick Access Operators");
        
        ToolBar toolBar = new ToolBar(group, SWT.HORIZONTAL);
        toolBar.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        createOperator(toolBar,
                new ExpressionOperator("\u003D",
                        "Test equality:  returns true when right and left operand are equals,false otherwise.", " == "),
                groovyScriptExpressionEditor);
        createOperator(toolBar, new ExpressionOperator("\u2260",
                "Test difference: returns true when right and left operand are not equals,false otherwise.", " != "),
                groovyScriptExpressionEditor);
        createOperator(toolBar, new ExpressionOperator("\u003E",
                "Test greater than: returns true when left operand is greater than the right operand,false otherwise.",
                " > "), groovyScriptExpressionEditor);
        createOperator(toolBar, new ExpressionOperator("\u2265",
                "Test greater than or equals: returns true when left operand is greater than or equals to the right operand,false otherwise.",
                " >= "), groovyScriptExpressionEditor);
        createOperator(toolBar, new ExpressionOperator("\u003C",
                "Test lesser than: returns true when left operand is lesser than the right operand,false otherwise.",
                " < "), groovyScriptExpressionEditor);
        createOperator(toolBar, new ExpressionOperator("\u2264",
                "Test lesser than or equals: returns true when left operand is lesser than or equals to the right operand,false otherwise.",
                " <= "), groovyScriptExpressionEditor);
        separator(toolBar);
        createOperator(toolBar, new ExpressionOperator("And",
                "Test intersection: returns true when left and right operand are true,false otherwise.", " && "),
                groovyScriptExpressionEditor);
        separator(toolBar);
        createOperator(toolBar,
                new ExpressionOperator("Or",
                        "Test union: returns true when left or right operand are true,false otherwise.", " || "),
                groovyScriptExpressionEditor);
        separator(toolBar);
        createOperator(toolBar,
                new ExpressionOperator("Not",
                        "Test negation: returns true when following expression is false, false otherwise.", "!"),
                groovyScriptExpressionEditor);
        separator(toolBar);
        createTemplate(toolBar,
                new TemplateOperator("Condition",
                        "Condition statement: execute a statement block only if a condition is true.", "if"),
                groovyScriptExpressionEditor);
        separator(toolBar);
        createTemplate(toolBar,
                new TemplateOperator("Iterate",
                        "For statement: execute a statement block for each element of a collection .", "for"),
                groovyScriptExpressionEditor);
        separator(toolBar);
        createTemplate(toolBar,
                new TemplateOperator("While",
                        "While statement: execute a statement block while a condition is true.", "while"),
                groovyScriptExpressionEditor);
        
    }

    private void separator(ToolBar toolBar) {
       new ToolItem(toolBar, SWT.SEPARATOR);
    }

    private void createOperator(ToolBar toolBar, ExpressionOperator operator,
            GroovyScriptExpressionEditor groovyScriptExpressionEditor) {
        ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        item.setText(operator.getLabel());
        item.setToolTipText(operator.getDescription());
        item.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                operator.apply((StyledText) groovyScriptExpressionEditor.getTextControl());
            }
            
        });
    }
    
    private void createTemplate(ToolBar toolBar, TemplateOperator operator,
            GroovyScriptExpressionEditor groovyScriptExpressionEditor) {
        ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        item.setText(operator.getLabel());
        item.setToolTipText(operator.getDescription());
        item.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                operator.apply(groovyScriptExpressionEditor.getEditor());
            }
            
        });
    }

}
