import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe CheckResultTable comporte des fonctions pour vérifier et interagir avec la table 'resultat' de la BDD.
 */
public class CheckResultTable {
    private final Connection connection;

    public CheckResultTable() {
        this.connection = Database.getInstance().getConnection();
    }

    /**
     * Vérifie si la table 'resultat' est vide pour une référence de protocole donnée.
     *
     * @param referenceProtocol La référence du protocole pour laquelle on vérifie la table.
     * @return true si la table est vide, sinon false.
     */
    public boolean isTableEmpty(String referenceProtocol) {
        String checkEmptyTableQuery = "SELECT COUNT(*) FROM resultat WHERE reference_protocole = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(checkEmptyTableQuery)) {
            preparedStatement.setString(1, referenceProtocol);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int rowCount = resultSet.getInt(1);
                    return rowCount == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Récupère le dernier test effectué pour une référence de protocole donnée.
     *
     * @param referenceProtocol La référence du protocole pour laquelle on veut récupérer le dernier test.
     * @return L'ID de session du dernier test, ou -1 s'il n'y a aucun test.
     */
    public int getLatestTest(String referenceProtocol) {
        String getMaxSessionIdQuery = "SELECT MAX(id_session) FROM resultat WHERE reference_protocole = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getMaxSessionIdQuery)) {
            preparedStatement.setString(1, referenceProtocol);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

}
