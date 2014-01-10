@echo off
setlocal EnableDelayedExpansion
echo -------------------------------------------------------------------------------
echo                     FRC Team 4931 IP Configuration Utility
echo -------------------------------------------------------------------------------

:START
return a given range for each axis:
echo [1] Set IP address to interface with 2014 bridge.
echo [2] Set IP address to interface with 2013 bridge.
echo [3] Reset IP settings to automatic.
SET /P choice=?
cls
echo -------------------------------------------------------------------------------
echo                     FRC Team 4931 IP Configuration Utility
echo -------------------------------------------------------------------------------
IF %choice%== 1 goto NEW
IF %choice%== 2 goto OLD
IF %choice%== 3 goto REVERT

:NEW
echo Please enter assigned IP address.
set /p address=10.49.31.
echo Setting IP Information...
netsh interface ip set address "Wireless" static 10.49.31.%address% 255.0.0.0
goto WAIT

:OLD
echo Please enter assigned IP address.
set /p address=10.99.31.
echo Setting IP Information...
netsh interface ip set address "Wireless" static 10.49.31.%address% 255.0.0.0
goto WAIT

:REVERT
echo Resetting IP configuration to automatic...
netsh interface ip set address "Wireless" DHCP
ipconfig /renew
goto WAIT

:WAIT
GOTO LOOP

:LOOP
for /f %%i in ('ipconfig^|findstr IPv4') do set config=%%i
IF [!config!]==[] GOTO LOOP

:END
cls
echo -------------------------------------------------------------------------------
echo                     FRC Team 4931 IP Configuration Utility
echo -------------------------------------------------------------------------------
echo Configuration successful.
netsh interface ip show address "Wireless"
pause
