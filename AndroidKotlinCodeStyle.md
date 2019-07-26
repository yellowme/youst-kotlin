# Android Kotlin Code Style

Code formatting rules for Android Studio

## Features

## Installation on your local machine

### Automatically (the easiest way)
Just run the [`install`](install.sh) script.
It will automatically copy the latest Code Style to each existing `AndroidStudio` and `AndroidStudioPreview` version you have installed.

You can also install it directly into an Android Project by running
```
$ ./install.sh PATH/TO/YOUT/PROJECT
```

> **Note:** Before you commit the changes to git make sure you have opened AS/IntelliJ already.
            The IDE will slightly change the XML after the coping.

### Manually (the hard way)
1. Copy the [`yellowme-kotlin.xml`](styles/yellowme-kotlin.xml) into (MacOS) ``~/Library/Preferences/AndroidStudio{VERSION}/codestyles/`` or (Linux) ``~/.AndroidStudio{VERSION}/config/codestyles/``
2. Restart AndroidStudio
3. Select the codestyle scheme via `Preferences --> Editor --> Code Style`.

The codestyle will be enabled/used for **all projects** that are used with AndroidStudio!

## Enabling project specific code styles for a project
If the codestyle is added to the git repository and IntelliJ is configured accordingly each project can have it's own style.

1. Install the [`yellowme-kotlin.xml`](styles/yellowme-kotlin.xml) locally (see above)
2. Restart AndroidStudio
3. In AndroidStudio, go to `Preferences --> Code style`
4. Open the scheme manager by clicking on `Manage...`
5. Select the code style and click `Copy to project`
6. In the scheme drop down select `Project`

Finally add the code style to the git repository:
```
git add -f .idea/codestyles/Project.xml .idea/codestyles/codeStyleConfig.xml
```
