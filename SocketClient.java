package inetaddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;





public class SocketClient  extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public static JTextArea DollarInput = new JTextArea();
	 public static JTextArea EuroInput = new JTextArea();
	 public static JTextField textField;

	 static JLabel l1;
	 static JLabel l2;
	 static JFrame f;
	 
	 int dolar;
	 DocumentListener documentListener = new DocumentListener() {
	      public void changedUpdate(DocumentEvent documentEvent) {
	        printIt(documentEvent);
	      }
	      public void insertUpdate(DocumentEvent documentEvent) {
	        printIt(documentEvent);
	        System.out.println("inserted");
	      }
	      public void removeUpdate(DocumentEvent documentEvent) {
	        printIt(documentEvent);
	      }
	      private void printIt(DocumentEvent documentEvent) {
	        DocumentEvent.EventType type = documentEvent.getType();
	        String typeString = null;
	        if (type.equals(DocumentEvent.EventType.CHANGE)) {
	          typeString = "(CHANGED KEY) ";
	        }  else if (type.equals(DocumentEvent.EventType.INSERT)) {
	          typeString = DollarInput.getText();
//	        BufferedReader entreeDepuisUtilisateur = new BufferedReader(new InputStreamReader(System.in));

	          Socket socketClient = null;
			try {
				socketClient = new Socket("localhost",7777);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          DataOutputStream sortieVersServeur = null;
			try {
				sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          BufferedReader entreeDepuisServeur = null;
			try {
				entreeDepuisServeur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//	          String phrase = entreeDepuisUtilisateur.readLine();
	          String phrase = DollarInput.getText();
	          System.out.println(phrase);
	          try {
				sortieVersServeur.writeBytes(phrase+'\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          String phraseModifiee = null;
			try {
				phraseModifiee = entreeDepuisServeur.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          System.out.println("RECU DU Serveur:"+phraseModifiee);
	          
	          
	          try {
				socketClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          EuroInput.setText(phraseModifiee); 
	        }  else if (type.equals(DocumentEvent.EventType.REMOVE)) {
	        	typeString = DollarInput.getText();
//		        BufferedReader entreeDepuisUtilisateur = new BufferedReader(new InputStreamReader(System.in));

		          Socket socketClient = null;
				try {
					socketClient = new Socket("localhost",7777);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          DataOutputStream sortieVersServeur = null;
				try {
					sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          BufferedReader entreeDepuisServeur = null;
				try {
					entreeDepuisServeur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//		          String phrase = entreeDepuisUtilisateur.readLine();
		          String phrase = DollarInput.getText();
		          System.out.println(phrase);
		          try {
					sortieVersServeur.writeBytes(phrase+'\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          String phraseModifiee = null;
				try {
					phraseModifiee = entreeDepuisServeur.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          System.out.println("RECU DU Serveur:"+phraseModifiee);
		          
		          
		          try {
					socketClient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          EuroInput.setText(phraseModifiee); 
	          
	        }
	        System.out.print("Type : " + typeString);
	        Document source = documentEvent.getDocument();
	        int length = source.getLength();
	        System.out.println("Current size: " + source);

	      }
	      
	      
	    };
	    
	    class MyTextActionListener implements ActionListener {
	        /** Handle the text field Return. */
	        public void actionPerformed(ActionEvent e) {
	          int selStart = DollarInput.getSelectionStart();
	          int selEnd = DollarInput.getSelectionEnd();

	          DollarInput.replaceRange(textField.getText(), selStart, selEnd);
	          textField.selectAll();
	        }
	    }



public void run()throws IOException {
		
		
		 textField = new JTextField(20);
		 textField.addActionListener(new MyTextActionListener());
		 textField.getDocument().addDocumentListener(documentListener);
		 textField.getDocument().putProperty("name", "Text Field");
		
		
		f = new JFrame("convertisseur");
		DollarInput.getDocument().addDocumentListener(documentListener);
//		EuroInput.setDocument(textField.getDocument());
		

		
		JPanel p = new JPanel();
		l1 = new JLabel("Dollar $ :");
		l2 = new JLabel("Euro € :");
		
		DollarInput.setColumns(4);
		EuroInput.setColumns(4);
		p.add(l1);
		p.add(DollarInput);
		p.add(l2);
		p.add(EuroInput);
		
        f.add(p);
     
        f.setSize(300, 100);
        System.out.println(DollarInput.getDocument().toString());
 
        f.setVisible(true);
        
        
        EuroInput.setText(DollarInput.getText());

    }
	
	public static void main(String args[]) throws IOException {
		SocketClient Client = new SocketClient();
		
		Client.run();
	}
	
}

