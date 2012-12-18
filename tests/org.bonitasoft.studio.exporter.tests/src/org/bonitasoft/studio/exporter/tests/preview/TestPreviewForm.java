package org.bonitasoft.studio.exporter.tests.preview;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.bonitasoft.studio.console.test.util.ProcessRegistry;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.exporter.preview.PreviewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.commands.ExecutionException;
import org.junit.Ignore;
import org.junit.Test;

public class TestPreviewForm  {

    @Test
    @Ignore
    public void testPreviewForm() throws FileNotFoundException, IOException {
        AbstractProcess process = ProcessRegistry.createBasicProcessWithForm();

        File outputFile = ((PreviewForm) ExporterService.getInstance().getExporterService(SERVICE_TYPE.FormPreview)).previewForm(process.getForm().get(0),null);

        Source pageTemplateSource = new Source(new FileInputStream(outputFile));
        Element el;
        for(Widget w :process.getForm().get(0).getWidgets()){
            el = pageTemplateSource.getElementById(w.getName());
            if(el == null){
                fail("missing an element in template");
            }
        }
    }

    @Test
    @Ignore
    public void testPreviewFormWithGreyTemplate() throws ExecutionException, IOException{
        //		NewDiagramCommandHandler handler = new NewDiagramCommandHandler();
        //		handler.execute(null);
        //		DiagramFileStore artifact = handler.getNewDiagramFileStore();
        //		LookNFeelRepositoryStore lnfStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        //
        //		LookNFeelFileStore selectedTemplate = lnfStore.getArtifact("grey");
        //		DiagramEditor editor = ((DiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
        //		TransactionalEditingDomain editingDomain = editor.getEditingDomain();
        //		final AbstractProcess abstractProcess = (AbstractProcess) artifact.getContent().getElements().get(0);
        //		CompoundCommand cc = WebTemplatesUtil.createAddTemplateCommand(editingDomain, abstractProcess, (ApplicationThemeArtifact) selectedTemplate);
        //		editingDomain.getCommandStack().execute(cc);
        //
        //		AddFormCommand command = new AddFormCommand(abstractProcess, ProcessPackage.Literals.PAGE_FLOW__FORM	, "testForm", "",Collections.EMPTY_MAP , editingDomain);
        //		OperationHistoryFactory.getOperationHistory().execute(command,
        //		new NullProgressMonitor(), null);
        //		File previewForm = ((PreviewForm) ExporterService.getInstance().getExporterService(SERVICE_TYPE.FormPreview)).previewForm(abstractProcess.getForm().get(0),null);
        //		boolean images = false;
        //		boolean css = false;
        //		boolean isGreyCss = false;
        //		boolean generatedCss = false;
        //		for (File brother : previewForm.getParentFile().listFiles()) {
        //			if(brother.getName().equals("css")){
        //				css = true;
        //				for (File cssFile : brother.listFiles()) {
        //					if(cssFile.getName().equals("bonita_form_default.css")){
        //						isGreyCss = FileTestUtil.fileContains(cssFile,"background: #c1c4cc url(../images/waves-merged.png)");
        //					}
        //					if(cssFile.getName().equals("generatedcss.css")){
        //						generatedCss = true;
        //					}
        //				}
        //			}
        //			if(brother.getName().equals("images")){
        //				images = true;
        //			}
        //		}
        //		assertTrue("can't find css folder", css);
        //		assertTrue("can't file images fodler", images);
        //		assertTrue("not the grey css file", isGreyCss);
        //		assertTrue("can't find the generated css file", generatedCss );
        //
        //		editor.doSave(new NullProgressMonitor());

    }

}
