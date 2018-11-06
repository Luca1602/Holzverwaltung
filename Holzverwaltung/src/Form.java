import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Form extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	
	public Form() {
		WoodStorage woodStorage = new WoodStorage();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 927, 548);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//title
		JLabel lblHolzverwaltung = new JLabel("Holzverwaltung");
		lblHolzverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblHolzverwaltung.setFont(new Font("Arial", Font.PLAIN, 32));
		lblHolzverwaltung.setBounds(322, 23, 231, 51);
		contentPane.add(lblHolzverwaltung);
		
		//Wertefeld Anzahl Holz
		JLabel txtAmountWood = new JLabel("0");
		txtAmountWood.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAmountWood.setBounds(655, 108, 56, 27);
		contentPane.add(txtAmountWood);
		
		//Wertefeld Holz Zustand
		JLabel txtState = new JLabel("");
		txtState.setFont(new Font("Arial", Font.PLAIN, 20));
		txtState.setBounds(655, 137, 194, 27);
		contentPane.add(txtState);
		
		//Error message
		JLabel txtErrorMessage = new JLabel("");
		txtErrorMessage.setFont(new Font("Arial", Font.PLAIN, 18));
		txtErrorMessage.setBounds(141, 416, 708, 43);
		contentPane.add(txtErrorMessage);
		
		//Label "Anzahl Hölzer im Lager"
		JLabel txtAnzahlHolz = new JLabel("Anzahl H\u00F6lzer im Lager:");
		txtAnzahlHolz.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAnzahlHolz.setBounds(410, 111, 222, 24);
		contentPane.add(txtAnzahlHolz);
		
		//label Zustand
		JLabel txtZustand = new JLabel("Zustand H\u00F6lzer:");
		txtZustand.setFont(new Font("Arial", Font.PLAIN, 20));
		txtZustand.setBounds(410, 148, 222, 16);
		contentPane.add(txtZustand);
		
		//Spinner for amount of wood
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 30, 1.0);
		JSpinner woodAmount = new JSpinner(spinnerModel);
		woodAmount.setBounds(193, 151, 54, 43);
		contentPane.add(woodAmount);
		woodAmount.setFont(new Font("Arial", Font.PLAIN, 26));
		
		//button Holz hinzufügen
		JButton btnHolzHinzufgen = new JButton("Holz hinzuf\u00FCgen");
		btnHolzHinzufgen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (txtState.getText() == "unverarbeitet" || txtState.getText() == "-" || txtState.getText() == "") {
					String state = "unvearbeitet";
					double amountDouble = (Double)woodAmount.getValue();
					int amount = (int)amountDouble;
					
					woodStorage.BuyWood(state, amount);
					//try because txtAmountWood doesn't always have a value
					try {
						amount += Integer.parseInt(txtAmountWood.getText());
					}
					finally {
						txtAmountWood.setText(Integer.toString(amount));
						txtState.setText("unverarbeitet");
					}
				}
				else {
					txtErrorMessage.setText("Bitte verarbeitetes Holz verkaufen bevor nächste Anschaffung getätigt wird");
				}
			}
		});
		btnHolzHinzufgen.setBackground(Color.LIGHT_GRAY);
		btnHolzHinzufgen.setFont(new Font("Arial", Font.PLAIN, 26));
		btnHolzHinzufgen.setBounds(54, 249, 263, 64);
		contentPane.add(btnHolzHinzufgen);
		
		//Button Holz trocknen
		JButton btnDry = new JButton("trocknen");
		btnDry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtErrorMessage.setText("");
				if (woodStorage.GetStock() > 0) {
					txtState.setText("Getrocknet");
				}
			}
		});
		btnDry.setFont(new Font("Arial", Font.PLAIN, 21));
		btnDry.setBounds(410, 201, 195, 51);
		contentPane.add(btnDry);
		
		//Button Holz sagen
		JButton btnSaw = new JButton("sagen");
		btnSaw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtErrorMessage.setText("");
				if (txtState.getText() == "Getrocknet" || woodStorage.GetStock() <= 0) {
					txtState.setText("Gesägt");
				}
				else {
					txtErrorMessage.setForeground(Color.RED);
					txtErrorMessage.setText("Nur trockenes Holz kann gesägt werden!");
				}
			}
		});
		btnSaw.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSaw.setBounds(410, 288, 195, 51);
		contentPane.add(btnSaw);
		
		//Button Holz hobeln
		JButton btnSlice = new JButton("hobeln");
		btnSlice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtErrorMessage.setText("");
				if (txtState.getText() == "Gesägt" || woodStorage.GetStock() <= 0) {
					txtState.setText("Gehobelt");
				}
				else {
					txtErrorMessage.setForeground(Color.RED);
					txtErrorMessage.setText("Nur gesägtes Holz kann gehobelt werden!");
				}
			}
		});
		btnSlice.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSlice.setBounds(654, 201, 195, 51);
		contentPane.add(btnSlice);
		
		//Button Holz verkaufen
		JButton btnSell = new JButton("verkaufen");
		btnSell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtErrorMessage.setText("");
				if (woodStorage.GetStock() > 0) {
					txtState.setText("-");
					woodStorage.ClearStorage();
					txtAmountWood.setText("0");
				}
			}
		});
		btnSell.setFont(new Font("Arial", Font.PLAIN, 21));
		btnSell.setBounds(654, 288, 195, 51);
		contentPane.add(btnSell);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(88, 151, 107, 43);
		contentPane.add(txtAmount);
		txtAmount.setFont(new Font("Arial", Font.PLAIN, 26));
		txtAmount.setBackground(Color.WHITE);
		txtAmount.setEditable(false);
		txtAmount.setText("Anzahl:");
		txtAmount.setColumns(10);
	}
}
