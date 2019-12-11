package Model;


public class Buch {
	//Alle Elemente von Buch initialisieren.
	
		private String Titel;
		private String VerkaufDatum;
		private String Sprache;
		private boolean Gebraucht;
		private boolean Neue;
		private double Menge;
		private String Signatur;		
		private double Preis;

		/**
		 * Standardkonstruktor, macht gar nichts.
		 */
		public Buch() {
		}
		/**
		 * Hier werden alle Elemente des Buchtitels definiert.
		 */
		public Buch(String Identifikator,String Titel,String VerkaufDatum,String Sprache,boolean Gebraucht,boolean Neue,double Menge,String Signatur,double Preis) {
			
			this.Identifikator=Identifikator;
			this.Titel=Titel;
			this.VerkaufDatum=VerkaufDatum;
			this.Sprache=Sprache;
			this.Gebraucht=Gebraucht;
			this.Neue=Neue;
			this.Menge=Menge;
			this.Signatur=Signatur;		
			this.Preis=Preis;
		}

		private String Identifikator;
		public String getIdentifikator() {
			return Identifikator;
		}
		public void setIdentifikator(String identifikator) {
			Identifikator = identifikator;
		}
		public String getTitel() {
			return Titel;
		}
		public void setTitel(String titel) {
			Titel = titel;
		}
		public String getVerkaufDatum() {
			return VerkaufDatum;
		}
		public void setVerkaufDatum(String verkaufDatum) {
			VerkaufDatum = verkaufDatum;
		}
		public String getSprache() {
			return Sprache;
		}
		public void setSprache(String sprache) {
			Sprache = sprache;
		}
		public boolean getGebraucht() {
			return Gebraucht;
		}
		public void setGebraucht(boolean gebraucht) {
			Gebraucht = gebraucht;
		}
		public boolean getNeue() {
			return Neue;
		}
		public void setNeue(boolean neue) {
			Neue = neue;
		}
		public double getMenge() {
			return Menge;
		}
		public void setMenge(double menge) {
			Menge = menge;
		}
		public String getSignatur() {
			return Signatur;
		}
		public void setSignatur(String signatur) {
			Signatur = signatur;
		}
		public double getPreis() {
			return Preis;
		}
		public void setPreis(double preis) {
			Preis = preis;
		}
}

