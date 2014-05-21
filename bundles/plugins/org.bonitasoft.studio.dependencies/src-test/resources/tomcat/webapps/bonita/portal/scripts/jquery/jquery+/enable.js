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
 * Enable / disable areas
 */

$(function(){
	/**
	 * Enable an area
	 * @param settings
	 * * duration : animation duration (default = 100)
	 * @return jQuery Return [this]
	 */
	$.fn.enable = function (settings) {
		return this.each(function(){
			var e = $(this);
			var opts = $.extend(true,
				{	opacity:0.3,
					duration:100
				}, e.getOptions(), settings);

			if (e.data('disable')) {
				e.data('disable', false);

				// Inputs
					e.find(':input, select, textarea').each(function(){
						if ($(this).closest(':data(disable)', e).not(e).length){
							return;
						}
						$(this).removeAttr('disabled', 'disabled');
					});
					
				// Opacity
					e.animate({opacity:1/*, color:e.data('dsb_old_color')*/}, opts.duration);
					
//					e.find(':css(color),:css(background-color)').each(function(){
//						var e = $(this);
//						if (e.closest(':data(disable)', e).not(e).length){
//							return;
//						}
//						e.animate({color:e.data('dsb_old_color')}, opts.duration);
//					});
//					
//					e.find('img').each(function() {
//						if ($(this).closest(':data(disable)', e).not(e).length){
//							return;
//						}
//						$(this).animate({opacity:$(this).data('dsb_old_opacity')}, opts.duration);
//					});
					
					e.find('a,input').unbind().unlockBinds();

			}
			
			e.trigger('enable').trigger('change');
		});
		
		this._retrieve_saveStates();
	};
	

	
	/**
	 * Rend un élément et ses enfants inactifs
	 * @param settings Options de désactivation :
	 * * opacity Opacité des élemnts désactivésDurée du fondu de changement d'opacité (default = 100)
	 * * duration Durée du fondu de changement d'opacité (default = 100)
	 * @return jQuery Return [this]
	 */
	$.fn.disable = function (settings) {
		this._enable_saveStates();
		
		return this.each(function(){
			var e = $(this);
			
			var opts = $.extend(true,
				{	opacity:0.3,
					duration:100
				}, e.getOptions(), settings);
		
			if (!e.data('disable')) {
				e.data('disable', true);

				// Etat des inputs
					e.find(':input, select, textarea').add(e.filter(':input, select, textarea')).each(function(){
						$(this).attr('disabled', 'disabled');
					});
				
				// Opacité
					e.animate({opacity:opts.opacity/*, color:$.calcColorOpacity(e.data('dsb_old_color'), opts.opacity, e.backgroundColor())*/}, opts.duration);

					// Change aussi les couleurs surchargée des enfants
//					e.find(':css(color),:css(background-color)').add(e.filter(':css(color),:css(background-color)')).each(function(){
//						var e = $(this);
//						e.animate({opacity:opts.opacity/*, color:$.calcColorOpacity(e.data('dsb_old_color'), opts.opacity, e.backgroundColor())*/}, opts.duration);
//					});
//					
//					e.find('img').add(e.filter('img')).each(function() {
//						$(this).animate({opacity:opts.opacity}, opts.duration);
//					});
					
					e.find('a,input')
						.lockBinds()
						.bind('click.enable', function(){return false;});
			}

			e.trigger('disable').trigger('change');
		});
	};
	
	/**
	 * Enregistre l'état initial des élements avant désactivation
	 * @return jQuery Return [this]
	 */
	$.fn._enable_saveStates = function() {
		return this.each(function(){
			var e = $(this);
			
			// Mémorisation des états initiaux
				if ($.isNull(e.data('dsb_old_color'))) {
					e.data('dsb_old_disable_attr', e.attr('disabled') == 'disabled');
				}
				e.find('[disabled]').each(function(){
					var e = $(this);
					if ($.isNull(e.data('dsb_old_disable_attr'))) {
						e.data('dsb_old_disable_attr', e.attr('disabled') == 'disabled');
					}
				});

				if ($.isNull(e.data('dsb_old_color'))) {
					e.data('dsb_old_color', e.css('color'));
				}
				e.find(':css(color)').each(function(){
					var e = $(this);
					if ($.isNull(e.data('dsb_old_color'))) {
						e.data('dsb_old_color', e.css('color'));
					}
				});
			
			// Apparence
				e.find('img').each(function() {
					var e = $(this);
					if ($.isNull(e.data('dsb_old_opacity'))) {
						e.data('dsb_old_opacity', e.css('opacity'));
					}
				});
			
		});
	};
	
	/**
	 * Enregistre l'état initial des élements avant désactivation
	 * @return jQuery Return [this]
	 */
	$.fn._enable_retrieveStates = function() {
		return this.each(function(){
			var e = $(this);
			
			// Disable attribute
				if (e.data('dsb_old_disable_attr')) {
					e.attr('disabled', 'disabled');
				}
				e.find(':data(dsb_old_disable_attr)').each(function() {
					var e = $(this);
					e.attr('disabled', 'disabled');
				});

			// Color
				if (e.data('dsb_old_color')) {
					e.css('color', e.data('dsb_old_color'));
				}
				e.find(':data(dsb_old_color)').each(function(){
					var e = $(this);
					e.css('color', e.data('dsb_old_color'));
				});
			
			// Opacity
				e.find('img').each(function() {
					var e = $(this);
					e.data('dsb_old_opacity', e.css('opacity'));
				});
			
		});
	};
	
});