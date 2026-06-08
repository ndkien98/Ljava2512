#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
BASE_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"
LOG_DIR="$BASE_DIR/log"
PID_DIR="$SCRIPT_DIR/pids"

mkdir -p "$LOG_DIR" "$PID_DIR"

services=(
  config-server
  eureka-server
  eureka-gateway
  user-service
  product-service
  master-data-service
  order-service
)

ports=(19088 19089 8080 8081 8082 8083 8084)

echo "Starting all microservices on macOS..."

for i in "${!services[@]}"; do
  service="${services[$i]}"
  port="${ports[$i]}"
  service_dir="$BASE_DIR/$service"
  log_file="$LOG_DIR/${service}.log"
  pid_file="$PID_DIR/${service}.pid"

  echo "Starting $service on port $port..."
  (
    cd "$service_dir"
    MAVEN_OPTS="-Xms64m -Xmx256m" nohup mvn spring-boot:run >"$log_file" 2>&1 &
    echo $! >"$pid_file"
  )
  sleep 3
done

echo "Starting frontend on port 3000..."
(
  cd "$BASE_DIR/frontend"
  nohup npm start >"$LOG_DIR/frontend.log" 2>&1 &
  echo $! >"$PID_DIR/frontend.pid"
)

echo "All services launched."
echo "Logs: $LOG_DIR"
echo "PIDs: $PID_DIR"
