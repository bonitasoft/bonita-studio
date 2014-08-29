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

import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.exception.EmptyDiagramSelectionException;
import org.bonitasoft.studio.swtbot.framework.exception.SemanticElementNotFoundException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
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

        final SWTBotEditor botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
    }

    /**
     * Select an element in the opened Draw.
     *
     * @param pName
     */
    public BotGefBaseEditor selectElement(final String pName) {
        final SWTBotGefEditPart element = gmfEditor.getEditPart(pName);
        Assert.assertNotNull("Error: Element not found : \'" + pName + "\'.", element);
        element.click();
        bot.sleep(100);
        return this;
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
        while (!(part instanceof IGraphicalEditPart)) {
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
