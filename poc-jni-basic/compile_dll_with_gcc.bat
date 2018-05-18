echo off

goto CheckArch

:CheckArch
reg Query "HKLM\Hardware\Description\System\CentralProcessor\0" | find /i "x86" > NUL && set ARCH=Win32 || set ARCH=x64
rem set ARCH=Win32
IF %ARCH% == x64 (GOTO 64BIT) ELSE (GOTO 32BIT)

:64BIT
echo 64-bit...
set CC=C:/mingw-w64/mingw64/bin/gcc
GOTO END

:32BIT
echo 32-bit...
set CC=C:/mingw/bin/gcc
GOTO END

:END

set JDK=%JAVA_HOME%
rem C:/Program Files/Java/jdk1.8.0_131

"%JDK%/bin/javah" -jni -classpath ./src/main/java -d ./src/main/c/JNativeSample NativeMethods
echo Header file created
%cc% -I"%JDK%/include" -I"%JDK%/include/win32" -c -o ./src/main/c/JNativeSample/SampleJNI.o ./src/main/c/JNativeSample/SampleJNI.cpp

rem rm %ARCH%/Release/*.*
%cc% -shared -Wl,--kill-at -o ./src/main/c/%ARCH%/Release/SampleJNI.dll ./src/main/c/JNativeSample/SampleJNI.o
echo Shared library created

objdump -p ./src/main/c/%ARCH%/Release/SampleJNI.dll | grep DLL

rem call ./copy_dlls_to_java_project.bat

pause