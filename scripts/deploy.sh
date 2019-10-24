#!/bin/bash

if [ "$1" == "-v" ]; then
  VERSION=$2
else
  echo "unkown version"
  exit 1
fi

echo $SSH_PRIVATE | base64 --decode >> id_rsa
chmod 400 id_rsa
ssh -o StrictHostKeyChecking=no -i id_rsa ci@$DEPLOY_IP "echo '$PROJECT_TAG=$VERSION' > ~/subl/properties/$PROJECT_TAG"
ssh -o StrictHostKeyChecking=no -i id_rsa ci@$DEPLOY_IP 'sh ~/subl/start.sh'