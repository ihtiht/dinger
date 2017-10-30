#!/bin/bash
set -e

BRANCH_NAME=${TRAVIS_BRANCH}
ARTIFACT_VERSION=undefined

uploadReleaseToGitHub() {
    echo 'FLAG 0'
    git fetch --tags
    LAST_TAG=$(git describe --tags --abbrev=0)
    THIS_RELEASE=$(git rev-parse --short ${BRANCH_NAME})
    local IFS=$'\n'
    echo 'FLAG 1'
    RELEASE_NOTES_ARRAY=($(git log --format=%B ${LAST_TAG}..${THIS_RELEASE} | tr -d '\r'))
    echo 'FLAG 2'
    { for i in "${RELEASE_NOTES_ARRAY[@]}"
    do
        RELEASE_NOTES="$RELEASE_NOTES\\n$i"
    done
    }
    echo 'FLAG 3'

    BODY="{
        \"tag_name\": \"$ARTIFACT_VERSION\",
        \"target_commitish\": \"$BRANCH_NAME\",
        \"name\": \"$ARTIFACT_VERSION\",
        \"body\": \" \"
    }"
    echo 'FLAG 4'

    # Create the release in GitHub and extract its id from the response
    RESPONSE_BODY=$(curl \
            -u ${REPO_USER}:${GITHUB_TOKEN} \
            --header "Accept: application/vnd.github.v3+json" \
            --header "Content-Type: application/json; charset=utf-8" \
            --request POST \
            --data "${BODY}" \
            https://api.github.com/repos/"${TRAVIS_REPO_SLUG}"/releases)

    echo 'FLAG 5'

    # Extract the upload_url value
    UPLOAD_URL=$(echo ${RESPONSE_BODY} | jq -r .upload_url)
    # And the id for later use
    RELEASE_ID=$(echo ${RESPONSE_BODY} | jq -r .id)

    # Build the apk
    ./gradlew --no-daemon assembleRelease
    # Copy it out of its cave
    cp app/build/outputs/apk/app-release.apk .

    # Attach the artifact
    UPLOAD_URL=$(echo ${UPLOAD_URL} | sed "s/{?name,label}/?name=app-release-${ARTIFACT_VERSION}.apk/")
    echo ${UPLOAD_URL}
    RESPONSE_BODY=$(curl \
            -u ${REPO_USER}:${GITHUB_TOKEN} \
            --header "Accept: application/vnd.github.v3+json" \
            --header "Content-Type: application/zip" \
            --data-binary "@app-release.apk" \
            --request POST \
            ${UPLOAD_URL})

    # Get the APK download link
    RESPOSE_BODY=$(curl \
            -u ${REPO_USER}:${GITHUB_TOKEN} \
            --header "Accept: application/vnd.github.v3+json" \
            --request GET \
            https://api.github.com/repos/"${TRAVIS_REPO_SLUG}"/releases/${RELEASE_ID})
    APK_DOWNLOAD_URL=$(echo ${RESPONSE_BODY} | jq -r .browser_download_url)

    # Attach the qr code
    UPLOAD_URL=$(echo ${UPLOAD_URL} | sed "s/app-release-${ARTIFACT_VERSION}.apk/qrcode-${ARTIFACT_VERSION}.png/")
    RESPONSE_BODY=$(curl \
            -u ${REPO_USER}:${GITHUB_TOKEN} \
            --header "Accept: application/vnd.github.v3+json" \
            --header "Content-Type: image/png" \
            --data-binary @${ARTIFACT_VERSION}.png $(bash ci/generate_qr.sh -d ${APK_DOWNLOAD_URL} -o ${ARTIFACT_VERSION}.png) \
            --request POST \
            ${UPLOAD_URL})
    QR_DOWNLOAD_URL=$(echo ${RESPONSE_BODY} | jq -r .browser_download_url)

    RELEASE_BODY="**APK DOWNLOAD**: Scan the QR code below or click [here](${APK_DOWNLOAD_URL} \\\"Direct APK download\\\").\\n\\n![QR code](${QR_DOWNLOAD_URL}?raw=true)\\n\\n**CHANGELOG**:\\n$RELEASE_NOTES"

    BODY="{
        \"body\": \"${RELEASE_BODY}\"
    }"

    curl \
        -u ${REPO_USER}:${GITHUB_TOKEN} \
        --header "Accept: application/vnd.github.v3+json" \
        --header "Content-Type: application/json; charset=utf-8" \
        --request PATCH \
        --data "${BODY}" \
        https://api.github.com/repos/"${TRAVIS_REPO_SLUG}"/releases/${RELEASE_ID}

    echo "Release complete."
}

case ${BRANCH_NAME} in
    "master")
        ARTIFACT_VERSION=$(git rev-list --count HEAD)
        uploadReleaseToGitHub
        ;;
    *)
        echo "Branch is ${BRANCH_NAME}, which is not releasable. Skipping release."
        exit 0
        ;;
esac
