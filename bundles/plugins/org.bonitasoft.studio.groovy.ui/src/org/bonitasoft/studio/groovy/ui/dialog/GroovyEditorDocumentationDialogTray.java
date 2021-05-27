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
package org.bonitasoft.studio.groovy.ui.dialog;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.GroovyPlugin;
import org.bonitasoft.studio.groovy.library.FunctionsRepositoryFactory;
import org.bonitasoft.studio.groovy.library.IFunction;
import org.bonitasoft.studio.groovy.library.IFunctionCategory;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.internal.text.html.HTMLTextPresenter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Romain Bioteau
 */
public class GroovyEditorDocumentationDialogTray extends DialogTray {

    private ListViewer categoriesList;
    private FilteredTree functionsList;
    private StyledText documenationText;
    private final GroovyViewer viewer;
    private HTMLTextPresenter fPresenter;
    private String javadocHtml;
    private final TextPresentation fPresentation = new TextPresentation();
    private final GroovyHelpLinkFactory groovyHelpLinkFactory = new GroovyHelpLinkFactory();

    public GroovyEditorDocumentationDialogTray(final GroovyViewer viewer) {
        super();
        this.viewer = viewer;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogTray#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(final Composite parent) {

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        createFunctionCategories(mainComposite);

        final SashForm sashForm = new SashForm(mainComposite, SWT.VERTICAL);
        sashForm.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(250, 250).minSize(100, SWT.DEFAULT).create());
        final GridLayout gridLaout = GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 2).create();
        sashForm.setLayout(gridLaout);
        createFunctionsList(sashForm);
        createFunctionDocumentaion(sashForm);

        sashForm.setWeights(new int[] { 1, 1 });

        return mainComposite;
    }

    private void createFunctionCategories(final Composite parent) {
        final Composite catComposite = new Composite(parent, SWT.NONE);
        catComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        catComposite.setLayout(new GridLayout(1, true));

        groovyHelpLinkFactory.createGroovyHelpLink(catComposite);

        final Label catTitle = new Label(catComposite, SWT.NONE);
        catTitle.setText(Messages.categoriesTitle);

        categoriesList = new ListViewer(catComposite, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
        categoriesList.setLabelProvider(new CategoryLabelProvider());
        categoriesList.setContentProvider(new FunctionCategoriesProvider());
        categoriesList.setInput(
                FunctionsRepositoryFactory.getFunctionCatgories(RepositoryManager.getInstance().getCurrentRepository()));
        categoriesList.getList().setSelection(0);
        final int categorieMinheight = categoriesList.getList().getItemHeight() * categoriesList.getList().getItemCount();
        categoriesList.getList().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, categorieMinheight).create());
        categoriesList.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final IFunctionCategory cat = (IFunctionCategory) ((IStructuredSelection) event.getSelection()).getFirstElement();
                functionsList.getViewer().setInput(cat);
            }
        });
    }

    private void createFunctionsList(final Composite parent) {
        final Composite funcComposite = new Composite(parent, SWT.NONE);
        funcComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(210, SWT.DEFAULT).create());
        funcComposite.setLayout(new GridLayout(1, true));

        final Label funcTitle = new Label(funcComposite, SWT.NONE);
        funcTitle.setText(Messages.functionTitle);

        functionsList = new FilteredTree(funcComposite, SWT.BORDER | SWT.V_SCROLL, new PatternFilter(), true);
        functionsList.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        functionsList.getViewer().setLabelProvider(new FunctionLabeProvider());
        functionsList.getViewer().setContentProvider(new FunctionContentProvider());
        final IFunctionCategory cat = (IFunctionCategory) ((IStructuredSelection) categoriesList.getSelection()).getFirstElement();
        functionsList.getViewer().setInput(cat);

        functionsList.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final IFunction f = (IFunction) ((IStructuredSelection) event.getSelection()).getFirstElement();
                refreshDocumentationText(f);
            }

        });

        parent.addControlListener(new ControlListener() {

            @Override
            public void controlResized(final ControlEvent e) {
                final IFunction f = (IFunction) ((IStructuredSelection) functionsList.getViewer().getSelection()).getFirstElement();
                refreshDocumentationText(f);
            }

            @Override
            public void controlMoved(final ControlEvent e) {

            }
        });

        functionsList.getViewer().addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent event) {
                try {
                    final SourceViewer srcViewer = viewer.getSourceViewer();
                    final IDocument document = srcViewer.getDocument();
                    final IFunction f = (IFunction) ((IStructuredSelection) event.getSelection()).getFirstElement();
                    if (f != null) {
                        final int offset = srcViewer.getTextWidget().getCaretOffset();
                        String before;

                        before = document.get(0, offset);
                        String toInsert = "";
                        if (f.isStatic()) {
                            toInsert += f.getOwner();
                        } else {
                            toInsert = "new " + f.getOwner() + "()";
                        }
                        toInsert += "." + f.getName();
                        if (f.getParametersCount() > 0) {
                            toInsert += "(" + f.getParameterNames() + ")";
                        } else {
                            toInsert += "()";
                        }

                        if (offset == document.get().length()) {
                            document.set(before + toInsert);
                        } else {
                            final String after = document.get().substring(offset, document.get().length());
                            document.set(before + toInsert + after);
                        }

                        srcViewer.getTextWidget().setCaretOffset(offset + toInsert.length());
                        srcViewer.getTextWidget().setFocus();
                    }
                } catch (final BadLocationException e) {
                    GroovyPlugin.logError(e);
                }

            }
        });

    }

    protected void refreshDocumentationText(final IFunction f) {
        if (documenationText != null && !documenationText.isDisposed() && f != null && f.getDocumentation() != null) {
            try {
                fPresentation.clear();
                final Rectangle size = documenationText.getClientArea();
                javadocHtml = fPresenter.updatePresentation(documenationText, f.getDocumentation(), fPresentation, size.width, size.height);
            } catch (final IllegalArgumentException ex) {
                // the javadoc might no longer be valid
                return;
            }
            documenationText.setText(javadocHtml);
            TextPresentation.applyTextPresentation(fPresentation, documenationText);
        }
    }

    private void createFunctionDocumentaion(final Composite parent) {
        final Composite docComposite = new Composite(parent, SWT.NONE);
        docComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        docComposite.setLayout(new GridLayout(1, true));

        final Label docTitle = new Label(docComposite, SWT.NONE);
        docTitle.setText(Messages.functionDocTitle);

        documenationText = new StyledText(docComposite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        documenationText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 90).create());
        documenationText.setEditable(false);
        fPresenter = new HTMLTextPresenter(false);
    }

}
