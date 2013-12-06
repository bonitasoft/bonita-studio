package org.bonitasoft.studio.common.refactoring;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Correlation;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlowTransition;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.IContentChangeListener;
import org.eclipse.compare.IContentChangeNotifier;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.DiffTreeViewer;
import org.eclipse.compare.structuremergeviewer.Differencer;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

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


	public BonitaCompareEditorInput(CompareConfiguration configuration,List<Expression> oldExpressions,List<Expression> newExpressions) {
		super(configuration);
		this.oldExpressions = oldExpressions;
		this.newExpressions = newExpressions;
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
		super.setTitle(Messages.refactorTitle);
	}




	@Override
	public Viewer createDiffViewer(Composite parent) {
		viewer = new DiffTreeViewer(parent,getCompareConfiguration());
		viewer.addOpenListener(new IOpenListener() {

			@Override
			public void open(OpenEvent event) {
				IStructuredSelection sel= (IStructuredSelection) event.getSelection();
				Object obj= sel.getFirstElement();

			}
		});
		viewer.setFilters(getFilters());
		viewer.setInput(root);
		viewer.setContentProvider(new BonitaScriptRefactoringContentProvider());
		return viewer;
	}


	@Override
	public String getTitle() {
		return Messages.refactorTitle;
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
			CompareScript lefts = new CompareScript(oldExpression.getName(),oldExpression);
			lefts.setImage(adapterFactoryLabelProvider.getImage(oldExpression));
			final Expression newExpression = newExpressions.get(i);

			final CompareScript rights = new CompareScript(newExpression.getName(),newExpression);
			rights.addContentChangeListener(new IContentChangeListener() {

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
			rights.setImage(adapterFactoryLabelProvider.getImage(newExpression));
			DiffNode leaf = new DiffNode(null,Differencer.CHANGE,null,lefts,rights);
			final DiffNode poolNode = buildPathNodes(oldExpression.eContainer(),leaf);
			poolNode.setParent(root);
			root.add(poolNode);
		}
	}

	private DiffNode buildPathNodes(EObject container,DiffNode node){
		DiffNode parentNode = new DiffNode(Differencer.NO_CHANGE);
		node.setParent(parentNode);
		parentNode.add(node);
		CompareScript ancestor =null;
		if (container instanceof SearchIndex){
			Expression expr = ExpressionHelper.createConstantExpression("SearchIndex", String.class.getName());
			expr.setName("SearchIndex");
			ancestor = new CompareScript(expr.getName()+" "+((SearchIndex)container).getName().getName(),expr);
		} else {
			if (container instanceof Parameter){
				Expression expr = ExpressionHelper.createConstantExpression("Parameter", String.class.getName());
				expr.setName("Parameter");
				ancestor = new CompareScript(expr.getName()+" "+((Parameter)container).getName(),expr);
			}
			else {
				if (container instanceof ConnectorParameter){
					String name="Connector parameter";
					Expression expr = ExpressionHelper.createConstantExpression(name,String.class.getName());
					expr.setName(name);
					ancestor = new CompareScript(expr.getName(),expr);
				} else {
					if (container instanceof Operation){
						Expression expr = ExpressionHelper.createConstantExpression("Operation", String.class.getName());
						expr.setName("Operation");
						ancestor = new CompareScript(expr.getName(),expr);
					} else {
						if (container instanceof Expression){
							Expression expr = ExpressionHelper.createConstantExpression("Expression", String.class.getName());
							expr.setName("Expression");
							ancestor = new CompareScript(expr.getName(),expr);
						} else {
							if (container instanceof ListExpression){
								Expression expr = ExpressionHelper.createConstantExpression("List of Expression", String.class.getName());
								expr.setName("List of Expression");
								ancestor = new CompareScript(expr.getName(),expr);
							} else {
								if (container instanceof TableExpression){
									Expression expr = ExpressionHelper.createConstantExpression("Table of Expression", String.class.getName());
									expr.setName("Table of Expression");
									ancestor = new CompareScript(expr.getName(),expr);
								} else {
									if (container instanceof Validator){
										Expression expr = ExpressionHelper.createConstantExpression("Validator", String.class.getName());
										expr.setName("Validator");
										ancestor = new CompareScript(expr.getName()+" "+((Validator)container).getName(),expr);
									} else {
										if (container instanceof PageFlowTransition){
											Expression expr = ExpressionHelper.createConstantExpression("Page flow transition", String.class.getName());
											expr.setName("Page flow transition");
											ancestor = new CompareScript(expr.getName(),expr);
										} else {
											if (container instanceof Correlation){
												Expression expr = ExpressionHelper.createConstantExpression("Correlation", String.class.getName());
												expr.setName("Correlation");
												ancestor = new CompareScript(expr.getName(),expr);
											}
											if (container instanceof Element){
												String name=adapterFactoryLabelProvider.getText(container);
												Expression expr = ExpressionHelper.createConstantExpression(name,String.class.getName());
												expr.setName(name);
												ancestor = new CompareScript(expr.getName(),expr);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		ancestor.setImage(adapterFactoryLabelProvider.getImage(container));
		parentNode.setAncestor(ancestor);
		if (container instanceof Pool){
			return parentNode;
		}
		if (container instanceof ConnectorParameter){
			return buildPathNodes(container.eContainer().eContainer(), parentNode);
		}
		return buildPathNodes(container.eContainer(),parentNode);
	}

	@Override
	public void saveChanges(IProgressMonitor monitor) throws CoreException {
		super.saveChanges(monitor);
		for (int i=0; i<oldExpressions.size();i++){
			if (!oldExpressions.get(i).getContent().equals(newExpressions.get(i).getContent())){
				cc.append(SetCommand.create(domain,oldExpressions.get(i) , ExpressionPackage.Literals.EXPRESSION__CONTENT, newExpressions.get(i).getContent()));
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
