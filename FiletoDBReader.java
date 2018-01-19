package threading;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import EmployeeHolderPackage.Employee;

public class FiletoDBReader {
	private static final int BATCH_SIZE = 5;

	Connection con;
	PreparedStatement pStmt;

	FiletoDBReader() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?useSSL=false", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void loadfileintoDB(File fileName) throws Exception, SQLException {
		
		System.out.println("loading file " + fileName);

		CSVReader csvFR = new CSVReader();
		FiletoDBReader fdr = new FiletoDBReader();
		ArrayList<Employee> employees = csvFR.readEmployeesFromCSV(fileName);

		pStmt = con.prepareStatement("insert into employee ( empNo, name, salary, doj, age) values (?,?,?,?,?)");

		try {
			con.setAutoCommit(false);
			int count = 1;
			boolean hasLeftOverBatchRecords = true;
			for (Employee e : employees) {

				if (e == null)
					break;

				pStmt.setInt(1, e.getNumber());
				pStmt.setString(2, e.getName());
				pStmt.setFloat(3, e.getSalary());
				pStmt.setDate(4, doj);
				pStmt.setInt(5, e.getAge());

				pStmt.addBatch();

				if (count++ % BATCH_SIZE == 0) {
					int[] updateCount = pStmt.executeBatch();
					hasLeftOverBatchRecords = false;
				} else {
					hasLeftOverBatchRecords = true;
				}
			}

			if (hasLeftOverBatchRecords) {
				int[] updateCount = pStmt.executeBatch();
			}

			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed loading file... " + fileName);

	}

}
