//java packages imports 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReader;


//import com.opencsv.CSVReader;



public class merge_mail {

	// Intializing Arraylist variables to hols csv data 
	ArrayList<String> CSVdata=new ArrayList<String>();
	ArrayList<String> tempdata=new ArrayList<String>();
	ArrayList<String> tempdata2=new ArrayList<String>();
	


	//defining FIle paths for input data files
	private String filePath ;
	
	merge_mail(String filePath){
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
	//printing data read from csv to to console
	
	public void printCSVdata() {
		
		for(int i=0;i<CSVdata.size();i++) {
			String[] data = CSVdata.get(i).split(",");
			for(int x=0; x<data.length;x++) {
				System.out.print(data[x]);
			}
			System.out.println();
		}
		
		
	}
	
	
	public void csv_read() throws IOException{
		try {
			BufferedReader readFile = new BufferedReader(new FileReader( filePath ));
			String readFilerow; int i=0;
			while ((readFilerow = readFile.readLine()) != null ) {
				if(i>0) {
					CSVdata.add(readFilerow);
					
				}
				
				i++;
			}
			
			
			readFile.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	

	//Reading template file 
	
	public void template_read() {
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("C:\Users\yuzri\vttp\SDF Test Files\tour_packages.txt"));
			String line = reader.readLine();
			while (line != null) {
				
				tempdata.add(line);
				line = reader.readLine();
			}
			

			
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//Reading thank you file 
	public void thankyou_read() {
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("thankyou.txt"));
			String line = reader.readLine();
			while (line != null) {
				
				tempdata2.add(line);
				//System.out.println(line);
				line = reader.readLine();
			}

			
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	// to merge  files to get the mail body 
	public void get_mail() {
		
		for(int csv=0;csv<CSVdata.size();csv++) {
		System.out.println("==>"+(csv+1));
		for(int x=0;x<tempdata.size();x++) {
			
			String data[]=CSVdata.get(csv).split(",");
			
			if(tempdata.get(x).contains("salutations")) {
				
					System.out.println("Dear "+data[0]+". "+data[1]);
				
			}
			else if(tempdata.get(x).contains("_package_name_")) {
				System.out.print("Thank you for your interest in our new +"+data[2]+" package! \r\n"
						+ "For the 2022 Spring promotion, you can sign up for the low low price of $"+data[3]+"!");
			}
			else if(tempdata.get(x).contains("Safe")) {
				System.out.println("Safe travels!\n");
			}
			else {
				System.out.println();
			}
			
		}
		
	}
		
	}
	
	//  merge  thank you file to the mail body 
	public void merge_thankyou_mb() {
		
		for(int csv=0;csv<CSVdata.size();csv++) {
		System.out.println("==>"+(csv+1));
		for(int x=0;x<tempdata2.size();x++) {
			
			String data[]=CSVdata.get(csv).split(",");
			
			if(tempdata2.get(x).contains("address")) {
				
				for(int z=0;z<data[2].length();z++) {
					
					if(data[2].charAt(z)!=92)
						System.out.print(data[2].charAt(z));
					else {
						System.out.println();
						z++;
					}
				}
			}
			else if(tempdata2.get(x).contains("first_name")) {
				System.out.print("\nDear "+data[0]+",");
			}
			else if(tempdata2.get(x).contains("Thank you")) {
				System.out.println("\nThank you for staying with us over these "+data[3]+" years.\n");
			}
			else {
				System.out.println();
			}
			
		}
		
	}
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		merge_mail read=new merge_mail( "tour_packages.csv" );
		
		read.csv_read();
		read.template_read();
		read.thankyou_read();
		
		read.get_mail();
		
		read=new merge_mail( "thankyou.csv" );
		read.csv_read();
		read.thankyou_read();
		read.merge_thankyou_mb();
	}
	
	

}
