/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.dialog.WarningMessageDialogRunnable;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.ClassGenerator;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.studio.connectors.repository.ConnectorDefFileStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplFileStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.swt.widgets.Display;
import org.ow2.bonita.connector.core.ConnectorDescription;
import org.ow2.bonita.connector.core.desc.Array;
import org.ow2.bonita.connector.core.desc.Checkbox;
import org.ow2.bonita.connector.core.desc.Component;
import org.ow2.bonita.connector.core.desc.Getter;
import org.ow2.bonita.connector.core.desc.Group;
import org.ow2.bonita.connector.core.desc.Password;
import org.ow2.bonita.connector.core.desc.Radio;
import org.ow2.bonita.connector.core.desc.Select;
import org.ow2.bonita.connector.core.desc.Setter;
import org.ow2.bonita.connector.core.desc.SimpleList;
import org.ow2.bonita.connector.core.desc.Text;
import org.ow2.bonita.connector.core.desc.Textarea;
import org.ow2.bonita.connector.core.desc.WidgetComponent;
import org.ow2.bonita.facade.runtime.AttachmentInstance;
import org.w3c.dom.Document;

/**
 * @author Romain Bioteau
 */
public class ConnectorDescriptorToConnectorDefinition {

    private static final String CONNECTOR_ID = "ConnectorId";
    private static final String DESCRIPTION = "Description";
    private static final String BASE_VERSION = "1.0.0";
    private static final String MIGRATION_COMMENT = "	//Following code has been retrieved from a v5 connector. Please adapt this code with Bonita 6 API";
    private static final String DEFAULT_PACKAGE = "com.mycompany.connector.";

    private final ConnectorDescription v5Descriptor;
    private final File tmpConnectorJarFile;
    private IProgressMonitor monitor;

    public ConnectorDescriptorToConnectorDefinition(
            final ConnectorDescription descriptor, final File tmpConnectorJarFile, final IProgressMonitor progressMonitor) {
        Assert.isNotNull(descriptor);
        v5Descriptor = descriptor;
        this.tmpConnectorJarFile = tmpConnectorJarFile;
        monitor = progressMonitor;
        if (monitor == null) {
            monitor = Repository.NULL_PROGRESS_MONITOR;
        }

    }

    public void createConnectorDefinition() throws IOException {
        final String connectorId = v5Descriptor.getId();
        final String connectorVersion = BASE_VERSION;
        monitor.subTask(Messages.bind(Messages.migratingCustomConnector, connectorId));
        final List<org.ow2.bonita.connector.core.desc.Category> v5Categories = v5Descriptor.getCategories();
        final ConnectorDefRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        final ConnectorDefFileStore connectorDefStore = store.getChild(NamingUtils.toConnectorDefinitionFilename(connectorId, connectorVersion, true));
        if (connectorDefStore != null) {
            if (FileActionDialog.overwriteQuestion(connectorDefStore.getName())) {
                initConnectorDefinition(connectorId, connectorVersion, v5Categories,
                        store);
            }
        } else {
            initConnectorDefinition(connectorId, connectorVersion, v5Categories,
                    store);
        }
    }

    private void initConnectorDefinition(
            final String connectorId,
            final String connectorVersion,
            final List<org.ow2.bonita.connector.core.desc.Category> v5Categories,
            final ConnectorDefRepositoryStore store) throws IOException {
        final ConnectorDefinition connectorDefinition = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        connectorDefinition.setId(connectorId);
        connectorDefinition.setVersion(connectorVersion);
        if (v5Descriptor.getIconPath() != null) {
            connectorDefinition.setIcon(getIconName(v5Descriptor.getIconPath()));
        }
        final List<Category> categories = createCategories(v5Categories);
        connectorDefinition.getCategory().addAll(categories);

        addInputs(connectorDefinition);
        addOutputs(connectorDefinition);
        addPages(connectorDefinition);

        final ConnectorDefFileStore file = store.createRepositoryFileStore(NamingUtils.toConnectorDefinitionFilename(connectorId, connectorVersion, true));
        file.save(connectorDefinition);
    }

