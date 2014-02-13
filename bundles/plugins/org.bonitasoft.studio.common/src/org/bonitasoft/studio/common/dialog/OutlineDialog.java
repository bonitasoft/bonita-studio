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

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.eclipse.swt.custom.CLabel;
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
	private ComposedAdapterFactory adapterFactory;
	private ListViewer objectListViewer;
	private TreeViewer outline;
	private Image warningImg;
	
	public OutlineDialog(Shell parentShell, String dialogTitle,
			Image dialogTitleImage, String dialogMessage, int dialogImageType,
			String[] dialogButtonLabels, int defaultIndex,List<Object> elementToDisplay) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage,
				dialogImageType, dialogButtonLabels, defaultIndex);
		this.message = dialogMessage;
		this.elementToDisplay = elementToDisplay;
		this.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		this.warningImg=dialogTitleImage;
	}
	
	
	
	@Override
	protected Control createMessageArea(Composite parent) {
		Composite mainComposite = new Composite(parent,SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10,20).create());
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		Composite messageComposite = new Composite(mainComposite,SWT.NONE);
		messageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		messageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		Label imageLabel = new Label(messageComposite,SWT.NULL);
		warningImg.setBackground(imageLabel.getBackground());
		imageLabel.setImage(warningImg);
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING).applyTo(imageLabel);
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
		Composite viewersComposite = new Composite(mainComposite,SWT.NONE);
		viewersComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(5,20).create());
		viewersComposite.setLayoutData(GridDataFactory.fillDefaults().hint(450,250).grab(true,true).create());
		objectListViewer = new ListViewer(viewersComposite);
		objectListViewer.getList().setLayoutData(GridDataFactory.fillDefaults().hint(100,200).create());
		objectListViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		objectListViewer.setContentProvider(new ArrayContentProvider());
		objectListViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				EObject selectedObject = (EObject)selection.getFirstElement();
				OutlineFilter filter =(OutlineFilter) outline.getFilters()[0];
				filter.setElementToDisplay(selectedObject);
				if (outline!=null){
					outline.refresh(true);
				}
			}
		});
		objectListViewer.setInput(elementToDisplay);
		
		outline = new TreeViewer(viewersComposite);
		outline.getTree().setLayoutData(GridDataFactory.fillDefaults().hint(300,200).create());
		outline.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		outline.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		new OutlineFilter();
		ViewerFilter[] filters = {new OutlineFilter()};
		outline.setFilters(filters);
		if (!(elementToDisplay.get(0) instanceof Widget)){
			outline.setInput(ModelHelper.getParentProcess((EObject)elementToDisplay.get(0)));
		} else {
			outline.setInput(ModelHelper.getPageFlow((Widget)elementToDisplay.get(0)));
		}
	    
	    objectListViewer.setSelection(new StructuredSelection(elementToDisplay.get(0)),true);
	    
		return mainComposite;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public void setMessage(String message) {
		this.message = message;
		if (messageLabel!=null && !messageLabel.isDisposed()) {
			messageLabel.setText(message);
		}
	}

}
