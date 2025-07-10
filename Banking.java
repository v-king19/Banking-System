package com.vking.postgresapp;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class Banking extends Frame implements ActionListener,MouseListener
{
    String mode="";
    Button b1,b2,b3,b4,b5;
    TextField f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
    boolean clicked=false;
    Label l,l1,l2,l3,l4,l5,l6,l7,l8,l9,ll1,ll2,ll3,ll4,ll5,ll6;
    Banking()
    { 
        
        setSize(500,500);
        setTitle("vking");
        setVisible(true);
        
        l=new Label("CREATED BY V.K.👑");
        l.setBackground(Color.orange);
        
      
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);
        b1=new Button("CREATE NEW ACCOUNT");
        b2=new Button("CHECK ACCOUNT");
        b3=new Button("WITHDRAW");
        b4=new Button("DEPOSIT");
        b5=new Button(" SUBMIT ");
        b5.setBackground(Color.PINK);
        b1.addActionListener(this);
         b2.addActionListener(this);
          b3.addActionListener(this);
           b4.addActionListener(this);
           b5.addActionListener(this);
           
         b1.addMouseListener(this);
         b2.addMouseListener(this);
         b3.addMouseListener(this);
         b4.addMouseListener(this);
           
           add(b1);
           add(b2);
           add(b3);
           add(b4);
           add(b5);
           add(l);
           
    }
    
     public void mouseClicked(MouseEvent er)
    {
       
    }
     public void mouseReleased(MouseEvent er)
     {

     }
      public void mouseExited(MouseEvent er)
      {
  
    b1.setBackground(Color.WHITE);
     b2.setBackground(Color.WHITE);
      b3.setBackground(Color.WHITE);
       b4.setBackground(Color.WHITE);
  
      }
       public void mouseEntered(MouseEvent er)
       {
            
       }
        public void mousePressed(MouseEvent er)
{
     if(er.getSource()==b1)
   {
    b1.setBackground(Color.GREEN);
   }
   else if(er.getSource()==b2)
   {
    b2.setBackground(Color.GREEN);
   }
   else if(er.getSource()==b3)
   {
    b3.setBackground(Color.GREEN);
   }
   else if(er.getSource()==b4)
   {
    b4.setBackground(Color.GREEN);
   }

}  

    
    
    public void actionPerformed(ActionEvent e)
    {
        try{
            Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/newDB","postgres","vking");
 
            PreparedStatement pst=con.prepareStatement("insert into bank values(?,?,nextval('bankid'),?)");
       
        String str=e.getActionCommand();
        if(str=="CREATE NEW ACCOUNT" && clicked!=true)
        {
            mode="create";
            
           clicked=true;   
          l1=new Label("Enter Your Name:");
          f1=new TextField(10);
           l2=new Label("Enter Your PassWord:");
          f2=new TextField(10);
        l3=new Label("Deposit Amount(min.balance=500):");
          f3=new TextField(10);
          
         add(l1);             
        add(f1);
        add(l2);
        add(f2);
        add(l3);
        add(f3);
        repaint();
          validate();
        }
        
        if(str=="CHECK ACCOUNT")
        {
            mode="check";
            removeAll();
           add(b1);
           add(b2);
           add(b3);
           add(b4);
           add(b5);
           add(l);
           
            l4=new Label("Enter ACCOUNT NO:");
            f4=new TextField(7);
            l5=new Label("Enter Password:");
            f5=new TextField(7);
            add(l4);
            add(f4);
            add(l5);
            add(f5);
              repaint();
              validate();  
            
         }
        if(str=="WITHDRAW")
        {
            mode="withdraw";
            removeAll();
           add(b1);
           add(b2);
           add(b3);
           add(b4);
           add(b5);
           add(l);
             
            l9=new Label("Enter Account No:");
            f6=new TextField(7);
            ll1=new Label("Enter PassWord:");
            f7=new TextField(7);
            ll2=new Label("Withdraw Amount:");
            f8=new TextField(7);
            add(l9);
            add(f6);
            add(ll1);
            add(f7);
            add(ll2);
            add(f8);
              repaint(); 
              validate();
           
           
           
        }
           if(str=="DEPOSIT")
           {
                mode="depo";
                removeAll();
           add(b1);
           add(b2);
           add(b3);
           add(b4);
           add(b5);
           add(l);
            
            ll4=new Label("Enter Account No:");
            f9=new TextField(7);
            ll5=new Label("DEPOSIT Amount:");
            f10=new TextField(7);
            add(ll4);
            add(f9);
            add(ll5);
            add(f10);
             repaint(); 
              validate();   
           
           }
        
            if(str==" SUBMIT ")
            {
                if(mode.equals("check"))
                {
                PreparedStatement pst1=con.prepareStatement("select * from bank where acno=?");

                pst1.setInt(1,Integer.parseInt(f4.getText()));
                ResultSet rs=pst1.executeQuery();
                String pass=null;
                String na=null;
                int ac=0;
                int bal=0;
                while(rs.next())
                {
                    pass=rs.getString("password");
                    na=rs.getString("name");
                    ac=rs.getInt("acno");
                    bal=rs.getInt("balance");
                }
                     if(pass.equals(f5.getText()))
                        {
                           l6=new Label("NAME: "+na);
                           l7=new Label("ACCOUNT NO: "+ac);
                           l8=new Label("BALANCE: "+bal);
                           l6.setBackground(Color.pink);
                           l7.setBackground(Color.PINK);
                           l8.setBackground(Color.pink);
                        
                        add(l6);
                        add(l7);
                        add(l8);
                        }
                     else
                     {
                       l6=new Label("PASSWORD IS INCORRECT OR not prsent !!!");  
                       l6.setBackground(Color.red);
                       add(l6);
                     }
                     
               repaint();
               validate();
             
               }
                if(mode.equals("create"))
                {
                  pst.setString(1,f1.getText());
                  pst.setString(2,f2.getText());
                  pst.setInt(3,Integer.parseInt(f3.getText()));
                   pst.executeUpdate();
                   l9=new Label("ACCOUNT CREATED SUCCESSFULLY....");
                   l9.setBackground(Color.YELLOW);
                    add(l9);
                 repaint(); // refresh frame
                 validate();
                }
                if(mode.equals("withdraw"))
                {
                     PreparedStatement pst2=con.prepareStatement("select * from bank where acno=?");
                     pst2.setInt(1,Integer.parseInt(f6.getText()));
                     ResultSet rs1=pst2.executeQuery();
                     int ac=0;
                     String ps=null;
                     while(rs1.next())
                     {
                         ps=rs1.getString("password");
                         ac=rs1.getInt("acno");
                         
                     }
                       if(ps.equals(f7.getText()))
                       {
                           PreparedStatement pst3=con.prepareStatement("update bank set balance=balance-? where acno=?");
                           pst3.setInt(1,Integer.parseInt(f8.getText()));
                           pst3.setInt(2,ac);
                           pst3.executeUpdate();
                           ll3=new Label("Withdraw SUCCESSFULLY");
                           ll3.setBackground(Color.GREEN);
                           add(ll3);
                       }
                       repaint();
                       validate();
                }
                if(mode.equals("depo"))
                {
                     PreparedStatement pst2=con.prepareStatement("select * from bank where acno=?");
                     pst2.setInt(1,Integer.parseInt(f6.getText()));
                     ResultSet rs1=pst2.executeQuery();
                     int ac=0;
                     
                     while(rs1.next())
                     {
                        
                         ac=rs1.getInt("acno");
                         
                     }
                       
                           PreparedStatement pst3=con.prepareStatement("update bank set balance=balance+? where acno=?");
                           pst3.setInt(1,Integer.parseInt(f10.getText()));
                           pst3.setInt(2,ac);
                           pst3.executeUpdate();
                           ll6=new Label("DEPOSIT SUCCESSFULLY");
                           ll6.setBackground(Color.GREEN);
                           add(ll6);
                       
                }
                repaint();
                validate();
            }
            
            
        
        } 
        catch(Exception er)
        {
            System.out.println(er);
        }
    }
    public static void main(String str[])
    {
        Banking ob=new Banking();
    }
}