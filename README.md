# More API for Paper
- Add at Minecraft Paper API

### What can MAP help me?
- Map Can Help Your Coding.
- You can reduce time to do.
- Easy Functions!

Maven ( pom.xml )
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.qLarge</groupId>
    <artifactId>MAP</artifactId>
    <version>Version</version>
</dependency>
```

Gradle Groovy DSL ( build.gradle )
```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.qLarge:MAP:Version"
}
```

Gradle Kotlin DSL ( build.gradle.kts )
```gradle
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.qLarge:MAP:Version")
}
```

Sample
```kotlin
class Sample : JavaPlugin() {
    override fun onEnable() {
        val sampleWorld = addWorld(NamespacedKey("sample"))

        elseEvent<PlayerDropItemEvent> {
            this.isCancelled = true
        }
        
        events {
            onPlayerJoin {
                wait(10.0) {
                    sendMessageToPlayers(sampleWorld.players)
                }
                
                loop(20L, 10) {
                    player.sendMessage(Component.text(time()))
                }
            }
            
            onPlayerDeath {
                isCancelled = true
                
                Text("${player.name} is Die!").toComponent() sendTo sampleWorld.players
            }
        }
    }
    
    private fun sendMessageToPlayers(playerList: List<Player>) {
        playerList.forEach { 
            it.elseEvent<PlayerMoveEvent>(this@Sample) {
                this.isCancelled = this.hasChangedBlock()
            }
            
            Component.text("Hello!") sendTo it
            
            it to Location(overWorld, 0.0, 60.0, 0.0)
            
            it.watch(Location(overWorld, 1.0, 60.0, 5.0))
        }
        
        wait(40L) { Component.text("Bye!") sendTo playerList }
    }
}
```
