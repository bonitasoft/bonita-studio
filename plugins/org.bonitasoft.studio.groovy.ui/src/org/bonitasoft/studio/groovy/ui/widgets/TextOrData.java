/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.XMLData;
import org.codehaus.groovy.ast.FieldNode;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Mickael Istria
 * @author Romain Bioteau
 */
public class TextOrData {

    public static enum Type {
        READ, WRITE;
    }

    protected static final String DEFAULT_VALUE_START = " ( default = "; //$NON-NLS-1$
    private static final String COMBO_PROVIDER_EXTENSION_POINT_ID = "org.bonitasoft.studio.groovy.ui.comboElementProvider";
    private static List<ComboElementsProvider> providers;
    private static List<ITextOrDataFactory> widgetProviders;
    private static final String TEXTORDATA_FACTORY_PROVIDER_EXTENSION_POINT_ID = "org.bonitasoft.studio.groovy.ui.textordataFactory";

    protected Type ioType = Type.READ;
    protected SuperCombo combo;
    protected Element eObject;
    protected String previousEntry = ""; //$NON-NLS-1$
    protected Collection<EClass> eClasses;

    protected Listener previoustTxtCache = new Listener() {

        @Override
        public void handleEvent(Event event) {
            if (!getProvidedEntries().contains(combo.getText())) {
                String entry = combo.getText();
                if (entry.indexOf(DEFAULT_VALUE_START) > 0) {
                    entry = entry.substring(0, entry.indexOf(DEFAULT_VALUE_START));
                }
                if (!entry.endsWith("...")) {
                    previousEntry = entry;
                }
            }
        }
    };

    protected SelectionListener comboSelectionListener = null;

    protected boolean groovyExpected = true;
    private boolean showGroovyPrefiSuffix = true;
    protected List<Connector> connectors;
    protected boolean isConnectable = false;
    private EStructuralFeature eFeature;
    protected Map<String, Data> labelDataMap = new HashMap<String, Data>();
    private EditingDomain domain;
    protected Set<String> dataEntries;
    protected Set<String> providedEntries;

    public TextOrData(Composite parent, Element element, boolean isPassword, boolean usePassword) {
        this(parent, element, new ArrayList<EClass>(), isPassword, usePassword);
    }

    public TextOrData(Composite parent, Element element, boolean isPassword) {
        this(parent, element, new ArrayList<EClass>(), isPassword, false);
    }

    public TextOrData(Composite parent, Element element, ArrayList<EClass> arrayList, boolean isPassword, boolean usePassword) {
        this(parent, false, element, arrayList, isPassword, usePassword);
    }

    public TextOrData(Composite parent, boolean isConnectable, Element element) {
        this(parent, isConnectable, element, new ArrayList<EClass>(), false, false);

    }

    public TextOrData(Composite parent, Element element, EClass eClass, boolean isPassword) {
        this(parent, false, element, Collections.singletonList(eClass), isPassword, false);
    }

    public TextOrData(Composite parent, boolean isConnectable, Element element, Collection<EClass> eClasses, boolean isPassword, boolean usePassword) {
        combo = createSuperCombo(parent, isPassword, usePassword);
        this.isConnectable = isConnectable;
        connectors = new ArrayList<Connector>();
        eObject = element;
        this.eClasses = eClasses;
        if(combo != null){
            reset();
            addValueChangedListener(previoustTxtCache);
        }
    }

    /**
     * @param parent
     * @param isPassword
     * @param usePassword
     */
    protected SuperCombo createSuperCombo(Composite parent, boolean isPassword, boolean usePassword) {
        if (parent instanceof CCombo) {
            return new DefaultSuperCCombo((CCombo)parent);
        } else if (parent != null) {
            if (isPassword) {
                return new PasswordSuperCombo(parent);
            } else if (usePassword) {
                return new SimplePasswordSuperCombo(parent);
            } else {
                return new DefaultSuperCombo(parent);
            }
        } else {
            return null;
        }
    }

