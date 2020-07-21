# Maskara

[![](https://www.jitpack.io/v/santalu/maskara.svg)](https://www.jitpack.io/#santalu/maskara)
[![Build Status](https://travis-ci.org/santalu/maskara.svg?branch=master)](https://travis-ci.org/santalu/maskara)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

A simple way to format text fields without getting affected by input filters.

| Normal        | Completable   | Persistent    |
| ------------- | ------------- | ------------- |
| <img src="./media/normal.gif" alt="maskara" title="Normal" />  | <img src="./media/completable.gif" alt="maskara" title="Completable" /> | <img src="./media/persistent.gif" alt="maskara" title="Persistent" />  |

## How It Works

#### [Mask](./library/src/main/java/com/santalu/maskara/Mask.kt)

Used to define how to format the text.

* `value` is used to define the format string. E.g. `+90 (___) ___ __ __`

There's no limitation here. You can use any type of characters with any type of input filters.

* `maskCharacter` is used to define which characters in the format string user can edit. E.g. `_`

It's not mandatory to define this explicitly. Mask will set the character with most occurences in the format string as `maskCharacter` by default.

You should use non alphanumeric characters here.

#### [MaskStyle](./library/src/main/java/com/santalu/maskara/MaskStyle.kt)

Used to define mask visibility and cursor behaviour.

* `Normal` Mask is never visible. Cursor is not limited.

* `Completable` Mask becomes visible right after the user started typing until the user deleted everything. Cursor is not limited.

* `Persistent` Mask becomes visible right after the user started typing and never becomes hidden. Cursor is limited between mask characters.

#### [MaskResult](./library/src/main/java/com/santalu/maskara/MaskResult.kt)

You can retrieve these from both the widget and the listener.

* `masked` Masked version of input text.

* `unMasked` Raw version of input text.

* `isDone` Validity of input text.

## Usage

#### With Widget

```
<com.santalu.maskara.widget.MaskEditText
    android:id="@+id/input"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:inputType="number"
    app:mask="+90 (___) ___ __ __"
    app:maskCharacter="_"
    app:maskStyle="completable" />
```

To get the result:
```
val masked = input.masked
val raw = input.unMasked
val isDone = input.isDone
```

#### Without Widget

```
val mask = Mask(
    value = "+90 (___) ___ __ __",
    character = '_',
    style = MaskStyle.COMPLETABLE
)
val listener = MaskChangedListener(mask)
textField.addTextChangedListener(listener)
```

To get the result:
```
val masked = listener.masked
val raw = listener.unMasked
val isDone = listener.isDone
```

## Dependencies

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```
dependencies {
    implementation 'com.github.santalu:maskara:1.0'
}
```

## License

```
Copyright 2020 Fatih Santalu

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
