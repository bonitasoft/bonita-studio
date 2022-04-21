/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.draw;

import java.util.List;

import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.exception.EmptyDiagramSelectionException;
import org.bonitasoft.studio.swtbot.framework.exception.SemanticElementNotFoundException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.finders.WorkbenchContentsFinder;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.Assert;

/**
 * Gef base editor.
 *
 * @author Joachim Segala
 */
public class BotGefBaseEditor extends BotBase {

    protected final SWTBotGefEditor gmfEditor;

    public BotGefBaseEditor(final SWTGefBot bot) {
        super(bot);
        WorkbenchContentsFinder workbenchContentsFinder = new WorkbenchContentsFinder();
        bot.waitUntil(new DefaultCondition() {
			
			@Override
			public boolean test() throws Exception {
				return workbenchContentsFinder.findActiveEditor() != null;
			}
			
			@Override
			public String getFailureMessage() {
				return "There is no active editor";
			}
		},10000,500);
        final SWTBotEditor botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
    }

    /**
     * Select an element in the opened Draw.
     *
     * @param pName
     */
    public BotGefBaseEditor selectElement(final String pName) {
    	gmfEditor.setFocus();
        final SWTBotGefEditPart element = gmfEditor.getEditPart(pName);
        Assert.assertNotNull("Error: Element not found : \'" + pName + "\'.", element);
        element.click();//
        element.parent().select();//call select on parent of LabelEditpart
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                for (final SWTBotGefEditPart ep : gmfEditor.selectedEditParts()) {
                    EditPart part = ep.part();
                    while (!(part instanceof IGraphicalEditPart)) {
                        part = part.getParent();
                    }
                    if (part != null) {
                        final EObject resolveSemanticElement = ((IGraphicalEditPart) part).resolveSemanticElement();
                        if (isElementWithName(pName, resolveSemanticElement)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                final StringBuilder editpartSelected = new StringBuilder();
                for (final SWTBotGefEditPart ep : gmfEditor.selectedEditParts()) {
                    EditPart part = ep.part();
                    while (!(part instanceof IGraphicalEditPart)) {
                        part = part.getParent();
                    }
                    if (part != null) {
                        final EObject resolveSemanticElement = ((IGraphicalEditPart) part).resolveSemanticElement();
                        if (resolveSemanticElement instanceof Element) {
                            editpartSelected.append(((Element) resolveSemanticElement).getName());
                        }
                    }
                }
                return "Failed to select " + pName + "\nThe currently selected elments are: " + editpartSelected;
            }
        });
        return this;
    }

    protected boolean isElementWithName(final String pName, final EObject resolveSemanticElement) {
        if (resolveSemanticElement instanceof Element) {
            if (((Element) resolveSemanticElement).getName().equals(pName)) {
                return true;
            }
        }
        return false;
    }

    public SWTBotGefEditor getGmfEditor() {
        return gmfEditor;
    }

    public EObject getSelectedSemanticElement() {
        final List<SWTBotGefEditPart> selectedEditParts = gmfEditor.getSWTBotGefViewer().selectedEditParts();
        if (selectedEditParts.isEmpty()) {
            throw new EmptyDiagramSelectionException();
        }
        final SWTBotGefEditPart swtBotGefEditPart = selectedEditParts.get(0);
        EditPart part = swtBotGefEditPart.part();
        while (part != null && !(part instanceof IGraphicalEditPart)) {
            part = part.getParent();
        }
        if (part == null) {
            throw new SemanticElementNotFoundException();
        }
        return ((IGraphicalEditPart) part).resolveSemanticElement();
    }

    public void clickContextMenu(final String contextMenuItem) {
        gmfEditor.clickContextMenu(contextMenuItem);
    }

}
