import java.util.Scanner;

class Employee
{
	private String name;
    private int id;
    private String address;
    private char gender;
    private int workingHours;
    private double discountRate;

    public Employee(String name, int id, String address, char gender, int workingHours)
	{
        this.name = name;
        this.id = id;
        this.address = address;
        this.gender = gender;
        this.workingHours = workingHours;
        this.discountRate = calculateDiscount(workingHours);
    }

    public void printEmployeeDetails() 
	{
        System.out.println("------Employee Details------");
        System.out.println("Name: " + name);
        System.out.println("Employee ID: " + id);
        System.out.println("Address: " + address);
        System.out.println("Gender: " + gender);
        System.out.println("Working Hours: " + workingHours);
        System.out.println("Discount Rate: " + discountRate + "%");
        System.out.println("----------------------------");
    }

    public double calculateDiscount(int workingHours) 
	{
        if (workingHours > 15) 
		{
            return 25; 
        }
		else
		{
            return 0;
        }
    }
	
	public int getWorkingHours()
	{
		return workingHours;
	}
	
	public void setWorkingHours(int workingHours)
	{
		this.workingHours=workingHours;
	}
}


abstract class Salary 
{

    protected double salary;
    protected static double basicSalary;

    public Salary(double basicSalary) 
	{
        this.basicSalary = basicSalary;
    }

    public abstract double calculateSalary();
}


interface Abroad 
{
	
    String checkAbroad(double salary);
 
}

class Discount_2 extends Employee 
{
	
    public Discount_2(String name, int id, String address, char gender, int workingHours) 
	{
        super(name, id, address, gender, workingHours);
    }

    
    public double calculateDiscount() 
	{
        if (getWorkingHours() >=10 && getWorkingHours() <= 15) 
		{
            return 10;   // 10% discount for working hours between 10 and 15
        }
		else 
		{
            // Call the parent class method for working hours exceeding 15
			
            double parentDiscount = super.calculateDiscount(getWorkingHours());
            return parentDiscount;
			
        }
    }
}


class ChildSalary extends Salary 
{

    private double discountRate;

    public ChildSalary(double discountRate, double basicSalary) 
	{
        super(basicSalary);
        this.discountRate = discountRate;
    }

    
    public double calculateSalary() 
	{
		
        if (discountRate == 25) 
		{
            salary = basicSalary + (basicSalary*25/100);
        } 
		else if (discountRate == 10)
		{
            salary = basicSalary + (basicSalary*10/100);
        } 
		else 
		{
            salary = basicSalary;
        }
        return salary;
    }
}

class ConfirmAbroad implements Abroad 
{

    private double salary;

    public ConfirmAbroad(double salary) 
	{
        this.salary = salary;
    }

    
    public String checkAbroad(double salary) 
	{
        if (salary > 120000) 
		{
            return "Select to go abroad.";
        } 
		else 
		{
            return "Not selected.";
        }
    }
}

public class EmployeeApp
{

    public static void main(String[] args) 
	{
        Scanner sc = new Scanner(System.in);

        do 
		{
            
            for (int i = 0; i < 1; i++) 
			{
                System.out.println("Employee " + (i + 1) + " Details:");
				System.out.println(" ");
				
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
				
                System.out.print("Enter Employee ID: ");
                int id = sc.nextInt();
				sc.nextLine();
				
				System.out.print("Enter Address: ");
                String address = sc.nextLine();
				
				System.out.print("Enter Gender (M/F): ");
                char gender = sc.next().charAt(0);
				
                System.out.print("Enter Working Hours: ");
                int workingHours = sc.nextInt();
				
                System.out.print("Enter the basic salary: ");
                double basicSalary = sc.nextDouble();
				sc.nextLine();				

               // Create an object of Discount_2 class
                Discount_2 employee = new Discount_2(name, id, address, gender, workingHours);

                employee.printEmployeeDetails();

                // Calculate discount based on working hours
				double discount = employee.calculateDiscount(workingHours);
                System.out.println("Discount Rate: " + discount + "%");
                
                // Create an object of ChildSalary class
                ChildSalary childSalary = new ChildSalary(discount, basicSalary);

                // Calculate salary
                double salary = childSalary.calculateSalary();
                System.out.println("Salary: " + salary);

                // Check eligibility to go abroad based on salary
                Abroad abroadChecker = new ConfirmAbroad(salary);
				
                String abroadStatus = abroadChecker.checkAbroad(salary);
                System.out.println("Abroad Status: " + abroadStatus);
				//sc.nextLine();
				
            }

            System.out.print("Do you want to run the program again? (yes/no): ");
            String runAgain = sc.nextLine();

            if (!runAgain.equalsIgnoreCase("yes")) 
			{
                break; // Exit the loop if the user doesn't want to run the program again
            }
        }  while (true); // Keep running until the user decides to exit

        System.out.println("Program has ended.");
    }
}
