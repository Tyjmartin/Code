import java.io.*;
import BasicIO.*;
import java.*;
import java.util.*;
/*
 *
 *Tyler Martin
 *
 *4836318
 *
 *I didnt abstract this to much because it was not really needed, also i could not get my decompress working properly.
 *You can call on different methods from the constructor to change what you need.
 */

class lzw {

	ASCIIDataFile input;
	ASCIIOutputFile out;
	int tableSize = 503;
	Node table[] = new Node[tableSize]; //my array of nodes

	public lzw (){
		Compress();

	}

	public void Compress(){         // this is my compression algorithm
		input = new ASCIIDataFile();
		out = new ASCIIOutputFile();
		String s = "";
		Node temp;
		char ch;
		int count = 4;
		boolean check = false;


		table[hashMath("0")] = new Node ("0",0,null);  // This is adding the dictionary values
		table[hashMath("1")] = new Node ("1",1,null);
		table[hashMath(" ")] = new Node (" ",2,null);
		table[hashMath("/n")] = new Node ("/n",3,null);



		while(!input.isEOF()){

			int st = hashMath(s);
			check = false;
			ch = input.readC();
			String te = Character.toString(ch); // creating the string
			int c = hashMath(te);

			String h = s+te;
			int sc = hashMath(h);
			temp = table[sc];
			while (temp != null){ //this is going through the list and seeing if any of the values are equal
				if ( h.equals(temp.st)){
					s = s+te;
					check = true;
				}
				temp=temp.next;
			}
			if (check){ //this goes back up to the while loop and starts over
				continue;
			}

			else{
				temp = table[hashMath(s)];
				while(temp!=null){     //this is finding the values in the list
					if (temp.st.equals(s)){
						st = temp.value;
						break;
					}
					else{
						temp = temp.next;
					}
				}
				s = s + te;
				if (table[hashMath(s)] == null){
					table[hashMath(s)] = new Node (s,count,null);
					out.writeInt(st);   //adding it to the output
					out.writeC(',');
				}
				else{
					table[hashMath(s)].next = new Node (s, count, null);
					out.writeInt(st);
					out.writeC(',');
				}
			}
			s = te;
			count++;
		}
	}

	public int hashMath(String s){
		int sum = 0;
		int welp = 0;
		int chari = 0;
		char temp;
		for (int i = 0; i < s.length(); i++){
			temp = s.charAt(i);
			welp = (87*(i+1)*temp);
			sum = sum + welp;
		}
		return sum%tableSize;
	}
	/*
	public void Decompress(){
		input = new ASCIIDataFile();
		out = new ASCIIOutputFile();

		char ch;
		int prev;

		String st = "";

		table[hashMath("0")] = new Node ("0",0,null);
		table[hashMath("1")] = new Node ("1",1,null);
		table[hashMath(" ")] = new Node (" ",2,null);
		table[hashMath("/n")] = new Node ("/n",3,null);


	}*/


	///part A wooo fairly straight forward!!!
	public boolean PartA(ASCIIDataFile one, ASCIIDataFile two){
		char f, s;
		String o, t;

		boolean check = false;

		while (!one.isEOF() && !two.isEOF()){
			f = one.readC();
			s = two.readC();
			o = Character.toString(f);
			t = Character.toString(s);

			if (o.equals(t))
				check = true;
			else{
				return check = false;
			}
		}
		return check;

	}

	public static void main (String args[]) {new lzw();}

}
