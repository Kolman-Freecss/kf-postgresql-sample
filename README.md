# kf-postgresql-db-sample

This project demonstrates how to use Groovy to execute basic SQL operations on a PostgreSQL database. The database is initialized using a Docker container with an `init.sql` script, and the project is managed with Maven. It is a simple test project to showcase database interactions such as **INSERT**, **SELECT**, **UPDATE**, and **DELETE**, as well as **INNER JOIN** queries, all written in Groovy.

---

## Tech Stack

- **Groovy**: A dynamic programming language for JVM.
- **PostgreSQL**: A powerful, open-source relational database.
- **Docker**: Used to containerize the PostgreSQL instance.
- **Maven**: For project and dependency management.

---

## Key Notes

- This project is for **testing purposes only** and is not optimized for production use.
- The Groovy script connects to the PostgreSQL database using the JDBC driver and performs basic SQL operations.
- To extend the project, modify `init.sql` or add more functionality in the Groovy script.

---

## Features

- **Database Initialization**: A PostgreSQL database is automatically created and seeded using a provided `init.sql` script.
- **Groovy Scripting**: Simple Groovy scripts are used to interact with the database using SQL.
- **Dockerized Environment**: The PostgreSQL database runs in a Docker container for easy setup and portability.
- **Maven Build**: Dependency management is handled with Maven, ensuring a smooth build and execution process.

---

## Prerequisites

1. **Docker**: Ensure Docker is installed and running.
2. **Maven**: Install Maven to manage dependencies and build the project.
3. **JDK 8+**: A compatible Java Development Kit is required to run the Groovy scripts.

---

## How to Run

1. **Start the PostgreSQL Container**
   - Build and run the Docker container using the provided `Dockerfile`. The database will be initialized using `init.sql`.
   ```bash
   docker build -t postgres-sample .
   docker run -d -p 5432:5432 --name postgres-sample postgres-sample
