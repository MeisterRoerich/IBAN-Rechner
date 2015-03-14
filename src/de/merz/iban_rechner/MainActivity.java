package de.merz.iban_rechner;

import java.math.BigInteger;

import de.merz.iban_rechner.R;
import android.R.string;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


@SuppressWarnings("unused")
public class MainActivity extends Activity{

	//Startseite	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//Menü initialisieren
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		Bundle icicle = null;
		super.onCreate(icicle);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	//Funktion Delete() für Schaltfläche Leeren 
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.delete:
			Delete();
			return super.onOptionsItemSelected(item);
		}
		return true;

	}

	//Funtkion für Berechnen Schaltfläche
	public void Calculate(View view){
		//Variablen deklarieren
		String knr , blz, bbanString, cs, iban;
		BigInteger bban,temp;
		BigInteger mod = new BigInteger("97");
		BigInteger chs = new BigInteger("98");
		BigInteger ten = new BigInteger("10");
		int de = 1314;

		Reset();
		//Felder auslesen
		EditText kField = (EditText)findViewById(R.id.editText1);
		EditText bField = (EditText)findViewById(R.id.editText2);
		EditText iField = (EditText)findViewById(R.id.editText3);

		//Länge der Kontonummer mussn gültig sein
		if(kField.getText().toString().length() < 7 || kField.getText().toString().length() > 10 ){ 
			kField.setError("Die Kontonummer muss zwischen 8 und 10 Zeichen lang sein");
			return;
		}

		//BLZ muss 8-stellig sein, sonst kommt eine Fehlermeldung
		if(bField.getText().toString().length() != 8){
			bField.setError("Die Bankleitzahl muss 8-stellig sein");
			return;
		}else

			//Felder in Strings übertragen
			knr = kField.getText().toString();
			blz	= bField.getText().toString();

		//Kontonummer um Nullen auf 10 Stellen auffüllen
		while(knr.length() < 10 ){
			knr = "0" + knr;       	
		}


		bbanString = blz + knr + de + "00"; //BBAN (Zwischenwert) als String zusammensetzten	
		bban = new BigInteger(bbanString); // In Bigintger umwandeln
		temp = bban.mod(mod); // Modulo 97
		temp = chs.subtract(temp); //98- Modulo 97 von BBAN

		//Prüferziffer um 0 ergänzen, wenn sie nur einstellig ist
		if(temp.compareTo(ten) == -1){ // <10
			cs = "0" + temp.toString();
		}else cs = temp.toString();;

		//IBAN als String aus Länderkürzel + Prüfziffer und BBAN zusammensetzten und das Feld im UI setzten
		iban = "DE" + cs + blz + knr;
		iField.setText(iban);
	}



	// Funktion Überprüft IBAN auf Gültigkeit und setzt dann die Überschrift entsprechend farbig
	public void Check(View view){
		TextView text=(TextView)findViewById(R.id.tvIBAN);
		EditText iField = (EditText)findViewById(R.id.editText3);
		String iban, temp;
		BigInteger bigint;
		BigInteger mod = new BigInteger("97");

		Reset();
		iField.setError(null);
		
		if(iField.getText().toString().length() != 22){ 
			iField.requestFocus();
			iField.setError("Die IBAN muss 22 Stellen besitzen");
			return;
		}
		
		//Prüfalgorithmus
		iban = iField.getText().toString();
		//DE abschneiden
		temp = iban.substring(2);
		//Länderkürzel als Zahl anhängen
		temp = temp + "1314";
		//Prüfziffer hinten anhägen
		temp = temp.substring(2) + temp.substring(0, 2);
		//In biginteger umwandeln und Modulo bilden
		bigint = new BigInteger(temp);
		bigint = bigint.mod(mod);
		temp = bigint.toString();

		if(temp.equals("1")){
			text.setText("IBAN gültig");
			text.setTextColor(Color.GREEN); //Textfarbe grün
		}else{
			text.setText("IBAN ungültig");
			text.setTextColor(Color.RED); //Textafrbe rot
		}
		temp= "";

	}

	//Funktion für Leeren
	private void Delete(){
		EditText kField = (EditText)findViewById(R.id.editText1);
		EditText bField = (EditText)findViewById(R.id.editText2);
		EditText iField = (EditText)findViewById(R.id.editText3);
		kField.setText("");
		bField.setText("");
		iField.setText("");
		Reset();
		return;
	}

	//Funktion um Überschrift IBAN zurückzusetzten
	private void Reset(){
		TextView text=(TextView)findViewById(R.id.tvIBAN);
		text.setText(getString(R.string.IBAN)); // Setzt auf String in strings.xml
		text.setTextColor(Color.BLACK);	
	}

}
