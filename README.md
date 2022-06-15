# API Java Cité de l'infinie

Java API of [Cité de l'infinie](https://citedelinfini.fr), can only get teams data.

Currently maintained by [@MaygoDev](https://www.github.com/MaygoDev).

## Using API

To use this API, first add maven dependency to your `pom.xml`.

```xml
<dependency>
    <groupId>eu.domei.uhc</groupId>
    <artifactId>uhc-api-local</artifactId>
    <version>2.0-RELEASE</version>
    <scope>provided</scope>
</dependency>
```
### Simple getting teams

```java
public static void main(String[] args) {
    final CiteInfinieClient citeInfinieClient = CiteInfinieClient.newClient();
    
    //Getting first team page
    citeInfinieClient.getTeamsInPage(1).thenAccept(citeInfinieTeams -> { //Executed after retrieved first page
        System.out.println("found "+citeInfinieTeams.size()+" teams!");
        for (CiteInfinieTeam citeInfinieTeam : citeInfinieTeams) {
            System.out.println(citeInfinieTeam.getName()+"/"+citeInfinieTeam.getTag()+" has "+citeInfinieTeam.getMembers().size()+" members.");
        }
    }).exceptionally(throwable -> { //Error while getting first page
        throwable.printStackTrace();
        return null;
    });
}
```

## Running Tests

To run tests, run the following command

```bash
mvn test
```

## Authors

- [@MaygoDev](https://www.github.com/MaygoDev)