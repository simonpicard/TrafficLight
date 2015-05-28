package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	public static int[] adjustPos(int x, int y, Orientation orientation){
		int temp;
		//System.out.println(orientation);
		switch (orientation){
		case SOUTH:
			x += 400;
			y += 400;
			break;
		case WEST:
			temp = y;
			y = 400+x;
			x = 400-temp;
			break;
		case NORTH:
			y = 400-y;
			x = 400-x;
			break;
		case EAST:
			temp = y;
			y = 400-x;
			x = 400+temp;
			break;
		}
		int[] res = {x, y};
		return res;
	}
	public static void replaceRegex(String pathIn, String pathOut){
		String chaine="";

		Pattern p1 = Pattern.compile("State: [^\n]*");
		Pattern p2 = Pattern.compile("^\n");
		Pattern p3 = Pattern.compile("Transition: ");
		Pattern p4 = Pattern.compile(" $" +
				"");
		Pattern p5 = Pattern.compile("\\} ");
		//Pattern p6 = Pattern.compile("\{[^\{\}]*\}");
		
		Matcher m;
		try{
			InputStream ips=new FileInputStream(pathIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne, tmp;
			FileWriter fw = new FileWriter (pathOut);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			int i = 1;
			while ((ligne=br.readLine())!=null){
				if (i%4 == 3){
					m = p3.matcher(ligne);
					tmp = m.replaceFirst("");
					m = p4.matcher(tmp);
					tmp = m.replaceFirst("");
					m = p5.matcher(tmp);
					tmp = m.replaceAll("\\}\n");
					fichierSortie.println(tmp);
				}
				i++;
			}
			br.close(); 
			fw.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		//toArray(pathOut, "../Parser/array.txt");
	}
}
