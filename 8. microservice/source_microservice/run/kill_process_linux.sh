#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
BASE_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"
PID_DIR="$SCRIPT_DIR/pids"

services=(
  config-server
  eureka-server
  eureka-gateway
  user-service
  product-service
  master-data-service
  order-service
  frontend
)

ports=(19088 19089 8080 8081 8082 8083 8084 3000)

kill_by_pid_file() {
  local service="$1"
  local pid_file="$PID_DIR/${service}.pid"

  if [[ -f "$pid_file" ]]; then
    local pid
    pid="$(cat "$pid_file")"
    if [[ -n "$pid" ]] && kill -0 "$pid" 2>/dev/null; then
      echo "Stopping $service via PID $pid"
      kill "$pid" 2>/dev/null || true
      sleep 1
      kill -9 "$pid" 2>/dev/null || true
    fi
    rm -f "$pid_file"
  fi
}

kill_by_port() {
  local service="$1"
  local port="$2"
  local pids
  pids="$(lsof -ti tcp:"$port" 2>/dev/null || true)"

  if [[ -n "$pids" ]]; then
    echo "Stopping $service on port $port (PID: $pids)"
    kill $pids 2>/dev/null || true
    sleep 1
    kill -9 $pids 2>/dev/null || true
  else
    echo "$service is not listening on port $port"
  fi
}

echo "Stopping all microservices on Linux..."

for service in "${services[@]}"; do
  kill_by_pid_file "$service"
done

for i in "${!services[@]}"; do
  kill_by_port "${services[$i]}" "${ports[$i]}"
done

echo "Done."
