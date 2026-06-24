# Stage 1: Build stage
FROM node:18-alpine AS builder
WORKDIR /app

# Install dependencies (utilizing cache)
COPY package.json package-lock.json* ./
RUN npm install

# Copy source code and build
COPY . .
RUN npm run build

# Stage 2: Runtime stage served via Nginx
FROM nginx:1.25-alpine
WORKDIR /usr/share/nginx/html

# Remove default nginx static assets
RUN rm -rf ./*

# Copy static assets from builder stage
COPY --from=builder /app/build .

# Copy custom nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose Nginx port
EXPOSE 3000

ENTRYPOINT ["nginx", "-g", "daemon off;"]
