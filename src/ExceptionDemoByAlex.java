
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ExceptionDemoByAlex extends JPanel implements MouseListener {
    
	protected static int MaxBulkInTruck = 20 ;
	
	protected JComboBox<String> bicycleList;
	protected JTextField itemsNumber;

    public ExceptionDemoByAlex() {
    	
    	String[] bicycleModels = { "Author",
							       "Cannondale",
							       "Cube",
							       "Forward",
							       "GT",
							       "Giant",
							       "Haro",
							       "Merida",
							       "Stark",
							       "Stels",
							       "Trek" };     

        bicycleList = new JComboBox<String>(bicycleModels);
        bicycleList.setSelectedIndex(0);
        
        JButton checkButton = new JButton("Check the Order!");
        checkButton.addMouseListener(this);
        
        JLabel infoLabel = new JLabel( "Items number:" );
        itemsNumber = new JTextField(5);

        add(bicycleList);
        add(infoLabel);
        add(itemsNumber);
        add(checkButton);
    }
    
    protected int getSizeForModel( String model ) {
  		
    		if ( "Author".equals(model) || "Cannondale".equals(model) || "Cube".equals(model) ) {
    			return 1;
    		}
    		else if ( "Forward".equals(model) || "GT".equals(model) || "Giant".equals(model) ) {
    			return 2;
    		}
    		else if ( "Haro".equals(model) || "Merida".equals(model) || "Stark".equals(model) ) {
    			return 3;
    		}
    		else { // "Stels", "Trek"
    			return 4;
    		}    		
		
    }

    public void mouseClicked(MouseEvent e){

    	String [] str = {"OK"};
    	
    	try{
	    	if ( validateOrder() ){
	    		JOptionPane.showOptionDialog( this,"You order is Ok!", 
	                    "Information",  JOptionPane.YES_OPTION, 
	                     JOptionPane.PLAIN_MESSAGE, null, str, "Ok") ;    		
	    	}
    	} catch ( ExceedLimitException exc ) {
    		JOptionPane.showOptionDialog( this,"You exceeded the space limit on " + exc.getOverLimit() + " units!", 
                    "Error",  JOptionPane.YES_OPTION, 
                     JOptionPane.PLAIN_MESSAGE, null, str, "Ok") ;    		
    	}
    	catch (NumberFormatException exc) {
    		JOptionPane.showOptionDialog( this,"The field \"Items number\" is empty or has a wrong number format!", 
                    "Error",  JOptionPane.YES_OPTION, 
                     JOptionPane.PLAIN_MESSAGE, null, str, "Ok") ;     		
    	}
    }
    
    public boolean validateOrder() throws NumberFormatException, ExceedLimitException {
    	int sizeForModel = getSizeForModel( (String)bicycleList.getSelectedItem() ); 
		int itemsNumberConverted = Integer.parseInt(itemsNumber.getText());
    	if (sizeForModel*itemsNumberConverted<=MaxBulkInTruck) {
    		return true ;
    	} else {
    		throw new ExceedLimitException(sizeForModel*itemsNumberConverted - MaxBulkInTruck ) ; 
    	}
    }

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
    
    private static void showGUI() {
    	
        JFrame frame = new JFrame("Unit 7 Demo (by Alex Natalchenko)");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new ExceptionDemoByAlex();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

        //frame.pack();
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {
    	
        javax.swing.SwingUtilities.invokeLater(
        		new Runnable() {
        				public void run() {showGUI();}
        			}
        		);
        
    }
}