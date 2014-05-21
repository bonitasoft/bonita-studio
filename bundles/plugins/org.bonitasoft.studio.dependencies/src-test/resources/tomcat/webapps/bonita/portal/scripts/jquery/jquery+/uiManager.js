/*
 * Copyright (c) 2011, Séverin MOUSSEL
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of jQuery+ nor the names of its contributors may be
 *       used to endorse or promote products derived from this software without
 *       specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * UI manager to run multiple plugins only on manual call and contextually
 * 
 * 1. Add update functions using : $.uiManager.add(function(context){...},
 * order, delayed); <br />
 * 2. Add cleaner functions using :
 * $.uiManager.addCleaner(function(context){...}, order); <br />
 * 
 * While an area change its DOM : $(area).updateUI(true); <br />
 * While a new area appears $(area).updateUI(false);<br />
 * Before an area disappear : $(area).cleanUI(false);
 * 
 */

$(function() {
	/**
	 * UI manager to automatically initialize multiple plugins
	 */
	$.uiManager = new function() {
		return {
			/**
			 * List of update functions
			 * 
			 * @param (Array)
			 */
			makers : new Array(),

			/**
			 * List of update functions delayed
			 * 
			 * @param (Array)
			 */
			delayedMakers : new Array(),

			/**
			 * List of cleaner functions
			 * 
			 * @param (Array)
			 */
			cleaners : new Array(),

			/**
			 * Add an update function
			 * 
			 * @param func
			 *            (function) An update function. This function must take
			 *            a unique parameter which is the context.
			 * @param order
			 *            (integer)|(String) Priority of execution.<br />
			 *            Functions with the same priority will be executed in
			 *            the same order as they are added to the updater.<br />
			 *            This value can be numeric (smaller numbers will be
			 *            executed sooner) or one of the following string :
			 *            <dl>
			 *            <dt>first</dt>
			 *            <dd>Will be executed before any other orders (even
			 *            before negative numbers)</dd>
			 *            <dt>last</dt>
			 *            <dd>Will be executed after any other orders (even big
			 *            numbers)</dd>
			 *            </dl>
			 * @param delayed
			 *            (boolean) If TRUE, this function will be executed
			 *            after repaint and its order depends on other delayed
			 *            functions.<br />
			 *            If a function doesn't repaint or if it only repaint
			 *            hidden elements it must be delayed in oprder to
			 *            optimize the repaint time.
			 * @return This function return the $.uiManager object in order to
			 *         allow cascading calls.
			 */
			addMaker : function(func, order, delayed) {
				// Default value for order is 'last'
				if (order == undefined) {
					order = 'last';
				}

				// Registering the maker
				if (!delayed) {
					if ($.isNull($.uiManager.makers[order])) {
						$.uiManager.makers[order] = new Array();
					}
					$.uiManager.makers[order].push(func);
				} else {
					if ($.isNull($.uiManager.delayedMakers[order])) {
						$.uiManager.delayedMakers[order] = new Array();
					}
					$.uiManager.delayedMakers[order].push(func);
				}

				return $.uiManager;
			},

			/**
			 * Add a cleaner function
			 * 
			 * @param func
			 *            (function) A cleaner function. This function must take
			 *            a unique parameter which is the context.
			 * @param order
			 *            (integer)|(String) Priority of execution.<br />
			 *            Functions with the same priority will be executed in
			 *            the same order as they are added to the updater.<br />
			 *            This value can be numeric (smaller numbers will be
			 *            executed sooner) or one of the following string :
			 *            <dl>
			 *            <dt>first</dt>
			 *            <dd>Will be executed before any other orders (even
			 *            before negative numbers)</dd>
			 *            <dt>last</dt>
			 *            <dd>Will be executed after any other orders (even big
			 *            numbers)</dd>
			 *            </dl>
			 * @return This function return the $.uiManager object in order to
			 *         allow cascading calls.
			 */
			addCleaner : function(func, order) {
				// Default value for order is 'last'
				if (order == undefined) {
					order = 'last';
				}

				// Registering cleaner
				if ($.isNull($.uiManager.cleaners[order])) {
					$.uiManager.cleaners[order] = new Array();
				}
				$.uiManager.cleaners[order].push(func);

				return $.uiManager;
			},

			/**
			 * Get the sorted orders from an array of makers/cleaners
			 * 
			 * @private
			 * @param a
			 *            (Array) The array from which to extract orders
			 * @return (Array) This function returns the list of orders sorted.
			 */
			sortOrders : function(a) {
				// Sorting makers
				var orders = new Array();
				for ( var order in a) {
					if (order != 'first' && order != 'last') {
						orders.push(order);
					}
				}
				orders.sort();
				orders.push('last');
				orders.reverse();
				orders.push('first');
				orders.reverse();

				return orders;
			},

			/**
			 * Call for an area update
			 * 
			 * @param context
			 *            (String)|(jQuery)|(domElement) The context is an
			 *            element or a set of elements whose <strong>content</strong>
			 *            will be updated
			 * @param force
			 *            (boolean) If TRUE, the area will be forced to update.
			 *            If FALSE, the function will check if it hadn't be
			 *            updated already.
			 * @return This function return the $.uiManager object in order to
			 *         allow cascading calls.
			 */
			update : function(context, force) {
				// If the same area is already updating its delayed makers, we
				// stop it.
				if (context.data('delayedupdater')) {
					clearTimeout(context.data('delayedupdater'));
				}

				// If the same area has already been updated and the update
				// isn't forced, we leave the update process.
				if (!force && context.data('uiUpdated')) {
					return;
				}

				// Before updating, we ensure that all IDs in the new content
				// are uniques.
				if (!context.is('body')) {
					context.makeIdsUnique();
				}

				// Running makers
				var orders = this.sortOrders($.uiManager.makers);
				for ( var order in orders) {
					order = orders[order];
					for ( var func in $.uiManager.makers[order]) {
						$.uiManager.makers[order][func](context);
					}
				}

				// Mark the context as updated
				context.data('uiUpdated', true);

				// Set the timer for delayed makers
				context.data('delayedupdater', setTimeout(function() {
					context.removeData('delayedupdater');
					$.uiManager.updateDelayed(context);
				}, 200));

				return $.uiManager;
			},

			/**
			 * Call for an area update
			 * 
			 * @private
			 * @param context
			 *            (String)|(jQuery)|(domElement) The context is an
			 *            element or a set of elements whose <strong>content</strong>
			 *            will be updated
			 */
			updateDelayed : function(context) {
				var orders = this.sortOrders($.uiManager.delayedMakers);

				for ( var order in orders) {
					order = orders[order];
					for ( var func in $.uiManager.delayedMakers[order]) {
						$.uiManager.delayedMakers[order][func](context);
					}
				}
			},

			/**
			 * Call for an area clean
			 * 
			 * @param context
			 *            (String)|(jQuery)|(domElement) The context is an
			 *            element or a set of elements whose <strong>content</strong>
			 *            will be cleaned
			 * @return This function return the $.uiManager object in order to
			 *         allow cascading calls.
			 */
			clean : function(context) {
				var orders = this.sortOrders($.uiManager.cleaners);

				for ( var order in orders) {
					order = orders[order];
					for ( var func in $.uiManager.cleaners[order]) {
						$.uiManager.cleaners[order][func](context);
					}
				}

				return $.uiManager;
			}
		};
	}();

	/**
	 * Call the UI update
	 * 
	 * @param force
	 *            (boolean) If TRUE, the area will be forced to update. If
	 *            FALSE, the function will check if it hadn't be updated
	 *            already.
	 * @return (jQuery) This function returns <this> to allow cascading calls
	 */
	$.fn.updateUI = function(force) {
		$.uiManager.update(this, force);
		return this;
	};

	/**
	 * Call the UI cleaning
	 * 
	 * @return (jQuery) This function returns <this> to allow cascading calls
	 */
	$.fn.cleanUI = function() {
		$.uiManager.clean(this);
		return this;
	};

});