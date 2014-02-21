
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
package org.bonitasoft.studio.data.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.corext.template.java.SignatureUtil;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class JavaExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

	private TableViewer viewer;
	private Expression editorInputExpression;
	private Composite mainComposite;
	private Text typeText;
	private TreeViewer javaTreeviewer;
	private Data data;
	private ITreeContentProvider contentProvider;
	private boolean isPageFlowContext=false;


	@Override
	public Control createExpressionEditor(Composite parent) {
		mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true).create()) ;

		viewer = new TableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL) ;

		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(100, false));
		viewer.getTable().setLayout(layout);
		viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;

		TableViewerColumn columnViewer = new TableViewerColumn(viewer,SWT.NONE) ;
		TableColumn column = columnViewer.getColumn() ;
		column.setText(Messages.name) ;

		TableColumnSorter sorter = new TableColumnSorter(viewer) ;
		sorter.setColumn(column) ;

		viewer.getTable().setHeaderVisible(true);
		viewer.setContentProvider(new ArrayContentProvider()) ;
		viewer.setLabelProvider(new DataStyledTreeLabelProvider()) ;

		viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if(!event.getSelection().isEmpty()){
					javaTreeviewer.getTree().setEnabled(true) ;

					data = (Data) ((IStructuredSelection) event.getSelection()).getFirstElement() ;
					String className = null ;
					if(data instanceof JavaObjectData){
						className = ((JavaObjectData) data).getClassName() ;
					}else if(data.isMultiple()){
						className = List.class.getName() ;
					}
					if(className != null){
						javaTreeviewer.setInput(className);
						javaTreeviewer.expandAll() ;
						javaTreeviewer.getTree().setFocus() ;
						javaTreeviewer.getTree().getShell().layout(true,true ) ;
						JavaExpressionEditor.this.fireSelectionChanged();
					}
				}
			}
		}) ;

		createBrowseJavaObjectForReadExpression(mainComposite) ;
		createReturnTypeComposite(parent) ;

		return mainComposite;
	}

	protected void createReturnTypeComposite(Composite parent) {
		Composite typeComposite = new Composite(parent,SWT.NONE) ;
		typeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
		typeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create()) ;

		Label typeLabel = new Label(typeComposite, SWT.NONE) ;
		typeLabel.setText(Messages.returnType) ;
		typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create()) ;

		typeText = new Text(typeComposite, SWT.BORDER | SWT.READ_ONLY) ;
		typeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create()) ;

	}


	protected void createBrowseJavaObjectForReadExpression(Composite composite) {
		final Composite res = new Composite(composite,SWT.NONE) ;
		res.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
		res.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create()) ;


		final Label label = new Label(res, SWT.NONE);
		label.setText(Messages.browseJava);
		javaTreeviewer = new TreeViewer(res, SWT.SINGLE | SWT.BORDER);
		javaTreeviewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		javaTreeviewer.setLabelProvider(new JavaUILabelProvider() {
			@Override
			public String getText(Object item) {
				if(item instanceof IMethod){
					try {
						return super.getText(item) + " - "+ SignatureUtil.stripSignatureToFQN(((IMethod) item).getReturnType());
					} catch (JavaModelException e) {
						BonitaStudioLog.error(e) ;
						return null ;
					}
				}else{
					return super.getText(item) ;
				}
			}
		});

		javaTreeviewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ITreeSelection selection = (ITreeSelection) event.getSelection();
				if(!selection.isEmpty()){
					JavaExpressionEditor.this.fireSelectionChanged();
					javaTreeviewer.getTree().setFocus() ;
				}
			}

		});

		javaTreeviewer.getTree().setEnabled(false) ;
	}

	private IMethod getJavaSelectionFromContent() {
		String className = null ;
		if(data != null){
			if(data.isMultiple()){
				className = List.class.getName();
			}else if( data instanceof JavaObjectData){
				className = ((JavaObjectData) data).getClassName() ;
			}
			if(className != null){
				String content = editorInputExpression.getContent() ;
				if(content != null){
					IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
					IType type = null;
					try {
						type = ((PojoBrowserContentProvider) getContentProvider()).getType();
						for(IMethod m : type.getMethods()){
							String method = m.getElementName() ;
							if(method.equals(content)){
								return m ;
							}
						}
					} catch (JavaModelException e) {
						BonitaStudioLog.error(e) ;
					}

				}
			}
		}
		return null;
	}

	protected ITreeContentProvider getContentProvider() {
		return contentProvider;
	}

	protected void setContentProvider(ITreeContentProvider provider) {
		contentProvider =  provider ;
	}

	protected String getJavaScript(ITreeSelection selection) {
		StringBuilder builder = new StringBuilder();
		TreePath path = selection.getPaths()[0];
		for (int i = 0; i < path.getSegmentCount(); i++) {
			Object item = path.getSegment(i);
			if (item instanceof IMethod) {
				builder.append(".");
				try {
					builder.append(((IMethod)item).getElementName() + ((IMethod)item).getSignature());
				} catch (JavaModelException e) {
					BonitaStudioLog.error(e) ;
				}
			} else if (item instanceof IField) {
				builder.append(".");
				builder.append(((IField)item).getElementName());
			} else if( item instanceof IType){
				builder.append(((IType)item).getFullyQualifiedName());
			}
		}
		return builder.toString();
	}

	protected void setExpressionIsValid(boolean isValdid) {
	}

	protected String generateJavaAdditionalPath(Data data,ITreeSelection selection) {
		if(selection == null){
			return "";
		}
		TreePath path = selection.getPaths()[0];
		if (path.getSegmentCount() == 1) {
			return "";
		}
		StringBuilder res = new StringBuilder(data.getName());
		res.append(".");
		for (int i = 1; i < path.getSegmentCount() - 1; i++) {
			Object item = path.getSegment(i);
			final IJavaElement iJavaElement = (IJavaElement)item;
			res.append(iJavaElement.getElementName());
			if (iJavaElement.getElementType() == IJavaElement.METHOD) {
				res.append("()");
			}
			res.append(".");
		}
		if (res.length() > 0) {
			res.deleteCharAt(res.length() - 1);
		}
		res.append(BonitaConstants.JAVA_VAR_SEPARATOR);
		Object item = path.getSegment(path.getSegmentCount() - 1);
		res.append(((IJavaElement)item).getElementName());
		return res.toString();
	}


	@Override
	public void bindExpression(EMFDataBindingContext dataBindingContext,EObject context,Expression inputExpression,ViewerFilter[] filters,ExpressionViewer expressionViewer) {

		editorInputExpression = inputExpression ;
		setContentProvider(new PojoBrowserContentProvider()) ;
		javaTreeviewer.setContentProvider(getContentProvider());

		Set<Data> input = new HashSet<Data>() ;
		IExpressionProvider provider = ExpressionEditorService.getInstance().getExpressionProvider(ExpressionConstants.VARIABLE_TYPE) ;
		for(Expression e : provider.getExpressions(context)){
			Data data = (Data) e.getReferencedElements().get(0) ;
			if(data instanceof JavaObjectData || data.isMultiple()){
				input.add(data) ;
			}
		}
		viewer.setInput(input) ;

		IObservableValue contentObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT) ;
		IObservableValue nameObservable = EMFObservables.observeValue( inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME) ;
		final IObservableValue returnTypeObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE) ;
		IObservableValue referenceObservable = EMFObservables.observeValue( inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS) ;

		UpdateValueStrategy selectionToName = new UpdateValueStrategy() ;
		IConverter nameConverter = new Converter(Data.class,String.class){

			@Override
			public Object convert(Object data) {
				if(data instanceof Data){
					return ((Data) data).getName() +" - "+ ((JavaObjectData) data).getClassName()+"#"+editorInputExpression.getContent() ;
				}else if (data instanceof IMethod){
					final JavaObjectData data2 = (JavaObjectData) editorInputExpression.getReferencedElements().get(0);
					return data2.getName() +" - "+ data2.getClassName()+"#"+((IMethod)data).getElementName() ;
				}
				return null;
			}

		};
		selectionToName.setConverter(nameConverter) ;


		UpdateValueStrategy selectionToContent = new UpdateValueStrategy() ;
		IConverter contentConverter = new Converter(Object.class,String.class){

			@Override
			public Object convert(Object value) {
				if(value instanceof IMethod){
					return ((IMethod) value).getElementName();
				}
				Object selection = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
				if( selection instanceof Data ){
					return ((Data) selection).getName();
				}
				return null;
			}

		};
		selectionToContent.setConverter(contentConverter) ;

		UpdateValueStrategy methodToSelection = new UpdateValueStrategy() ;
		IConverter methodToSelectionConverter = new Converter(String.class,IMethod.class){

			@Override
			public Object convert(Object methodName) {
				IType type = ((PojoBrowserContentProvider) getContentProvider()).getType();
				if(type != null){
					try {
						for(IMethod method : type.getMethods()){
							if(method.getElementName().equals(methodName)){
								return method ;
							}
						}
					} catch (JavaModelException e) {

					}
				}
				return null ;
			}

		};
		methodToSelection.setConverter(methodToSelectionConverter);

		UpdateValueStrategy selectionToReturnType = new UpdateValueStrategy() ;
		IConverter returnTypeConverter = new Converter(IType.class,String.class){

			@Override
			public Object convert(Object iType) {
				if(!editorInputExpression.isReturnTypeFixed()){
					if(iType instanceof IMethod){
						String qualifiedType = Object.class.getName();
						try {
							qualifiedType = JavaModelUtil.getResolvedTypeName(Signature.getTypeErasure(((IMethod)iType).getReturnType()), ((IMethod)iType).getDeclaringType());
							if("int".equals(qualifiedType)){
								qualifiedType = Integer.class.getName();
							}else if("boolean".equals(qualifiedType)){
								qualifiedType = Boolean.class.getName();
							}else if("long".equals(qualifiedType)){
								qualifiedType = Long.class.getName();
							}else if("float".equals(qualifiedType)){
								qualifiedType = Float.class.getName();
							}else if("double".equals(qualifiedType)){
								qualifiedType = Double.class.getName();
							}else if("short".equals(qualifiedType)){
								qualifiedType = Short.class.getName();
							}else if("byte".equals(qualifiedType)){
								qualifiedType = Byte.class.getName();
							}else if("E".equals(qualifiedType)){
								qualifiedType = Object.class.getName();
							}else if("V".equals(qualifiedType)){
								qualifiedType = Object.class.getName();
							}
						} catch (Exception e) {
							BonitaStudioLog.error(e) ;
						}
						return qualifiedType;
					}else if( iType instanceof IType){
						return ((IType) iType).getFullyQualifiedName() ;
					}
				}else{
					return returnTypeObservable.getValue();
				}
				return null  ;
			}

		};
		selectionToReturnType.setConverter(returnTypeConverter) ;

		UpdateValueStrategy selectionToReferencedData = new UpdateValueStrategy() ;
		IConverter referenceConverter = new Converter(Data.class,List.class){

			@Override
			public Object convert(Object data) {
				if(data != null){
					return Collections.singletonList(data)  ;
				} else {
					return Collections.emptyList();
				}
			}

		};
		selectionToReferencedData.setConverter(referenceConverter) ;

		UpdateValueStrategy referencedDataToSelection = new UpdateValueStrategy() ;
		IConverter referencetoDataConverter = new Converter(List.class,Data.class){

			@Override
			public Object convert(Object dataList) {
				Data d = ((List<Data>) dataList).get(0);
				Collection<Data> inputData = (Collection<Data>) viewer.getInput();
				for(Data data : inputData){
					if(data.getName().equals(d.getName()) && data.getDataType().getName().equals(d.getDataType().getName())){
						return data ;
					}
				}
				return null ;
			}

		};
		referencedDataToSelection.setConverter(referencetoDataConverter) ;
		dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), referenceObservable,selectionToReferencedData,referencedDataToSelection)  ;
		dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), nameObservable,selectionToName,new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER))  ;
		dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(javaTreeviewer), nameObservable,selectionToName,new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER))  ;
		dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(javaTreeviewer), contentObservable,selectionToContent,methodToSelection)  ;
		dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(javaTreeviewer), returnTypeObservable,selectionToReturnType,new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER))  ;
		dataBindingContext.bindValue(SWTObservables.observeText(typeText, SWT.Modify), returnTypeObservable)  ;
	}




	@Override
	public boolean canFinish() {
		return !viewer.getSelection().isEmpty() && !javaTreeviewer.getSelection().isEmpty() && ((IStructuredSelection) javaTreeviewer.getSelection()).getFirstElement() instanceof IMethod;
	}

	@Override
	public void okPressed() {

	}

	@Override
	public Control getTextControl() {
		return null;
	}

	@Override
	public boolean isPageFlowContext() {
		
		return isPageFlowContext;
	}

	@Override
	public void setIsPageFlowContext(boolean isPageFlowContext) {
		this.isPageFlowContext=isPageFlowContext;
		
	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
	}
}

