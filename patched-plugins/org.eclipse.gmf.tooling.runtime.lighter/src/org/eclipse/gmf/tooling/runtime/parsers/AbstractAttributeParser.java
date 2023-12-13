package org.eclipse.gmf.tooling.runtime.parsers;

import java.math.BigDecimal;
import java.util.Date;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.tooling.runtime.Messages;
import org.eclipse.osgi.util.NLS;

public abstract class AbstractAttributeParser extends AbstractFeatureParser {

	private String viewPattern;

	private String editorPattern;

	private String editPattern;

	protected final EAttribute[] features;

	protected final EAttribute[] editableFeatures;

	public AbstractAttributeParser(EAttribute[] features) {
		super(features);
		this.features = this.editableFeatures = features;
	}

	public AbstractAttributeParser(EAttribute[] features, EAttribute[] editableFeatures) {
		super(features, editableFeatures);
		this.features = features;
		this.editableFeatures = editableFeatures;
	}

	@Override
	protected Object getValue(EObject element, EStructuralFeature feature) {
		Object value = super.getValue(element, feature);
		Class<?> iClass = ((EAttribute) feature).getEAttributeType().getInstanceClass();
		if (String.class.equals(iClass)) {
			if (value == null) {
				value = ""; //$NON-NLS-1$
			}
		}
		return value;
	}

	@Override
	protected Object getValidNewValue(EStructuralFeature feature, Object value) {
		EClassifier type = feature.getEType();
		if (false == type instanceof EDataType) {
			return value;
		}

		if (type instanceof EEnum) {
			return getValidEnumValue((EEnum) type, value);
		}

		if (value instanceof String) {
			try {
				return type.getEPackage().getEFactoryInstance().createFromString((EDataType) type, (String) value);
			} catch (Exception e) {
				String safeExceptionMsg = e == null || e.getMessage() == null ? "" : e.getMessage();
				return new InvalidValue(NLS.bind(Messages.AbstractAttributeParser_WrongStringConversion, type.getName(), safeExceptionMsg));
			}
		}

		Class<?> iClass = type.getInstanceClass();
		if (iClass == null) {
			//we can't say anything, just give up
			return value;
		}

		if (value == null) {
			return iClass.isPrimitive() ? new InvalidValue(NLS.bind(Messages.AbstractAttributeParser_NullIsNotAllowed, type.getName())) : null;
		}

		if (Boolean.TYPE.equals(iClass) || Boolean.class.equals(iClass)) {
			return safeCast(value, type, Boolean.class);
		}
		if (Character.TYPE.equals(iClass) || Character.class.equals(iClass)) {
			return safeCast(value, type, Character.class);
		}
		if (Byte.TYPE.equals(iClass) || Byte.class.equals(iClass)) {
			return safeCastNumber(value, BYTE_CASTER);
		}
		if (Short.TYPE.equals(iClass) || Short.class.equals(iClass)) {
			return safeCastNumber(value, SHORT_CASTER);
		}
		if (Integer.TYPE.equals(iClass) || Integer.class.equals(iClass)) {
			return safeCastNumber(value, INT_CASTER);
		}
		if (Long.TYPE.equals(iClass) || Long.class.equals(iClass)) {
			return safeCastNumber(value, LONG_CASTER);
		}
		if (Float.TYPE.equals(iClass) || Float.class.equals(iClass)) {
			return safeCastNumber(value, FLOAT_CASTER);
		}
		if (Double.TYPE.equals(iClass) || Double.class.equals(iClass)) {
			return safeCastNumber(value, DOUBLE_CASTER);
		}
		if (Date.class.equals(iClass)) {
			return safeCast(value, type, Date.class);
		}
		if (BigDecimal.class.equals(iClass)) {
			if (value == null || value instanceof BigDecimal) {
				return value;
			}
			if (value instanceof Number) {
				return new BigDecimal(((Number) value).doubleValue());
			}
			return new InvalidValue(NLS.bind(Messages.AbstractAttributeParser_UnexpectedValueType, BigDecimal.class.getName()));
		}

		return value;
	}

	/**
	 * @since 3.2
	 */
	protected Object safeCast(Object value, EClassifier type, Class<?> clazz) {
		if (value == null) {
			return clazz.isPrimitive() ? new InvalidValue(NLS.bind(Messages.AbstractAttributeParser_NullIsNotAllowed, type.getName())) : null;
		}
		return clazz.isInstance(value) ? clazz.cast(value) : new InvalidValue(NLS.bind(Messages.AbstractAttributeParser_UnexpectedValueType, clazz.getName()));
	}

	/**
	 * @since 3.2
	 */
	protected <T extends Number> Object safeCastNumber(Object value, NumberCaster<T> caster) {
		if (value instanceof Number) {
			return caster.castNumber((Number) value);
		}
		return new InvalidValue(NLS.bind(Messages.AbstractAttributeParser_UnexpectedValueType, Number.class.getName()));
	}

	/**
	 * @since 3.2
	 */
	protected Object getValidEnumValue(EEnum type, Object value) {
		EEnumLiteral literal = null;
		if (value instanceof String) {
			literal = type.getEEnumLiteralByLiteral((String) value);
		} else if (value instanceof Number) {
			literal = type.getEEnumLiteral(((Number) value).intValue());
		} else if (value instanceof Enumerator) {
			literal = type.getEEnumLiteral(((Enumerator) value).getValue());
		}
		return literal == null ? new InvalidValue(NLS.bind(Messages.AbstractAttributeParser_UnknownLiteral, value)) : literal.getInstance();
	}

	public String getViewPattern() {
		return viewPattern;
	}

	public void setViewPattern(String viewPattern) {
		this.viewPattern = viewPattern;
	}

	public String getEditorPattern() {
		return editorPattern;
	}

	public void setEditorPattern(String editorPattern) {
		this.editorPattern = editorPattern;
	}

	public String getEditPattern() {
		return editPattern;
	}

	public void setEditPattern(String editPattern) {
		this.editPattern = editPattern;
	}

	private static interface NumberCaster<T extends Number> {

		public T castNumber(Number number);

	}

	private static NumberCaster<Short> SHORT_CASTER = new NumberCaster<Short>() {

		@Override
		public Short castNumber(Number number) {
			return number instanceof Short ? (Short) number : number.shortValue();
		}
	};

	private static NumberCaster<Byte> BYTE_CASTER = new NumberCaster<Byte>() {

		@Override
		public Byte castNumber(Number number) {
			return number instanceof Byte ? (Byte) number : number.byteValue();
		}

	};

	private static NumberCaster<Integer> INT_CASTER = new NumberCaster<Integer>() {

		@Override
		public Integer castNumber(Number number) {
			return number instanceof Integer ? (Integer) number : number.intValue();
		}
	};

	private static NumberCaster<Long> LONG_CASTER = new NumberCaster<Long>() {

		@Override
		public Long castNumber(Number number) {
			return number instanceof Long ? (Long) number : number.longValue();
		}
	};

	private static NumberCaster<Double> DOUBLE_CASTER = new NumberCaster<Double>() {

		@Override
		public Double castNumber(Number number) {
			return number instanceof Double ? (Double) number : number.doubleValue();
		}
	};

	private static NumberCaster<Float> FLOAT_CASTER = new NumberCaster<Float>() {

		@Override
		public Float castNumber(Number number) {
			return number instanceof Float ? (Float) number : number.floatValue();
		}
	};

}
