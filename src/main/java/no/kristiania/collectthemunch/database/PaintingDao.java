package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Painting;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static no.kristiania.collectthemunch.entities.Rarity.validateRarityEnum;

public class PaintingDao extends AbstractDao {

    @Inject
    public PaintingDao(DataSource dataSource) {
        super(dataSource);
    }

    public Boolean save(Painting painting) throws SQLException {
        //if (validateRarityEnum(painting.getRarity())) {

            try (var connection = dataSource.getConnection()) {
                String query = "INSERT INTO Paintings (name, author, painting_image, art_information, rarity, points) VALUES (?, ?, ?, ?, ?, ?)";

                try (var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, painting.getName());
                    statement.setString(2, painting.getAuthor());
                    statement.setBytes(3, painting.getPaintingImage());
                    statement.setString(4, painting.getArtInformation());
                    statement.setString(5, painting.getRarity());
                    statement.setInt(6, painting.getPoints());


                    statement.executeUpdate();
                    try (var generatedKeys = statement.getGeneratedKeys()) {
                        generatedKeys.next();
                        painting.setPaintingId(generatedKeys.getInt(1));
                    }
                    return true;
                }
            }
        //}
    }

    private void convertByteArrayToImage(byte[] image) {

    }

    public List<Painting> retrieveAll() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Paintings";

            try (var statement = connection.prepareStatement(query)) {
                try (var resultSet = statement.executeQuery()) {
                    List<Painting> paintings = new ArrayList<>();

                    while (resultSet.next()) {
                        var painting = mapFromResultSet(resultSet);
                        paintings.add(painting);
                    }
                    return paintings;
                }
            }
        }
    }

    public List<Painting> retrieveAllForUser(int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = """
                    SELECT *
                    FROM Paintings
                    JOIN Paintings_Collected
                        ON Paintings.painting_id = Paintings_Collected.painting_id
                    JOIN Users
                        ON Users.user_id = Paintings_Collected.user_id
                    WHERE Users.user_id = ?
                    """;

            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                try (var resultSet = statement.executeQuery()) {
                    List<Painting> paintings = new ArrayList<>();

                    while (resultSet.next()) {
                        var painting = mapFromResultSet(resultSet);
                        paintings.add(painting);
                    }
                    return paintings;
                }
            }
        }
    }

    public void saveToInventory(int userId, int paintingId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Paintings_Collected (user_id, painting_id) VALUES (?, ?)";

            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setInt(2, paintingId);
                statement.executeUpdate();
            }
        }
    }

    public Painting retrieve(int paintingId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Paintings WHERE painting_id = ?";

            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, paintingId);

                return getPainting(statement);
            }
        }
    }

    public Painting retrieve(String name) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Paintings WHERE name = ?";

            try (var statement = connection.prepareStatement(query)) {
                statement.setString(1, name);

                return getPainting(statement);
            }
        }
    }

    private Painting getPainting(PreparedStatement statement) throws SQLException {
        try (var resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return mapFromResultSet(resultSet);
            } else {
                return null;
            }
        }
    }

    private Painting mapFromResultSet(ResultSet resultSet) throws SQLException {
        var painting = new Painting();

        painting.setPaintingId(resultSet.getInt("painting_id"));
        painting.setName(resultSet.getString("name"));
        painting.setAuthor(resultSet.getString("author"));
        painting.setPaintingImage(resultSet.getBytes("painting_image"));
        painting.setArtInformation(resultSet.getString("art_information"));
        painting.setRarity(resultSet.getString("rarity"));
        painting.setPoints(resultSet.getInt("points"));

        return painting;
    }

}
