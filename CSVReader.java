package threading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import EmployeeHolderPackage.EmployeeUtil;
import EmployeeHolderPackage.CollectionEmployeeOperations;
import EmployeeHolderPackage.Employee;

public class CSVReader {

	public ArrayList<Employee> readEmployeesFromCSV(File fileName) throws Exception {

		ArrayList<Employee> emp = new ArrayList<Employee>();
		Reader fr = null;
		BufferedReader br = null;
		try {

			File inFile = fileName;

			fr = new FileReader(inFile);
			br = new BufferedReader(fr);

			String line = null;

			boolean keepReading = true;

			int index = 0;
			while (keepReading) {
				line = br.readLine();

				if (line == null || line.equals("")) {
					break;
				}
				if (index != 0) {
					Employee e = parseLine(line);
					emp.add(e);
				}

				index++;
			}

		} finally {
			br.close();
			fr.close();
		}

		return emp;

	}

	private Employee parseLine(String line) {
		String[] tokens = line.split(",");
		int eid = Integer.parseInt(tokens[0]);
		int num = Integer.parseInt(tokens[1]);
		String name = tokens[2];
		Float sal = Float.parseFloat(tokens[3]);
		Date doj = EmployeeUtil.getDate(Integer.parseInt(tokens[4]));
		int age = Integer.parseInt(tokens[5]);
		Employee emp = EmployeeUtil.empData(num, name, sal, age);
		return emp;
	}
}