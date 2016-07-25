package org.bonitasoft.studio.properties.form.sections.actions;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.AsynchronousSuggestBoxContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.AvailableValueContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.DateFormFielContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.DateFormatContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.DefaultValueContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.DurationContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.FileGridPropertySectionContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.FileWidgetDocumentPickerContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.HtmlInitialValueContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.ImageKindContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.ImageWidgetInitialValueContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.InitialValueContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.ItemAlignmentContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.MaxItemsSectionContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.OutputSectionContribution;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.WidgetModifierContribution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

public class DataPropertySection extends ExtensibleGridPropertySection {


    public static final int FIELD_WIDTH = 200;
    public static final GridData GRID_DATA = new GridData(SWT.FILL, SWT.FILL, false, false);

    public DataPropertySection() {
        super();
        GRID_DATA.widthHint = FIELD_WIDTH;
    }


    @Override
    protected void addContributions() {

        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager
                .getInstance().getConfigurationElements(
                        "org.bonitasoft.studio.common.properties.contribution");

        final List<IExtensibleGridPropertySectionContribution> contribs = new ArrayList<IExtensibleGridPropertySectionContribution>();

        for (final IConfigurationElement elem : elements) {
            if (elem.getAttribute("contributeTo").equals(
                    this.getClass().getName())) {
                try {
                    contribs
                    .add((IExtensibleGridPropertySectionContribution) elem
                            .createExecutableExtension("class"));
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }

        for (final IExtensibleGridPropertySectionContribution contrib : contribs) {
            addContribution(contrib);
        }
        //line1
        addContribution(new FileGridPropertySectionContribution());
        addContribution(new FileWidgetDocumentPickerContribution());
        addContribution(new ImageKindContribution());
        addContribution(new ImageWidgetInitialValueContribution());
        addContribution(new InitialValueContribution());
        addContribution(new HtmlInitialValueContribution());
        addContribution(new DateFormFielContribution());
        addContribution(new DurationContribution());
        addContribution(new AsynchronousSuggestBoxContribution());
        addContribution(new AvailableValueContribution());
        addContribution(new MaxItemsSectionContribution());
        addContribution(new DateFormatContribution());

        //line ++1
        addContribution(new DefaultValueContribution());
        addContribution(new WidgetModifierContribution());
        addContribution(new OutputSectionContribution());
        addContribution(new ItemAlignmentContribution());


    }


    @Override
    public String getSectionDescription() {
        return null;
    }


}
