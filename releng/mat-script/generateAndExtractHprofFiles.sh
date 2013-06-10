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
${MAT_HOME}/MemoryAnalyzer -consolelog -application org.eclipse.mat.api.parse $i org.eclipse.mat.api:suspects
#${MAT_HOME}/MemoryAnalyzer -consolelog -clean -application org.eclipse.mat.api.parse $i org.bonitasoft.mat.report:custom_report
rm ${i%.*}.*.index ${i%.*}.index ${i%.*}.threads
unzip -o ${i%.*}"_Leak_Suspects.zip" -d ${i%.*}"_html"
#unzip -o ${i%.*}"__Studio_Report_Histo.zip" -d ${i%.*}"_custom_html"
rm ${i%.*}"_Leak_Suspects.zip"
#rm ${i%.*}"__Studio_Report_Histo.zip"
done
