public class Query{
	public static void main(String[] args){
		System.out.println("Origin Query: ...");
		if(Integer.parseInt(args[0]) == 1)
		{
			System.out.println("Origin calling Query1: ...");
			Query1 one = new Query1();
			one.query(Double.parseDouble(args[1]));//x value
		}
		else if(Integer.parseInt(args[0]) == 2)
		{
			System.out.println("Origin calling Query2: ...");
			Query2 two = new Query2();
			two.query(Double.parseDouble(args[1]));//x value
		}
		else if(Integer.parseInt(args[0]) == 3)
		{
			System.out.println("Origin calling Query3: ...");
			Query3 three = new Query3();
			three.query(args[1]);//x value
		}
		else {
			System.out.println("An error has occured when querying the database");
		}
	}
}
