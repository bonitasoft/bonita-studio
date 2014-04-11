package org.bonitasoft.studio.common.refactoring;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.IContentChangeListener;
import org.eclipse.compare.IContentChangeNotifier;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.DiffTreeViewer;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.IDiffElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class BonitaCompareEditorInput extends CompareEditorInput{


	private CompoundCommand cc;
	private EditingDomain domain;
	private List<Expression> newExpressions;
	private List<Expression> oldExpressions;
	private boolean status = false;
	private DiffNode root ;
	private DiffTreeViewer viewer;
	private final  ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	private final AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
	private int operation;
	private String elementName;
	private String newName;


	public BonitaCompareEditorInput(CompareConfiguration configuration,List<Expression> oldExpressions,List<Expression> newExpressions,int operation,String elementName,String newName) {
		super(configuration);
		this.oldExpressions = oldExpressions;
		this.newExpressions = newExpressions;
		this.elementName = elementName;
		this.operation = operation;
		this.newName =newName;
		root = new DiffNode(Differencer.NO_CHANGE) {
			public boolean hasChildren(){
				return true;
			}
		};


	}

	@Override
	protected Object prepareInput(IProgressMonitor arg0)
			throws InvocationTargetException, InterruptedException {
		setDirty(true);
		if (!oldExpressions.isEmpty() && !newExpressions.isEmpty() && oldExpressions.size()==newExpressions.size()){
			buildTree();
			return root;
		}
		return null;

	}

	@Override
	public void setTitle(String title) {
		if (operation==BonitaGroovyRefactoringAction.REFACTOR_OPERATION){
			super.setTitle(Messages.bind(Messages.refactorTitle,elementName));
		} else {
			super.setTitle(Messages.bind(Messages.removeTitle,elementName));
		}
	}

	@Override
	public void setStatusMessage(String message) {
		super.setStatusMessage(message);
	}

	@Override
	public Control createContents(Composite parent) {
		CLabel label = new CLabel(parent,SWT.NONE);
		if (operation==BonitaGroovyRefactoringAction.REFACTOR_OPERATION) {
			label.setText(Messages.bind(Messages.reviewChangesMessageRefactoring,elementName,newName));
		}
		else{
			label.setText(Messages.bind(Messages.reviewChangesMessageRemoving,elementName));
		}
		label.setImage(Display.getCurrent().getSystemImage(SWT.ICON_WARNING));
		return super.createContents(parent);
	}

	@Override
	public Viewer createDiffViewer(Composite parent) {
		viewer = new DiffTreeViewer(parent,getCompareConfiguration());
		viewer.addOpenListener(new IOpenListener() {

			@Override
			public void open(OpenEvent event) {
				IStructuredSelection sel= (IStructuredSelection) event.getSelection();
				DiffNode obj=(DiffNode) sel.getFirstElement();
				obj.setDontExpand(false);
			}
		});
		viewer.setFilters(getFilters());
		viewer.setLabelProvider(new DiffTreeLabelProvider());
		viewer.setContentProvider(new BonitaScriptRefactoringContentProvider());
		viewer.setInput(root);
		viewer.expandAll();

		return viewer;
	}


	@Override
	public String getTitle() {
		if (operation==BonitaGroovyRefactoringAction.REFACTOR_OPERATION){
			return Messages.bind(Messages.refactorTitle,elementName);
		} else {
			return Messages.bind(Messages.removeTitle,elementName);
		}
	}


	public DiffTreeViewer getViewer(){
		return viewer;
	}

	public DiffNode getRoot() {
		return root;
	}

	public void setRoot(DiffNode root) {
		this.root = root;
	}

	private ViewerFilter[] getFilters() {
		return new ViewerFilter[] { new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return true;
			}

		}
		};
	}


	@Override
	public boolean canRunAsJob() {
		return true;
	}


	public void setCompoundCommand(CompoundCommand cc,EditingDomain domain){
		this.cc=cc;
		this.domain=domain;
	}


	public void buildTree(){
		for (int i=0;i<oldExpressions.size();i++){
			final Expression oldExpression = oldExpressions.get(i);
			CompareScript left = new CompareScript(oldExpression.getName(),oldExpression);
			left.setElement(oldExpression);
			left.setImage(adapterFactoryLabelProvider.getImage(oldExpression));
			final Expression newExpression = newExpressions.get(i);

			final CompareScript right = new CompareScript(newExpression.getName(),newExpression);
			right.setElement(newExpression);
			right.addContentChangeListener(new IContentChangeListener() {

				@Override
				public void contentChanged(IContentChangeNotifier compareScript) {
					if (compareScript instanceof CompareScript){
						setDirty(true);
						if (getViewer() == null || getViewer().getControl().isDisposed())
							return;
						getViewer().refresh(true);
					}
				}
			});
			right.setImage(adapterFactoryLabelProvider.getImage(newExpression));
			DiffNode leaf = new DiffNode(null,Differencer.CHANGE,null,left,right);
			final DiffNode poolNode = buildPathNodes(oldExpression.eContainer(),leaf);
			if (((CompareScript)poolNode.getAncestor()).getElement()instanceof Pool && root.getChildren().length==0){
				root.add(poolNode);
			}
		}
	}

	private DiffNode buildPathNodes(EObject container,DiffNode node){
		DiffNode parentNode = new DiffNode(Differencer.NO_CHANGE);
		node.setParent(parentNode);
		parentNode.add(node);
		CompareScript ancestor =null;
		String name=adapterFactoryLabelProvider.getText(container);
		Expression expr = ExpressionHelper.createConstantExpression(name,String.class.getName());
		expr.setName(name);
		ancestor = new CompareScript(expr.getName(),expr);
		ancestor.setElement(container);
		ancestor.setImage(adapterFactoryLabelProvider.getImage(container));
		parentNode.setAncestor(ancestor);
		
		if (insertParentNode(parentNode)){
			return parentNode;
		}
		if (container instanceof Pool){
			return parentNode;
		}
		if (container instanceof ConnectorParameter){
			return buildPathNodes(container.eContainer().eContainer(), parentNode);
		}
		return buildPathNodes(container.eContainer(),parentNode);
	}

	//	private void insertNode(DiffNode nodeToInsert){
	//		for (IDiffElement node:root.getChildren()){
	//			DiffNode diffNode = (DiffNode)node;
	//			if (diffNode.getAncestor().equals(ancestor.getName())){
	//				return diffNode;
	//			} else {
	//				if (diffNode.hasChildren()){
	//					return findNodeWithSameAncestor((CompareScript)diffNode.getAncestor());
	//				}
	//			}
	//		}
	//		
	//		return null;
	//	}

	private void getAllNodes(List<DiffNode> nodes,DiffNode root){
		if (root.hasChildren()){
			nodes.addAll((Collection<? extends DiffNode>) Arrays.asList(root.getChildren()));
			for (IDiffElement child:root.getChildren()){
				getAllNodes(nodes,(DiffNode)child);
			}
		}
				
	}
	
	private boolean insertParentNode(DiffNode nodeToInsert){
		List<DiffNode>nodes = new ArrayList<DiffNode>();
		getAllNodes(nodes, root);
		for (DiffNode node:nodes){
			if (node.getAncestor()!=null && ((CompareScript)node.getAncestor()).getElement().equals(((CompareScript)nodeToInsert.getAncestor()).getElement())){
				addChildrenToParent(node,nodeToInsert.getChildren());
				return true;
			}
		}
		return false;
	}
	
	private void addChildrenToParent(DiffNode parent,IDiffElement[] children){
		for (IDiffElement child : children){
			parent.add(child);
		}
	}

	@Override
	public void saveChanges(IProgressMonitor monitor) throws CoreException {
		super.saveChanges(monitor);
		for (int i=0; i<oldExpressions.size();i++){
			if (!oldExpressions.get(i).getContent().equals(newExpressions.get(i).getContent())){
				cc.append(SetCommand.create(domain,oldExpressions.get(i) , ExpressionPackage.Literals.EXPRESSION__CONTENT, newExpressions.get(i).getContent()));
				if (ExpressionConstants.CONDITION_TYPE.equals(oldExpressions.get(i).getType())){
					cc.append(SetCommand.create(domain, oldExpressions.get(i),ExpressionPackage.Literals.EXPRESSION__NAME, newExpressions.get(i).getContent()));
				}
			}
		}
		status = true;
	}




	public boolean getStatus(){
		return status;
	}

	@Override
	public boolean isDirty() {
		return true;
	}


	@Override
	public Object getCompareResult() {
		return super.getCompareResult();
	}

}
