package kokeentekija;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class KokeenTekijaController {


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
		//	kysymyksetLabel.setText(avattuFilu.getName());
			
		}
		return avattuFilu.getAbsolutePath();
		
    }

    @FXML
    void NimeaKoe() {
    	KoeNimiLabel.getText();
    }

    @FXML
    void teeKoe() throws IOException {
    	String filePath=Etsi();
    	String lkm= kysymysLKMLabel.getText();
    	int theLKM=Integer.parseInt(lkm);
    	Koe.teeUusiKoe(filePath,KoeNimiLabel.getText(),theLKM);

    }

    @FXML
    void kysymysLKM() {
    	//String lkm= kysymyksetLabel.getText();
    	//int theLKM=Integer.parseInt(lkm);
      //  Koe.annaLKM(theLKM);
    	
    	

    }
}
