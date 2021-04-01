/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.widget;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.ui.widget.BulletPointWidget.BulletPointState;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class BulletPointWidget extends Composite {

    public enum BulletPointState {
        TODO, DONE, ABORTED
    }

    private final String doneBullet = "\u2713";
    private final String emptyBullet = "\u26AA";
    private final String failedBullet = "\u274C";

    private List<BulletPoint> bulletPoints;

    public static class Builder {

        private List<BulletPoint> bulletPoints = new ArrayList<>();

        public Builder addBulletPoint(String text, BulletPointState state) {
            bulletPoints.add(new BulletPoint(text, state));
            return this;
        }

        public BulletPointWidget createIn(Composite container) {
            final BulletPointWidget control = new BulletPointWidget(container, bulletPoints);
            control.setLayoutData(GridDataFactory.fillDefaults().create());
            control.setLayout(GridLayoutFactory.fillDefaults().create());
            return control;
        }
    }

    private BulletPointWidget(Composite parent, List<BulletPoint> bulletPoints) {
        super(parent, SWT.NONE);
        this.bulletPoints = bulletPoints;
        createControl();
    }

    protected void createControl() {
        setLayout(GridLayoutFactory.fillDefaults().create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        bulletPoints.forEach(bulletPoints -> createBulletPoint(bulletPoints));
    }

    private void createBulletPoint(BulletPoint bulletPoint) {
        Label label = new Label(this, SWT.WRAP);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        label.setText(String.format("%s %s", bulletPoint.isTodo()
                ? emptyBullet
                : bulletPoint.isDone()
                        ? doneBullet
                        : failedBullet,
                bulletPoint.getName()));
        label.setBackground(getBackground());
    }

}

class BulletPoint {

    private String name;
    private BulletPointState state;

    public BulletPoint(String name, BulletPointState state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;

    }

    public boolean isDone() {
        return state == BulletPointState.DONE;
    }

    public boolean isTodo() {
        return state == BulletPointState.TODO;
    }

    public boolean isAborted() {
        return state == BulletPointState.ABORTED;
    }
}
