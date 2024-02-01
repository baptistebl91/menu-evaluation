import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * La classe IconPath gère les chemins d'accès aux icônes du programme.
 */
public class IconPath {

    private final Path iconPath;

    /**
     * Constructeur de la classe IconPath.
     *
     * @param iconName Le nom de l'icône.
     */
    public IconPath(String iconName) {
        try {
            // Récupération du chemin racine
            Path pathToIcons = Paths.get(getClass().getResource("").toURI());
            // Obtention du chemin vers l'icône en utilisant le chemin relatif
            iconPath = pathToIcons.resolve("../icons/" + iconName + ".png");
        } catch (URISyntaxException e) {
            // En cas d'erreur lors de la conversion de l'URI, une exception est levée
            throw new RuntimeException(e);
        }
    }

    /**
     * Récupère le chemin d'accès complet de l'icône.
     *
     * @return Le chemin d'accès complet de l'icône au format de chaîne de caractères.
     */
    String getIconPath() {
        return iconPath.toString();
    }
}
