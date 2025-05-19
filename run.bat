@echo off
if "%~1"=="" (
  echo Usage: run.bat ^<images_folder^>
  exit /b 1
)
set "FOLDER=%~1"
if not exist "%FOLDER%" (
  echo Folder "%FOLDER%" not found.
  exit /b 1
)
mkdir bin 2>nul
javac -d bin Main.java
if errorlevel 1 goto :eof
java -cp bin org.example.Main "%FOLDER%"