package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import no.kristiania.collectthemunch.entities.Painting;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaintingDao extends AbstractDao {

    @Inject
    public PaintingDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Painting painting) throws SQLException, ItemNotSavedException {
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
                    if (generatedKeys.next()) {
                        painting.setPaintingId(generatedKeys.getInt(1));
                    } else {
                        throw new ItemNotSavedException("Could not save painting id " + painting.getPaintingId());
                    }
                }
            }
        }
    }

    private void convertByteArrayToImage(byte[] image) {

    }

    public List<Painting> retrieveAllPaintings() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Paintings";
            try (var statement = connection.prepareStatement(query)) {
                try (var resultSet = statement.executeQuery()) {
                    List<Painting> result = new ArrayList<>();
                    while (resultSet.next()) {
                        result.add(mapFromResultSet(resultSet));
                    }
                    if(result.isEmpty()) {
                        throw new NotFoundException("No paintings found in database");
                    }
                    return result;
                }
            }
        }
    }

    public List<Painting> retrieveAllPaintingsByUserId(int userId) throws SQLException {
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
                    List<Painting> result = new ArrayList<>();
                    while (resultSet.next()) {
                        result.add(mapFromResultSet(resultSet));
                    }
                    if (result.isEmpty()) {
                        throw new NotFoundException("No paintings found for user id " + userId);
                    }
                    return result;
                }
            }
        }
    }

    public void saveToInventory(int userId, int paintingId) throws SQLException, ItemNotSavedException {
        try (var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Paintings_Collected (user_id, painting_id) VALUES (?, ?)";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setInt(2, paintingId);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new ItemNotSavedException("Could not save painting id " + paintingId + " to inventory of user id " + userId);
                }
            }
        }
    }

    public Painting retrievePaintingById(int paintingId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Paintings WHERE painting_id = ?";
            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, paintingId);
                return getPainting(statement);
            }
        }
    }

    public Painting retrievePaintingByName(String name) throws SQLException {
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
                throw new NotFoundException("Could not find painting in database");
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
