
/*
 *This is my secondary binary search tree node, it just deals with the int and char
 *had to have a seperate one since dealing with generics is really annoying.
 */
class CharNode {

	CharNode right;
	CharNode left;
	char element;
	int item;

	CharNode (CharNode r,CharNode l, char e){
		right = r;
		left = l;
		element = e;
		item = 1;

	}
}
