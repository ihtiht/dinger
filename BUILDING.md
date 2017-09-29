# Building

If you are going to build the app, you'll need to set up Firebase. As 
such:
* Add your private key under `app/` as `firebase-crashreporting-private-key.json`.
* Add your `google-services.json` files for the debug and release variants of your console project under `app/debug` and `app/release` respectively.

Then just use the `assemble` Gradle task to build both debug and release, or the specific task for one of them.
