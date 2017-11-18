# Dinger [![Build Status](https://travis-ci.org/stoyicker/dinger.svg?branch=master)](https://travis-ci.org/stoyicker/dinger)
## What Tinder should have been

__No clue what's going on? [Start here](_assets/overview.pdf "Architecture overview")__

Tinder is a very trendy thing. It's like clubbing, but without the fun :D

From a technical standpoint however, it is an awful piece of software, at least the Android app:
* It requires you to periodically perform a monkey task (swiping), which is cumbersome and inefficient, and could be easily automated.
* It performs a very aggressive polling to check for changes that may affect your experience (in other words, it continuously asks the server 'did anyone message me?' instead of letting the server notify the device when a message came). This results in unnecessary battery consumption and, when implemented poorly, buggy behavior too.
* It does not support landscape mode.

This repo is here to show that these and other pain points are easily addressed nowadays if things are done correctly and, while at it, I'll take the chance to demonstrate how well-built modern Android apps are made, and that this is something very feasible to achieve even if your team just consists of a single developer and instead of having the backend guy sitting next to him, you only have Charles Proxy.

## Distribution

At some point, I would love to distribute this app through some channel like Google Play or F-Droid, but at the time of writing there is little reason for anyone to use this app, although I intend that to change soon.
Until that happens, you are encouraged to try out the app by getting it yourself from the 'Releases' tab, which you can find above the file explorer in this page.

## Building

See [BUILDING.md](BUILDING.md).

## Contributing 

See [CONTRIBUTING.md](CONTRIBUTING.md).
