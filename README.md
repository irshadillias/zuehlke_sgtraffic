# Introduction
This application Using Google map for plotting Camera Location in SIngapore. Application Google Map and Using "https://data.gov.sg/dataset/traffic-images" Web api For 
Getting Location information and camera Image. 

  Assumption

  1. As per the web api Documentation need to refresh the map every 1 mint for every single mint for getting user experience.
  2. Web required time stamp - As per the document we need to pass the time stamp, Eventhough we are not passing this param we will get the data.
     For safer side application passing the mobile date on the web api. 
     If user chnage mobile date it will r=trigger with that timestamp. It not a good pracise if we get the value from service then it will be good.

# Clean Code Advantages

Scalability.
Modularization.
Testability.
Independence of frameworks, UI and Databases.

# Code Architecture
This application uses MVVM Architecture, and kotlin DSL used instead of gradle. Whole code is modularized So feature can develop independently with the help of databinidng. Following libraries are used for

    1. Dagger
    2. Robolectric
    2. Rectrofit
    3. Coroutines
    4. okHttp
    5. Livedata


# Development Activity

Please use following Gradle/adb commands for executing this project:

 * `./gradlew runApp` - Builds and install the debug apk on the current connected device.
 * `./gradlew compileApp` - Builds the debug apk.
 * `./gradlew sgTrafficRunUnitTests` - Execute unit tests (both unit and integration).
 * `./gradlew sgTrafficRunAcceptanceTests` - Execute acceptance and instrumentation tests in the connected device.

#Test Result
![HomePage](screenshots/landingpage.png "HomePage")
![HomePage](screenshots/unittestresult.png "unittest result")