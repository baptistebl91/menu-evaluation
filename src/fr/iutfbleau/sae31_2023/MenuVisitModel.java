import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MenuVisitModel {
    private final Connection connection;

    public MenuVisitModel() {
        this.connection = Database.getInstance().getConnection();
    }

    public Map<String, Integer> getMenuVisitData(String protocolReference) {
        Map<String, Integer> results = new HashMap<>();
        try {
            PreparedStatement pst = this.connection.prepareStatement(
                    "SELECT nombre_menu_visite, COUNT(*) AS count FROM resultat WHERE reference_protocole = ? GROUP BY nombre_menu_visite");
            pst.setString(1, protocolReference);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                String menuVisite = resultSet.getString("nombre_menu_visite");
                int count = resultSet.getInt("count");
                results.put(menuVisite, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
