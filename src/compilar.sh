#!/bin/bash

find -name "*.java" > sources.txt
find . -type f -name "*.class" -exec rm -f {} \;
javac -cp .:../lib/asm-all-3.3.jar:../lib/jdom.jar:../lib/junit-4.8.2.jar -encoding ISO-8859-1 @sources.txt
rm sources.txt
