@echo off
setlocal EnableExtensions EnableDelayedExpansion

echo Stopping all microservices on Windows...
set "RUN_DIR=%~dp0"
for %%I in ("%RUN_DIR%..") do set "BASE_DIR=%%~fI"

call :KillByPort 19088 config-server
call :KillByPort 19089 eureka-server
call :KillByPort 8080 eureka-gateway
call :KillByPort 8081 user-service
call :KillByPort 8082 product-service
call :KillByPort 8083 master-data-service
call :KillByPort 8084 order-service
call :KillByPort 3000 frontend

powershell -NoProfile -ExecutionPolicy Bypass -Command "$base=[Regex]::Escape('%BASE_DIR%');$procs=Get-CimInstance Win32_Process | Where-Object { $_.CommandLine -and $_.CommandLine -match $base -and ( $_.Name -match '^(cmd|java|mvn|node)(\.exe)?$' -or $_.CommandLine -match 'spring-boot:run|npm\s+start' ) };foreach($p in $procs){try{Stop-Process -Id $p.ProcessId -Force -ErrorAction Stop}catch{}}"

echo All known processes stopped.
pause
exit /b 0

:KillByPort
set "PORT=%~1"
set "NAME=%~2"
set "FOUND="
set "LAST_PID="

for /f "tokens=5" %%P in ('netstat -aon ^| findstr /R /C:":%PORT% .*LISTENING"') do (
    if not "%%P"=="!LAST_PID!" (
        set "FOUND=1"
        set "LAST_PID=%%P"
        echo - Stopping !NAME! (PID %%P, port %PORT%)
        taskkill /PID %%P /T /F >nul 2>&1
    )
)

if not defined FOUND (
    echo - !NAME! not listening on port %PORT%
)

exit /b 0
