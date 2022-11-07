#!/bin/sh
set -e

filename=$1
url=$2
workDir=$3
zipFile="$filename.zip"

cd $workDir

zip -q $zipFile -r $filename
echo "Signing $zipFile..."
HTTP_STATUS=$(curl --request POST -F exeFile=@$zipFile $url -w "%{http_code}" -o /tmp/$zipFile)
if [ $HTTP_STATUS != 200 ]; then 
	echo "An error occured while signing $zipFile..."
	return 1
fi
cat /tmp/$zipFile > $zipFile
rm -r $filename
unzip -q $zipFile
rm $zipFile