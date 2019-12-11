package GUI;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import Model.Buch;

public class BuchDialog extends JDialog implements ActionListener {
	public boolean closedOK = false; // true, wenn Dialog durch OK beendet wird

	// Referenz auf das Model-Objekt als Datenquelle:
	private Buch myBuch;

	// GUI-Elemente als Instanzvariablen:

	private JTextField Identifikator = new JTextField(15);
	private JLabel lblIdentifikator = new JLabel("Identifikator                            ");

	private JTextField Titel = new JTextField(15); // 15 Spalten
	private JLabel lblTitel = new JLabel("Titel                                           ");

	private JTextField VerkaufDatum = new JTextField(15); // 15 Spalten
	private JLabel lblVerkaufDatum = new JLabel("Verkauf Datum                       ");

	// Aufzählung mehrerer Sprachen
	private String wahl;
	private String[] texte = { "Bitte auswaehlen Sie", "Arabisch", "Französich", "Deutsch", "Englisch" };

	// ComboBox hinzufügen "Sprache"
	private JComboBox<String> Sprache = new JComboBox<String>(texte);
	private JLabel lblSprache = new JLabel("      Sprache                                           ");

	// Checkbox hinzufügen "Gebrauch"
	private JRadioButton Gebraucht = new JRadioButton(" Gebraucht ",true);

	// Checkbox hinzufügen " neue "
	private JRadioButton Neue = new JRadioButton(" Neue ",true);

	// GUI-Elemente als Instanzvariablen:
	private JTextField Menge = new JTextField(15); // 15 Spalten
	private JLabel lblMenge = new JLabel("Menge                   ");

	private JTextField Signatur = new JTextField(15); // 15 Spalten
	private JLabel lblSignatur = new JLabel("Signatur                 ");

	private JTextField Preis = new JTextField(12); // 12 Spalten
	private JLabel lblPreis = new JLabel("Preis                      ");
	private JLabel lblEinheit = new JLabel("Euro ");

	// ButtonSchliessen" :
	private JButton ok = new JButton("OK");
	private JButton abbrechen = new JButton("Abbrechen");
	
	
	

