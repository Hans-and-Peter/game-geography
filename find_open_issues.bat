for /F "usebackq delims=?" %%a in (`dir /s /b *.java *.xml *.txt *.properties Dockerfile`) do @findstr /I /N TODO "%%a"
pause
