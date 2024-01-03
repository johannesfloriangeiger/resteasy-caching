Client-Side caching with RESTEasy
=

Setup
-

Checkout the code and run

```
mvn -f server/pom.xml clean install spring-boot:repackage && mvn -f client/pom.xml clean install
```

Run
-

```
java -jar server/target/caching-server.jar
```

and

```
java -jar client/target/caching-client.jar
```

You should see ten lines of `Hello World` but only five `Request received` log statements because the
controller (`org.example.MessageController`) adds a `Cache-Control` header to the response that by default allows
caching of responses up to 10s - and as every request delays its execution by just about 5s every second request
immediately returns based on RESTEasy's `LightweightBrowserCache`.