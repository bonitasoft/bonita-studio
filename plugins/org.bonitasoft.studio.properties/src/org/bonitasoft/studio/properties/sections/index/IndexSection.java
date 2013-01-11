package org.bonitasoft.studio.properties.sections.index;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class IndexSection extends AbstractBonitaDescriptionSection{


	private final int MAX_LINES = 5;
	private TabbedPropertySheetPage aTabbedPropertySheetPage;
	private List<ExpressionViewer> nameViewers;
	private List<ExpressionViewer> valueViewers;
	private EMFDataBindingContext context ;
	private TransactionalEditingDomain editingDomain;
	private Pool pool;


	@Override
	public void createControls(Composite parent,
		TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		this.aTabbedPropertySheetPage = aTabbedPropertySheetPage;
		
		createAllLines(parent);
		refreshBindings();
	}

	protected void createAllLines(Composite parent){
		nameViewers = new ArrayList<ExpressionViewer>(MAX_LINES);
		valueViewers = new ArrayList<ExpressionViewer>(MAX_LINES);
		final TabbedPropertySheetWidgetFactory widgetFactory = aTabbedPropertySheetPage.getWidgetFactory();
		Composite lineComposite = widgetFactory.createComposite(parent);
		lineComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		lineComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());
		Label name = widgetFactory.createLabel(lineComposite, Messages.indexName);
		name.setLayoutData(GridDataFactory.fillDefaults().indent(16,0).create());
		Label value = widgetFactory.createLabel(lineComposite, Messages.indexValue);
		value.setLayoutData(GridDataFactory.fillDefaults().indent(16,0).create());
		for (int i=0;i<MAX_LINES;i++){
			createLine(lineComposite, widgetFactory);
		}

	}

	protected void createLine(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory){
		ExpressionViewer nameViewer = new ExpressionViewer(parent, SWT.BORDER, widgetFactory, ProcessPackage.Literals.SEARCH_INDEX__NAME);
		nameViewer.getTextControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(16,0).hint(100,0).create());
		nameViewers.add(nameViewer);
		ExpressionViewer valueViewer = new ExpressionViewer(parent, SWT.BORDER,widgetFactory, ProcessPackage.Literals.SEARCH_INDEX__VALUE);
		valueViewers.add(valueViewer);
		valueViewer.getTextControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(16,0).hint(100,0).create());

	}

	protected void refreshBindings(){
		if(context != null){
			context.dispose();
		}
		context = new EMFDataBindingContext();
		if (pool!=null){
			for (SearchIndex searchIndex : pool.getSearchIndexes()){
					int i = pool.getSearchIndexes().indexOf(searchIndex);
					final ExpressionViewer nameViewer = nameViewers.get(i);
					nameViewer.setContext(getEObject());
					nameViewer.setInput(searchIndex);
					nameViewer.setEditingDomain(editingDomain);
				    nameViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{  ExpressionConstants.CONSTANT_TYPE})); 
					context.bindValue(ViewerProperties.singleSelection().observe(nameViewer),EMFEditProperties.value(editingDomain, ProcessPackage.Literals.SEARCH_INDEX__NAME).observe(searchIndex));
					
					final ExpressionViewer valueViewer = valueViewers.get(i);
					valueViewer.setContext(getEObject());
					valueViewer.setInput(searchIndex);
					valueViewer.setEditingDomain(editingDomain);
					valueViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,ExpressionConstants.VARIABLE_TYPE,ExpressionConstants.SCRIPT_TYPE,ExpressionConstants.PARAMETER_TYPE}));
					context.bindValue(ViewerProperties.singleSelection().observe(valueViewer),EMFEditProperties.value(editingDomain, ProcessPackage.Literals.SEARCH_INDEX__VALUE).observe(searchIndex));
			}
		}
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		super.refresh();
		refreshBindings();
	}

	@Override
	public String getSectionDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
	 */
	@Override
	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	@Override
	public void setEObject(EObject object) {
		pool = (Pool)object;
		refreshBindings();
	}

	@Override
	public void dispose() {
		if(context != null){
			context.dispose();
		}
	}


}
