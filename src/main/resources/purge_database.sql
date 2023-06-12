-- Drop foreign key constraint in Event_Reviews table
ALTER TABLE Categories
DROP CONSTRAINT FK_event_id_for_category;
ALTER TABLE Preferences
DROP CONSTRAINT fk_preferences_for_user;
ALTER TABLE Points
DROP CONSTRAINT fk_user_points;
ALTER TABLE Paintings_Collected
DROP CONSTRAINT fk_users_painting;

-- Drop tables
DROP TABLE Paintings_Collected;
DROP TABLE Points;
DROP TABLE Preferences;
DROP TABLE Categories;
DROP TABLE Event_Reviews;
DROP TABLE App_Reviews;
DROP TABLE Events;
DROP TABLE Paintings;

-- Drop table Users
DROP TABLE Users;
DROP TABLE dbo.flyway_schema_history;

CREATE TABLE flyway_schema_history (
   installed_rank INT NOT NULL,
   version VARCHAR(50),
   description VARCHAR(200) NOT NULL,
   type VARCHAR(20) NOT NULL,
   script VARCHAR(1000) NOT NULL,
   checksum INT,
   installed_by VARCHAR(100) NOT NULL,
   installed_on DATETIME DEFAULT GETDATE() NOT NULL,
   execution_time INT NOT NULL,
   success BIT NOT NULL
);