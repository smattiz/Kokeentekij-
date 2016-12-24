package kokeentekija;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class KokeenTekijaController {

	
	@FXML
    private MenuItem menuLopeta;

    @FXML
    private MenuItem tietojaMenuButton;

    @FXML
    private MenuItem menuK�ytt�;

    @FXML
    private Button EtsiButton;

    
    @FXML
    private Button TeeKoeButton;

    
    @FXML
    private TextField kysymysLKMLabel;

    
    @FXML
    private TextField kysymyksetLabel;

    
    @FXML
    private TextField KoeNimiLabel;

    
    @FXML
    String Etsi() throws IOException {
    	
    	FileChooser fc= new FileChooser();
		fc.setTitle("Valitse avattava tiedosto");
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text files","*.txt")); //vain.txt-tiedostot
		
		File avattuFilu= fc.showOpenDialog(TeeKoeButton.getScene().getWindow());	//getScenejne, koska tarvitte Parent Windowsin toimiakseen
		if(avattuFilu != null) {
			
		}
		return avattuFilu.getAbsolutePath();
		
    }

    
    @FXML
    void NimeaKoe() {
    	KoeNimiLabel.getText();
    }

    
    @FXML
    /**
     * "Etsi kysymykset ja Luo Koe-napin toiminta, joka my�s k�yt�nn�ss� aktivoi Koe-luokan
     * @throws IOException Jos koekysymystiedoston luku h�iriintyy
     */
    void teeKoe() throws IOException {
    	String filePath=Etsi();
    	String lkm= kysymysLKMLabel.getText();
    	int theLKM=Integer.parseInt(lkm);
    	Koe.teeUusiKoe(filePath,KoeNimiLabel.getText(),theLKM);
    }
    
    
  
    @FXML
    /**
     * FXML-elementti, joka aktivoi Ohjeen n�ytt�misen.
     */
    void naytaOhje() {
    	Alert help=new Alert(AlertType.INFORMATION);
    	help.setHeaderText("Ohje");
    	help.setContentText("Anna ohjelmalle nimi, mink� nimisen tiedoston haluat, sek� kysymysten lukum��r�. T�m�n j�lkeen "
    			+ "paina Tee meille koe-nappia, josta saat etsi� tiedoston, josta kysymykset valitaan. Uusi koe ilmestyy sinne, mist� t�m� ohjelma "
    			+ "on ajettu!");
    	help.showAndWait();
    }

    @FXML
    void naytaTietoja() {
    	Alert info= new Alert(AlertType.INFORMATION);
    	info.setHeaderText("Tietoja ohjelmasta");
    	info.setContentText("Kokeentekij� versio 1.0 \n"
    			+ "Tekij�: Matti Keskiniemi \n"
    			+ "Ongelmatilanteissa ota yhteytt�: mazattimies@gmail.com");
    	info.showAndWait();
    }

    @FXML
    void suljeOhjelma() {
    	System.exit(0);
    }
}
