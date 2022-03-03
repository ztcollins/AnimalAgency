// --== CS400 Project One File Header ==--
// Name: Zachary Collins
// Email: ztcollins@wisc.edu
// Team: Red
// Group: CH
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: Borrowed implementation from Project 2: Red-Black
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

// interface (implemented with proposal and updated)
interface AnimalAgencyBackendInterface<AnimalInterface> {
    public boolean add(AnimalInterface data) throws NullPointerException, IllegalArgumentException;
    public LinkedList search(String species);
    public int size();
    public boolean isEmpty();
    //public String printAnimal();
    //public boolean adopt(Animal animal);
    public boolean adopt(String species, int age, String name);
}

class AnimalAgencyBackEndPlaceholder implements AnimalAgencyBackendInterface<AnimalInterface> {


	@Override
	public boolean add(AnimalInterface data) throws NullPointerException, IllegalArgumentException {
		System.out.println("ADD PLACEHOLDER METHOD CALLED. RETURNING FALSE.");
		return false;
	}

	@Override
	public int size() {
		System.out.println("SIZE PLACEHOLDER METHOD CALLED. RETURNING SIZE 0.");
		return 0;
	}

	@Override
	public boolean isEmpty() {
		System.out.println("ISEMPTY PLACEHOLDER METHOD CALLED. RETURNING FALSE.");
		return false;
	}

	@Override
	public boolean adopt(String species, int age, String name) {
		System.out.println("ADOPT PLACEHOLDER METHOD CALLED. RETURNING FALSE.");
		return false;
	}

	@Override
	public LinkedList search(String species) {
		System.out.println("SEARCH PLACEHOLDER METHOD CALLED. RETURNING NULL LIST.");
		return null;
	}
	
}

public class AnimalAgencyBackend implements AnimalAgencyBackendInterface<AnimalInterface>{
	
	protected static class Node<AnimalInterface> {
		public boolean isBlack = false;
        public AnimalInterface data;
        public Node<AnimalInterface> parent; // null for root node
        public Node<AnimalInterface> leftChild; 
        public Node<AnimalInterface> rightChild; 
        public Node(AnimalInterface data) { this.data = data; }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
    }
	
	LinkedList<AnimalInterface> searchList = new LinkedList<AnimalInterface>();
	protected Node<AnimalInterface> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree
    
    public boolean add(AnimalInterface data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");

        Node<AnimalInterface> newNode = new Node<>(data);
        
        if(root == null) { root = newNode; size++; root.isBlack = true; return true; }
        else {
            boolean returnValue = addHelper(newNode,root); 
            root.isBlack = true;
            if (returnValue) size++;
	    else throw new IllegalArgumentException(
	    	"This RedBlackTree already contains that value.");
            return returnValue;
        }
	}
    
