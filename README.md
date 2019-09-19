# DebugMenu (Android)
*Add a smart side drawer to your applications to simplify development.*

**This library is in very early stages of development. New features will be added and the API might change.**

### Usage
Add the following to your top level Gradle file:

```groovy
allprojects {
    repositories {
        …
        maven { url "https://jitpack.io" }
    }
}
```

...and this to the module-level build script:

```groovy
dependencies {
    …
    def debugMenuVersion = "0.0.4" // See the widget below for the latest version
    debugImplementation "com.github.pandulapeter.debug-menu:debug-menu:$debugMenuVersion"
    releaseImplementation "com.github.pandulapeter.debug-menu:debug-menu-noop:$debugMenuVersion"
}
```

The latest version is:
[![](https://jitpack.io/v/pandulapeter/debug-menu.svg)](https://jitpack.io/#pandulapeter/debug-menu)

The library has to be initialized in the Application class. See the [example](https://github.com/pandulapeter/debug-menu/blob/master/example/src/main/java/com/pandulapeter/debugMenuExample/DebugMenuExampleApplication.kt) for details. Also, if you want to properly support back navigation, all activities must check if the drawer consumes the event. This is implemented [here](https://github.com/pandulapeter/debug-menu/blob/master/example/src/main/java/com/pandulapeter/debugMenuExample/MainActivity.kt).

To use the NetworkLoggingModule a custom interceptor needs to be added to the OkHTTP Client's builder, as implemented [here](https://github.com/pandulapeter/debug-menu/blob/master/example/src/main/java/com/pandulapeter/debugMenuExample/NetworkingManager.kt).

### To do
* Fix keyline overlay setting not being synchronised between activities.
* Fix inset handling bugs
* Add module for environment switching
* Add support for filtering log messages based on tags
* Add support for log message payloads
* Add support for displaying the JSON request / response data
* Add module with device / OS information
* Add module for taking a screenshot
* Add support for writing custom modules
* Double-check the way the drawer is inserted into the layout hierarchy, make sure it doesn't break Activity shared element transitions
* Test with Android 10 gestures
* Improve this readme.

### License
```
Copyright 2019 Pandula Péter

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