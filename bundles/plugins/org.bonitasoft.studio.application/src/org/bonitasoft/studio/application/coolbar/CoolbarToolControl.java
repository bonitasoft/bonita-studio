/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.coolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.preferences.BonitaCoolBarPreferenceConstant;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimBar;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents.UIElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.e4.ui.workbench.renderers.swt.TrimmedPartLayout;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * @author Romain Bioteau
 */
public class CoolbarToolControl
        implements ISelectionListener, org.eclipse.e4.ui.workbench.modeling.IPartListener {

    enum CoolbarSize {
        SMALL, NORMAL
    }

    private static final String UNICODE_REDUCE = "\u25B2";
    private static final String UNICODE_INCREASE = "\u25BC";
    private static final String CLASS = "class";
    private static final String POSITION = "toolbarPosition";
    private static final String PRIORITY = "priority";
    private static final int MAX_CONTRIBUTION_SIZE = 25;
    private CoolbarSize size;
    private final List<CustomToolItem> contributions = new ArrayList<>();
    private Composite toolbarContainer;
    private boolean isRegistered;
    private Font biggerFont;

    @PostConstruct
    public void createControls( Composite parent, 
            EPartService partService, 
            ESelectionService selectionService, 
            IEclipseContext context) {
        if (isRendered(context) && toolbarContainer == null && context.get(IWorkbenchPage.class) != null) {
            IWorkbenchPage page = context.get(IWorkbenchPage.class);
            size = getCoolBarPreferredSize();
            final Composite parentShell = parent.getParent();
            final TrimmedPartLayout layout = (TrimmedPartLayout) parentShell.getLayout();
            if (layout != null) {
                toolbarContainer = new Composite(parentShell, SWT.INHERIT_FORCE);
                toolbarContainer.setLayoutData(
                        GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());
                toolbarContainer
                        .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(0, 0).margins(0, 0)
                                .extendedMargins(0, 10, 0, 0).create());
                layout.top = toolbarContainer;
                final Composite leftTrim = layout.left;
                if (leftTrim != null) {
                    leftTrim.setVisible(false);
                    leftTrim.setSize(0, 0);
                    leftTrim.getLayoutData();
                }
                final Composite rightTrim = layout.right;
                if (rightTrim != null) {
                    rightTrim.setVisible(false);
                    rightTrim.setSize(0, 0);
                }
                createToolbar(toolbarContainer, page);

                if(!isRegistered) {
                    partService.addPartListener(this);
                    selectionService.addSelectionListener(this);
                }
            }
        }
    }

    protected boolean isRendered(final IEclipseContext context) {
        final MTrimBar topTrim = getTrimBar(context, "org.eclipse.ui.main.toolbar");
        return topTrim != null && topTrim.isToBeRendered() && !PlatformUtil.isHeadless();
    }

    protected MTrimBar getTrimBar(final IEclipseContext context, final String trimBarId) {
        final EModelService modelService = context.get(EModelService.class);
        final MWindow window = context.get(MWindow.class);
        final MTrimBar topTrim = (MTrimBar) modelService.find(trimBarId, window);
        return topTrim;
    }

    @PreDestroy
    public void dispose(final IWorkbenchActivitySupport activitySupport) {
        isRegistered = false;
        if (toolbarContainer != null) {
            toolbarContainer.dispose();
        }
    }

    public List<CustomToolItem> getContributions() {
        return contributions;
    }

    private void createToolbar(final Composite toolbarContainer, IWorkbenchPage page) {
        loadContributions(page);
        Composite leftToolbarContainer = new Composite(toolbarContainer, SWT.NONE);
        leftToolbarContainer
                .setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());
        leftToolbarContainer
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(contributions.size())
                        .spacing(size == CoolbarSize.SMALL ? 3 : 5, LayoutConstants.getSpacing().y).create());
        contributions.stream().forEach(item -> item.createControl(leftToolbarContainer, size));

        final ToolBar sizingToolbar = new ToolBar(toolbarContainer, SWT.FLAT | SWT.VERTICAL);
        Font font = sizingToolbar.getFont();
        FontData[] fontData = font.getFontData();
        fontData[0].setHeight(10);
        if (biggerFont == null || biggerFont.isDisposed()) {
            biggerFont = new Font(sizingToolbar.getDisplay(), fontData[0]);
        }
        sizingToolbar.setFont(biggerFont);
        sizingToolbar.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0)
                .spacing(0, 0).create());
        sizingToolbar.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).create());

        final ToolItem changeSizeButton = new ToolItem(sizingToolbar, SWT.PUSH);
        changeSizeButton.setText(size == CoolbarSize.SMALL ? UNICODE_INCREASE : UNICODE_REDUCE);
        changeSizeButton
                .setToolTipText(
                        size == CoolbarSize.SMALL ? Messages.maximizeCoolbarTooltip : Messages.reduceCoolbarTooltip);
        changeSizeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (size == CoolbarSize.SMALL) {
                    maximizeCoolbar();
                } else {
                    minimizeCoolbar();
                }
            }
        });
    }

    private void loadContributions(IWorkbenchPage page) {
        contributions.stream().map(CustomToolItem::getContributionItem).filter(IPartListener.class::isInstance)
                .forEach(partListener -> page.removePartListener((IPartListener) partListener));
        contributions.clear();

        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(
                        "org.bonitasoft.studio.coolbarContributionItem");
        if (elements.length >= MAX_CONTRIBUTION_SIZE) {
            throw new RuntimeException("Too many coolbar contributions defined");
        }
        for (int i = 0; i < MAX_CONTRIBUTION_SIZE; i++) {
            final IConfigurationElement element = findContributionForPosition(i, elements);
            if (element != null) {
                try {
                    final IBonitaContributionItem item = (IBonitaContributionItem) element
                            .createExecutableExtension(CLASS);
                    if (contributions.size() > i && item instanceof SeparatorCoolbarItem) {
                        final IBonitaContributionItem previousItem = contributions.get(i - 1).getContributionItem();
                        if (previousItem instanceof SeparatorCoolbarItem) {
                            item.setVisible(false);
                        }
                    }
                    if (item.isVisible()) {
                        if (item instanceof SaveCoolbarItem) {
                            page.addPartListener((IPartListener) item);
                            ((SaveCoolbarItem) item).partOpened(page.getActivePart());
                        }
                        contributions.add(new CustomToolItem(item));
                    }
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    public void maximizeCoolbar() {
        for (final Control c : toolbarContainer.getChildren()) {
            c.dispose();
        }
        size = CoolbarSize.NORMAL;
        createToolbar(toolbarContainer, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());
        toolbarContainer.getParent().layout(true, true);
    }

    public void minimizeCoolbar() {
        for (final Control c : toolbarContainer.getChildren()) {
            c.dispose();
        }
        size = CoolbarSize.SMALL;
        createToolbar(toolbarContainer, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());
        toolbarContainer.getParent().layout(true, true);
    }

    private CoolbarSize getCoolBarPreferredSize() {
        final String value = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getString(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE);
        return value.equals(BonitaCoolBarPreferenceConstant.SMALL) ? CoolbarSize.SMALL : CoolbarSize.NORMAL;
    }

    public void refreshCoolBarButtons() {
        Display.getDefault().asyncExec(() -> contributions.stream().forEach(CustomToolItem::update));
    }

    private IConfigurationElement findContributionForPosition(final int position,
            final IConfigurationElement[] elements) {
        List<IConfigurationElement> list = new ArrayList<IConfigurationElement>();
        for (final IConfigurationElement element : elements) {
            final int pos = Integer.parseInt(element.getAttribute(POSITION));
            if (pos == position) {
                list.add(element);
            }
        }
        if (!list.isEmpty()) {
            if (list.size() > 1) {
                list = sortByPriority(list);
            }
            return list.get(0);
        }
        return null;
    }

    private List<IConfigurationElement> sortByPriority(final List<IConfigurationElement> list) {
        final List<IConfigurationElement> sortedConfigElems = new ArrayList<IConfigurationElement>();
        for (final IConfigurationElement elem : list) {
            sortedConfigElems.add(elem);
        }

        Collections.sort(sortedConfigElems, new Comparator<IConfigurationElement>() {

            @Override
            public int compare(final IConfigurationElement e1, final IConfigurationElement e2) {
                int p1 = 0;
                int p2 = 0;
                try {
                    p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
                } catch (final NumberFormatException e) {
                    p1 = 0;
                }
                try {
                    p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
                } catch (final NumberFormatException e) {
                    p2 = 0;
                }
                return p2 - p1; //Highest Priority first
            }

        });
        return sortedConfigElems;

    }

    public void registerHandlers(final IEclipseContext context, final IWorkbenchPage page) {
        EPartService partService = context.get(EPartService.class);
        ESelectionService selectionService = context.get(ESelectionService.class);
        if (!isRegistered && partService != null && selectionService != null) {
            partService.addPartListener(CoolbarToolControl.this);
            selectionService.addSelectionListener(CoolbarToolControl.this);
            isRegistered = true;
        }
    }


    @Override
    public void partActivated(final MPart part) {
        refreshCoolBarButtons();
    }

    @Override
    public void partBroughtToTop(final MPart part) {
        refreshCoolBarButtons();
    }

    @Override
    public void partDeactivated(final MPart part) {
        refreshCoolBarButtons();
    }

    @Override
    public void partHidden(final MPart part) {
        refreshCoolBarButtons();
    }

    @Override
    public void partVisible(final MPart part) {
        refreshCoolBarButtons();
    }

    @Override
    public void selectionChanged(MPart part, Object selection) {
        refreshCoolBarButtons();
    }
}
