/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 * @author Aurelie Zara
 */
public class NewCategoryDialog extends Dialog {

    private DataBindingContext context;
    private final Category category;
    private final Set<String> existingCatIds;
    private Image iconImage;
    private File iconFile;
    private String displayName;

    public NewCategoryDialog(Shell parentShell, Set<String> existingCatIds) {
        super(parentShell);
        category = ConnectorDefinitionFactory.eINSTANCE.createCategory();
        this.existingCatIds = existingCatIds;

    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(250, SWT.DEFAULT).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 10).create());

        context = new DataBindingContext();

        final Label nameLabel = new Label(mainComposite, SWT.NONE);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        nameLabel.setText(Messages.categoryId);

        final Text idText = new Text(mainComposite, SWT.BORDER);
        idText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        UpdateValueStrategy idStrategy = new UpdateValueStrategy();
        idStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                if (value == null || value.toString().isEmpty()) {
                    return ValidationStatus.error(Messages.idIsEmpty);
                }
                Object[] ids = existingCatIds.toArray();
                for (Object existingId : ids) {
                    if (existingId.toString().equalsIgnoreCase(value.toString())) {
                        return ValidationStatus.error(Messages.idAlreadyExists);
                    }
                }
                return Status.OK_STATUS;
            }
        });

        ControlDecorationSupport.create(context.bindValue(SWTObservables.observeText(idText, SWT.Modify),
                EMFObservables.observeValue(category, ConnectorDefinitionPackage.Literals.CATEGORY__ID), idStrategy, null),
                SWT.LEFT);

        final Label displayNameLabel = new Label(mainComposite, SWT.NONE);
        displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        displayNameLabel.setText(Messages.displayName);

        final Text displayNameText = new Text(mainComposite, SWT.BORDER);
        displayNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        context.bindValue(SWTObservables.observeText(displayNameText, SWT.Modify),
                PojoProperties.value(NewCategoryDialog.class, "displayName").observe(this));

        final Label parentCategoryLabel = new Label(mainComposite, SWT.NONE);
        parentCategoryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        parentCategoryLabel.setText(Messages.parentCategoryLabel);

        final Combo parentCategoryCombo = new Combo(mainComposite, SWT.BORDER | SWT.READ_ONLY);
        parentCategoryCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        Set<String> categories = new HashSet<String>(existingCatIds);
        categories.add("");
        parentCategoryCombo.setItems(categories.toArray(new String[] {}));

        UpdateValueStrategy str = new UpdateValueStrategy();
        str.setConverter(new Converter(String.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject != null && fromObject.toString().isEmpty()) {
                    return null;
                }
                return fromObject;
            }
        });
        context.bindValue(SWTObservables.observeText(parentCategoryCombo),
                EMFObservables.observeValue(category, ConnectorDefinitionPackage.Literals.CATEGORY__PARENT_CATEGORY_ID), str,
                null);

        final Label iconLabel = new Label(mainComposite, SWT.NONE);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        iconLabel.setText(Messages.iconLabel);

        final Composite iconComposite = new Composite(mainComposite, SWT.NONE);
        iconComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        iconComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        final Label icon = new Label(iconComposite, SWT.NONE);
        icon.setLayoutData(GridDataFactory.fillDefaults().create());

        final Button iconButton = new Button(iconComposite, SWT.PUSH);
        iconButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
        iconButton.setText("...");
        iconButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
                dialog.setFilterExtensions(new String[] { "*.jpg;*.jpeg;*.gif;*.png;*.bmp;*.ico" });
                dialog.setFilterPath(System.getProperty("user.home"));
                String res = dialog.open();
                if (res != null) {
                    try (FileInputStream is = new FileInputStream(res);) {
                        iconFile = new File(res);
                        category.setIcon(iconFile.getName());
                        if (iconImage != null) {
                            iconImage.dispose();
                        }
                        iconImage = new Image(Display.getDefault(), new ImageData(is).scaledTo(16, 16));
                        icon.setImage(iconImage);
                        icon.getParent().layout(true, true);
                    } catch (Exception ex) {
                        BonitaStudioLog.error(ex);
                    }
                }
            }
        });
        DialogSupport.create(this, context);
        return mainComposite;
    }

    @Override
    protected Control createButtonBar(Composite parent) {
        Control control = super.createButtonBar(parent);
        Button okButton = getButton(IDialogConstants.OK_ID);
        if (okButton != null) {
            okButton.setEnabled(category.getId() != null && !category.getId().isEmpty());
        }
        return control;
    }

    @Override
    public boolean close() {
        if (context != null) {
            context.dispose();
        }
        if (iconImage != null) {
            iconImage.dispose();
        }
        return super.close();
    }

    public Category getCategory() {
        return category;
    }

    public File getIconImageFile() {
        return iconFile;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
