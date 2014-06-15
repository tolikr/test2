package FileOperations;

import java.io.*;
import java.util.StringTokenizer;

public class FileOperations {
	public static void SaveToFile(String fileName, String data) throws IOException{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
		{
			String[] lines = data.split("\n");
			for (String line: lines) {
		        writer.write(line);
		        writer.newLine();
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	/**Прочитать файл
	 * @throws IOException */
	public static StringTokenizer ReadFromFile(String filename) throws IOException{
		File file = new File(filename);
		if (!file.exists()) {throw new FileNotFoundException();}
		String  result = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String thisLine;
			while ((thisLine = reader.readLine()) != null)
			{
				result += thisLine + ":";
			}
		}
		StringTokenizer st = new StringTokenizer(result, ":");
		return st;
	}
}
