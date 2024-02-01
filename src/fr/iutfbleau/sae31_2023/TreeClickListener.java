import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * La classe TreeClickListener gère les événements de clic sur les éléments du JTree.
 * Elle implémente les interfaces TreeWillExpandListener et TreeSelectionListener pour gérer
 * les événements d'expansion et de sélection d'un sous-menu (d'un noeud).
 */
public class TreeClickListener implements TreeWillExpandListener, TreeSelectionListener {

    private final ResultModel resultModel = new ResultModel();

    private int visitedSubMenusCounter = 0;

    private final Protocol currentProtocol;

    /**
     * Constructeur permettant d'initialiser le protocole actuel.
     *
     * @param currentProtocol Le protocole actuel.
     */
    public TreeClickListener(Protocol currentProtocol) {
        this.currentProtocol = currentProtocol;
    }

    /**
     * Méthode appelée lorsqu'on déroule le JTree.
     *
     * @param event L'événement qui déroule le JTree.
     */
    @Override
    public void treeWillExpand(TreeExpansionEvent event) {

        TreePath path = event.getPath();
        DefaultMutableTreeNode expandedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        Object associatedObject = expandedNode.getUserObject();

        if (associatedObject instanceof String && associatedObject.equals("Root")) {
            // Noeud racine cliqué, on ne fait rien
            return;
        }

        SubMenu expandedSubMenu = (SubMenu) associatedObject;

        System.out.println("[Sous-menu] Protocol = " + currentProtocol.getReference());
        System.out.println("[Sous-menu] " + expandedSubMenu.getName());
        System.out.println("[Sous-menu] " + path);

        this.visitedSubMenusCounter++;
        System.out.println("Arbre étendu, nombre de clics = " + this.visitedSubMenusCounter);
    }

    /**
     * Méthode appelée lorsque le JTree est en train d'être réduit.
     *
     * @param event L'événement qui réduit le JTree.
     */
    @Override
    public void treeWillCollapse(TreeExpansionEvent event) {

    }

    /**
     * Méthode appelée lorsqu'un nœud est sélectionné dans le JTree.
     *
     * @param event L'événement de sélection d'un nœud dans le JTree.
     */
    @Override
    public void valueChanged(TreeSelectionEvent event) {
        TreePath path = event.getNewLeadSelectionPath();

        if (path == null) {
            return;
        }

        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        Object associatedObject = selectedNode.getUserObject();

        if (associatedObject instanceof String && associatedObject.equals("Root")) {
            // Noeud racine cliqué, on ne fait rien
            return;
        }

        if (!(associatedObject instanceof Action)) {
            return;
        }
        System.out.println("Fini, nombre de clics = " + this.visitedSubMenusCounter);
        Action currentAction = (Action) associatedObject;

        System.out.println("[Action] Protocol = " + currentProtocol.getReference());
        System.out.println("[Action] Name = " + currentAction.getName());
        System.out.println("[Action] Path = " + path);

        if (currentAction.getId() == currentProtocol.getActionId()) {
            System.out.println("Tu as trouvé la bonne action. Le programme va se fermer dans 3 secondes.");

            String referenceProtocol = currentProtocol.getReference();

            resultModel.insertResultat(new Result(referenceProtocol,
                    currentProtocol.getActionId(),
                    MainTests.idSession,
                    1,
                    0,
                    this.visitedSubMenusCounter));

            Timer timer = new Timer(3000, e -> System.exit(0));
            timer.setRepeats(false);
            timer.start();
        } else {
            System.out.println("Mauvaise action. Le programme se ferme.");
            resultModel.insertResultat(
                    new Result(currentProtocol.getReference(),
                            currentProtocol.getActionId(),
                            MainTests.idSession,
                            2,
                            0,
                            this.visitedSubMenusCounter)
            );

            System.exit(1);

        }
    }
}