#!/bin/sh
set -e

filename=$1
url=$2
workDir=$3
zipFile="$filename.zip"

cd $workDir

zip -q $zipFile -r $filename
curl --request POST -F exeFile=@$zipFile $url > /tmp/$zipFile
cat /tmp/$zipFile > $zipFile
rm -r $filename
unzip -q $zipFile
rm $zipFile