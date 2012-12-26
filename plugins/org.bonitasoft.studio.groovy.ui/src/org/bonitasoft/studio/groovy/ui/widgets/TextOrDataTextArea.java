/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.codehaus.groovy.ast.FieldNode;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * @author Baptiste Mesta
 * 
 */
public class TextOrDataTextArea extends TextOrData {



    private final Text textArea;
    private final Composite buttonsComposite;
    private final Composite mainComposite;

    /**
     * @param parent
     * @param isConnectable
     * @param element
     */
    public TextOrDataTextArea(Composite parent, boolean isConnectable, Element element) {
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
                            setText(value);
                        }
                    }
                }

            };
        }
        return comboSelectionListener;
    }

    /**
     * @param parent
     * @param isConnectable
     * @param element
     * @param eClasses
     * @param isPassword
     * @param usePassword
     */
    public TextOrDataTextArea(Composite parent, boolean isConnectable, Element element, Collection<EClass> eClasses, boolean isPassword, boolean usePassword) {
        super(null, isConnectable, element, eClasses, isPassword, usePassword);
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setBackground(parent.getBackground());
        GridLayout layout = new GridLayout(2, false);
        mainComposite.setLayout(layout);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        textArea = new Text(mainComposite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.V_SCROLL);
        textArea.setLayoutData(GridDataFactory.swtDefaults().span(1, 1).create());
        Composite rightComposite = new Composite(mainComposite, SWT.NONE);
        rightComposite.setBackground(mainComposite.getBackground());
        GridLayout rightLayout = new GridLayout(1, false);
        rightLayout.marginWidth = 0;
        rightLayout.marginHeight = 0;
        rightComposite.setLayout(rightLayout);
        combo = new TextAreaSuperCombo(rightComposite,textArea);
        GridDataFactory.fillDefaults().grab(true, false).applyTo(((TextAreaSuperCombo)combo).getComboControl());
        buttonsComposite = new Composite(rightComposite, SWT.NONE);
        buttonsComposite.setBackground(mainComposite.getBackground());
        GridLayout layout2 = new GridLayout(1,false);
        layout2.marginHeight = 0;
        layout2.marginWidth = 0;
        layout2.verticalSpacing = 1;
        buttonsComposite.setLayout(layout2);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        reset();
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

    /**
     * @param parent
     * @param element
     * @param arrayList
     * @param isPassword
     * @param usePassword
     */
    public TextOrDataTextArea(Composite parent, Element element, ArrayList<EClass> arrayList, boolean isPassword, boolean usePassword) {
        this(parent, false, element, arrayList, isPassword, usePassword);
    }

    /**
     * @param parent
     * @param element
     * @param isPassword
     * @param usePassword
     */
    public TextOrDataTextArea(Composite parent, Element element, boolean isPassword, boolean usePassword) {
        this(parent, element, new ArrayList<EClass>(), isPassword, usePassword);
    }

    /**
     * @param parent
     * @param element
     * @param isPassword
     */
    public TextOrDataTextArea(Composite parent, Element element, boolean isPassword) {
        this(parent, element, new ArrayList<EClass>(), isPassword, false);
    }

    /**
     * @param parent
     * @param element
     * @param eClasses
     */
    public TextOrDataTextArea(Composite parent, Element element, Collection<EClass> eClasses) {
        this(parent, false, element, eClasses, false, false);
    }

    /**
     * @param parent
     * @param element
     * @param eClass
     * @param isPassword
     */
    public TextOrDataTextArea(Composite parent, Element element, EClass eClass, boolean isPassword) {
        this(parent, false, element, Collections.singletonList(eClass), isPassword, false);
    }

    /**
     * @param parent
     * @param element
     */
    public TextOrDataTextArea(Composite parent, Element element) {
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
        for(Control control : buttonsComposite.getChildren()){
            control.dispose();
        }
        for (ComboElementsProvider provider : getProviders()) {
            if (provider.appliesTo(this)) {
                Iterator<SelectionListener> iterator = provider.getListeners(this).iterator();
                for (String item : provider.getElements(this, eObject, eClasses)) {
                    Button button = new Button(buttonsComposite, SWT.FLAT);
                    button.setBackground(buttonsComposite.getBackground());
                    button.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).hint(SWT.DEFAULT, 25).create());
                    button.setText(item);
                    providedEntries.add(item) ;
                    button.setEnabled(true);
                    SelectionListener next = iterator.next();//elements must have the same order than listeners
                    if(next != null){
                        button.addSelectionListener(new TextOrDataListenerWrapper(next,item,(TextAreaSuperCombo) combo));
                    }
                }
            }
        }

        buttonsComposite.layout(true,true) ;
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
}
