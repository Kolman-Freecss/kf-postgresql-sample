import groovy.sql.Sql

// Database connection parameters
def dbUrl = "jdbc:postgresql://localhost:5432/testdb"
def dbUser = "user"
def dbPassword = "password"

// Create a connection to the database
def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, 'org.postgresql.Driver')

// INSERT operation: Adding a new user
sql.execute("INSERT INTO users (name, email) VALUES (?, ?)", ['Alice Smith', 'alice.smith@example.com'])

// SELECT operation: Retrieve all users
println "Users:"
def result = sql.rows("SELECT * FROM users")
result.each { row ->
    println "ID: ${row.id}, Name: ${row.name}, Email: ${row.email}"
}

// UPDATE operation: Update a user's email
sql.execute("UPDATE users SET email = ? WHERE id = ?", ['john.doe@newdomain.com', 1])

// DELETE operation: Delete orders first
sql.execute("DELETE FROM orders WHERE user_id = ?", [2])

// DELETE operation: Delete the user
sql.execute("DELETE FROM users WHERE id = ?", [2])

// DELETE operation: Delete the user alice.smith@example.com if are more than 1
def count = sql.firstRow("SELECT COUNT(*) AS count FROM users WHERE email = ?", ['alice.smith@example.com']).count
if (count > 1) {
    sql.execute("DELETE FROM users WHERE email = ?", ['alice.smith@example.com'])
    println "Deleted user alice.smith@example.com as there were more than one"
    def newResult = sql.rows("SELECT * FROM users")
    println "Users updated:"
    newResult.each { row ->
        println "ID: ${row.id}, Name: ${row.name}, Email: ${row.email}"
    }
}

// INNER JOIN operation: Retrieve orders with user details
println "\nOrders and Users (INNER JOIN):"
def joinResult = sql.rows("""
SELECT o.id AS order_id, u.name, u.email, o.product, o.amount
FROM orders o
INNER JOIN users u ON o.user_id = u.id
""")
joinResult.each { row ->
    println "Order ID: ${row.order_id}, Name: ${row.name}, Product: ${row.product}, Amount: ${row.amount}"
}

//// RIGHT JOIN operation: Retrieve all users and their orders (even if no orders exist for users)
println "\nUsers and Orders (RIGHT JOIN):"
def rightJoinResult = sql.rows("""
SELECT u.id AS user_id, u.name, u.email, o.product, o.amount
FROM orders o
RIGHT JOIN users u ON o.user_id = u.id
""")
rightJoinResult.each { row ->
    println "User ID: ${row.user_id}, Name: ${row.name}, Product: ${row.product ?: 'No Product'}, Amount: ${row.amount ?: 'N/A'}"
}

// Close the database connection
sql.close()
