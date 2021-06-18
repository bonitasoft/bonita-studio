/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.views.dashboard;

import org.bonitasoft.studio.application.views.extension.ExtensionComposite;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class ProjectDashboardEditorPart extends EditorPart {

    public static final String ID = "org.bonitasoft.studio.application.dashboard.editor";

    public static final String BOLD_20_FONT_ID = "bold20_bonita";
    public static final String BOLD_8_FONT_ID = "bold8_bonita";
    public static final String BOLD_4_FONT_ID = "bold4_bonita";
    public static final String BOLD_0_FONT_ID = "bold0_bonita";
    public static final String ITALIC_2_FONT_ID = "italic2_bonita";
    public static final String ITALIC_0_FONT_ID = "italic0_bonita";
    public static final String NORMAL_10_FONT_ID = "normal10_bonita";
    public static final String NORMAL_4_FONT_ID = "normal4_bonita";

    public static final String OPEN_MARKETPLACE_COMMAND = "org.bonitasoft.studio.application.marketplace.command";
    public static final String IMPORT_EXTENSION_COMMAND = "org.bonitasoft.studio.application.import.extension.command";
    public static final String UPDATE_GAV_COMMAND = "org.bonitasoft.studio.application.update.gav.command";

    private RepositoryAccessor repositoryAccessor;
    private Cursor cursorHand;
    private Cursor cursorArrow;
    private DataBindingContext ctx;
    private LocalResourceManager localResourceManager;
    private Composite mainComposite;

    public ProjectDashboardEditorPart() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(Display.getDefault()));
        ctx = new DataBindingContext();
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof ProjectDashboardEditorInput)) {
            throw new PartInitException("Invalid Input: Must be ProjectExtensionEditorInput");
        }
        setSite(site);
        setInput(input);
    }

    @Override
    public void createPartControl(Composite parent) {
        initVariables(parent);
        parent.setLayout(GridLayoutFactory.fillDefaults().create());

        mainComposite = createComposite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        initFonts(mainComposite.getFont());

        new ExtensionComposite(mainComposite, repositoryAccessor);
    }

    private void initFonts(Font defaultFont) {
        createFont(BOLD_20_FONT_ID, defaultFont, 20, SWT.BOLD);
        createFont(BOLD_8_FONT_ID, defaultFont, 8, SWT.BOLD);
        createFont(BOLD_4_FONT_ID, defaultFont, 4, SWT.BOLD);
        createFont(BOLD_0_FONT_ID, defaultFont, 0, SWT.BOLD);
        createFont(ITALIC_0_FONT_ID, defaultFont, 0, SWT.ITALIC);
        createFont(ITALIC_2_FONT_ID, defaultFont, 2, SWT.ITALIC);
        createFont(NORMAL_10_FONT_ID, defaultFont, 10, SWT.NORMAL);
        createFont(NORMAL_4_FONT_ID, defaultFont, 4, SWT.NORMAL);
    }

    private void createFont(String id, Font initialFont, int increaseHeight, int style) {
        if (!JFaceResources.getFontRegistry().hasValueFor(id)
                || JFaceResources.getFontRegistry().get(id).isDisposed()) {
            FontDescriptor descriptor = FontDescriptor.createFrom(initialFont).setStyle(style)
                    .increaseHeight(increaseHeight);
            JFaceResources.getFontRegistry().put(id, localResourceManager.createFont(descriptor).getFontData());
        }
    }

    private void initVariables(Composite parent) {
        cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
    }

    private Composite createComposite(Composite parent, int style) {
        Composite composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        return composite;
    }

    @Override
    public void setFocus() {
        // do nothing
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // do nothing
    }

    @Override
    public void doSaveAs() {
        // do nothing
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public Image getTitleImage() {
        return Pics.getImage(PicsConstants.openExtensions);
    }

}
