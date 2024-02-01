/**
 * La classe Protocol représente un protocole avec une référence, un identifiant d'action et une description.
 */
public class Protocol {
    private final String reference;

    private final int ActionId;

    private final String description;

    /**
     * Constructeur de la classe Protocol.
     *
     * @param reference   La référence du protocole.
     * @param actionId    L'id de l'action associée au protocole.
     * @param description La description du protocole.
     */
    public Protocol(String reference, int actionId, String description) {
        this.reference = reference;
        this.ActionId = actionId;
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public int getActionId() {
        return ActionId;
    }

    public String getDescription() {
        return description;
    }
}
