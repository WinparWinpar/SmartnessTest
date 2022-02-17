#!/bin/bash

function test(){
    javac smartnessTest.java
    java smartnessTest; rm -f smartnessTest.class
}

function installJava(){
    echo
    echo Installing Java
    wget https://download.oracle.com/java/17/latest/jdk-17_macos-x64_bin.dmg

    echo
    echo Open jdk-17_macos-x64_bin.dmg
    open .
    echo
    read -rsp $'Press any key to continue...\n' -n 1 key

    echo
    read -rp $'Delete jdk-17_macos-x64_bin.dmg [Y/n]: ' key
    if [$key -eq 'Y']; then
        echo Deleting jdk-17_macos-x64_bin.dmg
        rm -f jdk-17_macos-x64_bin.dmg
    fi
}

function installBrew(){
    /bin/bash -c $(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)
}

function install(){
    echo Installing Dependencies

    echo
    echo Installing Homebrew
    which brew > /dev/null || installBrew
    echo
    echo Installing wget
    brew install wget

    which java > /dev/null || installJava

    read -rp $'Uninstall Homebrew [Y/n]: ' key2
    if [$key2 -eq 'Y']; then
        /bin/bash -c $(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/uninstall.sh)
    fi

    curl -fsSL https://raw.githubusercontent.com/WinparWinpar/SmartnessTest/main/smartnessTest.java
}

while getopts ":ri" opt; do
    case ${opt} in
        r ) test
            ;;
        i ) install
            ;;
        \? ) exit 1
            ;;
    esac
done