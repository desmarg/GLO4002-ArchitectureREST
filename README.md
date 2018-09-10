# ReadMe à supprimer avant la remise

Ce readme sert à vous expliquer la structure de base de votre projet. Vous devez le remplacer par le vôtre **avant** la remise!

## Structure des modules

Il existe trois [modules maven](https://maven.apache.org/guides/mini/guide-multiple-modules.html) dans le projet : 

 * stocks-api : Un API qui vous permet d'obtenir des valeurs d'actions. Il vous est fourni comme "service externe", donc vous ne **pouvez pas le modifier**. Vous ne pouvez pas y référer directement en java non plus, vous devez passer par son API REST. Voir la documentation de ce projet au fil des stories pour l'utiliser.
 * trading-api : le projet que vous développerez. Ici, vous pouvez modifier tout ce qui vous plait. Le code présent n'est pas nécessairement bon/bien placé/selon les normes de votes équipe. À vous de voir!
 * application : permet de démarrer les 2 API simultanément. Vous pouvez le modifier également.
 
Vous pouvez également modifier tous les autres fichiers du projet, autre que ceux dans `stock-api`.

## Intégration travis

L'intégration avec travis vous est fourni. Si travis ne fonctionne pas, n'hésitez pas à nous contacter.

## Démarrer le projet

Vous pouvez démarrer soit un des deux serveurs (StocksServer ou TradingServer) individuellement ou simultanément.

Il y a trois classes que vous pouvez exécuter dans intelliJ ou Eclipse pour cela : `StocksServer`, `TradingServer` ou `ApplicationServer`. Aucun des `main()` ne demande d'argument.

Vous pouvez également rouler le serveur via maven (**c'est ce qui sera utilisé pour corriger votre projet**) : 

```bash
mvn clean install
mvn exec:java -pl application
```

## Prochaines étapes

Nous regarderons le projet rapidement mercredi. La cliente sera également présente pour vous le présenter davantage. D'ici là, assurez-vous de pouvoir exécuter le code sur votre machine.

## Besoin d'aide?

Vous pouvez nous écrire à aide@qualitelogicielle.ca si quelque chose ne fonctionne pas!