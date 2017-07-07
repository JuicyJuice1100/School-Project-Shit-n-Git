public class Driver
{
	public satic void main(String[] args)
	{
		DataList data = new DataList();
		int newData, menuOption, index;

		do
		{
			System.out.println("Enter 1 to enter new data \nEnter 2 to delete data at given index \nEnter 3 to display current data \nEnter 4 to clear all data 
				\nEnter 5 to insert data into certain index \n Enter 6 to sort data \n Enter 7 to randomize data \n Enter 8 to display data \nEnter 0 to exit \n");
			menuOption = scan.nextInt();
			switch (menuOption)
			{
				case 0: 
					break;
				case 1: 
					System.out.println("Enter new data");
					newData = scan.nextInt();

					data.dataList.add(newData);
					break;
				case 2:
					System.out.println("Enter Index of where you to delete data");
					index = scan.nextInt();
					data.dataList.remove(index;)
					break;
				case 3: 
					data.display();
					break;
				case 4: 
					data.dataList.clear();
					break;
				case 5: 
					//ask for new data
					System.out.println("Enter new data");
					newData = scan.nextInt();

					//ask for index to place data
					System.out.println("Enter index of where you want to place data");
					index = scan.nextInt();
					data.dataList.add(index, newData);
					break;
				case 6:
					//sort data
					data.sort();
					break;
				case 7:
					//shuffle data
					data.shuffle();
					break;
				default:  
					System.out.println("Invalid input");
			}
		}while(menuOption != 0);

		System.out.printf("Min: %d \nMax: %d \nAvg: %d", Collections.min(data.getDataList()), Collection.max(data.getDataList()), data.getAvg());
		
		}
	}
}