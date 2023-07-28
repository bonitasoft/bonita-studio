/**
 * 
 */
package org.mycompany.connector;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.connector.ConnectorValidationException;

/**
 * This abstract class is generated and should not be modified.
 */
public abstract class AbstractMyCustomConnectorImpl extends AbstractConnector {

	protected final static String ENTRÉE1_INPUT_PARAMETER = "Entrée1";

	protected final java.lang.String getEntrée1() {
		return (java.lang.String) getInputParameter(ENTRÉE1_INPUT_PARAMETER);
	}

	@Override
	public void validateInputParameters() throws ConnectorValidationException {
		try {
			getEntrée1();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("Entrée1 type is invalid");
		}

	}

}
