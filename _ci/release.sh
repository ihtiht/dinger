#!/bin/bash
set -e

json_escape () {
    printf '%s' $1 | python -c 'import json,sys; print(json.dumps(sys.stdin.read()))'
}

uploadReleaseToGitHub() {
    git fetch --tags
    LAST_TAG=$(git describe --tags --abbrev=0)
    THIS_RELEASE=$(git rev-parse --short HEAD)
    local IFS=$'\n'
    RELEASE_NOTES_ARRAY=($(git log --format=%B ${LAST_TAG}..${THIS_RELEASE} | tr -d '\r'))
    { for i in "${RELEASE_NOTES_ARRAY[@]}"
    do
        RELEASE_NOTES="$RELEASE_NOTES\\n$i"
    done
    }

    BODY="{
        \"tag_name\": \"$ARTIFACT_VERSION\",
        \"target_commitish\": \"master\",
        \"name\": \"$ARTIFACT_VERSION\",
        \"body\": \" \"
    }"

    # Create the release in GitHub and extract its id from the response
    RESPONSE_BODY=$(curl -s \
            -u ${REPO_USER}:${GITHUB_TOKEN} \
            --header "Accept: application/vnd.github.v3+json" \
            --header "Content-Type: application/json; charset=utf-8" \
            --request POST \
            --data "${BODY}" \
            https://api.github.com/repos/"${TRAVIS_REPO_SLUG}"/releases)

    # Extract the upload_url value
    UPLOAD_URL=$(echo ${RESPONSE_BODY} | jq -r .upload_url)
    # And the id for later use
    RELEASE_ID=$(echo ${RESPONSE_BODY} | jq -r .id)

    cp app/build/outputs/apk/release/app-release.apk .

    # Attach the artifact
    UPLOAD_URL=$(echo ${UPLOAD_URL} | sed "s/{?name,label}/?name=dinger-${ARTIFACT_VERSION}.apk/")
    echo ${UPLOAD_URL}
    RESPONSE_BODY=$(curl -s \
            -u ${REPO_USER}:${GITHUB_TOKEN} \
            --header "Accept: application/vnd.github.v3+json" \
            --header "Content-Type: application/zip" \
            --data-binary "@app-release.apk" \
            --request POST \
            ${UPLOAD_URL})

    # Get the APK download link
    RESPOSE_BODY=$(curl -s \
            -u ${REPO_USER}:${GITHUB_TOKEN} \
            --header "Accept: application/vnd.github.v3+json" \
            --request GET \
            https://api.github.com/repos/"${TRAVIS_REPO_SLUG}"/releases/${RELEASE_ID})
    APK_DOWNLOAD_URL=$(echo ${RESPONSE_BODY} | jq -r .browser_download_url)

    # Attach the qr code
    UPLOAD_URL=$(echo ${UPLOAD_URL} | sed "s/dinger-${ARTIFACT_VERSION}.apk/qrcode-${ARTIFACT_VERSION}.png/")
    RESPONSE_BODY=$(curl -s \
            -u ${REPO_USER}:${GITHUB_TOKEN} \
            --header "Accept: application/vnd.github.v3+json" \
            --header "Content-Type: image/png" \
            --data-binary @${ARTIFACT_VERSION}.png $(bash _ci/generate_qr.sh -d ${APK_DOWNLOAD_URL} -o ${ARTIFACT_VERSION}.png) \
            --request POST \
            ${UPLOAD_URL})
    QR_DOWNLOAD_URL=$(echo ${RESPONSE_BODY} | jq -r .browser_download_url)

    RELEASE_BODY="**APK DOWNLOAD**: Scan the QR code below or click [here](${APK_DOWNLOAD_URL} \\\"Direct APK download\\\").\\n\\n![QR code](${QR_DOWNLOAD_URL}?raw=true)\\n\\n**CHANGELOG**:\\n$RELEASE_NOTES"

    BODY="{
        \"body\": \"$(json_escape ${RELEASE_BODY})\"
    }"

    echo ${BODY} > body-file

    curl -s \
        -u ${REPO_USER}:${GITHUB_TOKEN} \
        --header "Accept: application/vnd.github.v3+json" \
        --header "Content-Type: application/json; charset=utf-8" \
        --request PATCH \
        --data @body-file \
        https://api.github.com/repos/"${TRAVIS_REPO_SLUG}"/releases/${RELEASE_ID}

    ./_ci/update_version_json.sh -v ${ARTIFACT_VERSION}

    echo "Release complete."
}

case ${TRAVIS_BRANCH} in
    *)
        uploadReleaseToGitHub
        ;;
    *)
        echo "Branch is ${TRAVIS_BRANCH}, which is not releasable. Skipping release."
        exit 0
        ;;
esac
