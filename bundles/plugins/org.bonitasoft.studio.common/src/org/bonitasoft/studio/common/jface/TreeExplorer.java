/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * @author Romain Bioteau
 *
 */
public class TreeExplorer extends Composite {

	private TreeViewer leftTree;
	private TableViewer rightTable;
	private ITreeContentProvider contentProvider;
	private ILabelProvider labelProvider;

	public TreeExplorer(Composite parent, int style) {
		super(parent, style);
		setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

		final Text searchField = new Text(this, SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
		searchField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		searchField.setMessage(Messages.filterLabel);

		final Composite content = new Composite(this, SWT.BORDER | SWT.FLAT);
		content.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true).spacing(0, 0).margins(0, 0).create());
		content.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		leftTree = new TreeViewer(content, SWT.V_SCROLL);
		leftTree.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		rightTable = new TableViewer(content, SWT.NONE);
		rightTable.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		rightTable.setSorter(new ViewerSorter());
		rightTable.addFilter(new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if(searchField.getText().isEmpty()){
					return true;
				}
				final String text = labelProvider.getText(element);
				return text != null && text.toLowerCase().contains(searchField.getText().toLowerCase()) ;
			}
		});
		leftTree.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object selection =	((IStructuredSelection) event.getSelection()).getFirstElement();
				if(selection != null){
					Object[] children = getSubtree(selection);
					if(children != null){
						rightTable.setInput(children);
					}
				}
			}
		});
		
		rightTable.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object selection =	((IStructuredSelection) event.getSelection()).getFirstElement();
				if(selection != null){
					Object parent = contentProvider.getParent(selection);
					if(parent != null){
						leftTree.setSelection(new StructuredSelection(parent),true);
					}
				}
			}
		});
		
		searchField.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						rightTable.refresh();
					}
				});
				
			}
		});

	}

	protected Object[] getSubtree(Object selection) {
		Set<Object> result = new HashSet<Object>();
		addChildren(result,selection);
		return result.toArray();
	}
	
	private void addChildren(Set<Object> result, Object element) {
		if(contentProvider.hasChildren(element)){
			for(Object c : contentProvider.getChildren(element)){
				result.add(c);
				addChildren(result, c);
			}
		}
	}

	public void setContentProvider(ITreeContentProvider contentProvider){
		this.contentProvider = contentProvider;
	}

	public void addLeftTreeFilter(ViewerFilter filter){
		leftTree.addFilter(filter);
	}

	public void addRightTreeFilter(ViewerFilter filter){
		rightTable.addFilter(filter);
	}

	public void setLabelProvider(ILabelProvider labelProvider){
		this.labelProvider = labelProvider;
	}

	public void setInput(Object input){
		Assert.isNotNull(contentProvider);
		Assert.isNotNull(labelProvider);
		leftTree.setContentProvider(contentProvider);
		leftTree.setLabelProvider(labelProvider);
		rightTable.setContentProvider(new ArrayContentProvider());
		rightTable.setLabelProvider(labelProvider);
		leftTree.setInput(input);
	}

	public void setLeftHeader(String title) {
		leftTree.getTree().setHeaderVisible(true);
		final TreeColumn columnName = new TreeColumn(leftTree.getTree(), SWT.NONE);
		columnName.setText(title);
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(1));
		leftTree.getTree().setLayout(layout);
	}

	public void setRightHeader(String title) {
		rightTable.getTable().setHeaderVisible(true);
		final TableColumn columnName = new TableColumn(rightTable.getTable(), SWT.NONE);
		columnName.setText(title);
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(1));
		rightTable.getTable().setLayout(layout);
	}

	public Viewer getRightTableViewer() {
		return rightTable;
	}

}
