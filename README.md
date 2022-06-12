# GraphQL Sample

## Overview

This application has been developed as an introduction to both GraphQL and document storage using Elasticsearch.

## Technology Stack

- JDK 17
- MariaDB for database persistence
- Flyway for database migration management
- Elasticsearch for archival marketplace transaction receipt storage

## Prerequisites

- Ensure that you have both Docker and Maven installed and configured on the PATH of your machine.
- If running through an IDE, ensure that you have JDK 17 installed and configured on the PATH of your machine.
- While a `docker-compose.yml` file has been provided for MariaDB, if you prefer to use your own instance ensure that
a database named `graphql_test` exists and skip the section below "MariaDB Configuration"

## Setup

Within the `docker` directory at the project root you will find `docker-compose.yml` files for both MariaDB and this
application.

### MariaDB Configuration

0) In your home directory create the following directory structure: `~/apps/mariadb`
1) Start by launching the `mariadb` container using `docker-compose up -d` within the `docker/mariadb` directory
2) Once this has started successfully use `docker-compose exec mariadb /bin/bash` to enter the `mariadb` container
3) Use the command `mariadb -u root -p` and enter the password **password** when prompted
4) Within the MariaDB console, that you have just logged into, now use the command: `CREATE DATABASE graphql_test;`
5) You may now exit the container by using the command `exit` twice. Leave the container running.

### Table Generation and Seed Data Insertion

1) This application uses the Maven flyway plugin for database migration, at the project root use the command:
   `mvn flyway:migrate` to create both the tables required for the `graphql_test` schema and insert 3 players and 3
   items
   into the MariaDB database

### Running Elasticsearch

1) Lastly as a pre-requisite, use `docker-compose up -d` in the `docker/elasticsearch` directory to launch an instance
   of Elasticsearch running on port 9200. 

### Building the graphql-test application

**Note:**  Integration tests will currently only work with seed data inserted using Flyway migrations. It is intended
in the future that the use of Testcontainers will remedy this. 

1) Again, at the project root, use the command `mvn clean install && cd target && docker build -t graphql_test .` in 
   order to build this application as a Docker image
2) Once the image has been built successfully you may now use the `docker-compose.yml` within `docker/graphql_test` to
   launch the application, again with `docker-compose up -d`, on port 8080.

## Usage

The application contains GraphiQL as a dependency which may be used to quickly test out functionality without any
additional installation of tools. By default, this is accessible at **http://localhost:8080/graphiql**

From this interface you are able to see the available `Query` and `Mutation` types, along with the configurable response
entities. There are 8 available interactions viewable in `resources/graphql/schema.graphqls`

Using the left-hand pane of GraphiDQL you can enter your queries.

## Example - Retrieving all players 

The following query:

```graphql
query {
    allPlayers {
        id
        username
        level
        inventory {
            id
            name
            effect
        }
    }
}
```

when run with the included seed data will return all the list of players inserted using Flyway:

```json
{
  "data": {
    "allPlayers": [
      {
        "id": "1",
        "username": "whiterose",
        "level": 42,
        "inventory": []
      },
      {
        "id": "2",
        "username": "hunter2",
        "level": 13,
        "inventory": [
          {
            "id": "1",
            "name": "Red Potion of Healing",
            "effect": "Restore 50HP"
          },
          {
            "id": "3",
            "name": "Green Potion of Magic",
            "effect": "Restore 50HGP"
          }
        ]
      },
      {
        "id": "3",
        "username": "coolguy42",
        "level": 50,
        "inventory": [
          {
            "id": "2",
            "name": "Blue Potion of Mana",
            "effect": "Restore 20MP"
          }
        ]
      }
    ]
  }
}
```