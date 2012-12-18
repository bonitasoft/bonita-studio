/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.ui.dialog;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Document;

/**
 * @author Romain Bioteau
 *
 */
public class ScriptResultDialog extends Dialog {

    private Composite mainComposite;
    private final Object result;

    public ScriptResultDialog(Shell parentShell,Object result) {
        super(parentShell);
        this.result = result ;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.evaluationResults);
    }


    @Override
    protected Control createDialogArea(Composite parent) {
        mainComposite = new Composite(parent,SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().hint(400, 200).grab(true, true).create());
        mainComposite.setLayout(new GridLayout(2, false));
        new Label(mainComposite, SWT.NONE).setText("Result");
        Object value = result;
        if(value == null || value instanceof String || value instanceof Long || value instanceof Integer){//TODO check other types
            Text text = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL) ;
            text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
            if(value != null){
                text.setText(value.toString());
            }else{
                text.setText("NULL"); //$NON-NLS-1$
            }
            text.setEditable(false);
            text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        } else if(value instanceof Document){
            TreeViewer viewer = new TreeViewer(mainComposite, SWT.BORDER);
            viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

            viewer.setContentProvider(new XmlDocumentContentProvider());
            viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
            viewer.setLabelProvider(new XmlLabelProvider());
            viewer.setInput(value);
            viewer.getTree().setFont(BonitaStudioFontRegistry.getCommentsFont());
            viewer.expandAll();
        } else{
            TreeViewer viewer = new TreeViewer(mainComposite, SWT.BORDER);
            viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

            viewer.setContentProvider(new PojoBrowserContentProvider());
            viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
            viewer.setLabelProvider(new JavaUILabelProvider() {

                @Override
                public Image getImage(Object element) {
                    if( element instanceof Pair<?,?>) {
                        return getImage(((Pair<?,?>) element).getFirst());
                    }else if(element == null){
                        return null;
                    }
                    return super.getImage(element);
                }
                @Override
                public String getText(Object item) {
                    if (item instanceof String) {
                        return (String)item;
                    } if( item instanceof Pair<?,?>) {

                        Object first = ((Pair<?,?>) item).getFirst();
                        if(first instanceof IType){
                            return getText(first) + " : " + ((Pair<?,?>) item).getSecond().toString();
                        }else{
                            return getText(first);
                        }
                    }else if (item instanceof IMember) {
                        return super.getText(item);
                    } else if(item != null){
                        return item.toString();
                    } else{
                        return null;
                    }
                }
            });
            viewer.setInput(value);
        }

        return parent;
    }

}
