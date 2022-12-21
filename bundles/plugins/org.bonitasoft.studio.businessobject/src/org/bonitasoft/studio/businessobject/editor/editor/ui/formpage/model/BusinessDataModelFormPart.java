/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.IOException;
import java.util.function.Consumer;

import javax.xml.bind.JAXBException;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wst.sse.core.internal.text.JobSafeStructuredDocument;
import org.xml.sax.SAXException;

public class BusinessDataModelFormPart extends AbstractFormPart {

    private static final String TEXT_EDITOR_FONT = "org.eclipse.jdt.ui.editors.textfont";
    private DataBindingContext ctx = new DataBindingContext();
    private BusinessDataModelFormPage formPage;
    private BusinessObjectList businessObjectList;
    private BusinessObjectEditionControl businessObjectEditionControl;

    public BusinessDataModelFormPart(Composite businessDataModelComposite,
            BusinessDataModelFormPage formPage) {
        this.formPage = formPage;
        businessDataModelComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(20, 5).create());
        businessDataModelComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 700).create());
        Composite leftComposite = formPage.getToolkit().createComposite(businessDataModelComposite);
        leftComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        leftComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        createBusinessObjectList(leftComposite);
        createMavenArtifactGAV(leftComposite, ctx);

        Composite rightComposite = formPage.getToolkit().createComposite(businessDataModelComposite);
        rightComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        rightComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createBusinessObjectEditionControl(rightComposite);
    }

    private void createMavenArtifactGAV(Composite parent, DataBindingContext ctx) {
        Section section = formPage.getToolkit().createSection(parent, Section.TWISTIE | Section.NO_TITLE_FOCUS_BOX);
        section.setExpanded(false);
        section.setLayoutData(GridDataFactory.fillDefaults().create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setText(Messages.mavenArtifactProperties);
        section.setToolTipText(Messages.mavenArtifactPropertiesHint);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().create());
        client.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10).create());

        IObservableValue<BusinessObjectModel> workingCopyObservable = formPage.observeWorkingCopy();
        var bdmStore = formPage.getRepositoryAccessor().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore store = (BusinessObjectModelFileStore) bdmStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false);
        Dependency dep = null;
        try {
            dep = store.getModelMavenDependency();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            dep = new Dependency();
        }
        String modelDependencyGav = String.format("<dependency>" + System.lineSeparator()
                + "    <groupId>%s</groupId>" + System.lineSeparator()
                + "    <artifactId>%s</artifactId>" + System.lineSeparator()
                + "    <version>%s</version>" + System.lineSeparator()
                + "    <scope>provided</scope>" + System.lineSeparator()
                + "</dependency>", dep.getGroupId(), dep.getArtifactId(), dep.getVersion());
        var textAreaWidget = new TextAreaWidget.Builder()
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(new WritableValue<String>(modelDependencyGav,
                        String.class))
                .editable(false)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .withButton(Pics.getImage(PicsConstants.copyToClipboard), Messages.copyToClipboard)
                .createIn(client);

        textAreaWidget.onClickButton(e -> {
            copyToClipboard(modelDependencyGav, textAreaWidget, true);
        });

        var textControl = textAreaWidget.getTextControl();
        textControl.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
        textControl.getParent().setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
        textControl.setFont(JFaceResources.getFontRegistry().get(TEXT_EDITOR_FONT));
        textControl.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                textControl.getDisplay().timerExec(100, () -> {
                    textControl.selectAll();
                    copyToClipboard(modelDependencyGav, textAreaWidget, false);
                });
            }

        });

        section.setClient(client);
    }

    private void copyToClipboard(String text, TextWidget textAreaWidget, boolean forceFeedback) {
        Clipboard clipboard = new Clipboard(Display.getDefault());
        TextTransfer textTransfer = TextTransfer.getInstance();
        var content = clipboard.getContents(textTransfer);
        if (!text.equals(content)) {
            clipboard.setContents(new String[] { text }, new Transfer[] { textTransfer });
        }
        if (!text.equals(content) || forceFeedback) {
            textAreaWidget.getButtonWithImage().ifPresent(showCopiedToClipboard());
        }
        clipboard.dispose();
    }

    private Consumer<? super ToolItem> showCopiedToClipboard() {
        return b -> Display.getDefault().asyncExec(() -> {
            var originalImage = b.getImage();
            b.setImage(Pics.getImage(PicsConstants.checkmark));
            Display.getDefault().timerExec(1500, () -> {
                if (!b.isDisposed() && !originalImage.isDisposed()) {
                    b.setImage(originalImage);
                }
            });
        });
    }

    private void createBusinessObjectEditionControl(Composite parent) {
        businessObjectEditionControl = new BusinessObjectEditionControl(parent, formPage, ctx);
        ctx.bindValue(formPage.observeBusinessObjectSelected(), businessObjectEditionControl.observeSectionTitle(),
                updateValueStrategy()
                        .withConverter(
                                IConverter.<BusinessObject, String> create(o -> o != null ? o.getSimpleName() : ""))
                        .create(),
                neverUpdateValueStrategy().create());
        ctx.bindValue(businessObjectEditionControl.observeSectionVisible(), new ComputedValue<Boolean>(Boolean.TYPE) {

            @Override
            protected Boolean calculate() {
                return formPage.observeBusinessObjectSelected().getValue() != null;
            }
        });
    }

    private void createBusinessObjectList(Composite businessDataModelComposite) {
        businessObjectList = new BusinessObjectList(businessDataModelComposite, formPage, ctx);
        ctx.bindValue(businessObjectList.observeInput(), formPage.observeWorkingCopy());
        businessObjectList.expandAll();
    }

    @Override
    public void commit(boolean onSave) {
        BusinessObjectModel workingCopy = formPage.observeWorkingCopy().getValue();
        JobSafeStructuredDocument document = (JobSafeStructuredDocument) formPage.getDocument();
        DocumentRewriteSession session = null;
        try {
            session = document.startRewriteSession(DocumentRewriteSessionType.STRICTLY_SEQUENTIAL);
            document.set(new String(formPage.getParser().marshall(formPage.getConverter().toEngineModel(workingCopy))));
        } catch (final JAXBException | IOException | SAXException e) {
            throw new RuntimeException("Fail to update the document", e);
        } finally {
            if (session != null) {
                document.stopRewriteSession(session);
            }
        }
        super.commit(onSave);
        if (onSave) {
            getManagedForm().dirtyStateChanged();
        }
    }

    public void updateFieldDetailsTopControl() {
        businessObjectEditionControl.updateFieldDetailsTopControl();
    }

    public void refreshBusinessObjectList() {
        businessObjectList.refreshViewer();
    }

    public void showBusinessObjectSelection() {
        businessObjectList.showBusinessObjectSelection();
    }
}
