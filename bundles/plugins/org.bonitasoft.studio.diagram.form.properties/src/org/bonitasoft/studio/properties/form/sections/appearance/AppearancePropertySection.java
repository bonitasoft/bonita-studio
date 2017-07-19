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
package org.bonitasoft.studio.properties.form.sections.appearance;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.LabelPosition;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.form.sections.appearance.contributions.IAppearancePropertySectionExtension;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Aurelien Pupier
 * @author Baptiste Mesta
 * @author Mickael Istria (Refactoring tabs, added databinding)
 *         The class responsible for appearance property section of form
 *         diagram.
 */
public class AppearancePropertySection extends AbstractModelerPropertySection {

    protected static List<IAppearancePropertySectionExtension> extSections;

    protected static final int LEFT_WIDTH = 300;

    protected static final int RIGHT_WIDTH = 300;

    // the attribute name -> the widget editing it
    protected HashMap<String, org.eclipse.swt.widgets.Widget> widgets;

    // default values for each attribute
    protected HashMap<String, String> defaults;

    // widgets that are binded to model
    protected EMFDataBindingContext databindingContext;

    protected HashMap<String, org.eclipse.swt.widgets.Widget> widgets_others;

    private Listener modifyListener = new Listener() {

        public void handleEvent(Event event) {

            updateWidget(event.widget);
        }
    };

    private Listener radioListener = new Listener() {

        public void handleEvent(Event e) {
            Control[] children = ((Button) e.widget).getParent().getChildren();
            if (((Button) e.widget).getSelection()) {
                for (int i = 0; i < children.length; i++) {
                    Control child = children[i];
                    if (e.widget != child && child instanceof Button && (child.getStyle() & SWT.TOGGLE) != 0) {
                        ((Button) child).setSelection(false);
                        updateWidget(child);
                    }
                }
            }
        }
    };

    protected Composite tabInputContents;

    protected Composite tabLabelContents;

