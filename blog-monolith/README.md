https://devskiller.com/coding-tests/middle-java-developer-spring-security-secure-the-article-editing-method/

Java | Spring Security | Secure the article editing method - Secure methods to allow only the article author 
to create and read an article with his name, using method annotations.


Test app:
- users with basic auth, defined in mysql/mongodb, each user has its own articles feed
- articles with comments sorted by date
- BDD tests with Spock
- Mongo as datasource
- CQRS
- DDD approach as a second implementation


### sonar 
* Start sonar container: docker/blog_sonar.yml
* Login to sonar http://localhost:9001/ and set a new password for admin: `qaz123`
* Run sonar analysis with mvn plugin:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```
