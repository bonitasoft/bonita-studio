#!/bin/sh
set -e

filename=$1
url=$2
output=$3
workdir="$4"

echo "URL=$url"
echo "Current folder = $PWD"
cd $workdir
echo "cd into $workdir"
if [ -f $filename ]; then
    echo "Signing $filename..."
    HTTP_STATUS=$(curl --request POST -F exeFile=@$filename $url -w "%{http_code}" -o /tmp/$filename)
    if [ $HTTP_STATUS != 200 ]; then 
    	echo "An error occured while signing $filename..."
    	return 1
    fi
    cat /tmp/$filename > $output
    if [ $filename != $output ]; then
        rm $filename
        echo "rm $filename"
    fi
else
   echo "$filename not found in $workdir. Codesign skipped."
fi