/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.themes.tests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.bonitasoft.studio.repository.themes.UserXpFileStore;
import org.bonitasoft.studio.repository.themes.exceptions.ThemeAlreadyExistsException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 * 
 */
public class TestThemeRepository extends TestCase{

    private static final String DEFAULT_APPLICATION_LNF_ID = "Default Application";


	public void testDuplicateLookNFeelFileStore() throws Exception {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final LookNFeelFileStore artifact = repository.getChild(DEFAULT_APPLICATION_LNF_ID);
        assertNotNull(artifact);
        final LookNFeelFileStore duplicateTheme = repository.duplicateFrom("grey2", "grey2Desc", artifact);
        assertEquals("grey2", duplicateTheme.getName());
        assertEquals("grey2Desc", duplicateTheme.getDescription());

        assertEquals(artifact.getBindings(), duplicateTheme.getBindings());
        assertEquals(artifact.getPreviewFilePath(), duplicateTheme.getPreviewFilePath());
        assertEquals(artifact.getAuthor(), duplicateTheme.getAuthor());
        duplicateTheme.delete();
    }

    public void testDuplicateLookNFeelFileStoreWithNullDesc() throws Exception {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final LookNFeelFileStore artifact = repository.getChild(DEFAULT_APPLICATION_LNF_ID);
        assertNotNull(artifact);
        final LookNFeelFileStore duplicateTheme = repository.duplicateFrom("grey2", null, artifact);
        assertEquals("grey2", duplicateTheme.getName());
        assertEquals(null, duplicateTheme.getDescription());

        assertEquals(artifact.getBindings(), duplicateTheme.getBindings());
        assertEquals(artifact.getPreviewFilePath(), duplicateTheme.getPreviewFilePath());
        assertEquals(artifact.getAuthor(), duplicateTheme.getAuthor());
        duplicateTheme.delete();

    }

    public void testDuplicateLookNFeelFileStoreWithNoName() throws ThemeAlreadyExistsException {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final int size = repository.getApplicationLookNFeels().size();
        try {
            final LookNFeelFileStore artifact = repository.getChild(DEFAULT_APPLICATION_LNF_ID);
            assertNotNull(artifact);
            repository.duplicateFrom("", "", artifact);
            fail("should have failed");
        } catch (IllegalArgumentException e){
            //OK
        } finally {
            assertEquals(size, repository.getApplicationLookNFeels().size());
        }
    }

    public void testDuplicateLookNFeelFileStoreWithNullName() throws ThemeAlreadyExistsException {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final int size = repository.getApplicationLookNFeels().size();
        try {
            final LookNFeelFileStore artifact = repository.getChild(DEFAULT_APPLICATION_LNF_ID);
            assertNotNull(artifact);
            repository.duplicateFrom(null, null, artifact);
            fail("should have failed");
        } catch (IllegalArgumentException e){
            //OK
        } finally {
            assertEquals(size, repository.getApplicationLookNFeels().size());
        }
    }

    public void testDuplicateLookNFeelFileStoreWithSameName() throws ThemeAlreadyExistsException {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final int size = repository.getApplicationLookNFeels().size();
        final LookNFeelFileStore artifact = repository.getChild(DEFAULT_APPLICATION_LNF_ID);
        assertNotNull(artifact);
        assertNull(repository.duplicateFrom(DEFAULT_APPLICATION_LNF_ID, "desc", artifact));
        assertEquals(size, repository.getApplicationLookNFeels().size());
    }


    public void testCantRemoveProvidedTheme() throws Exception {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        try {
            final LookNFeelFileStore artifact = repository.getChild(DEFAULT_APPLICATION_LNF_ID);
            assertNotNull(artifact);
            artifact.delete();
        } catch (IllegalArgumentException e){
            //OK
        } finally {
            assertNotNull("grey theme was removed", repository.getChild(DEFAULT_APPLICATION_LNF_ID));
        }
    }

    public void testImportLookNFeelFileStore() throws Exception {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        InputStream stream = this.getClass().getResourceAsStream("myTheme.zip");
        LookNFeelFileStore importInputStream = repository.importInputStream("myTheme.zip", stream);
        importInputStream.load();
        assertTrue(importInputStream instanceof ApplicationLookNFeelFileStore);
        assertNotNull(importInputStream);
        assertEquals("myTheme", importInputStream.getName());
        assertEquals("d", importInputStream.getDescription());
        assertEquals(8, importInputStream.getBindings().size());
        assertEquals(4,importInputStream.getResource().members().length);
        importInputStream.delete();

    }

