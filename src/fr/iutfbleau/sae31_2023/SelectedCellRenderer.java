import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * La classe SelectedCellRenderer personnalise l'affichage des cases d'un JTree pour
 * que ce soit plus joli et lisible.
 */
public class SelectedCellRenderer extends DefaultTreeCellRenderer {

    private final Protocol currentProtocol;

    private final TreeClickListener treeClickListener;

    /**
     * Constructeur de la classe SelectedCellRenderer qui initialise le protocole actuel et le Listener
     * quand on clique sur le JTree.
     *
     * @param currentProtocol   Le protocole actuellement sélectionné.
     * @param treeClickListener Le listener de clic sur le JTree.
     */
    public SelectedCellRenderer(Protocol currentProtocol, TreeClickListener treeClickListener) {
        this.currentProtocol = currentProtocol;
        this.treeClickListener = treeClickListener;
    }

    /**
     * Personnalise les cases du JTree en fonction des conditions définies.
     *
     * @param tree     L'arbre JTree.
     * @param value    La valeur de la case.
     * @param selected Indique si la case est sélectionnée.
     * @param expanded Indique si la case est développée.
     * @param leaf     Indique si la case est une feuille.
     * @param row      L'index de la ligne de la case.
     * @param hasFocus Indique si la case a le focus.
     * @return Le composant représentant la case personnalisée.
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        IconPath fileIconPath = new IconPath("file");
        IconPath folderIconPath = new IconPath("folder");

        Icon iconFile = new ImageIcon(fileIconPath.getIconPath());
        Icon iconFolder = new ImageIcon(folderIconPath.getIconPath());

        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
        Object userObject = treeNode.getUserObject();

        if (userObject instanceof SubMenu || (userObject instanceof String && userObject.equals("Root"))) {
            setIcon(iconFolder);
        } else {
            setIcon(iconFile);
        }

        if (selected && userObject instanceof Action) {
            Action currentAction = (Action) userObject;
            if (currentAction.getId() == currentProtocol.getActionId()) {
                setBackgroundSelectionColor(Color.GREEN);
                tree.removeTreeWillExpandListener(treeClickListener);
                tree.removeTreeSelectionListener(treeClickListener);
            }
        } else {
            setBackgroundSelectionColor(null);
        }
        return this;
    }
}
