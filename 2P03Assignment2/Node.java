/*
 *My main node class, deal with the main binary search tree of names. Within this class it calls on another node with breaks down the names
 *and outputs the chars and ints.
 *
 */
class Node {

	Node right;
	Node left;
	boolean rthread,lthread;
	String element;
	CharNode root;

	Node (String ele, Node r, Node l){

		element = ele;
		left = l;
		right = r;
		addChar();
		rthread = false;
		lthread = false;

	}

	// My recursive insert method for the char nodes, and inserts it into the main node.

	private CharNode insert(CharNode temp, char c){

		if (temp == null)
			return new CharNode(null, null, c);
		if (c < temp.element )
			temp.left = insert(temp.left,c);
		else if (c > temp.element)
			temp.right = insert(temp.right,c);
		else
			temp.item++;

		return temp;
	}
   // My public print, just so nothing screws up and really learning how to properly code.
	public void print (){
		print(root);
	}
	// the actual print method, it is done inorder recursively
	private void print (CharNode temp){
		if (temp == null)
			return;

		print(temp.left);
		System.out.println(temp.element + " " + temp.item);
		print(temp.right);
	}

	// adds the char and int to the node and has a check to make sure it does not add "(", ")", and " ' ".
	private void addChar(){
		for (int i =0; i<element.length();i++){
			char c = element.charAt(i);
			if (c>='A'&&c<='Z')
				root=insert(root,c);
		}
	}


}
