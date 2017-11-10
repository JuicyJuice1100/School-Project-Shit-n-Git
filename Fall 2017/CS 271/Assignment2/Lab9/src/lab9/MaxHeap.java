
import java.util.Date;

public class MaxHeap<T extends Comparable<T>>{
        //Inner Class to capture a node
    private class MyNode<T extends Comparable<T>> {
        private T data;
        private int priority;

        private MyNode(T e){
            data=e;
            priority=(int)new Date().getTime();
        }
    }
	private MyNode[] array;
	private int last; //position 0 is unused

    @SuppressWarnings("unchecked")
	public MaxHeap(){
		array = (MyNode[])(new Comparable[10]);
		last = 0;
	}

    @SuppressWarnings("unchecked")
	private void resize(){
		MyNode[] tempArray =
                    (MyNode[])new Comparable[array.length*2];
		for(int i = 0; i<array.length; i++)
                    tempArray[i] = array[i];
		array = tempArray;
	}

	public MyNode peek(){
		if(last==0)
			return null;
		return array[1];
	}

	private void heapifyDown(int index){
		int leftChild = (index * 2);
		int rightChild = (index * 2) + 1;
		//base case, no children
		if(leftChild > last)
			return; //nothing
		else if(leftChild == last){
			//base case, 1 child
			if(array[index].data.compareTo(array[leftChild]) < 0)
				swap(index,leftChild);
		}
		else if(array[leftChild].data.compareTo(array[rightChild]) > 0 &&
					 	array[leftChild].data.compareTo(array[index]) > 0){
				swap(index,leftChild);
				heapifyDown(leftChild);
		}
		else if(array[leftChild].data.compareTo(array[rightChild]) < 0 &&
						array[rightChild].data.compareTo(array[index]) > 0){
				swap(index,rightChild);
				heapifyDown(rightChild);
		}
                else if(array[leftChild].data.compareTo(array[rightChild]) == 0 &&
                        array[rightChild].data.compareTo(array[index]) > 0){
                    if(array[leftChild].priority > array[rightChild].priority){
                        swap(index, leftChild);
                        heapifyDown(leftChild);
                    }
                    else{
                        swap(index, rightChild);
                        heapifyDown(rightChild);
                    }
                }
	}


	public  MyNode  deleteMax(){
		if(last==0)
			return null;
		MyNode temp = array[1];
		array[1] = array[last];
		last--;
		heapifyDown(1);
		return temp;
	}

	private void heapifyUp(int index){
		int parentIndex = index / 2;
		if (parentIndex==0)
			return;
		if(array[parentIndex].data.compareTo(array[index]) < 0){
			swap(parentIndex,index);
			heapifyUp(parentIndex);
		}
	}
        
        public void insert(T element){
            MyNode node = new MyNode(element);
            insert(node);
        }
        
	private void insert(MyNode element){
		last++;
		if(last==array.length)
			resize();
		array[last] = element;
		heapifyUp(last);
	}

	public void levelTraversal(){
		System.out.print("LevelTraversal: ");
		for (int i=1;i<=last; i++)
			System.out.print(array[i]+" ");
		System.out.println();
	}

	private void swap(int i, int j){
		MyNode temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public int size(){
		return last;
	}
        
    public static void main(String args[]){
        MinHeap<Integer> m = new MinHeap<>();
        java.util.Random r = new java.util.Random();

        int[] data=new int[20];
        for(int i=0; i<data.length; i++)
                data[i]=r.nextInt(100);

        for(int i=0; i<data.length; i++)
                m.insert(data[i]);
        m.levelTraversal();
        String s = "";
        for(int j=0; j<data.length; j++){
                //m.levelTraversal();
                System.out.println(m.size());
                int next = m.deleteMin();
                s += next + " ";
        }
        System.out.println("\n"+s);
        System.out.println(java.util.Arrays.toString(data));
    }
}






