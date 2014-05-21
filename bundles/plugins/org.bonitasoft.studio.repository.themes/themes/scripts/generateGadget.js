/**
 * Generate gadget js
 */
function generateMyGadgets(gadgetChromeIds, specUrls, titles) {
		var chromeIds;
		var gadgetURLs;
		var gadgetTitles;
		if (gadgetChromeIds) {
			chromeIds = gadgetChromeIds.split(",");
		}
		if (specUrls) {
			gadgetURLs = specUrls.split(",");
		}
		if (titles) {
			gadgetTitles = titles.split(",");
		}
		shindig.container.layoutManager.setGadgetChromeIds(chromeIds);
		
		for ( var i = 0; i < gadgetURLs.length; i++) {
			var gadget = shindig.container.createGadget({specUrl:gadgetURLs[i],width:500});
			gadget.title = gadgetTitles[i];
			shindig.container.addGadget(gadget);
		}
		shindig.container.renderGadgets();
};