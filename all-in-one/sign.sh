#!/bin/sh

filename=$1
url=$2
workDir=$3

cd $workDir
if [ -f $filename ]; then 
    curl --request POST -F exeFile=@$filename $url > /tmp/$filename
    cat /tmp/$filename > $filename
fi