    public TextOrData(Composite parent, Element element, Collection<EClass> eClasses) {
        this(parent, false, element, eClasses, false, false);
    }

    public TextOrData(Composite parent, Element element) {
        this(parent, false, element, new ArrayList<EClass>(), false, false);
    }

    public void addConnectors(Collection<Connector> connectors) {
        if (isConnectable) {
            this.connectors.clear();
            this.connectors.addAll(connectors);
            addConnectors();
        }
    }

    /**
     * @param name
     * @return
     */
    protected boolean containsDataWithName(String name) {
        int i = 0;
        while (i < combo.getItemCount()) {
            if (combo.getItem(i).equals(showGroovyPrefiSuffix ? (GroovyUtil.GROOVY_PREFIX + name + GroovyUtil.GROOVY_SUFFIX) : name)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public synchronized String getText() {
        return combo.getText();
    }

    /**
     * @param defaultValue
     */
    public synchronized void setText(String text) {
        // TODO :instead of remove them and keep only the current, kept in
        // memory the old selection in set.
        // List<String> toRemove = new ArrayList<String>();
        // /*Remove old entries, ie the one before the empty entry*/
        // for(int i = 0; i < combo.getItemCount();i++ ){
        // String currentItem = combo.getItem(i);
        // if(currentItem != null && currentItem.length() != 0){
        // toRemove.add(currentItem);
        // } else {
        // break;
        // }
        // }
        // for (String string : toRemove) {
        // combo.remove(string);
        // }
        if (!text.endsWith("...")) {
            previousEntry = text;
        }
        //
        // /*Add the current value and select it (or select empty if none)*/
        // if(text != null){
        // combo.add(text, 0);
        // }
        // combo.select(0);// BUGFIX OPENING POP UP UNDER MAC
        combo.setText(text);
    }

    public Control getControl() {
        return combo.getControl();
    }

    public Element getElement() {
        return eObject;
    }

    /**
     * Adds all entries to the list of suggested values. Sorting is respected
     * and items are added on the top of the list
     * 
     * @param dataTypeLiterals
     */
    public void addAll(Collection<String> entries) {
        int i = 0;
        List<String> currentEntries = new ArrayList<String>();
        for (String entry : entries) {
            if (!currentEntries.contains(entry)) {
                combo.add(entry, i);
                i++;
            }
        }
    }

    public void setElement(Element element) {
        eObject = element;
    }

    public Data getSelectedData() {
        return labelDataMap.get(combo.getText());
    }

    /**
     * Resets the list of suggested values to its original content (at creation)
     * To extend, use extension point
     */
    final public void reset() {
        removeListeners();
        previousEntry = ""; //$NON-NLS-1$
        combo.removeAll();
        combo.setText(""); //$NON-NLS-1$
        combo.add("");
        doPopulate();
        if (isConnectable()) {
            addConnectors(new ArrayList<Connector>(connectors));
        }

        addListeners();
    }

    /**
     * 
     */
    protected void doPopulate() {
        dataEntries = new HashSet<String>();
        providedEntries = new HashSet<String>();
        dataEntries.addAll(populate());
        for (ComboElementsProvider provider : getProviders()) {
            if (provider.appliesTo(this)) {
                for (String item : provider.getElements(this, eObject, eClasses)) {
                    combo.add(item);
                    providedEntries.add(item) ;
                }
            }
        }
    }

    /**
     * 
     * @return A set containing the entries related with data
     */
    public Set<String> populate() {
        Set<String> result = new HashSet<String>();
        result.addAll(addWidgetsFields());
        result.addAll(addDatas());
        return result ;
    }

    /**
     * 
     */
    protected Set<String> addWidgetsFields() {
        Set<String> result = new HashSet<String>() ;
        EObject form = null;
        if (eObject instanceof Form) {
            form = eObject;
        } else if (eObject instanceof Widget) {
            form = ModelHelper.getForm((Widget) eObject);
        }
        boolean useOnlyFieldFromOtherForm = true;//it's an action so we use only field from current form
        //		if(eFeature != null){
        //			if(eFeature.equals(ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS)||
        //					eFeature.equals(FormPackage.Literals.WIDGET__OUTPUT_CONNECTORS)){
        //				useOnlyFieldFromOtherForm = false;
        //			}
        //		}
        if (form != null) {
            Element pageFlow = (Element) form.eContainer();
            EStructuralFeature feature = form.eContainingFeature();
            if (pageFlow != null) {
                for (Iterator<?> iterator = ((List<?>)pageFlow.eGet(feature)).iterator(); iterator.hasNext();) {
                    EObject eObject = (EObject) iterator.next();
                    if (!(useOnlyFieldFromOtherForm && eObject.equals(form))) {
                        for (Iterator<?> iterator2 = eObject.eAllContents(); iterator2.hasNext();) {
                            EObject child = (EObject) iterator2.next();
                            if (child instanceof FormField) {
                                String enty = GroovyUtil.GROOVY_PREFIX + ExporterTools.FIELD_IDENTIFIER + ((Element) child).getName() + GroovyUtil.GROOVY_SUFFIX ;
                                getSuperCombo().add(enty);
                                result.add(enty) ;
                            }
                        }
                    }
                }
            }
        }
        if(eObject instanceof SuggestBox && ((SuggestBox) eObject).isAsynchronous()){
            String enty = GroovyUtil.GROOVY_PREFIX + ExporterTools.FIELD_IDENTIFIER +eObject.getName() + GroovyUtil.GROOVY_SUFFIX ;
            getSuperCombo().add(enty);
            result.add(enty) ;
        }
        return result ;
    }

    protected void addSelectionListener(SelectionListener listener) {
        combo.addSelectionListener(listener);
    }

    protected void addListeners() {
        combo.addSelectionListener(getComboSelectionListner());
        for (ComboElementsProvider provider : getProviders()) {
            if (provider.appliesTo(this) && provider.getListeners(this) != null) {
                for (SelectionListener listener : provider.getListeners(this)) {
                    combo.addSelectionListener(listener);
                }
            }
        }
    }

    protected Set<String> getProvidedEntries() {
        return  Collections.unmodifiableSet(providedEntries);
    }

    protected void removeListeners() {
        combo.removeSelectionListener(getComboSelectionListner());
        for (ComboElementsProvider provider : getProviders()) {
            if (provider.appliesTo(this) && provider.getListeners(this) != null) {
                for (SelectionListener listener : provider.getListeners(this)) {
                    combo.removeSelectionListener(listener);
                    provider.resetListeners() ;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected Set<String> addDatas(Collection<EClass> eClasses2) {
        Set<String> result = new HashSet<String>() ;
        EObject currentObject = eObject;

        //		if (eObject instanceof CatchMessageEvent) {
        //			result.addAll(addEventVariables(eClasses2));
        //		}

        if (eObject != null && eObject.eContainer() != null
                && eObject.eContainer() instanceof Group
                && ((Group)eObject.eContainer()).isUseIterator()) {
            result.add(addIteratorVariable((Group)eObject.eContainer()));
        }

        boolean isInEntryPageFlow = false;
        boolean isInViewPageFlow = false ;
        boolean isInOverviewPageFlow = false;
        Form parentForm = null;
        if(eObject instanceof Widget){
            parentForm = ModelHelper.getForm((Widget) eObject);
        }
        if(eObject instanceof Form){
            parentForm = (Form) eObject;
        }
        if(parentForm != null){
            EReference feature = parentForm.eContainmentFeature();
            isInEntryPageFlow = feature!=null && feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM);
            isInViewPageFlow = feature!=null && feature.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM);
            isInOverviewPageFlow = feature!=null && feature.equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS);
        }
        boolean isReadMode = getIOType().equals(Type.READ);

        if(eObject instanceof SequenceFlow){
            currentObject = ((SequenceFlow) eObject).getSource() ;
        }

        while (currentObject != null) {
            if (currentObject.eClass().getEAllStructuralFeatures().contains(ProcessPackage.Literals.DATA_AWARE__DATA)) {
                result.addAll(proceedData(eClasses2, (List<Data>) currentObject.eGet(ProcessPackage.Literals.DATA_AWARE__DATA)));
            }
            currentObject = currentObject.eContainer();
        }
        if (parentForm != null && parentForm.eContainer() instanceof Element) {
            Element pageFlow = (Element) parentForm.eContainer();
            if (isReadMode) {
                if (isInEntryPageFlow && pageFlow.eClass().getEAllStructuralFeatures().contains(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA)) {
                    result.addAll(proceedData(eClasses2, (List<Data>) pageFlow.eGet(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA)));
                }
                if (isInViewPageFlow && pageFlow.eClass().getEAllStructuralFeatures().contains(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA)) {
                    result.addAll(proceedData(eClasses2, (List<Data>) pageFlow.eGet(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA)));
                }
                if (isInOverviewPageFlow && pageFlow.eClass().getEAllStructuralFeatures().contains(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA)) {
                    result.addAll(proceedData(eClasses2, (List<Data>) pageFlow.eGet(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA)));
                }
            }
        }
        if (eObject instanceof MainProcess) {
            result.addAll(addProcessVariables(eClasses2));
        }

        return result ;

    }

    private String addIteratorVariable(Group group) {
        String label = null ;
        if (showGroovyPrefiSuffix) {
            label =  GroovyUtil.GROOVY_PREFIX + group.getIteratorName() + GroovyUtil.GROOVY_SUFFIX;
        } else {
            label = group.getIteratorName() ;
        }
        combo.add(label);
        return label ;
    }

    protected Set<String> proceedData(Collection<EClass> eClasses2, List<Data> datas) {
        Set<String> result = new HashSet<String>() ;
        if (datas != null) {
            for (Data data : datas) {
                if (!containsDataWithName(data.getName())) {
                    if (eClasses2.isEmpty()) {
                        result.add(addData(data));
                    } else {
                        for (EClass eclass : eClasses2) {
                            if (eclass.isSuperTypeOf(data.getDataType().eClass())) {
                                result.add(addData(data));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result ;
    }


    /**
     * @param data
     */
    private String addData(Data data) {
        String label = getDataExpression(data) + addDefaultValue(data);
        labelDataMap.put(getDataExpression(data), data);
        labelDataMap.put(label, data);
        combo.add(label);
        return label ;
    }

    //	private Set<String> addEventVariables(Collection<EClass> eClasses2) {
    //		Set<String> result = new HashSet<String>() ;
    //		if (((CatchMessageEvent) eObject).getEvent() != null && ((CatchMessageEvent) eObject).getEvent().trim().length() > 0) {
    //			Message ev = ModelHelper.findEvent(eObject, ((CatchMessageEvent) eObject).getEvent());
    //			if(ev != null){
    //				result.addAll(proceedData(eClasses2,ev.getData()));
    //			}
    //		}
    //		return result ;
    //	}

    private Set<String> addProcessVariables(Collection<EClass> eClasses2) {
        Set<String> result = new HashSet<String>();
        List<AbstractProcess> parentProcesses = ModelHelper.getAllProcesses(eObject);
        for (AbstractProcess p : parentProcesses) {
            result.addAll(proceedData(eClasses2, p.getData()));
        }
        return result ;
    }

    public String getDataExpression(Data data) {
        String expressionSuffix = "";
        if (data.isMultiple() || data instanceof XMLData || data instanceof JavaObjectData) {
            expressionSuffix = "...";
        }

        if (showGroovyPrefiSuffix) {
            return GroovyUtil.GROOVY_PREFIX + data.getName() + GroovyUtil.GROOVY_SUFFIX + expressionSuffix;
        } else {
            return data.getName() + expressionSuffix;
        }
    }

    public String addDefaultValue(Data data) {
        //		if (data instanceof XMLData || data instanceof JavaObjectData) {
        //			return "";
        //		}
        //		if (data.getDefaultValue() != null && data.getDefaultValue().length() > 0 && data.getDefaultValue().length() < 25) {
        //			return DEFAULT_VALUE_START + data.getDefaultValue() + " )"; //$NON-NLS-1$
        //		} else if (data.getDefaultValue() != null && data.getDefaultValue().length() > 0 && data.getDefaultValue().length() >= 25) {
        //			return DEFAULT_VALUE_START + data.getDefaultValue().substring(0, 14) + "... )"; //$NON-NLS-1$
        //		} else {
        //			return ""; //$NON-NLS-1$
        //		}
        return "" ;
    }

    protected Set<String> addDatas() {
        return addDatas(eClasses);
    }

    protected void addConnectors() {
        if (!getConnectors().isEmpty()) {
            combo.add(getConnectorsNameList(), 1);
        }
    }

    /**
     * @param groovyExpected
     */
    public void setExepectedIsGroovy(boolean groovyExpected) {
        this.groovyExpected = groovyExpected;
    }

    public void setShowGroovyPrefixSuffix(boolean show) {
        showGroovyPrefiSuffix = show;
    }

    /**
     * @param size
     */
    public void setSize(Point size) {
        combo.setSize(size);
    }

    /**
     * @param listener
     */
    public void addValueChangedListener(Listener listener) {
        combo.addListener(SWT.Modify, listener);
    }

    /**
     * @param confirmationMessageListener
     */
    public void removeValueChangedListener(Listener listener) {
        if (listener != null) {
            combo.removeListener(SWT.Modify, listener);
        }
    }

    public static synchronized Collection<ComboElementsProvider> getProviders() {
        if (providers == null) {
            providers = new ArrayList<ComboElementsProvider>();
            IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(COMBO_PROVIDER_EXTENSION_POINT_ID);
            if (extensions.length > 0) {
                Comparator<IConfigurationElement> comparator = new Comparator<IConfigurationElement>() {
                    @Override
                    public int compare(IConfigurationElement ext1, IConfigurationElement ext2) {
                        int priority1 = Integer.parseInt(ext1.getAttribute("priority"));
                        int priority2 = Integer.parseInt(ext2.getAttribute("priority"));
                        return priority1 - priority2;
                    }
                };
                PriorityQueue<IConfigurationElement> queue = new PriorityQueue<IConfigurationElement>(extensions.length, comparator);
                for (IConfigurationElement ext : extensions) {
                    queue.add(ext);
                }
                for (IConfigurationElement ext : queue) {
                    try {
                        providers.add((ComboElementsProvider) ext.createExecutableExtension("providerClass"));
                    } catch (Exception ex) {
                        BonitaStudioLog.error(ex);
                    }
                }
            }
        }
        return providers;
    }

    /**
     * @return
     */
    public List<FieldNode> computeAccessibleData() {
        List<FieldNode> listVariables = new ArrayList<FieldNode>();

        //listVariables = GroovyUtil.createVariablesFromElement(eObject);

        if(eObject != null){
            final EObject eContainer = eObject.eContainer();
            if (eContainer != null
                    && eContainer instanceof Group
                    && ((Group)eContainer).isUseIterator()
                    && ((Group)eContainer).getIteratorName() != null
                    && ((Group)eContainer).getIteratorName().trim().length() > 0) {
                String script = null ;// ((Group)eContainer).getScript().getInputScript() ;
                if(script != null && script.length() > 0){
                    Class<?> iteratorType = resovleIteratorType(script);
                    listVariables.add(GroovyUtil.createVariablesFromGroupIterator((Group)eContainer,iteratorType)) ;
                }
            }
        }

        if (eObject instanceof MainProcess) {
            //            List<AbstractProcess> processes = ModelHelper.getAllProcesses(eObject);
            //            for (AbstractProcess p : processes) {
            //                listVariables.addAll(GroovyUtil.createVariablesFromProcess(p));
            //            }
        }

        return listVariables;
    }

    private Class<?> resovleIteratorType(String inputScript) {
        Class<?> clazz = Object.class ;
        String script  = inputScript ;
        if( script.startsWith(GroovyUtil.GROOVY_PREFIX)){
            script =inputScript.substring(2,inputScript.length()-1);
        }
        for(Data d : labelDataMap.values()){
            if(d.getName().equals(script)){
                if(d.isMultiple()){
                    //		clazz = GroovyUtil.getClassForData(d) ;
                }
            }
        }
        return clazz;
    }

    protected void setComboSelectionListner(SelectionListener listener) {
        comboSelectionListener = listener;
    }

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
                        setText(value) ;
                    }
                }

            };
        }
        return comboSelectionListener;
    }

    /**
     * 
     */
    public void revertValue() {
        if (!combo.getText().equals(previousEntry)) {
            combo.setText(previousEntry);
        }
    }

    public void clear() {
        removeListeners();
        previousEntry = ""; //$NON-NLS-1$
        combo.removeAll();
        combo.setText(""); //$NON-NLS-1$
    }

    public String getPreviousEntry() {
        return previousEntry;
    }

    public void setPreviousEntry(String entry) {
        previousEntry = entry;
    }

    public SuperCombo getSuperCombo() {
        return combo;
    }

    public List<Connector> getConnectors() {
        return Collections.unmodifiableList(connectors);
    }

    private static synchronized List<ITextOrDataFactory> getWidgetProviders() {
        if (widgetProviders == null) {
            widgetProviders = new ArrayList<ITextOrDataFactory>();
            IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                    TEXTORDATA_FACTORY_PROVIDER_EXTENSION_POINT_ID);
            if (extensions.length > 0) {
                for (IConfigurationElement ext : extensions) {
                    try {
                        widgetProviders.add((ITextOrDataFactory) ext.createExecutableExtension("class"));
                    } catch (Exception ex) {
                        BonitaStudioLog.error(ex);
                    }
                }
            }
        }
        return widgetProviders;
    }

    @SuppressWarnings("rawtypes")
    public static synchronized ITextOrDataFactory getTextOrDataFactory(Class type) {
        for (ITextOrDataFactory factory : TextOrData.getWidgetProviders()) {
            if (factory.appliesTo(type)) {
                return factory;
            }
        }
        return null;
    }

    public void clearConnectors() {

        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItem(i).startsWith("[") && combo.getItem(i).endsWith("]")) {
                combo.remove(combo.getItem(i));
            }
        }

        connectors.clear();
    }

    public boolean isConnectable() {
        return isConnectable;
    }

    public void setIsConnectable(boolean connectable) {
        isConnectable = connectable;
    }

    public String getConnectorsNameList() {
        if (isConnectable && !getConnectors().isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            for (Connector c : getConnectors()) {
                builder.append(c.getName());
                builder.append(';');
            }
            builder.delete(builder.length() - 1, builder.length());
            builder.append(']');
            return builder.toString();
        } else {
            return "";
        }
    }

    public EStructuralFeature getConnectorsFeature() {
        return eFeature;
    }

    public void setConnectorsFeaure(EStructuralFeature eFeature) {
        this.eFeature = eFeature;
    }

    public void setEClasses(Collection<EClass> eClasses) {
        this.eClasses = eClasses;
        reset();
    }

    public Set<String> getDataEntries() {
        return Collections.unmodifiableSet(dataEntries);
    }

    public void setIOType(Type type) {
        ioType = type;
    }

    public Type getIOType() {
        return ioType;
    }

    public EditingDomain getEditingDomain() {
        return domain;
    }

    public void setEditingDomain(EditingDomain domain) {
        this.domain = domain;
    }

    /**
     * @return Whether expected result should be Groovy ${...} or not
     */
    public boolean showGroovyPrefixSuffix() {
        return showGroovyPrefiSuffix;
    }
}
