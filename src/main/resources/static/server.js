const express = require("express");
const cors = require("cors");
const mysql = require("mysql2");

const app = express();

app.use(cors());
app.use(express.json());

// ✅ MySQL Connection (your details included)
const db = mysql.createConnection({
  host: "localhost",              // no http:// needed
  user: "root",
  password: "karan@9867",
  database: "sevabharat_db"
});

// ✅ Connect to MySQL
db.connect(err => {
  if (err) {
    console.error("❌ Database connection failed:", err.message);
    return;
  }
  console.log("✅ Connected to MySQL Database");
});

// ✅ Example route to test connection
app.get("/api/users", (req, res) => {
  const query = "SELECT * FROM users"; // change 'users' if your table name is different
  db.query(query, (err, results) => {
    if (err) {
      console.error("❌ Query error:", err.message);
      return res.status(500).json({ message: "Database query failed" });
    }
    res.json(results);
  });
});

// ✅ Start Server
const PORT = 8081;
app.listen(PORT, () => console.log(`🚀 Server running on port ${PORT}`));