    public void testReplaceUrlsOfLookNFeelFileStore() throws Exception {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final LookNFeelFileStore createArtifact = repository.duplicateFrom("testgetContentsWithUrl", "", repository.getUserXPLookNFeels().get(0));
        IFolder afolder = createArtifact.getResource().getFolder("afolder");
        afolder.create(false, false, new NullProgressMonitor());
        afolder.getFile("aresource").create(new ByteArrayInputStream("plop".getBytes("UTF-8")), false, new NullProgressMonitor());

        IFile file = createArtifact.getResource().getFile("test.html");//depth = 0
        file.create(new ByteArrayInputStream("src=\"test/\"".getBytes("UTF-8")), false, new NullProgressMonitor());
        file = createArtifact.getResource().getFile("test2.html");//depth = 0
        file.create(new ByteArrayInputStream("src=\"themeResource?theme=testgetContentsWithUrl&location=..%2Ftest%2F\"".getBytes("UTF-8")), false, new NullProgressMonitor());
        IFolder folder = createArtifact.getResource().getFolder("test");
        folder.create(false, true, new NullProgressMonitor());
        file = folder.getFile("test3.html");//depth = 1
        file.create(new ByteArrayInputStream("src=\"../afolder/aresource\"".getBytes("UTF-8")), false, new NullProgressMonitor());
        IFolder subFolder = folder.getFolder("subFolder");
        IFolder imagesFolder = folder.getFolder("images");
        imagesFolder.create(false, false, new NullProgressMonitor());
        imagesFolder.getFile("plop.jpg").create(new ByteArrayInputStream("plop".getBytes("UTF-8")), false, new NullProgressMonitor());
        subFolder.create(false, true, new NullProgressMonitor());
        IFile testCss = subFolder.getFile("test.css");//depth = 2
        testCss.create(new ByteArrayInputStream("src=\"../images/plop.jpg\"".getBytes("UTF-8")), false, new NullProgressMonitor());
        final InputStream is = createArtifact.getContent();
        final File createTempFile = File.createTempFile("exportLookNFeelFileStore", ".zip");
        final FileOutputStream out = new FileOutputStream(createTempFile);
        final byte[] bytes = new byte[1024];
        int nbRead = 0;
        while ((nbRead = is.read(bytes, 0, 1024)) > 0) {
            out.write(bytes, 0, nbRead);
        }
        is.close();
        out.close();
        assertNotNull(createTempFile);
        assertTrue(createTempFile.exists());

        final File destDir = new File(createTempFile.getParentFile(),createTempFile.getName().substring(0, createTempFile.getName().length()-4));
        destDir.mkdir();
        PlatformUtil.unzipZipFiles(createTempFile, destDir, null);
        FileInputStream fis = new FileInputStream(new File(destDir,"test.html"));
        String fileContent = PlatformUtil.getFileContent(fis);
        String startsWith = "src=\"themeResource?theme=testgetContentsWithUrl&timestamp=";
        String endsWithForTest = "&location=test%2F\"";
        assertTrue("should start with:<"+startsWith+"> but was <"+fileContent+">", fileContent.startsWith(startsWith));
        assertTrue("should end with:<"+endsWithForTest+"> but was <"+fileContent+">", fileContent.endsWith(endsWithForTest));
        fis.close();
        String endsWith = "&location=..%2Ftest%2F\"";
        fis = new FileInputStream(new File(destDir,"test2.html"));
        fileContent = PlatformUtil.getFileContent(fis);
        startsWith = "src=\"themeResource?theme=testgetContentsWithUrl";
        assertTrue("should start with:<"+startsWith+"> but was <"+fileContent+">", fileContent.startsWith(startsWith));
        assertTrue("should end with:<"+endsWith+"> but was <"+fileContent+">", fileContent.endsWith(endsWith));
        fis.close();
        fis = new FileInputStream(new File(new File(destDir,"test"),"test3.html"));
        fileContent = PlatformUtil.getFileContent(fis);
        endsWith = "&location=afolder%2Faresource\"";
        assertTrue("should start with:<"+startsWith+"> but was <"+fileContent+">", fileContent.startsWith(startsWith));
        assertTrue("should end with:<"+endsWith+"> but was <"+fileContent+">", fileContent.endsWith(endsWith));
        fis.close();
        fis = new FileInputStream(new File(new File(new File(destDir,"test"),"subFolder"),"test.css"));
        fileContent = PlatformUtil.getFileContent(fis);
        endsWith = "&location=test%2Fimages%2Fplop.jpg\"";
        assertTrue("should start with:<"+startsWith+"> but was <"+fileContent+">", fileContent.startsWith(startsWith));
        assertTrue("should end with:<"+endsWith+"> but was <"+fileContent+">", fileContent.endsWith(endsWith));
        fis.close();
        PlatformUtil.delete(destDir, null);

        //re import
        createArtifact.delete();
        FileInputStream stream = new FileInputStream(createTempFile);
        LookNFeelFileStore importInputStream = repository.importInputStream("testgetContentsWithUrl.zip", stream);
        stream.close();

        InputStream contents = importInputStream.getResource().getFile("test.html").getContents();
        String fileContent2 = PlatformUtil.getFileContent(contents);
        contents.close();
        assertEquals("src=\"test/\"", fileContent2);
        contents = importInputStream.getResource().getFolder("test").getFile("test3.html").getContents();
        fileContent2 = PlatformUtil.getFileContent(contents);
        contents.close();
        assertEquals("src=\"../afolder/aresource\"", fileContent2);
        contents = importInputStream.getResource().getFolder("test").getFolder("subFolder").getFile("test.css").getContents();
        fileContent2 = PlatformUtil.getFileContent(contents);
        contents.close();
        assertEquals("src=\"../../test/images/plop.jpg\"", fileContent2);// no choice but to have this it's not beautifull but it's working
        importInputStream.delete();
    }



