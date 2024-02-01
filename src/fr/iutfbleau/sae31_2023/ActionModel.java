import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe ActionModel représente un modèle pour interagir avec les actions dans la base de données.
 */
public class ActionModel {

    private final Connection connection;

    public ActionModel() {
        this.connection = Database.getInstance().getConnection();
    }

    /**
     * Récupère les actions associées à un identifiant de sous-menu parent donné.
     *
     * @param actionSubMenuId L'identifiant du sous-menu parent des actions à récupérer.
     * @return Un objet QueryResult contenant une liste d'objets Action et un indicateur de réussite de la requête.
     */
    public QueryResult getActionByParentSubMenuId(int actionSubMenuId) {

        List<Action> actions = new ArrayList<>();

        try {
            // Récupérer toutes les actions associées au sous-menu parent donné
            PreparedStatement pstMenus = this.connection.prepareStatement("SELECT * FROM action WHERE sous_menu_pere = ?");
            pstMenus.setString(1, String.valueOf(actionSubMenuId));
            ResultSet rsMenus = pstMenus.executeQuery();

            while (rsMenus.next()) {

                // Extraire les informations de chaque action
                int actionId = rsMenus.getInt("id");
                String actionName = rsMenus.getString("nom_action");
                int parentSubMenuId = rsMenus.getInt("sous_menu_pere");

                // Créer un objet Action et l'ajouter à la liste d'actions
                actions.add(new Action(actionId, actionName, parentSubMenuId));
            }
            return new QueryResult(actions, true); // Retourner un objet QueryResult contenant les actions récupérées

        } catch (SQLException e) {
            System.err.println(e.getMessage()); // Print les erreurs SQL dans le terminal
        }
        return new QueryResult(null, false); // Retourner un objet QueryResult indiquant un échec de la requête
    }
}
