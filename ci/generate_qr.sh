#!/usr/bin/env bash
set -e

usage() { echo "Usage: $0 [-d <input_data>] [-o <output_file>]" 1>&2; exit 1; }

while getopts ":d:o:" it; do
    case "${it}" in
        d)
            DATA=${OPTARG}
            ;;
        o)
            OUTPUT_FILE=${OPTARG}
            ;;
        *)
            usage
            ;;
    esac
done

curl --request GET \
        -o ${OUTPUT_FILE} \
        "https://api.qrserver.com/v1/create-qr-code/?data=${DATA}&bgcolor=fff&margin=10"
