package kokeentekija;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Vector;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * Koe-luokka, jossa teemme kokeen, jota sitten manipuloimme
 * @author Matti Keskiniemi 17.12.2016
 *
 */
public class Koe {
	/**
	 * Asetetaan Filu  Vectoriin, että se saadaan satunnaisutettua
	 * @param string file Meidän filu, jota halutaan hyödyntää
	 */
 public  static Vector<String> filuListaan(File ourFile) { //Voidaan myös ihan luoda olio, joka meillä on
	// Koe koe= new Koe();
	 Vector<String> meidänLista= new Vector<String>();
	 
	 try {
		BufferedReader bfIn= new BufferedReader(new FileReader(ourFile));
		while(bfIn.readLine() != null) {
			meidänLista.add(bfIn.readLine()); //luetaan filua rivi kerrallaan meidän listaan
		} 
		bfIn.close();
		
	 }
	 catch (FileNotFoundException e) {
		Alert alert=new Alert(AlertType.ERROR);
		alert.setHeaderText("File not Found!");
		alert.setContentText("Please Try again!");
		alert.showAndWait();
		
	}
	 catch (IOException e) {
		Alert alert=new Alert(AlertType.ERROR);
		alert.setHeaderText("Problem with Input!");
		alert.showAndWait();
		
	}
	//  System.out.println(meidänLista);
	return teeRandomKysymykset(meidänLista);
	
 }
 
 /**
  * Tehdään meidän satunnaiset kysymykset halutusta Vectorista
  * @param kysymysLista vector, joka sisältää kysymykset
  */
 private static  Vector<String> teeRandomKysymykset(Vector<String> kysymysLista) {
	Random rng= new Random();
	Vector<String> meidänKysymykset= new Vector<String>();
	for(int i=0; i<=10; i++) {
		meidänKysymykset.add(kysymysLista.get(rng.nextInt(kysymysLista.size()))); //Tää sulkuhelvetti: haetaan satunnainen nro, kun yläraja on meidän kysymyskten lkm
		
	}
	//System.out.println(meidänKysymykset);
	//TODO: Käsittele meidän  randomoitua listaa....
	return meidänKysymykset;
	
 }
	
/**
 * Kokeentekijä, joka kutsuu aliohjelmia halutussa järjestyksessä lopulta tulostellen tiedostoon meidän uuden kokeen
 * @param filePath
 * @param koeNimi
 * @throws IOException 
 */
public static  void teeUusiKoe(String filePath, String koeNimi,int kyslkm) throws IOException {
	Random rdn=new Random();
	String nimi= koeNimi;
	File koeKysymyksetFile=new File(filePath);
	Vector<String> kysymykset= filuListaan(koeKysymyksetFile);
	
			BufferedWriter bf= new BufferedWriter(new FileWriter(koeNimi));
			for(int i=0; i<kyslkm; i++) {
			bf.write(kysymykset.get(rdn.nextInt(kysymykset.size())));
			bf.flush();
			bf.newLine();
		} 
		bf.close();
	
	
	
	
	
	
	
}
 
}
