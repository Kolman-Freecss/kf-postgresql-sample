# Use an official PostgreSQL image to run the database
FROM postgres:13

# Copy the init.sql to the folder where PostgreSQL will automatically execute it
COPY init.sql /docker-entrypoint-initdb.d/

# Set environment variables for PostgreSQL
ENV POSTGRES_USER=user
ENV POSTGRES_PASSWORD=password
ENV POSTGRES_DB=testdb

# Expose PostgreSQL port
EXPOSE 5432

# Default command to start PostgreSQL
CMD ["postgres"]
