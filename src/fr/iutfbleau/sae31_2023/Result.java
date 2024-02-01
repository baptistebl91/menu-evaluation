/**
 * La classe Result représente les détails spécifiques d'une action effectuée dans le cadre d'un protocole,
 * contenant des informations telles que la référence du protocole, l'identifiant de l'action, l'identifiant de session,
 * le résultat de l'action, le type de l'action et le nombre de sous-menus visités.
 */
public class Result {
    private final String protocolReference;
    private final int idAction;
    private final int idSession;
    private final int resultatAction;
    private final int typeAction;
    private final int visitedSubMenusCount;

    /**
     * Constructeur de la classe Result.
     *
     * @param protocolReference    La référence du protocole associé à cette action.
     * @param idAction             L'id de l'action.
     * @param idSession            L'id de la session lors de l'action.
     * @param resultatAction       Le résultat de l'action.
     * @param typeAction           Le type de l'action effectuée.
     * @param visitedSubMenusCount Le nombre de sous-menus visités lors de cette action.
     */
    public Result(String protocolReference, int idAction, int idSession, int resultatAction, int typeAction, int visitedSubMenusCount) {
        this.protocolReference = protocolReference;
        this.idAction = idAction;
        this.idSession = idSession;
        this.resultatAction = resultatAction;
        this.typeAction = typeAction;
        this.visitedSubMenusCount = visitedSubMenusCount;
    }

    public String getProtocolReference() {
        return protocolReference;
    }

    public int getIdAction() {
        return idAction;
    }

    public int getIdSession() {
        return idSession;
    }

    public int getResultatAction() {
        return resultatAction;
    }

    public int getTypeAction() {
        return typeAction;
    }

    public int getVisitedSubMenusCount() {
        return visitedSubMenusCount;
    }
}
