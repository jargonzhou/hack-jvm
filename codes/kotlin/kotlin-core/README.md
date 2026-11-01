# kotlin-core

Setup
```shell
$ mkdir kotlin-core
$ cd kotlin-core/
$ gradle init --type kotlin-application --dsl kotlin

$ tree
├── app
│	 ├── build.gradle.kts
│	 └── src
│	     ├── main
│	     │	 ├── kotlin
│	     │	 │	 └── org
│	     │	 │	     └── example
│	     │	 │	         └── app
│	     │	 │	             ├── App.kt
│	     │	 │	             └── MessageUtils.kt
│	     │	 └── resources
│	     └── test
│	         ├── kotlin
│	         │	 └── org
│	         │	     └── example
│	         │	         └── app
│	         │	             └── MessageUtilsTest.kt
│	         └── resources
├── buildSrc
│	 ├── build.gradle.kts
│	 ├── settings.gradle.kts
│	 └── src
│	     └── main
│	         └── kotlin
│	             ├── buildlogic.kotlin-application-conventions.gradle.kts
│	             ├── buildlogic.kotlin-common-conventions.gradle.kts
│	             └── buildlogic.kotlin-library-conventions.gradle.kts
├── gradle
│	 ├── libs.versions.toml
│	 └── wrapper
│	     ├── gradle-wrapper.jar
│	     └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── list
│	 ├── build.gradle.kts
│	 └── src
│	     ├── main
│	     │	 ├── kotlin
│	     │	 │	 └── org
│	     │	 │	     └── example
│	     │	 │	         └── list
│	     │	 │	             └── LinkedList.kt
│	     │	 └── resources
│	     └── test
│	         ├── kotlin
│	         │	 └── org
│	         │	     └── example
│	         │	         └── list
│	         │	             └── LinkedListTest.kt
│	         └── resources
├── settings.gradle.kts
└── utilities
    ├── build.gradle.kts
    └── src
        ├── main
        │	 ├── kotlin
        │	 │	 └── org
        │	 │	     └── example
        │	 │	         └── utilities
        │	 │	             ├── JoinUtils.kt
        │	 │	             ├── SplitUtils.kt
        │	 │	             └── StringUtils.kt
        │	 └── resources
        └── test
            └── resources
```