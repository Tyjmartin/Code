/*
 *Alright so this is the bulk of the work, it is my pride and joy!
 *
 */
class Bst {

	Node root;

	public Bst (){
		root = null;
	}

	//public insert for inserting with out threads!

	public void insert (String temp){
		root = insert(temp, root);
	}
	//public insert with threads, boo threads suck
	public void insertB (String temp){
		if (root != null){
			root = insertB(temp,root);
			}
		else {
			root = new Node(temp,null,null);
		}
	}
	//private insert, so the real insert for the binary tree without threads
	private Node insert(String item, Node temp){

		if (temp == null)
			return new Node(item, null, null);

		int compare = item.compareTo(temp.element);

		if (compare < 0)
			temp.left = insert(item, temp.left);
		else if (compare > 0 )
			temp.right = insert (item, temp.right);
		else;
			return temp;
	}

	//Real insert with threads, really tedious to get right.
	//Checks to see first which side it should insert, then checks each side for the threads.

	private Node insertB (String item, Node parent){

		int compare = item.compareTo(parent.element);
		if (compare < 0){
			if (parent.left == null || parent.lthread){
				insertLeft(item, parent);
			}
			else{
				parent.left = insertB(item,parent.left);
			}
		}

		else if (compare > 0 ){
			if (parent.right == null || parent.rthread){
				insertRight(item, parent);
			}
			else{
				parent.right = insertB(item,parent.right);
			}
		}
			return parent;
	}

	//actually placing the node in its spot in the binary seach tree for left and right!

	private void insertLeft (String item, Node temp){
		Node n = new Node(item,temp,temp.left);
		n.lthread = true;
		n.rthread = true;
		temp.left = n;
		temp.lthread = false;
	}

		private void insertRight (String item, Node temp){
		Node n = new Node(item,temp.right,temp);
		n.lthread = true;
		n.rthread = true;
		temp.right = n;
		temp.rthread = false;
	}

	//print method for use with out threads. Call this in the main and it will print!
	public void printPre(){
		print (root);
	}
	// the actual work for the print method!
	private void print(Node temp){
		if (temp == null)
			return;

		print(temp.left);
		System.out.println(temp.element);
		//temp.print();
		print(temp.right);
	}

	// Alright so traversing and print the BST inorder! You call on this from the public method for any of the prints.
	private void printThreadRecIn (Node temp){
		if (!temp.lthread){
			printThreadRecIn(temp.left);
		}

		System.out.println(temp.element);
		temp.print();

		if (!temp.rthread){
			printThreadRecIn(temp.right);
		}
		if (temp.lthread && temp.rthread){
			return;
		}

	}
	// Printing the BST in preorder and traversing the tree.
	private void printThreadRecPre (Node temp){
		System.out.println(temp.element);
		temp.print();

		if(!temp.lthread){
			printThreadRecPre(temp.left);
		}
		if(!temp.rthread){
			printThreadRecPre(temp.right);
		}
		if (temp.lthread && temp.rthread){
			return;
		}
	}
	// Traversing and printing the BST in postorder again can switch up in the public print method
	private void printThreadRecPos (Node temp){

		if(!temp.lthread){
			printThreadRecPos(temp.left);
		}
		if(!temp.rthread){
			printThreadRecPos(temp.right);
		}
		System.out.println(temp.element);
		temp.print();

		if (temp.lthread && temp.rthread){
			return;
		}

	}
	// this is the public print method used for calling on any of the traversals that deal with threads
	public void printPreT(){
		printThreadRecIn(root);
	}
	// The iterative traversal and print for dealing with threads!
	private void printThread (Node temp){
		Node cur = temp;

		while (cur.left != null){
			cur = cur.left;
		}
		while (cur != null){
			System.out.println(cur.element);
			cur.print();
			if (cur.rthread){
				cur = cur.right;
			}
			else
			{
				cur = cur.right;
				while(!cur.lthread){
					cur = cur.left;
				}
			}
		}
	}

}
