package org.bonitasoft.studio.designer.core.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateUIDArtifactOperation.ArtifactyType;
import org.json.JSONArray;
import org.json.JSONObject;
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
        JSONObject pageBody = createPageOperation.createBody();
        assertThat(pageBody.get(TYPE)).isEqualTo(ArtifactyType.PAGE);
        JSONArray rows = (JSONArray) pageBody.get(ROWS);
        assertThat(rows.length()).isEqualTo(1);
        assertThat((List) rows.get(0)).isEmpty();
    }

    private void validateJsonBodyForForms() throws Exception {
        JSONObject pageBody = createFormOperation.createBody();
        assertThat(pageBody.get(TYPE)).isEqualTo(ArtifactyType.FORM);
        JSONArray rows = (JSONArray) pageBody.get(ROWS);
        assertThat(rows.length()).isEqualTo(1);
        assertThat((List) rows.get(0)).isEmpty();
    }

    private void validateJsonBodyForLayouts() throws Exception {
        JSONObject pageBody = createLayoutOperation.createBody();
        assertThat(pageBody.get(TYPE)).isEqualTo(ArtifactyType.LAYOUT);
        JSONArray rows = (JSONArray) pageBody.get(ROWS);
        assertThat(rows.length()).isEqualTo(1);
        assertThat((List) rows.get(0)).isEmpty();
    }

}
