REM Mr. S.P. Sithungu
REM Computer Science 2A 2022
REM This batch file is setup to be more robust than previous versions

REM Turn echo off and clear the screen
@echo off
cls

REM Good batch file coding practice
setlocal enabledelayedexpansion

REM Paths for Java
echo Setup JDK bin path before this script
set JAVA_HOME=C:\jdk-17.0.2
set PATH=%JAVA_HOME%\bin;%PATH%

REM Move to correct folder
echo Build script set to run in Project folder like eclipse
cd ..

REM Variables for batch
set ERRMSG=
set PRAC_BIN=.\bin
set PRAC_DOCS=.\docs
set PRAC_LIB=.\lib\*
set PRAC_SRC=.\src
  
REM Clean all class files from bin folder
:CLEAN
echo ~~~ Cleaning project ~~~
DEL /S %PRAC_BIN%\*.class
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Error cleaning project"
    GOTO ERROR
)

:VERSION
echo ~~~ Checking Version ~~~
javac -version
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Error checking version"
    GOTO ERROR
)
java -version
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Error checking version"
    GOTO ERROR
)

REM Compile project by compiling just Main. Main will reference required classes
:COMPILE
echo ~~~ Compiling project ~~~
javac -sourcepath %PRAC_SRC% -cp "%PRAC_BIN%;%PRAC_LIB%" -d %PRAC_BIN% %PRAC_SRC%\Main.java
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Error compiling project"
    GOTO ERROR
)

REM Generate JavaDoc for project for only acsse subpackage.
:JAVADOC
echo ~~~ Generate JavaDoc for project ~~~
javadoc %JAVAFX_ARGS% -sourcepath %PRAC_SRC% -classpath %PRAC_BIN%;%PRAC_LIB% -use -version -author -d %PRAC_DOCS%\%PRAC_JDOC% -subpackages csc2a
IF /I "%ERRORLEVEL%" NEQ "0" (
    echo ~~! Error generating JavaDoc for project !~~
)

REM Run project by running Main
:RUN
echo ~~~ Running project ~~~
java -cp %PRAC_BIN%;%PRAC_LIB% Main
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG="Error running project"
    GOTO ERROR
)
GOTO END

REM Something went wrong, display error
:ERROR
echo Fatal error with project
echo %ERRMSG%

REM Move back to docs folder and wait
:END
echo ~~~ End ~~~
cd %PRAC_DOCS%
pause