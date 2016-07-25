/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.form.preview;

import java.util.List;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.form.preview.i18n.Messages;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;

/**
 * @Author Aur�lie Zara
 */
public abstract class AbstractFormPreviewInitialization {

    protected Form form;
    protected Form formCopy;
    protected ApplicationLookNFeelFileStore lookNFeel;
    protected IBrowserDescriptor browser;
    public static final String VERSION = "1.0";
    private static final String EMPTY_LIST = "empty_list";
    private static final String GROOVY_SCRIPT_EMPTY_LIST = "[]";
    protected boolean isOnTask = false;
    protected boolean canPreview = true;

    public AbstractFormPreviewInitialization(final Form form, final ApplicationLookNFeelFileStore lookNFeel, final IBrowserDescriptor browser) {
        this.form = form;
        this.lookNFeel = lookNFeel;
        this.browser = browser;
        initializeForm();
        initializeAllWidgets(formCopy);
    }

    public AbstractProcess createAbstractProcess(final Configuration configuration) {
        final AbstractProcess proc = ProcessFactory.eINSTANCE.createPool();
        proc.setName(form.getName() + " preview");
        proc.setVersion(VERSION);
        final FormMapping formMapping = ProcessFactory.eINSTANCE.createFormMapping();
        formMapping.setType(FormMappingType.LEGACY);
        proc.setFormMapping(formMapping);
        if (lookNFeel != null && lookNFeel.getName() != null) {
            proc.setBasedOnLookAndFeel(lookNFeel.getName());
        }
        final Element parent = ModelHelper.getParentFlowElement(form);
        if (parent == null) {
            proc.getForm().add(formCopy);
            initializeProcessActor(configuration, proc);
        } else {
            if (parent instanceof Task) {
                initializeTask(parent, proc, configuration);
            }
        }
        configuration.setUsername(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
        configuration.setPassword("bpm");
        return proc;
    }

    protected void initializeProcessActor(final Configuration configuration, final AbstractProcess proc) {
        AbstractProcess parentProcess = null;
        if (form.eContainer() instanceof AbstractProcess) {
            parentProcess = (AbstractProcess) form.eContainer();
        } else {
            parentProcess = ProcessFactory.eINSTANCE.createPool();
            parentProcess.setName("Preview template");
            parentProcess.setVersion("1.0");
            final Actor actor = ProcessFactory.eINSTANCE.createActor();
            actor.setInitiator(true);
            actor.setName("preview");
            parentProcess.getActors().add(actor);
        }
        if (parentProcess.getActors().isEmpty()) {
            canPreview = false;
            openNoActorErrorMessage(parentProcess);

        } else {
            copyActors(parentProcess, proc);
            setActorMapping(parentProcess, configuration);
        }

    }

    /**
     * @param parentProcess
     */
    protected void openNoActorErrorMessage(final Element element) {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.noActorDefinedTitle,
                        Messages.bind(Messages.noActorDefined, element.getName()));

            }
        });
    }

    protected Task initializeTask(final Element parent, final AbstractProcess proc, final Configuration configuration) {
        final AbstractProcess parentProc = ModelHelper.getParentProcess(parent);
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.setName(((Task) parent).getName());
        task.getForm().add(formCopy);
        final FormMapping formMapping = ProcessFactory.eINSTANCE.createFormMapping();
        formMapping.setType(FormMappingType.LEGACY);
        task.setFormMapping(formMapping);
        if (!parentProc.getActors().isEmpty()) {
            copyActors(parentProc, proc);

            if (((Task) parent).getActor() != null) {
                addActorToTask(task, ((Task) parent).getActor());
            } else {
                if (ModelHelper.getParentContainer(parent) instanceof Lane) {
                    final Lane lane = ModelHelper.getParentLane(parent);
                    final Actor actorCopy = addActorToTask(task, lane.getActor());
                    proc.getActors().add(actorCopy);
                } else {
                    canPreview = false;
                    openNoActorErrorMessage(task);
                }

            }
            setActorMapping(parentProc, configuration);
            proc.setByPassFormsGeneration(true);
            proc.getElements().add(task);
            isOnTask = true;
        } else {
            canPreview = false;
            openNoActorErrorMessage(task);
        }
        return task;
    }

    private Actor addActorToTask(final Task task, final Actor actor) {
        final Actor actorCopy = EcoreUtil.copy(actor);
        actorCopy.setInitiator(true);
        task.setActor(actorCopy);
        return actorCopy;
    }

    protected void setActorMapping(final AbstractProcess proc, final Configuration previewConfiguration) {
        final ProcessConfigurationRepositoryStore configurationStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        final String id = ModelHelper.getEObjectID(proc);
        final ProcessConfigurationFileStore configurationFileStore = configurationStore.getChild(id + ".conf");
        ActorMappingsType actorMapping = null;
        if (configurationFileStore != null) {
            final Configuration configuration = configurationFileStore.getContent();
            actorMapping = EcoreUtil.copy(configuration.getActorMappings());
        }
        if (actorMapping == null) {
            actorMapping = ActorMappingFactory.eINSTANCE.createActorMappingsType();
            final ActorMapping newMapping = ActorMappingFactory.eINSTANCE.createActorMapping();
            newMapping.setName(proc.getActors().get(0).getName());
            final Users users = ActorMappingFactory.eINSTANCE.createUsers();
            users.getUser().add(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
            newMapping.setUsers(users);
            newMapping.setGroups(ActorMappingFactory.eINSTANCE.createGroups());
            newMapping.setMemberships(ActorMappingFactory.eINSTANCE.createMembership());
            newMapping.setRoles(ActorMappingFactory.eINSTANCE.createRoles());
            actorMapping.getActorMapping().add(newMapping);
        } else {
            for (final ActorMapping mapping : actorMapping.getActorMapping()) {
                mapping.getMemberships().getMembership().clear();
                mapping.getGroups().getGroup().clear();
                mapping.getUsers().getUser().clear();
                mapping.getRoles().getRole().clear();
                mapping.getUsers().getUser().add(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
            }
        }
        previewConfiguration.setActorMappings(actorMapping);

    }

    private void initializeForm() {
        formCopy = EcoreUtil.copy(form);
        final List<Expression> exprs = ModelHelper.getAllItemsOfType(formCopy, ExpressionPackage.Literals.EXPRESSION);
        for (Expression expr : exprs) {
            expr = initializeExpression(form, expr);
        }
        formCopy.getData().clear();
        formCopy.getKpis().clear();
        formCopy.getValidators().clear();
        formCopy.getActions().clear();
        formCopy.getConnectors().clear();
    }

    protected abstract void initializeAllWidgets(Form formCopy);

    private Expression initializeExpression(final Form form, final Expression expr) {
        if (ExpressionConstants.VARIABLE_TYPE.equals(expr.getType())) {
            handleVariableExpression(form, expr);
        } else if (ExpressionConstants.PARAMETER_TYPE.equals(expr.getType())) {
            handleParameterExpression(expr);
        } else if (ExpressionConstants.SCRIPT_TYPE.equals(expr.getType())) {
            handleScriptExpression(expr);
        } else if (!ExpressionConstants.CONSTANT_TYPE.equals(expr.getType())) {
            toEmptyConstantExpression(expr);
        }
        return expr;
    }

    protected void toEmptyConstantExpression(final Expression expr) {
        expr.setType(ExpressionConstants.CONSTANT_TYPE);
        expr.setContent("");
        expr.setName("");
        expr.getReferencedElements().clear();
    }

    protected void handleParameterExpression(final Expression expr) {
        final Parameter parameter = (Parameter) expr.getReferencedElements().get(0);
        expr.setType(ExpressionConstants.CONSTANT_TYPE);
        expr.setContent(parameter.getValue());
    }

    protected void handleScriptExpression(final Expression expr) {
        if (expr.getReferencedElements() == null || expr.getReferencedElements().isEmpty()) {
            if (EMPTY_LIST.equals(expr.getName()) && GROOVY_SCRIPT_EMPTY_LIST.equals(expr.getContent())) {
                toEmptyConstantExpression(expr);
            }
        }
        if (expr.getReferencedElements() != null && !expr.getReferencedElements().isEmpty()) {
            toEmptyConstantExpression(expr);
        }
    }

    protected void handleVariableExpression(final Form form, final Expression expr) {
        final Data data = getReferencedData(form, expr);
        if (data != null && data.getDefaultValue() != null && data.getDefaultValue().getContent() != null && !data.getDefaultValue().getContent().isEmpty()) {
            if (data.getDataType() instanceof JavaType) {
                expr.setType(ExpressionConstants.SCRIPT_TYPE);
                expr.setInterpreter(ExpressionConstants.GROOVY);
                if (data.getDefaultValue().getReferencedElements().isEmpty()) {
                    expr.setContent(data.getDefaultValue().getContent());
                    expr.getReferencedElements().clear();
                } else {
                    toEmptyConstantExpression(expr);
                }
            } else if (data.getDataType() instanceof XMLData) {
                toEmptyConstantExpression(expr);
            } else {
                final String defaultValueType = data.getDefaultValue().getType();
                if (defaultValueType.equals(ExpressionConstants.SCRIPT_TYPE) || defaultValueType.equals(ExpressionConstants.CONSTANT_TYPE)) {
                    expr.setType(data.getDefaultValue().getType());
                    expr.setInterpreter(data.getDefaultValue().getInterpreter());
                    expr.setContent(data.getDefaultValue().getContent());
                    expr.getReferencedElements().clear();
                } else {
                    toEmptyConstantExpression(expr);
                }
            }
        } else {
            toEmptyConstantExpression(expr);
        }
    }

    private Data getReferencedData(final Form form, final Expression expr) {
        final List<Data> datas = ModelHelper.getAccessibleData(form, true);
        for (final Data data : datas) {
            if (data.getName().equals(expr.getName())) {
                return data;
            }
        }
        return null;
    }

    private void copyActors(final AbstractProcess proc, final AbstractProcess procCopy) {
        final List<Actor> actors = proc.getActors();
        for (final Actor actor : actors) {
            procCopy.getActors().add(EcoreUtil.copy(actor));
        }
    }

    protected void deleteAllOperations(final Widget widget) {
        final List<Operation> operations = ModelHelper.getAllItemsOfType(widget, ExpressionPackage.Literals.OPERATION);
        for (final Operation operation : operations) {
            EcoreUtil.delete(operation);
        }
    }

    /**
     * @return the form
     */
    public Form getForm() {
        return form;
    }

    /**
     * @return the formCopy
     */
    public Form getFormCopy() {
        return formCopy;
    }

    /**
     * @return the lookNFeel
     */
    public ApplicationLookNFeelFileStore getLookNFeel() {
        return lookNFeel;
    }

    /**
     * @return the browser
     */
    public IBrowserDescriptor getBrowser() {
        return browser;
    }

    /**
     * @return the isOnTask
     */
    public boolean isOnTask() {
        return isOnTask;
    }

    /**
     * @return the canPreview
     */
    public boolean isCanPreview() {
        return canPreview;
    }

}
