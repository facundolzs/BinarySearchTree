package tree;

public class BST {

    private Node root;

    private class Node {

        private int data;
        private Node leftChild;
        private Node rightChild;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Access root node from another class
     *
     * @return root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Check if the tree has elements
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert a new node
     *
     * @param value
     */
    public void insert(int value) {

        Node newNode = new Node(value);

        if (root == null) { // isEmpty() method doesn't work here!
            root = newNode;
        } else {

            Node aux = root;
            Node parent;

            while (true) {
                parent = aux;
                if (value < aux.data) {
                    aux = aux.leftChild;
                    if (aux == null) { // an empty space was found for the new node
                        parent.leftChild = newNode; // the new node is assigned in the left branch
                        return;
                    }
                } else {
                    aux = aux.rightChild;
                    if (aux == null) {
                        parent.rightChild = newNode; // the new node is assigned in the right branch
                        return;
                    }
                }
            }
        }
    }

    /**
     * Find a node by its value
     *
     * @param nodeValue
     * @return
     */
    public Node Search(int nodeValue) {

        Node aux = root;

        while (aux.data != nodeValue) {

            if (nodeValue < aux.data) {
                aux = aux.leftChild; // we should look on the left side...
            } else {
                aux = aux.rightChild; // we should look on the right side...
            }

            if (aux == null) {
                return null; // value not found!
            }
        }
        return aux;
    }

    /**
     * Gets a replacement node for delete(int) method
     *
     * @param nodeToReplace
     * @return
     */
    private Node getReplacement(Node nodeToReplace) {

        Node parentReplacement = nodeToReplace;
        Node replacement = nodeToReplace;
        Node aux = nodeToReplace.rightChild;

        while (aux != null) {
            parentReplacement = replacement;
            replacement = aux;
            aux = aux.leftChild;
        }

        if (replacement != nodeToReplace.rightChild) {
            parentReplacement.leftChild = replacement.rightChild;
            replacement.rightChild = nodeToReplace.rightChild;
        }

        return replacement;
    }

    /**
     * Deletes a node from the tree
     *
     * @param nodeValue
     * @return true if the node was deleted
     */
    public boolean delete(int nodeValue) {

        Node aux = root;
        Node parent = root;
        boolean is_a_leftChild = true; // true if a left child; false if a right child

        while (aux.data != nodeValue) {
            parent = aux;

            if (nodeValue < aux.data) {
                is_a_leftChild = true;
                aux = aux.leftChild;

            } else {
                is_a_leftChild = false;
                aux = aux.rightChild;
            }

            if (aux == null) {
                return false;
            }

        } // end while

        if (aux.leftChild == null && aux.rightChild == null) {

            if (aux == root) {
                root = null;
            } else if (is_a_leftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }

        } else if (aux.rightChild == null) {

            if (aux == root) {
                root = aux.leftChild;
            } else if (is_a_leftChild) {
                parent.leftChild = aux.leftChild;
            } else {
                parent.rightChild = aux.leftChild;
            }

        } else if (aux.leftChild == null) {

            if (aux == root) {
                root = aux.rightChild;
            } else if (is_a_leftChild) {
                parent.rightChild = aux.rightChild;
            } else {
                parent.leftChild = aux.rightChild;
            }

        } else { // the node to be deleted has two child nodes...
            // so we should look for a replacement node
            Node replacement = getReplacement(aux);

            if (aux == root) {
                root = replacement;
            } else if (is_a_leftChild) {
                parent.leftChild = replacement;
            } else {
                parent.rightChild = replacement;
            }

            replacement.leftChild = aux.leftChild;
        }

        return true;
    }

    /**
     * Recursively gets the maximum value in the tree
     *
     * @param root value
     * @return
     */
    public int getMaxValue(Node root) {

        if (root.rightChild == null) { // obviously, maximum value will be on the right side 
            return root.data;
        }

        return getMaxValue(root.rightChild);
    }

    /**
     * Recursively gets the minimum value in the tree
     *
     * @param root value
     * @return
     */
    public int getMinValue(Node root) {

        if (root.leftChild == null) { // minimum value will be on the left 
            return root.data;
        }

        return getMinValue(root.leftChild);
    }

    /**
     * Print BST using Inorder traversal (left - root - right)
     *
     * @param root
     */
    public void inOrderTraversal(Node root) {

        if (root != null) {
            inOrderTraversal(root.leftChild);
            System.out.print(root.data + " | ");
            inOrderTraversal(root.rightChild);
        }
    }

    /**
     * Print BST using Preorder traversal (root - left - right)
     *
     * @param root
     */
    public void preOrderTraversal(Node root) {

        if (root != null) {
            System.out.print(root.data + " | ");
            preOrderTraversal(root.leftChild);
            preOrderTraversal(root.rightChild);
        }
    }

    /**
     * Print BST using Postorder traversal (left - right - root)
     *
     * @param root
     */
    public void postOrderTraversal(Node root) {

        if (root != null) {
            postOrderTraversal(root.leftChild);
            postOrderTraversal(root.rightChild);
            System.out.print(root.data + " | ");
        }
    }

}
