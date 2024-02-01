import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;

/**
 * La classe ShowMenu crée une JFrame pour afficher la hiérarchie des menus et des actions liés à un protocole.
 * Elle utilise un JTree pour présenter les sous-menus et les actions sous forme d'une structure arborescente.
 */
public class ShowMenu {
    private final JTree tree;
    private final DefaultTreeModel treeModel;

    private final TreeClickListener treeClickListener;

    public ShowMenu(Protocol currentProtocol) {
        this.treeClickListener = new TreeClickListener(currentProtocol);

        JFrame frame = new JFrame("Opera GX");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        IconPath operaIconPath = new IconPath("operagx");
        ImageIcon img = new ImageIcon(operaIconPath.getIconPath());
        frame.setIconImage(img.getImage());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setCellRenderer(new SelectedCellRenderer(currentProtocol, this.treeClickListener));


        loadMenus(root);

        frame.add(new JScrollPane(tree));
        frame.setVisible(true);
    }

    /**
     * Charge le menu root et les sous-menus dans le JTree.
     *
     * @param root Le root du JTree (son noeud racine).
     */
    private void loadMenus(DefaultMutableTreeNode root) {

        SubMenuModel subMenuModel = new SubMenuModel();
        QueryResult queryResult = subMenuModel.getSubMenusWithoutParents();

        List<SubMenu> subMenusWithoutParent = (List<SubMenu>) queryResult.getResult();
        boolean success = queryResult.getSuccess();

        if (success) {

            for (SubMenu subMenuWithoutParent : subMenusWithoutParent) {

                DefaultMutableTreeNode menuNode = new DefaultMutableTreeNode(subMenuWithoutParent);
                root.add(menuNode);

                loadChildMenus(menuNode, subMenuWithoutParent.getId()); // Chargement récursif des sous-menus
            }

        }
        // Inform the model that the structure has changed to update the view
        treeModel.reload();
        tree.addTreeWillExpandListener(treeClickListener);
        tree.addTreeSelectionListener(treeClickListener);
    }

    /**
     * Charge les sous-menus et les actions correspondant à un sous-menu spécifique dans le JTree.
     *
     * @param parentNode      Le sous-menu parent du JTree pour lequel charger les sous-menus et les actions.
     * @param parentSubMenuId L'id du sous-menu parent pour charger ses sous-menus et ses actions associées.
     */
    private void loadChildMenus(DefaultMutableTreeNode parentNode, int parentSubMenuId) {
        SubMenuModel subMenuModel = new SubMenuModel();
        QueryResult queryResult = subMenuModel.getSubMenuByParentId(parentSubMenuId);

        // Chargement des sous-menus
        List<SubMenu> subMenus = (List<SubMenu>) queryResult.getResult();
        boolean success = queryResult.getSuccess();

        if (success) {
            for (SubMenu subMenu : subMenus) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(subMenu);
                parentNode.add(childNode);
                loadChildMenus(childNode, subMenu.getId()); // Chargement récursif des sous-menus
            }

            // Chargement des actions
            loadActions(parentNode, parentSubMenuId);
        }
    }

    /**
     * Charge les actions pour un sous-menu spécifique de l'arbre.
     *
     * @param parentNode      Le sous-menu parent du JTree pour lequel charger les actions.
     * @param parentSubMenuId L'id du sous-menu parent pour charger ses actions associées.
     */
    private void loadActions(DefaultMutableTreeNode parentNode, int parentSubMenuId) {
        ActionModel actionModel = new ActionModel();
        QueryResult queryResult = actionModel.getActionByParentSubMenuId(parentSubMenuId);

        List<Action> actions = (List<Action>) queryResult.getResult();
        boolean success = queryResult.getSuccess();

        if (success) {
            for (Action action : actions) {
                parentNode.add(new DefaultMutableTreeNode(action));
            }
        }
    }
}
