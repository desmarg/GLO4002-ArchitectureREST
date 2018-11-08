# SEMESTER PROJECT - GLO4002UL - TEAM 5
|   FIRSTNAME  |    LASTNAME    |   IDUL   |  MATRICULATION  |
|:---------:|:---------:|:--------:|:-----------:|
|   Jacob   |  Bouchard | JABOU404 | 111 127 636 |
|  Maxence  |   Caron   | MACAR703 | 111 157 100 |
|   Jessy   |    Chea   |  JECHE34 | 111 128 599 |
|   Samuel  | Dansereau |  SADAN16 | 111 157 751 |
| Guillaume | Desmarais-de Grandpré |  GUDED1  | 111 022 887 |
|   Thomas  |   Drouin  |  THDRO15 | 111 007 979 |
|  Raphael  | Gaudrealt |  RAGAU72 | 111 129 495 |
|   Daniel  |   Lavoie  | DALAV162 | 111 103 095 |
|  Antoine  |   Somma   |  ANSOM2  | 111 105 700 |

## PROJECT STATUS
|                                USER STORIES                               | STATUS   |
|:-------------------------------------------------------------------------:|----------|
| COOU - Ouvrir un compte avec des crédits                                  | COMPLETE |
| TXAC - Effectuer un achat                                                 | COMPLETE |
| TXVE - Effectuer une vente                                                | COMPLETE |
| TXFR - Appliquer des frais de transaction                                 | COMPLETE |
| TXHO - Refuser les transactions en dehors des heures d’ouverture          | PENDING PR |
| RTHI - Lister l’historique des transactions                               | PENDING PR |
| RTBA - Calculer la valeur du portefeuille                                 | TODO     |
| EVDI - Traiter l’émission d’un dividende                                  | TODO     |
| RTPO - Afficher le rapport trimestriel                                    | TODO     |
| TXHF - Supporter les transactions faites en dehors des heures d’ouverture | TODO     |
| RANN - Consulter le rapport annuel                                        | TODO     |
| COPR - Choisir mon profil d’investisseur                                  | TODO     |
| TXCO - Faire une transaction avec contrainte du profil d’investisseur     | TODO     |
| COTY - Change le type de compte (à découvert)                             | TODO     |
| TXVD - Effectuer une vente à découvert                                    | TODO     |

# ENV
The environment variable `TRADING_API_PORT` can be set, otherwise it
will default to `8181`.

# Commands

## Install project
```bash
# While in project root
mvn clean install
```

## Run project
```bash
# While in project root
mvn exec:java -pl application
```

## Linting
```bash
# While in project root
mvn checkstyle:check -pl application
```

# Contributing

[See here](CONTRIBUTING.md)

# Remises

[See here](http://projet2018.qualitelogicielle.ca/)

## IDE Integration
[Here](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea)
is a jetbrains plugin for checkstyle, and
[here](https://checkstyle.org/eclipse-cs/#!/)
the eclipse one.
