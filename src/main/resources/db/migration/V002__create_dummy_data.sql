INSERT INTO Users (username, password, date_of_birth, email)
VALUES ('johnsmith', 'password123', 10091985, 'johnsmith@example.com');

INSERT INTO Users (username, password, date_of_birth, email)
VALUES ('janedoe', 'pass456', 18031992, 'janedoe@example.com');

INSERT INTO Users (username, password, date_of_birth, email)
VALUES ('michaelbrown', 'secret789', 22071989, 'michaelbrown@example.com');

INSERT INTO Users (username, password, date_of_birth, email)
VALUES ('emilywilson', 'p@ssw0rd', 05121997, 'emilywilson@example.com');

INSERT INTO Users (username, password, date_of_birth, email)
VALUES ('davidlee', 'david123', 28111983, 'davidlee@example.com');



-- Paintings
INSERT INTO Paintings (name, author, painting_image, rarity, points, art_information)
VALUES ('The Starry Night', 'Vincent van Gogh', 0x010203, 'EPIC', 500, 'The Starry Night is an oil on canvas painting...');

INSERT INTO Paintings (name, author, painting_image, rarity, points, art_information)
VALUES ('Mona Lisa', 'Leonardo da Vinci', 0x040506, 'LEGENDARY', 1000, 'Mona Lisa is a half-length portrait...');

INSERT INTO Paintings (name, author, painting_image, rarity, points, art_information)
VALUES ('The Scream', 'Edvard Munch', 0x070809, 'LEGENDARY', 800, 'The Scream is the popular name...');

INSERT INTO Paintings (name, author, painting_image, rarity, points, art_information)
VALUES ('Girl with a Pearl Earring', 'Johannes Vermeer', 0x0A0B0C, 'RARE', 700, 'Girl with a Pearl Earring is an oil painting...');

INSERT INTO Paintings (name, author, painting_image, rarity, points, art_information)
VALUES ('The Persistence of Memory', 'Salvador Dalí', 0x0D0E0F, 'COMMON', 600, 'The Persistence of Memory is a surrealist painting...');


-- Events
INSERT INTO Events (name, description, poster)
VALUES ('Art Jam: Express Yourself', 'Join us for an interactive art jam session where you can unleash your creativity...', 0x040506);

INSERT INTO Events (name, description, poster)
VALUES ('Street Art Workshop: Spray & Stencil', 'Learn the techniques of street art and create your own vibrant graffiti masterpiece...', 0x040506);

INSERT INTO Events (name, description, poster)
VALUES ('Glow-in-the-Dark Paint Party', 'Experience the ultimate glow-in-the-dark paint party with neon colors, blacklights, and live music...', 0x040506);

INSERT INTO Events (name, description, poster)
VALUES ('Digital Art Masterclass', 'Discover the world of digital art and learn how to create stunning artworks using digital tools...', 0x040506);

INSERT INTO Events (name, description, poster)
VALUES ('Art Battle: Live Painting Competition', 'Witness an exciting live painting competition where artists compete against the clock to create captivating artworks...', 0x040506);
