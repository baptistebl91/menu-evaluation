import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

/**
 * La classe MenuVisitChartPanel représente un menu affichant un camembert représentant le nombre
 * de sous-menus cliqués lors d'un test.
 */
public class MenuVisitChartPanel extends JPanel {
    private final Map<String, Integer> data;
    private final String title;
    private final String description;
    private final Map<String, Color> colorMap;

    /**
     * Constructeur de la classe MenuVisitChartPanel.
     *
     * @param data        Les données à afficher dans le camembert.
     * @param title       Le titre du camembert.
     * @param description La description du camembert.
     */
    public MenuVisitChartPanel(Map<String, Integer> data, String title, String description) {
        this.data = data;
        this.title = title;
        this.description = description;
        this.colorMap = generateColorMap(data.keySet());
    }

    /**
     * Redessine le composant graphique en utilisant le contexte graphique spécifié.
     * Cette méthode appelle les méthodes pour dessiner le titre, le camembert et sa légende.
     *
     * @param g L'objet Graphics utilisé pour redessiner le composant graphique.
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
     * @param description La description du camembert.
     */
    private void drawTitleAndDescription(Graphics g, String description) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Nombre de sous-menus visités", 10, 20);

        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(description, 10, 40);
    }

    /**
     * Dessine le titre et la description du camembert.
     *
     * @param g L'objet Graphics utilisé pour dessiner le titre et la description.
     */
    private void drawTitle(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(title, 10, 20);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(description, 10, 40);
    }

    /**
     * Dessine un camembert représentant les données fournies.
     *
     * @param g L'objet Graphics utilisé pour dessiner le camembert.
     */
    private void drawPieChart(Graphics g) {
        int total = data.values().stream().mapToInt(Integer::intValue).sum();
        int startAngle = 0;

        int diameter = Math.min(getWidth(), getHeight()) - 100;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        for (Entry<String, Integer> entry : data.entrySet()) {
            int arcAngle = (int) Math.round(360.0 * entry.getValue() / total);
            g.setColor(colorMap.get(entry.getKey()));
            g.fillArc(x, y, diameter, diameter, startAngle, arcAngle);
            startAngle += arcAngle;
        }


    }

    /**
     * Génère un jeu de couleurs associée à chaque clé fournie.
     *
     * @param keys Un ensemble de clés pour lesquelles les couleurs doivent être générées.
     * @return Un jeu associant chaque clé à une couleur correspondante.
     */
    private Map<String, Color> generateColorMap(Set<String> keys) {
        Map<String, Color> map = new HashMap<>();
        Random rand = new Random();
        for (String key : keys) {
            if (key.equals("Réussite")) {
                map.put(key, Color.GREEN);
            } else {
                // Génère une couleur aléatoire pour chaque clé
                map.put(key, new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            }
        }
        return map;
    }

    /**
     * Dessine une légende pour les données fournies sur le camembert.
     *
     * @param g L'objet Graphics utilisé pour dessiner la légende.
     */
    private void drawLegend(Graphics g) {
        int total = data.values().stream().mapToInt(Integer::intValue).sum();
        int y = getHeight() - (data.size() * 30 + 20); // Ajuster la position de départ de la légende

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            g.setColor(colorMap.get(entry.getKey()));
            g.fillRect(10, y, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(entry.getValue() + " tests de " + entry.getKey(), 35, y + 15);
            y += 30;
        }
    }

    private Color getRandomColor() {
        // Generates a random color for each segment
        return new Color((int) (Math.random() * 0x1000000));
    }
}
