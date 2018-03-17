#!/bin/bash
docker run -p 9000:9000 --name minio-dev -d \
  -e "MINIO_ACCESS_KEY=KTSK9ZLO9GVRXLY2JTCI" \
  -e "MINIO_SECRET_KEY=00Vz3MYI0MJh4j+fNZDwPnQBbuPdPPwVW6Ja8c+s" \
  -v "$HOME/.minio/data:/data" \
  -v "$HOME/.minio/config:/root/.minio" \
  minio/minio server /data