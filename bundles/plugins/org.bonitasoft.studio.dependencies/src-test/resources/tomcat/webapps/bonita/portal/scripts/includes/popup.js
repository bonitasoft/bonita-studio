/**
 * Copyright (C) 2011 BonitaSoft S.A. BonitaSoft, 32 rue Gustave Eiffel - 38000
 * Grenoble This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 2.0 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */


function Popup(settings) {
	var self = this;

	var options = $.extend({
	    onOpen : function() {
	    },
	    onClose : function() {
	    },
	    id : false,
	    cleanDomOnClose : false
	}, settings);

	var wrapper = $('<div class="popupwrapper" style="display:none">\
			<div class="popupframe">\
				<div class="popupcontainer">\
					<div id="popupcontainerheader" class="header popupcontainerheader">\
					</div>\
					<div class="body popupcontainerbody"></div>\
					<div class="footer popupcontainerfooter"></div>\
				</div>\
			</div>\
			<div class="popupoverlay"></div>\
		</div>'),
		body = $('.popupcontainerbody:first', wrapper)

	if (options.id) {
		body.attr('id', options.id);
	}

	$('a.close_popup', wrapper).click(function() {
		if (wrapper.is(':visible')) {
			// body.empty();
			// wrapper.fadeOut(75);
			wrapper.hide();

			if (options.cleanDomOnClose) {
				wrapper.remove();
			}

			options.onClose.call(this);
		}
	});
	$('body').append(wrapper);

	function autoResize() {
		$('.popupoverlay:first', wrapper).css({
		    position : 'fixed',
		    'z-index' : 1000,
		    top : 0,
		    left : 0,
		    height : '100%',
		    width : '100%'
		});

		$('.popupframe:first', wrapper).css({
		    position : 'absolute',
		    'z-index' : 1001,
		    width : '100%'
		});
	}

	return {
	    open : function(content) {

		    if (!$.isNull(content)) {
			    body.empty().append(content);
		    }

		    if (wrapper.is(':visible')) {
			    autoResize();
			    return;
		    }
		    wrapper.displayMakeHide(function() {
			    autoResize();
		    });
		    // wrapper.fadeIn(75);
		    wrapper.show();

		    options.onOpen.call(this);

		    $('html, body').animate({
			    scrollTop : 0
		    }, 'slow');

	    },

	    isOpen : function() {
		    return wrapper.is(':visible');
	    },

	    redirect : function(url) {
		    // wrapper.fadeIn(75);
		    wrapper.show();
		    body.redirect(url, function() {
			    if (wrapper.is(':visible')) {
				    autoResize();
				    return;
			    }
			    wrapper.displayMakeHide(function() {
				    autoResize();
			    });

			    options.onOpen.call(this);
		    });
	    },

	    update : function() {
		    wrapper.updateUI(true);
	    },

	    close : function() {
		    if (wrapper.is(':visible')) {
			    // body.empty();
			    // wrapper.fadeOut(75);
		    	$('.popupcontainer').css({
		    		"top": 0,
		    		"left": 0
		    	});
			    wrapper.hide();

			    if (options.cleanDomOnClose) {
				    wrapper.remove();
			    }

			    // body.empty();

			    options.onClose.call(this);
		    }
	    }
	}
};

$(function() {
	$.popup = new Popup({
		id : 'popup'
	});

	$(document).bind('keydown', function(e) {
		if (e.keyCode == 27) {
			$.popup.close()
			return false;
		}
		return true;
	});

	$.uiManager.addMaker(function(context) {
		$("a.close_popup", context).click(function() {
			$.popup.close();
		})
	});


});

function alert(msg) {
	var popup = new Popup({
		cleanDomOnClose : true
	});
	msg = $('<div class="page"><div class="header"><h1>Alert</h1></div><div class="body">' + msg + '<div class="formactions"><a class="button close_popup">OK</a></div></div></div>');
	msg.updateUI();
	msg.find('.formactions a').click(function() {
		popup.close();
	})
	popup.open(msg);
}
