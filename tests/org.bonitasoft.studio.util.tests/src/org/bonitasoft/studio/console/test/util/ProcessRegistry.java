/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.console.test.util;

import java.net.URL;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.Platform;

/**
 * @author Aurelien Pupier
 */
public class ProcessRegistry {

    public static MainProcess createBasicProcess() {
        MainProcess process = ProcessFactory.eINSTANCE.createMainProcess();
        process.setName("processName");
        process.setVersion("1.0");
        AssociatedFile t = ProcessFactory.eINSTANCE.createAssociatedFile();
        t.setPath("/html/Template/Value");
        t.setWarPath("war/html/Template/Value");
        process.setHtmlTemplate(t);
        t = ProcessFactory.eINSTANCE.createAssociatedFile();
        t.setPath("process/template");
        t.setWarPath("war/process/template");
        process.setProcessTemplate(t);
        Expression mandatoryLabel = ExpressionFactory.eINSTANCE.createExpression();
        mandatoryLabel.setType(ExpressionConstants.CONSTANT_TYPE);
        mandatoryLabel.setName("mandatoryLabel");
        mandatoryLabel.setContent("mandatoryLabel");
        mandatoryLabel.setReturnType(String.class.getName());
        process.setMandatoryLabel(mandatoryLabel);
        Expression mandatorySymbol = ExpressionFactory.eINSTANCE.createExpression();
        mandatorySymbol.setType(ExpressionConstants.CONSTANT_TYPE);
        mandatorySymbol.setName("mandatorySymbol");
        mandatorySymbol.setContent("mandatorySymbol");
        mandatorySymbol.setReturnType(String.class.getName());
        process.setMandatorySymbol(mandatorySymbol);
        t = ProcessFactory.eINSTANCE.createAssociatedFile();
        t.setPath("/error/templtae");
        t.setWarPath("war/error/templtae");
        process.setErrorTemplate(t);
        return process;
    }

    /**
     * @param name of the bar in the folder procExamplesTest
     * @return
     * @throws Exception
     */
    public static MainProcess getTestExampleProcess(String procFileName) throws Exception {
        URL fileUrl = Platform.getBundle("org.bonitasoft.studio.util.tests").getEntry("procExamplesTest/" + procFileName);
        DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore fileStore = store.importInputStream(procFileName, fileUrl.openStream());
        return fileStore.getContent();
    }

    public static AbstractProcess createBasicProcessWithForm() {
        AbstractProcess process = createBasicProcess();
        Form form = FormFactory.eINSTANCE.createForm();
        form.setName("myForm1");
        form.setNColumn(6);
        form.setNLine(4);
        createWidget(form, FormFactory.eINSTANCE.createTextFormField(), "textbox1", 0, 0);
        createWidget(form, FormFactory.eINSTANCE.createCheckBoxSingleFormField(), "checkBox1", 0, 1);
        createWidget(form, FormFactory.eINSTANCE.createCheckBoxMultipleFormField(), "checkBoxM2", 0, 2);
        createWidget(form, FormFactory.eINSTANCE.createDateFormField(), "date1", 1, 0);
        createWidget(form, FormFactory.eINSTANCE.createFileWidget(), "file1", 0, 3);
        createWidget(form, FormFactory.eINSTANCE.createHiddenWidget(), "hidden1", 1, 2);
        createWidget(form, FormFactory.eINSTANCE.createImageWidget(), "image1", 2, 0);
        createWidget(form, FormFactory.eINSTANCE.createListFormField(), "list1", 2, 1);
        createWidget(form, FormFactory.eINSTANCE.createMessageInfo(), "message1", 2, 4);
        createWidget(form, FormFactory.eINSTANCE.createTextInfo(), "textInfo1", 2, 3);
        createWidget(form, FormFactory.eINSTANCE.createPasswordFormField(), "password1", 3, 0);
        createWidget(form, FormFactory.eINSTANCE.createRadioFormField(), "radio1", 3, 1);
        createWidget(form, FormFactory.eINSTANCE.createTextAreaFormField(), "textArea1", 3, 2);
        createWidget(form, FormFactory.eINSTANCE.createSelectFormField(), "select1", 3, 3);
        createWidget(form, FormFactory.eINSTANCE.createSubmitFormButton(), "submit1", 3, 4);
        process.getForm().add(form);

        return process;

    }

    private static void createWidget(Form form, Widget w, String name, int y, int x) {
        w.setName("widget1");
        WidgetLayoutInfo wlayout = FormFactory.eINSTANCE.createWidgetLayoutInfo();
        wlayout.setLine(y);
        wlayout.setColumn(x);
        w.setWidgetLayoutInfo(wlayout);
        form.getWidgets().add(w);
    }

}
