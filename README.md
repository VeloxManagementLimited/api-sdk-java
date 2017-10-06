# Velotrade-sdk

## Recent Update
 As of this current code is beta. Please take care yourself :)

# Installing

You can complie it yourself, here's how:

    $ git clone https://github.com/VeloxManagementLimited/api-sdk-java.git
    $ cd api-sdk-java
    $ ./gradlew fatJar
    
You can see the jar file at folder

    build/libs/
    
# Quickstart

```java
String baseUrl = "https://devapi.velotrade.com";
String username = "your username";
String password = "your md5 password";
VelotradePublicAPI api = new VelotradePublicAPIImpl(baseUrl, username, password);    
    
```

### getDebtorContact

```java
DebtorContact result = api.getDebtorContact(id);
```
