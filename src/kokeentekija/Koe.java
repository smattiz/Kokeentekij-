package kokeentekija;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * Koe-luokka, jossa teemme kokeen, jota sitten manipuloimme
 * @author Matti Keskiniemi 18.12.2016
 * TODO: TÄMÄ PITÄÄ MUUTTA KUNNON OLIOKSI
 */
public class Koe {
	private String nimi;
	private Vector<String> koeKysymykset;
	
	public Koe(String nimiIn) {
		nimi=nimiIn;
		koeKysymykset=null;
	}
	
	
	/**
	 * Asetetaan meidän File rivi riviltä  Vectoriin, että se saadaan käyttöön
	 * @param string file Meidän filu, jota halutaan hyödyntää
	 */
 public  static Vector<String> filuListaan(File ourFile) { //Voidaan myös ihan luoda olio, joka meillä on
	 Vector<String> meidänLista= new Vector<String>();
	 
	 try {
		BufferedReader bfIn= new BufferedReader(new FileReader(ourFile));
		while(bfIn.readLine() != null) {
			meidänLista.add(bfIn.readLine()); //luetaan filua rivi kerrallaan meidän listaan
		} 
		bfIn.close();
		
	 }
	 catch (FileNotFoundException e) { //Tiedetään, vältä toistoa... Olen kuitenkin liian laiska xD
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
	return meidänLista;	
 }
 
 /**
  * Tehdään meidän satunnaiset kysymykset halutusta Vectorista
  * @param kysymysLista vector, joka sisältää kysymykset
  */
 private static  Vector<String> teeRandomKysymykset(Vector<String> kysymysLista) {
	Random rng= new Random();
	Vector<String> meidänKysymykset= new Vector<String>();
	for(int i=0; i<=10; i++) {
		meidänKysymykset.add(kysymysLista.get(rng.nextInt(kysymysLista.size()))); 
	}
	return meidänKysymykset;
 }
	
/**
 * Kokeentekijä, joka kutsuu aliohjelmia halutussa järjestyksessä lopulta tulostellen tiedostoon meidän uuden kokeen
 * @param filePath Tiedoston polku merkkijonona
 * @param koeNimi Tulevan koetiedoston nimi
 * @throws IOException Jos tietovirrassa on ongelmia
 */
public static  void teeUusiKoe(String filePath, String koeNimi,int kyslkm) throws IOException {
	Koe koe=new Koe(koeNimi);
	File koeKysymyksetFile=new File(filePath); //muutetaan Filu Stringiksi, helpompi käsitellä näin leikkimällä
	Vector<String> kysymykset= filuListaan(koeKysymyksetFile);
	koe.koeKysymykset=teeRandomKysymykset(kysymykset);	
	tulostaTiedostoon(koe,kyslkm);
}


/**
 * Tallenetaan Koe .txt-tiedostona
 * @param koe tallenettava olio
 * @param kyslkm tallennettavien kysymysten LKM
 * @throws IOException Jos tietovirta katkeaa, heittää Poikkeuksen
 */
private static void tulostaTiedostoon(Koe koe, int kyslkm) throws IOException {
	BufferedWriter bf;
	bf = new BufferedWriter(new FileWriter(koe.nimi+".txt"));
	for(int i=0; i<kyslkm; i++) {
		bf.write(koe.koeKysymykset.get(i));
		bf.flush();
		bf.newLine();
		} 
	bf.close();
	
	}
 
}
