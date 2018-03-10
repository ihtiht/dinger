# Dinger [![Build Status](https://travis-ci.org/stoyicker/dinger.svg?branch=master)](https://travis-ci.org/stoyicker/dinger "stoyicker/dinger - Travis CI")
## Maintenance notice
This project is in maintenance-mode only. I won't be adding any new features, although I will continue to maintain auto-swiping for the foreseeable future, and I'm open to anyone else who wants to add features and contribute.

## What Tinder should have been

Tinder is a very trendy thing. It's like clubbing, but without the fun :D

From a technical standpoint however, the Android app has some... 'shortcomings':
* It requires you to periodically perform a monkey task (swiping), which is cumbersome and inefficient, and could be easily automated.
* It performs a very aggressive polling to check for changes that may affect your experience (in other words, it continuously asks the server 'did anyone message me?' instead of letting the server notify the device when a message came). This results in unnecessary battery and data consumption plus, when implemented poorly, buggy behavior too.
* It does not support landscape mode.
* It requires Internet access to even enter the app.
* It does not support multi-window mode.

This app exists to show that these and other pain points are easily addressed nowadays if things are done correctly. And for my and every other Tinder user's joy too, of course :)

## Distribution

This app does not meet the terms to make it to F-Droid since it uses both Firebase and Google Play Services, and I don't want to risk a strike on my Google Play account, so the default way to install it is by going to the [website](https://stoyicker.github.io/dinger/#download "Dinger APK download"). Whenever you open the app, you will get notified if newer versions are available.

## Feature requests & bug reports

Something not working as expected? Missing something you'd like to be able to do? [Open an issue!](https://github.com/stoyicker/dinger/issues/new "New issue - stoyicker/dinger")

## Architectural overview

[Click here](_assets/overview.pdf "Architecture overview").

## API overview

[Click here instead](https://app.swaggerhub.com/apis/stoyicker/app.tinder-dinger/ "Tinger by Dinger (unofficial) on SwaggerHub"). Source [here](https://github.com/stoyicker/dinger-swagger "stoyicker/dinger-swagger")

## Building

See [BUILDING.md](BUILDING.md "BUILDING.md").

## Contributing 

See [CONTRIBUTING.md](CONTRIBUTING.md "CONTRIBUTING.md").
