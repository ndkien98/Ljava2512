@echo off
chcp 65001 >nul
title Uniqlo Microservice & ELK Manager
color 0B
cd /d "%~dp0"

:MENU
cls
echo ============================================================
echo   UNIQLO MICROSERVICE + ELK STACK MANAGEMENT TOOL
echo ============================================================
echo   1. Start All Services (Khoi dong he thong)
echo   2. Stop All Services (Dung he thong - Giu nguyen Data)
echo   3. Build and Start (Build lai code va khoi dong)
echo   4. Clean Reset and Fresh Start (Xoa sach log/data va chay lai)
echo   5. Check System Status (Kiem tra trang thai)
echo   6. View Container Logs (Xem log cua container)
echo   7. Exit (Thoat)
echo ============================================================
set /p choice="Nhap lua chon cua ban [1-7]: "

if "%choice%"=="1" goto START_ALL
if "%choice%"=="2" goto STOP_ALL
if "%choice%"=="3" goto BUILD_START
if "%choice%"=="4" goto CLEAN_RESET
if "%choice%"=="5" goto STATUS
if "%choice%"=="6" goto VIEW_LOGS
if "%choice%"=="7" exit
goto MENU

:START_ALL
cls
echo === STARTING ALL SERVICES ===
echo.
echo [1/3] Kiem tra Docker...
docker info >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Docker khong chay! Vui long bat Docker Desktop.
    pause
    goto MENU
)

echo.
echo [2/3] Tao cac thu muc du lieu va log...
if not exist "data\mysql"             mkdir "data\mysql"
if not exist "data\redis"             mkdir "data\redis"
if not exist "data\elasticsearch"     mkdir "data\elasticsearch"
if not exist "data\kibana"            mkdir "data\kibana"
if not exist "data\logstash"          mkdir "data\logstash"
if not exist "log\config-server"      mkdir "log\config-server"
if not exist "log\eureka-server"      mkdir "log\eureka-server"
if not exist "log\eureka-gateway"     mkdir "log\eureka-gateway"
if not exist "log\user-service"       mkdir "log\user-service"
if not exist "log\product-service"    mkdir "log\product-service"
if not exist "log\master-data-service" mkdir "log\master-data-service"
if not exist "log\order-service"      mkdir "log\order-service"
echo [OK] Thu muc san sang.

echo.
echo [3/3] Dang khoi chay Docker Compose...
docker-compose up -d
echo.
echo Khoi chay hoan tat! Doi khoang 60 giay de cac dich vu Java boot len.
echo Trinh duyet:
echo   - Frontend     : http://localhost:3000
echo   - API Gateway  : http://localhost:8080
echo   - Eureka Dash  : http://localhost:19089
echo   - Kibana View  : http://localhost:5601
echo.
pause
goto MENU

:STOP_ALL
cls
echo === STOPPING ALL SERVICES ===
echo.
docker-compose down
echo [OK] Da dung va giai phong tat ca container.
pause
goto MENU

:BUILD_START
cls
echo === REBUILDING AND STARTING ===
echo.
echo Bat dau build lai toan bo Spring Boot services...
docker-compose build --no-cache
if %errorlevel% neq 0 (
    echo [ERROR] Build that bai!
    pause
    goto MENU
)
echo Build thanh cong! Tien hanh khoi chay...
docker-compose up -d
pause
goto MENU

:CLEAN_RESET
cls
echo CANH BAO: Lua chon nay se dung tat ca dich vu, xoa sach du lieu 
echo          database (MySQL, Redis), log va Elasticsearch indices cu.
echo.
set /p confirm="Ban co chac chan muon RESET? (y/n): "
if /i "%confirm%" neq "y" goto MENU

echo.
echo Dung cac container...
docker-compose down
docker rm -f mysql-db redis-db elasticsearch logstash kibana config-server eureka-server eureka-gateway user-service product-service master-data-service order-service frontend 2>nul
docker volume rm elk_elasticsearch_data 2>nul

echo.
echo Xoa cac thu muc cu va tao lai thu muc sach...
rd /s /q data 2>nul
rd /s /q log 2>nul

mkdir data\mysql data\redis data\elasticsearch data\kibana data\logstash
mkdir log\config-server log\eureka-server log\eureka-gateway
mkdir log\user-service log\product-service log\master-data-service log\order-service
echo [OK] Don dep sach se data cu.

echo.
echo Khoi chay fresh tu dau...
docker-compose up -d
echo [OK] Dang khoi chay he thong sach.
pause
goto MENU

:STATUS
cls
echo === SYSTEM CONTAINER STATUS ===
echo.
docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
echo.
pause
goto MENU

:VIEW_LOGS
cls
echo === VIEW CONTAINER LOGS ===
echo Nhap ten container (vi du: user-service, logstash, elasticsearch):
set /p container_name="Ten container: "
if "%container_name%"=="" goto MENU
cls
echo === LOGS FOR %container_name% (Nhan Ctrl+C de thoat xem log) ===
docker logs -f %container_name%
pause
goto MENU