    public void testReplaceUrlsOfLookNFeelFileStoreWithUnexistingFile() throws Exception {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final LookNFeelFileStore createArtifact = repository.duplicateFrom("testgetContentsWithnonExistingResources","",repository.getUserXPLookNFeels().get(0));
        IFolder afolder = createArtifact.getResource().getFolder("afolder");
        afolder.create(true, true, new NullProgressMonitor());
        afolder.getFile("aresource").create(new ByteArrayInputStream("plop".getBytes("UTF-8")), false, new NullProgressMonitor());

        IFolder folder = createArtifact.getResource().getFolder("test");
        folder.create(false, true, new NullProgressMonitor());
        IFile file = folder.getFile("test1.html");//depth = 1
        file.create(new ByteArrayInputStream("src=\"../afolder/aresource\"".getBytes("UTF-8")), false, new NullProgressMonitor());

        IFile file2 = folder.getFile("test2.html");//depth = 1
        file2.create(new ByteArrayInputStream("src=\"../afolder/aNonExistingResource\"".getBytes("UTF-8")), false, new NullProgressMonitor());

        final InputStream is = createArtifact.getContent();
        final File createTempFile = File.createTempFile("exportLookNFeelFileStore", ".zip");
        final FileOutputStream out = new FileOutputStream(createTempFile);
        final byte[] bytes = new byte[1024];
        int nbRead = 0;
        while ((nbRead = is.read(bytes, 0, 1024)) > 0) {
            out.write(bytes, 0, nbRead);
        }
        is.close();
        out.close();
        assertNotNull(createTempFile);
        assertTrue(createTempFile.exists());
        final File destDir = new File(createTempFile.getParentFile(),createTempFile.getName().substring(0, createTempFile.getName().length()-4));
        destDir.mkdir();
        PlatformUtil.unzipZipFiles(createTempFile, destDir, null);

        String startsWith = "src=\"themeResource?theme=testgetContentsWithnonExistingResources&timestamp=";
        FileInputStream fis = new FileInputStream(new File(new File(destDir,"test"),"test1.html"));
        String fileContent = PlatformUtil.getFileContent(fis);
        String endsWith = "&location=afolder%2Faresource\"";
        assertTrue("should start with:<"+startsWith+"> but was <"+fileContent+">", fileContent.startsWith(startsWith));
        assertTrue("should end with:<"+endsWith+"> but was <"+fileContent+">", fileContent.endsWith(endsWith));
        fis.close();
        fis = new FileInputStream(new File(new File(destDir,"test"),"test2.html"));
        fileContent = PlatformUtil.getFileContent(fis);
        assertEquals("src=\"../afolder/aNonExistingResource\"", fileContent);
        fis.close();
        PlatformUtil.delete(destDir, null);

        //re import
        createArtifact.delete();
    }


    public void testChangeArtifactName() throws Exception {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        InputStream stream = this.getClass().getResourceAsStream("myTheme.zip");
        LookNFeelFileStore artifact = repository.importInputStream("myTheme.zip", stream);

        artifact.setThemeName("myTheme2");
        assertEquals("myTheme",artifact.getResource().getName());
        assertTrue(repository.getResource().getFolder("myTheme").exists());
        assertFalse(repository.getResource().getFolder("myTheme2").exists());
        artifact.save(null);
        assertEquals("myTheme2",artifact.getResource().getName());
        assertFalse(repository.getResource().getFolder("myTheme").exists());
        assertTrue(repository.getResource().getFolder("myTheme2").exists());
        artifact.delete();
    }

