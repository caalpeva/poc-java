echo off

:: Se muestra la invocación del archivo
rem echo %0 %*

set cmd="java" -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -jar ${project.build.finalName}.${project.packaging} %*
rem echo %cmd%
%cmd%