package realofficefurniture;

import javax.swing.JOptionPane;

public class Chair extends Furniture
{
        
    Chair()
    {
       super(); 
    }
    
    
    Chair(String IDNumber, String woodType, int quantity, Boolean armrests)
    {
        IDNumber = JOptionPane.showInputDialog("Product ID Number");
        woodType = JOptionPane.showInputDialog("Wood type");
        quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity"));
        armrests = Boolean.parseBoolean(JOptionPane.showInputDialog("Armrests?"));
    }
    
    @Override
    public String toString()
    {
        return (IDNumber +  woodType + quantity);
    }
}
