import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;

public class db{
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int id;
		String name;
		Long mobile_no;
		int choice;
		Connection connection=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/vikash","root","ravivikash@04");
		}
		catch(ClassNotFoundException e1){
			System.out.println(e1);
		}
		catch(SQLException e2){
			System.out.println(e2);
		}
		if(connection==null)
			System.out.println("Not Connected");
		else
			System.out.println("Connected!!!");
		
		do{
		System.out.println("Enter a choice");
            		System.out.println("1: Add Student Details");
            		System.out.println("2: Display Student Details");
            		System.out.println("3: Edit Student Details");
		System.out.println("4.Delete student Details");
            		System.out.println("5: Exit");
		
            		choice = sc.nextInt();
		sc.nextLine();
		if(choice==1){
			String insertQuery="insert into Student (student_name,mobile_no) values(?,?);";
			try{
				PreparedStatement ptr=connection.prepareStatement(insertQuery);
				System.out.println("Enter a name:");
				name=sc.nextLine();
				System.out.println("Enter mobile number:");
				mobile_no=sc.nextLong();
				sc.nextLine();
				ptr.setString(1,name);
				ptr.setLong(2,mobile_no);
				System.out.println(ptr);
				int r=ptr.executeUpdate();
				if(r==1)
					System.out.println("Inserted");
				else
					System.out.println("not inserted");
				}
			catch(SQLException e){
				System.out.println(e);
			}
		}
	
		if(choice==2){
			System.out.println("Displaying student details!!!!");
                    		System.out.println("Press 1: Display the whole data");
                    		System.out.println("Press 2: Display RollNo");
                    		System.out.println("Press 3: Display Name");
                    		System.out.println("Press 4: Display Mobile no");
                    		int display=sc.nextInt();
			sc.nextLine();
			if(display==1){
				String selectQuery="select * from Student;";
				try{
					PreparedStatement ptr=connection.prepareStatement(selectQuery);
					ResultSet rs=ptr.executeQuery();
					while(rs.next()){
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getLong(3));
					}
				}
				catch(SQLException e){
					System.out.println(e);
				}
			}
		
			if(display==2){
				String selectIdQuery="select * from Student where student_id=?";
				try{
					PreparedStatement ptr=connection.prepareStatement(selectIdQuery);
					System.out.println("Enter the id?..");
					id=sc.nextInt();
					ptr.setInt(1,id);
					ResultSet rs=ptr.executeQuery();
					while(rs.next())
						System.out.println(rs.getString(2)+" "+rs.getLong(3));
					
				}
				catch(SQLException e){
					System.out.println(e);
				}
			}

			if(display==3){
				String selectNameQuery="select * from Student where student_name=?;";
				try{
					PreparedStatement ptr=connection.prepareStatement(selectNameQuery);
					System.out.println("Enter the name?..");
					name=sc.nextLine();
					ptr.setString(1,name);
					ResultSet rs=ptr.executeQuery();
					while(rs.next())
						System.out.println(rs.getInt(1)+" "+rs.getLong(3));
				}
				catch(SQLException e){
					System.out.println(e);
				}
			}

			if(display==4){
				String selectMobileNoQuery="select * from Student where mobile_no=?;";
				try{
					PreparedStatement ptr=connection.prepareStatement(selectMobileNoQuery);
					System.out.println("Enter the mobile no?..");
					mobile_no=sc.nextLong();
					ptr.setLong(1,mobile_no);
					ResultSet rs=ptr.executeQuery();
					while(rs.next())
						System.out.println(rs.getInt(1)+" "+rs.getString(2));
				}
				catch(SQLException e){
					System.out.println(e);
				}
			}
		}
		if(choice==3){
			String updateQuery="update Student set student_name=?,mobile_no=? where student_id=?";
			try{
				PreparedStatement ptr=connection.prepareStatement(updateQuery);
				System.out.println("Enter name:");
				name=sc.nextLine();
				System.out.println("Enter mobile no:");
				mobile_no=sc.nextLong();
				System.out.println("Enter the student Id");		
				id=sc.nextInt();	
				ptr.setString(1,name);
				ptr.setLong(2,mobile_no);
				ptr.setInt(3,id);
				System.out.println(ptr);
				int r=ptr.executeUpdate();
				if(r==1)
					System.out.println("Updated!!");
				else
					System.out.println("Not updated");
			}
			catch(SQLException e){
				System.out.println(e);
			}
		}

		if(choice==4){
			String deleteQuery="delete from Student where student_id=?";
			try{
				PreparedStatement ptr=connection.prepareStatement(deleteQuery);
				System.out.println("Enter id");
				id=sc.nextInt();
				ptr.setInt(1,id);
				int r=ptr.executeUpdate();
				if(r==1)
					System.out.println("Deleted!!");
				else
					System.out.println("Not deleted");
			}
			catch(SQLException e){
				System.out.println(e);
			}
		}
		if(choice==5){
			System.out.println("Exiting the program............");
		}
		}while(choice!=5);
	}
}