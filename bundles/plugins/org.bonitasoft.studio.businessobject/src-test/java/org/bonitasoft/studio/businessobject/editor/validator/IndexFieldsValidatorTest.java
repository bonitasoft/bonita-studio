package org.bonitasoft.studio.businessobject.editor.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.IndexBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class IndexFieldsValidatorTest {

    static final String FIELD_WITH_TEXT_TYPE = "fieldWithTextType";
    static final String FIELD1 = "field1";
    static final String FIELD_WITH_MULTIPLE_TYPE = "fieldWithMultipleType";
    static final String FIELD_WITH_NULL_REF = "fieldWithNullRef";

    IndexFieldsValidator validator;
    BusinessObject bo;

    @Before
    public void init() {
        validator = new IndexFieldsValidator();
        bo = createBusinessObject("bo");
        bo.getFields().add(createField(FIELD1));
    }

    private BusinessObject createBusinessObject(String name) {
        return new BusinessObjectBuilder()
                .withQualifiedName(String.format("com.%s", name))
                .create();
    }

    public Field createField(String name) {
        return new SimpleFieldBuilder()
                .withName(name)
                .create();
    }

    @Test
    public void should_validate_field_list_is_not_empty() {
        Index index = new IndexBuilder().withName("index").create();
        IStatus status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()))
                .flatExtracting(IStatus::getMessage)
                .contains(String.format(Messages.indexFieldEmptiness, index.getName()));
    }

    @Test
    public void should_validate_unknown_fields() {
        Index index = new IndexBuilder()
                .withName("index")
                .withFieldNames(FIELD1, "unknownField")
                .create();
        bo.getIndexes().add(index);
        IStatus status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()))
                .flatExtracting(IStatus::getMessage)
                .contains(String.format(Messages.indexReferencesUnknownAttributes, index.getName(),
                        Arrays.asList("unknownField").toString()));
    }

    @Test
    public void should_validate_invalid_types() {
        bo.getFields().add(new SimpleFieldBuilder()
                .withName(FIELD_WITH_TEXT_TYPE)
                .withCollection(false)
                .withType(FieldType.TEXT)
                .create());
        bo.getFields().add(new SimpleFieldBuilder()
                .withName(FIELD_WITH_MULTIPLE_TYPE)
                .withCollection(true)
                .withType(FieldType.INTEGER)
                .create());
        bo.getFields().add(new RelationFieldBuilder()
                .withName(FIELD_WITH_NULL_REF)
                .withReference(null)
                .create());
        Index index = new IndexBuilder()
                .withName("index")
                .withFieldNames(FIELD_WITH_TEXT_TYPE, FIELD_WITH_MULTIPLE_TYPE, FIELD_WITH_NULL_REF)
                .create();
        bo.getIndexes().add(index);
        IStatus status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()))
                .flatExtracting(IStatus::getMessage)
                .contains(String.format("%s\n%s\n%s",
                        String.format(Messages.cantUseTextTypeInIndex, FIELD_WITH_TEXT_TYPE),
                        String.format(Messages.cantUseMultipleTypeInIndex, FIELD_WITH_MULTIPLE_TYPE),
                        String.format(Messages.cantHaveNullReferenceInIndex, FIELD_WITH_NULL_REF)));
    }

}
