import org.bonitasoft.engine.api.APIAccessor;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.ContactData;

public class BonitaUsers {

	/**
	*@param apiAccessor, the current APIAccessor
	*@param taskAssigneeId, a user id represented by a long.
	*@return the User identified by its id
	**/
	public static User getUser(APIAccessor apiAccessor,long taskAssigneeId){
		return apiAccessor.getIdentityAPI().getUser(taskAssigneeId);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param taskAssigneeId, a user id represented by a long.
	*@return the user professional ContactData
	**/
	public static ContactData getUserProfessionalContactInfo(APIAccessor apiAccessor,long taskAssigneeId){
		return apiAccessor.getIdentityAPI().getUserContactData(taskAssigneeId, false);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator manager User
	**/
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
	public static User getProcessInstanceInitiator(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUser(initiatorId);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator personal ContactData
	**/
	public static ContactData getProcessInstanceInitiatorPersonalContactInfo(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUserContactData(initiatorId, true);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param processInstanceId, the process instance id represented by a long.
	*@return the process instance initiator professional ContactData
	**/
	public static ContactData getProcessInstanceInitiatorProfessionalContactInfo(APIAccessor apiAccessor,long processInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(processInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUserContactData(initiatorId, false);
	}
	
}