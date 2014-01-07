package org.bonitasoft.studio.common.databinding.converter;

import org.bonitasoft.studio.model.process.Element;
import org.eclipse.core.databinding.conversion.Converter;

public class ElementToStringConverter extends Converter {

	public ElementToStringConverter() {
		super(Element.class, String.class);
	}

	public Object convert(Object fromObject) {
		return fromObject == null?"":((Element) fromObject).getName();
	}

}
