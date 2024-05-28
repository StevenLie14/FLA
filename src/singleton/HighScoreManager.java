package singleton;

import model.HighScore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HighScoreManager {

	private static HighScoreManager instance = null;
	public Scanner scanf = new Scanner (System.in);
	
	public static HighScoreManager getInstance() {
		if(instance == null) {
			instance = new HighScoreManager();
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
			ArrayList<HighScore> highScores = new ArrayList<>();
			int i = 1;
			System.out.println();
			System.out.printf("| %40s | %-7s | %35s %n"," ","HIGHSCORE", "");
			System.out.println();
			printEquals(100);
			System.out.printf("| %30s %5s %-20s %8s %35s %n"," ","Rank","Restaurant's name","Score", "");
			printHyphens(100);
			while (fscanf.hasNextLine()) {
				String data = fscanf.nextLine();
				String[] parts = data.split("#");
				String restaurantName = parts[0];
				int score = Integer.parseInt(parts[1]);
				highScores.add(new HighScore(restaurantName, score));
			}
			Collections.sort(highScores, Comparator.comparingInt(HighScore::getScore).reversed());
			for (HighScore score : highScores) {
				if(currname.equals(score.getRestoname())) {
					System.out.printf("| %-20s %3s %7s %3s %-20s %8s %7s %3s %25s %n"," ",">>>"," ",i,score.getRestoname(),score.getScore(), " ","<<<"," ");

				}else {
					System.out.printf("| %30s %5s %-20s %8s %35s %n"," ",i,score.getRestoname(),score.getScore(), "");
				}
				i++;
				if(i == 11) break;
			}

			i++;
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
			ArrayList<HighScore> highScores = new ArrayList<>();
			int i = 1;
			System.out.println();
			System.out.printf("| %40s | %-7s | %35s %n"," ","HIGHSCORE", "");
			System.out.println();
			printEquals(100);
			System.out.printf("| %30s %5s %-20s %8s %35s %n"," ","Rank","Restaurant's name","Score", "");
			printHyphens(100);
			while (fscanf.hasNextLine()) {
				String data = fscanf.nextLine();
				String[] parts = data.split("#");
				String restaurantName = parts[0];
				int score = Integer.parseInt(parts[1]);
				highScores.add(new HighScore(restaurantName, score));
				
			}
			Collections.sort(highScores, Comparator.comparingInt(HighScore::getScore).reversed());
			for (HighScore score : highScores) {
				System.out.printf("| %30s %5s %-20s %8s %35s %n", " ", i, score.getRestoname(), score.getScore(), "");
				i++;
				if(i == 11) break;
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
		String path = "src/assets/highscore.txt";
		FileWriter fw = new FileWriter(path,true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		pw.printf("%s#%s\n",restoname,score);
		pw.close();
		System.out.println("Restaurant Save Successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	

}
