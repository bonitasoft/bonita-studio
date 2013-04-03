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

import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.groovy.GroovyPlugin;
import org.bonitasoft.studio.groovy.library.FunctionsRepositoryFactory;
import org.bonitasoft.studio.groovy.library.IFunction;
import org.bonitasoft.studio.groovy.library.IFunctionCategory;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;


/**
 * @author Romain Bioteau
 *
 */
public class GroovyEditorDocumentationDialogTray extends DialogTray {

    private static final String GROOVY_DOC_LINK = "<a href=\"http://groovy.codehaus.org/Getting+Started+Guide\">"+Messages.groovyDocumentationLink+"</a>"; //$NON-NLS-1$ //$NON-NLS-2$
    protected static final String GROOVY_BROWSER_ID = "org.bonitasoft.studio.groovy.browser"; //$NON-NLS-1$

    private ListViewer categoriesList;
    private FilteredTree functionsList;
    private Browser documenationText;
    private final GroovyViewer viewer;


    public GroovyEditorDocumentationDialogTray(GroovyViewer viewer){
        super();
        this.viewer = viewer ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogTray#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());

        createFunctionCategories(mainComposite);
        createFunctionsList(mainComposite);
        createFunctionDocumentaion(mainComposite);
        return mainComposite;
    }

    private void createGroovyHelpLink(Composite parent) {
        final Link docLinkText = new Link(parent, SWT.NONE);
        docLinkText.setText(GROOVY_DOC_LINK);
        docLinkText.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {


                try {
                    IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(GROOVY_BROWSER_ID);
                    browser.openURL(new URL(event.text));
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                }


            }
        });

    }

    private void createFunctionCategories(Composite parent) {
        Composite catComposite = new Composite(parent, SWT.WRAP);
        catComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        catComposite.setLayout(new GridLayout(1, true));

        createGroovyHelpLink(catComposite);

        Label catTitle = new Label(catComposite, SWT.NONE);
        catTitle.setText(Messages.categoriesTitle);

        categoriesList = new ListViewer(catComposite, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
        categoriesList.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        categoriesList.setLabelProvider(new CategoryLabelProvider());
        categoriesList.setContentProvider(new FunctionCategoriesProvider());
        categoriesList.setInput(FunctionsRepositoryFactory.getFunctionCatgories());
        categoriesList.getList().setSelection(0);
        categoriesList.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IFunctionCategory cat =(IFunctionCategory) ((IStructuredSelection)event.getSelection()).getFirstElement();
                functionsList.getViewer().setInput(cat);
            }
        });
    }

    private void createFunctionsList(Composite parent) {
        Composite funcComposite = new Composite(parent, SWT.WRAP);
        funcComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        funcComposite.setLayout(new GridLayout(1, true));

        Label funcTitle = new Label(funcComposite, SWT.NONE);
        funcTitle.setText(Messages.functionTitle);

        functionsList = new FilteredTree(funcComposite,  SWT.BORDER | SWT.V_SCROLL, new PatternFilter(), true) ;
        functionsList.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(300, SWT.DEFAULT).create());
        functionsList.getViewer().setLabelProvider(new FunctionLabeProvider());
        functionsList.getViewer().setContentProvider(new FunctionContentProvider());
        IFunctionCategory cat =(IFunctionCategory) ((IStructuredSelection)categoriesList.getSelection()).getFirstElement();
        functionsList.getViewer().setInput(cat);

        functionsList.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IFunction f = (IFunction) ((IStructuredSelection)event.getSelection()).getFirstElement();
                if(documenationText != null && !documenationText.isDisposed() && f != null && f.getDocumentation() != null) {
                    documenationText.setText(f.getDocumentation());
                }


            }
        });

        functionsList.getViewer().addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                try {
                    final SourceViewer srcViewer = viewer.getSourceViewer();
                    IDocument document = srcViewer.getDocument();
                    IFunction f = (IFunction) ((IStructuredSelection)event.getSelection()).getFirstElement();
                    if(f != null){
                        int offset = srcViewer.getTextWidget().getCaretOffset();
                        String before;

                        before = document.get(0, offset);
                        String toInsert = "";
                        if(f.isStatic()) {
                            toInsert += f.getOwner();
                        } else {
                            toInsert = "new " + f.getOwner() + "()";
                        }
                        toInsert += "."+f.getName();
                        if (f.getParametersCount() > 0) {
                            toInsert += "(" + f.getParameters() + ")";
                        } else {
                            toInsert += "()";
                        }

                        if(offset == document.get().length()){
                            document.set(before + toInsert);
                        } else {
                            String after = document.get().substring(offset, document.get().length());
                            document.set(before + toInsert + after);
                        }

                        srcViewer.getTextWidget().setCaretOffset(offset + toInsert.length());
                        srcViewer.getTextWidget().setFocus();
                    }
                } catch (BadLocationException e) {
                    GroovyPlugin.logError(e);
                }

            }
        });

    }


    private void createFunctionDocumentaion(Composite parent) {
        Composite docComposite = new Composite(parent, SWT.WRAP);
        docComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        docComposite.setLayout(new GridLayout(1, true));

        Label docTitle = new Label(docComposite, SWT.NONE);
        docTitle.setText(Messages.functionDocTitle);
        
        documenationText = new Browser(docComposite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        documenationText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        documenationText.setBackground(ColorConstants.white);
    }

}
