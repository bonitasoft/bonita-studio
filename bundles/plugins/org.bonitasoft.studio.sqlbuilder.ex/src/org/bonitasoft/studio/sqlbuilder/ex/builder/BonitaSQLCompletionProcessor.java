/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
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
package org.bonitasoft.studio.sqlbuilder.ex.builder;

import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.IDBContext;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLCompletionEngine;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLCompletionProcessor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLEditorDocumentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLPartitionScanner;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaSQLCompletionProcessor extends SQLCompletionProcessor  implements IContentAssistProcessor {


	private char[] fProposalAutoActivationSet;

	/**
	 * Simple content assist tip closer. The tip is valid in a range of 5
	 * characters around its popup location.
	 */
	protected static class Validator implements IContextInformationValidator, IContextInformationPresenter {

		protected int fInstallOffset;

		/**
		 * @see IContextInformationValidator#isContextInformationValid(int)
		 */
		public boolean isContextInformationValid(int offset) {
			return Math.abs(fInstallOffset - offset) < 5;
		}

		/**
		 * @see IContextInformationValidator#install(IContextInformation,
		 *      ITextViewer, int)
		 */
		public void install(IContextInformation info, ITextViewer viewer, int offset) {
			fInstallOffset = offset;
		}

		public boolean updatePresentation(int position, TextPresentation presentation) {
			return true;
		}
	};

	protected IContextInformationValidator fValidator = new Validator();
	private IDBContext fDBContext = null;
	private SQLCompletionEngine fCompletionEngine = null;  // RATLC01136221 bgp 10Jan2007
	private BonitaSQLCompletionProposalFactory fProposalFactory = null;


	/**
	 * Constructs an instance of this class.  This is the default constructor.
	 * @param textOrDataFactory 
	 */
	public BonitaSQLCompletionProcessor(List<Expression> filteredExpressions) {
		super();
		/* Define the trigger char for content assist. */
		char[] completionChars = { '.' };
		setCompletionProposalAutoActivationCharacters(completionChars);

		fCompletionEngine = new BonitaSQLCompletionEngine();
		fProposalFactory = new BonitaSQLCompletionProposalFactory(filteredExpressions);
		((BonitaSQLCompletionEngine)fCompletionEngine).setProposalFactory(fProposalFactory);
	}

	// RATLC01136221 bgp 10Jan2007 - new method
	/**
	 * Returns a list of proposed content completions based on the specified
	 * offset given SQL statement.
	 * 
	 * @param stmt the statement for which proposals are needed
	 * @param stmtOffset offset within the statement where proposals are needed
	 * @return an array of content assist proposals
	 */
	public ICompletionProposal[] computeCompletionProposals(String stmt, int stmtOffset) {
		IDocument doc = null;

		/* Create a document to contain the statement.*/
		try {
			doc = SQLEditorDocumentProvider.createDocument(stmt);
		} catch (CoreException e) {
			// TODO: handle this exception better
			e.printStackTrace();
		}

		return computeCompletionProposals(doc, stmtOffset);
	}

	/**
	 * Returns a list of proposed content completions based on the specified
	 * offset within the document associated with the given text viewer.
	 * 
	 * @param viewer the viewer whose document is used to compute the proposals
	 * @param docOffset the offset within the document where content assist is needed
	 * @return an array of content assist proposals
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int docOffset) {
		IDocument doc = viewer.getDocument();
		return computeCompletionProposals(doc, docOffset);
	}

	/**
	 * Returns a list of proposed content completions based on the specified
	 * offset within the given document.
	 * 
	 * @param doc the current document containing the SQL statement
	 * @param docOffset the offset within the document where content assist is needed
	 * @return an array of content assist proposals
	 */
	public ICompletionProposal[] computeCompletionProposals(IDocument doc, int docOffset) {

		ICompletionProposal[] proposalArray = null;

		try {
			ITypedRegion partition = null;

			if (docOffset > 0) {
				if (doc.getChar(docOffset - 1) == ';')
					partition = doc.getPartition(docOffset);
				else
					// for incomplete statement.
					partition = doc.getPartition(docOffset - 1);
			}
			else
				partition = doc.getPartition(docOffset);

			ICompletionProposal[] dbProposalArray = fCompletionEngine.computeDBProposals(doc, partition, docOffset, getDBContext());
			ICompletionProposal[] syntaxProposalArray = fCompletionEngine.computeSyntaxProposals(doc, partition, docOffset );
			ICompletionProposal[] bonitaProposalArray = ((BonitaSQLCompletionEngine)fCompletionEngine).computeBonitaProposals(doc, partition, docOffset);
			
			int dbProposalArrayLength = 0;
			if (dbProposalArray != null) {
				dbProposalArrayLength = dbProposalArray.length;
			}
			int syntaxProposalArrayLength = 0;
			if (syntaxProposalArray != null) {
				syntaxProposalArrayLength = syntaxProposalArray.length;
			}
			int bonitaProposalArrayLength = 0;
			if (bonitaProposalArray != null) {
				bonitaProposalArrayLength = bonitaProposalArray.length;
			}

			/* Create the result proposal array by combining the DB and syntax proposals. */
			proposalArray = new ICompletionProposal[ dbProposalArrayLength + syntaxProposalArrayLength +bonitaProposalArrayLength];
			int proposalArrayIndex = 0;
			for (int i=0; i < bonitaProposalArrayLength; i++) {
				proposalArray[proposalArrayIndex] = bonitaProposalArray[i];
				proposalArrayIndex++;
			}     
			for (int i=0; i < dbProposalArrayLength; i++) {
				proposalArray[proposalArrayIndex] = dbProposalArray[i];
				proposalArrayIndex++;
			}
			for (int i=0; i < syntaxProposalArrayLength; i++) {
				proposalArray[proposalArrayIndex] = syntaxProposalArray[i];
				proposalArrayIndex++;
			}     
		}
		catch (BadLocationException x) {
		}

		return proposalArray;
	}

	/**
	 * Returns a list of content assist tips based on the specified location
	 * within the document associated with the given text viewer.
	 * 
	 * @param viewer the text viewer for which tips are needed
	 * @param docOffset the offset in the document where tips are needed
	 * @return an array of content assist tips
	 */
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int docOffset) {
		IDocument doc = viewer.getDocument();
		return computeContextInformation(doc, docOffset);
	}

	//  RATLC01136221 bgp 10Jan2007 - new method
	/**
	 * Returns a list of content assist tips based on the specified location
	 * within the given statement string.
	 * 
	 * @param stmt the SQL statement for which tips are needed
	 * @param stmtOffset the offset in the statement where tips are needed
	 * @return an array of content assist tips
	 */
	public IContextInformation[] computeContextInformation(String stmt, int stmtOffset) {
		IDocument doc = null;
		try {
			doc = SQLEditorDocumentProvider.createDocument(stmt);
		} catch (CoreException e) {
			// TODO: handle this exception better
			e.printStackTrace();
		}

		return computeContextInformation(doc, stmtOffset);
	}


	/**
	 * Returns a list of content assist tips based on the specified location
	 * within the given document.
	 * 
	 * @param doc the document containing the SQL statement for which tips are needed
	 * @param docOffset the offset within the document where tips are needed
	 * @return an array of content assist tips
	 */
	public IContextInformation[] computeContextInformation(IDocument doc, int docOffset) {
		String partitionId = null;
		String[] proposals = fProposalFactory.getStatementTemplateProposals();
		IContextInformation[] result = null;
		ITypedRegion partition = null;

		try {
			if (docOffset > 0) {
				if (doc.getChar(docOffset - 1) == ';')
					partition = doc.getPartition(docOffset);
				else
					// for incomplete statement.
					partition = doc.getPartition(docOffset - 1);
			}
			else
				partition = doc.getPartition(docOffset);
			partitionId = partition.getType();
		}
		catch (BadLocationException x) {
		}

		if (partitionId == SQLPartitionScanner.SQL_SELECT) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[0]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_INSERT) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[1]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_UPDATE) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[2]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_DELETE) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[3]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_CREATE) {
			if (SQLCompletionEngine.showContextInformation(doc, partition, docOffset, "CREATE ") == true) {
				result = new IContextInformation[1];
				result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[5]);
			}
		}
		else if (partitionId == SQLPartitionScanner.SQL_DROP) {
			if (SQLCompletionEngine.showContextInformation(doc, partition, docOffset, "DROP ") == true) {
				result = new IContextInformation[1];
				result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[10]);
			}
		}
		else if (partitionId == SQLPartitionScanner.SQL_ALTER) {
			if (SQLCompletionEngine.showContextInformation(doc, partition, docOffset, "ALTER ") == true) {
				result = new IContextInformation[1];
				result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[4]);
			}
		}
		else if (partitionId == SQLPartitionScanner.SQL_GRANT) {
			if (SQLCompletionEngine.showContextInformation(doc, partition, docOffset, "GRANT ") == true) {
				result = new IContextInformation[1];
				result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[18]);
			}
		}
		else if (partitionId == SQLPartitionScanner.SQL_REVOKE) {
			if (SQLCompletionEngine.showContextInformation(doc, partition, docOffset, "REVOKE ") == true) {
				result = new IContextInformation[1];
				result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[19]);
			}
		}
		else if (partitionId == SQLPartitionScanner.SQL_COMMIT) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[15]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_ROLLBACK) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[16]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_SET) {
			if (SQLCompletionEngine.showContextInformation(doc, partition, docOffset, "SET ") == true) {
				result = new IContextInformation[1];
				result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[17]);
			}
		}
		else if (partitionId == SQLPartitionScanner.SQL_CONNECT) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[20]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_DISCONNECT) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[21]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_COMMENT_ST) {
			if (SQLCompletionEngine.showContextInformation(doc, partition, docOffset, "COMMENT ON ") == true) {
				result = new IContextInformation[1];
				result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[24]);
			}
		}
		else if (partitionId == SQLPartitionScanner.SQL_CATALOG) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[22]);
		}
		else if (partitionId == SQLPartitionScanner.SQL_UNCATALOG) {
			result = new IContextInformation[1];
			result[0] = new ContextInformation("e.g.", "e.g.: " + proposals[23]);
		}
		else {
			String[] displayString = fProposalFactory.getStatementProposals();
			String[] context = fProposalFactory.getContextInformation();
			result = new IContextInformation[context.length];

			for (int i = 0; i < context.length; i++)
				result[i] = new ContextInformation(displayString[i], "e.g.: " + context[i]);
		}

		return result;
	}

	// [wsdbu00055322] bgp 04May2006
	/**
	 * Gets the SQLCompletionEngine associated with this completion
	 * processor.
	 * 
	 * @return the current completion engine
	 */
	public SQLCompletionEngine getCompletionEngine() {
		return fCompletionEngine;
	}

	/**
	 * Returns a string of characters which when pressed should automatically
	 * display content-assist proposals.
	 * 
	 * @see IContentAssistProcessor.getCompletionProposalAutoActivationCharacters()
	 * @return string of characters
	 */
	public char[] getCompletionProposalAutoActivationCharacters() {
		return fProposalAutoActivationSet;
	}

	/**
	 * Returns a string of characters which when pressed should automatically
	 * display a content-assist tip.
	 * 
	 * @return string of characters
	 */
	public char[] getContextInformationAutoActivationCharacters() {
		return new char[] { '#' };
	}

	/**
	 * Returns a delegate used to determine when a displayed tip should be
	 * dismissed.
	 * 
	 * @return a tip closer
	 */
	public IContextInformationValidator getContextInformationValidator() {
		return fValidator;
	}

	/**
	 * Gets the current DB context object.
	 * @return the current DB context object
	 */
	public IDBContext getDBContext() {
		return fDBContext;
	}

	/**
	 * Returns the reason why the content assist processor was unable to produce
	 * any proposals or tips.
	 * 
	 * @return an error message or null if no error occurred
	 */
	public String getErrorMessage() {
		return null;
	}

	// [wsdbu00055322] bgp 04May2006
	/**
	 * Sets the SQLCompletionEngine object for this processor to the given object.
	 * 
	 * @param completionEngine the completion engine to use
	 */
	public void setCompletionEngine(SQLCompletionEngine completionEngine) {
		fCompletionEngine = completionEngine;
	}

	/**
	 * Sets this processor's set of characters triggering the activation of the
	 * completion proposal computation.
	 * 
	 * @param activationSet the activation set to use
	 */
	public void setCompletionProposalAutoActivationCharacters(char[] activationSet) {
		fProposalAutoActivationSet = activationSet;
	}

	/**
	 * Sets the DB context object to the given object.
	 * @param newDBContext the DB context object to set
	 */
	public void setDBContext(IDBContext newDBContext) {
		fDBContext = newDBContext;
	}



}
