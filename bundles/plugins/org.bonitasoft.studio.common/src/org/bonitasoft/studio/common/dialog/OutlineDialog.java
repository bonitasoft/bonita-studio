/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * @author aurelie Zara
 *
 */
public class OutlineDialog extends MessageDialog{

    private String message;
    private Label messageLabel;
    private List<Object> elementToDisplay;
    private final ComposedAdapterFactory adapterFactory;
    private ListViewer objectListViewer;
    private TreeViewer outline;
    private final Image warningImg;

    public OutlineDialog(	final Shell parentShell, 		final String dialogTitle,
            final Image dialogTitleImage, final String dialogMessage,
            final int dialogImageType,	final String[] dialogButtonLabels,
            final int defaultIndex,		final List<Object> elementToDisplay) {

        super(parentShell, dialogTitle, dialogTitleImage, dialogMessage,
                dialogImageType, dialogButtonLabels, defaultIndex);
        message = dialogMessage;
        this.elementToDisplay = elementToDisplay;
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        warningImg=dialogTitleImage;
        updateListOfElementToDisplay();

    }

    /** Remove objects that are not referenced in expressions in the parent process in the <i>elementToDisplay</i> list field.
     *
     */
    protected void updateListOfElementToDisplay() {
        final List<Object> filteredElements = new ArrayList<Object>();
        final Iterator<Object> elementIter = elementToDisplay.iterator();
        while (elementIter.hasNext()){
            final Object elem = elementIter.next();
            final AbstractProcess process = ModelHelper.getParentProcess((EObject)elem);
            final List<Expression> listExpr = ModelHelper.getAllItemsOfType(process, ExpressionPackage.Literals.EXPRESSION);
            for(final Expression expr : listExpr){
                if(ModelHelper.isObjectIsReferencedInExpression(expr, elem) && !filteredElements.contains(elem)){
                    filteredElements.add(elem);
                }
            }
        }
        elementToDisplay=filteredElements;
    }

    @Override
    protected Control createMessageArea(final Composite parent) {

        final Composite mainComposite = new Composite(parent,SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10,20).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createMessageComposite(mainComposite);

        if(elementToDisplay!=null && !elementToDisplay.isEmpty()){

            createMessageInformation(mainComposite);

            final Composite viewersComposite = new Composite(mainComposite,SWT.NONE);
            viewersComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(5,5).create());
            viewersComposite.setLayoutData(GridDataFactory.fillDefaults().hint(450,250).grab(true,true).create());
            createTableHeader(viewersComposite);
            createObjectListViewer(viewersComposite);
            createOutline(viewersComposite);
            createEndMessageInformation(mainComposite);
            objectListViewer.setSelection(new StructuredSelection(elementToDisplay.get(0)),true);

        }

        return mainComposite;
    }

    private void createEndMessageInformation(final Composite viewersComposite) {

        final Composite compoMessage = new Composite(viewersComposite, SWT.NONE);
        compoMessage.setLayoutData(GridDataFactory.fillDefaults().hint(450, SWT.DEFAULT).grab(true, false).create());
        compoMessage.setLayout(GridLayoutFactory.fillDefaults().create());

        final Label warningMessage = new Label(compoMessage, SWT.WRAP);
        warningMessage.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final StringBuilder sBuilder = new StringBuilder(Messages.endWarningMessage);
        sBuilder.append(' ');
        sBuilder.append(getElementToDisplayName(elementToDisplay.get(0)));

        for (int i = 1; i < elementToDisplay.size(); i++) {
            final Object elem = elementToDisplay.get(i);
            sBuilder.append(", ");
            sBuilder.append(getElementToDisplayName(elem));
        }
        sBuilder.append(".");

        warningMessage.setText(sBuilder.toString());

    }

    private String getElementToDisplayName(final Object elementToDisplay) {
        if (elementToDisplay instanceof Parameter) {
            return ((Parameter) elementToDisplay).getName();
        } else {
            return ((Element) elementToDisplay).getName();
        }
    }

    private void createMessageInformation(final Composite mainComposite) {
        final Label information = new Label(mainComposite, SWT.NONE);
        information.setText(Messages.bind(Messages.outlineDialogRefactoringText, getElementNameListHeader().toLowerCase()));

    }


    private void createTableHeader(final Composite viewersComposite) {
        final Label headerObjectList= new Label(viewersComposite,  SWT.BOTTOM | SWT.LEFT );
        headerObjectList.setText(getElementNameListHeader());
        final Label headerOutline= new Label(viewersComposite,  SWT.BOTTOM | SWT.LEFT);
        headerOutline.setText(Messages.referenceTreeViewerTitle);
    }


    private String getElementNameListHeader() {
        if(elementToDisplay!=null && !elementToDisplay.isEmpty()){

            final Object element = elementToDisplay.get(0);
            if(element instanceof Parameter){
                return Messages.parameterListTitle;
            }
            if(element instanceof SearchIndex){
                return Messages.searchIndexListViewerTitle;
            }
            if (element instanceof Document) {
                return Messages.documentListViewerTitle;
            }

            if(element instanceof Element){
                return Messages.dataListViewerTitle;
            }


        }
        return "";
    }

    private void createMessageComposite(final Composite mainComposite) {
        final Composite messageComposite = new Composite(mainComposite,SWT.NONE);
        messageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        messageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Label imageLabel = new Label(messageComposite,SWT.NULL);
        warningImg.setBackground(imageLabel.getBackground());
        imageLabel.setImage(warningImg);
        GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING).applyTo(imageLabel);
        createMessageLabel(messageComposite);
    }

    private void createMessageLabel(final Composite messageComposite) {
        messageLabel = new Label(messageComposite,SWT.WRAP);
        if (message!=null){
            messageLabel.setText(message);
            GridDataFactory
            .fillDefaults()
            .align(SWT.FILL, SWT.BEGINNING)
            .grab(true, true)
            .hint(400,
                    SWT.DEFAULT).applyTo(messageLabel);
        }
    }

    private void createOutline(final Composite viewersComposite) {
        outline = new TreeViewer(viewersComposite);
        outline.getTree().setLayoutData(GridDataFactory.fillDefaults().hint(300,200).create());
        outline.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
        outline.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        final ViewerFilter[] filters = {new OutlineFilter()};
        outline.setFilters(filters);
        outline.setInput(ModelHelper.getParentProcess((EObject) elementToDisplay.get(0)));
    }

    private void createObjectListViewer(final Composite viewersComposite) {
        objectListViewer = new ListViewer(viewersComposite);
        objectListViewer.getList().setLayoutData(GridDataFactory.fillDefaults().hint(100,200).create());
        objectListViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
        objectListViewer.setContentProvider(new ArrayContentProvider());
        objectListViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                final EObject selectedObject = (EObject)selection.getFirstElement();
                final OutlineFilter filter =(OutlineFilter) outline.getFilters()[0];
                filter.setElementToDisplay(selectedObject);
                if (outline!=null){
                    outline.refresh(true);
                }
            }
        });
        objectListViewer.setInput(elementToDisplay);
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(final String message) {
        this.message = message;
        if (messageLabel!=null && !messageLabel.isDisposed()) {
            messageLabel.setText(message);
        }
    }

}
