package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.entities.Painting;
import no.kristiania.collectthemunch.entities.Rarity;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintingDao extends AbstractDao{

    public PaintingDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Painting painting) throws SQLException {
        if (validateRarityEnum(painting.getRarity())) {

            try (var connection = dataSource.getConnection()) {
                String query = "INSERT INTO Paintings (title, author, painting_image, art_information, rarity, points) VALUES (?, ?, ?, ?, ?, ?)";

                try (var statement = connection.prepareStatement(query )) {
                    statement.setString(1, painting.getTitle());
                    statement.setString(2, painting.getAuthor());
                    statement.setBytes(3, painting.getPaintingImage());
                    statement.setString(4, painting.getArtInformation());
                    statement.setString(5, painting.getRarity());
                    statement.setInt(6, painting.getPaintingId());
                }
            }
        }
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
            String query = "SELECT * FROM Paintings_Collected WHERE user_id = ?";

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

    public Painting retrieve(int paintingId) {
        return null;
    }

    public Painting retrieve(String title) {
        return null;
    }

    private Painting mapFromResultSet(ResultSet resultSet) throws SQLException {
        var painting = new Painting();

        painting.setPaintingId(resultSet.getInt("painting_id"));
        painting.setTitle(resultSet.getString("title"));
        painting.setAuthor(resultSet.getString("author"));
        painting.setPaintingImage(resultSet.getBytes("painting_image"));
        painting.setArtInformation(resultSet.getString("art_information"));
        painting.setRarity(resultSet.getString("rarity"));
        painting.setPoints(resultSet.getInt("points"));

        return painting;
    }

    private boolean validateRarityEnum(String rarity) {
        for (Rarity r : Rarity.values()) {
            if (r.name().equals(rarity.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}