	public BuchDialog(Window parent, Buch m) {

	super (parent, "Daten bearbeiten", Dialog.ModalityType.APPLICATION_MODAL );// Modaler Dialog, Titel wird spaeter gesetzt

		myBuch = m;
		// Daten aus Buchsobjekt übernehmen:
		Identifikator.setText(m.getIdentifikator());
		Titel.setText(m.getTitel());
		VerkaufDatum.setText(m.getVerkaufDatum());
		Sprache.setSelectedItem("" + m.getSprache());

		// Menge (double-Wert für Note in formatierten String mit 2 Dezimalstellen:)
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator(','); // Dezimalkomma statt Punkt
		DecimalFormat df = new DecimalFormat("0.00", dfs); // 2 Dezimalstellen
		Menge.setText(df.format(m.getMenge()));

		// Preis
		DecimalFormatSymbols dfs1 = DecimalFormatSymbols.getInstance();
		dfs1.setDecimalSeparator(','); // Dezimalkomma statt Punkt
		DecimalFormat df1 = new DecimalFormat("0.00", dfs1); // 2 Dezimalstellen
		Preis.setText(df1.format(m.getPreis()));

		// Layout festlegen und GUI-Elemente hinzufügen:
		//setLayout(new FlowLayout());

		/*
		 * add(lblIdentifikator); add(Identifikator); add(lblTitel); add(Titel);
		 * add(lblVerkaufDatum); add(VerkaufDatum); add(lblSprache); add(Sprache);
		 * add(Gebraucht); add(Neue); add(lblMenge); add(Menge); add(lblSignatur);
		 * add(Signatur); add(lblPreis); add(Preis);
		 * 
		 * add(ok); add(abbrechen); pack();
		 */

		/**
		 * // Layout festlegen und GUI-Elemente hinzufugen (BorderLayout und FlowLayout)
		 */

		class WestPanel extends Box {
			/**
			 * Layout in FirstLine entwerfen.
			 */

			class ErsteLinie extends JPanel {
				/**
				 * Layout in ThirdLine entwerfen.
				 */
				public ErsteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblIdentifikator);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Identifikator);
				}
			}

			class ZweiteLinie extends JPanel {
				/**
				 * Layout in FourthLine entwerfen.
				 */
				public ZweiteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblTitel);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Titel);
				}
			}

			class DritteLinie extends JPanel {
				/**
				 * Layout in FifthLine entwerfen.
				 */
				public DritteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblVerkaufDatum);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(VerkaufDatum);
				}
			}

			class VierteLinie extends JPanel {
				/**
				 * Layout in SixthLine entwerfen.
				 */
				public VierteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblSprache);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Sprache);
				}
			}

			class FuenfteLinie extends JPanel {
				/**
				 * Layout in SeventhLine entwerfen.
				 */
				public FuenfteLinie() {

					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(Gebraucht);
				}
			}

			class SechsteLinie extends JPanel {
				/**
				 * Layout in EighthLine entwerfen.
				 */
				public SechsteLinie() {

					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(Neue);
				}
			}

			class SiebteLinie extends JPanel {
				/**
				 * Layout in NinthLine entwerfen.
				 */
				public SiebteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblMenge);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Menge);
				}
			}

			class AchteLinie extends JPanel {
				/**
				 * Layout in TenthLine entwerfen.
				 */
				public AchteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblSignatur);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Signatur);
				}
			}

			class NeunteLinie extends JPanel {
				/**
				 * Layout in EleventhLine entwerfen.
				 */
				public NeunteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblPreis);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Preis);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(lblEinheit);
				}
			}

			/**
			 * BoxLayout in WestPanel entwerfen.
			 */
			public WestPanel() {

				// 11 Zeilen sukzessiv aufkommen
				// hier habe ich BoxLayout benutzt , umd die compenent unter einander zu ordnen
				super(BoxLayout.Y_AXIS);
				add(new ErsteLinie());
				add(new ZweiteLinie());
				add(new DritteLinie());
				add(new VierteLinie());
				add(new FuenfteLinie());
				add(new SechsteLinie());
				add(new SiebteLinie());
				add(new AchteLinie());
				add(new NeunteLinie());

			}
		}

		// Am SouthPanel gibt es zwei Button "OK" und "Abbrechen" und FlowLayout genutzt
		// wird.
		class SouthPanel extends JPanel {

			/**
			 * Layout in SouthPanel entwerfen.
			 */
			public SouthPanel() {
				setLayout(new FlowLayout(FlowLayout.RIGHT));
				add(ok);
				add(abbrechen);
			}
		}
		class CenterPanel extends JPanel{
			
			public CenterPanel() {
				
				//BorderLayout wird benutzt und ein Bild hinzufügen in guiListe 
				setLayout(new BorderLayout());
				add(new JLabel(new ImageIcon("image\\Book1.jpg")),BorderLayout.EAST);
				
			}
		}
		// Layout setzen und GUI-Elemente hinzufügen:
		setLayout(new BorderLayout());
		add(new WestPanel(), BorderLayout.WEST);
		add(new SouthPanel(), BorderLayout.SOUTH);
		add(new CenterPanel(), BorderLayout.CENTER);

		// Event-Handler installieren:
		ok.addActionListener(this);
		abbrechen.addActionListener(this);

		this.setTitle("Neue Buch hinzufügen");
		this.setSize(620, 500); // Breite: 1200 Pixel; Hoehe: 550 Pixel
		this.setLocation(100,50); // Koordination: x=100, y=50;
		setVisible(true); // Fensterrahmen sichtbar machen

		// verschiben und reseisen
		this.setResizable(true);

	}

	/**
	 * Klicken auf Button "Hinzufügen" auswerten:
	 */
	public void actionPerformed(ActionEvent e) {

		// Schaltfläche entnehmen:
		Object source = e.getSource();
		if (source == ok) {
			// Werte des Buch-Objekts entsprechend den Dialogelementen aktualisieren:

			myBuch.setIdentifikator(Identifikator.getText());
			myBuch.setTitel(Titel.getText());
			myBuch.setVerkaufDatum(VerkaufDatum.getText());
			myBuch.setSprache((String) Sprache.getSelectedItem());
			//Group box
			myBuch.setGebraucht(Gebraucht.isSelected());
			myBuch.setNeue(Neue.isSelected());

			// Menge kann sien ,dass sie Dezimalkomma hat .
			double Mengee = 0; // Voreinstellung(Menge =0.0), falls Textumwandlung schief geht

			// To format a number for a different locale. specify it in the call to
			// getInstance.
			// Formalieren Sie eine Nummer für eine andere Locale,geben Sie es in dem Aufruf
			// von getInstance.
			NumberFormat nf = NumberFormat.getInstance();
			try {
				Number sd = nf.parse(Menge.getText());
				Mengee = sd.doubleValue();
			} catch (ParseException ex) {
				System.out.print("Ungueltiger Wert");
			}
			myBuch.setMenge(Mengee);
			
			// Preis hat Dezimalkomma.
			double Preise = 0; // Voreinstellung(Spielauer =0.0), falls Textumwandlung schief geht
			
			NumberFormat nf1 = NumberFormat.getInstance();
			try {
				Number sd = nf1.parse(Preis.getText());
				Preise = sd.doubleValue();

			} catch (ParseException ex) {
				System.out.print("Ungueltige Eingabe");
			}
			myBuch.setPreis(Preise);
			// true,wenn Dialog durch Ok beendet wird.
			closedOK = true;
		}
		// Dialog schließen:
		setVisible(false);
	}

}