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
package org.bonitasoft.studio.application.views.extension.card.zoom;

import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.Implementation;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.views.BonitaPropertiesView;
import org.bonitasoft.studio.application.views.extension.card.zoom.usage.ConnectorUsagesControlSupplier;
import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorPart;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.ConnectorDefinitionRegistry;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.IPage;

public class ConnectorZoomControl extends AbstractZoomControl {

    public ConnectorZoomControl(Composite parent,
            ZoomListener zoomListener,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, zoomListener, dep, bonitaDep);
    }

    @Override
    protected void createDetailsSection(Composite parent) {
        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 20, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var titleComposite = createComposite(composite, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var sectionTitle = new Label(titleComposite, SWT.WRAP);
        sectionTitle.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        sectionTitle.setText(getDetailsTitle());
        sectionTitle.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        sectionTitle.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
        sectionTitle.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_4_FONT_ID));

        var iconLabel = new Label(titleComposite, SWT.NONE);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.FILL)
                .span(1, 2)
                .hint(BonitaMarketplacePage.ICON_SIZE, BonitaMarketplacePage.ICON_SIZE)
                .create());
        iconLabel.setImage(bonitaDep.getIconImage());

        var hint = new Label(titleComposite, SWT.WRAP);
        hint.setLayoutData(GridDataFactory.fillDefaults().indent(10, 0).create());
        hint.setText(getDetailsHint());
        hint.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var definitionsComposite = createComposite(composite, SWT.NONE);
        definitionsComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 10).create());
        definitionsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        getDefinitions().forEach(def -> createConnectorDetails(definitionsComposite, def));

        var separator = new Label(definitionsComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);
    }

    protected String getDetailsHint() {
        return String.format(Messages.connectorsHint, bonitaDep.getName());
    }

    protected String getDetailsTitle() {
        return Messages.connectors;
    }

    private void createConnectorDetails(Composite parent, ExtendedConnectorDefinition extendedDefinition) {
        var separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);

        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true).margins(10, 10)
                .spacing(LayoutConstants.getSpacing().x, 20).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createIdVersionStyledText(composite, extendedDefinition.getConnectorDefinitionLabel(),
                extendedDefinition.getVersion(), JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_0_FONT_ID), 1);

        new DynamicButtonWidget.Builder()
                .withLabel(Messages.findUsages)
                .withId(SWTBotConstants.findUsageButtonId(extendedDefinition.getId(), extendedDefinition.getVersion()))
                .withImage(Pics.getImage(PicsConstants.findUsages))
                .withHotImage(Pics.getImage(PicsConstants.findUsagesHot))
                .withLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.TOP).create())
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> findUsages(extendedDefinition))
                .createIn(composite);

        var descriptionContainer = createComposite(composite, SWT.NONE);
        descriptionContainer.setLayoutData(GridDataFactory.fillDefaults().indent(10, 0).grab(true, true).create());
        descriptionContainer.setLayout(GridLayoutFactory.fillDefaults().create());

        var description = new Label(descriptionContainer, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        description.setText(extendedDefinition.getConnectorDefinitionDescription());
        description.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.ITALIC_0_FONT_ID));
        description.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var implementationsComposite = createComposite(composite, SWT.NONE);
        implementationsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        implementationsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var implementationsLabel = new Label(implementationsComposite, SWT.WRAP);
        implementationsLabel.setLayoutData(GridDataFactory.fillDefaults().indent(30, 0).create());
        implementationsLabel.setText(Messages.implementations);
        implementationsLabel.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_0_FONT_ID));
        implementationsLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var implementations = getImplementations(extendedDefinition);

        var implementationListComposite = createComposite(implementationsComposite, SWT.NONE);
        implementationListComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 0).create());
        implementationListComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.FILL).create());

        implementations.forEach(impl -> createIdVersionStyledText(implementationListComposite,
                impl.getImplementationId(),
                impl.getImplementationVersion(), null, 1));
    }

    private void findUsages(ExtendedConnectorDefinition definition) {
        ConnectorUsagesControlSupplier connectorUsagesPage = getUsagesControlSupplier(definition);
        WizardBuilder.<Map<DiagramFileStore, List<Element>>> newWizard()
                .withTitle(Messages.usages)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.usages)
                        .withDescription(String.format(Messages.connectorUsages, definition.getConnectorDefinitionLabel()))
                        .withControl(connectorUsagesPage))
                .onFinish(container -> performFinish(connectorUsagesPage))
                .withSize(SWT.DEFAULT, 700)
                .withFixedInitialSize()
                .open(getShell(), IDialogConstants.OPEN_LABEL)
                .ifPresent(dToOpen -> dToOpen.entrySet().forEach(this::open));
    }

    protected ConnectorUsagesControlSupplier getUsagesControlSupplier(ExtendedConnectorDefinition definition) {
        return new ConnectorUsagesControlSupplier(definition);
    }

    private void openDiagram(DiagramFileStore fileStore, Element element) {
        fileStore.open();
        DiagramEditor editor = fileStore.getOpenedEditor();
        if (element instanceof ConnectableElement) {
            ModelHelper.getAllElementOfTypeIn(editor.getDiagram().getElement(), Element.class).stream()
                    .filter(elt -> sameElement(elt, element))
                    .findFirst()
                    .ifPresent(elt -> selectElement(editor, elt));
        }
    }

    protected void selectElement(DiagramEditor editor, Element element) {
        IGraphicalEditPart findEditPart = GMFTools.findEditPart(editor.getDiagramEditPart(),
                element);
        if (findEditPart != null) {
            Display.getDefault().syncExec(() -> editor.getDiagramGraphicalViewer().select(findEditPart));
            IWorkbenchPage page = editor.getEditorSite().getPage();
            String executionViewId = "org.bonitasoft.studio.views.properties.process.execution";
            BonitaPropertiesView executionView = (BonitaPropertiesView) page.findView(executionViewId);
            if (executionView != null) {
                try {
                    page.showView(executionViewId);
                    IPage currentPage = executionView.getCurrentPage();
                    if (currentPage instanceof BonitaPropertiesBrowserPage) {
                        ((BonitaPropertiesBrowserPage) currentPage).selectionChanged(editor,
                                editor.getDiagramGraphicalViewer().getSelection());
                    }
                } catch (PartInitException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    private boolean sameElement(Element a1, Element a2) {
        if (Objects.equals(a1.getName(), a2.getName())) {
            AbstractProcess p1 = getProcess(a1);
            AbstractProcess p2 = getProcess(a2);
            return p1 != null && p2 != null && Objects.equals(p1.getName(), p2.getName());
        }
        return false;
    }

    private AbstractProcess getProcess(Element element) {
        EObject o = element.eContainer();
        while (o != null && !(o instanceof AbstractProcess)) {
            o = o.eContainer();
        }
        return (AbstractProcess) o;
    }

    private Optional<Map<DiagramFileStore, List<Element>>> performFinish(
            ConnectorUsagesControlSupplier connectorUsagesPage) {
        var elementsToOpen = connectorUsagesPage.getCheckedElements();
        Map<DiagramFileStore, List<Element>> diagramsToOpen = new HashMap();
        elementsToOpen.forEach(element -> {
            EObject mainProcess = element;
            while (!(mainProcess instanceof MainProcess)) {
                mainProcess = mainProcess.eContainer();
            }
            DiagramFileStore diagram = RepositoryManager.getInstance()
                    .getRepositoryStore(DiagramRepositoryStore.class)
                    .getDiagram(((MainProcess) mainProcess).getName(), ((MainProcess) mainProcess).getVersion());
            if (!diagramsToOpen.containsKey(diagram)) {
                diagramsToOpen.put(diagram, new ArrayList());
            }
            diagramsToOpen.get(diagram).add(element);
        });
        return Optional.of(diagramsToOpen);
    }

    private void open(Entry<DiagramFileStore, List<Element>> entry) {
        openDiagram(entry.getKey(), entry.getValue().stream().findFirst().orElseThrow());
    }

    private void createIdVersionStyledText(Composite parent, String id, String version, Font font, int horizontalSpan) {
        var styledText = new StyledText(parent, SWT.WRAP);
        styledText.setEditable(false);
        styledText.setEnabled(false);
        styledText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(horizontalSpan, 1).create());
        styledText.setText(String.format("%s (%s)", id, version));
        styledText.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        styledText.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        Display.getDefault().asyncExec(() -> {
            StyleRange definitionStyle = new StyleRange(0, id.length(), styledText.getForeground(),
                    styledText.getBackground());
            definitionStyle.font = font;
            StyleRange versionStyle = new StyleRange(id.length() + 1,
                    version.length() + 2,
                    gav.getForeground(), styledText.getBackground());
            versionStyle.font = JFaceResources.getFont(ProjectOverviewEditorPart.ITALIC_0_FONT_ID);
            styledText.setStyleRanges(new StyleRange[] { definitionStyle, versionStyle });
            parent.layout();
        });
    }

    protected List<ExtendedConnectorDefinition> getDefinitions() {
        ConnectorDefinitionRegistry registry = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class).getResourceProvider()
                .getConnectorDefinitionRegistry();
        return projectDependenciesStore.getConnectorDefinitions().stream()
                .filter(def -> Objects.equals(def.getArtifact().getGroupId(), dep.getGroupId()))
                .filter(def -> Objects.equals(def.getArtifact().getArtifactId(), dep.getArtifactId()))
                .filter(def -> Objects.equals(def.getArtifact().getVersion(), dep.getVersion()))
                .map(def -> registry.find(def.getDefinitionId(), def.getDefinitionVersion()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted((def1, def2) -> def1.getConnectorDefinitionLabel().compareTo(def2.getConnectorDefinitionLabel()))
                .collect(Collectors.toList());
    }

    protected List<Implementation> getImplementations(ExtendedConnectorDefinition def) {
        return projectDependenciesStore.getConnectorImplementations().stream()
                .filter(impl -> Objects.equals(impl.getDefinitionId(), def.getId()))
                .filter(impl -> Objects.equals(impl.getDefinitionVersion(), def.getVersion()))
                .collect(Collectors.toList());
    }

}
