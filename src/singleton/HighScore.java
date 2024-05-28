package singleton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import model.Restaurant;

public class HighScore {

	private static HighScore instance = null;
	public Scanner scanf = new Scanner (System.in);
	
	public static HighScore getInstance() {
		if(instance == null) {
			instance = new HighScore();
		}
		return instance;
	}
	
	public void printHyphens (int times) {
		for (int i = 0; i < times; i++) {
            System.out.print("-");
        }
        System.out.println();
	}
	
	public void printEquals (int times) {
		for (int i = 0; i < times; i++) {
            System.out.print("=");
        }
        System.out.println();
	}
	
	
	public void ReadFile(String currname) {
		try {
			File file = new File("src/assets/highscore.txt");
			Scanner fscanf = new Scanner(file);
			int i = 1;
			System.out.println();
			System.out.printf("| %40s | %-7s | %35s %n"," ","HIGHSCORE", "");
			System.out.println();
			printEquals(100);
			System.out.printf("| %30s %5s %-20s %8s %35s %n"," ","Rank","Restaurant's name","Score", "");
			printHyphens(100);
			while (fscanf.hasNextLine() && i <11) {
				String data = fscanf.nextLine();
				String restoname = data.split("#")[0];
				String score = data.split("#")[1];
				if(currname == restoname) {
					System.out.printf("| %-20s %3s %7s %5s %-20s %8s %7s %3s %25s %n"," ",">>>"," ",i,restoname,score, " ","<<<"," ");
					
				}else {
					System.out.printf("| %30s %5s %-20s %8s %35s %n"," ",i,restoname,score, "");
				}
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Press Enter to Continue");
			fscanf.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ReadFile() {
		try {
			File file = new File("src/assets/highscore.txt");
			Scanner fscanf = new Scanner(file);
			int i = 1;
			System.out.println();
			System.out.printf("| %40s | %-7s | %35s %n"," ","HIGHSCORE", "");
			System.out.println();
			printEquals(100);
			System.out.printf("| %30s %5s %-20s %8s %35s %n"," ","Rank","Restaurant's name","Score", "");
			printHyphens(100);
			while (fscanf.hasNextLine() && i <11) {
				String data = fscanf.nextLine();
				String restoname = data.split("#")[0];
				String score = data.split("#")[1];
				
				System.out.printf("| %30s %5s %-20s %8s %35s %n"," ",i,restoname,score, "");
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Press Enter to Continue");
			fscanf.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void WriteFile(String restoname,Integer score) {
		try {
		//1. Kita perlu path menuju file
		String path = "src/assets/highscore.txt";
		FileWriter fw = new FileWriter(path,true);
		//True = append, False = write in C
		//Tujuan : tulis file
		BufferedWriter bw = new BufferedWriter(fw);
		//Tujuan : untuk menulis file dalam buffer tertentu.
		PrintWriter pw = new PrintWriter(bw);
		//Tujuan : bisa menggunakan println() dalam file writer.
		pw.printf("%s#%s\n",restoname,score);
		pw.close();
		System.out.println("Restaurant Save Successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
