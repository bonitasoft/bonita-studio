/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * @author Romain Bioteau
 */
public class SelectConnectorConfigurationWizard extends Wizard {

    private final ConnectorConfiguration currentConfiguraiton;
    private SelectConnectorConfigurationWizardPage page;
    private final IRepositoryStore<? extends IRepositoryFileStore> configurationStore;
    private final IDefinitionRepositoryStore definitionRepositoryStore;

    public SelectConnectorConfigurationWizard(ConnectorConfiguration currentConfiguraiton,
            IRepositoryStore<? extends IRepositoryFileStore> configurationStore, IDefinitionRepositoryStore definitionRepositoryStore) {
        setDefaultPageImageDescriptor(Pics.getWizban());
        this.currentConfiguraiton = currentConfiguraiton;
        this.configurationStore = configurationStore;
        this.definitionRepositoryStore = definitionRepositoryStore;
    }

    @Override
    public void addPages() {
        page = new SelectConnectorConfigurationWizardPage(currentConfiguraiton, configurationStore, definitionRepositoryStore);
        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        applyConfiguration();
        return true;
    }

    protected void applyConfiguration() {
        final ConnectorConfiguration selectedConfiguration = page.getSelectedConfiguration();
        currentConfiguraiton.getParameters().clear();
        final DefinitionResourceProvider resourceProvider = definitionRepositoryStore.getResourceProvider();
        final Set<MissingDependency> dependenciesNotFound = newHashSet();
        for (final ConnectorParameter parameter : selectedConfiguration.getParameters()) {
            final ConnectorParameter newParam = EcoreUtil.copy(parameter);
            for (final EObject exp : ModelHelper.getAllItemsOfType(newParam, ExpressionPackage.Literals.EXPRESSION)) {
                final Expression expression = (Expression) exp;
                if (ExpressionConstants.SCRIPT_TYPE.equals(expression.getType())) {
                    for (final EObject dependency : newArrayList(expression.getReferencedElements())) {
                        final EObject matchingDependency = matchingDependency(dependency);
                        expression.getReferencedElements().remove(dependency);
                        if (matchingDependency != null) {
                            expression.getReferencedElements().add(matchingDependency);
                        } else {
                            dependenciesNotFound.add(new MissingDependency(dependencyName(dependency),
                                    dependency.eClass().getName(),
                                    inputName(resourceProvider, parameter)));
                        }
                    }
                } else {
                    if (!expression.getReferencedElements().isEmpty()) {
                        final EObject dependency = expression.getReferencedElements().get(0);
                        final EObject matchingDependency = matchingDependency(dependency);
                        expression.getReferencedElements().clear();
                        if (matchingDependency != null) {
                            expression.getReferencedElements().add(matchingDependency);
                        } else {
                            dependenciesNotFound.add(new MissingDependency(dependencyName(dependency),
                                    dependency.eClass().getName(),
                                    inputName(resourceProvider, parameter)));
                            expression.setType(ExpressionConstants.CONSTANT_TYPE);
                        }
                    }
                }
            }
            currentConfiguraiton.getParameters().add(newParam);
        }
        MessageDialog.open(loadKind(dependenciesNotFound), getShell(), Messages.loadConfigurationWarningTitle,
                loadMessage(dependenciesNotFound), SWT.NONE);
    }

    private String inputName(final DefinitionResourceProvider resourceProvider, final ConnectorParameter parameter) {
        final String key = parameter.getKey();
        final ConnectorDefinition connectorDefinition = connectorDefinition();
        final Optional<WidgetComponent> widgetOptionnal = Iterables.tryFind(ModelHelper.getAllElementOfTypeIn(connectorDefinition, WidgetComponent.class),
                withInputName(key));
        if (widgetOptionnal.isPresent()) {
            return resourceProvider.getFieldLabel(
                    connectorDefinition,
                    widgetOptionnal.get().getId());
        } else {
            return key;
        }

    }

    private Predicate<? super WidgetComponent> withInputName(final String inputName) {
        return new Predicate<WidgetComponent>() {

            @Override
            public boolean apply(WidgetComponent component) {
                return Objects.equals(inputName, component.getInputName());
            }
        };
    }

    private ConnectorDefinition connectorDefinition() {
        return definitionRepositoryStore.getDefinition(currentConfiguraiton.getDefinitionId(), currentConfiguraiton.getVersion());
    }

    private String dependencyName(EObject dependency) {
        if (dependency instanceof Parameter) {
            return ((Parameter) dependency).getName();
        } else if (dependency instanceof Element) {
            return ((Element) dependency).getName();
        }
        return "<name_not_found>";
    }

    private int loadKind(Set<MissingDependency> dependenciesNotFound) {
        return dependenciesNotFound.isEmpty() ? MessageDialog.INFORMATION : MessageDialog.WARNING;
    }

    private String loadMessage(final Set<MissingDependency> dependenciesNotFound) {
        return dependenciesNotFound.isEmpty() ? Messages.loadConfigurationSuccessMsg
                : String.format(Messages.loadConfigurationWarningMsg, missingDependenciesMsg(dependenciesNotFound));
    }

    private String missingDependenciesMsg(Set<MissingDependency> dependenciesNotFound) {
        if (!dependenciesNotFound.isEmpty()) {
            final StringBuilder sb = new StringBuilder("Dependencies not found in the current scope:\n");
            for (final MissingDependency dependency : dependenciesNotFound) {
                sb.append("- ");
                sb.append(dependency.toString());
                sb.append("\n");
            }
            return sb.toString();
        }

        return "";
    }

    protected EObject matchingDependency(final EObject dependency) {
        if (dependency instanceof Data) {
            for (final Data d : ModelHelper.getAccessibleData(currentConfiguraiton)) {
                if (d.getName().equals(((Data) dependency).getName())) {
                    return ExpressionHelper.createDependencyFromEObject(d);
                }
            }
        } else if (dependency instanceof Parameter) {
            for (final Parameter p : ModelHelper.getParentProcess(currentConfiguraiton).getParameters()) {
                if (p.getName().equals(((Parameter) dependency).getName())) {
                    return ExpressionHelper.createDependencyFromEObject(p);
                }
            }
        } else if (dependency instanceof Expression) {//a provided engine expression
            return dependency;
        }
        return null;
    }

    class MissingDependency {

        private final String name;
        private final String type;
        private final String inputName;

        public MissingDependency(String name, String type, String inputName) {
            this.name = name;
            this.type = type;
            this.inputName = inputName;
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return String.format(Messages.missingDependency, name, type, inputName);
        }
    }
}
