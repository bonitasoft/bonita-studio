/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.views.extension;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorPart;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class ProblemSection extends Composite {

    private Composite container;

    public ProblemSection(Composite parent, String message) {
        this(parent, List.of());

        var problemsDescription = new Label(container, SWT.WRAP);
        problemsDescription.setText(message);
        problemsDescription.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.PROBLEM_TEXT_COLOR);
    }

    public ProblemSection(Composite parent, List<MultiStatus> problems) {
        super(parent, SWT.NONE);
        setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 5, 5).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.PROBLEM_SECTION_BACKGROUND);
        addListener(SWT.Paint, e -> {
            var gc = e.gc;
            gc.setLineStyle(SWT.LINE_SOLID);
            var fillColor = new Color(Display.getDefault(), 237, 137, 54);
            gc.setBackground(fillColor);
            gc.fillRectangle(0, 0, 3, this.getBounds().height);
            fillColor.dispose();
        });

        var problemsTitle = new Label(this, SWT.NONE);
        problemsTitle.setText(Messages.problems);
        problemsTitle.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.PROBLEM_TEXT_COLOR);
        problemsTitle.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.PROBLEM_SECTION_BACKGROUND);
        problemsTitle.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_4_FONT_ID));

        container = new Composite(this, SWT.NONE);
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        container.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10).create());
        container.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.PROBLEM_SECTION_BACKGROUND);
        if (!problems.isEmpty()) {
            problems.stream()
                    .flatMap(multiStatus -> Stream.of(multiStatus.getChildren()))
                    .sorted(Comparator.comparing(IStatus::getSeverity).reversed())
                    .forEach(problem -> {
                        var problemContainer = new Composite(container, SWT.NONE);
                        problemContainer.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
                        problemContainer.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

                        var icon = new Label(problemContainer, SWT.NONE);
                        icon.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.TOP).create());
                        icon.setImage(getImage(problem.getSeverity()));
                        icon.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.PROBLEM_SECTION_BACKGROUND);

                        var problemLabel = new Label(problemContainer, SWT.WRAP);
                        problemLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
                        problemLabel.setText(problem.getMessage());
                        problemLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                                BonitaThemeConstants.PROBLEM_TEXT_COLOR);
                        problemLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.PROBLEM_SECTION_BACKGROUND);
                    });
        }

    }

    private Image getImage(int severity) {
        switch (severity) {
            case IStatus.ERROR:
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
            case IStatus.WARNING:
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
            default:
                return null;
        }
    }

}