    private boolean addHelper(Node<AnimalInterface> newNode, Node<AnimalInterface> subtree) {
    	String newNodeString = newNode.data.getSpecies()+newNode.data.getAge()+newNode.data.getName();
		String subtreeString = subtree.data.getSpecies()+subtree.data.getAge()+subtree.data.getName();
        int compare = newNodeString.compareTo(subtreeString); //done alphabetically
        if(compare == 0) return false;

        //left subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { //left empty, insert
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
            //continue
            } else return addHelper(newNode, subtree.leftChild);
        }

        //right subtree
        else { 
            if(subtree.rightChild == null) { //right empty, insert
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
            //continue
            } else return addHelper(newNode, subtree.rightChild);
        }
    }
	
	private void enforceRBTreePropertiesAfterInsert(Node<AnimalInterface> redNode) {

		Node<AnimalInterface> parent = null;
	    Node<AnimalInterface> grandParent = null;
	 
	    while ((redNode != root) && (redNode.isBlack == false)
	           && (redNode.parent.isBlack == false))
	    {
	        parent = redNode.parent;
	        grandParent = parent.parent;
	 
	        // Case A: Parent of redNode is left child of Grand-parent of redNode
	        if (parent == grandParent.leftChild)
	        {
	 
	            Node<AnimalInterface> sibling = grandParent.rightChild;
	 
	            // Case 1: The sibling of redNode is also red Only recoloring required
	            if (sibling != null && sibling.isBlack == false)
	            {
	                grandParent.isBlack = false;
	                parent.isBlack = true;
	                sibling.isBlack = true;
	                redNode = grandParent;
	            }
	 
	            else {
	 
	                // Case 2: redNode is right child of its parent Left-rotation required
	                if (redNode == parent.rightChild) {
	                    rotate(redNode, parent);
	                    redNode = parent;
	                    parent = redNode.parent;
	                }
	 
	                // Case 3: redNode is left child of its parent Right-rotation required
	                rotate(parent, grandParent);
	                boolean t = parent.isBlack;
	                parent.isBlack = grandParent.isBlack;
	                grandParent.isBlack = t;
	                redNode = parent;
	            }
	        }
	 
	        // Case B: Parent of redNode is right child of Grand-parent of redNode
	        else {
	            Node<AnimalInterface> sibling = grandParent.leftChild;
	 
	            //  Case 1: The sibling of redNode is also red Only Recoloring required
	            if ((sibling != null) && (sibling.isBlack == false))
	            {
	                grandParent.isBlack = false;
	                parent.isBlack = true;
	                sibling.isBlack = true;
	                redNode = grandParent;
	            }
	            else {
	                // Case 2: redNode is left child of its parent Right-rotation required
	                if (redNode == parent.leftChild) {
	                    rotate(redNode, parent);
	                    redNode = parent;
	                    parent = redNode.parent;
	                }
	 
	                // Case 3: redNode is right child of its parent Left-rotation required
	                rotate(parent, grandParent);
	                boolean t = parent.isBlack;
	                parent.isBlack = grandParent.isBlack;
	                grandParent.isBlack = t;
	                redNode = parent;
	            }
	        }
	    }
	 
	    root.isBlack = true;
    	
    }
	
	

	private void rotate(Node<AnimalInterface> child, Node<AnimalInterface> parent) throws IllegalArgumentException {
		Node<Animal> grandParent = null;
		Node<Animal> connector = null;

		if(!child.isLeftChild()) {
			child = parent.rightChild;
			parent.rightChild = child.leftChild;
			if (child.leftChild != null) {
				child.leftChild.parent = parent;
			}
			child.parent = parent.parent;
			if (parent.parent == null) {
				root = child;
			}
			else if (parent == parent.parent.leftChild) {
				parent.parent.leftChild = child;
			}
			else  {
				parent.parent.rightChild = child;
			}
        
			child.leftChild = parent;
			parent.parent = child;
		}
		else if(child.isLeftChild()) {
			child = parent.leftChild;
			parent.leftChild = child.rightChild;

			if (child.rightChild != null) {
				child.rightChild.parent = parent;
			}
			child.parent = parent.parent;
			if (parent.parent == null) {
				root = child;
			}
			else if (parent == parent.parent.rightChild) {
				parent.parent.rightChild = child;
			}
			else  {
				parent.parent.leftChild = child;
			}
        
			child.rightChild = parent;
			parent.parent = child;
		}
		else {
			throw new IllegalArgumentException();
		}
}	

    
    
    @Override
	public LinkedList<AnimalInterface> search(String species) {
    	searchList.clear();
		searchSpecies(root, species);
		
		//System.out.println("size: "+searchList.size());
		return searchList;
	}
	
	

	private void searchSpecies(Node<AnimalInterface> node, String species) {
		if(node == null) {
			return;
		}
		//System.out.println(node.data.getSpecies());
		if(node.data.getSpecies().equals(species)) {
			searchList.add(node.data);
		}
		searchSpecies(node.leftChild, species);
		searchSpecies(node.rightChild, species);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		if(this.size() == 0) {
			return true;
		}
		return false;
	}

	public boolean adopt(String species, int age, String name) {
		String dataString = species+age+name;
		return adoptHelper(dataString, root);
	}
	
	/**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean adoptHelper(String data, Node<AnimalInterface> subtree) {
    	if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        }
		String subtreeString = subtree.data.getSpecies()+subtree.data.getAge()+subtree.data.getName();
        
        
            int compare = data.compareTo(subtreeString);
            if (compare < 0) {
                // left
                return adoptHelper(data, subtree.leftChild);
            } 
            else if (compare > 0) {
                // right
                return adoptHelper(data, subtree.rightChild);
            }
            else {
                // found
                subtree.data.setExistence(false);
                return true;
            
        }
    }


}