package realofficefurniture;

import javax.swing.*;
/**
 *
 * @author Demon
 */
public class RealOfficeFurniture 
{
        
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Real Office Furniture");
        GUI panel = new GUI();
        frame.add(panel);
        frame.setSize(1000,700);  
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    
}