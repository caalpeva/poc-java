@echo off
copy .\src\main\c\Win32\Release\*.dll .\src\main\resources\jni\win\x86_32\
copy .\src\main\c\x64\Release\*.dll .\src\main\resources\jni\win\x86_64\
rem if %errorlevel% neq 0

pause