    public void createConnectorImplementation() throws Exception {
        final ConnectorImplementation connectorImplementation = ConnectorImplementationFactory.eINSTANCE.createConnectorImplementation();
        final String implementationId = v5Descriptor.getId() + "-impl";
        connectorImplementation.setImplementationId(implementationId);
        connectorImplementation.setImplementationVersion(BASE_VERSION);
        connectorImplementation.setDefinitionVersion(BASE_VERSION);
        connectorImplementation.setDefinitionId(v5Descriptor.getId());
        connectorImplementation.setHasSources(true);
        connectorImplementation.setDescription(v5Descriptor.getDescription());
        connectorImplementation.setImplementationClassname(getNewImplementationClassName());
        connectorImplementation.setJarDependencies(ConnectorImplementationFactory.eINSTANCE.createJarDependencies());

        final ConnectorDefRepositoryStore defStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        final ConnectorSourceRepositoryStore sourceStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class);
        final ConnectorDefinition definition = ((IDefinitionRepositoryStore) defStore).getDefinition(connectorImplementation.getDefinitionId(),
                connectorImplementation.getDefinitionVersion());
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    ClassGenerator.generateConnectorImplementationAbstractClass(connectorImplementation, definition, AbstractConnector.class.getName(),
                            sourceStore, Repository.NULL_PROGRESS_MONITOR);
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                    throw new RuntimeException(e);
                }
            }
        });

        final IType classType = RepositoryManager.getInstance().getCurrentRepository().getJavaProject()
                .findType(connectorImplementation.getImplementationClassname());
        if (classType != null) {
            if (FileActionDialog.overwriteQuestion(classType.getParent().getElementName())) {
                classType.getCompilationUnit().delete(true, Repository.NULL_PROGRESS_MONITOR);
                generateAndMergeConnectorImplClass(connectorImplementation,
                        sourceStore, definition);
            }
        } else {
            generateAndMergeConnectorImplClass(connectorImplementation,
                    sourceStore, definition);
        }

        final ConnectorImplRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
        final ConnectorImplFileStore file = store
                .createRepositoryFileStore(NamingUtils.toConnectorImplementationFilename(implementationId, BASE_VERSION, true));
        file.save(connectorImplementation);
    }

    private void generateAndMergeConnectorImplClass(
            final ConnectorImplementation connectorImplementation,
            final ConnectorSourceRepositoryStore sourceStore,
            final ConnectorDefinition definition) throws Exception, ZipException,
            IOException, CoreException {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    ClassGenerator.generateConnectorImplementationClass(connectorImplementation, definition, sourceStore, Repository.NULL_PROGRESS_MONITOR);
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                    throw new RuntimeException(e);
                }
            }
        });
        mergeSourceFile(v5Descriptor.getConnectorClass().getName(), sourceStore);
    }

    private String getNewImplementationClassName() {
        String name = v5Descriptor.getConnectorClass().getName();
        if (name.indexOf(".") == -1) {//no package
            name = DEFAULT_PACKAGE + name;
        }
        return name + "Impl";
    }

    protected Map<String, String> mergeSourceFile(final String implementationClassname, final ConnectorSourceRepositoryStore sourceStore) throws ZipException,
            IOException, CoreException {
        final ZipFile zipfile = new ZipFile(tmpConnectorJarFile);
        final Enumeration<?> enumEntries = zipfile.entries();
        ZipEntry zipEntry = null;
        boolean sourceImported = false;
        while (enumEntries.hasMoreElements()) {
            zipEntry = (ZipEntry) enumEntries.nextElement();
            if (!zipEntry.isDirectory() && zipEntry.getName().equals(implementationClassname.replace(".", "/") + ".java")) {
                sourceStore.importInputStream(zipEntry.getName(), zipfile.getInputStream(zipEntry));
                sourceImported = true;
                break;
            }
        }
        zipfile.close();
        if (sourceImported) {
            RepositoryManager.getInstance().getCurrentRepository().build(Repository.NULL_PROGRESS_MONITOR);
            final IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
            final IType originalImplType = project.findType(implementationClassname);
            final IType implType = project.findType(getNewImplementationClassName());
            if (originalImplType != null && implType != null) {
                final org.eclipse.jface.text.Document doc = new org.eclipse.jface.text.Document();
                for (final IMethod method : originalImplType.getMethods()) {
                    if (!isGetter(method) && !isSetter(method)) {
                        if (!method.getElementName().equals("executeConnector")) {
                            try {
                                if (!method.isReadOnly()) {
                                    method.copy(implType, null, null, false, Repository.NULL_PROGRESS_MONITOR);
                                }
                            } catch (final CoreException e) {
                                BonitaStudioLog.error(e);
                            }
                        }
                    }
                }
                for (final IMethod method : originalImplType.getMethods()) {
                    if (!isGetter(method) && !isSetter(method)) {
                        if (method.getElementName().equals("executeConnector")) {
                            final IMethod executeMethod = implType.getMethod("executeBusinessLogic", new String[0]);
                            final ISourceRange range = executeMethod.getSourceRange();
                            final String toAdd = MIGRATION_COMMENT
                                    + method.getSource().substring(method.getSource().indexOf("{") + 1, method.getSource().length() - 1);
                            doc.set(implType.getCompilationUnit().getSource());
                            try {
                                doc.replace(range.getOffset() + range.getLength() - 1, 0, toAdd);
                            } catch (final BadLocationException e) {
                                BonitaStudioLog.error(e);
                            }

                        }
                    }
                }
                if (!doc.get().isEmpty()) {
                    BufferedWriter out = null;
                    try {
                        out = new BufferedWriter(new FileWriter(implType.getCompilationUnit().getCorrespondingResource().getLocation().toFile()));
                        out.write(doc.get());
                        out.flush();
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                }
                final ICompilationUnit compilationUnit = originalImplType.getCompilationUnit();
                if (compilationUnit != null) {
                    compilationUnit.delete(true, Repository.NULL_PROGRESS_MONITOR);
                }
                //RepositoryManager.getInstance().getCurrentRepository().refresh(Repository.NULL_PROGRESS_MONITOR);
            }
        }
        return Collections.emptyMap();
    }

    protected boolean isGetter(final IMethod method) throws JavaModelException {
        return method.getElementName().startsWith("get")
                && method.getParameters().length == 0
                && method.getReturnType() != null
                && !method.getReturnType().equals("V")
                && Flags.isPublic(method.getFlags())
                && !Flags.isStatic(method.getFlags());
    }

    protected boolean isSetter(final IMethod method) throws JavaModelException {
        return method.getElementName().startsWith("set")
                && method.getParameters().length == 1
                && method.getReturnType() != null
                && method.getReturnType().equals("V")
                && Flags.isPublic(method.getFlags())
                && !Flags.isStatic(method.getFlags());
    }

    private void addPages(final ConnectorDefinition connectorDefinition) {
        for (final String pageId : v5Descriptor.getPages()) {
            final Page page = createPage(pageId);
            connectorDefinition.getPage().add(page);
        }
    }

    protected Page createPage(final String pageId) {
        final Page page = ConnectorDefinitionFactory.eINSTANCE.createPage();
        page.setId(pageId);

        for (final Component component : v5Descriptor.getAllPageInputs(pageId)) {
            page.getWidget().add(createWidget(component));
        }
        return page;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createWidget(
            final Component component) {
        org.bonitasoft.studio.connector.model.definition.Component widget = null;
        if (component instanceof Password) {
            widget = createPasswordWidget((Password) component);
        } else if (component instanceof Text) {
            widget = createTextWidget((Text) component);
        } else if (component instanceof Radio) {
            widget = createCheckboxWidget((Radio) component);
        } else if (component instanceof Checkbox) {
            widget = createCheckboxWidget((Checkbox) component);
        } else if (component instanceof Select) {
            widget = createSelectWidget((Select) component);
        } else if (component instanceof Array) {
            widget = createTableWidget((Array) component);
        } else if (component instanceof SimpleList) {
            widget = createListWidget((SimpleList) component);
        } else if (component instanceof Textarea) {
            widget = createTextAreaWidget((Textarea) component);
        } else if (component instanceof Group) {
            widget = createGroupWidget((Group) component);
            for (final WidgetComponent wc : ((Group) component).getWidgets()) {
                ((org.bonitasoft.studio.connector.model.definition.Group) widget).getWidget().add(createWidget(wc));
            }
        }
        return widget;
    }

    private String toInputName(final Setter setter) {
        String name = setter.getSetterName();
        if (name.startsWith("set")) {
            name = name.substring(3);
        }
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createGroupWidget(
            final Group component) {
        final org.bonitasoft.studio.connector.model.definition.Group group = ConnectorDefinitionFactory.eINSTANCE.createGroup();
        group.setId(component.getLabelId());
        group.setOptional(component.isOptional());
        return group;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createTextAreaWidget(
            final Textarea component) {
        final TextArea textArea = ConnectorDefinitionFactory.eINSTANCE.createTextArea();
        textArea.setId(component.getLabelId());
        textArea.setInputName(toInputName(component.getSetter()));
        return textArea;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createPasswordWidget(
            final Password component) {
        final org.bonitasoft.studio.connector.model.definition.Password text = ConnectorDefinitionFactory.eINSTANCE.createPassword();
        text.setId(component.getLabelId());
        text.setInputName(toInputName(component.getSetter()));
        return text;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createListWidget(
            final SimpleList component) {
        final org.bonitasoft.studio.connector.model.definition.List list = ConnectorDefinitionFactory.eINSTANCE.createList();
        list.setId(component.getLabelId());
        list.setInputName(toInputName(component.getSetter()));
        return list;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createTableWidget(
            final Array component) {
        final org.bonitasoft.studio.connector.model.definition.Array array = ConnectorDefinitionFactory.eINSTANCE.createArray();
        array.setId(component.getLabelId());
        array.setInputName(toInputName(component.getSetter()));
        array.setCols(BigInteger.valueOf(component.getCols()));
        array.setRows(BigInteger.valueOf(component.getRows()));
        array.setFixedCols(component.isFixedCols());
        array.setFixedRows(component.isFixedRows());
        for (final String caption : component.getColsCaptions()) {
            array.getColsCaption().add(caption);
        }
        return array;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createSelectWidget(
            final Select component) {
        final org.bonitasoft.studio.connector.model.definition.Select select = ConnectorDefinitionFactory.eINSTANCE.createSelect();
        select.setId(component.getLabelId());
        select.setInputName(toInputName(component.getSetter()));
        if (component.getTop() != null) {
            select.getItems().add(component.getTop().getValue());
        }
        for (final String item : component.getValues().values()) {
            if (!select.getItems().contains(item)) {
                select.getItems().add(item);
            }

        }
        select.setReadOnly(true);
        return select;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createCheckboxWidget(
            final Checkbox component) {
        final org.bonitasoft.studio.connector.model.definition.Checkbox checkbox = ConnectorDefinitionFactory.eINSTANCE.createCheckbox();
        checkbox.setId(component.getLabelId());
        checkbox.setInputName(toInputName(component.getSetter()));
        return checkbox;
    }

    protected org.bonitasoft.studio.connector.model.definition.Component createTextWidget(
            final Text component) {
        final org.bonitasoft.studio.connector.model.definition.Text text = ConnectorDefinitionFactory.eINSTANCE.createText();
        text.setId(component.getLabelId());
        text.setInputName(toInputName(component.getSetter()));
        final Object[] param = component.getSetter().getParameters();
        if (param != null && param.length == 1) {
            if (param[0] instanceof AttachmentInstance) {
                text.setShowDocuments(true);
            }
        }
        return text;
    }

    protected void addInputs(final ConnectorDefinition connectorDefinition) {
        for (final Setter setter : v5Descriptor.getInputs()) {
            String defaultValue = null;
            final String name = toInputName(setter);
            final String isRequired = setter.getRequired();
            final Object[] parameters = setter.getParameters();
            final String inputType = getInputType(setter.getParameters());
            if (parameters != null && parameters.length == 1) {
                if (!(parameters[0] instanceof Collection)
                        && !(parameters[0] instanceof Document)
                        && !(parameters[0] instanceof AttachmentInstance)) {
                    if (!inputType.equals(Object.class.getName())) {
                        defaultValue = parameters[0].toString();
                    }
                }
            }
            final Input connectorInput = ConnectorDefinitionFactory.eINSTANCE.createInput();
            connectorInput.setDefaultValue(defaultValue);
            connectorInput.setMandatory(isRequired != null);
            connectorInput.setName(name);
            connectorInput.setType(inputType);
            connectorDefinition.getInput().add(connectorInput);
        }
    }

    protected void addOutputs(final ConnectorDefinition connectorDefinition) {
        for (final Getter getter : v5Descriptor.getOutputs()) {
            final String name = retrieveOutputName(getter);
            final Output connectorOutput = ConnectorDefinitionFactory.eINSTANCE.createOutput();
            connectorOutput.setName(name);
            final Type outputType = v5Descriptor.getOutputType(getter.getName());
            if (outputType instanceof Class) {
                connectorOutput.setType(((Class<?>) outputType).getName());
                connectorDefinition.getOutput().add(connectorOutput);
            } else if (outputType instanceof ParameterizedType) {
                final Type rawType = ((ParameterizedType) outputType).getRawType();
                if (rawType instanceof Class) {
                    connectorOutput.setType(((Class<?>) rawType).getName());
                    connectorDefinition.getOutput().add(connectorOutput);
                } else {
                    BonitaStudioLog.warning("Unknown connector output type " + outputType.toString() + " with Raw type:" + rawType.toString(),
                            BarImporterPlugin.PLUGIN_ID);
                }
            } else {
                BonitaStudioLog.warning("Unknown connector output type " + outputType.toString(), BarImporterPlugin.PLUGIN_ID);
            }
        }
    }

    private String retrieveOutputName(final Getter getter) {
        String name = getter.getName();
        if (name.startsWith("get")) {
            name = name.substring(3);
        }
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

    private String getInputType(final Object[] parameters) {
        if (parameters != null && parameters.length == 1) {
            final Object object = parameters[0];
            if (object instanceof String) {
                return String.class.getName();
            } else if (object instanceof Long) {
                return Long.class.getName();
            } else if (object instanceof Boolean) {
                return Boolean.class.getName();
            } else if (object instanceof Double) {
                return Double.class.getName();
            } else if (object instanceof List) {
                return List.class.getName();
            } else if (object instanceof AttachmentInstance) {
                return String.class.getName();
            } else if (object instanceof Document) {
                return String.class.getName();
            }
        }
        return Object.class.getName();
    }

    protected List<Category> createCategories(
            final List<org.ow2.bonita.connector.core.desc.Category> v5Categories) throws IOException {
        final ConnectorDefRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        final DefinitionResourceProvider resourceProvider = store.getResourceProvider();
        final Set<String> allCategories = resourceProvider.getProvidedCategoriesIds();
        allCategories.addAll(resourceProvider.getUserCategoriesIds());
        final List<Category> categories = new ArrayList<Category>();
        for (final org.ow2.bonita.connector.core.desc.Category c : v5Categories) {
            final String catId = c.getName();
            if (containsIgnoreCase(catId, allCategories)) {
                for (final Category cat : resourceProvider.getAllCategories()) {
                    if (cat.getId().equalsIgnoreCase(catId)) {
                        categories.add(cat);
                        allCategories.add(catId);
                    }
                }
            } else {
                categories.add(createCategory(c));
            }
        }
        return categories;
    }

    private boolean containsIgnoreCase(final String id, final Set<String> l) {
        for (final String s : l) {
            if (s.equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    private Category createCategory(
            final org.ow2.bonita.connector.core.desc.Category c) throws IOException {
        final ConnectorDefRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        final Category category = ConnectorDefinitionFactory.eINSTANCE.createCategory();
        category.setId(c.getName());
        if (c.getIconPath() != null && !c.getIconPath().isEmpty()) {
            category.setIcon(getIconName(c.getIconPath()));
            InputStream iconInputstream = c.getIcon();
            if (iconInputstream != null) {
                BufferedImage image = ImageIO.read(iconInputstream);
                image = FileUtil.resizeImage(image, 16);
                final File createTempFile = File.createTempFile("icon", ".png");
                final FileOutputStream resizedStream = new FileOutputStream(createTempFile);
                ImageIO.write(image, "PNG", resizedStream);
                resizedStream.close();
                iconInputstream.close();
                iconInputstream = new FileInputStream(createTempFile);
                store.importInputStream(category.getIcon(), iconInputstream);
                createTempFile.delete();
            }
        }
        return category;
    }

    public void importConnectorDefinitionResources() throws ZipException, IOException {
        final ConnectorDefRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        if (v5Descriptor.getIconPath() != null && !v5Descriptor.getIconPath().isEmpty()) {
            InputStream iconInputStream = v5Descriptor.getIcon();
            final String iconName = getIconName(v5Descriptor.getIconPath());
            if (iconInputStream != null && isSupportedIconExtension(iconName)) {
                BufferedImage image = ImageIO.read(iconInputStream);
                image = FileUtil.resizeImage(image, 16);
                final File createTempFile = File.createTempFile("icon", ".png");
                final FileOutputStream resizedStream = new FileOutputStream(createTempFile);
                ImageIO.write(image, "PNG", resizedStream);
                resizedStream.close();
                iconInputStream.close();
                iconInputStream = new FileInputStream(createTempFile);
                store.importInputStream(iconName, iconInputStream);
                createTempFile.delete();
            }
        }
        importI18NFiles();
    }

    private boolean isSupportedIconExtension(final String iconName) {
        if (iconName.toLowerCase().matches(".*\\.jpg|.*\\.jpeg|.*\\.gif|.*\\.png|.*\\.bmp")) {
            return Boolean.TRUE;
        } else {
            Display.getDefault().syncExec(
                    new WarningMessageDialogRunnable(Messages.warningImageFormat, Messages.bind(Messages.warningImageFormatMessage, iconName)));
            return Boolean.FALSE;
        }
    }

    protected void importI18NFiles() throws ZipException, IOException {
        final ConnectorDefRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        final ZipFile zipfile = new ZipFile(tmpConnectorJarFile);
        final Enumeration<?> enumEntries = zipfile.entries();
        ZipEntry zipEntry = null;
        while (enumEntries.hasMoreElements()) {
            zipEntry = (ZipEntry) enumEntries.nextElement();
            final File currentFile = new File(zipEntry.getName());
            if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".properties")) {
                String name = currentFile.getName();
                String locale = "";
                if (name.indexOf("_") != -1) {
                    locale = name.substring(name.lastIndexOf("_"), name.lastIndexOf("."));
                    name = name.substring(0, name.lastIndexOf("_"));
                }
                if (name.endsWith(".properties")) {
                    name = name.substring(0, name.lastIndexOf("."));
                }
                if (name.equals(v5Descriptor.getId())) {
                    name = NamingUtils.toConnectorDefinitionFilename(v5Descriptor.getId(), BASE_VERSION, false) + locale + ".properties";
                    final InputStream stream = transformPropertiesFile(name, zipfile.getInputStream(zipEntry));
                    store.importInputStream(name, stream);
                }
            }
        }
        zipfile.close();
    }

    private InputStream transformPropertiesFile(final String fileName, final InputStream inputStream) throws IOException {
        FileOutputStream out = null;
        final File file = new File(ProjectUtil.getBonitaStudioWorkFolder(), fileName);
        try {
            final Properties p = new Properties();
            p.load(inputStream);
            final Set<Object> keySet = new HashSet<Object>(p.keySet());
            for (final Object key : keySet) {
                if (key.equals(CONNECTOR_ID)) {
                    replaceProperty(p, key, DefinitionResourceProvider.connectorDefinition);
                } else if (key.equals(DESCRIPTION)) {
                    replaceProperty(p, key, DefinitionResourceProvider.connectorDefinitionDescription);
                }
                for (final org.ow2.bonita.connector.core.desc.Category c : v5Descriptor.getCategories()) {
                    if (key.equals(c.getName())) {
                        replaceProperty(p, key, c.getName() + "." + DefinitionResourceProvider.category);
                    }
                }
                for (final String pageId : v5Descriptor.getPages()) {
                    if (key.equals(pageId)) {
                        replaceProperty(p, key, pageId + "." + DefinitionResourceProvider.pageTilte);
                    } else if (key.equals(pageId + ".description")) {
                        replaceProperty(p, key, pageId + "." + DefinitionResourceProvider.pageDescription);
                    }
                }
            }
            file.delete();
            out = new FileOutputStream(file);
            p.store(out, null);
        } finally {
            if (out != null) {
                out.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new FileInputStream(file);
    }

    private void replaceProperty(final Properties p, final Object key, final String newKey) {
        final Object value = p.get(key);
        if (value != null) {
            p.put(newKey, value);
        }
        p.remove(key);
    }

    protected String getIconName(final String iconPath) {
        String iconName = iconPath;
        if (iconName.indexOf("/") != -1) {
            iconName = iconName.substring(iconName.lastIndexOf("/") + 1, iconName.length());
        }
        return iconName;
    }

    public Change createReportChange() {
        final Change change = MigrationReportFactory.eINSTANCE.createChange();
        change.setElementName(v5Descriptor.getId());
        change.setElementType(Messages.customConnector);
        change.setElementUUID("");
        change.setDescription(Messages.customConnectorMigrationDescription);
        change.setPropertyName(Messages.development);
        change.setStatus(IStatus.WARNING);
        return change;
    }

}
