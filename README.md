# Easy Android

[![](https://jitpack.io/v/facile-it/easy-android.svg)](https://jitpack.io/#facile-it/easy-android)
[![Build Status](https://travis-ci.org/facile-it/easy-android.svg?branch=master)](https://travis-ci.org/facile-it/easy-android)

This repo contains a set of Android utility functions written in Kotlin. 

## Download

To include Easy Android into your app using gradle just add [JitPack](https://jitpack.io/) repository to your root `build.gradle`
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
and then add the dependency to your app `build.gradle`
```groovy
compile 'com.github.facile-it:easy-android:x.x.x'
```
replacing x.x.x with the [last release](https://github.com/facile-it/easy-android/releases) version.

# Usage

All the functions are divided into multiple files, one for each different context of application.
 
Every function is written as a Kotlin extension function so within Kotlin code it is directly accessible 
as a method on the receiver instance and you can take advantage of optional parameters:

```kotlin
class MyActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        openUrl("www.facile.it".toUri())
        openUrl(url = "www.facile.it".toUri(), tabColor = R.color.colorPrimary)
    }
}
```

You can also use them from Java, accessing them as static functions on a class named as file_nameUtils 
(e.g.: `ActivitiesUtils`) and passing them the receiver:

```java
public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ActivitiesUtils.openUrl(this, VariousUtils.toUri("www.facile.it"));
        ActivitiesUtils.openUrl(this, VariousUtils.toUri("www.facile.it"), R.color.colorPrimary);
    }
}
```

As you can see from the previous example you can also take advantage of optional parameters form Java
code. This is possible thanks to `@JvmOverloads` Kotlin annotation which produces multiple static method,
one for every combination of optional parameters.
