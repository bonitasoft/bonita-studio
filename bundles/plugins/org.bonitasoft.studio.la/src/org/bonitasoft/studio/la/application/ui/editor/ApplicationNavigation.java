/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor;

import static org.bonitasoft.studio.la.application.ui.validator.ApplicationDescriptorValidators.applicationPageColumnValidator;
import static org.bonitasoft.studio.la.application.ui.validator.ApplicationDescriptorValidators.tokenPageColumnValidator;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.studio.common.jface.ArrayTreeContentProvider;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.converter.ApplicationToNavigationConverter;
import org.bonitasoft.studio.la.application.ui.editor.converter.UpdateApplicationNodeOperation;
import org.bonitasoft.studio.la.application.ui.editor.customPage.ApplicationPageProposalProvider;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageDescriptor;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageProvider;
import org.bonitasoft.studio.la.application.ui.editor.listener.MoveMenuListener;
import org.bonitasoft.studio.la.application.ui.provider.NavigationPageTreeContentProvider;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationDescriptorValidators;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.viewer.AutoCompleteTextCellEditor;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.google.common.base.Strings;

public class ApplicationNavigation extends Composite {

    private final ApplicationToNavigationConverter toNavigationConverter = new ApplicationToNavigationConverter();
    private final UpdateApplicationNodeOperation updateApplicationNodeOperation;
    private final FormToolkit toolkit;
    private final DataBindingContext ctx;
    private final CustomPageProvider customPageProvider;
    private final IObservableValue<String> homePageObservable;

    private final List<NavigationPageNode> menuPageNodes;
    private final List<NavigationPageNode> orphanPageNode;
    private final List<NavigationPageNode> menuAndOrphanList;
    private TreeViewer menuTreeViewer;
    private TreeViewer orphanPagesTreeViewer;
    private SimpleContentProposalProvider tokenProposalProvider;
    private ApplicationFormPage formPage;

