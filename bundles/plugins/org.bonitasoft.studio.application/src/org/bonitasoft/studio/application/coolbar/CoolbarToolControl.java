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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.e4.ui.workbench.renderers.swt.TrimmedPartLayout;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.INullSelectionListener;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.activities.ActivityManagerEvent;
import org.eclipse.ui.activities.IActivityManagerListener;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.handlers.DirtyStateTracker;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * @author Romain Bioteau
 */
public class CoolbarToolControl
        implements INullSelectionListener, IActivityManagerListener, org.eclipse.e4.ui.workbench.modeling.IPartListener {

    private static final int ICON_SIZE = 24;

    private enum CoolbarSize {
        SMALL, NORMAL
    }

    private static final String UNICODE_REDUCE = "\u25B2";
    private static final String UNICODE_INCREASE = "\u25BC";
    private static final String CLASS = "class";
    private static final String POSITION = "toolbarPosition";
    private static final String PRIORITY = "priority";
    private static final int MAX_CONTRIBUTION_SIZE = 25;
    private CoolbarSize size;
    private final Map<Integer, IBonitaContributionItem> contributions = new HashMap<Integer, IBonitaContributionItem>();
    private ToolBar toolbar;
    private Composite toolbarContainer;
    private boolean isRegistered;
    private Font biggerFont;

    @PostConstruct
    public void createControls(final Composite parent, final IEclipseContext context,
            final IWorkbenchActivitySupport activitySupport) {
        if (isRendered(context) && toolbarContainer == null ) {
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
                createToolbar(toolbarContainer);
                activitySupport.getActivityManager().addActivityManagerListener(this);
                final IEventBroker eventBroker = context.get(IEventBroker.class);
                eventBroker.subscribe(UIElement.TOPIC_ALL, new EventHandler() {

                    @Override
                    public void handleEvent(final Event arg0) {
                        final IWorkbenchWindow window = context.get(IWorkbenchWindow.class);
                        final IWorkbenchPage page = context.get(IWorkbenchPage.class);
                        if (window != null) {
                            registerHandlers((WorkbenchWindow) window, page);
                        }
                    }
                });
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
            activitySupport.getActivityManager().removeActivityManagerListener(this);
        }
    }

    public Map<Integer, IBonitaContributionItem> getContributions() {
        return contributions;
    }

    private void createToolbar(final Composite toolbarContainer) {
        toolbar = new ToolBar(toolbarContainer, SWT.FLAT | SWT.HORIZONTAL);
        toolbar.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, true).create());

        fillBonitaBar();

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
                .setToolTipText(size == CoolbarSize.SMALL ? Messages.maximizeCoolbarTooltip : Messages.reduceCoolbarTooltip);
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

    private void fillBonitaBar() {

        for (final IBonitaContributionItem contribution : contributions.values()) {
            contribution.dispose();
        }
        contributions.clear();

        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.coolbarContributionItem");
        if (elements.length >= MAX_CONTRIBUTION_SIZE) {
            throw new RuntimeException("Too many coolbar contributions defined");
        }
        for (int i = 0; i < MAX_CONTRIBUTION_SIZE; i++) {
            final IConfigurationElement element = findContributionForPosition(i, elements);
            if (element != null) {
                try {
                    final IBonitaContributionItem item = (IBonitaContributionItem) element.createExecutableExtension(CLASS);
                    if (toolbar.getItemCount() > 1 && item instanceof SeparatorCoolbarItem) {
                        final int index = toolbar.getItemCount() - 1;
                        final ToolItem previousItem = toolbar.getItem(index);
                        if ((previousItem.getStyle() & SWT.SEPARATOR) != 0) {
                            item.setVisible(false);
                        }
                    }
                    if (item.isVisible()) {
                        if (size == CoolbarSize.SMALL) {
                            item.fill(toolbar, i, ICON_SIZE);
                        } else {
                            item.fill(toolbar, i, -1);
                        }
                        contributions.put(toolbar.getItemCount() - 1, item);
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
        createToolbar(toolbarContainer);
        toolbarContainer.getParent().layout(true, true);
    }

    public void minimizeCoolbar() {
        for (final Control c : toolbarContainer.getChildren()) {
            c.dispose();
        }
        size = CoolbarSize.SMALL;
        createToolbar(toolbarContainer);
        toolbarContainer.getParent().layout(true, true);
    }

    private CoolbarSize getCoolBarPreferredSize() {
        final String value = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getString(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE);
        return value.equals(BonitaCoolBarPreferenceConstant.SMALL) ? CoolbarSize.SMALL : CoolbarSize.NORMAL;
    }

    public void refreshCoolBarButtons() {
        if (toolbar != null && !toolbar.isDisposed()) {
            for (final ToolItem item : toolbar.getItems()) {
                if (!item.isDisposed()) {
                    final IBonitaContributionItem iBonitaContributionItem = contributions.get(toolbar.indexOf(item));
                    item.setEnabled(iBonitaContributionItem.isEnabled());
                }
            }
        }
    }

    private IConfigurationElement findContributionForPosition(final int position, final IConfigurationElement[] elements) {
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

    private void refreshCoolbar() {
        for (final Control c : toolbarContainer.getChildren()) {
            c.dispose();
        }
        createToolbar(toolbarContainer);
        for (final IBonitaContributionItem bcItem : contributions.values()) {
            if (bcItem instanceof SaveCoolbarItem) {
                ((SaveCoolbarItem) bcItem).createDirtyStateTracker();
            }
        }
        toolbar.getParent().layout(true, true);
    }

    public void registerHandlers(final WorkbenchWindow workbenchWindow, final IWorkbenchPage page) {
        if (!isRegistered) {
            workbenchWindow.getModel().getContext().get(EPartService.class).addPartListener(CoolbarToolControl.this);
            if (page != null) {
                page.addSelectionListener(CoolbarToolControl.this);
                page.addPartListener(new IPartListener() {

                    @Override
                    public void partOpened(final IWorkbenchPart wp) {
                        if (wp instanceof ISaveablePart) {
                            for (final IBonitaContributionItem bcItem : contributions.values()) {
                                if (bcItem instanceof SaveCoolbarItem) {
                                    final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker(bcItem);
                                    dirtyStateTracker.partOpened(wp);
                                }
                            }
                        }
                    }

                    @Override
                    public void partDeactivated(final IWorkbenchPart wp) {
                        if (wp instanceof ISaveablePart) {
                            for (final IBonitaContributionItem bcItem : contributions.values()) {
                                if (bcItem instanceof SaveCoolbarItem) {
                                    final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker(bcItem);
                                    dirtyStateTracker.partDeactivated(wp);

                                }
                            }
                        }
                    }

                    @Override
                    public void partClosed(final IWorkbenchPart wp) {
                        if (wp instanceof ISaveablePart) {
                            for (final IBonitaContributionItem bcItem : contributions.values()) {
                                if (bcItem instanceof SaveCoolbarItem) {
                                    final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker(bcItem);
                                    dirtyStateTracker.partClosed(wp);
                                }
                            }
                        }
                    }

                    @Override
                    public void partBroughtToTop(final IWorkbenchPart wp) {
                        if (wp instanceof ISaveablePart) {
                            for (final IBonitaContributionItem bcItem : contributions.values()) {
                                if (bcItem instanceof SaveCoolbarItem) {
                                    final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker(bcItem);
                                    dirtyStateTracker.partBroughtToTop(wp);
                                }
                            }
                        }
                    }

                    @Override
                    public void partActivated(final IWorkbenchPart wp) {
                        if (wp instanceof ISaveablePart) {
                            for (final IBonitaContributionItem bcItem : contributions.values()) {
                                if (bcItem instanceof SaveCoolbarItem) {
                                    final DirtyStateTracker dirtyStateTracker = getDirtyStateTracker(bcItem);
                                    dirtyStateTracker.partActivated(wp);
                                }
                            }
                        }
                        if (wp instanceof DiagramEditor) {
                            for (final IBonitaContributionItem bcItem : contributions.values()) {
                                if (bcItem instanceof ISelectionChangedListener) {
                                    ((DiagramEditor) wp).getDiagramGraphicalViewer()
                                            .addSelectionChangedListener((ISelectionChangedListener) bcItem);
                                }
                            }
                        }
                    }

                    /**
                     * @param bcItem
                     * @return
                     */
                    public DirtyStateTracker getDirtyStateTracker(
                            final IBonitaContributionItem bcItem) {
                        DirtyStateTracker dirtyStateTracker = ((SaveCoolbarItem) bcItem).getDirtyStateTracker();
                        if (dirtyStateTracker == null) {
                            ((SaveCoolbarItem) bcItem).createDirtyStateTracker();
                            dirtyStateTracker = ((SaveCoolbarItem) bcItem).getDirtyStateTracker();
                        }
                        return dirtyStateTracker;
                    }
                });
            }
            isRegistered = true;
        }
    }

    @Override
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        refreshCoolBarButtons();
    }

    @Override
    public void activityManagerChanged(final ActivityManagerEvent activityManagerEvent) {
        refreshCoolbar();
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
}
