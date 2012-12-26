/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.codehaus.groovy.ast.FieldNode;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * @author Mickael Istria
 *
 */
public class TextAreaOrData extends TextOrData {


    private final Text textArea;
    private final Composite mainComposite;

    /**
     * @param parent
     * @param isConnectable
     * @param element
     */
    public TextAreaOrData(Composite parent, boolean isConnectable, Element element) {
        this(parent, isConnectable, element, new ArrayList<EClass>(), false, false);
    }

    @Override
    public void revertValue() {
        if (!((TextAreaSuperCombo)combo).getTextOnTextArea().equals(previousEntry)) {
            combo.setText(previousEntry);
        }
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.groovy.widgets.TextOrData#getSelectedData()
     */
    @Override
    public Data getSelectedData() {
        return labelDataMap.get(textArea.getText());
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.groovy.widgets.TextOrData#getComboSelectionListner()
     */
    @Override
    protected SelectionListener getComboSelectionListner() {
        if (comboSelectionListener == null) {
            comboSelectionListener = new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    String value = combo.getText();
                    if (!getProvidedEntries().contains(value)) { // select
                        // directly a
                        // data
                        if (value.indexOf(DEFAULT_VALUE_START) > 0) {
                            value = value.substring(0, value.indexOf(DEFAULT_VALUE_START));
                        }
                        if (!groovyExpected && value.startsWith(GroovyUtil.GROOVY_PREFIX) && value.endsWith(GroovyUtil.GROOVY_SUFFIX)) {
                            value = value.substring(2, value.length() - 1);
                        }
                        if(value.length()>0) {

                            if(!value.endsWith("...")){
                                int caretPosition = textArea.getCaretPosition();
                                String begin = textArea.getText(0, caretPosition-1);
                                String end = textArea.getText(caretPosition, textArea.getText().length());
                                String res  = begin + value + end;
                                textArea.setSelection(caretPosition + value.length()) ;
                                setText(res);
                            }else{
                                setText(value);
                            }
                        }
                    }
                }

            };
        }
        return comboSelectionListener;
    }

    @Override
    public synchronized void setText(String text) {
        if (!text.endsWith("...")) {
            previousEntry = text;
        }



        combo.setText(text);
    }

    /**
     * @param parent
     * @param isConnectable
     * @param element
     * @param eClasses
     * @param isPassword
     * @param usePassword
     */
    public TextAreaOrData(Composite parent, boolean isConnectable, Element element, Collection<EClass> eClasses, boolean isPassword, boolean usePassword) {
        super(null, isConnectable, element, eClasses, isPassword, usePassword);
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setBackground(parent.getBackground());
        GridLayout layout = new GridLayout(3, false);
        mainComposite.setLayout(layout);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        textArea = new Text(mainComposite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        textArea.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 90).span(3, 1).create());
        Composite blank = new Composite(mainComposite, SWT.NONE);
        GridData blankLayoutData = new GridData(SWT.DEFAULT, SWT.DEFAULT, true, false);
        blankLayoutData.heightHint = 0;
        blank.setLayoutData(blankLayoutData);
        Label label = new Label(mainComposite, SWT.None);
        label.setText(Messages.textAreaSelectDataLabel);

        combo = new TextAreaSuperCombo(mainComposite,textArea);

        reset() ;
        previoustTxtCache = new Listener() {

            @Override
            public void handleEvent(Event event) {
                String entry = ((TextAreaSuperCombo)combo).getTextOnTextArea();
                if (!getProvidedEntries().contains(entry)) {
                    if (entry.indexOf(DEFAULT_VALUE_START) > 0) {
                        entry = entry.substring(0, entry.indexOf(DEFAULT_VALUE_START));
                    }
                    if (!entry.endsWith("...")) {
                        previousEntry = entry;
                    }
                }
            }
        };
        addValueChangedListener(previoustTxtCache);
    }

    @Override
    public void addValueChangedListener(Listener listener) {
        textArea.addListener(SWT.Modify, listener);
    }

    /**
     * @param parent
     * @param element
     * @param arrayList
     * @param isPassword
     * @param usePassword
     */
    public TextAreaOrData(Composite parent, Element element, ArrayList<EClass> arrayList, boolean isPassword, boolean usePassword) {
        this(parent, false, element, arrayList, isPassword, usePassword);
    }

    /**
     * @param parent
     * @param element
     * @param isPassword
     * @param usePassword
     */
    public TextAreaOrData(Composite parent, Element element, boolean isPassword, boolean usePassword) {
        this(parent, element, new ArrayList<EClass>(), isPassword, usePassword);
    }

    /**
     * @param parent
     * @param element
     * @param isPassword
     */
    public TextAreaOrData(Composite parent, Element element, boolean isPassword) {
        this(parent, element, new ArrayList<EClass>(), isPassword, false);
    }

    /**
     * @param parent
     * @param element
     * @param eClasses
     */
    public TextAreaOrData(Composite parent, Element element, Collection<EClass> eClasses) {
        this(parent, false, element, eClasses, false, false);
    }

    /**
     * @param parent
     * @param element
     * @param eClass
     * @param isPassword
     */
    public TextAreaOrData(Composite parent, Element element, EClass eClass, boolean isPassword) {
        this(parent, false, element, Collections.singletonList(eClass), isPassword, false);
    }

    /**
     * @param parent
     * @param element
     */
    public TextAreaOrData(Composite parent, Element element) {
        this(parent, false, element, new ArrayList<EClass>(), false, false);
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.groovy.widgets.TextOrData#doPopulate()
     */
    @Override
    protected void doPopulate() {
        dataEntries = new HashSet<String>();
        providedEntries = new HashSet<String>();
        dataEntries.addAll(populate());

        for (ComboElementsProvider provider : getProviders()) {
            if (provider.appliesTo(this)) {
                for (String item : provider.getElements(this, eObject, eClasses)) {
                    combo.add(item) ;
                    providedEntries.add(item) ;
                }
            }
        }

    }

    @Override
    public List<FieldNode> computeAccessibleData() {
        List<FieldNode> result = super.computeAccessibleData();
        //		if(eObject instanceof SuggestBox && ((SuggestBox) eObject).isAsynchronous()){
        //			result.add(GroovyUtil.createFieldNodeVariable((SuggestBox) eObject));
        //		}
        return result ;
    }

    public Composite getComposite() {
        return mainComposite;
    }

    public void addTextModifiedListener(ModifyListener listener) {
        textArea.addModifyListener(listener);
    }
}
