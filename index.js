import {NativeModules} from 'react-native'
const {GetDeviceInfo} = NativeModules

function getOsVersion () {
  console.log('Getting OS Version...')
  GetDeviceInfo.getOSVersion((error, osVersion) => {
    if (!error) {
      console.log('OS Version: ', osVersion)
    } else {
      console.error(error)
    }
  })
}

function getDeviceId () {
  console.log('Getting device id...')
  GetDeviceInfo.getDeviceID((err, deviceID) => {
    if (err) {
      console.error(err)
    } else {
      console.log(deviceID)
    }
  })
}

function getIpAddress () {
  console.log('Getting Ip Address...')
  GetDeviceInfo.getDeviceIPAddress((error, ipAddress) => {
    if (!error) {
      console.log('IP Address: ', ipAddress)
    } else {
      console.error(error)
    }
  })
}

function getAaid () {
  console.log('Getting AAID...')
  GetDeviceInfo.getAAID((error, aaid) => {
    if (!error) {
      console.log('Google Advertising ID: ', aaid)
    } else {
      console.error(error)
    }
  })
}

function getDeviceBrand () {
  console.log('Getting Device Brand...')
  GetDeviceInfo.getDeviceBrand((error, brand) => {
    if (!error) {
      console.log('Device Brand: ', brand)
    } else {
      console.error(error)
    }
  })
}

function getDeviceModel () {
  console.log('Getting Device Model...')
  GetDeviceInfo.getDeviceModel((error, model) => {
    if (!error) {
      console.log('Device Model: ', model)
    } else {
      console.error(error)
    }
  })
}

function getScreenSize () {
  console.log('Getting Screen Size...')
  GetDeviceInfo.getScreenSize((error, width, height, density) => {
    if (!error) {
      console.log(`Screen Size: ${width}x${height}, Density: ${density}`)
    } else {
      console.error(error)
    }
  })
}

export {
  getOsVersion,
  getDeviceId,
  getIpAddress,
  getAaid,
  getDeviceBrand,
  getDeviceModel,
  getScreenSize,
}
