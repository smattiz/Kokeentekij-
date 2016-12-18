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
 * TODO: T�M� PIT�� MUUTTA KUNNON OLIOKSI
 */
public class Koe {
	private String nimi;
	private Vector<String> koeKysymykset;
	
	public Koe(String nimiIn) {
		nimi=nimiIn;
		koeKysymykset=null;
	}
	
	
	/**
	 * Asetetaan meid�n File rivi rivilt�  Vectoriin, ett� se saadaan k�ytt��n
	 * @param string file Meid�n filu, jota halutaan hy�dynt��
	 */
 public  static Vector<String> filuListaan(File ourFile) { //Voidaan my�s ihan luoda olio, joka meill� on
	 Vector<String> meid�nLista= new Vector<String>();
	 
	 try {
		BufferedReader bfIn= new BufferedReader(new FileReader(ourFile));
		while(bfIn.readLine() != null) {
			meid�nLista.add(bfIn.readLine()); //luetaan filua rivi kerrallaan meid�n listaan
		} 
		bfIn.close();
		
	 }
	 catch (FileNotFoundException e) { //Tiedet��n, v�lt� toistoa... Olen kuitenkin liian laiska xD
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
	return meid�nLista;	
 }
 
 /**
  * Tehd��n meid�n satunnaiset kysymykset halutusta Vectorista
  * @param kysymysLista vector, joka sis�lt�� kysymykset
  */
 private static  Vector<String> teeRandomKysymykset(Vector<String> kysymysLista) {
	Random rng= new Random();
	Vector<String> meid�nKysymykset= new Vector<String>();
	for(int i=0; i<=10; i++) {
		meid�nKysymykset.add(kysymysLista.get(rng.nextInt(kysymysLista.size()))); 
	}
	return meid�nKysymykset;
 }
	
/**
 * Kokeentekij�, joka kutsuu aliohjelmia halutussa j�rjestyksess� lopulta tulostellen tiedostoon meid�n uuden kokeen
 * @param filePath Tiedoston polku merkkijonona
 * @param koeNimi Tulevan koetiedoston nimi
 * @throws IOException Jos tietovirrassa on ongelmia
 */
public static  void teeUusiKoe(String filePath, String koeNimi,int kyslkm) throws IOException {
	Koe koe=new Koe(koeNimi);
	File koeKysymyksetFile=new File(filePath); //muutetaan Filu Stringiksi, helpompi k�sitell� n�in leikkim�ll�
	Vector<String> kysymykset= filuListaan(koeKysymyksetFile);
	koe.koeKysymykset=teeRandomKysymykset(kysymykset);	
	tulostaTiedostoon(koe,kyslkm);
}


/**
 * Tallenetaan Koe .txt-tiedostona
 * @param koe tallenettava olio
 * @param kyslkm tallennettavien kysymysten LKM
 * @throws IOException Jos tietovirta katkeaa, heitt�� Poikkeuksen
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
