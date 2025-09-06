# 📌 SideBanner – Android Custom View

**SideBanner** is a lightweight and customizable Android View that lets you display a stylish diagonal banner (like a “DEBUG”, “BETA”, or “SALE” label) on top of your layouts. Perfect for marking build types, promotions, or any highlighted text in your app.  

---

## ✨ Features
- 🎨 Customizable **text, size, and color**
- 🟥 Customizable **background color**
- 🔄 Supports **runtime updates** (`setText()`, `setBackGroundColor()`)
- ⚡ Simple and lightweight (no external dependencies)
- 🛠️ Easy to integrate via **JitPack**

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
    implementation 'com.github.<your-username>:SideBanner:1.0.2'
}
```

---

## 🛠️ Usage

```xml
<com.brine.sidebanner.SideBanner
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

---

## 📸 Preview  
*(Add a screenshot/gif of the banner here to make it more attractive)*  

---

## 📝 License
```
MIT License
Copyright (c) 2025 <Nadiya Sagar>
```
