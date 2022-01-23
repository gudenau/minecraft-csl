### How to add as a dependency
Throw this into your build.gradle:
```groovy
repositories {
    maven {
        name = "gudenau's maven"
        url = "https://maven.gudenau.net"
        content {
            includeGroup 'net.gudenau.minecraft'
        }
    }
}

dependencies {
    modImplementation(include("net.gudenau.minecraft:CustomShaderLib:${project.csl_version}"))
}
```
Throw this into your `gradle.properties`:
```groovy
csl_version=[required version]
```
