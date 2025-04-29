package org.eclipse.gmf.tooling.runtime.parsers;

import java.util.Arrays;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.tooling.runtime.GMFToolingRuntimePlugin;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

public abstract class AbstractFeatureParser implements IParser {

	private final EStructuralFeature[] features;

	private final EStructuralFeature[] editableFeatures;

	public AbstractFeatureParser(EStructuralFeature[] features) {
		this(features, features);
	}

	public AbstractFeatureParser(EStructuralFeature[] features, EStructuralFeature[] editableFeatures) {
		if (features == null || Arrays.asList(features).contains(null)) {
			throw new IllegalArgumentException();
		}
		if (editableFeatures == null || Arrays.asList(editableFeatures).contains(null)) {
			throw new IllegalArgumentException();
		}
		this.features = features;
		this.editableFeatures = editableFeatures;
	}

	protected EStructuralFeature[] getFeatures() {
		return features;
	}

	protected EStructuralFeature[] getEditableFeatures() {
		return editableFeatures;
	}

	protected Object[] getValues(EObject element) {
		Object[] values = new Object[features.length];
		for (int i = 0; i < features.length; i++) {
			values[i] = getValue(element, features[i]);
		}
		return values;
	}

	protected Object[] getEditableValues(EObject element) {
		Object[] values = new Object[editableFeatures.length];
		for (int i = 0; i < editableFeatures.length; i++) {
			values[i] = getValue(element, editableFeatures[i]);
		}
		return values;
	}

	protected Object getValue(EObject element, EStructuralFeature feature) {
		return element.eGet(feature);
	}

	protected ICommand getParseCommand(IAdaptable adapter, Object[] values, int flags) {
		if (values == null || validateNewValues(values).getCode() != IParserEditStatus.EDITABLE) {
			return UnexecutableCommand.INSTANCE;
		}
		EObject element = (EObject) adapter.getAdapter(EObject.class);
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(element);
		if (editingDomain == null) {
			return UnexecutableCommand.INSTANCE;
		}
		CompositeTransactionalCommand command = new CompositeTransactionalCommand(editingDomain, "Set Values"); //$NON-NLS-1$
		for (int i = 0; i < values.length; i++) {
			command.compose(getModificationCommand(element, editableFeatures[i], values[i]));
		}
		return command;
	}

	protected IParserEditStatus validateNewValues(Object[] values) {
		if (values.length != editableFeatures.length) {
			return ParserEditStatus.UNEDITABLE_STATUS;
		}
		for (int i = 0; i < values.length; i++) {
			Object value = getValidNewValue(editableFeatures[i], values[i]);
			if (value instanceof InvalidValue) {
				return new ParserEditStatus(GMFToolingRuntimePlugin.ID, IParserEditStatus.UNEDITABLE, value.toString());
			}
		}
		return ParserEditStatus.EDITABLE_STATUS;
	}

	protected Object getValidNewValue(EStructuralFeature feature, Object value) {
		return value;
	}

	protected ICommand getModificationCommand(EObject element, EStructuralFeature feature, Object value) {
		value = getValidNewValue(feature, value);
		if (value instanceof InvalidValue) {
			return UnexecutableCommand.INSTANCE;
		}
		SetRequest request = new SetRequest(element, feature, value);
		return new SetValueCommand(request);
	}

	public boolean isAffectingEvent(Object event, int flags) {
		if (event instanceof Notification) {
			return isAffectingFeature(((Notification) event).getFeature());
		}
		return false;
	}

	protected boolean isAffectingFeature(Object feature) {
		for (int i = 0; i < features.length; i++) {
			if (features[i] == feature) {
				return true;
			}
		}
		return false;
	}

	public IContentAssistProcessor getCompletionProcessor(IAdaptable element) {
		return null;
	}

	protected static class InvalidValue {

		private String description;

		public InvalidValue(String description) {
			this.description = description;
		}

		public String toString() {
			return description;
		}
	}
}