    public ApplicationNavigation(Composite parent,
            ApplicationFormPage formPage,
            ApplicationNode applicationNode,
            FormToolkit toolkit,
            WebPageRepositoryStore webPageStore,
            ThemeRepositoryStore themeStore,
            IObservableValue<String> homePageObservable) {
        super(parent, SWT.NONE);
        toolkit.adapt(this);
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setLayout(GridLayoutFactory.fillDefaults().margins(0, 5).create());
        this.formPage = formPage;
        this.updateApplicationNodeOperation = new UpdateApplicationNodeOperation(applicationNode);
        this.toolkit = toolkit;
        this.homePageObservable = homePageObservable;
        customPageProvider = new CustomPageProvider(webPageStore, themeStore);
        this.ctx = new DataBindingContext();

        menuAndOrphanList = toNavigationConverter.toNavigationPageNodeList(applicationNode);
        orphanPageNode = menuAndOrphanList.stream()
                .filter(NavigationPageNode::isOrphan)
                .collect(Collectors.toList());

        menuPageNodes = menuAndOrphanList.stream()
                .filter(navPageNode -> !navPageNode.isOrphan())
                .collect(Collectors.toList());

        createMenuTable(this);

        Section orphanSection = toolkit.createSection(this, Section.EXPANDED | Section.DESCRIPTION);
        orphanSection.setLayout(GridLayoutFactory.fillDefaults().create());
        orphanSection.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).indent(0, 30).create());
        orphanSection.setText(Messages.orphanPage);
        orphanSection.setDescription(Messages.orphanPagesDescription);
        orphanSection.setClient(createOrphanPagesTable(orphanSection));

        Section homePageSection = toolkit.createSection(this, Section.EXPANDED);
        homePageSection.setLayout(GridLayoutFactory.fillDefaults().create());
        homePageSection.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).indent(0, 30).create());
        homePageSection.setText(Messages.homePage);
        homePageSection.setClient(createHomePageWidget(homePageSection));

        createTokenColumn(menuTreeViewer);
        createTokenColumn(orphanPagesTreeViewer);
        refreshViewers();

        ctx.updateTargets();
    }

    private Composite createHomePageWidget(Section homePageSection) {
        Composite homePageComposite = toolkit.createComposite(homePageSection);
        homePageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(0, 0, 10, 0).create());
        homePageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        homePageObservable.addValueChangeListener(this::changeListener);

        tokenProposalProvider = new SimpleContentProposalProvider(getTokenList().stream().toArray(String[]::new));
        tokenProposalProvider.setFiltering(true);

        new TextWidget.Builder()
                .withLabel(Messages.homePageToken)
                .withMessage(Messages.homePageDetails)
                .labelAbove()
                .widthHint(400)
                .bindTo(homePageObservable)
                .withProposalProvider(tokenProposalProvider)
                .withValidator(this::validateHomePageToken)
                .inContext(ctx)
                .adapt(toolkit)
                .createIn(homePageComposite);

        return homePageComposite;
    }

    public IStatus validateHomePageToken(Object value) {
        final String token = (String) value;
        return Strings.isNullOrEmpty(token)
                ? ValidationStatus.warning(Messages.homePageWarning)
                : getTokenList().stream()
                        .anyMatch(token::equals)
                                ? ValidationStatus.ok()
                                : ValidationStatus.error(Messages.unknownToken);
    }

    private Composite createOrphanPagesTable(Section section) {
        Composite orphanComposite = toolkit.createComposite(section);
        orphanComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(0, 0, 5, 0).create());
        orphanComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        WritableList<NavigationPageNode> inputList = new WritableList<>(orphanPageNode,
                NavigationPageNode.class);
        orphanPageNode.stream()
                .forEach(orphanPage -> orphanPage.addChangeListener(this::valueChangeListener));
        inputList.addChangeListener(this::valueChangeListener);

        Composite buttonsComposite = toolkit.createComposite(orphanComposite);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 3).create());
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        orphanPagesTreeViewer = new TreeViewer(orphanComposite, SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
        orphanPagesTreeViewer.getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.la.ex.ui.editor.orphanPagesTreeViewer");
        toolkit.adapt(orphanPagesTreeViewer.getTree(), true, true);
        orphanPagesTreeViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 120).create());
        ColumnViewerToolTipSupport.enableFor(orphanPagesTreeViewer);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, 300, true));
        layout.addColumnData(new ColumnWeightData(1, 300, true));

        orphanPagesTreeViewer.getTree().setLayout(layout);
        orphanPagesTreeViewer.getTree().setHeaderVisible(true);

        createPageColumn(orphanPagesTreeViewer);

        orphanPagesTreeViewer.setContentProvider(new ArrayTreeContentProvider());
        orphanPagesTreeViewer.setInput(inputList);

        IViewerObservableValue selectionObservable = ViewerProperties.singleSelection().observe(orphanPagesTreeViewer);
        createButton(buttonsComposite, Messages.addOrphanPage, toolkit).addListener(SWT.Selection, event -> {
            NavigationPageNode newElement = new NavigationPageNode("", "", "");
            newElement.setOrphan(true);
            newElement.addChangeListener(this::valueChangeListener);
            inputList.add(newElement);
            getDisplay().asyncExec(() -> {
                orphanPagesTreeViewer.refresh();
                orphanPagesTreeViewer.editElement(newElement, 0);
            });
        });

        Button removeButton = createButton(buttonsComposite, Messages.remove, toolkit);
        removeButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, "org.bonitasoft.studio.la.ex.ui.editor.orphanRemove");
        removeButton.addListener(SWT.Selection, event -> {
            NavigationPageNode selectedNode = (NavigationPageNode) selectionObservable.getValue();
            inputList.remove(selectedNode);
            refreshViewers();
        });

        ctx.bindValue(WidgetProperties.enabled().observe(removeButton),
                selectionObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(nonNullConverter())
                        .create());
        return orphanComposite;
    }

    private Composite createMenuTable(Composite parent) {
        Composite menuComposite = toolkit.createComposite(parent);
        menuComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        menuComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        WritableList<NavigationPageNode> inputList = new WritableList<>(menuPageNodes, NavigationPageNode.class);
        menuPageNodes.stream()
                .forEach(navigationPageNode -> navigationPageNode.flattened()
                        .forEach(navPageNode -> navPageNode.addChangeListener(this::valueChangeListener)));
        inputList.addChangeListener(this::valueChangeListener);

        Composite buttonsComposite = toolkit.createComposite(menuComposite);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, LayoutConstants.getSpacing().y).create());
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        menuTreeViewer = new TreeViewer(menuComposite, SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
        menuTreeViewer.getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.la.ex.ui.editor.menuTreeViewer");
        toolkit.adapt(menuTreeViewer.getTree(), true, true);
        menuTreeViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        ColumnViewerToolTipSupport.enableFor(menuTreeViewer);

        TableLayout layout = new TableLayout();

        layout.addColumnData(new ColumnWeightData(2, 300, true));
        layout.addColumnData(new ColumnWeightData(1, 200, true));
        layout.addColumnData(new ColumnWeightData(1, 200, true));

        menuTreeViewer.getTree().setLayout(layout);
        menuTreeViewer.getTree().setHeaderVisible(true);

        createMenuColumn(menuTreeViewer);
        createPageColumn(menuTreeViewer);

        menuTreeViewer.setContentProvider(new NavigationPageTreeContentProvider());
        menuTreeViewer.setInput(inputList);

        IViewerObservableValue selectionObservable = ViewerProperties.singleSelection().observe(menuTreeViewer);

        Composite addRemoveComposite = toolkit.createComposite(buttonsComposite);
        addRemoveComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 3).create());
        addRemoveComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createButton(addRemoveComposite, Messages.addOnePageMenu, toolkit).addListener(SWT.Selection, event -> {
            NavigationPageNode newElement = new NavigationPageNode(Messages.defaultMenuLabel, "", "");
            inputList.add(newElement);
            newElement.addChangeListener(this::valueChangeListener);
            getDisplay().asyncExec(() -> {
                menuTreeViewer.refresh();
                menuTreeViewer.editElement(newElement, 0);
            });
        });

        Button addTopMenuButton = createButton(addRemoveComposite, Messages.addMultiPageMenu, toolkit);
        addTopMenuButton.addListener(SWT.Selection, event -> {
            NavigationPageNode newElement = new NavigationPageNode(Messages.defaultMenuLabel);

            NavigationPageNode firstChild = new NavigationPageNode(Messages.defaultMenuLabel, "", "");
            newElement.addChild(firstChild);
            menuTreeViewer.add(newElement, firstChild);
            getDisplay().asyncExec(menuTreeViewer::expandAll);

            firstChild.addChangeListener(this::valueChangeListener);
            newElement.addChangeListener(this::valueChangeListener);

            inputList.add(newElement);
            getDisplay().asyncExec(() -> {
                menuTreeViewer.refresh();
                menuTreeViewer.editElement(firstChild, 0);
            });
        });

        Button addChildButton = createButton(addRemoveComposite, Messages.addChild, toolkit);
        addChildButton.addListener(SWT.Selection, event -> {
            NavigationPageNode newElement = new NavigationPageNode(Messages.defaultMenuLabel, "", "");
            NavigationPageNode selectedNode = (NavigationPageNode) selectionObservable.getValue();
            selectedNode.addChild(newElement);
            menuTreeViewer.add(selectedNode, newElement);
            getDisplay().asyncExec(menuTreeViewer::expandAll);
            newElement.addChangeListener(this::valueChangeListener);
            getDisplay().asyncExec(() -> {
                menuTreeViewer.refresh();
                menuTreeViewer.editElement(newElement, 0);
            });
        });

        Button removeButton = createButton(addRemoveComposite, Messages.remove, toolkit);
        removeButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, "org.bonitasoft.studio.la.ex.ui.editor.menuRemove");
        removeButton.addListener(SWT.Selection, event -> {
            NavigationPageNode selectedNode = (NavigationPageNode) selectionObservable.getValue();
            if (selectedNode.getParent().isPresent()) {
                NavigationPageNode nodeParent = selectedNode.getParent().get();
                nodeParent.removeChild(selectedNode);
                menuTreeViewer.remove(selectedNode);
                if (nodeParent.getChildren().isEmpty()) {
                    inputList.remove(nodeParent);
                }
            } else {
                inputList.remove(selectedNode);
            }
            refreshViewers();
        });

        toolkit.createSeparator(buttonsComposite, SWT.HORIZONTAL)
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Composite upDownComposite = toolkit.createComposite(buttonsComposite);
        upDownComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 3).create());
        upDownComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Button upButton = createButton(upDownComposite, Messages.up, toolkit);
        upButton.addListener(SWT.Selection, new MoveMenuListener(-1, selectionObservable, inputList, this));

        Button downButton = createButton(upDownComposite, Messages.down, toolkit);
        downButton.addListener(SWT.Selection, new MoveMenuListener(1, selectionObservable, inputList, this));

        ctx.bindValue(WidgetProperties.enabled().observe(removeButton),
                selectionObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(nonNullConverter())
                        .create());

        ctx.bindValue(WidgetProperties.enabled().observe(addChildButton), selectionObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(isTopMenuConverter())
                        .create());

        ctx.bindValue(WidgetProperties.enabled().observe(upButton), selectionObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(upAvailableConverter())
                        .create());

        ctx.bindValue(WidgetProperties.enabled().observe(downButton), selectionObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(downAvailableConverter())
                        .create());

        getDisplay().asyncExec(menuTreeViewer::expandAll);
        return menuComposite;
    }

    private TreeViewerColumn createMenuColumn(TreeViewer viewer) {
        TreeViewerColumn menuColumn = new TreeViewerColumn(viewer, SWT.FILL);
        menuColumn.getColumn().setText(Messages.menu);
        menuColumn.setLabelProvider(new LabelProviderBuilder<NavigationPageNode>()
                .withTextProvider(NavigationPageNode::getMenuLabel)
                .withStatusProvider(ApplicationDescriptorValidators.menuColumnValidator())
                .createColumnLabelProvider());
        menuColumn.setEditingSupport(new EditingSupportBuilder<NavigationPageNode>(viewer)
                .withValueProvider(NavigationPageNode::getMenuLabel)
                .withValueUpdater((node, menuLabel) -> node.setMenuLabel((String) menuLabel))
                .withId(SWTBotConstants.SWTBOT_ID_APPLICATION_MENU_COLUMN_EDITOR)
                .create());
        return menuColumn;
    }

    private TreeViewerColumn createPageColumn(TreeViewer viewer) {
        TreeViewerColumn applicationColumn = new TreeViewerColumn(viewer, SWT.FILL);
        applicationColumn.getColumn().setText(Messages.applicationPage);
        applicationColumn.setLabelProvider(new LabelProviderBuilder<NavigationPageNode>()
                .withTextProvider(this::toApplicationPageDisplayName)
                .withStatusProvider(applicationPageColumnValidator(customPageProvider))
                .createColumnLabelProvider());
        applicationColumn.setEditingSupport(new EditingSupportBuilder<NavigationPageNode>(viewer)
                .withCellEditorProvider(applicationPageCellEditor(viewer, customPageProvider))
                .withValueProvider(NavigationPageNode::getApplicationPage)
                .withValueUpdater((node, id) -> node.setApplicationPage((String) id))
                .withCanEditProvider(isTopMenu().negate())
                .create());
        return applicationColumn;
    }

    private String toApplicationPageDisplayName(NavigationPageNode node) {
        String name = node.getApplicationPage();
        if (name != null && name.startsWith(CustomPageDescriptor.CUSTOMPAGE_PREFIX)) {
            return name.substring(CustomPageDescriptor.CUSTOMPAGE_PREFIX.length(), name.length());
        }
        return name;
    }

    private TreeViewerColumn createTokenColumn(TreeViewer viewer) {
        TreeViewerColumn pageTokenColumn = new TreeViewerColumn(viewer, SWT.FILL);
        pageTokenColumn.getColumn().setText(Messages.applicationPageToken);
        pageTokenColumn.setLabelProvider(new LabelProviderBuilder<NavigationPageNode>()
                .withTextProvider(NavigationPageNode::getApplicationToken)
                .withStatusProvider(tokenPageColumnValidator(menuAndOrphanList))
                .shouldRefreshAllLabels(menuTreeViewer, orphanPagesTreeViewer)
                .createColumnLabelProvider());
        pageTokenColumn.setEditingSupport(new EditingSupportBuilder<NavigationPageNode>(viewer)
                .withValueProvider(NavigationPageNode::getApplicationToken)
                .withValueUpdater((node, token) -> node.setApplicationToken((String) token))
                .withCanEditProvider(isTopMenu().negate())
                .withId(SWTBotConstants.SWTBOT_ID_APPLICATION_TOKEN_COLUMN_EDITOR)
                .create());
        return pageTokenColumn;
    }

    public void valueChangeListener(EventObject event) {
        changeListener(event);
        ctx.updateTargets();
        getDisplay().asyncExec(menuTreeViewer::expandAll);
    }

    public void changeListener(EventObject event) {
        menuAndOrphanList.clear();
        menuAndOrphanList.addAll(menuPageNodes);
        menuAndOrphanList.addAll(orphanPageNode);
        tokenProposalProvider.setProposals(getTokenList().stream().toArray(String[]::new));
        updateApplicationNodeOperation.updateApplicationNode(menuAndOrphanList);
        formPage.makeDirty();
    }

    private List<String> getTokenList() {
        List<String> tokenList = new ArrayList<>();
        menuAndOrphanList.stream()
                .filter(node -> !node.isTopMenu())
                .map(NavigationPageNode::getApplicationToken)
                .filter(Objects::nonNull)
                .filter(token -> !token.isEmpty())
                .distinct()
                .forEach(tokenList::add);
        menuAndOrphanList.stream()
                .filter(NavigationPageNode::isTopMenu)
                .map(NavigationPageNode::getChildren)
                .forEach(children -> children.stream()
                        .map(NavigationPageNode::getApplicationToken)
                        .filter(Objects::nonNull)
                        .filter(token -> !token.isEmpty())
                        .distinct()
                        .filter(token -> !tokenList.contains(token))
                        .forEach(tokenList::add));
        if (tokenList.isEmpty()) {
            tokenList.add("");
        }
        return tokenList;
    }

    private Button createButton(Composite buttonsComposite, String text, FormToolkit toolkit) {
        Button button = toolkit.createButton(buttonsComposite, text, SWT.FLAT);
        button.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        return button;
    }

    private Function<NavigationPageNode, CellEditor> applicationPageCellEditor(ColumnViewer viewer,
            CustomPageProvider customPageProvider) {
        return node -> {
            AutoCompleteTextCellEditor cellEditor = new AutoCompleteTextCellEditor(viewer);
            cellEditor.setProposalProvider(new ApplicationPageProposalProvider(customPageProvider, true));
            cellEditor.getControl().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                    SWTBotConstants.SWTBOT_ID_APPLICATION_PAGE_COLUMN_EDITOR);
            return cellEditor;
        };
    }

    private Predicate<NavigationPageNode> isTopMenu() {
        return NavigationPageNode::isTopMenu;
    }

    private IConverter nonNullConverter() {
        return ConverterBuilder.<NavigationPageNode, Boolean> newConverter()
                .fromType(NavigationPageNode.class)
                .toType(Boolean.class)
                .withConvertFunction(Objects::nonNull)
                .create();
    }

    private IConverter isTopMenuConverter() {
        return ConverterBuilder.<NavigationPageNode, Boolean> newConverter()
                .fromType(NavigationPageNode.class)
                .toType(Boolean.class)
                .withConvertFunction(selection -> Objects.nonNull(selection) && selection.isTopMenu())
                .create();
    }

    private IConverter upAvailableConverter() {
        return ConverterBuilder.<NavigationPageNode, Boolean> newConverter()
                .fromType(NavigationPageNode.class)
                .toType(Boolean.class)
                .withConvertFunction(selection -> Objects.nonNull(selection)
                        && (menuPageNodes.contains(selection)
                                ? menuPageNodes.indexOf(selection) > 0
                                : selection.getParent().isPresent()
                                        ? selection.getParent().get().getChildren().indexOf(selection) > 0
                                        : false))
                .create();
    }

    private IConverter downAvailableConverter() {
        return ConverterBuilder.<NavigationPageNode, Boolean> newConverter()
                .fromType(NavigationPageNode.class)
                .toType(Boolean.class)
                .withConvertFunction(selection -> Objects.nonNull(selection)
                        && (menuPageNodes.contains(selection)
                                ? menuPageNodes.indexOf(selection) < menuPageNodes.size() - 1
                                : selection.getParent().isPresent()
                                        ? selection.getParent().get().getChildren()
                                                .indexOf(selection) < selection.getParent().get().getChildren().size() - 1
                                        : false))
                .create();
    }

    public TreeViewer getMenuTreeViewer() {
        return menuTreeViewer;
    }

    private void refreshViewers() {
        getDisplay().asyncExec(() -> {
            menuTreeViewer.refresh();
            orphanPagesTreeViewer.refresh();
        });
    }
}
