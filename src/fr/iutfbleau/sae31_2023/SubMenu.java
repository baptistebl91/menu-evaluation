/**
 * La classe SubMenu représente un sous-menu dans la hiérarchie des menus.
 * Chaque sous-menu est identifié par un ID unique, a un nom et est associé à un ID de sous-menu parent.
 */
public class SubMenu {
    private final int id;
    private final String name;
    private final int parentId;

    /**
     * Constructeur de la classe SubMenu initialisant un sous-menu avec un ID, un nom et un ID de sous-menu parent.
     *
     * @param id       L'identifiant unique du sous-menu.
     * @param name     Le nom du sous-menu.
     * @param parentId L'identifiant du sous-menu parent auquel ce sous-menu est associé.
     */
    public SubMenu(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
