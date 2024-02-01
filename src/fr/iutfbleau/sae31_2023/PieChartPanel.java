import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * La classe PieChartPanel représente un panneau affichant un camembert séparé en tranches
 * basé sur les sous-menus visités, accompagné d'une légende qui présente le pourcentage de réussite
 * et d'échec.
 */
public class PieChartPanel extends JPanel {
    private final Map<String, Integer> data;
    private final String description;

    /**
     * Constructeur de la classe PieChartPanel.
     *
     * @param data        Les données à afficher dans le camembert.
     * @param description La description du protocole de test.
     */
    public PieChartPanel(Map<String, Integer> data, String description) {
        this.data = data;
        this.description = description;
    }

    /**
     * Redéfinition de la méthode paintComponent pour réaliser le camembert et la légende.
     *
     * @param g .
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitleAndDescription(g, description);
        drawPieChart(g);
        drawLegend(g);
    }

    /**
     * Affiche le titre et la description du camembert.
     *
     * @param g           Le composant graphique.
     * @param description La description du protocole à afficher.
     */
    private void drawTitleAndDescription(Graphics g, String description) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Taux de réussite au test", 10, 20);

        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(description, 10, 40);
    }

    /**
     * Dessine le camembert en tranches en fonction des sous-menus visités.
     *
     * @param g Le composant graphique.
     */
    private void drawPieChart(Graphics g) {
        int total = data.values().stream().mapToInt(Integer::intValue).sum();
        int startAngle = 0;
    
        int diameter = Math.min(getWidth(), getHeight()) - 100;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
    
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            int arcAngle = (int) Math.round(360.0 * entry.getValue() / total);
            Color sliceColor = getColorFor(entry.getKey());
    
            g.setColor(sliceColor);
            g.fillArc(x, y, diameter, diameter, startAngle, arcAngle);
            startAngle += arcAngle;
        }
    }    

    /**
     * Dessine la légende du camembert.
     *
     * @param g Le composant graphique.
     */
    private void drawLegend(Graphics g) {
        int total = data.values().stream().mapToInt(Integer::intValue).sum();

        String successLabel = "Réussite - 1";
        String failureLabel = "Échec - " + (total - 1);

        g.setColor(new Color(0, 128, 0)); // Vert
        g.fillRect(10, getHeight() - 70, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString(successLabel, 35, getHeight() - 55);

        g.setColor(Color.RED); // Rouge
        g.fillRect(10, getHeight() - 45, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString(failureLabel, 35, getHeight() - 30);
    }

    /**
     * Retourne une couleur spécifique pour chaque sous-menu.
     *
     * @param key La clé pour laquelle obtenir une couleur spécifique.
     * @return La couleur associée à la clé.
     */
    private Color getColorFor(String key) {
        if ("Success".equals(key)) {
            return new Color(0, 128, 0); // Vert pour les réussites
        } else if ("Failure".equals(key)) {
            return Color.RED; // Rouge pour les échecs
        }
        return Color.LIGHT_GRAY; // Couleur par défaut
    }
}
