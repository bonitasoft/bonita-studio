#!/bin/sh
#
##
# Copyright (C) 2013 BonitaSoft S.A.
# BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 2.0 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
##
#
# Script to generate memory leaks report (hprof files)
#

echo $(pwd)
listOfHprof=$(find -name "*.hprof")
echo "$listOfHprof"
for i in $listOfHprof
do

	file=${i%.*}

	echo "\n******* Generate default report for "${file}" ********"
	${MAT_HOME}/MemoryAnalyzer -consolelog -application org.eclipse.mat.api.parse $i org.eclipse.mat.api:suspects
	unzip -o ${file}"_Leak_Suspects.zip" -d ${file}"_html"
	rm -v ${file}"_Leak_Suspects.zip"

	echo "\n******* Generate custom report for "${file}" *******"
	${MAT_HOME}/MemoryAnalyzer -consolelog -clean -application org.eclipse.mat.api.parse $i org.bonitasoft.mat.report:custom_report
	if [ -f ${file}"__Studio_Report_Histo.zip" ] 
	then	
		unzip -o ${file}"__Studio_Report_Histo.zip" -d ${file}"_custom_html"
		rm -v ${file}"__Studio_Report_Histo.zip"
	else
		echo "No file ${file}__Studio_Report_Histo.zip found"
	fi

	# remove useless generated files
	rm -v ${file}.*.index ${file}.index ${file}.threads
	

done
