CREATE TABLE Users
(
    user_id         INT IDENTITY PRIMARY KEY,
    username        VARCHAR(15)        NOT NULL,
    password        VARCHAR(30)        NOT NULL,
    date_of_birth   CHAR(8)            NOT NULL,
    email           VARCHAR(50) UNIQUE NOT NULL,
    profile_picture varchar(1000)
);

CREATE TABLE App_Reviews
(
    user_id     INT IDENTITY PRIMARY KEY,
    review_text VARCHAR(1000),
    num_stars   INT,
    CONSTRAINT fk_userid_to_app_review FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE Reviews
(
    review_id   INT IDENTITY PRIMARY KEY,
    user_id     INT,
    review_text VARCHAR(1000),
    num_stars   INT,
    CONSTRAINT fk_userid_to_review FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE Events
(
    event_id    INT IDENTITY PRIMARY KEY,
    description VARCHAR(1000) NOT NULL,
);

CREATE TABLE Categories
(
    event_id INT IDENTITY,
    category VARCHAR(100) NOT NULL CHECK (category IN('PARTY', 'EXHIBITION', 'KIDS', 'FAMILY', 'NEW', 'GAMES')),
    PRIMARY KEY (event_id, category),
    CONSTRAINT FK_event_id_for_category FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Event_Reviews
(
    event_id  INT,
    review_id INT,
    PRIMARY KEY (event_id, review_id),
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES Events (event_id),
    CONSTRAINT fk_review_event FOREIGN KEY (review_id) REFERENCES Reviews (review_id)
);

CREATE TABLE Preferences (
    user_id INT IDENTITY,
    preferences VARCHAR(100) NOT NULL CHECK (preferences IN('PARTY', 'EXHIBITION', 'KIDS', 'FAMILY', 'NEW', 'GAMES')),
    PRIMARY KEY (user_id, preferences),
    CONSTRAINT fk_preferences_for_user FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Points
(
    user_id        INT IDENTITY PRIMARY KEY,
    current_points INTEGER,
    weekly_points  INTEGER,
    monthly_points INTEGER,
    alltime_points INTEGER,
    CONSTRAINT fk_user_points FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE Paintings
(
    painting_id     INT IDENTITY PRIMARY KEY,
    name            VARCHAR(100),
    author          VARCHAR(100),
    painting_image  VARCHAR(100),
    rarity          VARCHAR(50),
    points          INT,
    art_information VARCHAR(1000)
);

CREATE TABLE Paintings_Collected
(
    user_id     INT,
    painting_id INT,
    PRIMARY KEY (user_id, painting_id),
    CONSTRAINT fk_users_painting FOREIGN KEY (user_id) REFERENCES Users (user_id),
    CONSTRAINT fk_painting FOREIGN KEY (painting_id) REFERENCES Paintings (painting_id),
);