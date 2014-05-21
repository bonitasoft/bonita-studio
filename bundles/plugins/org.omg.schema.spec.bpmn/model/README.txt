After generating model, you need to modify load option in XXXResourceFactoryImpl, add:

//Ignore Unknown Feature
result.getDefaultLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
	