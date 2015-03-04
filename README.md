# Caching
Support de présentation sur la gestion du cache dans les applications Web 2.0^^.

- Verbes HTTP
- La vie sans cache
- Please, pas de cache !
- Le cache mais quand ?
- Cache par expiration
- Cache par validation

## Installation
``` bash
mvn clean install
```

## Démarrer le micro service
- unzip le fichier dans /target
``` bash
java -jar caching-X.X-SNAPSHOT.jar -Dserver.port=8080
```

## Consulter la présentation
- http://localhost:8080/presentation.html

## Refaire les tests
Sur la page principal http://localhost:8080/index.html, saisir dans la console
- $.get("rest/...") cf code source