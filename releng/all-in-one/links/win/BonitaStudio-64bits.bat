cd studio
::Generate the bootclasspath using jars in endorsed folder
set bootclasspath=
FOR %%f IN ("%CD%\endorsed\*") DO call :Loop "%%f"

goto :launchExe

:Loop
Set bootclasspath=%bootclasspath%;%1
GoTo :EOF

:launchExe
BonitaStudio-x86_64.exe %* -vmargs "-Dbonita_client_home=%CD%\.." -Dosgi.nls.warnings=ignore -Dfile.encoding=UTF-8 -Xmx512m -Xms256m -XX:MaxPermSize=256m -Xbootclasspath/a:%bootclasspath% -Dgreclipse.nonlocking=true
