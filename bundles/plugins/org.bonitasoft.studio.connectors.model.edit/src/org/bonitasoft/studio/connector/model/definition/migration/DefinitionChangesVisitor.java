/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.migration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.definition.migration.model.DefinitionInputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.DefinitionOutputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.InputMandatoryChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.InputTypeChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.InputWidgetChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.NewInputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.NewOutputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.OutputTypeChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.RemovedInputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.RemovedOutputChange;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.ReferenceChange;

public class DefinitionChangesVisitor {

    private List<DefinitionInputChange> inputChanges = new ArrayList<>();
    private List<DefinitionOutputChange> outputChanges = new ArrayList<>();
    private DefaultValueExpressionFactory defaultValueExpressionFactory;

    private List<InputWidgetChange> widgetChanges = new ArrayList<>();

    public DefinitionChangesVisitor(DefaultValueExpressionFactory defaultValueExpressionFactory) {
        this.defaultValueExpressionFactory = defaultValueExpressionFactory;
    }

    public void visit(Diff diff) {
        if (diff instanceof AttributeChange) {
            visit((AttributeChange) diff);
        } else if (diff instanceof ReferenceChange) {
            visit((ReferenceChange) diff);
        }
    }

    public void visit(AttributeChange diff) {
        if (isInputTypeAttributeChange(diff)) {
            inputChanges.add(createInputTypeChange(diff));
        } else if (isInputMandatoryAttributeChange(diff)) {
            inputChanges.add(createInputMandatoryChange(diff));
        } else if (isOutputTypeAttributeChange(diff)) {
            outputChanges.add(createOutputTypeChange(diff));
        }
    }

    public void visit(ReferenceChange diff) {
        if (isInputRefChange(diff)) {
            visitInputRefChange(diff);
        } else if (isOutputRefChange(diff)) {
            visitOutputRefChange(diff);
        } else if (isInputWidgetTypeChange(diff)) {
            WidgetComponent wc = (WidgetComponent) diff.getValue();
            InputWidgetChange change = findOrCreateWidgetTypeChange(wc);
            if (diff.getKind() == DifferenceKind.ADD) {
                change.setNewWidgetType(wc);
            } else if (diff.getKind() == DifferenceKind.DELETE) {
                change.setOldWidgetType(wc);
            }
            if (change.isChange()) {
                inputChanges.add(change);
            }
        }
    }

    private InputWidgetChange findOrCreateWidgetTypeChange(WidgetComponent wc) {
        InputWidgetChange inputWidgetChange = widgetChanges.stream()
                .filter(change -> Objects.equals(change.getInputName(), wc.getInputName()))
                .findFirst()
                .orElseGet(() -> new InputWidgetChange(wc.getInputName()));
        if (!widgetChanges.contains(inputWidgetChange)) {
            widgetChanges.add(inputWidgetChange);
        }
        return inputWidgetChange;
    }

    private void visitOutputRefChange(Diff diff) {
        if (diff.getKind() == DifferenceKind.ADD) {
            outputChanges.add(new NewOutputChange((Output) ((ReferenceChange) diff).getValue()));
        } else if (diff.getKind() == DifferenceKind.DELETE) {
            outputChanges.add(new RemovedOutputChange((Output) ((ReferenceChange) diff).getValue()));
        }
    }

    private void visitInputRefChange(Diff diff) {
        if (diff.getKind() == DifferenceKind.ADD) {
            inputChanges.add(
                    new NewInputChange((Input) ((ReferenceChange) diff).getValue(), defaultValueExpressionFactory));
        } else if (diff.getKind() == DifferenceKind.DELETE) {
            inputChanges.add(new RemovedInputChange((Input) ((ReferenceChange) diff).getValue()));
        }
    }

    private OutputTypeChange createOutputTypeChange(Diff diff) {
        Match match = diff.getMatch();
        Output newOutput = (Output) match.getLeft();
        Output oldOutput = (Output) match.getRight();
        return new OutputTypeChange(newOutput.getName(), oldOutput.getType(), newOutput.getType());
    }

    private InputTypeChange createInputTypeChange(Diff diff) {
        Match match = diff.getMatch();
        Input newInput = (Input) match.getLeft();
        Input oldInput = (Input) match.getRight();
        return new InputTypeChange(newInput.getName(), oldInput.getType(), newInput.getType());
    }

    private InputMandatoryChange createInputMandatoryChange(AttributeChange diff) {
        Match match = diff.getMatch();
        Input newInput = (Input) match.getLeft();
        return new InputMandatoryChange(newInput.getName());
    }

    private boolean isOutputTypeAttributeChange(AttributeChange attributeChange) {
        return attributeChange.getKind() == DifferenceKind.CHANGE
                && attributeChange.getAttribute() == ConnectorDefinitionPackage.Literals.OUTPUT__TYPE;
    }

    private boolean isInputTypeAttributeChange(AttributeChange attributeChange) {
        return attributeChange.getKind() == DifferenceKind.CHANGE
                && attributeChange.getAttribute() == ConnectorDefinitionPackage.Literals.INPUT__TYPE;
    }

    private boolean isInputMandatoryAttributeChange(AttributeChange attributeChange) {
        return attributeChange.getKind() == DifferenceKind.CHANGE
                && attributeChange.getAttribute() == ConnectorDefinitionPackage.Literals.INPUT__MANDATORY
                && (Boolean) attributeChange.getValue();
    }

    private boolean isInputWidgetTypeChange(ReferenceChange referenceChange) {
        return (referenceChange.getKind() == DifferenceKind.ADD || referenceChange.getKind() == DifferenceKind.DELETE)
                && referenceChange.getReference() == ConnectorDefinitionPackage.Literals.PAGE__WIDGET
                && referenceChange.getValue() instanceof WidgetComponent;
    }

    private boolean isInputRefChange(ReferenceChange refChange) {
        return ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__INPUT == refChange.getReference();
    }

    private boolean isOutputRefChange(ReferenceChange refChange) {
        return ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__OUTPUT == refChange.getReference();
    }

    public List<DefinitionInputChange> getInputChanges() {
        return Collections.unmodifiableList(inputChanges);
    }

    public List<DefinitionOutputChange> getOutputChanges() {
        return Collections.unmodifiableList(outputChanges);
    }

    public boolean hasInputBreakingChanges(ConnectorConfiguration configruation) {
        return inputChanges.stream().anyMatch(c -> c.isBreakingChange(configruation));
    }

    public boolean hasOutputBreakingChanges(Connector connector) {
        return outputChanges.stream().anyMatch(c -> c.isBreakingChange(connector));
    }

}