    public void testChangeArtifactNameIntoExsitingName() throws Exception {
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        InputStream stream = this.getClass().getResourceAsStream("myTheme.zip");
        LookNFeelFileStore artifact = repository.importInputStream("myTheme.zip", stream);

        artifact.setThemeName(DEFAULT_APPLICATION_LNF_ID);

        artifact.save(null);

        assertEquals("myTheme", artifact.getResource().getName());
        assertNotNull(repository.getChild(DEFAULT_APPLICATION_LNF_ID));
        assertNotNull(repository.getChild("myTheme"));
        artifact.delete();
        assertNotNull(repository.getChild(DEFAULT_APPLICATION_LNF_ID));
        assertNull(repository.getChild("myTheme"));
    }

    public void testDuplicateUserXpFileStore(){
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        LookNFeelFileStore artifact = repository.getUserXPLookNFeels().get(0);
        LookNFeelFileStore duplicateTheme = repository.duplicateFrom("myDuplicateduserXPTheme", "myDesc", artifact);
        assertTrue(duplicateTheme instanceof UserXpFileStore);
    }


    public void testDuplicateApplicationLookNFeelFileStore() throws ThemeAlreadyExistsException{
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        LookNFeelFileStore artifact = repository.getChild( DEFAULT_APPLICATION_LNF_ID);
        LookNFeelFileStore duplicateTheme = repository.duplicateFrom("myDuplicatedApplicationTheme", "myDesc", artifact);
        assertTrue(duplicateTheme instanceof ApplicationLookNFeelFileStore);
    }

    public void testApplyApplicationTheme() throws Exception{
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        InputStream stream = this.getClass().getResourceAsStream("themewithpreview.zip");
        LookNFeelFileStore artifactToApply = repository.importInputStream("themewithpreview.zip", stream);

        NewDiagramCommandHandler newProcessCommandHandler = new NewDiagramCommandHandler();
        newProcessCommandHandler.execute(null);
        DiagramFileStore artifact = newProcessCommandHandler.getNewDiagramFileStore();

        AbstractProcess process = ModelHelper.getAllProcesses(artifact.getContent()).get(0);
        TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(process);
        CompoundCommand command = WebTemplatesUtil.createAddTemplateCommand(editingDomain, process, (ApplicationLookNFeelFileStore) artifactToApply);

        editingDomain.getCommandStack().execute(command);
        artifact.save(artifact.getOpenedEditor());
        artifact.close();
        File file = WebTemplatesUtil.getFile(process.getProcessTemplate().getPath());
        FileInputStream is = new FileInputStream(file);
        String fileContent = PlatformUtil.getFileContent(is);
        is.close();
        assertEquals("PLOP PLOP\n", fileContent);
        assertTrue(process.getResourceFiles().get(0).getPath().endsWith("pom.xml"));
        artifactToApply.delete();
    }


    public void testSaveAsLookNFeel() throws Exception{
        LookNFeelRepositoryStore repository = (LookNFeelRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        InputStream resourceIS = this.getClass().getResourceAsStream("previewImage.png");
        IFolder tmpFolder = repository.getResource().getFolder("tmpTest");
        tmpFolder.create(false, true, new NullProgressMonitor());
        IFile file = tmpFolder.getFile("previewImage.png");
        file.create(resourceIS, false, new NullProgressMonitor());
        assertTrue(file.exists());
        NewDiagramCommandHandler newProcessCommandHandler = new NewDiagramCommandHandler();
        newProcessCommandHandler.execute(null);
        DiagramFileStore artifact = newProcessCommandHandler.getNewDiagramFileStore();
        artifact.close();
        AbstractProcess process = ModelHelper.getAllProcesses(artifact.getContent()).get(0);

        ApplicationResourceRepositoryStore store = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
        store.refresh();

        ApplicationResourceFileStore webTemplateArtifact = (ApplicationResourceFileStore) store.getChild(ModelHelper.getEObjectID(process));
        ApplicationLookNFeelFileStore theme = WebTemplatesUtil.convertWebTemplateToTheme(webTemplateArtifact, "test Theme", file.getLocation().toFile().getAbsolutePath(), new NullProgressMonitor());
        assertNotNull(theme);
        assertEquals("test Theme", theme.getName());
        Image previewImage = theme.getPreviewImage();
        assertNotNull(previewImage);
        theme.delete();
        PlatformUtil.delete(tmpFolder.getLocation().toFile(),new NullProgressMonitor());
    }

}
