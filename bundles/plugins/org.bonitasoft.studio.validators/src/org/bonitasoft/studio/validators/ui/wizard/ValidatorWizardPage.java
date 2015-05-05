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

package org.bonitasoft.studio.validators.ui.wizard;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.minMaxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.urlEncodableInputValidator;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.validators.ValidatorPlugin;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorType;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class ValidatorWizardPage extends NewTypeWizardPage implements ISelectionChangedListener{

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;

    protected String packageName;
    protected String className;
    protected String displayName = null;
    private EMFDataBindingContext context;
    private final ValidatorDescriptor validator;
    private WizardPageSupport pageSupport;
    private Button removeButton;

    private final boolean editMode;

    protected ValidatorWizardPage(final ValidatorDescriptor validator, final boolean editMode) {
        super(true, ValidatorWizardPage.class.getName());
        setImageDescriptor(Pics.getWizban());
        setTitle(Messages.ValidatorWizardPage_title) ;
        setDescription(Messages.ValidatorWizardPage_description);
        this.validator =  validator ;
        this.editMode = editMode ;
        final ValidatorSourceRepositorySotre store = RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class) ;
        try {
            final IPackageFragmentRoot root = RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findPackageFragmentRoot(store.getResource().getFullPath());
            setPackageFragmentRoot(root, false) ;
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e) ;
        }

    }

    @Override
    public void dispose() {
        if(pageSupport != null){
            pageSupport.dispose() ;
        }
        if(context != null){
            context.dispose() ;
        }
        super.dispose();
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(new GridLayout(3, false));

        context = new EMFDataBindingContext() ;

        createDisplayNameLine(mainComposite);
        createTypeCombo(mainComposite) ;
        createClassAndPackageName(mainComposite);
        createDependenciesViewer(mainComposite) ;

        setControl(mainComposite);
    }

    @Override
    public void setVisible(final boolean visible){
        super.setVisible(visible);
        if(visible && pageSupport == null){
            pageSupport =  WizardPageSupport.create(this, context) ;
        }
    }

    protected void createTypeCombo(final Composite mainComposite) {
        final Label typeLabel = new Label(mainComposite, SWT.NONE);
        typeLabel.setText(Messages.validatorType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;

        final ComboViewer typeViewer = new ComboViewer(mainComposite, SWT.BORDER | SWT.READ_ONLY) ;
        typeViewer.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(2, 1).create()) ;
        typeViewer.setContentProvider(new ArrayContentProvider()) ;
        typeViewer.setLabelProvider(new LabelProvider(){
            @Override
            public String getText(final Object element) {
                if(element == ValidatorType.FILED_VALIDATOR){
                    return Messages.fieldValidator ;
                }else if(element == ValidatorType.PAGE_VALIDATOR){
                    return Messages.pageValidator ;
                }
                return super.getText(element);
            }
        }) ;
        typeViewer.setInput(ValidatorType.values()) ;
        typeViewer.getCombo().setEnabled(!editMode) ;
        context.bindValue(ViewersObservables.observeSingleSelection(typeViewer), EMFObservables.observeValue(validator, ValidatorPackage.Literals.VALIDATOR_DESCRIPTOR__TYPE)) ;
    }

    protected void createDependenciesViewer(final Composite mainComposite) {
        final Label dependencyLabel = new Label(mainComposite, SWT.NONE);
        dependencyLabel.setText(Messages.dependencies);
        dependencyLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create()) ;

        final TableViewer viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION) ;
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
        viewer.setContentProvider(new ArrayContentProvider()) ;
        viewer.addSelectionChangedListener(this) ;
        viewer.setLabelProvider(new LabelProvider(){
            @Override
            public Image getImage(final Object element) {
                return Pics.getImage("jar.gif",ValidatorPlugin.getDefault());
            }
        }) ;

        context.bindValue(ViewersObservables.observeInput(viewer), EMFObservables.observeValue(validator, ValidatorPackage.Literals.VALIDATOR_DESCRIPTOR__DEPENDENCIES)) ;

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false,true).create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;

        final Button addButton = new Button(buttonComposite, SWT.FLAT) ;
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        addButton.setText(Messages.Add) ;
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final SelectJarsDialog dialog =  new SelectJarsDialog(Display.getDefault().getActiveShell()) ;
                if(dialog.open() == Dialog.OK){
                    for (final IRepositoryFileStore jarFile : dialog.getSelectedJars()) {
                        final String jar = jarFile.getName() ;
                        if(!validator.getDependencies().contains(jar)){
                            validator.getDependencies().add(jar) ;
                        }
                    }
                }
            }

        } ) ;

        removeButton = new Button(buttonComposite, SWT.FLAT) ;
        removeButton.setLayoutData(GridDataFactory.fillDefaults().hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        removeButton.setText(Messages.Remove) ;
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
                for(final Object selected :selection.toList()){
                    if(selected instanceof String){
                        validator.getDependencies().remove(selected) ;
                    }
                }
                viewer.refresh() ;
            }
        }) ;

    }

    private void createDisplayNameLine(final Composite mainComposite) {
        final Label displayNameLabel = new Label(mainComposite, SWT.NONE);
        displayNameLabel.setText(Messages.createValidatorWizardPage_displayNameLabel +" *");
        displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;

        final Text displayNameText = new Text(mainComposite, SWT.BORDER);
        displayNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(2, 1).create());

        context.bindValue(SWTObservables.observeText(displayNameText, SWT.Modify),
                EMFObservables.observeValue(validator, ValidatorPackage.Literals.VALIDATOR_DESCRIPTOR__NAME),
                updateValueStrategy().withValidator(multiValidator()
                        .addValidator(minMaxLengthValidator(Messages.createValidatorWizardPage_displayNameLabel, 1, 50))
                        .addValidator(urlEncodableInputValidator(Messages.createValidatorWizardPage_displayNameLabel)).create()
                        ).create()
                , null);
    }

    private void createClassAndPackageName(final Composite mainComposite) {
        final Label classNameLabel = new Label(mainComposite, SWT.NONE);
        classNameLabel.setText(Messages.createValidatorWizardPage_classNameLabel +" *");
        classNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;

        final Text classNameText = new Text(mainComposite, SWT.BORDER);
        classNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        final Label packageLabel = new Label(mainComposite, SWT.NONE);
        packageLabel.setText(Messages.createValidatorWizardPage_packageLabel+" *");
        packageLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;

        final Text packageText = new Text(mainComposite, SWT.BORDER);
        packageText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true,false).create());


        final UpdateValueStrategy packageTargetToModel = new UpdateValueStrategy() ;
        packageTargetToModel.setConverter(new Converter(String.class,String.class) {

            @Override
            public Object convert(final Object from) {
                if(from != null){
                    final String packageName = from.toString() ;
                    if(classNameText != null && !classNameText.isDisposed()){
                        return packageName +"." + classNameText.getText() ;
                    }
                }
                return null;
            }
        }) ;
        packageTargetToModel.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                if(!value.toString().isEmpty()){
                    return JavaConventions.validatePackageName(value.toString(), JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
                } else {
                	return new Status(IStatus.ERROR, ValidatorPlugin.PLUGIN_ID,Messages.missingPackageName) ;
                }
            }
        }) ;

        final UpdateValueStrategy packageModelToTarget = new UpdateValueStrategy() ;
        packageModelToTarget.setConverter(new Converter(String.class,String.class) {

            @Override
            public Object convert(final Object from) {
                if(from != null){
                    final String qualifiedClassname = from.toString() ;
                    if(qualifiedClassname.indexOf(".") != -1){
                        final int i = qualifiedClassname.lastIndexOf(".") ;
                        return qualifiedClassname.subSequence(0, i) ;
                    }else{
                        return "" ;
                    }
                }
                return null;
            }
        }) ;

        packageModelToTarget.setAfterConvertValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                if(value == null || value.toString().isEmpty()){
                    return new Status(IStatus.ERROR, ValidatorPlugin.PLUGIN_ID,Messages.missingPackageName) ;
                }
                return JavaConventions.validatePackageName(value.toString(), JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
            }
        }) ;


        final UpdateValueStrategy classTargetToModel = new UpdateValueStrategy() ;
        classTargetToModel.setConverter(new Converter(String.class,String.class) {

            @Override
            public Object convert(final Object from) {
                if(from != null){
                    final String className = from.toString() ;
                    if(packageText != null && !packageText.isDisposed()){
                        return packageText.getText() +"." + className ;
                    }
                }
                return null;
            }
        }) ;
        classTargetToModel.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                if(value == null || value.toString().isEmpty()){
                    return new Status(IStatus.ERROR, ValidatorPlugin.PLUGIN_ID,Messages.missingClassname) ;
                }
                return JavaConventions.validateJavaTypeName(value.toString(), JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
            }
        }) ;

        final UpdateValueStrategy classModelToTarget = new UpdateValueStrategy() ;
        classModelToTarget.setConverter(new Converter(String.class,String.class) {

            @Override
            public Object convert(final Object from) {
                if(from != null){
                    final String qualifiedClassname = from.toString() ;
                    if(qualifiedClassname.indexOf(".") != -1){
                        final int i = qualifiedClassname.lastIndexOf(".") ;
                        return qualifiedClassname.subSequence(i+1,qualifiedClassname.length()) ;
                    }else{
                        return qualifiedClassname ;
                    }
                }
                return null;
            }
        }) ;

        context.bindValue(SWTObservables.observeText(classNameText, SWT.Modify), EMFObservables.observeValue(validator, ValidatorPackage.Literals.VALIDATOR_DESCRIPTOR__CLASS_NAME),classTargetToModel,classModelToTarget) ;
        context.bindValue(SWTObservables.observeText(packageText, SWT.Modify), EMFObservables.observeValue(validator, ValidatorPackage.Literals.VALIDATOR_DESCRIPTOR__CLASS_NAME),packageTargetToModel,packageModelToTarget) ;

        final Button browsePackagesButton = new Button(mainComposite, SWT.PUSH);
        browsePackagesButton.setText(Messages.createValidatorWizardPage_browsePackages);
        browsePackagesButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                final IPackageFragment selectedPackage = ValidatorWizardPage.this.choosePackage();
                if (selectedPackage != null) {
                    packageText.setText(selectedPackage.getElementName());
                }
            }
        });

    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        updateButtons(event.getSelection()) ;
    }

    private void updateButtons(final ISelection selection) {
        if(removeButton != null && !removeButton.isDisposed()){
            removeButton.setEnabled(!selection.isEmpty()) ;
        }
    }
}
