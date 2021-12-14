package org.bonitasoft.studio.designer.core.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateUIDArtifactOperation.ArtifactyType;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class CreateUIDArtifactOperationTest {

    private static final String TYPE = "type";
    private static final String ROWS = "rows";

    private RepositoryAccessor repositoryAccessor;
    private PageDesignerURLFactory pageDesignerURLFactory;
    private CreatePageOperation createPageOperation;
    private CreateLayoutOperation createLayoutOperation;
    private CreateFormOperation createFormOperation;

    @Before
    public void init() {
        repositoryAccessor = mock(RepositoryAccessor.class);
        pageDesignerURLFactory = mock(PageDesignerURLFactory.class);
        createPageOperation = new CreatePageOperation(pageDesignerURLFactory, repositoryAccessor);
        createLayoutOperation = new CreateLayoutOperation(pageDesignerURLFactory, repositoryAccessor);
        createFormOperation = new CreateFormOperation(pageDesignerURLFactory, repositoryAccessor);
    }

    @Test
    public void should_create_request_body_according_to_type() throws Exception {
        validateJsonBodyForPages();
        validateJsonBodyForForms();
        validateJsonBodyForLayouts();
    }

    private void validateJsonBodyForPages() throws Exception {
        Map<String, Object> pageBody = createPageOperation.createBody();
        assertThat(pageBody.get(TYPE)).isEqualTo(ArtifactyType.PAGE);
        List rows = (List) pageBody.get(ROWS);
        assertThat(rows.size()).isEqualTo(1);
        assertThat((List) rows.get(0)).isEmpty();
    }

    private void validateJsonBodyForForms() throws Exception {
        Map<String, Object> pageBody = createFormOperation.createBody();
        assertThat(pageBody.get(TYPE)).isEqualTo(ArtifactyType.FORM);
        List rows = (List) pageBody.get(ROWS);
        assertThat(rows.size()).isEqualTo(1);
        assertThat((List) rows.get(0)).isEmpty();
    }

    private void validateJsonBodyForLayouts() throws Exception {
        Map<String, Object> pageBody = createLayoutOperation.createBody();
        assertThat(pageBody.get(TYPE)).isEqualTo(ArtifactyType.LAYOUT);
        List rows = (List) pageBody.get(ROWS);
        assertThat(rows.size()).isEqualTo(1);
        assertThat((List) rows.get(0)).isEmpty();
    }

}
