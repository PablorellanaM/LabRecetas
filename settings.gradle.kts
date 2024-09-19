pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml")) // solo UNA llamada a 'from'
        }
    }
}

rootProject.name = "recipes_app"
include(":app")
