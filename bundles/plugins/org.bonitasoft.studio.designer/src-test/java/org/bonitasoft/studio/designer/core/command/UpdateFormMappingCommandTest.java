/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.designer.core.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;

import org.bonitasoft.studio.designer.core.command.UpdateFormMappingCommand;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class UpdateFormMappingCommandTest implements ProcessPackage.Literals {

    private EditingDomain editingDomain;

    @Before
    public void setUp() throws Exception {
        editingDomain = new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory(), new TransactionalCommandStackImpl());
    }

    @Test
    public void should_create_a_command_setting_target_form_and_internal_mapping_type() throws Exception {
        //given
        final FormMapping formMapping = aFormMapping().build();
        final Expression targetForm = anExpression().build();

        //when
        final UpdateFormMappingCommand updateFormMappingCommand = new UpdateFormMappingCommand(editingDomain, formMapping, targetForm);

        //then
        assertThat(updateFormMappingCommand.getCommandList()).extracting("label", "feature", "value").contains(
                tuple("Set", FORM_MAPPING__TYPE, FormMappingType.INTERNAL),
                tuple("Set", FORM_MAPPING__TARGET_FORM, targetForm));
    }

    @Test
    public void should_create_a_command_setting_url_and_url_mapping_type() throws Exception {
        //given
        final FormMapping formMapping = aFormMapping().build();

        //when
        final UpdateFormMappingCommand updateFormMappingCommand = new UpdateFormMappingCommand(editingDomain, formMapping, "http://www.bonitasoft.com");

        //then
        assertThat(updateFormMappingCommand.getCommandList()).extracting("label", "feature", "value").contains(
                tuple("Set", FORM_MAPPING__TYPE, FormMappingType.URL),
                tuple("Set", FORM_MAPPING__URL, "http://www.bonitasoft.com"));
    }
}
