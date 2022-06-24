/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.editor.editor.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.junit.Test;

public class IndexableFieldFilterTest {

    private IndexableFieldFilter filter = new IndexableFieldFilter();

    @Test
    public void should_filter_multiple_attributes() throws Exception {
        assertThat(filter.isIndexableField(new SimpleFieldBuilder().withCollection(true).create())).isFalse();
        assertThat(filter.isIndexableField(new SimpleFieldBuilder().withCollection(false).create())).isTrue();
    }

    @Test
    public void should_filter_text_attributes() throws Exception {
        assertThat(filter.isIndexableField(new SimpleFieldBuilder().withType(FieldType.TEXT).create())).isFalse();
        assertThat(filter.isIndexableField(new SimpleFieldBuilder().withType(FieldType.BOOLEAN).create())).isTrue();
    }

    @Test
    public void should_filter_attributes_with_null_references() throws Exception {
        assertThat(filter.isIndexableField(new RelationFieldBuilder().withReference(null).create())).isFalse();
        assertThat(filter.isIndexableField(new RelationFieldBuilder().withReference(mock(BusinessObject.class)).create()))
                .isTrue();
    }

}