    protected Composite tabWidgetContents;

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        widgets = new HashMap<String, org.eclipse.swt.widgets.Widget>();
        defaults = new HashMap<String, String>();
        databindingContext = new EMFDataBindingContext();
        widgets_others = new HashMap<String, org.eclipse.swt.widgets.Widget>();
        parent.setLayout(new FillLayout());
    }

    /**
     * @param left
     * @param right
     * @param up
     * @param down
     */
    protected void bindLabelPositionGroup(Button left, Button right, Button up, Button down) {
        if (databindingContext != null) {
            databindingContext.dispose();
        }
        databindingContext = new EMFDataBindingContext();

        UpdateValueStrategy rightToEnum = new UpdateValueStrategy()
                .setConverter(new Converter(Boolean.class, LabelPosition.class) {

                    public Object convert(Object fromObject) {
                        return LabelPosition.RIGHT;
                    }
                });
        UpdateValueStrategy enumToRight = new UpdateValueStrategy()
                .setConverter(new Converter(LabelPosition.class, Boolean.class) {

                    public Object convert(Object fromObject) {
                        return LabelPosition.RIGHT.equals(fromObject);
                    }
                });
        UpdateValueStrategy leftToEnum = new UpdateValueStrategy()
                .setConverter(new Converter(Boolean.class, LabelPosition.class) {

                    public Object convert(Object fromObject) {
                        return LabelPosition.LEFT;
                    }
                });
        UpdateValueStrategy enumToLeft = new UpdateValueStrategy()
                .setConverter(new Converter(LabelPosition.class, Boolean.class) {

                    public Object convert(Object fromObject) {
                        return LabelPosition.LEFT.equals(fromObject);
                    }
                });
        UpdateValueStrategy upToEnum = new UpdateValueStrategy()
                .setConverter(new Converter(Boolean.class, LabelPosition.class) {

                    public Object convert(Object fromObject) {
                        return LabelPosition.UP;
                    }
                });
        UpdateValueStrategy enumToUp = new UpdateValueStrategy()
                .setConverter(new Converter(LabelPosition.class, Boolean.class) {

                    public Object convert(Object fromObject) {
                        return fromObject.equals(LabelPosition.UP);
                    }
                });
        UpdateValueStrategy downToEnum = new UpdateValueStrategy()
                .setConverter(new Converter(Boolean.class, LabelPosition.class) {

                    public Object convert(Object fromObject) {
                        return LabelPosition.DOWN;
                    }
                });
        UpdateValueStrategy enumToDown = new UpdateValueStrategy()
                .setConverter(new Converter(LabelPosition.class, Boolean.class) {

                    public Object convert(Object fromObject) {
                        return LabelPosition.DOWN.equals(fromObject);
                    }
                });

        databindingContext.bindValue(SWTObservables.observeSelection(right),
                EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                        FormPackage.Literals.WIDGET__LABEL_POSITION),
                rightToEnum, enumToRight);
        databindingContext.bindValue(SWTObservables.observeSelection(left),
                EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                        FormPackage.Literals.WIDGET__LABEL_POSITION),
                leftToEnum, enumToLeft);
        databindingContext.bindValue(SWTObservables.observeSelection(up),
                EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                        FormPackage.Literals.WIDGET__LABEL_POSITION),
                upToEnum, enumToUp);
        databindingContext.bindValue(SWTObservables.observeSelection(down),
                EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                        FormPackage.Literals.WIDGET__LABEL_POSITION),
                downToEnum, enumToDown);
    }

    /**
     * this group contains classes text and style text
     * 
     * @param prefix
     */
    protected void addCustomStyleGroup(Composite composite, String prefix, int hs, int vs) {
        // custom style group
        Group group = getWidgetFactory().createGroup(composite, Messages.AppearanceSection_CustomStyle);
        group.setLayout(new GridLayout(2, false));
        GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        gd.horizontalSpan = hs;
        gd.verticalSpan = vs;
        gd.widthHint = LEFT_WIDTH;
        group.setLayoutData(gd);

        Label label = getWidgetFactory().createLabel(group, Messages.AppearanceSection_Class);
        gd = new GridData(SWT.FILL, SWT.TOP, false, false);
        label.setLayoutData(gd);

        Text text = getWidgetFactory().createText(group, "", SWT.BORDER | SWT.SINGLE); //$NON-NLS-1$  //new StyledText(composite, SWT.BORDER | SWT.WRAP |SWT.MULTI|SWT.V_SCROLL);//
        gd = new GridData(SWT.FILL, SWT.TOP, true, false);
        text.setLayoutData(gd);
        text.setToolTipText(Messages.AppearanceSection_Class_tooltip);

        widgets.put(prefix + ExporterTools.CLASS_ATTR, text);

        label = getWidgetFactory().createLabel(group, Messages.AppearanceSection_StyleAttr);
        gd = new GridData(SWT.FILL, SWT.TOP, false, false);
        label.setLayoutData(gd);

        text = getWidgetFactory().createText(group, "", SWT.BORDER | SWT.MULTI | SWT.WRAP); //$NON-NLS-1$  //new StyledText(composite, SWT.BORDER | SWT.WRAP |SWT.MULTI|SWT.V_SCROLL);//
        gd = new GridData(SWT.FILL, SWT.TOP, true, false);
        gd.heightHint = 60;
        text.setLayoutData(gd);
        text.setToolTipText(Messages.AppearanceSection_StyleAttr_tooltip);

        widgets.put(prefix + ExporterTools.STYLE_ATTR, text);

        // custom style group end
    }

    /**
     * add properties on the size
     * 
     * @param mainComposite2
     *        where to put the composite
     * @param prefix
     *        wich part of the widget it affect (prefix properties name)
     * @param vs
     * @param hs
     */
    protected void addSizeGroup(Composite mainComposite2, String prefix, int hs, int vs) {

        Group group = getWidgetFactory().createGroup(mainComposite2, Messages.AppearanceSection_Size);

        GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        group.setLayoutData(gd);
        gd.horizontalSpan = hs;
        gd.verticalSpan = vs;
        gd.widthHint = RIGHT_WIDTH;
        group.setLayout(new GridLayout(4, false));

        Label label = getWidgetFactory().createLabel(group, Messages.AppearanceSection_Width);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        Text text = getWidgetFactory().createText(group, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        text.setToolTipText(Messages.AppearanceSection_Width_tooltip);
        widgets.put(prefix + ExporterTools.WIDGET_WIDTH, text);

        label = getWidgetFactory().createLabel(group, Messages.AppearanceSection_Height);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        text = getWidgetFactory().createText(group, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        text.setToolTipText(Messages.AppearanceSection_Height_tooltip);
        widgets.put(prefix + ExporterTools.WIDGET_HEIGHT, text);

    }

    /**
     * group that contains properties on text
     * 
     * @param mainComposite2
     *        where to put the composite
     * @param prefix
     *        wich part of the widget it affect (prefix properties name)
     * @param vs
     * @param hs
     */
    protected void addTextGroup(Composite mainComposite2, String prefix, int hs, int vs) {
        Group group = getWidgetFactory().createGroup(mainComposite2, Messages.AppearanceSection_Text);
        group.setLayout(new GridLayout(2, false));
        GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        gd.horizontalSpan = hs;
        gd.verticalSpan = vs;
        gd.widthHint = RIGHT_WIDTH;
        group.setLayoutData(gd);

        Composite gp = getWidgetFactory().createComposite(group);//$NON-NLS-1$

        GridLayout gpLayout = new GridLayout(4, true);
        gpLayout.marginHeight = 0;
        gpLayout.marginWidth = 0;
        gp.setLayout(gpLayout);
        Button button = getWidgetFactory().createButton(gp, "", SWT.TOGGLE);//$NON-NLS-1$
        button.setImage(Pics.getImage(PicsConstants.fontLeft));
        button.addListener(SWT.Selection, radioListener);
        button.setToolTipText(Messages.AppearanceSection_AlignLeft_tooltip);
        widgets.put(prefix + ExporterTools.TEXT_LEFT, button);
        button = getWidgetFactory().createButton(gp, "", SWT.TOGGLE);//$NON-NLS-1$
        button.setImage(Pics.getImage(PicsConstants.fontCenter));
        button.addListener(SWT.Selection, radioListener);
        button.setToolTipText(Messages.AppearanceSection_AlignCenter_tooltip);
        widgets.put(prefix + ExporterTools.TEXT_CENTER, button);
        button = getWidgetFactory().createButton(gp, "", SWT.TOGGLE);//$NON-NLS-1$
        button.setImage(Pics.getImage(PicsConstants.fontRight));
        button.addListener(SWT.Selection, radioListener);
        button.setToolTipText(Messages.AppearanceSection_AlignRight_tooltip);
        widgets.put(prefix + ExporterTools.TEXT_RIGHT, button);
        button = getWidgetFactory().createButton(gp, "", SWT.TOGGLE);//$NON-NLS-1$
        button.setImage(Pics.getImage(PicsConstants.fontJustify));
        button.addListener(SWT.Selection, radioListener);
        button.setToolTipText(Messages.AppearanceSection_AlignJustify_tooltip);
        widgets.put(prefix + ExporterTools.TEXT_JUSTIFY, button);

        Combo ccombo = new Combo(group, SWT.BORDER);
        ccombo.add("aqua");//$NON-NLS-1$
        ccombo.add("grey");//$NON-NLS-1$
        ccombo.add("navy");//$NON-NLS-1$
        ccombo.add("silver");//$NON-NLS-1$
        ccombo.add("black");//$NON-NLS-1$
        ccombo.add("green");//$NON-NLS-1$
        ccombo.add("olive");//$NON-NLS-1$
        ccombo.add("teal");//$NON-NLS-1$
        ccombo.add("blue");//$NON-NLS-1$
        ccombo.add("lime");//$NON-NLS-1$
        ccombo.add("purple");//$NON-NLS-1$
        ccombo.add("white");//$NON-NLS-1$
        ccombo.add("fuchsia");//$NON-NLS-1$
        ccombo.add("maroon");//$NON-NLS-1$
        ccombo.add("red");//$NON-NLS-1$
        ccombo.add("yellow");//$NON-NLS-1$
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        ccombo.setLayoutData(gd);
        ccombo.setToolTipText(Messages.AppearanceSection_Color_tooltip);
        widgets.put(prefix + ExporterTools.TEXT_COLOR, ccombo);

    }

    /**
     * contains font properties
     * 
     * @param mainComposite2
     * @param prefix
     * @param vs
     * @param hs
     */
    protected void addFontGroup(Composite mainComposite2, String prefix, int hs, int vs) {
        Group fontGroup = getWidgetFactory().createGroup(mainComposite2, Messages.AppearanceSection_Font);
        fontGroup.setLayout(new GridLayout(5, false));
        GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        gd.horizontalSpan = hs;
        gd.verticalSpan = vs;
        gd.widthHint = RIGHT_WIDTH;
        fontGroup.setLayoutData(gd);

        Combo combo = new Combo(fontGroup, SWT.MULTI); //$NON-NLS-1$  //new StyledText(composite, SWT.BORDER | SWT.WRAP |SWT.MULTI|SWT.V_SCROLL);//
        gd = new GridData(SWT.FILL, SWT.FILL, true, false);
        gd.widthHint = 50;
        combo.setLayoutData(gd);
        combo.add("serif");//$NON-NLS-1$
        combo.add("sans-serif");//$NON-NLS-1$
        combo.add("monospace");//$NON-NLS-1$

        widgets.put(prefix + ExporterTools.FONT_FAMILY, combo);

        combo = new Combo(fontGroup, SWT.MULTI);
        for (int i = 5; i < 32; i++) {
            combo.add(i + "px"); //$NON-NLS-1$
        }
        gd = new GridData(SWT.FILL, SWT.CENTER, false, false);
        gd.widthHint = 50;
        combo.setLayoutData(gd);
        // defaults.put(FONT_SIZE, "12px");
        widgets.put(prefix + ExporterTools.FONT_SIZE, combo);

        Button button = getWidgetFactory().createButton(fontGroup, "", SWT.TOGGLE); //$NON-NLS-1$
        button.setImage(Pics.getImage(PicsConstants.fontItalic));
        widgets.put(prefix + ExporterTools.TEXT_ITALIC, button);
        button.setToolTipText(Messages.AppearanceSection_Italic_tooltip);
        button = getWidgetFactory().createButton(fontGroup, "", SWT.TOGGLE); //$NON-NLS-1$
        button.setImage(Pics.getImage(PicsConstants.fontBold));
        button.setToolTipText(Messages.AppearanceSection_Bold_tooltip);
        widgets.put(prefix + ExporterTools.TEXT_BOLD, button);
        Composite gp = getWidgetFactory().createComposite(fontGroup);//$NON-NLS-1$
        GridLayout gpLayout = new GridLayout(2, true);
        gpLayout.marginHeight = 0;
        gpLayout.marginWidth = 0;

        gp.setLayout(gpLayout);
        button = getWidgetFactory().createButton(gp, "", SWT.TOGGLE); //$NON-NLS-1$
        button.setImage(Pics.getImage(PicsConstants.fontUnderscore));
        button.addListener(SWT.Selection, radioListener);
        button.setToolTipText(Messages.AppearanceSection_Underscore_tooltip);
        widgets.put(prefix + ExporterTools.TEXT_UNDERLINE, button);
        button = getWidgetFactory().createButton(gp, "", SWT.TOGGLE); //$NON-NLS-1$
        button.setImage(Pics.getImage(PicsConstants.fontStrikeThrough));
        button.setToolTipText(Messages.AppearanceSection_StrikeThrough_tooltip);
        button.addListener(SWT.Selection, radioListener);
        widgets.put(prefix + ExporterTools.TEXT_STRIKE, button);

    }

    /**
     * add listeners
     */
    private void addListeners() {

        for (String attr : widgets.keySet()) {
            if (widgets.get(attr) instanceof Button) {
                if (widgets.get(attr).getListeners(SWT.Selection).length <= 1)
                    widgets.get(attr).addListener(SWT.Selection, modifyListener);

            } else {
                if (widgets.get(attr).getListeners(SWT.Modify).length == 0)
                    widgets.get(attr).addListener(SWT.Modify, modifyListener);
            }
        }
        for (String attr : widgets_others.keySet()) {
            if (widgets_others.get(attr).getListeners(SWT.Modify).length == 0) {
                widgets_others.get(attr).addListener(SWT.Modify, modifyListener);
            }
        }
    }

    /**
     * remove listeners
     */
    private void removeListeners() {
        for (String attr : widgets.keySet()) {
            widgets.get(attr).removeListener(SWT.Modify, modifyListener);
        }
        for (String attr : widgets_others.keySet()) {
            if (widgets_others.get(attr).getListeners(SWT.Modify).length == 0)
                widgets_others.get(attr).removeListener(SWT.Modify, modifyListener);
        }

    }

    /**
     * @return the widget of this section
     */
    protected Widget getWidget() {
        return (Widget) getEObject();
    }

    /**
     * get text of the widget (if possible)
     * 
     * @param widget
     * @return the widget's text or null if not applicable
     */
    private String getWidgetText(org.eclipse.swt.widgets.Widget widget) {
        if (widget instanceof Text) {
            return ((Text) widget).getText();
        } else if (widget instanceof Combo) {
            return ((Combo) widget).getText();
        } else if (widget instanceof CCombo) {
            return ((CCombo) widget).getText();
        } else if (widget instanceof Button) {
            return ((Button) widget).getSelection() ? ExporterTools.ATTR_ENABLE : ""; //$NON-NLS-1$
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * set the text of the widget with the string value
     * 
     * @param widget
     *        SWT widget on which set the text
     * @param string
     *        the string to set the widget with
     */
    private void setWidgetText(org.eclipse.swt.widgets.Widget widget, String string) {
        if (widget instanceof Text) {
            ((Text) widget).setText(string);
        } else if (widget instanceof Combo) {
            ((Combo) widget).setText(string);
        } else if (widget instanceof CCombo) {
            ((CCombo) widget).setText(string);
        } else if (widget instanceof Button) {
            if (string.equals(ExporterTools.ATTR_ENABLE)) {
                ((Button) widget).setSelection(true);
            } else {
                ((Button) widget).setSelection(false);
            }
        }
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        removeListeners();
        updateFields();
        addListeners();
    }

    /**
     * update fields with widget's values
     */
    protected void updateFields() {

        EMap<String, String> map = getWidget().getHtmlAttributes();
        for (String attr : widgets.keySet()) {
            if (map.containsKey(attr) && map.get(attr).length() > 0) {
                setWidgetText(widgets.get(attr), map.get(attr));

            } else {
                if (defaults.containsKey(attr)) {
                    setWidgetText(widgets.get(attr), defaults.get(attr));
                } else {
                    setWidgetText(widgets.get(attr), ""); //$NON-NLS-1$
                }
            }
        }
        updateWidgetOthers();

    }

    private void updateWidgetOthers() {
        Widget w = getWidget();

        if (widgets_others.containsKey(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXHEIGHT)) {
            if (w instanceof ListFormField) {
                setWidgetText(widgets_others.get(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXHEIGHT),
                        "" + ((ListFormField) w).getMaxHeigth()); //$NON-NLS-1$
            } else if (w instanceof TextAreaFormField) {
                setWidgetText(widgets_others.get(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXHEIGHT),
                        "" + ((TextAreaFormField) w).getMaxHeigth()); //$NON-NLS-1$
            }
        }
        if (widgets_others.containsKey(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXLENGTH)) {
            org.eclipse.swt.widgets.Widget widgetText = widgets_others
                    .get(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXLENGTH);
            if (w instanceof PasswordFormField) {
                int maxLength = ((PasswordFormField) w).getMaxLength();
                if (maxLength > 0) {
                    setWidgetText(widgetText, "" + maxLength); //$NON-NLS-1$
                } else {
                    setWidgetText(widgetText, ""); //$NON-NLS-1$
                }
            } else if (w instanceof TextAreaFormField) {
                int maxLength = ((TextAreaFormField) w).getMaxLength();
                if (maxLength > 0) {
                    setWidgetText(widgetText, "" + maxLength); //$NON-NLS-1$
                } else {
                    setWidgetText(widgetText, ""); //$NON-NLS-1$
                }
            } else if (w instanceof TextFormField) {
                int maxLength = ((TextFormField) w).getMaxLength();
                if (maxLength > 0) {
                    setWidgetText(widgetText, "" + maxLength); //$NON-NLS-1$
                } else {
                    setWidgetText(widgetText, ""); //$NON-NLS-1$
                }
            }

        }

    }

    /**
     * update widget with fields
     * 
     * @param event
     */
    private void updateWidget(org.eclipse.swt.widgets.Widget widget) {
        EMap<String, String> map = getWidget().getHtmlAttributes();
        Boolean done = false;// true when widget is found
        for (String attr : widgets.keySet()) {
            if (widget.equals(widgets.get(attr))) {
                done = true;
                if (getWidgetText(widgets.get(attr)).length() > 0) {
                    if (!map.containsKey(attr)) {
                        EStringToStringMapEntryImpl entry = (EStringToStringMapEntryImpl) EcoreUtil
                                .create(EcorePackage.eINSTANCE.getEStringToStringMapEntry());
                        entry.setKey(attr);
                        entry.setValue(getWidgetText(widgets.get(attr)));
                        getEditingDomain().getCommandStack().execute(
                                new AddCommand(getEditingDomain(), getWidget(),
                                        FormPackage.Literals.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES, entry));
                    } else {
                        getEditingDomain().getCommandStack().execute(
                                new SetCommand(getEditingDomain(), (EObject) map.get(map.indexOfKey(attr)),
                                        EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE,
                                        getWidgetText(widgets.get(attr))));
                    }
                } else {
                    if (map.containsKey(attr)) {
                        int index = map.indexOfKey(attr);
                        Entry<String, String> entry = map.get(index);
                        getEditingDomain().getCommandStack().execute(
                                new RemoveCommand(getEditingDomain(), getWidget(),
                                        FormPackage.Literals.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES, entry));
                    }
                }
                break;
            }
        }
        if (!done)
            updateWidgetMaxValues(widget);
    }

    private void updateWidgetMaxValues(org.eclipse.swt.widgets.Widget widget) {
        Widget w = getWidget();
        if (widgets_others.containsKey(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXHEIGHT)
                && widgets_others.get(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXHEIGHT).equals(widget)) {

            String text = getWidgetText(widgets_others.get(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXHEIGHT));
            try {
                int value = Integer.valueOf(text);
                if (w instanceof ListFormField) {
                    getEditingDomain().getCommandStack()
                            .execute(new SetCommand(getEditingDomain(), w, FormPackage.Literals.LIST_FORM_FIELD__MAX_HEIGTH,
                                    value));
                } else if (w instanceof TextAreaFormField) {
                    getEditingDomain().getCommandStack().execute(
                            new SetCommand(getEditingDomain(), w, FormPackage.Literals.TEXT_AREA_FORM_FIELD__MAX_HEIGTH,
                                    value));
                }
            } catch (NumberFormatException e) {

            }
        } else if (widgets_others.containsKey(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXLENGTH)
                && widgets_others.get(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXLENGTH).equals(widget)) {

            String text = getWidgetText(widgets_others.get(ExporterTools.PREFIX_INPUT + ExporterTools.ATTR_MAXLENGTH));
            int value = -1;
            try {
                value = Integer.valueOf(text);
            } catch (NumberFormatException e) {

            }
            if (w instanceof PasswordFormField) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), w, FormPackage.Literals.PASSWORD_FORM_FIELD__MAX_LENGTH, value));

            } else if (w instanceof TextAreaFormField) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), w, FormPackage.Literals.TEXT_AREA_FORM_FIELD__MAX_LENGTH, value));

            } else if (w instanceof TextFormField) {
                getEditingDomain().getCommandStack()
                        .execute(new SetCommand(getEditingDomain(), w, FormPackage.Literals.TEXT_FORM_FIELD__MAX_LENGTH,
                                value));

            }

        }

    }

}
