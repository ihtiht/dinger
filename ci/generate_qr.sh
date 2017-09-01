#!/usr/bin/env bash
set -e

usage() { echo "Usage: $0 [-d <input_data>]" 1>&2; exit 1; }

while getopts ":d:o:" it; do
    case "${it}" in
        d)
            DATA=${OPTARG}
            ;;
        *)
            usage
            ;;
    esac
done

curl --request GET \
        "https://api.qrserver.com/v1/create-qr-code/?data=${DATA}&bgcolor=fff&margin=10"
