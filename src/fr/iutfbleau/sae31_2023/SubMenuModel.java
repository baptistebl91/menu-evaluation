import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe SubMenuModel gère l'accès aux données des sous-menus depuis la base de données.
 * Elle permet de récupérer les sous-menus sans parents et les sous-menus associés à un parent spécifique.
 */
public class SubMenuModel {

    private final Connection connection;

    public SubMenuModel() {
        this.connection = Database.getInstance().getConnection();
    }

    /**
     * Récupère les sous-menus n'ayant pas de parent (sous-menus de base).
     *
     * @return Un objet QueryResult contenant une liste des sous-menus sans parents si la requête est un succès,
     * sinon false.
     */
    public QueryResult getSubMenusWithoutParents() {

        List<SubMenu> parentSubMenus = new ArrayList<>();

        try {
            // Récupérer tous les menus de base (menus sans parent)
            PreparedStatement pstMenus = this.connection.prepareStatement("SELECT * FROM sous_menu WHERE sous_menu_pere IS NULL");
            ResultSet rsMenus = pstMenus.executeQuery();

            while (rsMenus.next()) {

                int subMenuId = rsMenus.getInt("id");
                String subMenuName = rsMenus.getString("nom_sous_menu");
                int parentId = rsMenus.getInt("sous_menu_pere");

                parentSubMenus.add(new SubMenu(subMenuId, subMenuName, parentId));
            }
            return new QueryResult(parentSubMenus, true);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return new QueryResult(null, false);
    }

    /**
     * Récupère les sous-menus associés à un parent spécifique.
     *
     * @param parentSubMenuId L'identifiant du sous-menu parent pour lequel récupérer les sous-menus associés.
     * @return Un objet QueryResult contenant une liste des sous-menus associés au parent spécifié si la requête
     * est un succès, sinon false.
     */
    public QueryResult getSubMenuByParentId(int parentSubMenuId) {

        List<SubMenu> parentSubMenus = new ArrayList<>();

        try {
            // Récupérer tous les menus de base (menus sans parent)
            PreparedStatement pstMenus = this.connection.prepareStatement("SELECT * FROM sous_menu WHERE sous_menu_pere = ?");
            pstMenus.setString(1, String.valueOf(parentSubMenuId));
            ResultSet rsMenus = pstMenus.executeQuery();

            while (rsMenus.next()) {

                int subMenuId = rsMenus.getInt("id");
                String subMenuName = rsMenus.getString("nom_sous_menu");
                int parentId = rsMenus.getInt("sous_menu_pere");

                parentSubMenus.add(new SubMenu(subMenuId, subMenuName, parentId));
            }
            return new QueryResult(parentSubMenus, true);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return new QueryResult(null, false);
    }
}
