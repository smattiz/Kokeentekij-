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
	 * Asetetaan Filu  Vectoriin, ett� se saadaan satunnaisutettua
	 * @param string file Meid�n filu, jota halutaan hy�dynt��
	 */
 public  static Vector<String> filuListaan(File ourFile) { //Voidaan my�s ihan luoda olio, joka meill� on
	// Koe koe= new Koe();
	 Vector<String> meid�nLista= new Vector<String>();
	 
	 try {
		BufferedReader bfIn= new BufferedReader(new FileReader(ourFile));
		while(bfIn.readLine() != null) {
			meid�nLista.add(bfIn.readLine()); //luetaan filua rivi kerrallaan meid�n listaan
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
	//  System.out.println(meid�nLista);
	return teeRandomKysymykset(meid�nLista);
	
 }
 
 /**
  * Tehd��n meid�n satunnaiset kysymykset halutusta Vectorista
  * @param kysymysLista vector, joka sis�lt�� kysymykset
  */
 private static  Vector<String> teeRandomKysymykset(Vector<String> kysymysLista) {
	Random rng= new Random();
	Vector<String> meid�nKysymykset= new Vector<String>();
	for(int i=0; i<=10; i++) {
		meid�nKysymykset.add(kysymysLista.get(rng.nextInt(kysymysLista.size()))); //T�� sulkuhelvetti: haetaan satunnainen nro, kun yl�raja on meid�n kysymyskten lkm
		
	}
	//System.out.println(meid�nKysymykset);
	//TODO: K�sittele meid�n  randomoitua listaa....
	return meid�nKysymykset;
	
 }
	
/**
 * Kokeentekij�, joka kutsuu aliohjelmia halutussa j�rjestyksess� lopulta tulostellen tiedostoon meid�n uuden kokeen
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
