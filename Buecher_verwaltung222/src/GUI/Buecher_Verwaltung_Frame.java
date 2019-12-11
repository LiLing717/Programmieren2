package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.plaf.ListUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.xml.soap.Text;
import Model.Buch;
import Model.Buecher;
import Model.BuecherDAO;

//igor.grossklaus@student.jade-hs.de
/**
 *Anwendungsfenster mit Men�s. 
 * Anwendungsfenster mit einigen Button-Funktionen.
 * Klicken auf den Button "Hinzuf�gen". Mann kann hier einen neuen BUchtitel hinzuf�gen.(Funktion: Hinzuf�gen eines neues Buchtitels)
 * Klicken auf den Button "�ndern", der in der Liste stellt, kann man diesen Buchtitel �ndern.
 * (Funktion: �ndern eines vorhandenen Buchtitels)
 * Ausw�hlen einen Buchtitel,der in der Liste stellt. Danach klicken auf den Button"L�schen", kann man diesen Buchtitel l�schen.
 * (Funktion: Entfernen eines vorhandenen Buchtitel)
 * Schreiben den ganzen Buchtitel und klicken auf den Button "Suchen", kann man diesen Buchtitel suchen. Der gesuchte Buchtitel
 * wird markiert.(Funktion: Suchen eines Titels)
 *
 */
public class Buecher_Verwaltung_Frame extends JFrame implements ActionListener  {
	/**
	 * Konstruktor initialisiert den Dialog.
	 * Das Dialogfenster hat keine Daten, wenn ein neues Buch angelegt wird. In diesem
	 * Fall ist das �bergebenen Buch-Objekt leer.
	 * Wird ein vorhandener Buch bearbeitet, werden dessen Daten zu Initialisierung
	 * der GUI-Elemente verwendet.
	 */
	public static void main(String[] args) {
		
		new Buecher_Verwaltung_Frame();

	}
	
	Buecher buch = new Buecher();  // package Sichtbarkeit, damit GUIBuecherliste zugreifen kann
	// GUI-Elemente als Instanzvariablen:
	private GUI_Buecher_Liste guiListe = new GUI_Buecher_Liste(this);
	
	// GUI-Elemente als Instanzvariablen:

	        private JTextField 		Identifikator      =   new JTextField(15);
	        private JLabel			lblIdentifikator   =   new JLabel("Identifikator");
	        //Button "Hinzuf�gen", "�ndern" und "L�schen" 
			private JButton			Botten_Hinzufuegen		  = new JButton("  Hinzuf�gen  ");
			private JButton			Botten_Aendern = new JButton("      �ndern      ");
			private JButton			Botten_Loeschen	  = new JButton("     L�schen     ");
			private JButton			Botten_Anzeigen = new JButton("    Anzeigen    ");
			
			//Button "Suchen" und "Schliessen" :
			private JTextField      Suchen  = new JTextField("Buchtitel eingeben",15); // 15 Spalten
			private JButton         Botten_Suchen  = new JButton("Suchen");
			
			private JButton			Botten_Schliessen = new JButton("Schlie�en");
		   
			/**
			 * Men�leiste f�r das Anwendungsfenster.
			 */
			class MenuLeiste extends JMenuBar {

				/**
				 * Konstruktor initialisiert die Men�leiste.
				 */
				public MenuLeiste () {

					add (new DateiMenu());
				}


				/**
				 * Men� "Datei".
				 */
				class DateiMenu extends JMenu {

					/**
					 * Konstruktor initialisiert das Men�.
					 */
					public DateiMenu () {

						super ("Datei"); //Konstruktur der SuperKlasse wird angerufen.

						add (new DateiOeffnenItem());//Hinzuf�grn eine neue DateiOeffenItem;
						addSeparator();
						add (new DateiSpeichernItem());//Hinzuf�grn eine neue DateiOeffenItem;
					}


					/**
					 * Men�eintrag f�r "Datei �ffnen"
					 */
					class DateiOeffnenItem extends JMenuItem implements ActionListener {

						/**
						 * Konstruktor initialisiert den Men�eintrag.
						 */
						public DateiOeffnenItem () {
							super ("�ffnen");			// Verbinden �ffnen-MenuItem mit MeunItem.
							addActionListener (this);	// Reaktion auf Klicken
						}

						/**
						 * Reagieren auf Klick. 
						 */
						public void actionPerformed(ActionEvent e) {

							// Standarddialog zum Ausw�hlen einer Datei:
							JFileChooser fc = new JFileChooser();
							int returnValue = fc.showOpenDialog(Buecher_Verwaltung_Frame.this);
							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File file = fc.getSelectedFile();
								String fileName = file.toString();
								BuecherDAO dao = new BuecherDAO (fileName, false);
								try {
									dao.read(buch);
									dao.close();
								} 
								catch (IOException ex) {
								}
								updateGUI();
							}
						}
					}

