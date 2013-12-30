$(function() {

	var PLUGIN_UPLOADER = 'plugin.uploader'

	/**
	 * Override val function
	 */
	var super_val = $.fn.val;
	$.fn.val = function(value) {
		var inputUploader = $(this).data(PLUGIN_UPLOADER)
		if ($.isDefined(inputUploader)) {
			if(value === null) {
                inputUploader.reset();
			} else {
			    return inputUploader.val();
			}
		} else {
			if ($.isNull(value)) {
				// get super value
				return super_val.call(this);
			} else {
				// set super value
				super_val.call(this, value);
			}
			return this;
		}
	}

	/**
	 * Check if all inputs have finished to upload
	 */
	function areAllUploadOver(inputs) {
		for (i = 0; i < inputs.length; i++) {
			var input = $(inputs.get(i)).data(PLUGIN_UPLOADER);
			if ($.isDefined(input) && input.isUploading()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Apply uploader plugin to all inputs
	 */
	function applyPluginOnInputs(inputs) {
		inputs.each(function() {
			// for each input in the form
			var self = $(this);
			self.data(PLUGIN_UPLOADER, new AutoUploader(self));
		})
	}

	/**
	 * Create auto upload plugin
	 */
	$.fn.autoUploader = function(action) {
		switch (action) {
		case 'finished':
			return areAllUploadOver($(':file', this));
		default:
			return applyPluginOnInputs(this);
		}
	}

	/**
	 * Add plugin to makers
	 */
	$.uiManager.addMaker(function(context) {
		$(':file[rel]', context).autoUploader();
	});

	// /////////////////////////////////////////////////////////
	// Auto uploader object definition
	// /////////////////////////////////////////////////////////
	function AutoUploader(fileInput) {
		var __set__ = {}

		var input = fileInput

		var uploaderEl = $('<div class="uploader" id="uploader_'+input.attr("name")+'"><a id="' + input.attr("name")
				+ '"></a></div>')

		var pluploader = new plupload.Uploader({
			runtimes : 'gears,html5,html4,flash,silverlight,browserplus',
			multipart : true,
			container : 'uploader_'+input.attr("name"),
			browse_button : input.attr("name"),
			url : input.getOption('url'),
			flash_swf_url : 'scripts/ext/plupload.flash.swf',
			silverlight_xap_url : 'scripts/ext/plupload.silverlight.xap',
			filters : input.getOption('filters')
		})

		// /////////////////////////////////////////////////////////
		// private
		// /////////////////////////////////////////////////////////

		var UPLOADER_VALUE = 'uploader_value',
		    UPLOADING_STATUS = 'uploading_status',
		    CSS_IS_READY = 'ready',
		    CSS_IS_UPLOADING = 'uploading',
		    CSS_IS_FAILED = 'failed',
		    CSS_IS_DONE = 'done';

		// object initialization
		var init = function() {

			input.after(uploaderEl).hide()

			pluploader.bind('QueueChanged', onQueueChanged)
			pluploader.bind('FileUploaded', onFileUploaded)
			pluploader.bind('Error', onError)
			pluploader.init()

			updateUploaderState(false, "", CSS_IS_READY, input
					.getOption('text.filepicker'))
		}

		/**
		 * Fired when the file queue is changed.
		 */
		var onQueueChanged = function(pluploader) {
			if (pluploader.files.length > 0) {
				$('div.alert_message.ERROR').remove();
				updateUploaderState(true, pluploader.files[0].name,
						CSS_IS_UPLOADING, pluploader.files[0].name);

                /* WTF... */
				var cache = document.createElement("div");
				cache.id="uploadCache_"+input.attr("name");
				cache.style.position="absolute";
				cache.style.background="#000000";
				cache.style.opacity="0.3";
				cache.style.width="100%";
				cache.style.height="100%";
				cache.style.top="0";
				cache.style.left="0";
				$('div.page_processupload div.fileupload div.input div#uploader'+input.attr("name")).append(cache);
				/* ...is that?! */

				if (!$("a.installUpload").hasClass("disabled")) {
					$("a.installUpload").addClass("disabled");
				}
				pluploader.start();
			}
		}

		/**
		 * Fired when a file is successfully uploaded.
		 */
		var onFileUploaded = function(pluploader, file, response) {
			$('div.alert_message.ERROR').remove();
			pluploader.removeFile(file)
			$("a.installUpload").removeClass("disabled");
			$("#uploadCache_"+input.attr("name")).remove();
			updateUploaderState(false, response.response, CSS_IS_DONE)
		}

		var addError = function(message) {
			$('div.alert_message.ERROR').remove();
			$('#filepicker_' + input.attr("name") + ', #uploader_' + input.attr("name")).after(
					'<div class="alert_message ERROR">' + message + '</div>');
		}

		/**
		 * Fires when a error occurs.
		 */
		var onError = function(pluploader, file, response) {
			pluploader.removeFile(file.file)
			if (file.code == -601) {
				addError(input.getOption('text.extensionerror'));
			} else if (file.code == -500) {
				addError('Our uploader doesn\'t support your browser. Support for: gears, html5, flash, silverlight, browserplus');
			} else {
				addError(file.message);
			}
			updateUploaderState(false, file.message, CSS_IS_FAILED)
		}

		/**
		 * Update object status, values, style as well as message
		 */
		var updateUploaderState = function(isUploading, value, cssClass,
				message) {
			cleanAllCssClasses()
			uploaderEl.addClass(cssClass)

			if ($.isDefined(message)) {
				$(uploaderEl.children()[0], uploaderEl).text(message)
			}

			input.data(UPLOADING_STATUS, isUploading)
			    .data(UPLOADER_VALUE, value);
		}

		var cleanAllCssClasses = function() {
			uploaderEl.removeClass(CSS_IS_READY)
			        .removeClass(CSS_IS_UPLOADING)
					.removeClass(CSS_IS_DONE)
					.removeClass(CSS_IS_FAILED);
		}

		// /////////////////////////////////////////////////////////
		// public
		// /////////////////////////////////////////////////////////

		__set__.isUploading = function() {
			return input.data(UPLOADING_STATUS)
		}

		__set__.val = function() {
			return input.data(UPLOADER_VALUE)
		}

		__set__.reset = function () {
		    updateUploaderState(false, "", CSS_IS_READY, input.getOption('text.filepicker'));

		}

		// /////////////////////////////////////////////////////////
		// constructor
		// /////////////////////////////////////////////////////////

		init()

		return __set__
	}

});