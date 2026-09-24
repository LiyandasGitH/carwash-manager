#!/usr/bin/env bash
set -e

# check if your .env file exists, if not warn
# shellcheck disable=SC1073
if [ -f .env ]; then
  echo "Loading configuration from .env"
  # export all vars defined in .env
  set -a
  source .env
  set +a
else
  echo "Warning: .env file not found. Fallinh back to default settings"
  export CARWASH_DB_HOST=${CARWASH_DB_HOST:-localhost}
    export CARWASH_DB_PORT=${CARWASH_DB_PORT:-3306}
    export CARWASH_DB_NAME=${CARWASH_DB_NAME:-carwash}
    export CARWASH_DB_USER=${CARWASH_DB_USER:-carwash_app}
    export CARWASH_DB_PASSWORD=${CARWASH_DB_PASSWORD:-CHANGE_THIS_PASSWORD}
fi

# build and package if jar is not there
if [ ! -f target/carwash-manager.jar ]; then
  echo "Jar, no jar, bro. Packing with mvn"
  mvn clean package -DskipTests
fi

# launch app w/ environment
echo "Starting Car Wash Manager"
java -jar target/carwash-manager.jar