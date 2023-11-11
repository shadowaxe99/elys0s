#!/bin/bash

# ElysiumOS Deployment Script

# Stop execution on any error
set -e

# Define the path to the Dockerfile and Kubernetes deployment configuration
DOCKERFILE_PATH="./Dockerfile"
KUBERNETES_DEPLOYMENT_PATH="./kubernetes/deployment.yaml"

# Build the Docker image for ElysiumOS
echo "Building ElysiumOS Docker image..."
docker build -t elysiumos:latest -f $DOCKERFILE_PATH .

# Push the Docker image to a registry (replace with actual registry URL)
# echo "Pushing image to Docker registry..."
# docker tag elysiumos:latest your-registry-url/elysiumos:latest
# docker push your-registry-url/elysiumos:latest

# Deploy to Kubernetes cluster
echo "Deploying ElysiumOS to Kubernetes cluster..."
kubectl apply -f $KUBERNETES_DEPLOYMENT_PATH

echo "ElysiumOS deployment complete."