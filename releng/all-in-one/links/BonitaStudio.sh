#!/bin/bash
source_path="$(dirname $0)"
cd "$source_path/studio"
vmArgs="-Dfile.encoding=UTF-8 -Xmx512m -Xms256m -XX:MaxPermSize=256m"
if [ "a" = "a$(uname -m | grep 64)" ]; then # computer is 32-bits
	 unset UBUNTU_MENUPROXY; ./BonitaStudio-linux %@ -data ./workspace -vmargs -Dorg.eclipse.swt.browser.DefaultType=webkit -Dbonita_client_home="$source_path" -Dosgi.nls.warnings=ignore $vmArgs
else
	 unset UBUNTU_MENUPROXY; ./BonitaStudio-linux64 %@ -data ./workspace -vmargs -Dorg.eclipse.swt.browser.DefaultType=webkit -Dbonita_client_home="$source_path" -Dosgi.nls.warnings=ignore $vmArgs
fi
	
