@echo off
setlocal EnableExtensions EnableDelayedExpansion

echo Starting all microservices on Windows...
set "MAVEN_OPTS=-Xms64m -Xmx256m"
set "RUN_DIR=%~dp0"
for %%I in ("%RUN_DIR%..") do set "BASE_DIR=%%~fI"

for %%s in (config-server eureka-server eureka-gateway user-service product-service master-data-service order-service) do (
    echo Starting %%s...
    start "%%s" /D "%BASE_DIR%\%%s" cmd /k "mvn spring-boot:run"
    timeout /t 8 /nobreak >nul
)

echo Starting frontend...
start "frontend" /D "%BASE_DIR%\frontend" cmd /k "npm start"

echo All services launched.
pause
