package realofficefurniture;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI extends JPanel implements ActionListener
{
    private JLabel tableImage, deskImage, tableImage2, 
            deskImage2, deskImage3, deskImage4, 
            chairImage, chairImage2;
    
    private JButton totalPrice, table, Save, Desk, Load, 
            Clearall, Summary, Chair;
    
    private ImageIcon chair1, chair2, table1, table2, 
            desk1, desk2, desk3, desk4;
    
    GUI()
    {
    
        this.setLayout(new GridLayout(4,4));
        
        chair1 = new ImageIcon("Chair1.png");
        chair2 = new ImageIcon("Chair2.png");
        table1 = new ImageIcon("Table1.png");
        table2 = new ImageIcon("Table2.png");
        desk1 = new ImageIcon("Desk1.png");
        desk2 = new ImageIcon("Desk2.png");
        desk3 = new ImageIcon("Desk3.png");
        desk4 = new ImageIcon("Desk4.png");
        
        
        totalPrice = new JButton("Total Price");
        table = new JButton("Add Table"); //
        Save = new JButton("Save"); //
        Desk = new JButton("Add Desk");
        Load = new JButton("Load");
        Clearall = new JButton("Clear All");
        Summary = new JButton("Summary");
        Chair = new JButton("Add Chair");
        tableImage = new JLabel(table1);
        tableImage2 = new JLabel(table2);
        deskImage = new JLabel(desk1);
        deskImage2 = new JLabel(desk2);
        deskImage3 = new JLabel(desk3);
        deskImage4 = new JLabel(desk4);       
        chairImage = new JLabel(chair1);
        chairImage2 = new JLabel(chair2);
                
        Chair.addActionListener(this);
        table.addActionListener(this);
        Save.addActionListener(this);
        Desk.addActionListener(this);
        Load.addActionListener(this);
        Clearall.addActionListener(this);
        Summary.addActionListener(this);
        totalPrice.addActionListener(this);
        
        this.add(Chair);
        this.add(chairImage);
        this.add(chairImage2);
        this.add(totalPrice);
        this.add(table);
        this.add(tableImage);
        this.add(tableImage2);
        this.add(Save);
        this.add(Desk);
        this.add(deskImage);
        this.add(deskImage2);
        this.add(Load);
        this.add(Clearall);
        this.add(deskImage3);
        this.add(deskImage4);
        this.add(Summary);
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(Chair))
        {
            Chair newchair = new Chair();
        }
        else if (e.getSource().equals(table))
        {
            System.out.println("table");
        }
        else if (e.getSource().equals(Save))
        {
            System.out.println("Save");
        }
        else if (e.getSource().equals(Desk))
        {
            System.out.println("Desk");
        }
        else if (e.getSource().equals(Load))
        {
            System.out.println("Load");
        }
        else if (e.getSource().equals(Clearall))
        {
            System.out.println("Clearall");
        }
        else if (e.getSource().equals(Summary))
        {
            String summary;
            summary = Chair.toString();
            System.out.print(summary);
        }
        else if (e.getSource().equals(totalPrice))
        {
            System.out.println("totalPrice");
        }
    }
    
}
