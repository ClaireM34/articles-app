CREATE TABLE article (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL,
    prix DECIMAL(10, 2) NOT NULL
);

INSERT INTO article (libelle, prix) VALUES
    ('Article 1', 20.50),
    ('Article 2', 29.50),
    ('Article 3', 14.50);