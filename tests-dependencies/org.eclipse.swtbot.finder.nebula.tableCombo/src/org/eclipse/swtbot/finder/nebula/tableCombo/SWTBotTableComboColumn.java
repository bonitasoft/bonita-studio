package org.eclipse.swtbot.finder.nebula.tableCombo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableColumn;
import org.hamcrest.SelfDescribing;

public class SWTBotTableComboColumn extends AbstractSWTBot<TableColumn>{

	
	private final Table	parent;
	
	
	/**
	 * Constructs a new instance of this object.
	 * 
	 * @param w the widget.
	 * @throws WidgetNotFoundException if the widget is <code>null</code> or widget has been disposed.
	 * @since 2.0
	 */
	public SWTBotTableComboColumn(final TableColumn w) throws WidgetNotFoundException {
		this(w, UIThreadRunnable.syncExec(new WidgetResult<Table>() {
			public Table run() {
				return w.getParent();
			}
		}));
	}
	
	/**
	 * Constructs a new instance of this object.
	 * 
	 * @param w the widget.
	 * @param parent the parent table.
	 * @throws WidgetNotFoundException if the widget is <code>null</code> or widget has been disposed.
	 * @since 2.0
	 */
	public SWTBotTableComboColumn(TableColumn w, Table parent) throws WidgetNotFoundException {
		this(w, parent, null);
	}

	/**
	 * Constructs a new instance of this object.
	 * 
	 * @param w the widget.
	 * @param parent the parent table.
	 * @param description the description of the widget, this will be reported by {@link #toString()}
	 * @throws WidgetNotFoundException if the widget is <code>null</code> or widget has been disposed.
	 * @since 2.0
	 */
	public SWTBotTableComboColumn(TableColumn w, Table parent, SelfDescribing description) throws WidgetNotFoundException {
		super(w, description);
		this.parent = parent;
	}

	/**
	 * Clicks the item.
	 */
	public SWTBotTableComboColumn click() {
		waitForEnabled();
		notify(SWT.Selection);
		notify(SWT.MouseUp, createMouseEvent(0, 0, 1, SWT.BUTTON1, 1), parent);
		return this;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
