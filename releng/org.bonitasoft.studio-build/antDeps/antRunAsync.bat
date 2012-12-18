@echo off
if exist %ANT_HOME%\bin\antRunAsync.js goto RUN_IT
echo ERROR: ANT_HOME\bin\antRunAsync.js was not found!
goto END
:RUN_IT
cscript //NoLogo %ANT_HOME%\bin\antRunAsync.js %1 %2 %3 %4 %5 %6 %7 %8 %9
:END
