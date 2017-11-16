#!/usr/bin/env bash
set -e

usage() { echo "Usage: $0 [-v <version>]" 1>&2; exit 1; }

while getopts ":d:o:" it; do
    case "${it}" in
        v)
            VERSION=${OPTARG}
            ;;
        *)
            usage
            ;;
    esac
done

git checkout gh-pages

echo "{
  \"title\": \"Update available\",
  \"body\": \"There is an update available for the app. Older versions are now unsupported.\",
  \"positive_button\": \"Download\",
  \"negative_button\": \"Cancel\",
  \"download_url\": \"https://github.com/stoyicker/dinger/releases/download/${VERSION}/app-release.apk\",
  \"version\": \"${VERSION}\"
}" > version.json
