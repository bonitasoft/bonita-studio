package org.bonitasoft.studio.properties.form.sections.widget;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.properties.form.sections.widget.contributions.DisplayDateFormatContribution;

public class WidgetPropertySection extends ExtensibleGridPropertySection {

	@Override
	protected void addContributions() {


		
		addContribution(new DisplayDateFormatContribution());
		
	}

	

	@Override
	public String getSectionDescription() {
		// TODO Auto-generated method stub
		return null;
	}


}
