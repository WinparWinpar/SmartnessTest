#!/bin/bash

function uninstall(){
    rm -f smartnessTest.java
    if [ $JAVA = false ]; then
        read -rp $'Uninstall Java [Y/n]: ' key3
        if [ $key3 -eq 'Y']; then
            wget https://javadl-esd-secure.oracle.com/update/jut/JavaUninstallTool.dmg
            echo Open JavaUninstallTool.dmg
            open .
            read -rsp $'Press any key to continue uninstallation...\n' -n 1 key
            echo Open JavaUninstallTool.app (Double click it)
            open .
        fi
    fi
    rm -f Test-dl.sh
}

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
    read -rsp $'Press any key to continue installation...\n' -n 1 key

    echo
    read -rp $'Delete jdk-17_macos-x64_bin.dmg [Y/n]: ' key
    if [$key -eq 'Y']; then
        echo Deleting jdk-17_macos-x64_bin.dmg
        rm -f jdk-17_macos-x64_bin.dmg
    fi
    JAVA=false
}

function installBrew(){
    /bin/bash -c $(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)
    BREW=false
}

function install(){
    echo Installing Dependencies

    echo
    echo Installing Homebrew
    which brew > /dev/null || installBrew
    echo
    echo Installing wget
    brew install wget

    which java > /dev/null || installJava || JAVA=true

    read -rp $'Uninstall Homebrew [Y/n]: ' key2
    if [$key2 -eq 'Y']; then
        /bin/bash -c $(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/uninstall.sh)
    fi

    curl -fsSL https://raw.githubusercontent.com/WinparWinpar/SmartnessTest/main/smartnessTest.java
}

while getopts ":riu" opt; do
    case ${opt} in
        r ) test
            ;;
        i ) install
            ;;
        u ) uninstall
            ;;
        \? ) echo Error; exit 1
            ;;
    esac
done
