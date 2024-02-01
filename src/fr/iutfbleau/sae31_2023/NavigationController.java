import javax.swing.*;
import java.awt.*;

/**
 * La classe NavigationController contrôle la navigation entre les différentes vues à l'aide d'un CardLayout.
 */
public class NavigationController {

    // Le CardLayout utilisé pour la navigation.
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    /**
     * Constructeur de la classe NavigationController.
     *
     * @param cardLayout Le CardLayout utilisé pour défiler les camemberts.
     * @param cardPanel  Le JPanel contenant les CardLayout.
     */
    public NavigationController(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    }

    /**
     * Crée une flèche pour passer au camembert dans le CardLayout.
     *
     * @return La flèche pour passer au camembert suivant.
     */
    public JButton createNextButton() {
        JButton nextButton = new JButton("->");
        nextButton.addActionListener(e -> cardLayout.next(cardPanel)); // Passe à la vue suivante lors du clic
        return nextButton;
    }

    /**
     * Crée un flèche pour revenir au camembert précédent dans le CardLayout.
     *
     * @return La flèche pour revenir au camembert précédent.
     */
    public JButton createPreviousButton() {
        JButton previousButton = new JButton("<-");
        // Revient au camembert précédent lors d'un clic
        previousButton.addActionListener(e -> cardLayout.previous(cardPanel));
        return previousButton;
    }
}
