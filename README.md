# TAR3
 

## Outils Utilisés
- **Java 8** : Langage de programmation utilisé pour le développement de l'application.
- **Hibernate** : Framework ORM pour la gestion de la persistance des données.
- **NetBeans** : IDE utilisé pour le développement du projet.
- **MySQL** : Base de données relationnelle utilisée pour stocker les informations.


## Structure du Projet
Le projet est organisé en plusieurs packages, avec une séparation claire entre les couches de persistance, de service et de configuration.
![image](https://github.com/user-attachments/assets/0d8e4740-d60c-4c86-85e8-98dee34f113a)


### Package : `ma.projet.classes` : Contient les classes entités représentant les objets du domaine :
- `Personne` :Classe mere qui représente une personne avec des attributs tels que l'ID, le nom, etc.
- `Femme` : Classe fille de Personne, représente une femme avec des attributs comme l'ID, le nom, etc.
- `Homme` : Classe fille de Personne , représente un femme avec des attributs tels que l'ID, le nom, etc.
- `Marriage` : Classe associant un Homme à une Femme avec une date du mariage.

### Package : `ma.projet.config`
- `hibernate.cfg.xml` : Fichier XML configurant les paramètres de connexion à la base de données, les propriétés de Hibernate et les mappings des entités.

### Package : `ma.projet.util`
- `HibernateUtil` : Classe utilitaire pour gérer la session Hibernate et la configuration.

### Package : `ma.projet.dao`
- `IDao<T>` : Interface générique définissant les méthodes CRUD de base.

### Package : `ma.projet.service`: Contient les classes de service qui implémentent les méthodes spécifiques pour gérer les entités :
- `PersonneService` : Gère les opérations liées aux personnes.
- `FemmeService` : Gère les opérations liées aux femmes.
- `HommeService` : Gère les opérations liées aux hommes.

## Vue relationnelle entre les différentes entités dans la base de données
![image](https://github.com/user-attachments/assets/bce3bd0e-9f00-4833-8139-d400366b7d99)


