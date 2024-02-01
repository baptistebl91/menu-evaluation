import javax.swing.*;
import java.util.Map;

public class MainStats {
    public static void main(String[] args) {
        // Demande à l'utilisateur de saisir une référence de protocole
        Protocol protocol = AskProtocol.askProtocolReference();

        if (protocol != null) {
            JOptionPane.showMessageDialog(null,
                    "Référence : " + protocol.getReference() + "\n" +
                            "Description : " + protocol.getDescription(),
                    "Protocole valide",
                    JOptionPane.INFORMATION_MESSAGE);

            ResultModel resultModel = new ResultModel();
            Map<String, Integer> results = resultModel.getProtocolResults(protocol.getReference());

            MenuVisitModel menuVisitModel = new MenuVisitModel();
            // Passez la référence du protocole à getMenuVisitData
            Map<String, Integer> menuVisitResults = menuVisitModel.getMenuVisitData(protocol.getReference());

            // Crée et affiche une vue avec les deux graphiques
            new ProtocolChartView(protocol, results, menuVisitResults);
        }

        Database.getInstance().closeConnection();
    }
}
