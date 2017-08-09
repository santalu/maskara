# Mask EditText

[![](https://jitpack.io/v/santalu/mask-edittext.svg)](https://jitpack.io/#santalu/mask-edittext) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Mask%20EditText-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6037)

## Sample

<img src="https://github.com/santalu/mask-edittext/blob/master/screens/sample.png"/>

## Usage

### Gradle
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
```
dependencies {
  compile 'com.github.santalu:mask-edittext:1.0'
}
```

### XML
```xml
 <com.santalu.maskedittext.MaskEditText
    android:id="@+id/et_phone_number"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/hint_phone_number"
    android:inputType="phone"
    app:mask="+90(###) ### ## ##"/>
```

## Attributes

| Name        | Description           | Value  |
| ------------- |:-------------:| -----:|
| mask      | pattern | string |

## Notes

* use ```getRawText()``` to get unmasked text

## License
```
Copyright 2017 Fatih Santalu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
