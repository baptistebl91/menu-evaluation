import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe ProtocolModel est responsable de la récupération des données de protocole depuis une base de données.
 */
public class ProtocolModel {
    private final Connection connection;

    public ProtocolModel() {
        this.connection = Database.getInstance().getConnection();
    }

    /**
     * Récupère un protocole à partir de sa référence dans la base de données.
     *
     * @param reference La référence du protocole à récupérer.
     * @return Un objet QueryResult contenant le protocole s'il est trouvé, sinon null.
     */
    public QueryResult getProtocolByReference(String reference) {
        try {
            PreparedStatement pstDesc = this.connection.prepareStatement("SELECT * FROM protocole WHERE reference = ?");
            pstDesc.setString(1, reference);
            ResultSet resultSet = pstDesc.executeQuery();

            if (resultSet.next()) { // Vérification du résultat

                int actionId = resultSet.getInt("id_action");
                String description = resultSet.getString("description");
                Protocol protocol = new Protocol(reference, actionId, description);

                return new QueryResult(protocol, true);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return new QueryResult(null, false);
    }
}
