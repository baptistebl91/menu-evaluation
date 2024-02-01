import javax.swing.JOptionPane;

/**
 * La classe AskProtocol permet de demander à l'utilisateur de saisir une référence de protocole via une boîte de dialogue.
 */
public class AskProtocol {

    /**
     * Demande à l'utilisateur de saisir une référence de protocole.
     *
     * @return Un objet de type Protocol représentant le protocole saisi par l'utilisateur.
     */
    public static Protocol askProtocolReference() {

        Protocol protocol = null;

        boolean continueProgram = true;
        String protocolReference;

        while (continueProgram) {
            // Affichage d'une boîte de dialogue pour saisir la référence du protocole
            protocolReference = JOptionPane.showInputDialog(null, "Entrez la référence du protocole souhaité : ", "Choix du protocole", JOptionPane.QUESTION_MESSAGE);

            if (protocolReference == null) {
                System.exit(0); // On quitte si l'utilisateur ferme la fenêtre.
            } else {
                ProtocolModel protocolModel = new ProtocolModel();
                QueryResult queryResult = protocolModel.getProtocolByReference(protocolReference);

                if (queryResult.getSuccess()) {
                    protocol = (Protocol) queryResult.getResult();

                    if (protocol == null) {
                        // Affichage d'un message d'erreur si la référence du protocole n'existe pas.
                        JOptionPane.showMessageDialog(null, "La référence n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        continueProgram = false;
                    }
                }
            }
        }

        return protocol;
    }
}