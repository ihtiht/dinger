# Dinger
## What Tinder should have been

Tinder is a very trendy thing; you can like people without revealing anything unless intentions match, which is really helpful nowadays for teenagers and people that are afraid of being judged.

From a technical standpoint however, it is an awful piece of software, at least the Android app [1]:
* It requires you to periodically perform a monkey task (swiping), which is cumbersome and inefficient, and could be easily automated.
* It performs a very aggressive polling to check for changes that may affect your experience (in other words, it continuously asks the server 'did anyone message me?' instead of letting the server notify the device when a message came). This results in unnecessary battery consumption and, when implemented poorly, buggy behavior too.
* It does not support landscape mode.

This repo is here to show that these and other pain points are easily mitigable nowadays if things are done correctly and, while at it, I'll take the chance to demonstrate how well-built modern Android apps are made, and that this is something very feasible to achieve even if your team just consists of a single developer and instead of having the backend guy sitting next to him, you only have Charles Proxy.

## Distribution

At some point, I would love to distribute this app through some channel like Google Play or F-Droid, but at the time of writing there is little reason for anyone to use this app, although I intend that to change soon.
Until that happens, you are encouraged to try out the app by checking out the repo and running `./gradlew assembleRelease` to build the apk (or by getting it yourself from the 'Releases' tab, which you can find above the file explorer in this page).

[1]: Dear Android developers at Tinder, I have nothing against you, your intentions nor your skillsets. I understand there are many factors outside of your control preventing you from being able to deliver as good a product as you would like it to be.
