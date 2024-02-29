package realofficefurniture;
import javax.swing.*;


public abstract class Furniture
{
    String IDNumber, woodType;
    Double itemPrice;
    int quantity;
    ImageIcon image;

    Furniture()
    {
        IDNumber = JOptionPane.showInputDialog("Product ID Number");
        woodType = JOptionPane.showInputDialog("Wood type");
        quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity"));
    }

    public String getIDNumber()
    {
        return IDNumber;
    }
    
    public String getType()
    {
        return woodType;
    }
    
    public Double getItemPrice()
    {
        return itemPrice;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return (IDNumber +  woodType + quantity);
    }
}
