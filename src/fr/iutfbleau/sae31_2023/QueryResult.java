/**
 * La classe QueryResult représente le résultat d'une requête provenant de la base de données,
 * et contient un objet résultant de la requête ainsi qu'une indication de succès ou d'échec de cette requête.
 */
public class QueryResult {
    private final Object result;
    private final boolean success;

    /**
     * Constructeur de la classe QueryResult.
     *
     * @param result  L'objet résultant de la requête.
     * @param success Indique si la requête a réussi ou non.
     */
    public QueryResult(Object result, boolean success) {
        this.result = result;
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public boolean getSuccess() {
        return success;
    }
}
