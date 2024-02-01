import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe ResultModel contient des méthodes pour interagir avec la base de données afin de récupérer les résultats
 * associés à un protocole spécifique et ainsi pouvoir insérer de nouveaux résultats dans la table 'resultat'.
 */
public class ResultModel {

    private final Connection connection;

    public ResultModel() {
        this.connection = Database.getInstance().getConnection();
    }

    /**
     * Récupère les résultats associés à un protocole spécifique.
     *
     * @param protocolReference La référence du protocole pour lequel récupérer les résultats.
     * @return Un objet Map avec les résultats (nombre de succès et d'échecs).
     */

    public Map<String, Integer> getProtocolResults(String protocolReference) {
        Map<String, Integer> results = new HashMap<>();
        results.put("Success", 0);
        results.put("Failure", 0);

        try {
            PreparedStatement pst = this.connection.prepareStatement(
                    "SELECT resultat_action, COUNT(*) AS count FROM resultat WHERE reference_protocole = ? GROUP BY resultat_action");
            pst.setString(1, protocolReference);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                int actionResult = resultSet.getInt("resultat_action");
                int count = resultSet.getInt("count");

                if (actionResult == 1) {
                    results.put("Success", count);
                } else {
                    results.put("Failure", count);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Insère un nouveau résultat dans la table 'resultat'.
     *
     * @param result L'objet Result à insérer dans la base de données.
     */

    public void insertResultat(Result result) {
        try {
            PreparedStatement pstDesc = this.connection.prepareStatement("INSERT INTO `resultat`(`reference_protocole`, `id_action`, `id_session`, `resultat_action`, `type_action`, `nombre_menu_visite`) VALUES (?, ?, ?, ?, ?, ?)");

            // Ajouter les valeurs à la requête
            pstDesc.setString(1, result.getProtocolReference());
            pstDesc.setInt(2, result.getIdAction());
            pstDesc.setInt(3, result.getIdSession());
            pstDesc.setInt(4, result.getResultatAction());
            pstDesc.setInt(5, result.getTypeAction());
            pstDesc.setInt(6, result.getVisitedSubMenusCount());

            // Ajouter la requête à la batch
            pstDesc.addBatch();

            // Exécuter la batch
            pstDesc.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
