@echo off
REM Dừng tất cả các service của hệ thống Uniqlo Microservice

REM Dừng Frontend (nếu đang chạy bằng npm)
for /f "tokens=2 delims==;" %%i in ('wmic process where "CommandLine like '%%npm%%start%%'" get ProcessId /format:value') do (
    if not "%%i"=="" taskkill /PID %%i /F
)

REM Dừng các service Java Spring Boot (theo tên thư mục dự án)
set SERVICES="config-server" "eureka-server" "eureka-gateway" "user-service" "product-service" "master-data-service" "order-service"
for %%S in (%SERVICES%) do (
    for /f "tokens=2 delims==;" %%i in ('wmic process where "CommandLine like '%%spring-boot:run%%%%S%%'" get ProcessId /format:value') do (
        if not "%%i"=="" taskkill /PID %%i /F
    )
)

REM Thông báo hoàn thành
echo Tat tat ca cac service thanh cong.
pause
