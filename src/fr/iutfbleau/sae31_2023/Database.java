import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La classe Database gère la connexion à la base de données MariaDB.
 */
public class Database {

    private static final String DB_URL = "jdbc:mariadb://dwarves.iut-fbleau.fr/ammara";
    private static final String DB_USER = "ammara";
    private static final String DB_PASSWORD = "ayoubammara";

    private static Database instance;

    private Connection connection;

    // Constructeur privé pour empêcher l'instanciation externe de Database.
    private Database() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); // Chargement du pilote JDBC
        } catch (ClassNotFoundException e) {
            System.err.println("Driver class not found: " + e.getMessage());
            System.exit(1);
        }

        try {
            // Établissement de la connexion à la base de données
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("SQL problem: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Retourne une instance unique de Database en suivant le modèle de conception Singleton.
     *
     * @return L'instance unique de la classe Database.
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Ferme la connexion à la base de données si elle est ouverte.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
