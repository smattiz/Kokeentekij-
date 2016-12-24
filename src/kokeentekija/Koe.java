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
 * A class used to make our new Exam and manipulate given questions 
 * @author Matti Keskiniemi, If you have improvement ideas, please do contact me :)
 * @version 1.0 Completed 24.12.2016 as a christmas gift
 */
public class Koe {
	private String nimi;
	private Vector<String> koeKysymykset;
	
	public Koe(String nimiIn) {
		nimi=nimiIn;
		koeKysymykset=null;
	}
	
	
	/**
	 * Asetetaan meid‰n File rivi rivilt‰  Vectoriin, ett‰ se saadaan k‰yttˆˆn / Users Questions line per line to Vector
	 * @param string file Meid‰n filu, jota halutaan hyˆdynt‰‰
	 */
 public  static Vector<String> filuListaan(File ourFile) {  
	 Vector<String> meid‰nLista= new Vector<String>();
	 
	 try {
		BufferedReader bfIn= new BufferedReader(new FileReader(ourFile));
		while(bfIn.readLine() != null) {
			meid‰nLista.add(bfIn.readLine()); //luetaan filua rivi kerrallaan meid‰n listaan
		} 
		bfIn.close();
		
	 }
	 catch (FileNotFoundException e) { //Tiedet‰‰n, v‰lt‰ toistoa... Olen kuitenkin liian laiska xD
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
	return meid‰nLista;	
 }
 
 
 /**
  * Tehd‰‰n meid‰n satunnaiset kysymykset halutusta Vectorista
  * @param kysymysLista vector, joka sis‰lt‰‰ kysymykset
  * TODO: Pit‰‰ tarkistaa, ettei ole samoja kysymyksi‰...
  */
 private static  Vector<String> teeRandomKysymykset(Vector<String> kysymysLista, int kysLKM) {
	Random rng= new Random();
	Vector<String> meid‰nKysymykset= new Vector<String>();
	for(int i=0; i<=kysLKM; i++) {
		meid‰nKysymykset.add(kysymysLista.get(rng.nextInt(kysymysLista.size()))); 
	}
	meid‰nKysymykset=tarkistaTuplaKysymykset(meid‰nKysymykset,kysymysLista);
	return meid‰nKysymykset;
 }
	
 
 /**
  * Tarkistetaan "kuplalajittelulla" mahdolliset tuplakysymykset ja vaihdetaan ne. Riskin‰ on, ett‰ tuplakysymykset lis‰‰ntyy
  * @param kysymykset Jo arvotut kysymykset, joista tarkistamme tuplat /already randomised questions
  * @param kysymysLista Alkuper‰inen lista, josta arvottiin ensimm‰iset kysymykykset / original questions vector
  * @return 
  */
 private static Vector<String> tarkistaTuplaKysymykset(Vector<String> kysymykset, Vector<String> kysymysLista) {
	 Random rng=new Random();
	 for(int i=0; i<kysymykset.size(); i++) {
		 
		 for(int j=1; j<kysymykset.size(); j++) {
			 
			 String s1=kysymykset.get(i); //Muutetaan Stringeiksi vertailun helpottamiseksi
			 String s2=kysymykset.get(j);
			 	if(s1.equals(s2)) {
			 		
			 		kysymykset.remove(j);
			 		kysymykset.add(j, kysymysLista.get(rng.nextInt(kysymysLista.size())));
			 	}
		 }
	 }
	return kysymykset;	 
 }
 
 
/**
 * Kokeentekij‰, joka kutsuu aliohjelmia halutussa j‰rjestyksess‰ lopulta tulostellen tiedostoon meid‰n uuden kokeen
 * @param filePath Tiedoston polku merkkijonona / path of file, as a String
 * @param koeNimi Tulevan koetiedoston nimi / our new Exams name
 * @throws IOException Jos tietovirrassa on ongelmia If there's problems in data stream E.g connection lost etc
 * TODO: Make this better and more stable
 */
 public static  void teeUusiKoe(String filePath, String koeNimi,int kyslkm) throws IOException {
	Koe koe=new Koe(koeNimi);
	File koeKysymyksetFile=new File(filePath); //muutetaan Filu Stringiksi, helpompi k‰sitell‰ n‰in leikkim‰ll‰
	Vector<String> kysymykset= filuListaan(koeKysymyksetFile);
	koe.koeKysymykset=teeRandomKysymykset(kysymykset,kyslkm);	
	tulostaTiedostoon(koe,kyslkm);
}


/**
 * Tallenetaan Koe .txt-tiedostona
 * @param koe tallenettava olio / Our Exam we want to make
 * @param kyslkm tallennettavien kysymysten LKM / Amount of questions wanted in exam, since we don't want it in object, althought it could be good idea
 * @throws IOException Jos tietovirta katkeaa, heitt‰‰ Poikkeuksen / If datastream is interrupted by any way
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
