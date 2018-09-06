#!/bin/sh

filename=$1
url=$2
output=$3
workDir=$4

cd $workDir
echo "cd into $workDir"
if [ -f $filename ]; then
    echo "Signing $filename..."
    curl --request POST -F exeFile=@$filename $url > /tmp/$filename
    cat /tmp/$filename > $output
    if [ $filename != $output ]; then
        rm $filename
        echo "rm $filename"
    fi
else
   echo "$filename not found in $workDir. Codesign skipped."
fi