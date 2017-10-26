package lab6;

public class MyBST<T extends Comparable<T>>{
	//Inner Class to capture a node
	public class MyNode<T> {
            public T data;
            public MyNode<T> left;
            public MyNode<T> right;
            public MyNode(T e, MyNode<T> l, MyNode<T> r){
                data=e;
                left=l;
                right=r;
            }
	}

	//Data members
	public MyNode<T>root;
	public int size;

	//Operations
	public MyBST(){
            root = null;
            size=0;
	}

	public int size(){
            return size;
	}

	public boolean contains(T element){
            MyNode<T> temp=root;
            return containsNode(root,element);
	}

	//O(h) -- O(log n) to O(n)
	public boolean containsNode(MyNode<T> temp,T element){
            if(temp!=null){
                int compared = temp.data.compareTo(element);
                if(compared==0)
                    return true;
                else if (compared >0)
                    return containsNode(temp.left,element);
                else
                    return containsNode(temp.right,element);
            }
            return false;
	}

	public void inorder(){
            System.out.print("Tree of size "+size+": ");
            inorder(root);
            System.out.println();
	}

	//O(n)
	private void inorder(MyNode<T> temp){
            if (temp!=null){
                inorder(temp.left);
                System.out.print(temp.data+" ");
                inorder(temp.right);
            }
	}

	//O(n)
	public void levelTraversal(){
            java.util.Queue<MyNode<T>> queue = new java.util.LinkedList<MyNode<T>>();
            MyNode<T> temp = root;
            while(temp!=null){
                System.out.print(temp.data+" ");
                if (temp.left!=null)
                    queue.offer(temp.left);
                if (temp.right!=null)
                    queue.offer(temp.right);
                temp = queue.poll();
            }
            System.out.println();
	}


	public void insert(T element){
            if(root==null)
                root = new MyNode<T>(element,null,null);
            else
                insertNode(root, element);
            size++;
	}

	//O(h) -- O(log n) to O(n)
	private void insertNode(MyNode<T> temp, T element){
            int compared = temp.data.compareTo(element);
            if(compared > 0){
                if(temp.left==null){
                        MyNode<T> insertMe = new MyNode<T>(element,null,null);
                        temp.left = insertMe;
                }
                else
                    insertNode(temp.left, element);
            }
            else {
                if(temp.right==null){
                    MyNode<T> insertMe = new MyNode<T>(element,null,null);
                    temp.right = insertMe;
                }
                else
                    insertNode(temp.right, element);
            }
	}


	public boolean remove(T element){
            boolean success = removeNode(root,null, element);
            if (success)
                size--;
            return success;
	}

	//O(h) -- O(log n) to O(n)
	private boolean removeNode(MyNode<T> temp, MyNode<T> parent, T element){
            if (temp==null)	//Node not present
                    return false;
            else{
                int compared = temp.data.compareTo(element);
                if(compared==0){	//this is the node to remove
                    //delete temp
                    removeNode(temp,parent);
                    return true;
                }
                else if (compared>0){ //in left subtree
                    return removeNode(temp.left, temp, element);
                }
                else{ //in right subtree
                    return removeNode(temp.right, temp, element);
                }
            }
	}

	//overloaded method to remove non-null node temp
	private void removeNode(MyNode<T> temp, MyNode<T> parent){
            if(temp.right==null && temp.left==null) {//leaf node, easy!
                if(root==temp)	//deleted only node
                    root=null;
                else if (parent.left==temp)
                    parent.left=null;
                else if (parent.right==temp)
                    parent.right=null;
            }
            else if (temp.right==null){ //one left child
                if(root==temp)
                    root=temp.left;
                else if (parent !=null && parent.left==temp)
                    parent.left=temp.left;
                else
                    parent.right=temp.left;
            }
            else if (temp.left==null){ //one right child
                if(root==temp)
                    root=temp.right;
                else if (parent.left==temp)
                    parent.left=temp.right;
                else
                    parent.right=temp.right;
            }
            else { //two children
                //find inorder successor
                //this is the leftmost node in the right subtree
                //we could also use the inorder predecessor - rightmost node in left subtree
                MyNode<T> curr = temp.right;
                MyNode<T> currParent = temp;
                while(curr.left!=null){
                        currParent = curr;
                        curr=curr.left;
                }
                //write this value in place of temp:
                temp.data=curr.data;
                //and remove this new node:
                removeNode(curr,currParent);
                //No need to adjust the root here...
            }
	}
        
        public int countOccurances(T element){
            int count = 0;
            MyNode<T> temp = root;
            int compared = temp.data.compareTo(element);
            while(temp != null){
                if(compared == 0){
                    count++;
                    temp = temp.right;
                    compared = temp.data.compareTo(element);
                    while(compared == 0){
                        count++;
                        temp = temp.right;
                        compared = temp.data.compareTo(element);
                    }
                    return count;
                }
                else if(compared > 0){
                    temp = temp.right;
                }
                else{
                    temp = temp.left;
                }
                if(temp != null){
                    compared = temp.data.compareTo(element);
                }
            }
            return count;
        }
        
        public int inBetween(T low, T high){
            int count = inBetween(root, low, high);
            return count;
        }
        
        private int inBetween(MyNode<T> temp, T low, T high){
            int count = 0;
            if (temp!=null){
                count += inBetween(temp.left, low, high);
                if(temp.data.compareTo(low) > 0 && temp.data.compareTo(high) < 0){
                    count++;
                }
                count += inBetween(temp.right, low, high);
            }
            return count;
        }
        
        /*
        * O(n)
        * The remove node that used has a big O run time of O(1) as it's only 
        * if/else statements.  The removeLessThan at most will be running at a 
        * big O of O(n) as it will never run more than the amount of elements in
        * the tree.  Finally the removeLessThan will only run at O(1) ignoring 
        * the removeLessThan method as it was already considered in the calcluation
        * above.  With that we should have O(1) + O(n) * O(1) which can be simplified
        * as O(n)
        */
        public int removeLessThan(T element){
            int count = removeLessThan(root, null, element);
            size -= count;
            return count;
        }
        
        private int removeLessThan(MyNode<T> temp, MyNode<T> parent, T element){
            int count = 0;
            if(temp!=null){
                count += removeLessThan(temp.left, temp, element);
                count += removeLessThan(temp.right, temp, element);
                if(temp.data.compareTo(element) < 0){
                    count++;
                    removeNode(temp, parent);
                }
            }
            return count;
        }

//	public static void main(String args[]){
//            MyBST<Integer> tree = new MyBST<>();
//            tree.insert(20);
//            tree.insert(1);
//            tree.insert(17);
//            tree.insert(10);
//            tree.insert(7);
//            tree.insert(30);
//            tree.insert(40);
//            tree.insert(15);
//            tree.insert(11);
//            tree.inorder();
//            tree.levelTraversal();
//            tree.inorder();
//            tree.levelTraversal();
//            System.out.println(tree.contains(40));
//            System.out.println(tree.contains(1));
//            System.out.println(tree.countOccurances(8));
//            System.out.println(tree.countOccurances(20));
//            System.out.println(tree.inBetween(1, 4));
//            System.out.println(tree.inBetween(10, 40));
//            System.out.println(tree.removeLessThan(15));
//            tree.inorder();
//	}









}