					/**
					 * Men�eintrag f�r "Datei Speichern"
					 */
					class DateiSpeichernItem extends JMenuItem implements ActionListener {

						/**
						 * Konstruktor initialisiert den Men�eintrag.
						 */
						public DateiSpeichernItem () {
							super ("Speichern unter");
							addActionListener (this);
						}

						/**
						 * Reagieren auf Klick. 
						 */
						public void actionPerformed(ActionEvent e) {

							// Standarddialog zum Ausw�hlen einer Datei:
							JFileChooser fc = new JFileChooser();
							int returnValue = fc.showOpenDialog(Buecher_Verwaltung_Frame.this);
							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File file = fc.getSelectedFile();
								String fileName = file.toString();
								BuecherDAO dao = new BuecherDAO (fileName, true);
								try {
									dao.write(buch);
									dao.close();
								} 
								catch (IOException ex) {
								}
							}
						}
					}


				}

			}

		    /**
			 * // Layout festlegen und GUI-Elemente hinzufugen (BorderLayout und FlowLayout)
			 */
			// Am SouthPanel gibt es zwei Button "Hinzufuegen" , "Aendern" und "Loeschen" und (FlowLayout) genutzt wird.
			class SouthPanel extends JPanel {
				
				class Link extends JPanel {
					/**
					 * Layout in Link entwerfen.
					 */
					public Link() {
						setLayout (new FlowLayout(FlowLayout.LEFT));

					}
				}
				class Center extends JPanel {
					/**
					 * Layout in Center entwerfen.
					 */
					public Center() {
						setLayout (new FlowLayout(FlowLayout.CENTER));
						add (Botten_Suchen);
						add (Suchen );
					}
				}
				class Recht extends JPanel {
					/**
					 * Layout in Recht entwerfen.
					 */
					public Recht() {
						setLayout (new FlowLayout(FlowLayout.RIGHT));
						add(Botten_Schliessen);
					}
				}
				/**
				 * Layout in SouthPanel entwerfen.
				 */
				public SouthPanel() {	
					setLayout(new GridLayout(0,3 , 2, 2));
					
					add (new Link());
					add (new Center());
					add (new Recht());
					
				}
				
				
			}

			class EasthPanel extends Box {
				public EasthPanel() {

					// 11 Zeilen sukzessiv aufkommen
					// hier habe ich BoxLayout benutzt , umd die compenent unter einander zu ordnen
					super(BoxLayout.Y_AXIS);					
					add(new ErsteLinie());
		            add(new ZweiteLinie()); 
			        add(new DritteLinie()); 
			        add(new VierteLinie());

			 

				}

				class ErsteLinie extends JPanel {
					/**
					 * Layout in ThirdLine entwerfen.
					 */
					public ErsteLinie() {
						setLayout(new FlowLayout(FlowLayout.CENTER));
						add(Botten_Hinzufuegen);	
						
					}
				}
				class ZweiteLinie extends JPanel {
					/**
					 * Layout in FourthLine entwerfen.
					 */
					public ZweiteLinie() {
						setLayout(new FlowLayout(FlowLayout.CENTER));
						add(Botten_Aendern);	

					}
				}

				class DritteLinie extends JPanel {
					/**
					 * Layout in FifthLine entwerfen.
					 */
					public DritteLinie() {
						setLayout(new FlowLayout(FlowLayout.CENTER));
						add(Botten_Loeschen);

					}
				}

				class VierteLinie extends JPanel {
					/**
					 * Layout in SixthLine entwerfen.
					 */
					public VierteLinie() {
						setLayout(new FlowLayout(FlowLayout.CENTER));
						add(Botten_Anzeigen);
					}
				}

			}

	/*
	 * class CenterPanel extends JPanel {
	 * 
	 * public CenterPanel() {
	 * 
	 * 
	 * 
	 * 
	 * } }
	 */
			
			public Buecher_Verwaltung_Frame() {
				
			
				// Reaktion beim Schlie�en des Fensters festlegen (statt WindowEventHandler):
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				
				// Men�zeile installieren:
				setJMenuBar (new MenuLeiste());
				//add (guiListe);
				
				// Studentendatenliste in GUI-Liste �bertragen:
				guiListe.setBuecher(buch);
				
				// Layout setzen und GUI-Elemente hinzuf�gen:
				setLayout (new BorderLayout());
				add (new SouthPanel(), BorderLayout.SOUTH);
				//add (new CenterPanel(), BorderLayout.CENTER);
				add (guiListe        , BorderLayout.CENTER);
				add (new EasthPanel()     , BorderLayout.EAST);
				
				// Event-Handler installieren:

				Botten_Hinzufuegen.addActionListener (this);
				Botten_Aendern.addActionListener (this);
				Botten_Loeschen.addActionListener (this);
				Botten_Anzeigen.addActionListener (this);
				Botten_Suchen.addActionListener (this);
				Botten_Schliessen.addActionListener (this);
				
				this.setTitle ("Bucher Verwaltung");
				this.setSize (900, 550);	// Breite: 1200 Pixel; Hoehe: 550 Pixel
				this.setLocation(100,50);   //Koordination: x=100, y=50;
				setVisible (true);	// Fensterrahmen sichtbar machen
				//verschiben und reseisen
				this.setResizable(true);

			   }		

			/**
			 * Klicken auf Button "Hinzuf�gen" auswerten:
			 */

			public void actionPerformed (ActionEvent e) {

				if(e.getActionCommand().equals("  Hinzuf�gen  ")){
					// Neues Buch-Objekt erzeugen:
					Buch m = new Buch();
					// Dialog zum Bearbeite des neuen Objekts zeigen:
					BuchDialog dlg = new BuchDialog(this,m);
					
					if (dlg.closedOK) {
						// Neuen Buecher hinzuf�gen:
						buch.addBuch (m);					
						// GUI-Elemente mit Daten aktualisieren:
						updateGUI();
						System.out.print("Buch hinzugefuegt");

					}
				}
				
				/**
				 * Klicken auf Button "�ndern" auswerten:
				 */
				else if (e.getActionCommand().equals("      �ndern      ")){
					//Buch-Objekt w�hlen:
					int[] items = guiListe.getSelectedIndices();
					
					//das ausw�hlende Buch-Objekt �ndern:
					int i = items[0];
					Buch m = buch.get(i);
					BuchDialog dlg = new BuchDialog(this,m);
					if(dlg.closedOK){
						
						// GUI-Elemente mit Daten aktualisieren:
						updateGUI();
					}
				}

				
				
				
				/**
				 * Klicken auf Button "L�schen" auswerten:
				 */
				else if(e.getActionCommand().equals("     L�schen     ")){
					
					//Buch-Objekt w�hlen:
					int[] items = guiListe.getSelectedIndices();
					int decrease = 0;
					
					//Mehre Buch-Objekt l�schen:
					for(int item:items){
						buch.remove(item - decrease);
						decrease++;
					}
					
					JOptionPane.showConfirmDialog(null,
                            "You are going to delete you history.\nAre you sure?",
                            "Delete History",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE);
					// GUI-Elemente mit Daten aktualisieren:
					updateGUI();
				}
               else if(e.getActionCommand().equals("    Anzeigen    ")){

					// GUI-Elemente mit Daten aktualisieren:
					updateGUI();
				}

				
				
				
				
				/**
				 * Klicken auf Button "Suchen" auswerten:
				 */
				else if (e.getActionCommand().equals("Suchen")){

					ListModel<String> model = guiListe.getModel();

					//erhalten die eingegebene Titel
					//public String trim(), benuztet man diese Methode, wenn schreibt man Leerzeichen am Anfang und am Ende  keine Behinderung f�r Erhaltung des Titels 			     
					String suchtext = Suchen.getText().trim();

					//erhalten Anzahl der Buchtitel
					int size = model.getSize();

					//Die ganze Buchtitel pr�fen einzeln.
					for (int i = 0; i < size; i++) {
						Object o = model.getElementAt(i);
						//Hier kann man pr�fen, ob es passende Buchtitel in guiListe gibt   
						if (o.equals(suchtext)) {
							guiListe.setSelectedIndex(i);
							return;
						}
					}
					//Wenn keine passende Buchtitel gesucht wird, zeigt ein Dialog " Not found".
					guiListe.setSelectedIndex(-1);   
					JOptionPane.showMessageDialog(this, "Nicht gefunden. "); 
					return;   
				}
				
				
				
				
				else if(e.getSource()== Botten_Schliessen){
					JOptionPane.showConfirmDialog(null,
                            "Close The program now?",
                            "Warning",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
			
			}
			
			
			void updateGUI () {

				// Buecherdatenliste in GUI-Liste �bertragen:
				guiListe.setBuecher(buch);
				
				// Auswertung f�r Anzahl Buecher aktualisieren:
				
				lblIdentifikator.setText(""+buch.size());	
			}




		}

	
	



