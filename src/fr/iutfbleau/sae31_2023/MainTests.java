import javax.swing.*;

/**
 * La classe MainTests est responsable du démarrage des tests pour un protocole spécifique.
 */
public class MainTests {

    // Identifiant de session initialisé à 1.
    public static int idSession = 1;

    /**
     * La fonction main qui démarre les tests pour un protocole donné.
     *
     * @param args Arguments de ligne de commande (non utilisés puisque le protocole est demandé dans une pop-up).
     */
    public static void main(String[] args) {

        // Demande à l'utilisateur de saisir une référence de protocole
        Protocol protocol = AskProtocol.askProtocolReference();

        // Affiche la référence du protocole saisie par l'utilisateur
        System.out.println("Référence du protocole entrée par l'utilisateur : " + protocol.getReference());

        // Vérifie s'il existe des résultats précédents pour ce protocole dans la table 'resultat'
        CheckResultTable checkResultTable = new CheckResultTable();

        if (!checkResultTable.isTableEmpty(protocol.getReference())) {
            // Si la table n'est pas vide, met à jour l'id de session pour le prochain test
            idSession = checkResultTable.getLatestTest(protocol.getReference()) + 1;
        }

        // Affiche la description de la tâche liée au protocole
        JOptionPane.showMessageDialog(null, protocol.getDescription(), "Description de la tâche",
                JOptionPane.INFORMATION_MESSAGE);

        // Affiche le menu pour commencer les tests concernant le protocole.
        new ShowMenu(protocol);
    }
}
