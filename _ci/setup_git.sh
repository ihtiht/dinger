#!/usr/bin/env bash
set -e

chmod 600 travis_rsa
eval `ssh-agent -s`
ssh-add travis_rsa
