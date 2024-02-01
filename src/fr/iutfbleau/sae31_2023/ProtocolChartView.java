import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ProtocolChartView extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private NavigationController navigationController;

    public ProtocolChartView(Protocol protocol, Map<String, Integer> pieChartResults, Map<String, Integer> menuVisitResult) {
        setTitle("Statistiques du protocole sélectionné");
        setSize(700, 700);
        setLocationRelativeTo(null);
        IconPath operaIconPath = new IconPath("operagx");
        ImageIcon img = new ImageIcon(operaIconPath.getIconPath());
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuration du CardLayout et du JPanel principal
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        navigationController = new NavigationController(cardLayout, cardPanel);

        // Ajout du premier graphique en camembert (PieChartPanel)
        PieChartPanel pieChartPanel = new PieChartPanel(pieChartResults, protocol.getDescription());
        cardPanel.add(pieChartPanel, "PieChart");

        // Ajout du second graphique en camembert (MenuVisitChartPanel)
        MenuVisitModel menuVisitModel = new MenuVisitModel();
        Map<String, Integer> menuVisitResults = menuVisitModel.getMenuVisitData(protocol.getReference());
        MenuVisitChartPanel menuVisitChartPanel = new MenuVisitChartPanel(menuVisitResults, "Nombre de sous menu visité", protocol.getDescription());
        cardPanel.add(menuVisitChartPanel, "MenuVisitChart");

        // Configuration du panneau de contrôle pour la navigation entre les graphiques
        JPanel controlPanel = new JPanel();
        JButton btnPrevious = navigationController.createPreviousButton();
        JButton btnNext = navigationController.createNextButton();
        controlPanel.add(btnPrevious);
        controlPanel.add(btnNext);

        // Ajout des composants au JFrame
        add(controlPanel, BorderLayout.SOUTH);
        add(cardPanel, BorderLayout.CENTER);

        // Rendre le JFrame visible
        setVisible(true);
    }


}

