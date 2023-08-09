/**
 * 
 */
package org.mycompany.connector;

import java.util.List;
import org.bonitasoft.engine.connector.ConnectorValidationException;
import org.bonitasoft.engine.filter.UserFilterException;

/**
*The actor filter execution will follow the steps
* 1 - setInputParameters() --> the actor filter receives input parameters values
* 2 - validateInputParameters() --> the actor filter can validate input parameters values
* 3 - filter(final String actorName) --> execute the user filter
* 4 - shouldAutoAssignTaskIfSingleResult() --> auto-assign the task if filter returns a single result
*/
public class MyActorFilterImpl extends AbstractMyActorFilterImpl {

	@Override
	public void validateInputParameters() throws ConnectorValidationException {
		//TODO validate input parameters here 
	
	}

	@Override
	public List<Long> filter(final String actorName) throws UserFilterException {
		//TODO execute the user filter here
		//The method must return a list of user id's
		//you can use getApiAccessor() and getExecutionContext()
		return null;
	
	}

	@Override
	public boolean shouldAutoAssignTaskIfSingleResult() {
		// If this method returns true, the step will be assigned to 
		//the user if there is only one result returned by the filter method
		return super.shouldAutoAssignTaskIfSingleResult();
	
	}

}
