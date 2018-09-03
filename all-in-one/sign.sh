#!/bin/sh

filename=$1
url=$2
output=$3
workDir=$4

cd $workDir
if [ -f $filename ]; then 
    curl --request POST -F exeFile=@$filename $url > /tmp/$filename
    cat /tmp/$filename > $output
    if [ $filename != $output ]; then
        rm $filename
    fi
fi