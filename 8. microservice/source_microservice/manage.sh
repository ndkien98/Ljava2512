#!/bin/bash

# Vi tri cua file script
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$DIR"

show_menu() {
    clear
    echo "============================================================"
    echo "  UNIQLO MICROSERVICE + ELK STACK MANAGEMENT TOOL (LINUX)"
    echo "============================================================"
    echo "  1. Start All Services (Khoi dong he thong)"
    echo "  2. Stop All Services (Dung he thong - Giu nguyen Data)"
    echo "  3. Build and Start (Build lai code va khoi dong)"
    echo "  4. Clean Reset and Fresh Start (Xoa sach log/data va chay lai)"
    echo "  5. Check System Status (Kiem tra trang thai)"
    echo "  6. View Container Logs (Xem log cua container)"
    echo "  7. Exit (Thoat)"
    echo "============================================================"
    read -p "Nhap lua chon cua ban [1-7]: " choice
}

create_dirs() {
    echo "Tao cac thu muc du lieu va log..."
    mkdir -p data/mysql data/redis data/elasticsearch data/kibana data/logstash
    mkdir -p log/config-server log/eureka-server log/eureka-gateway
    mkdir -p log/user-service log/product-service log/master-data-service log/order-service
    echo "[OK] Thu muc san sang."
}

case_start() {
    clear
    echo "=== STARTING ALL SERVICES ==="
    echo ""
    if ! command -v docker &> /dev/null; then
        echo "[ERROR] Docker khong duoc tim thay! Vui long cai dat Docker."
        read -n 1 -s -r -p "Nhan phim bat ky de tiep tuc..."
        return
    fi
    create_dirs
    docker compose up -d
    echo ""
    echo "Khoi chay hoan tat! Doi khoang 60 giay de cac dich vu Java boot len."
    read -n 1 -s -r -p "Nhan phim bat ky de tiep tuc..."
}

case_stop() {
    clear
    echo "=== STOPPING ALL SERVICES ==="
    echo ""
    docker compose down
    echo "[OK] Da dung va giai phong tat ca container."
    read -n 1 -s -r -p "Nhan phim bat ky de tiep tuc..."
}

case_build() {
    clear
    echo "=== REBUILDING AND STARTING ==="
    echo ""
    docker compose build --no-cache
    if [ $? -ne 0 ]; then
        echo "[ERROR] Build that bai!"
        read -n 1 -s -r -p "Nhan phim bat ky de tiep tuc..."
        return
    fi
    docker compose up -d
    read -n 1 -s -r -p "Nhan phim bat ky de tiep tuc..."
}

case_reset() {
    clear
    echo "CANH BAO: Lua chon nay se dung tat ca dich vu, xoa sach du lieu"
    echo "          database (MySQL, Redis), log va Elasticsearch indices cu."
    echo ""
    read -p "Ban co chac chan muon RESET? (y/n): " confirm
    if [ "$confirm" != "y" ] && [ "$confirm" != "Y" ]; then
        return
    fi
    
    docker compose down
    docker rm -f mysql-db redis-db elasticsearch logstash kibana config-server eureka-server eureka-gateway user-service product-service master-data-service order-service frontend 2>/dev/null
    
    echo "Xoa cac thu muc cu..."
    rm -rf data log
    create_dirs
    
    docker compose up -d
    echo "[OK] Dang khoi chay he thong sach."
    read -n 1 -s -r -p "Nhan phim bat ky de tiep tuc..."
}

case_status() {
    clear
    echo "=== SYSTEM CONTAINER STATUS ==="
    echo ""
    docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
    echo ""
    read -n 1 -s -r -p "Nhan phim bat ky de tiep tuc..."
}

case_logs() {
    clear
    echo "=== VIEW CONTAINER LOGS ==="
    read -p "Ten container (vi du: user-service, logstash): " container_name
    if [ -z "$container_name" ]; then
        return
    fi
    clear
    echo "=== LOGS FOR $container_name (Nhan Ctrl+C de thoat) ==="
    docker logs -f "$container_name"
}

while true; do
    show_menu
    case $choice in
        1) case_start ;;
        2) case_stop ;;
        3) case_build ;;
        4) case_reset ;;
        5) case_status ;;
        6) case_logs ;;
        7) exit 0 ;;
        *) echo "Lua chon khong hop le!" && sleep 1 ;;
    esac
done
