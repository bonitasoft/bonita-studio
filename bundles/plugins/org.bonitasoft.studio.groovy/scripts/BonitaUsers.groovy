import org.bonitasoft.engine.api.APIAccessor;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.ContactData;

/**
 *
 * @deprecated This class will be removed in future Bonita version, you may replace its usage using the 
 * provided code templates propsals from the Script editor.
 * @since 7.12.0
 */
@Deprecated
public class BonitaUsers {

	/**
	*@param apiAccessor, the current APIAccessor
	*@param taskAssigneeId, a user id represented by a long.
	*@return the User identified by its id
	**/
    @Deprecated
	public static User getUser(APIAccessor apiAccessor,long taskAssigneeId){
		return apiAccessor.getIdentityAPI().getUser(taskAssigneeId);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param taskAssigneeId, a user id represented by a long.
	*@return the user professional ContactData
	**/
    @Deprecated
	public static ContactData getUserProfessionalContactInfo(APIAccessor apiAccessor,long taskAssigneeId){
		return apiAccessor.getIdentityAPI().getUserContactData(taskAssigneeId, false);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator manager User
	**/
    @Deprecated
	public static User getProcessInstanceInitiatorManager(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		long managerId = apiAccessor.getIdentityAPI().getUser(initiatorId).getManagerUserId();
 		return apiAccessor.getIdentityAPI().getUser(managerId);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator manager personal ContactData
	**/
    @Deprecated
	public static ContactData getProcessInstanceInitiatorManagerPersonalContactInfo(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		long managerId = apiAccessor.getIdentityAPI().getUser(initiatorId).getManagerUserId();
 		return apiAccessor.getIdentityAPI().getUserContactData(managerId, true);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator manager professional ContactData
	**/
    @Deprecated
	public static ContactData getProcessInstanceInitiatorManagerProfessionalContactInfo(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		long managerId = apiAccessor.getIdentityAPI().getUser(initiatorId).getManagerUserId();
 		return apiAccessor.getIdentityAPI().getUserContactData(managerId, false);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator User
	**/
    @Deprecated
	public static User getProcessInstanceInitiator(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUser(initiatorId);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator personal ContactData
	**/
    @Deprecated
	public static ContactData getProcessInstanceInitiatorPersonalContactInfo(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUserContactData(initiatorId, true);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator professional ContactData
	**/
    @Deprecated
	public static ContactData getProcessInstanceInitiatorProfessionalContactInfo(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUserContactData(initiatorId, false);
	}
	
}