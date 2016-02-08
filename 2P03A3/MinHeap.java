/**
 * @(#)MinHeap.java
 *
 *
 *This is the minHeap class that will implement the heaps interface
 *not really sure if it will work yet.
 *
 * @author
 * @version 1.00 2012/11/6
 */


public class MinHeap <E extends KeyedNode> implements Heap<E> {

	int heapSize = 0;
	E[] data;
	E test = null;



	//Constructor that will create an array of the size needed for the data structure.
	public MinHeap(int size){
	//	@SuppressWarnings()
		data = (E[])new KeyedNode[size];
	}


	//This is the public add method and will hopefully add new elements to the heap.
	public void Add(E element){
//		if (heapSize == data.length)//checks to see if it can add a new element in the array.
//			throw new Exception("OverFlow!!");
	//	else {
			heapSize++;
			data[heapSize - 1] = element;
			siftUp(heapSize -1);
	//	}
	}


	//Sifts up the heap, or rather the array.
	private void siftUp(int index){
		int parentIn;
		E temp;//for setting the parent and an extra variable to play with.
	//	if(index !=0){
			parentIn = (int)Math.floor(index-1)/2;
	//	}
		if (data[parentIn].getKey() > data[index].getKey()){
			temp = data[parentIn];
			data[parentIn] = data[index];
			data[index] = temp;
			siftUp(parentIn);
		}
	}

	//removes the min element at the top and then proceeds to sift down and place the next min item at the top by
	// recursively calling siftdown.
	public E remove(){
	//	if (isEmpty())
	//		throw new Exception("Heap is empty");
	//	else{
			E temp= data[0];
			data[0] = data[heapSize-1];
			heapSize--;
			if (heapSize > 0)
				siftDown(0);
	//	}
	return temp;
	}

	//Siftdown will hopefully siftDown the heap, this is also done recursively
	private void siftDown(int index){
		int leftChild, rightChild, minIndex;
		E temp;
		leftChild = (2*index+1);
		rightChild = (2*index+2);

		if(rightChild >= heapSize){
			if (leftChild >= heapSize)
				return;
			else
				minIndex = leftChild;
		} else {
			if (data[leftChild].getKey()<=data[rightChild].getKey())
				minIndex = leftChild;
			else
				minIndex = rightChild;
		}
		if (data[index].getKey() > data[minIndex].getKey()){
			temp = data[minIndex];
			data[minIndex] = data[index];
			data[index] = temp;
			siftDown(minIndex);
		}

	}


	//Returns the length of the array.
	public int length(){
		return data.length;
	};

	public E Top(){
		return data[0];
	};

	//returns the boolean when the heapsize is 0.
	public boolean isEmpty() {
		return (heapSize == 0);
	};

}