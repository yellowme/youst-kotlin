# Building Universal APK for bundled app

First, generate the app bundle with AndroidStudio. This will generate a file with a **`.aab`** extension. 

To build the APK's you need to download the bundletool provided by google. You can find the **`.jar`** file [here](https://github.com/google/bundletool/releases).

In case you want to run the app on a device you will need to sign the apk. To do this you will need a **`keystore.jks`** file to sign the app. 

All the modules that you want to include into your universal APK need to have **fusing set to true** (you can find this in the manifest of your module).

```xml
    <dist:module
        dist:instant="false"
        dist:title="@string/title_portfolio">
        <dist:delivery>
            <dist:install-time />
        </dist:delivery>

        <dist:fusing dist:include="true" />
    </dist:module>
```

To sign the APK, the bundletool will run some adb commands. You should be able to run the adb commands from the directory where you are building the APK's. If you can´t already, run these on the command line:

```
echo 'export PATH=$PATH:/Users/*thisismyaccount*/Library/Android/sdk/platform-tools/' >> ~/.bash_profile

source ~/.bash_profile
```

For simplicity, add both the **`.aab`** and **`keystore.jks`** files to the folder where you saved the **`bundletool.jar`** and then run this command. For the command below we are using the following bundle name: **`app-debug.aab`**.

```
java -jar bundletool.jar build-apks --bundle=app-debug.aab --output=app-debug.apks --ks=keystore.jks --key-pass=<key_store_password> --ks-key-alias=<key_alias> --ks-pass=<key_password> --overwrite --mode=universal
```

When using a file for the password you need to add **`file:`** before the path. If you are just providing the password you need to add **`pass:`** before the password. For example: **`--ks-pass=pass:123456`**

```
java -jar bundletool.jar build-apks --bundle=app-debug.aab --output=app-debug.apks --ks=keystore.jks --key-pass=pass:123456 --ks-key-alias=<key_alias> --ks-pass=pass:123456 --overwrite --mode=universal
```

After running this you will have a **`app-debug.apks`** file in your folder. Change the extension of the generated file to **`.zip`** (it should look like this **`app-debug.zip`**) and extract the files to get the universal APK, now it's ready for installation. ***Just make sure when installing this APK the user doesn´t have an unsigned version installed.***