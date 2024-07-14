# react-native-get-deviceinfo
An SDK for react Native that helps getting information about user's android device's id, AAID, ip address, brand, model, os version etc.


## TOC
- [Installation](#installation)
- [Usage](#usage)


## Documentations
- [React Native Documentation](https://www.reactnative.dev)

## Installation
```shell
  npm install react-native-get-deviceinfo
```

## Configuring Native Modules
The native modules must be located in the project root `/android/app/src/main/java/com/yourprojectname`.

- Copy `DeviceInfoModule.java` to `/android/app/src/main/java/com/yourprojectname`.

- Register the module in our app, by creating a package `DeviceInfoPackage`. 

- Above files are present in this repository inside `Native Modules` folder.


## Configuring `MainApplication.java` or `MainApplication.kt`

- Add `DeviceInfoPackage()` inside the `getPackages()` in the `MainApplication.kt` file. (Kotlin)

- Add `packages.add(new MyReactNativePackage());` inside the `getPackages()` in the `MainApplication.java` file. (Java)

The DeviceInfoModule contains the native implementation of the package. Please complete above steps before using it.



## Usage

#### Import components
```shell
import {getOsVersion, getDeviceId, getIpAddress, getAaid, getDeviceBrand, getDeviceModel, getScreenSize} from 'react-native-get-deviceinfo'
```

#### Quick Example
```shell
<View>
      <TouchableOpacity onPress={getOsVersion}>
        <Text>Get OS Version</Text>
      </TouchableOpacity>
</View>
```