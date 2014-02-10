package org.bonitasoft.studio.document.ui;


import java.util.Collections;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.document.DocumentPlugin;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.xtext.ui.XtextProjectHelper;

public class DocumentWizard extends Wizard {
	
	private EObject context;
	private Document document;
	
	public DocumentWizard(EObject context){
		super();
		this.context=context;
		this.setWindowTitle(Messages.newDocument);
		setDefaultPageImageDescriptor(Pics.getWizban());
		document = ProcessFactory.eINSTANCE.createDocument();
		Expression mimeTypeExpression = ExpressionHelper.createConstantExpression("", String.class.getName());
		mimeTypeExpression.setReturnTypeFixed(true);
		document.setMimeType(mimeTypeExpression);
		Expression urlExpression = ExpressionHelper.createConstantExpression("", String.class.getName());
		urlExpression.setReturnTypeFixed(true);
		document.setUrl(urlExpression);
	}
	
	
	
	@Override
	public void addPages() {
		addPage(new DocumentWizardPage(context,document));
	}

	@Override
	public boolean performFinish() {
		Pool pool =(Pool) ModelHelper.getParentProcess(context);
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(context);
		editingDomain.getCommandStack().execute(new AddCommand(editingDomain, pool.getDocuments(), document));
		try {
			RepositoryManager
					.getInstance()
					.getCurrentRepository()
					.getProject()
					.build(IncrementalProjectBuilder.FULL_BUILD,
							XtextProjectHelper.BUILDER_ID,
							Collections.<String, String> emptyMap(), null);
		} catch (CoreException e1) {
			BonitaStudioLog.error(e1, DocumentPlugin.PLUGIN_ID);
		}
		return true;
	}
	
	
	public Document getDocument(){
		return document;
	}

}
