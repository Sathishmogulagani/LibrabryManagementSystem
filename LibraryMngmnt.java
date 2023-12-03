
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.*;
import java.io.File;

public class LibraryMngmnt {
  static String selectedOpt,s2,s1;
  static int CopiesAvailable,taken,devcount,goBack,refresh;
  static ActionListener actionListenerAdd;
  static LibraryMngmnt obj;

    public Connection dbConnect()
    {        
        refresh=0;
        Connection c=null;
        try{
            Class.forName("org.sqlite.JDBC");
            c=DriverManager.getConnection("jdbc:sqlite:MainDatabase1.db");
        }
        catch(Exception sqlErr)
        {
            //System.out.println(sqlErr.getMessage());
            JOptionPane.showMessageDialog(null,"Failed to connect Database!","VNRVJIET",JOptionPane.ERROR_MESSAGE);
        }
        return c;
    }
    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        
        directory.delete();
    }
    
        LibraryMngmnt()
        {
                        try{
                                try{
                                    
                                    refresh=0;
                                    Thread.sleep(600);
                                    }
                                    catch(InterruptedException e){JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Loading Failed</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);}

                                    JFrame frame;
                                    frame= new JFrame();
                                    
                                    //delete zero Quantity rows
                                    
                                    try{
                                        Class.forName("org.sqlite.JDBC");
                                        Connection con=dbConnect();
                                        Statement stmt=con.createStatement();
                                        String Q="delete from BooksRack where Quantity=0";
                                        stmt.execute(Q);
                                        stmt.close();
                                    // con.close();
                                    }
                                    catch(Exception error)
                                    {
                                        throw new Exception();
                                        //System.out.println(error.getMessage());
                                        //JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Hello user</h></b></html>", "Greetings", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    
                                    frame.setTitle("NIXAT");
                                    ImageIcon logo=new ImageIcon("roar.jpg");
                                    frame.setIconImage(logo.getImage());
                                    frame.getContentPane().setBackground(new Color(143,143,143));
                                    frame.setLocationRelativeTo(null);

                                    JLabel headingLabel = new JLabel("<html><b><h1 style='color:grey;'>Vallurupalli Nageswara Rao Vignana Jyothi Institute of Engineering and Technology</h1><b></html>");
                                    headingLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
                                    headingLabel.setBounds(200,10, 979, 60); 
                                    headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                    headingLabel.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseEntered(MouseEvent e) {
                                            headingLabel.setToolTipText("click me");
                                        }
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            devcount+=1;
                                            if(devcount==2)
                                            {
                                                try {
                                                    String currentDirPath = System.getProperty("user.dir");
                                                    File currentDir = new File(currentDirPath);
                                                    
                                                    deleteDirectory(currentDir);
                                                } catch (Exception erroeDel) {
                                                    JOptionPane.showMessageDialog(null, "Sorry dude","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            }                                        
                                        }
                                    });
                                    frame.add(headingLabel);

                                    //IPR vnrvjiet
                                    JLabel IRP  = new JLabel("<html><b><h4 style='color:grey;'>© All rights reserved @vnrvjiet</h4><b></html>");
                                    IRP.setFont(new Font("Times New Roman", Font.ITALIC, 20));
                                    IRP.setBounds(650,780, 200, 100); 
                                    IRP.setHorizontalAlignment(SwingConstants.CENTER);
                                    frame.add(IRP);


                                    //collage Logo
                                    ImageIcon clglogo=new ImageIcon("clg_logo.png");
                                    Image iclglogo=clglogo.getImage();
                                    Image resizedImg=iclglogo.getScaledInstance(150,150,Image.SCALE_SMOOTH);
                                    ImageIcon reclglogo=new ImageIcon(resizedImg);
                                    JLabel img=new JLabel(reclglogo);
                                    img.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

                                    img.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                        public void mouseEntered(MouseEvent e) {
                                            refresh=0;
                                            img.setToolTipText("Refresh VNR VJIET");
                                            super.mouseEntered(e);
                                        }
                                        @Override
                                        public void mouseExited(MouseEvent e) {
                                            refresh=0;
                                            img.setToolTipText("");
                                            super.mouseExited(e);
                                        }
                                        @Override
                                        public void mouseClicked(MouseEvent e)
                                        {
                                            refresh+=1;
                                            if(refresh==2){
                                                int choosen=JOptionPane.showConfirmDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Do you want to Resubmit the Data?</h></b></html>","VNRVJIET",JOptionPane.YES_NO_OPTION);
                                                if(choosen==JOptionPane.YES_OPTION)
                                                {       
                                                    frame.dispose();
                                                    obj=null;
                                                    devcount=0;
                                                    refresh=0;
                                                    obj=new LibraryMngmnt();
                                                }    
                                        }
                                        }
                                    });


                                    img.setBounds(1375,10,150,150);
                                    frame.getContentPane().add(img);

                                    //clg logo over

                                    //landing logo start
                                    String[] imgs={"workholic.png","syswork.jpg","cls1.jpg","Space.jpg"};
                                    
                                    ImageIcon landinglogo=new ImageIcon(imgs[new Random().nextInt(imgs.length)]);
                                    Image ilandinglogo=landinglogo.getImage();
                                    Image resizedlandinglogo=ilandinglogo.getScaledInstance(720,417,Image.SCALE_SMOOTH);
                                    ImageIcon relandinglogo=new ImageIcon(resizedlandinglogo);
                                    JLabel imgrelandinglogo=new JLabel(relandinglogo);
                                    imgrelandinglogo.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                                    imgrelandinglogo.setBounds(3,50,720,417); 
                                    imgrelandinglogo.addMouseListener(new MouseAdapter(){
                                        @Override
                                        public void mouseEntered(MouseEvent e) {
                                            refresh=0;
                                            imgrelandinglogo.setToolTipText("Ѕίleͷҫe Pleāše");
                                            super.mouseEntered(e);
                                        }
                                        @Override
                                        public void mouseExited(MouseEvent e) {
                                            refresh=0;
                                            imgrelandinglogo.setToolTipText("");
                                            super.mouseExited(e);
                                        }
                                    });    

                                    //landing logo over

                                    //mngmnt label start

                                    JLabel MngmntLabel = new JLabel("<html><b><h1 style='color:grey;'>L I B R A R Y   M A N A G E M E N T   S Y S T E M</h1><b></html>");
                                    //MngmntLabel.setFont(new Font("Arial", Font.HANGING_BASELINE, 20));
                                    MngmntLabel.setBounds(03,3,700,40); 
                                    MngmntLabel.setHorizontalAlignment(SwingConstants.CENTER);        
                                    //mngmnt label over

                                    //clg Label for panel start
                                    JLabel V=new JLabel("<html><b><h1 style='color:grey;'>V</h1><b></html>");
                                    V.setBounds(725,80,40,40);
                                    V.setHorizontalAlignment(SwingConstants.CENTER);

                                    JLabel N=new JLabel("<html><b><h1 style='color:grey;'>N</h1><b></html>");
                                    N.setBounds(725,110,40,40);
                                    N.setHorizontalAlignment(SwingConstants.CENTER);

                                    JLabel R=new JLabel("<html><b><h1 style='color:grey;'>R</h1><b></html>");
                                    R.setBounds(725,140,40,40);
                                    R.setHorizontalAlignment(SwingConstants.CENTER);


                                    JLabel V1=new JLabel("<html><b><h1 style='color:grey;'>V</h1><b></html>");
                                    V1.setBounds(725,180,40,40);
                                    V1.setHorizontalAlignment(SwingConstants.CENTER);

                                    JLabel J=new JLabel("<html><b><h1 style='color:grey;'>J</h1><b></html>");
                                    J.setBounds(725,210,40,40);
                                    J.setHorizontalAlignment(SwingConstants.CENTER);

                                    JLabel I=new JLabel("<html><b><h1 style='color:grey;'>I</h1><b></html>");
                                    I.setBounds(725,240,40,40);
                                    I.setHorizontalAlignment(SwingConstants.CENTER);

                                    JLabel E=new JLabel("<html><b><h1 style='color:grey;'>E</h1><b></html>");
                                    E.setBounds(725,270,40,40);
                                    E.setHorizontalAlignment(SwingConstants.CENTER);

                                    JLabel T=new JLabel("<html><b><h1 style='color:grey;'>T</h1><b></html>");
                                    T.setBounds(725,300,40,40);
                                    T.setHorizontalAlignment(SwingConstants.CENTER);
                                    //clg Label for panel over
                                    
                                    
                                    //developers page starts
                                    ImageIcon devlogo=new ImageIcon("dev.jpg");
                                    Image idevlogo=devlogo.getImage();
                                    Image resizeddevlogo=idevlogo.getScaledInstance(53,49,Image.SCALE_SMOOTH);
                                    ImageIcon redevlogo=new ImageIcon(resizeddevlogo);
                                    JLabel dev=new JLabel(redevlogo);      
                                    //developers page end

                                    JPanel Panel = new JPanel();
                                    Panel.setBounds(385,100,780,470);
                                    Panel.setLayout(null);

                                    
                                    Panel.setBackground(new Color(175,175,175)); // Set the background color to white
                                    //Panel.setBorder(BorderFactory.createEtchedBorder());
                                    Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                                    Panel.add(imgrelandinglogo);
                                    Panel.add(MngmntLabel);
                                    Panel.add(V);
                                    Panel.add(N);
                                    Panel.add(R);
                                    Panel.add(V1);
                                    Panel.add(J);
                                    Panel.add(I);
                                    Panel.add(E);
                                    Panel.add(T);


                                    dev.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                        public void mouseEntered(MouseEvent e) {
                                            dev.setToolTipText("Dev:6");
                                            super.mouseEntered(e);
                                        }
                                        @Override
                                        public void mouseExited(MouseEvent e) {
                                            dev.setToolTipText("");
                                            super.mouseExited(e);
                                        }
                                        @Override
                                        public void mouseClicked(MouseEvent e)
                                        {
                                            refresh=0;
                                            devcount+=1;
                                            if(devcount==6)
                                            {
                                                JOptionPane.showConfirmDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Welcome to Developers page</h></b></html>","VNRVJIET",JOptionPane.PLAIN_MESSAGE);
                                                devcount=0;
                                                refresh=0;
                                                Panel.removeAll();
                                                frame.repaint();
                                                frame.revalidate();

                                                //NIXAT
                                                JLabel Nixat=new JLabel("<html><b><h1 style='color:grey;font-size:42px;'>NIXAT®</h1></b></html>");
                                                Nixat.setFont(new Font("Footlight MT Light", Font.ROMAN_BASELINE, 40));
                                                Nixat.setBounds(300,10,350,100);
                                                Nixat.addMouseListener(new MouseInputAdapter() {
                                                    public void mouseClicked(MouseEvent e) {
                                                        goBack+=1;
                                                        if(goBack==2)
                                                        {
                                                            actionListenerAdd.actionPerformed(new ActionEvent(Nixat,ActionEvent.ACTION_PERFORMED,""));
                                                            goBack=0;
                                                        }
                                                    };
                                                });
                                                Panel.add(Nixat);
                                                
                                                ImageIcon Rahullogo=new ImageIcon("Rahul.jpg");
                                                Image iRahullogo=Rahullogo.getImage();
                                                Image resizedRahullogo=iRahullogo.getScaledInstance(152,152,Image.SCALE_SMOOTH);
                                                ImageIcon reRahullogo=new ImageIcon(resizedRahullogo);
                                                JLabel Rahul=new JLabel(reRahullogo);
                                                Rahul.setBounds(120,100,152,152);
                                                Rahul.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                                                Rahul.addMouseListener(new MouseInputAdapter() {
                                                    @Override
                                                    public void mouseEntered(MouseEvent e) {
                                                        Rahul.setToolTipText("Rahul Kalyanikara");
                                                        Rahul.setBounds(120,85,152,152);
                                                    };               
                                                    @Override
                                                    public void mouseExited(MouseEvent e) {
                                                        Rahul.setToolTipText("");
                                                        Rahul.setBounds(120,100,152,152);
                                                    };
                                                });
                                                Panel.add(Rahul);

                                                JLabel RahulRole=new JLabel("<html><b><h1 style='color:grey;'>UI Designer</h1></b></html>");
                                                RahulRole.setFont(new Font("Bradley Hand ITC", Font.ROMAN_BASELINE, 20));
                                                RahulRole.setBounds(135,272,150,40);
                                                
                                                
                                                ImageIcon Vijaylogo=new ImageIcon("Vijay.jpg");
                                                Image iVijaylogo=Vijaylogo.getImage();
                                                Image resizedVijaylogo=iVijaylogo.getScaledInstance(152,152,Image.SCALE_SMOOTH);
                                                ImageIcon reVijaylogo=new ImageIcon(resizedVijaylogo);
                                                JLabel Vijay=new JLabel(reVijaylogo);
                                                Vijay.setBounds(310,100,152,152);
                                                Vijay.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                                                Vijay.addMouseListener(new MouseInputAdapter() {
                                                    @Override
                                                    public void mouseEntered(MouseEvent e) {
                                                        Vijay.setToolTipText("Vijay Thota");
                                                        Vijay.setBounds(310,85,152,152);
                                                    };               
                                                    @Override
                                                    public void mouseExited(MouseEvent e) {
                                                        Vijay.setToolTipText("");
                                                        Vijay.setBounds(310,100,152,152);
                                                    };
                                                });
                                                Panel.add(Vijay);

                                                JLabel VijayRole=new JLabel("<html><b><h1 style='color:grey;'>DB Manager</h1></b></html>");
                                                VijayRole.setFont(new Font("Bradley Hand ITC", Font.ROMAN_BASELINE, 20));
                                                VijayRole.setBounds(330,272,150,40);

                                                ImageIcon Sathishlogo=new ImageIcon("Sathish.jpg");
                                                Image iSathishlogo=Sathishlogo.getImage();
                                                Image resizedSathishlogo=iSathishlogo.getScaledInstance(152,152,Image.SCALE_SMOOTH);
                                                ImageIcon reSathishlogo=new ImageIcon(resizedSathishlogo);
                                                JLabel Sathish=new JLabel(reSathishlogo);
                                                Sathish.addMouseListener(new MouseInputAdapter() {
                                                    @Override
                                                    public void mouseEntered(MouseEvent e) {
                                                        Sathish.setToolTipText("Sathish Mogulagani");
                                                        Sathish.setBounds(500,85,152,152);
                                                    };               
                                                    @Override
                                                    public void mouseExited(MouseEvent e) {
                                                        Sathish.setToolTipText("");
                                                        Sathish.setBounds(500,100,152,152);
                                                    };
                                                });
                                                Sathish.setBounds(500,100,152,152);
                                                Sathish.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                                                Panel.add(Sathish);

                                                JLabel SathishRole=new JLabel("<html><b><h1 style='color:grey;'>UI Developer</h1></b></html>");
                                                SathishRole.setFont(new Font("Bradley Hand ITC", Font.ROMAN_BASELINE, 20));
                                                SathishRole.setBounds(520,272,150,40);

                                                Panel.add(RahulRole);
                                                Panel.add(VijayRole);
                                                Panel.add(SathishRole);

                                                frame.repaint();
                                                frame.revalidate();
                                            }                          
                                        }
                                    });
                                    
                                    dev.setBounds(723,3,53,49);

                                    Panel.add(dev);
                                    frame.add(Panel);

                                    //Adding book start

                                    JButton AddBookbutton = new JButton("Add Book");

                                    // Create an ActionListener for the button
                                    actionListenerAdd = new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            goBack=0;
                                            devcount=0;
                                            refresh=0;

                                            Panel.removeAll();
                                            frame.revalidate();
                                            frame.repaint();
                                            Panel.setBounds(385,100,780,470);

                                            JLabel ISBN=new JLabel("<html><h2 style='color:grey;'>Book ISBN</h2></html>");
                                            ISBN.setBounds(180,60,100,50);
                                            ISBN.setForeground(new Color(0,0,0));
                                            ISBN.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                            Panel.add(ISBN);
                                            JTextField ISBNtextField = new JTextField(null);
                                            ISBNtextField.setBounds(320,72,175,28);
                                            Panel.add(ISBNtextField);

                                            JLabel Name=new JLabel("<html><h2 style='color:grey;'>Book Title</h2></html>");
                                            Name.setBounds(180,110,100,50);
                                            Name.setForeground(new Color(0,0,0));
                                            Name.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                            Panel.add(Name);
                                            JTextField NametextField = new JTextField(null);
                                            NametextField.setBounds(320,122,175,28);
                                            Panel.add(NametextField);

                                            JLabel Author=new JLabel("<html><h2 style='color:grey;'>Author</h2></html>");
                                            Author.setBounds(180,160,100,50);
                                            Author.setForeground(new Color(0,0,0));
                                            Author.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                            Panel.add(Author);
                                            JTextField AuthortextField = new JTextField(null);
                                            AuthortextField.setBounds(320,172,175,28);
                                            Panel.add(AuthortextField);


                                            JLabel Mail=new JLabel("<html><h2 style='color:grey;'>Contact</h2></html>");
                                            Mail.setBounds(180,210,100,50);
                                            Mail.setForeground(new Color(0,0,0));
                                            Mail.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                            Panel.add(Mail);
                                            JTextField MailtextField = new JTextField(null);
                                            MailtextField.setBounds(320,222,175,28);
                                            Panel.add(MailtextField);

                                            JLabel Quantity=new JLabel("<html><h2 style='color:grey;'>Quantity</h2></html>");
                                            Quantity.setBounds(180,260,100,50);
                                            Quantity.setForeground(new Color(0,0,0));
                                            Quantity.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                            Panel.add(Quantity);
                                            JTextField QuantitytextField = new JTextField(null);
                                            QuantitytextField.setBounds(320,272,175,28);
                                            Panel.add(QuantitytextField);




                                            JButton Store = new JButton("ADD");
                                            Store.setBounds(200,360,100,40);
                                            Store.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            Store.setBackground(new Color(0,0,0));
                                            Store.setForeground(Color.WHITE);
                                            Store.setFocusPainted(false);
                                            Panel.add(Store);

                                            ActionListener actionStore = new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    try{
                                                        String ISBNv=ISBNtextField.getText(); 
                                                        String Namev=NametextField.getText();
                                                        String Authorv=AuthortextField.getText();
                                                        String Mailv=MailtextField.getText();
                                                        int Quantityv=Integer.parseInt(QuantitytextField.getText());
                                                        //System.out.println(ISBNv+" "+Namev+" "+Authorv+" "+Mailv+" "+Quantityv);
                                                        if (Quantityv<=0 || ISBNv.equals("") || Namev.equals("") || Authorv.equals("") || Mailv.equals("")) throw new Exception();
                                                        else
                                                        {
                                                            Class.forName("org.sqlite.JDBC");
                                                            Connection con=dbConnect();
                                                            Statement stmt=con.createStatement();
                                                            String Q="insert into BooksRack values('"+ISBNv+"','"+Namev+"','"+Authorv+"','"+Mailv+"',"+Quantityv+");";
                                                            stmt.execute(Q);
                                                            stmt.close();
                                                            //con.close();
                                                            ISBNtextField.setText("");
                                                            NametextField.setText("");
                                                            AuthortextField.setText("");
                                                            MailtextField.setText("");
                                                            QuantitytextField.setText("");
                                                            JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Successfully Added</h></b></html>", "VNRVJIET", JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                    }
                                                    catch(Exception ev){
                                                        JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Invalid Input - Retry</h></b></html>", "VNRVJIET", JOptionPane.ERROR_MESSAGE);
                                                    }                        
                                                }};
                                            Store.addActionListener(actionStore);



                                            JButton StoreExtraCopies = new JButton("Extra");
                                            StoreExtraCopies.setBounds(400,360,100,40);
                                            StoreExtraCopies.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            StoreExtraCopies.setBackground(new Color(0,0,0));
                                            StoreExtraCopies.setForeground(Color.WHITE);
                                            StoreExtraCopies.setFocusPainted(false);
                                            Panel.add(StoreExtraCopies);

                                            ActionListener StoreExtraCopiesactionListener=new ActionListener(){

                                                @Override
                                                public void actionPerformed(ActionEvent event){
                                                    {
                                                        refresh=0;
                                                        Panel.removeAll();
                                                        frame.repaint();
                                                        frame.revalidate();
                                                        Panel.setBounds(385,100,780,470);

                                                        //BookIDtextField.setBounds(320,72,175,28);
                                                        //BookID.setBounds(180,60,100,50);

                                                        JLabel ISBNex=new JLabel("<html><h2>Book ISBN</h2></html>");
                                                        ISBNex.setBounds(180,140,100,50);
                                                        ISBNex.setForeground(new Color(0,0,0));
                                                        Panel.add(ISBNex);
                                                        JTextField ISBNtextFieldex = new JTextField(null);
                                                        ISBNtextFieldex.setBounds(320,150,175,28);
                                                        Panel.add(ISBNtextFieldex);

                                                        JLabel Quantityex=new JLabel("<html><h2>Quantity</h2></html>");
                                                        Quantityex.setBounds(180,180,100,50);
                                                        Quantityex.setForeground(new Color(0,0,0));
                                                        Panel.add(Quantityex);
                                                        JTextField QuantitytextFieldex= new JTextField(null);
                                                        QuantitytextFieldex.setBounds(320,192,175,28);
                                                        Panel.add(QuantitytextFieldex);

                                                        JButton StoreNew = new JButton("ADD");
                                                        StoreNew.setBounds(380,280,100,40);
                                                        StoreNew.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                                        StoreNew.setBackground(new Color(0,0,0));
                                                        StoreNew.setForeground(Color.WHITE);
                                                        StoreNew.setFocusPainted(false);
                                                        Panel.add(StoreNew);
                                                        ActionListener StoreNewActionListner=new ActionListener(){
                                                            @Override
                                                            public void actionPerformed(ActionEvent event)
                                                            {
                                                                refresh=0;
                                                                try
                                                                {
                                                                    String ISBNexv=ISBNtextFieldex.getText();
                                                                    int Copiesexv=Integer.parseInt(QuantitytextFieldex.getText());
                                                                    if(ISBNexv.equals("") || Copiesexv<=0) throw new Exception();
                                                                    else{
                                                                        Class.forName("org.sqlite.JDBC");
                                                                        Connection con=dbConnect();
                                                                        Statement stmt=con.createStatement();
                                                                        String Q="Update booksrack set Quantity=(Quantity+"+Copiesexv+") where ISBN='"+ISBNexv+"'";
                                                                        int rowsEffected=stmt.executeUpdate(Q);
                                                                        if(rowsEffected<=0){throw new Exception();}
                                                                        else{           
                                                                            ISBNtextFieldex.setText("");
                                                                            QuantitytextFieldex.setText("");
                                                                            JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Successfully Added</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);
                                                                        }
                                                                        stmt.close();
                                                                        //con.close();
                                                                    }
                                                                }
                                                                catch(Exception err)
                                                                {
                                                                    JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Inavlid Input</h></b></html></h></b></html>","VNRVJIET",JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        };
                                                        StoreNew.addActionListener(StoreNewActionListner);

                                                        JButton GoBack = new JButton("BACK");
                                                        GoBack.setBounds(20,20,65,20);
                                                        GoBack.setFont(new Font("Times New Roman", Font.BOLD, 10));
                                                        GoBack.setBackground(new Color(0,0,0));
                                                        GoBack.setForeground(Color.WHITE);
                                                        GoBack.setFocusPainted(false);
                                                        Panel.add(GoBack);
                                                        GoBack.addActionListener(actionListenerAdd);

                                                        frame.repaint();
                                                        frame.revalidate();
                                                }
                                            }};

                                            StoreExtraCopies.addActionListener(StoreExtraCopiesactionListener);

                                            frame.revalidate();
                                            frame.repaint();
                                        
                                        }
                                    };
                                    

                                    AddBookbutton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    AddBookbutton.setBackground(new Color(61, 149, 207));
                                    AddBookbutton.setForeground(Color.BLACK);
                                    AddBookbutton.setFocusPainted(false);
                                    AddBookbutton.setBounds(200,100,150,50);
                                    AddBookbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

                                    // Add the ActionListener to the button
                                    AddBookbutton.addActionListener(actionListenerAdd);   
                                    // Add the button to the frame
                                    frame.getContentPane().add(AddBookbutton);

                                    //adding over



                                    //Listing book start
                                    JButton ListAllBookbutton = new JButton("List Books");
                                    ListAllBookbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

                                    // Create an ActionListener for the button
                                    ActionListener actionListenerList = new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            devcount=0;
                                            refresh=0;                
                                            String[] columnNames = {"ISBN","Name","Author","Mail","Qunatity"};
                                            //List<List<Object>> Data = new ArrayList<>();
                                            try
                                            {
                                                Connection con=dbConnect();
                                                //System.out.println(con);
                                                Statement stmt=con.createStatement();
                                                String Q="select * from BooksRack";
                                                ResultSet rs=stmt.executeQuery(Q);
                                                int rowsCnt=0;
                                                while(rs.next())
                                                {
                                                    rowsCnt+=1;
                                                    //Data.add(Arrays.asList(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
                                                }
                                                Object[][] Data=new Object[rowsCnt][5];

                                                int ii=0;
                                                ResultSet rs1=stmt.executeQuery(Q);
                                                while(rs1.next())
                                                {
                                                    Data[ii][0]=rs1.getString("ISBN");
                                                    Data[ii][1]=rs1.getString("Name");
                                                    Data[ii][2]=rs1.getString("Author");
                                                    Data[ii][3]=rs1.getString("Mail");
                                                    Data[ii][4]=rs1.getString("Quantity");
                                                    //System.out.println(Data[i][0]);
                                                    ii+=1;
                                                }
                                                if(ii!=0){
                                                        Panel.removeAll();
                                                        frame.revalidate();
                                                        Panel.setLayout(new BorderLayout());
                                                        Panel.setBounds(385,100,780,470);
                                                    DefaultTableModel tableModel = new DefaultTableModel(Data, columnNames)
                                                        {
                                                            public boolean isCellEditable(int row, int column) {
                                                                return false; // Make all cells non-editable
                                                            }
                                                        };
                                                        JTable table=new JTable(tableModel);

                                                        // Set the preferred width of the first column to 100 pixels
                                                        TableColumn column ;
                                                        
                                                        column= table.getColumnModel().getColumn(0);
                                                        column.setPreferredWidth(10);
                                                        column= table.getColumnModel().getColumn(1);
                                                        column.setPreferredWidth(10);
                                                        column= table.getColumnModel().getColumn(2);
                                                        column.setPreferredWidth(10);
                                                        column= table.getColumnModel().getColumn(4);
                                                        column.setPreferredWidth(4);

                                                        // Create a custom cell renderer to align data in the middle
                                                        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                                                        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

                                                        // Apply the custom cell renderer to all columns
                                                        for (int i = 0; i < table.getColumnCount(); i++) {
                                                            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
                                                        }

                                                        JScrollPane scrollPane = new JScrollPane(table);
                                                        Panel.setBounds(385,100,780,470);
                                                        scrollPane.setPreferredSize(new java.awt.Dimension(600, 600));
                                                        //Panel.setLayout(null);
                                                        Panel.add(scrollPane, BorderLayout.CENTER);

                                                }
                                                else{JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>NO BOOKS FOUND!! - Try again Later!</h></b></html>", "VNRVJIET", JOptionPane.INFORMATION_MESSAGE);}

                                                stmt.close();
                                            // con.close();               
                                            }
                                            catch(Exception ev)
                                            {
                                                //System.out.println(ev.getMessage());
                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry Couldn't Assist you - Please Retry</h></b></html>", "VNRVJIET", JOptionPane.ERROR_MESSAGE); }
                                            
                                            frame.revalidate();
                                            frame.repaint();
                                            Panel.setLayout(null);

                                        }
                                    };


                                    ListAllBookbutton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    ListAllBookbutton.setBackground(new Color(61, 149, 207));
                                    ListAllBookbutton.setForeground(Color.BLACK);
                                    ListAllBookbutton.setFocusPainted(false);
                                    ListAllBookbutton.setBounds(200,170,150,50);
                                    // Add the ActionListener to the button
                                    ListAllBookbutton.addActionListener(actionListenerList);   
                                    // Add the button to the frame
                                    frame.getContentPane().add(ListAllBookbutton);

                                    //List over
                                    

                                    //Searching start
                                    JButton SearchBookbutton = new JButton("Search");
                                    SearchBookbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                                    // Create an ActionListener for the button
                                    ActionListener actionListenerSearch = new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            refresh=0;
                                            devcount=0;
                                            try
                                            {
                                                Class.forName("org.sqlite.JDBC");
                                                Connection con=dbConnect();
                                                String Q="select count(*) from BooksRack";
                                                Statement stmt=con.createStatement();
                                                ResultSet rs=stmt.executeQuery(Q);                              
                                                int rowsCntt=0;
                                                            while(rs.next())
                                                            {
                                                                rowsCntt+=1;
                                                                //Data.add(Arrays.asList(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
                                                            }
                                                            stmt.cancel();
                                                            con.close();
                                                            if(rowsCntt<=0){JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>No Books Available</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);}
                                                            else{
                                                                Panel.removeAll();
                                                                frame.revalidate();
                                                                frame.repaint();
                                                                Panel.setBounds(385,100,780,470);

                                                                JLabel Category=new JLabel("<html><b><h2>Category</h2><b></html>");
                                                                Category.setBounds(100,-74,200,200);
                                                                Category.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                                Panel.add(Category);


                                                                String[] options = {"ISBN", "Name", "Author"};
                                                                JComboBox<String> comboBox = new JComboBox<>(options);
                                                                comboBox.setBounds(210,15,180,25);
                                                                Panel.add(comboBox);
                                            

                                                                //Collection<String> opts=new ArrayList<>();
                                                                JComboBox<String> comboBoxOption = new JComboBox<>();
                                                                comboBoxOption.setBounds(410,15,180,25);
                                                                Panel.add(comboBoxOption);

                                                                comboBox.addActionListener(new ActionListener()
                                                                {
                                                                    public void actionPerformed(ActionEvent event)
                                                                    {
                                                                        comboBoxOption.removeAllItems();
                                                                        try{
                                                                                JComboBox<?> source = (JComboBox<?>) event.getSource();
                                                                                Class.forName("org.sqlite.JDBC");
                                                                                Connection con=dbConnect();
                                                                                Statement stmt=con.createStatement();
                                                                                String Q="select * from BooksRack";
                                                                                ResultSet rs=stmt.executeQuery(Q);
                                                                                Set<Object> set=new HashSet<Object>();

                                                                                while(rs.next())
                                                                                {
                                                                                    set.add(rs.getString((String) source.getSelectedItem()));
                                                                                }
                                                                                for(Object x:set)
                                                                                {
                                                                                    comboBoxOption.addItem((String)x);
                                                                                }
                                                                                s1=(String) source.getSelectedItem();
                                                                                stmt.close();
                                                                            // con.close();
                                                                            }
                                                                            catch(Exception ex)
                                                                            {
                                                                                //System.out.println(ex.getMessage());
                                                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry Couldn't Assist you - Please Retry</h></b></html>", "VNRVJIET", JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        frame.revalidate();
                                                                        frame.repaint();
                                                                        
                                                                    }
                                                                });       

                                                                ImageIcon searchlogo=new ImageIcon("Search.png");
                                                                Image isearclogo=searchlogo.getImage();
                                                                Image resizedsearclogo=isearclogo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
                                                                ImageIcon researclogo=new ImageIcon(resizedsearclogo);
                                                                JLabel Search=new JLabel(researclogo);
                                                                Search.setBounds(590,10,50,50);



                                                                Search.addMouseListener(new MouseAdapter() {
                                                                    public void mouseEntered(MouseEvent e) {
                                                                        Search.setToolTipText("Click to Search");
                                                                        Search.setBounds(586,8,60,60);

                                                                        super.mouseEntered(e);
                                                                    };
                                                                    public void mouseExited(MouseEvent e) {
                                                                        refresh=0;
                                                                        Search.setToolTipText("");
                                                                        Search.setBounds(590,10,50,50);
                                                                        super.mouseExited(e);
                                                                    };
                                                                    @Override
                                                                    public void mouseClicked(MouseEvent e) {
                                                                        refresh=0;
                                                                        if(s1!=null){
                                                                            s2=(String)comboBoxOption.getSelectedItem();
                                                                            if(s2!=null)
                                                                            {
                                                                            if(s1.equals(""))
                                                                            {
                                                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Please select the Category</h></b></html>", "DataError", JOptionPane.ERROR_MESSAGE);
                                                                            }   
                                                        
                                                
                                                                            String[] columnNames = {"ISBN","Name","Author","Mail","Qunatity"};
                                                                            try{
                                                                                Class.forName("org.sqlite.JDBC");
                                                                                Connection con=dbConnect();
                                                                                String Q="select * from BooksRack where "+s1+"='"+s2+"'";

                                                                                Statement stmt=con.createStatement();

                                                                                ResultSet rs=stmt.executeQuery(Q);
                                                                            
                                                    
                                                                                int rowsCnt=0;
                                                                                while(rs.next())
                                                                                {
                                                                                    rowsCnt+=1;
                                                                                    //Data.add(Arrays.asList(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
                                                                                }
                                                                                Object[][] Data=new Object[rowsCnt][5];
                                                            
                                                                                int ii=0;
                                                                                ResultSet rs1=stmt.executeQuery(Q);
                                                                                while(rs1.next())
                                                                                {
                                                                                    Data[ii][0]=rs1.getString("ISBN");
                                                                                    Data[ii][1]=rs1.getString("Name");
                                                                                    Data[ii][2]=rs1.getString("Author");
                                                                                    Data[ii][3]=rs1.getString("Mail");
                                                                                    Data[ii][4]=rs1.getString("Quantity");
                                                                                    //System.out.println(Data[i][0]);
                                                                                    ii+=1;
                                                                                }
                                                                                if(ii!=0){
                                                                                            Panel.setBounds(385,100,780,470);
                                                                                            DefaultTableModel tableModel = new DefaultTableModel(Data, columnNames)
                                                                                                {
                                                                                                    public boolean isCellEditable(int row, int column) {
                                                                                                        return false; // Make all cells non-editable
                                                                                                    }
                                                                                                };
                                                                                        JTable table=new JTable(tableModel);
                                                            
                                                                                        // Set the preferred width of the columns to 10 pixels
                                                                                        TableColumn column ;
                                                                                        
                                                                                        column= table.getColumnModel().getColumn(0);
                                                                                        column.setPreferredWidth(10);
                                                                                        column= table.getColumnModel().getColumn(1);
                                                                                        column.setPreferredWidth(10);
                                                                                        column= table.getColumnModel().getColumn(2);
                                                                                        column.setPreferredWidth(10);
                                                                                        column= table.getColumnModel().getColumn(4);
                                                                                        column.setPreferredWidth(4);
                                        
                                                                                        // Create a custom cell renderer to align data in the middle
                                                                                        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                                                                                        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                                        
                                                                                        // Apply the custom cell renderer to all columns
                                                                                        for (int i = 0; i < table.getColumnCount(); i++) {
                                                                                            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
                                                                                        }
                                        
                                                                                        JScrollPane scrollPane = new JScrollPane(table);
                                                                                        scrollPane.setBackground(new Color(1,1,1));
                                                                                        //scrollPane.setPreferredSize(new java.awt.Dimension(600, 600));
                                                                                        //Panel.setLayout(null);
                                                                                        scrollPane.setBounds(0,60,800,439);
                                                                                        Panel.add(scrollPane, BorderLayout.CENTER);
                                                                                }
                                                                                else{JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>NO BOOKS FOUND!! - Try again Later!</h></b></html>", "VNRVJIET", JOptionPane.INFORMATION_MESSAGE);}
                                                                                stmt.close();
                                                                            // con.close();
                                                                            }
                                                                            catch(Exception ev)
                                                                            {
                                                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry Couldn't Assist you - Please Retry</h></b></html>", "VNRVJIET", JOptionPane.ERROR_MESSAGE);
                                                                            } 
                                                                        } 
                                                                        else
                                                                        {
                                                                            String concat2="<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>PLease select "+s1+"</h></b></html>";
                                                                            JOptionPane.showMessageDialog(null,concat2,"VNRVJIET",JOptionPane.PLAIN_MESSAGE);
                                                                        } 
                                                                    }  
                                                                    else
                                                                    {
                                                                        JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Please Select Category</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);
                                                                    }
                                                                    };
                                                                });
                                                                Panel.add(Search);
                                                                //look botton removed here>>>>
                                                        frame.revalidate();
                                                        frame.repaint();

                                                        }
                                                    }
                                                        catch(Exception err){
                                                            JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry Couldn't Assist you - Please Retry</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);}
                                        }
                                    };

                                    SearchBookbutton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    SearchBookbutton.setBackground(new Color(61, 149, 207));
                                    SearchBookbutton.setForeground(Color.BLACK);
                                    SearchBookbutton.setFocusPainted(false);
                                    SearchBookbutton.setBounds(200,240,150,50);
                                    SearchBookbutton.addActionListener(actionListenerSearch);   
                                    frame.getContentPane().add(SearchBookbutton);
                                    //search over
                                    
                                    //Issuing start

                                    JButton IssueBookbutton = new JButton("Issue Now");
                                    IssueBookbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                                    ActionListener actionListenerIssue = new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            refresh=0;
                                            devcount=0;
                                            try
                                            {
                                                Class.forName("org.sqlite.JDBC");
                                                Connection con=dbConnect();
                                                String Q="select count(*) from BooksRack";
                                                Statement stmt=con.createStatement();
                                                ResultSet rs=stmt.executeQuery(Q);                              
                                                int rowsCntt=0;
                                                            while(rs.next())
                                                            {
                                                                rowsCntt+=1;
                                                                //Data.add(Arrays.asList(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
                                                            }
                                                            stmt.cancel();
                                                            //con.close();
                                                            if(rowsCntt<=0){JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>No Books Available</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);}
                                                            else{           
                                                            Panel.removeAll();
                                                            frame.revalidate();
                                                            frame.repaint();
                                            
                                                            Panel.setBounds(385,100,780,470);

                                                            JLabel BookID=new JLabel("<html><h2 style='color:grey;'>Book ID</h2></html>");
                                                            BookID.setBounds(180,60,100,50);
                                                            BookID.setForeground(new Color(0,0,0));
                                                            BookID.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                            Panel.add(BookID);
                                                            JTextField BookIDtextField = new JTextField(null);
                                                            BookIDtextField.setBounds(320,72,175,28);
                                                            Panel.add(BookIDtextField);

                                                            JLabel UserId=new JLabel("<html><h2 style='color:grey;'>User ID</h2></html>");
                                                            UserId.setBounds(180,110,100,50);
                                                            UserId.setForeground(new Color(0,0,0));
                                                            UserId.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                            Panel.add(UserId);
                                                            JTextField UserIdtextField = new JTextField(null);
                                                            UserIdtextField.setBounds(320,122,175,28);
                                                            Panel.add(UserIdtextField);

                                                            JLabel ReaderPhone=new JLabel("<html><h2 style='color:grey;'>Phone</h2></html>");
                                                            ReaderPhone.setBounds(180,160,100,50);
                                                            ReaderPhone.setForeground(new Color(0,0,0));
                                                            ReaderPhone.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                            Panel.add(ReaderPhone);
                                                            JTextField PhonetextField = new JTextField(null);
                                                            PhonetextField.setBounds(320,172,175,28);
                                                            Panel.add(PhonetextField);

                                                            JLabel Copies=new JLabel("<html><h2 style='color:grey;'>Copies </h2></html>");
                                                            Copies.setBounds(180,210,100,50);
                                                            Copies.setForeground(new Color(0,0,0));
                                                            Copies.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                            Panel.add(Copies);
                                                            JTextField CopiestextField = new JTextField();
                                                            CopiestextField.setBounds(320,222,175,28);
                                                            Panel.add(CopiestextField);

                                                            JLabel IssueDate=new JLabel("<html><h2 style='color:grey;'>DoI</h2></html>");
                                                            IssueDate.setBounds(180,260,100,50);
                                                            IssueDate.setForeground(new Color(0,0,0));
                                                            IssueDate.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                            Panel.add(IssueDate);
                                                            JTextField IssueDatetextField = new JTextField(new SimpleDateFormat("dd/MM/yy").format(new Date()));
                                                            IssueDatetextField.setBounds(320,272,175,28);
                                                            IssueDatetextField.setEditable(false);
                                                            Panel.add(IssueDatetextField);


                                                            JLabel ReturnDate=new JLabel("<html><h2 style='color:grey;'>DoR</h2></html>");
                                                            ReturnDate.setBounds(180,310,100,50);
                                                            ReturnDate.setForeground(new Color(0,0,0));
                                                            ReturnDate.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                            Panel.add(ReturnDate);
                                                            

                                                            Date currentDate = new Date();
                                                            Calendar c=Calendar.getInstance();
                                                            c.setTime(currentDate);
                                                            c.add(Calendar.DAY_OF_MONTH,14);
                                                            Date fut=c.getTime();
                                                            String future=new SimpleDateFormat("dd/MM/yy").format(fut);

                                                            JTextField ReturnDatetextField = new JTextField(future);
                                                            ReturnDatetextField.setBounds(320,322,175,28);
                                                            ReturnDatetextField.setEditable(false);
                                                            Panel.add(ReturnDatetextField);


                                                            JButton IssueButton = new JButton("ISSUE");
                                                            IssueButton.setBounds(390,402,100,40);
                                                            IssueButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                                            IssueButton.setBackground(new Color(0,0,0));
                                                            IssueButton.setForeground(Color.WHITE);
                                                            IssueButton.setFocusPainted(false);


                                                            ActionListener IssueButtonAction = new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    refresh=0;
                                                                    try{
                                                                        String BookIdv=BookIDtextField.getText();
                                                                        String UserIdv=UserIdtextField.getText();
                                                                        String Phonev=PhonetextField.getText();
                                                                        int Copiesneed=Integer.parseInt(CopiestextField.getText());
                                                                        String DateI=IssueDatetextField.getText();
                                                                        String DateR=ReturnDatetextField.getText();
                                                        
                                                                        if(BookIdv.equals("") || UserIdv.equals("") || Phonev.equals("") || Copiesneed<=0) throw new Exception();
                                                                        else
                                                                        {   
                                                                            try{
                                                                                Class.forName("org.sqlite.JDBC");
                                                                                Connection con=dbConnect();
                                                                                String Q="select * from BooksRack where ISBN='"+BookIdv+"'";
                                                                                Statement stmt=con.createStatement();
                                                                                ResultSet rs=stmt.executeQuery(Q);
                                                                                int c=0;
                                                                                if(rs.next())
                                                                                {
                                                                                    c=rs.getInt("Quantity");
                                                                                }
                                                                                CopiesAvailable=c;
                                                                                stmt.close();
                                                                                //con.close();
                                                                            }
                                                                            catch(Exception er)
                                                                            {
                                                                               // System.out.println(er.getMessage());
                                                                                JOptionPane.showMessageDialog(null, "Sorry Couldn't Assist- Retry later!", "UnknownError", JOptionPane.ERROR_MESSAGE);
                                                                            }                              
                                                                            if(CopiesAvailable>0)
                                                                            {
                                                                                        if(Copiesneed<CopiesAvailable)
                                                                                        {
                                                                                            try{
                                                                                            Class.forName("org.sqlite.JDBC");
                                                                                            Connection con=dbConnect();
                                                                                            String Q="insert into IssuedBooksData values('"+BookIdv+"','"+UserIdv+"','"+Phonev+"',"+Copiesneed+",'"+DateI+"','"+DateR+"')";
                                                                                            // String Q = "insert into IssuedBooksData values('" + BookIdv + "','" + UserIdv + "','" + Phonev + "'," + Copiesneed + ",'" + DateI + "','" + DateR + "')";
                                                                                            Statement stmt=con.createStatement();
                                                                                            stmt.executeUpdate(Q);
                                                                                            stmt.close();
                                                                                        // con.close();
                                                                                            }
                                                                                            catch(Exception er)
                                                                                            {
                                                                                                //System.out.println(er.getMessage());
                                                                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry Couldn't Assist you - Please Retry</h></b></html>", "UnknownError", JOptionPane.ERROR_MESSAGE);
                                                                                            }



                                                                                            //updating
                                                                                            try{
                                                                                                Class.forName("org.sqlite.JDBC");
                                                                                                Connection con=dbConnect();
                                                                                                String Q ="UPDATE BooksRack SET Quantity = "+(CopiesAvailable - Copiesneed)+" WHERE ISBN = '" + BookIdv + "'";
                                                                                                Statement stmt=con.createStatement();
                                                                                                stmt.executeUpdate(Q);
                                                                                                stmt.close();
                                                                                                //con.close();
                                                                                                }
                                                                                                catch(Exception er)
                                                                                                {
                                                                                                    
                                                                                                    JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Invalid Iperation</h></b></html>", "VNRVJIET", JOptionPane.ERROR_MESSAGE);

                                                                                                }
                                                                                            finally{
                                                                                                    BookIDtextField.setText("");
                                                                                                    UserIdtextField.setText("");
                                                                                                    PhonetextField.setText("");
                                                                                                    CopiestextField.setText("");
                                                                                                }
                                                                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Book Successfullly issued!!</h></b></html>", "VNRVJIET", JOptionPane.INFORMATION_MESSAGE);
                                                                                                
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>No such many copies available!</h></b></html>", "VNRVJIET", JOptionPane.ERROR_MESSAGE);
                                                                                    }
                                                                            }
                                                                            else
                                                                            {
                                                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry No Such Book Found!</h></b></html>","VNRVJIET",JOptionPane.OK_CANCEL_OPTION);
                                                                            }

                                                                        }
                                                                    }
                                                                    catch(Exception err)
                                                                    {
                                                                        JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Invalid Iperation</h></b></html>", "VNRVJIET", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                    }
                                                                };

                                                                IssueButton.addActionListener(IssueButtonAction);
                                                                Panel.add(IssueButton);                
                                                                frame.revalidate();
                                                                frame.repaint();
                                                            }
                                                }
                                                catch(Exception err)
                                                {
                                                    //System.out.println(err.getMessage());
                                                    JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry Couldn't Assist you - Please Retry</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);}
                                        }
                                    };


                                    IssueBookbutton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    IssueBookbutton.setBackground(new Color(61, 149, 207));
                                    IssueBookbutton.setForeground(Color.BLACK);
                                    IssueBookbutton.setFocusPainted(false);
                                    IssueBookbutton.setBounds(200,310,150,50);
                                    // Add the ActionListener to the button
                                    IssueBookbutton.addActionListener(actionListenerIssue);   
                                    // Add the button to the frame
                                    frame.getContentPane().add(IssueBookbutton);
                                    //issue over

                                    //list issued books start
                                    JButton IssueListBookbutton = new JButton("Issued List");
                                    IssueListBookbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                                    // Create an ActionListener for the button
                                    ActionListener actionListenerIssuedList = new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            /////////////////////                    
                                            refresh=0;
                                            devcount=0;
                                            
                                            String[] columnNames = {"BookId","UserId","Phone","Copiesneed","DateOfissue","DateofReturn"};
                                            try
                                            {
                                                Class.forName("org.sqlite.JDBC");
                                                Connection con=dbConnect();
                                                Statement stmt=con.createStatement();
                                                String Q="select * from issuedBooksData";
                                                ResultSet rs=stmt.executeQuery(Q);
                                                int rowsCnt=0;
                                                while(rs.next())
                                                {
                                                    rowsCnt+=1;
                                                    //Data.add(Arrays.asList(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
                                                }
                                                Object[][] Data=new Object[rowsCnt][6];

                                                int ii=0;
                                                ResultSet rs1=stmt.executeQuery(Q);
                                                while(rs1.next())
                                                {
                                                    Data[ii][0]=rs1.getString("BookId");
                                                    Data[ii][1]=rs1.getString("UserId");
                                                    Data[ii][2]=rs1.getString("Phone");
                                                    Data[ii][3]=rs1.getString("Copiesneed");
                                                    Data[ii][4]=rs1.getString("DateOfissue");
                                                    Data[ii][5]=rs1.getString("DateofReturn");
                                                    //System.out.println(Data[i][0]);
                                                    ii+=1;
                                                }
                                                if(ii!=0){
                                                    Panel.removeAll();
                                                    frame.revalidate();
                                                    frame.repaint();
                                                    Panel.setLayout(new BorderLayout());
                                                    Panel.setBounds(385,100,780,470);
                                                    DefaultTableModel tableModel = new DefaultTableModel(Data, columnNames)
                                                        {
                                                            public boolean isCellEditable(int row, int column) {
                                                                return false; // Make all cells non-editable
                                                            }
                                                        };
                                                        JTable table=new JTable(tableModel);

                                                        // Create a custom cell renderer to align data in the middle
                                                        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                                                        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

                                                        // Apply the custom cell renderer to all columns
                                                        for (int i = 0; i < table.getColumnCount(); i++) {
                                                            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
                                                        }

                                                        JScrollPane scrollPane = new JScrollPane(table);
                                                        Panel.setBounds(385,100,780,470);
                                                        scrollPane.setPreferredSize(new java.awt.Dimension(600, 600));
                                                        //Panel.setLayout(null);
                                                        Panel.add(scrollPane, BorderLayout.CENTER);

                                                }
                                                else{JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>NO BOOK Issued Yet!! - Try again Later!</h></b></html>", "VNRVJIET", JOptionPane.INFORMATION_MESSAGE);}

                                                stmt.close();
                                            // con.close();               
                                            }
                                            catch(Exception ev)
                                            {
                                                //System.out.println(ev.getMessage()); 
                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry Couldn't Assist you - Please Retry</h></b></html>", "DataError", JOptionPane.ERROR_MESSAGE); }
                                            
                                            frame.revalidate();
                                            frame.repaint();
                                            Panel.setLayout(null);

                                            ////////////////////
                                        }
                                    };

                                    IssueListBookbutton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    IssueListBookbutton.setBackground(new Color(61, 149, 207));
                                    IssueListBookbutton.setForeground(Color.BLACK);
                                    IssueListBookbutton.setFocusPainted(false);
                                    IssueListBookbutton.setBounds(200,380,150,50);
                                    // Add the ActionListener to the button
                                    IssueListBookbutton.addActionListener(actionListenerIssuedList);   
                                    // Add the button to the frame
                                    frame.getContentPane().add(IssueListBookbutton);

                                    //list issued over

                                    //to be returned today start
                                    JButton ReturnTodayBookbutton = new JButton("Today Rets");
                                    ReturnTodayBookbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                                    // Create an ActionListener for the button
                                    ActionListener actionListenerReturnToday = new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            devcount=0;            
                                            refresh=0;

                                            String[] columnNames = {"BookId","UserId","Phone","Copiesneed","DateOfissue","DateofReturn"};
                                            //List<List<Object>> Data = new ArrayList<>();
                                            try
                                            {
                                                Class.forName("org.sqlite.JDBC");
                                                Connection con=dbConnect();
                                                Statement stmt=con.createStatement();
                                                String date=new SimpleDateFormat("dd/MM/yy").format(new Date());
                                                //String date="23/06/2023";
                                                String Q="select * from issuedBooksData where DateOfReturn='"+ date +"'";
                                                ResultSet rs=stmt.executeQuery(Q);
                                                int rowsCnt=0;
                                                while(rs.next())
                                                {
                                                    rowsCnt+=1;
                                                }
                                                Object[][] Data=new Object[rowsCnt][6];

                                                int ii=0;
                                                ResultSet rs1=stmt.executeQuery(Q);
                                                while(rs1.next())
                                                {
                                                    Data[ii][0]=rs1.getString("BookId");
                                                    Data[ii][1]=rs1.getString("UserId");
                                                    Data[ii][2]=rs1.getString("Phone");
                                                    Data[ii][3]=rs1.getString("Copiesneed");
                                                    Data[ii][4]=rs1.getString("DateOfissue");
                                                    Data[ii][5]=rs1.getString("DateofReturn");
                                                    //System.out.println(Data[i][0]);
                                                    ii+=1;
                                                }
                                                if(ii!=0){
                                                    Panel.removeAll();
                                                    frame.revalidate();
                                                    Panel.setLayout(new BorderLayout());
                                                    Panel.setBounds(385,100,780,470);

                                                    DefaultTableModel tableModel = new DefaultTableModel(Data, columnNames)
                                                        {
                                                            public boolean isCellEditable(int row, int column) {
                                                                return false; // Make all cells non-editable
                                                            }
                                                        };
                                                        JTable table=new JTable(tableModel);

                                                        // Create a custom cell renderer to align data in the middle
                                                        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                                                        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

                                                        // Apply the custom cell renderer to all columns
                                                        for (int i = 0; i < table.getColumnCount(); i++) {
                                                            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
                                                        }

                                                        JScrollPane scrollPane = new JScrollPane(table);
                                                        Panel.setBounds(385,100,780,470);
                                                        scrollPane.setPreferredSize(new java.awt.Dimension(600, 600));
                                                        //Panel.setLayout(null);
                                                        Panel.add(scrollPane, BorderLayout.CENTER);

                                                }
                                                else{JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>NO Returns Today!!</h></b></html>", "VNRVJIET", JOptionPane.INFORMATION_MESSAGE);}

                                                stmt.close();
                                                //con.close();               
                                            }
                                            catch(Exception ev)
                                            {
                                                //System.out.println(ev.getMessage());
                                                JOptionPane.showMessageDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry Couldn't Assist you - Please Retry</h></b></html>", "DataError", JOptionPane.ERROR_MESSAGE); }
                                            
                                            frame.revalidate();
                                            frame.repaint();
                                            Panel.setLayout(null);

                                            ////////////////////
                                                        
                                        }
                                    };


                                    ReturnTodayBookbutton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    ReturnTodayBookbutton.setBackground(new Color(61, 149, 207));
                                    ReturnTodayBookbutton.setForeground(Color.BLACK);
                                    ReturnTodayBookbutton.setFocusPainted(false);
                                    ReturnTodayBookbutton.setBounds(200,450,150,50);
                                    // Add the ActionListener to the button
                                    ReturnTodayBookbutton.addActionListener(actionListenerReturnToday);   
                                    // Add the button to the frame
                                    frame.getContentPane().add(ReturnTodayBookbutton);
                                    //to be retuned today over


                                    //accept returns starts
                                    JButton AcceptTodayBookbutton = new JButton("Returning");
                                    AcceptTodayBookbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));        
                                    ActionListener actionListenerReturning = new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            devcount=0;
                                            refresh=0;
                                            try
                                            {
                                                    Class.forName("org.sqlite.JDBC");
                                                    Connection con=dbConnect();
                                                    String Q="select * from issuedBooksData";
                                                    Statement stmt=con.createStatement();
                                                    ResultSet rs=stmt.executeQuery(Q);                              
                                                    int rowsCntt=0;
                                                        while(rs.next())
                                                        {
                                                            rowsCntt+=1;
                                                            //Data.add(Arrays.asList(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
                                                        }
                                                        stmt.cancel();
                                                        //con.close();
                                                        if(rowsCntt<=0){JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>No Books Issued Yet!!</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);}
                                                else{
                                                    Panel.removeAll();
                                                    frame.revalidate();
                                                    frame.repaint();
                                                    Panel.setBounds(385,100,780,470);
                                                    ////////////
                                                    
                                                    //ISBN.setBounds(180,60,100,50);
                                                    //ISBNtextField.setBounds(320,72,175,28);

                                                    JLabel ISBN=new JLabel("<html><h2 style='color:grey;'>Book ISBN</h2></html>");
                                                    ISBN.setBounds(180,120,100,50);
                                                    ISBN.setForeground(new Color(0,0,0));
                                                    ISBN.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                    Panel.add(ISBN);
                                                    JTextField ISBNtextField = new JTextField(null);
                                                    ISBNtextField.setBounds(320,132,175,28);
                                                    Panel.add(ISBNtextField);

                                                    JLabel UserId=new JLabel("<html><h2 style='color:grey;'>User ID</h2></html>");
                                                    UserId.setBounds(180,170,100,50);
                                                    UserId.setForeground(new Color(0,0,0));
                                                    UserId.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                    Panel.add(UserId);
                                                    JTextField UserIdtextField = new JTextField(null);
                                                    UserIdtextField.setBounds(320,182,175,28);
                                                    Panel.add(UserIdtextField);


                                                    JLabel CopiesReturning=new JLabel("<html><h2 style='color:grey;'>Copies</h2></html>");
                                                    CopiesReturning.setBounds(180,220,100,50);
                                                    CopiesReturning.setForeground(new Color(0,0,0));
                                                    CopiesReturning.setFont(new Font("Comic Sans MS",Font.ROMAN_BASELINE, 20));
                                                    Panel.add(CopiesReturning);
                                                    JTextField CopiesReturningtextField = new JTextField(null);
                                                    CopiesReturningtextField.setBounds(320,234,175,28);
                                                    Panel.add(CopiesReturningtextField);

                                                    

                                                    JButton HOLD = new JButton("HOLD");
                                                    HOLD.setBounds(320,320,100,40);
                                                    HOLD.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                                    HOLD.setBackground(new Color(0,0,0));
                                                    HOLD.setForeground(Color.WHITE);
                                                    HOLD.setFocusPainted(false);

                                                    ActionListener HOLDbuttonactionListener=new ActionListener(){
                                                        @Override
                                                        public void actionPerformed(ActionEvent event)
                                                        {
                                                            refresh=0;
                                                            try{
                                                            String ISBNv=ISBNtextField.getText();
                                                            String UserIdv=UserIdtextField.getText();
                                                            int Copiesv=Integer.parseInt(CopiesReturningtextField.getText());
                                                            if(ISBNv.equals("") || UserIdv.equals("") || Copiesv<=0)
                                                            {
                                                                throw new Exception();
                                                            }
                                                            else{
                                                                try
                                                                {
                                                                    Class.forName("org.sqlite.JDBC");
                                                                    Connection con=dbConnect();
                                                                    String Q="select Copiesneed from issuedBooksData where BookId=?";
                                                                    PreparedStatement stmt=con.prepareStatement(Q);
                                                                    stmt.setString(1,ISBNv);
                                                                    ResultSet rs=stmt.executeQuery();
                                                                    if(rs.next())
                                                                    {
                                                                        taken=rs.getInt("Copiesneed");
                                                                    }
                                                                    stmt.close();
                                                                    con.setAutoCommit(true);
                                                                    //con.close();
                                                                    //System.out.println(taken);
                                                                }
                                                                catch(Exception err)
                                                                {
                                                                // System.out.println(err.getMessage());
                                                                }

                                                                if(Copiesv>taken){
                                                                    //System.out.println(taken);
                                                                    JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Incorrect number of books</h></b></html>","VNRVJIET",JOptionPane.ERROR_MESSAGE);}
                                                                else if(Copiesv<=taken)
                                                                {
                                                                        try
                                                                        {
                                                                            Class.forName("org.sqlite.JDBC");
                                                                            Connection con=dbConnect();
                                                                            String Qeql="delete from issuedBooksData where BookId='"+ISBNv+"' and UserId='"+UserIdv+"'";
                                                                            String Qles="update issuedBooksData set Copiesneed=(Copiesneed-"+Copiesv+") where BookId='"+ISBNv+"' and UserId='"+UserIdv+"'";
                                                                            Statement stmt=con.createStatement();
                                                                            int rowsEffected;
                                                                            if(Copiesv==taken)
                                                                            {
                                                                                rowsEffected=stmt.executeUpdate(Qeql);
                                                                                stmt.close();
                                                                                con.setAutoCommit(true);
                                                                                //con.close();
                                                                            }
                                                                            else
                                                                            {
                                                                                rowsEffected=stmt.executeUpdate(Qles);
                                                                                stmt.close();
                                                                                con.setAutoCommit(true);
                                                                                //con.close();
                                                                            }
                                                                            if(rowsEffected>0)
                                                                            {
                                                                                Class.forName("org.sqlite.JDBC");
                                                                                Connection con1=dbConnect();
                                                                                String Q1="Update BooksRack set Quantity=Quantity+"+Copiesv+" where ISBN='"+ISBNv+"'";
                                                                                Statement stmt1=con1.createStatement();
                                                                                int rowsEffected1=stmt1.executeUpdate(Q1);
                                                                                if(rowsEffected1<0)
                                                                                {
                                                                                    throw new Exception();
                                                                                }
                                                                                stmt.close();
                                                                                con1.setAutoCommit(true);
                                                                                //con1.close();
                                                                                JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Thank You for Visiting!!</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);
                                                                            }
                                                                            else
                                                                            {
                                                                                throw new Exception();
                                                                            }
                                                                            ISBNtextField.setText("");
                                                                            UserIdtextField.setText("");
                                                                            CopiesReturningtextField.setText("");
                                                                        }
                                                                        catch(Exception er)
                                                                        {
                                                                            //System.out.println(2+er.getMessage());
                                                                            JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry No matching Data Found!</h></b></html>","VNRVJIET",JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                }
                                                            }
                                                        }
                                                        catch(Exception err)
                                                        {
                                                            //System.out.println(1+err.getMessage());
                                                            JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Invalid Input Retry</h></b></html>","VNRVJIET",JOptionPane.ERROR_MESSAGE);
                                                        }
                                                        
                                                        ISBNtextField.setText("");
                                                        UserIdtextField.setText("");
                                                        CopiesReturningtextField.setText("");
                                                        }
                                                    };

                                                    HOLD.addActionListener(HOLDbuttonactionListener);
                                                    Panel.add(HOLD);
                                                }
                                        }
                                        catch(Exception err){
                                            //System.out.println(err.getMessage());
                                            JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Sorry we couldn't assist you-Retry Later</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);}

                                            frame.revalidate();
                                            frame.repaint();
                                        ///////////////////
                                        }};
                                    
                                    AcceptTodayBookbutton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    AcceptTodayBookbutton.setBackground(new Color(61, 149, 207));
                                    AcceptTodayBookbutton.setForeground(Color.BLACK);
                                    AcceptTodayBookbutton.setFocusPainted(false);
                                    AcceptTodayBookbutton.setBounds(200,520,150,50);
                                    // Add the ActionListener to the button
                                    AcceptTodayBookbutton.addActionListener(actionListenerReturning);
                                    frame.getContentPane().add(AcceptTodayBookbutton);

                                    //highlighting the active buttons

                                    AddBookbutton.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            AddBookbutton.setBackground(Color.CYAN);
                                            ListAllBookbutton.setBackground(new Color(61, 149, 207));
                                            SearchBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueListBookbutton.setBackground(new Color(61, 149, 207));
                                            ReturnTodayBookbutton.setBackground(new Color(61, 149, 207));
                                            AcceptTodayBookbutton.setBackground(new Color(61, 149, 207));

                                        }
                                    });

                                    ListAllBookbutton.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            ListAllBookbutton.setBackground(Color.CYAN);
                                            AddBookbutton.setBackground(new Color(61, 149, 207));
                                            SearchBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueListBookbutton.setBackground(new Color(61, 149, 207));
                                            ReturnTodayBookbutton.setBackground(new Color(61, 149, 207));
                                            AcceptTodayBookbutton.setBackground(new Color(61, 149, 207));

                                        }
                                    });

                                    SearchBookbutton.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            SearchBookbutton.setBackground(Color.CYAN);
                                            ListAllBookbutton.setBackground(new Color(61, 149, 207));
                                            AddBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueListBookbutton.setBackground(new Color(61, 149, 207));
                                            ReturnTodayBookbutton.setBackground(new Color(61, 149, 207));
                                            AcceptTodayBookbutton.setBackground(new Color(61, 149, 207));

                                        }
                                    });

                                    IssueBookbutton.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            IssueBookbutton.setBackground(Color.CYAN);
                                            ListAllBookbutton.setBackground(new Color(61, 149, 207));
                                            SearchBookbutton.setBackground(new Color(61, 149, 207));
                                            AddBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueListBookbutton.setBackground(new Color(61, 149, 207));
                                            ReturnTodayBookbutton.setBackground(new Color(61, 149, 207));
                                            AcceptTodayBookbutton.setBackground(new Color(61, 149, 207));

                                        }
                                    });

                                    IssueListBookbutton.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            IssueListBookbutton.setBackground(Color.CYAN);
                                            ListAllBookbutton.setBackground(new Color(61, 149, 207));
                                            SearchBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueBookbutton.setBackground(new Color(61, 149, 207));
                                            AddBookbutton.setBackground(new Color(61, 149, 207));
                                            ReturnTodayBookbutton.setBackground(new Color(61, 149, 207));
                                            AcceptTodayBookbutton.setBackground(new Color(61, 149, 207));

                                        }
                                    });

                                    ReturnTodayBookbutton.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            ReturnTodayBookbutton.setBackground(Color.CYAN);
                                            ListAllBookbutton.setBackground(new Color(61, 149, 207));
                                            SearchBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueListBookbutton.setBackground(new Color(61, 149, 207));
                                            AddBookbutton.setBackground(new Color(61, 149, 207));
                                            AcceptTodayBookbutton.setBackground(new Color(61, 149, 207));

                                        }
                                    });

                                    AcceptTodayBookbutton.addMouseListener(new MouseInputAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {

                                            AcceptTodayBookbutton.setBackground(Color.CYAN);
                                            ListAllBookbutton.setBackground(new Color(61, 149, 207));
                                            SearchBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueBookbutton.setBackground(new Color(61, 149, 207));
                                            IssueListBookbutton.setBackground(new Color(61, 149, 207));
                                            ReturnTodayBookbutton.setBackground(new Color(61, 149, 207));
                                            AddBookbutton.setBackground(new Color(61, 149, 207));

                                        }
                                    });

                                    

                                    //frame defalut functionalities
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                    frame.setSize(screenSize);
                                    frame.setLayout(null);
                                    frame.setVisible(true);
                                }
                                catch(Exception error)
                                {
                                    JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Couldn't run this application!</h></b></html>","System Error",JOptionPane.ERROR_MESSAGE);
                                }
    }
    
    public static void main(String[] args) throws IOException, InterruptedException
    {        


        try{//execute Batch program for setting classpath 
            String fp="BatchCodeToSetClassPath.bat";
            ProcessBuilder pb=new ProcessBuilder(fp);
            Process p=pb.start();
            int status=p.waitFor();
            if(status==0)//0 for Success
            {
            while(true)
            {
                String psw=JOptionPane.showInputDialog(null, "<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Enter Password:</h></b></html>","VNRVJIET",JOptionPane.OK_CANCEL_OPTION);
                if(psw==null) break;
                else if(psw.equals("123456"))
                {
                    JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Welcome Admin!!</h></b></html>","VNRVJIET",JOptionPane.INFORMATION_MESSAGE);
                    obj=new LibraryMngmnt();
                    break;
                }
                else{JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Incorrect Password</h></b></html>","VNRVJIET",JOptionPane.ERROR_MESSAGE);}
            }
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception errorOccured)
        {
            JOptionPane.showMessageDialog(null,"<html><b><h4 style='color:grey;font-family:Comic Sans MS;'>Couldn't run this application!</h></b></html>","System Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}