/**
 * La classe Action représente une action dans un menu.
 */
public class Action {

    private final int id;
    private final String name;
    private final int parentSubMenuId;

    /**
     * Constructeur de la classe Action.
     *
     * @param id              L'identifiant de l'action.
     * @param name            Le nom de l'action.
     * @param parentSubMenuId L'identifiant du sous-menu parent de l'action.
     */
    public Action(int id, String name, int parentSubMenuId) {
        this.id = id;
        this.name = name;
        this.parentSubMenuId = parentSubMenuId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParentSubMenuId() {
        return parentSubMenuId;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
