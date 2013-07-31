package org.bonitasoft.studio.condition.ui.quickfix;

import org.bonitasoft.studio.condition.validation.ConditionModelJavaValidator;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

/**
 * @author Maxence Raoux
 * 
 */
public class ConditionModelQuickfixProvider extends DefaultQuickfixProvider {

	@Fix(ConditionModelJavaValidator.INVALID_EQUALITY_SIGN)
	public void fixInvalidEqualitySign(final Issue issue, IssueResolutionAcceptor acceptor) {
		
		acceptor.accept(issue, "Replace \"=\" by \"==\"", "", PicsConstants.edit, new IModification() {
			@Override
			public void apply(IModificationContext context) throws Exception {
				//Only test
				IXtextDocument d = context.getXtextDocument();
				System.out.println("Document : " + d);
			}
		});
	}

}
