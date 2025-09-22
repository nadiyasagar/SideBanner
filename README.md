# 📌 SideBanner – Android Custom View

**SideBanner** is a lightweight and customizable Android View that lets you display a stylish diagonal banner (like a “DEBUG”, “BETA”, or “SALE” label) on top of your layouts. Perfect for marking build types, promotions, or any highlighted text in your app.

---

## ✨ Features

* 🎨 Customizable **text, size, and color**
* 🟥 Customizable **background color**
* 🔄 Supports **runtime updates** (`setText()`, `setBackGroundColor()`)
* ⚡ Simple and lightweight (no external dependencies)
* 🛠️ Easy to integrate via **JitPack**
* 📌 **Programmatic attachment** to any activity at runtime

---

## 🚀 Installation

Add **JitPack** to your root `settings.gradle`:

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency in your **app module**:

```gradle
dependencies {
    implementation 'com.github.nadiyasagar:SideBanner:1.0.4'
}
```

---

## 🛠️ Usage

### XML Usage

```xml
<com.github.nadiyasagar.sidebanner.SideBanner
    android:layout_width="match_parent"
    android:layout_height="100dp"
    app:text="DEBUG"
    app:textSize="18sp"
    app:textColor="@android:color/white"
    app:backgroundColor="@android:color/holo_red_dark"/>
```

### Programmatic Usage

```kotlin
val banner = findViewById<SideBanner>(R.id.sideBanner)
banner.setText("BETA")
banner.setBackGroundColor(Color.GREEN)
```

### Attach Banner to All Activities (Runtime)

You can now attach the SideBanner programmatically to any activity dynamically during runtime, for example to mark debug builds:

```kotlin
if (BuildConfig.DEBUG) {
    registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
        override fun onActivityStarted(activity: Activity) {
            SideBannerHelper.attachBanner(
                activity,
                text = "DEBUG",
                backgroundColor = Color.RED,
                textColor = Color.WHITE,
                textSize = 30,
                position = 3
            )
        }
        override fun onActivityResumed(activity: Activity) {}
        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {}
    })
}
```

This feature is particularly useful for marking debug, beta, or special environment builds without manually adding banners in every layout.

---

## 📸 Preview

<img width="1440" height="3120" alt="Screenshot_20250906_153945" src="https://github.com/user-attachments/assets/a1ec538a-dd3c-43d8-a428-6bfc89218fd1" />

---

## 📝 License

```
MIT License
Copyright (c) 2025 <Nadiya Sagar>
```
