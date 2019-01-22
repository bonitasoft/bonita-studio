/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.form.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.bonitasoft.studio.model.form.util.FormAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FormItemProviderAdapterFactory extends FormAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.WidgetDependency} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WidgetDependencyItemProvider widgetDependencyItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.WidgetDependency}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWidgetDependencyAdapter() {
		if (widgetDependencyItemProvider == null) {
			widgetDependencyItemProvider = new WidgetDependencyItemProvider(this);
		}

		return widgetDependencyItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.Validator} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidatorItemProvider validatorItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.Validator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createValidatorAdapter() {
		if (validatorItemProvider == null) {
			validatorItemProvider = new ValidatorItemProvider(this);
		}

		return validatorItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.Validable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidableItemProvider validableItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.Validable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createValidableAdapter() {
		if (validableItemProvider == null) {
			validableItemProvider = new ValidableItemProvider(this);
		}

		return validableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.Form} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormItemProvider formItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.Form}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFormAdapter() {
		if (formItemProvider == null) {
			formItemProvider = new FormItemProvider(this);
		}

		return formItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.WidgetLayoutInfo} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WidgetLayoutInfoItemProvider widgetLayoutInfoItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.WidgetLayoutInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWidgetLayoutInfoAdapter() {
		if (widgetLayoutInfoItemProvider == null) {
			widgetLayoutInfoItemProvider = new WidgetLayoutInfoItemProvider(this);
		}

		return widgetLayoutInfoItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.Column} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColumnItemProvider columnItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.Column}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createColumnAdapter() {
		if (columnItemProvider == null) {
			columnItemProvider = new ColumnItemProvider(this);
		}

		return columnItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.Line} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LineItemProvider lineItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.Line}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createLineAdapter() {
		if (lineItemProvider == null) {
			lineItemProvider = new LineItemProvider(this);
		}

		return lineItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.ViewForm} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewFormItemProvider viewFormItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.ViewForm}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createViewFormAdapter() {
		if (viewFormItemProvider == null) {
			viewFormItemProvider = new ViewFormItemProvider(this);
		}

		return viewFormItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.Group} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupItemProvider groupItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.Group}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGroupAdapter() {
		if (groupItemProvider == null) {
			groupItemProvider = new GroupItemProvider(this);
		}

		return groupItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.CheckBoxMultipleFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckBoxMultipleFormFieldItemProvider checkBoxMultipleFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.CheckBoxMultipleFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCheckBoxMultipleFormFieldAdapter() {
		if (checkBoxMultipleFormFieldItemProvider == null) {
			checkBoxMultipleFormFieldItemProvider = new CheckBoxMultipleFormFieldItemProvider(this);
		}

		return checkBoxMultipleFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.ComboFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComboFormFieldItemProvider comboFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.ComboFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComboFormFieldAdapter() {
		if (comboFormFieldItemProvider == null) {
			comboFormFieldItemProvider = new ComboFormFieldItemProvider(this);
		}

		return comboFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.DateFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DateFormFieldItemProvider dateFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.DateFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDateFormFieldAdapter() {
		if (dateFormFieldItemProvider == null) {
			dateFormFieldItemProvider = new DateFormFieldItemProvider(this);
		}

		return dateFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.ListFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListFormFieldItemProvider listFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.ListFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createListFormFieldAdapter() {
		if (listFormFieldItemProvider == null) {
			listFormFieldItemProvider = new ListFormFieldItemProvider(this);
		}

		return listFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.PasswordFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PasswordFormFieldItemProvider passwordFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.PasswordFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPasswordFormFieldAdapter() {
		if (passwordFormFieldItemProvider == null) {
			passwordFormFieldItemProvider = new PasswordFormFieldItemProvider(this);
		}

		return passwordFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.RadioFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RadioFormFieldItemProvider radioFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.RadioFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRadioFormFieldAdapter() {
		if (radioFormFieldItemProvider == null) {
			radioFormFieldItemProvider = new RadioFormFieldItemProvider(this);
		}

		return radioFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.SelectFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SelectFormFieldItemProvider selectFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.SelectFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSelectFormFieldAdapter() {
		if (selectFormFieldItemProvider == null) {
			selectFormFieldItemProvider = new SelectFormFieldItemProvider(this);
		}

		return selectFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.TextFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextFormFieldItemProvider textFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.TextFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTextFormFieldAdapter() {
		if (textFormFieldItemProvider == null) {
			textFormFieldItemProvider = new TextFormFieldItemProvider(this);
		}

		return textFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.TextAreaFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextAreaFormFieldItemProvider textAreaFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.TextAreaFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTextAreaFormFieldAdapter() {
		if (textAreaFormFieldItemProvider == null) {
			textAreaFormFieldItemProvider = new TextAreaFormFieldItemProvider(this);
		}

		return textAreaFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.RichTextAreaFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RichTextAreaFormFieldItemProvider richTextAreaFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.RichTextAreaFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRichTextAreaFormFieldAdapter() {
		if (richTextAreaFormFieldItemProvider == null) {
			richTextAreaFormFieldItemProvider = new RichTextAreaFormFieldItemProvider(this);
		}

		return richTextAreaFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.FormButton} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormButtonItemProvider formButtonItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.FormButton}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFormButtonAdapter() {
		if (formButtonItemProvider == null) {
			formButtonItemProvider = new FormButtonItemProvider(this);
		}

		return formButtonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.SubmitFormButton} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubmitFormButtonItemProvider submitFormButtonItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.SubmitFormButton}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSubmitFormButtonAdapter() {
		if (submitFormButtonItemProvider == null) {
			submitFormButtonItemProvider = new SubmitFormButtonItemProvider(this);
		}

		return submitFormButtonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.PreviousFormButton} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PreviousFormButtonItemProvider previousFormButtonItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.PreviousFormButton}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPreviousFormButtonAdapter() {
		if (previousFormButtonItemProvider == null) {
			previousFormButtonItemProvider = new PreviousFormButtonItemProvider(this);
		}

		return previousFormButtonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.NextFormButton} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NextFormButtonItemProvider nextFormButtonItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.NextFormButton}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNextFormButtonAdapter() {
		if (nextFormButtonItemProvider == null) {
			nextFormButtonItemProvider = new NextFormButtonItemProvider(this);
		}

		return nextFormButtonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.Info} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InfoItemProvider infoItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.Info}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createInfoAdapter() {
		if (infoItemProvider == null) {
			infoItemProvider = new InfoItemProvider(this);
		}

		return infoItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.TextInfo} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextInfoItemProvider textInfoItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.TextInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTextInfoAdapter() {
		if (textInfoItemProvider == null) {
			textInfoItemProvider = new TextInfoItemProvider(this);
		}

		return textInfoItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.MessageInfo} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageInfoItemProvider messageInfoItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.MessageInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMessageInfoAdapter() {
		if (messageInfoItemProvider == null) {
			messageInfoItemProvider = new MessageInfoItemProvider(this);
		}

		return messageInfoItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.CheckBoxSingleFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckBoxSingleFormFieldItemProvider checkBoxSingleFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.CheckBoxSingleFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCheckBoxSingleFormFieldAdapter() {
		if (checkBoxSingleFormFieldItemProvider == null) {
			checkBoxSingleFormFieldItemProvider = new CheckBoxSingleFormFieldItemProvider(this);
		}

		return checkBoxSingleFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.FileWidget} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileWidgetItemProvider fileWidgetItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.FileWidget}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFileWidgetAdapter() {
		if (fileWidgetItemProvider == null) {
			fileWidgetItemProvider = new FileWidgetItemProvider(this);
		}

		return fileWidgetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.ImageWidget} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImageWidgetItemProvider imageWidgetItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.ImageWidget}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createImageWidgetAdapter() {
		if (imageWidgetItemProvider == null) {
			imageWidgetItemProvider = new ImageWidgetItemProvider(this);
		}

		return imageWidgetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.HiddenWidget} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HiddenWidgetItemProvider hiddenWidgetItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.HiddenWidget}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHiddenWidgetAdapter() {
		if (hiddenWidgetItemProvider == null) {
			hiddenWidgetItemProvider = new HiddenWidgetItemProvider(this);
		}

		return hiddenWidgetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.DurationFormField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DurationFormFieldItemProvider durationFormFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.DurationFormField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDurationFormFieldAdapter() {
		if (durationFormFieldItemProvider == null) {
			durationFormFieldItemProvider = new DurationFormFieldItemProvider(this);
		}

		return durationFormFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.Table} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableItemProvider tableItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.Table}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTableAdapter() {
		if (tableItemProvider == null) {
			tableItemProvider = new TableItemProvider(this);
		}

		return tableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.DynamicTable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicTableItemProvider dynamicTableItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.DynamicTable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDynamicTableAdapter() {
		if (dynamicTableItemProvider == null) {
			dynamicTableItemProvider = new DynamicTableItemProvider(this);
		}

		return dynamicTableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.IFrameWidget} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IFrameWidgetItemProvider iFrameWidgetItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.IFrameWidget}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIFrameWidgetAdapter() {
		if (iFrameWidgetItemProvider == null) {
			iFrameWidgetItemProvider = new IFrameWidgetItemProvider(this);
		}

		return iFrameWidgetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.HtmlWidget} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HtmlWidgetItemProvider htmlWidgetItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.HtmlWidget}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHtmlWidgetAdapter() {
		if (htmlWidgetItemProvider == null) {
			htmlWidgetItemProvider = new HtmlWidgetItemProvider(this);
		}

		return htmlWidgetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.SuggestBox} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SuggestBoxItemProvider suggestBoxItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.SuggestBox}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSuggestBoxAdapter() {
		if (suggestBoxItemProvider == null) {
			suggestBoxItemProvider = new SuggestBoxItemProvider(this);
		}

		return suggestBoxItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.bonitasoft.studio.model.form.GroupIterator} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupIteratorItemProvider groupIteratorItemProvider;

	/**
	 * This creates an adapter for a {@link org.bonitasoft.studio.model.form.GroupIterator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGroupIteratorAdapter() {
		if (groupIteratorItemProvider == null) {
			groupIteratorItemProvider = new GroupIteratorItemProvider(this);
		}

		return groupIteratorItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void dispose() {
		if (widgetDependencyItemProvider != null) widgetDependencyItemProvider.dispose();
		if (validatorItemProvider != null) validatorItemProvider.dispose();
		if (validableItemProvider != null) validableItemProvider.dispose();
		if (formItemProvider != null) formItemProvider.dispose();
		if (widgetLayoutInfoItemProvider != null) widgetLayoutInfoItemProvider.dispose();
		if (columnItemProvider != null) columnItemProvider.dispose();
		if (lineItemProvider != null) lineItemProvider.dispose();
		if (viewFormItemProvider != null) viewFormItemProvider.dispose();
		if (groupItemProvider != null) groupItemProvider.dispose();
		if (checkBoxMultipleFormFieldItemProvider != null) checkBoxMultipleFormFieldItemProvider.dispose();
		if (comboFormFieldItemProvider != null) comboFormFieldItemProvider.dispose();
		if (dateFormFieldItemProvider != null) dateFormFieldItemProvider.dispose();
		if (listFormFieldItemProvider != null) listFormFieldItemProvider.dispose();
		if (passwordFormFieldItemProvider != null) passwordFormFieldItemProvider.dispose();
		if (radioFormFieldItemProvider != null) radioFormFieldItemProvider.dispose();
		if (selectFormFieldItemProvider != null) selectFormFieldItemProvider.dispose();
		if (textFormFieldItemProvider != null) textFormFieldItemProvider.dispose();
		if (textAreaFormFieldItemProvider != null) textAreaFormFieldItemProvider.dispose();
		if (richTextAreaFormFieldItemProvider != null) richTextAreaFormFieldItemProvider.dispose();
		if (formButtonItemProvider != null) formButtonItemProvider.dispose();
		if (submitFormButtonItemProvider != null) submitFormButtonItemProvider.dispose();
		if (previousFormButtonItemProvider != null) previousFormButtonItemProvider.dispose();
		if (nextFormButtonItemProvider != null) nextFormButtonItemProvider.dispose();
		if (infoItemProvider != null) infoItemProvider.dispose();
		if (textInfoItemProvider != null) textInfoItemProvider.dispose();
		if (messageInfoItemProvider != null) messageInfoItemProvider.dispose();
		if (checkBoxSingleFormFieldItemProvider != null) checkBoxSingleFormFieldItemProvider.dispose();
		if (fileWidgetItemProvider != null) fileWidgetItemProvider.dispose();
		if (imageWidgetItemProvider != null) imageWidgetItemProvider.dispose();
		if (hiddenWidgetItemProvider != null) hiddenWidgetItemProvider.dispose();
		if (durationFormFieldItemProvider != null) durationFormFieldItemProvider.dispose();
		if (tableItemProvider != null) tableItemProvider.dispose();
		if (dynamicTableItemProvider != null) dynamicTableItemProvider.dispose();
		if (iFrameWidgetItemProvider != null) iFrameWidgetItemProvider.dispose();
		if (htmlWidgetItemProvider != null) htmlWidgetItemProvider.dispose();
		if (suggestBoxItemProvider != null) suggestBoxItemProvider.dispose();
		if (groupIteratorItemProvider != null) groupIteratorItemProvider.dispose();
	}

}
