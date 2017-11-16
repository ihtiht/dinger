#!/usr/bin/env bash
set -e

usage() { echo "Usage: $0 [-v <version>]" 1>&2; exit 1; }

while getopts ":v" it; do
    case "${it}" in
        v)
            VERSION=${OPTARG}
            ;;
        *)
            usage
            ;;
    esac
done

git config user.name "Jorge Antonio Diaz-Benito Soriano"
git config user.email "jorge.diazbenitosoriano@gmail.com"

chmod 600 travis_rsa
eval `ssh-agent -s`
ssh-add travis_rsa

ssh-keyscan -t rsa github.com >> ~/.ssh/known_hosts
git config --replace-all remote.origin.fetch +refs/heads/*:refs/remotes/origin/*
git fetch
git checkout gh-pages

echo "{
  \"title\": \"Update available\",
  \"body\": \"There is an update available for the app. Older versions are now unsupported.\",
  \"positive_button\": \"Download\",
  \"negative_button\": \"Cancel\",
  \"download_url\": \"https://github.com/stoyicker/dinger/releases/download/${VERSION}/app-release.apk\",
  \"version\": \"${VERSION}\"
}" > version.json

git commit -am "Update version check file: ${VERSION}"
git push git@github.com:stoyicker/dinger.git gh-pages
