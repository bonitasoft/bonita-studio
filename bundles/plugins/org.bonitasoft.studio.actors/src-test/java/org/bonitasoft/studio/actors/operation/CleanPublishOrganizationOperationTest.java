package org.bonitasoft.studio.actors.operation;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.model.organization.User;
import org.junit.Test;

public class CleanPublishOrganizationOperationTest {

	@Test
	public void should_export_default_attribute_in_xml() throws Exception {
		Organization organization = OrganizationFactory.eINSTANCE.createOrganization();
		User user = OrganizationFactory.eINSTANCE.createUser();
		PasswordType passwordType = OrganizationFactory.eINSTANCE.createPasswordType();
		passwordType.setValue("testbpm");
		user.setPassword(passwordType);
		organization.setUsers( OrganizationFactory.eINSTANCE.createUsers());
		organization.getUsers().getUser().add(user);
		String xml = new CleanPublishOrganizationOperation(organization).toString(organization);
		
		assertThat(xml).contains("<password encrypted=\"true\">testbpm</password>");
	}
	
}
