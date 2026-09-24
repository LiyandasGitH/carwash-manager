#!/usr/bin/env bash
set -a
[ -f .env ] && source .env
set +a

mvn test "$@"