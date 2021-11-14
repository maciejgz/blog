# discovery-service
cd comment-service
docker build -t blog-comment-service:latest .

cd ..
cd configuration-service
docker build -t blog-configuration-service:latest .

cd ..
cd discovery-service
docker build -t blog-discovery-service:latest .

cd ..
cd gateway-service
docker build -t blog-gateway-service:latest .

cd ..
cd post-service
docker build -t blog-post-service:latest .

cd ..
cd simulation-service
docker build -t blog-simulation-service:latest .

cd ..
cd user-service
docker build -t blog-user-service:latest .

