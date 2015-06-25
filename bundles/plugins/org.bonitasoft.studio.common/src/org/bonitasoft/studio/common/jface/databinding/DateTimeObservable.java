package org.bonitasoft.studio.common.jface.databinding;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.DateTime;

public class DateTimeObservable extends AbstractObservableValue {

    private final DateTime dateTime;

    protected Date oldValue;

    SelectionListener listener = new SelectionAdapter() {

        @Override
        public void widgetSelected(final SelectionEvent e) {

            final Date newValue = dateTimeToDate();

            if (!newValue.equals(oldValue)) {
                fireValueChange(Diffs.createValueDiff(oldValue, newValue));
                oldValue = newValue;
            }
        }

    };

    public DateTimeObservable(final DateTime dateTime) {
        this.dateTime = dateTime;
        this.dateTime.addSelectionListener(listener);
    }

    @Override
    protected Object doGetValue() {
        return dateTimeToDate();
    }

    @Override
    protected void doSetValue(final Object value) {
        if (value instanceof Date) {
            final Date date = (Date) value;
            dateToDateTime(date);
        }
    }

    private void dateToDateTime(final Date date) {
        if (!dateTime.isDisposed()) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            dateTime.setYear(cal.get(Calendar.YEAR));
            dateTime.setMonth(cal.get(Calendar.MONTH));
            dateTime.setDay(cal.get(Calendar.DAY_OF_MONTH));
            dateTime.setHours(cal.get(Calendar.HOUR_OF_DAY));
            dateTime.setMinutes(cal.get(Calendar.MINUTE));
            dateTime.setSeconds(cal.get(Calendar.SECOND));
        }
    }

    private Date dateTimeToDate() {
        Date result = null;
        if (!dateTime.isDisposed()) {
            final Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, dateTime.getYear());
            cal.set(Calendar.MONTH, dateTime.getMonth());
            cal.set(Calendar.DAY_OF_MONTH, dateTime.getDay());
            cal.set(Calendar.HOUR_OF_DAY, dateTime.getHours());
            cal.set(Calendar.MINUTE, dateTime.getMinutes());
            cal.set(Calendar.SECOND, dateTime.getSeconds());
            result = cal.getTime();
        }
        return result;
    }

    @Override
    public synchronized void dispose() {
        dateTime.removeSelectionListener(listener);
        super.dispose();
    }

    @Override
    public Object getValueType() {
        return Date.class;
    }
}
