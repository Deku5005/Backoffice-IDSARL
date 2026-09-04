# IDSARL Backend

Backend Spring Boot pour la gestion d'entreprise IDSARL.

## 📌 Technologies

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security** (JWT, RBAC)
- **Spring Data JPA** (Hibernate)
- **PostgreSQL** (ou H2 en dev)
- **MapStruct** (mapping DTO)
- **AES** (chiffrement champs sensibles)
- **Swagger/OpenAPI** (documentation)
- **Lombok** (réduction boilerplate)
- **Maven** (gestion dépendances)

---

## 🚀 Installation

### Prérequis

- Java 17+
- Maven 3.8+
- PostgreSQL (ou Docker)

### Base de données

Créez une base PostgreSQL `idsarl_db` (ou utilisez H2 en commentant/décommentant la config dans `application.yml`).

### Configuration

1. Clonez le dépôt.
2. Copiez `application.yml.example` en `application.yml` et renseignez :
    - `spring.datasource.password` (mot de passe PostgreSQL)
    - `jwt.secret` (clé secrète JWT, >= 32 caractères)
    - `encryption.secret` (clé AES 256 bits encodée en Base64)

### Compilation et exécution

```bash
mvn clean install
mvn spring-boot:run