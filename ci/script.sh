case "$TRAVIS_COMMIT_MESSAGE" in
    *"[noci]"*)
        echo "Commit message has [noci]. CI skipped (reported successful)".
        ;;
    *)
        printenv ANDROID_HOME && ./gradlew clean build dokka :app:check :domain:check :data:check --no-daemon --stacktrace
        ;;
esac