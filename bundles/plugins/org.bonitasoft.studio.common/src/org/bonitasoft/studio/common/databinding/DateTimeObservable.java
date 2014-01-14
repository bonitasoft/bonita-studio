package org.bonitasoft.studio.common.databinding;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.DateTime;

public class DateTimeObservable extends AbstractObservableValue {

	private final DateTime dateTime;

	protected Date oldValue;

	SelectionListener listener = new SelectionListener() {

		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub

		}

		public void widgetSelected(SelectionEvent e) {

			Date newValue = dateTimeToDate();

			if (!newValue.equals(DateTimeObservable.this.oldValue)) {
				fireValueChange(Diffs.createValueDiff(DateTimeObservable.this.oldValue, newValue));
				DateTimeObservable.this.oldValue = newValue;

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
			Date date = (Date) value;
			dateToDateTime(date);
		}
	}


	private void dateToDateTime(final Date date) {
		if (!this.dateTime.isDisposed()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			this.dateTime.setYear(cal.get(Calendar.YEAR));
			this.dateTime.setMonth(cal.get(Calendar.MONTH));
			this.dateTime.setDay(cal.get(Calendar.DAY_OF_MONTH));
			this.dateTime.setHours(cal.get(Calendar.HOUR_OF_DAY));
			this.dateTime.setMinutes(cal.get(Calendar.MINUTE));
			this.dateTime.setSeconds(cal.get(Calendar.SECOND));
		}
	}

	private Date dateTimeToDate() {
		Date result = null;
		if (!this.dateTime.isDisposed()) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, this.dateTime.getYear());
			cal.set(Calendar.MONTH, this.dateTime.getMonth());
			cal.set(Calendar.DAY_OF_MONTH, this.dateTime.getDay());
			cal.set(Calendar.HOUR_OF_DAY, this.dateTime.getHours());
			cal.set(Calendar.MINUTE, this.dateTime.getMinutes());
			cal.set(Calendar.SECOND, this.dateTime.getSeconds());
			result = cal.getTime();
		}
		return result;
	}

	@Override
	public synchronized void dispose() {
		this.dateTime.removeSelectionListener(this.listener);
		super.dispose();
	}

	public Object getValueType() {
		return Date.class;
	}
}
