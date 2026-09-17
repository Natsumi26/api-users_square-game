# API Users

API REST développée avec **Spring Boot** permettant de gérer les utilisateurs de l'application https://github.com/Natsumi26/API_square-game.

Cette API est utilisée en complément de l'API Games. Elle permet notamment à l'API Games de vérifier qu'un utilisateur existe avant de lui permettre d'accéder aux fonctionnalités de jeu.

---

## 📋 Présentation

L'application permet :

* de créer des utilisateurs ;
* de consulter les utilisateurs ;
* de récupérer un utilisateur par son identifiant ;
* de vérifier qu'un utilisateur existe ;
* de communiquer avec une base de données MySQL ;
* d'exposer une API REST documentée avec Swagger / OpenAPI.

L'API Users fonctionne indépendamment de l'API Games.

### Architecture du projet

```text
API Users
│
├── controllers
│   └── UserController
│
├── dao
│   ├── JpaUserDao
│   └── UserDao
│
├── models
│   ├── UserEntity
│   ├── UserEntityRepository
│   └── User
│
├── services
│   ├── UserServiceImpl
│   └── UserService
│
│
└── resources
    ├── application.properties 
    └── application-mysql.properties (Dans le gitIgnore : concerne les données sensibles d'accès à la BDD)
```

---

## 🛠️ Technologies utilisées

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* MySQL
* Docker / Docker Compose
* Maven
* Swagger / OpenAPI

---

## ⚙️ Prérequis

Avant de lancer le projet, installer :

* **Java**
* **Maven**

---

## 🗄️ Base de données

L'application utilise une base de données **MySQL** de l'API Games (https://github.com/Natsumi26/API_square-game).

La base de données est gérée sur l'API games.


---

## 🔧 Configuration

L'API Users utilise le port **8081** afin de pouvoir fonctionner en parallèle de l'API Games qui utilise le port **8080**.

Exemple de configuration :

```properties
server.port=8081
```

Les informations de connexion à la base de données sont définies dans les fichiers de configuration Spring Boot.

> ⚠️ Les mots de passe et informations sensibles ne doivent pas être publiés sur GitHub.

---

## ▶️ Lancer l'application

### 1. Démarrer MySQL (API GAMES)

```bash
docker compose up -d
```

### 2. Lancer l'application

Avec Maven :

```bash
./mvnw spring-boot:run
```

Sous Windows, il est également possible d'utiliser :

```bash
mvnw.cmd spring-boot:run
```

L'application démarre alors sur :

```text
http://localhost:8081
```

---
