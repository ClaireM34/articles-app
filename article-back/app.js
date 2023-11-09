const express = require('express');
const app = express();
const mysql = require('mysql');
const bodyParser = require('body-parser');

app.use(bodyParser.json());

// Configure MySQL connection
const db = mysql.createConnection({
  host: 'localhost',
  user: 'claire',
  password: 'claire',
  database: 'article',
});

db.connect(err => {
  if (err) {
    console.error('Erreur de connexion à la base de données MySQL : ' + err.message);
    process.exit(1);
  }
  console.log('Connecté à la base de données MySQL');
});



// Route GET /articles
app.get('/articles', (req, res) => {
  db.query('SELECT * FROM article', (error, results) => {
    if (error) {
      console.error('Erreur lors de la récupération des articles : ' + error.message);
      res.status(500).json({ error: 'Erreur serveur' });
    } else {
      res.json(results);
    }
  });
});

// Route POST /articles
app.post('/articles', (req, res) => {
  const { libelle, prix } = req.body;
  db.query('INSERT INTO article (libelle, prix) VALUES (?, ?)', [libelle, prix], (error, result) => {
    if (error) {
      console.error('Erreur lors de la création de l\'article : ' + error.message);
      res.status(500).json({ error: 'Erreur serveur' });
    } else {
      res.status(201).json({ message: 'Article créé avec succès' });
    }
  });
});

const port = 3000;
app.listen(port, () => {
  console.log(`Serveur écoutant sur le port ${port}`);
});
