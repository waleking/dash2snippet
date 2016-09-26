#!/bin/bash
# run bash2snippet from bash
set -e
lib="lib"
src="src"
classes="bin"

LibPath=${lib}/sqlite-jdbc-3.8.11.2.jar
javac -classpath ${classes}:$LibPath -sourcepath ${src} -d ${classes} ${src}/dash2snippet/Portal.java

java -classpath ${classes}:$LibPath dash2snippet/Portal
