package org.bonitasoft.studio.application.ui.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnit44Runner;

@RunWith(MockitoJUnit44Runner.class)
public class SelectArtifactToDeployPageTest {

    public static String DIAGRAM_REPOSITORY_NAME = "Diagrams";
    public static String DIAGRAM_1_NAME = "diagram1";
    public static String DIAGRAM_2_NAME = "diagram2";
    public static String BOM_REPOSITORY_NAME = "Business Data Model";

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    @Spy
    SelectArtifactToDeployPage page;
    List<IRepositoryFileStore> fileStores;
    WritableSet<Object> checkedElementObservable;
    HashSet<IRepositoryFileStore> allCheckedElement;
    List<Object> artifactsToFilter;
    DiagramFileStore diagram1;
    DiagramFileStore diagram2;
    BusinessObjectModelFileStore bom;

    @Before
    public void init() {
        fileStores = initFileStores();
        when(page.getAllFileStores()).thenReturn(fileStores);
        checkedElementObservable = new WritableSet<>();
        allCheckedElement = new HashSet<>();
        artifactsToFilter = new ArrayList<>();
        page.checkedElementsObservable = checkedElementObservable;
        page.allCheckedElements = allCheckedElement;
        page.toFilter = artifactsToFilter;
    }

    @Test
    public void should_filter_file_stores() {
        page.updateArtifactsToFilter("filterAll");
        assertThat(artifactsToFilter).containsAll(fileStores);

        page.updateArtifactsToFilter(DIAGRAM_1_NAME);
        assertThat(artifactsToFilter).hasSize(2);
        assertThat(artifactsToFilter).containsExactlyInAnyOrder(diagram2, bom);

        page.updateArtifactsToFilter("Business");
        assertThat(artifactsToFilter).hasSize(2);
        assertThat(artifactsToFilter).containsExactlyInAnyOrder(diagram1, diagram2);

        page.updateArtifactsToFilter(DIAGRAM_REPOSITORY_NAME);
        assertThat(artifactsToFilter).hasSize(1);
        assertThat(artifactsToFilter).containsExactly(bom);
    }

    @Test
    public void should_merge_set() {
        checkedElementObservable.addAll(fileStores);
        page.mergeSets();
        assertThat(allCheckedElement).containsAll(fileStores);

        // User filter on diagrams -> bom should not be really unchecked
        artifactsToFilter.add(bom);
        checkedElementObservable.remove(bom);
        page.mergeSets();
        assertThat(allCheckedElement).containsAll(fileStores);

        // User still filter on diagrams and remove diagram1 -> diagram1 should really be unchecked
        checkedElementObservable.remove(diagram1);
        page.mergeSets();
        assertThat(allCheckedElement).containsExactlyInAnyOrder(diagram2, bom);

        // User doesn't filter and clear all -> all should really be unchecked
        artifactsToFilter.clear();
        checkedElementObservable.clear();
        page.mergeSets();
        assertThat(allCheckedElement).isEmpty();
    }

    @Test
    public void should_return_visible_checked_elements() {
        checkedElementObservable.addAll(fileStores);
        page.mergeSets();
        artifactsToFilter.add(bom);
        checkedElementObservable.remove(bom);
        checkedElementObservable.remove(diagram2);
        assertThat(page.getElements()).containsExactly(diagram1);
    }

    private List<IRepositoryFileStore> initFileStores() {
        DiagramRepositoryStore diagramRepositoryStore = mock(DiagramRepositoryStore.class);
        IFolder diagramFolder = mock(IFolder.class);
        IFile diagram1file = mock(IFile.class);
        IFile diagram2file = mock(IFile.class);
        IPath diagram1Path = new Path("/home/" + DIAGRAM_1_NAME);
        IPath diagram2Path = new Path("/home/" + DIAGRAM_2_NAME);
        when(diagram1file.getLocation()).thenReturn(diagram1Path);
        when(diagram2file.getLocation()).thenReturn(diagram2Path);
        when(diagramFolder.getFile(DIAGRAM_1_NAME)).thenReturn(diagram1file);
        when(diagramFolder.getFile(DIAGRAM_2_NAME)).thenReturn(diagram2file);
        when(diagramRepositoryStore.getResource()).thenReturn(diagramFolder);
        when(diagramRepositoryStore.getDisplayName()).thenReturn(DIAGRAM_REPOSITORY_NAME);

        diagram1 = new DiagramFileStore(DIAGRAM_1_NAME, diagramRepositoryStore);
        diagram2 = new DiagramFileStore(DIAGRAM_2_NAME, diagramRepositoryStore);

        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> bomRepositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(bomRepositoryStore.getDisplayName()).thenReturn(BOM_REPOSITORY_NAME);
        bom = new BusinessObjectModelFileStore("bom.xml", bomRepositoryStore);

        return Arrays.asList(diagram1, diagram2, bom);
    }

}
