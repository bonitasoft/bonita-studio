// antRunAsync.js - Wrapper script to run an executable detached in the 
// background from Ant's <exec> task.  This works by running the executable
// using the Windows Scripting Host WshShell.Run method which doesn't copy
// the standard filehandles stdin, stdout and stderr. Ant finds them closed
// and doesn't wait for the program to exit.
//
// requirements:
//   Windows Scripting Host 1.0 or better.  This is included with Windows 
//   98/Me/2000/XP.  Users of Windows 95 or Windows NT 4.0 need to download
//   and install WSH support from 
//   http://msdn.microsoft.com/scripting/.
//
// usage:
// <exec executable="cscript.exe">
//   <env key="ANTRUN_TITLE" value="Title for Window" />  <!-- optional -->
//   <env key="ANTRUN_OUTPUT" value="output.log" />  <!-- optional -->
//   <arg value="//NoLogo" />
//   <arg value="antRunAsync.js" />  <!-- this script -->
//   <arg value="real executable" />
// </exec>


var WshShell = WScript.CreateObject("WScript.Shell");
var exeStr = "%comspec% /c";
var arg = "";
var windowStyle = 1;
var WshProcessEnv = WshShell.Environment("PROCESS");
var windowTitle = WshProcessEnv("ANTRUN_TITLE");
var outputFile = WshProcessEnv("ANTRUN_OUTPUT");
var OS = WshProcessEnv("OS");
var isWindowsNT = (OS == "Windows_NT");

// On Windows NT/2000/XP, specify a title for the window.  If the environment
// variable ANTRUN_TITLE is specified, that will be used instead of a default.
if (isWindowsNT) {
  if (windowTitle == "")
     windowTitle = "Ant - " + WScript.Arguments(i);
  exeStr += "title " + windowTitle + " &&";
}

// Loop through arguments quoting ones with spaces
for (var i = 0; i < WScript.Arguments.count(); i++) {
  arg = WScript.Arguments(i);
  if (arg.indexOf(' ') > 0)
    exeStr += " \"" + arg + "\"";
  else
    exeStr += " " + arg;
}

// If the environment variable ANTRUN_OUTPUT was specified, redirect
// output to that file.
if (outputFile != "") {
  windowStyle = 7;  // new window is minimized
  exeStr += " > \"" + outputFile + "\"";
  if (isWindowsNT)
    exeStr += " 2>&1";
}

// WScript.Echo(exeStr);
// WshShell.Run(exeStr);
WshShell.Run(exeStr, windowStyle, false);
