// Kathy Zeng
// 3/22/21
// ControlPanel.java
// This program gives an example of using several components in order to change the
// different components and/or images.

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel
{
	public static void main(String[] args) 
	{
		ControlPanel ce = new ControlPanel();
		ce.run();
	}
	
	public void run() 
	{
		JFrame frame = new JFrame ("Control Panel for Picture");
		frame.setSize(800, 600);
		frame.setLocation(10, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		CpPanelHolder cph = new CpPanelHolder();
		frame.getContentPane().add(cph);
		frame.setVisible(true);
	}
}

class CpPanelHolder extends JPanel
{
	private int selected;  // the index for the picture selected to draw
	private JTextArea tAQuote;	// text area in the PictPanel, but changed in RightControlPanel2
	private JLabel welcome;	// label in the PictPanel, but changed in RightControlPanel2
	private Font font;  // most fonts are the same, so there is one
	private PictPanel pp; // the variables in the RightControlPanel2 need access to use repaint
	private int val; // value of the slider to change the picture size
	private int width;
	private int height;
	private int [] widthOfImages;  // stores the width of each image
	private int [] heightOfImages;  // stores the height of each image
	
	public CpPanelHolder()
	{
		selected = 0;
		tAQuote = null;
		welcome = null;
		font = null;
		pp = new PictPanel();
		//pp.setLayout(new BorderLayout() );
		add(pp);
		//setLayout(pp);
		//RightControlPanel rcp = new RightControlPanel();
		//add(rcp);
		val = 0;
		width = 0;
		height = 0;
	}
	
	
	/* PictPanel, which has a border layout,  has a label and a text area, both declared above.
	*	most of the code for loading the images is given.  add the rest for the images
	*	plus add the code for the text area, label and font (not necessarily in that order).
	*	The fonts, unless otherwise stated are size 20, bold and Serif.  
	*/
	class PictPanel extends JPanel
	{
		private String[] names;	// the names of the pictures
		private Image[] images;	// array of images to be drawn
		
		public PictPanel()
		{
			setLayout( new BorderLayout(20, 200) );
			welcome = new JLabel("Welcome");
			welcome.setFont(new Font("Serif", Font.BOLD, 20));
			add(welcome, BorderLayout.NORTH);
			tAQuote = new JTextArea("Write the component changed will show here", 20, 30);
			add(tAQuote, BorderLayout.SOUTH);
			names = new String[] {"mountains.jpg", "shanghai.jpg", "trees.jpg", "water.jpg"};
			images = new Image[names.length];
			widthOfImages = new int[names.length];
			heightOfImages = new int[names.length];
			widthOfImages = new int[names.length]; // names.length = 4
				// create the array for the heights
			heightOfImages = new int[names.length];
			// load all of the pictures
			for (int i = 0; i < names.length; i++)
			{
				images[i] = getMyImage(names[i]);	// finish this line
				
				widthOfImages[i] = images[i].getWidth(this);
					// find the heights of each picture
				heightOfImages[i] = images[i].getHeight(this);
			}
		}
		
		// this has been started for you
		public Image getMyImage(String pictName) 
		{
			Image picture = null;
			File imageFile = new File(pictName);
			try
			{
				picture = ImageIO.read(imageFile);
			}

			catch(IOException e)
			{
				System.out.print("\n\n\n" + pictName + " can't be found.\n\n\n");
				e.printStackTrace();
			}
			
			return picture;
		}
		
		// draw the image on a blank screen with the top left corner at (20,20)
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(images[selected], 10,20, widthOfImages[selected], heightOfImages[selected], this);
		}
	}	
		
	/* Make all panels on the right be cyan.
	* RightControlPanel has a border layout.
	* On this panel are:  label, which font size already done, the text field, the menu,
	* the radio buttons and the slider.
	* You will have to determine the layouts in order to make them show up like the sample
	* run provided.
	*/
	 class RightControlPanel extends JPanel
	 {
		private JTextField tfName; // text field for user to type in their name
		private ButtonGroup bg;	// to select the color so only one is selected
		private JRadioButton color1, color2, color3;	// color choices
		private JSlider sSize;	// slider for changing the size of the picture
		
		public RightControlPanel()
		{
			setBackground(Color.CYAN);
			tfName = null;
			bg = null;
			color1 = null;
			color2 = null;
			color3 = null;
			sSize = null; 
			
			setLayout( new BorderLayout(3, 1) );
			setLocation(500, 0);
			setSize(0, 600);
			TextFieldNorth tfn = new TextFieldNorth();
			add(tfn, BorderLayout.NORTH);
			makePictureMenuBar();
	
			//welcome = new JLabel("Control Panel");
			//welcome.setFont(new Font("Serif", Font.BOLD, 20));
			//add(welcome);
			
			// TextField
			JPanel top = new JPanel();
			top.setLayout(new FlowLayout() );
			top.add(tfName);

			// JRadioButtons
			JPanel rbPanel = new JPanel();
			rbPanel.setLayout(new GridLayout(4, 1) );
			rbPanel.setLayout( new GridLayout(200, 50) );
			bg = new ButtonGroup();

			JLabel question1 = new JLabel("Select color of label");
			question1.setFont(new Font("Serif", Font.BOLD, 12));
			rbPanel.add( question1 );
			
			// JSlider
			sSize.setLayout(new GridLayout(1, 1) );
			sSize = new JSlider(0, 200, 20);
			sSize.setMajorTickSpacing(20);	// create tick marks on slider every 5 units
			sSize.setPaintTicks(true);
			sSize.setLabelTable(sSize.createStandardLabels(20) ); // create labels on tick marks
			sSize.setPaintLabels(true);
			sSize.setOrientation(JSlider.HORIZONTAL);
			SliderListener slistener1 = new SliderListener();
			sSize.addChangeListener(slistener1);
			add(sSize, BorderLayout.EAST);

			RButtonHandler rbh = new RButtonHandler();
			setLayout(new GridLayout(4, 1) );
			color1 = new JRadioButton("Red");	// construct button  
			bg.add(color1);					// add button to panel	
			color1.addActionListener(rbh); 	// add listener to JRadioButton
			rbPanel.add(color1);				// add JRadioButton to Panel
	
			color2 = new JRadioButton("Blue");	// construct button  
			bg.add( color2 );		// add b2utton to panel	
			color2.addActionListener(rbh); 		// add listener to button
			rbPanel.add( color2 );
	
			color3 = new JRadioButton("Magenta");	// construct button  
			bg.add( color3 );		// add b3utton to panel	
			color3.addActionListener(rbh); 	// add listener to button
			rbPanel.add( color3 );
			add(rbPanel, BorderLayout.CENTER);
		}
	
		// There are a some more classes that you will need here to add to RightControlPanel
		// You will need to figure them out based on the directions/prompt and the 
		// sample run in the prompt.  You can figure them out based on your drawing of the
		// layout, i.e. your pseudocode for this.
	
	
		public JMenuBar makePictureMenuBar()
		{
			JPanel menuPanel = new JPanel();
			menuPanel.setLayout(new FlowLayout() );
			//menuPanel.add(bg);
			JMenuBar bar = new JMenuBar();
			JMenu picture = new JMenu("Picture");

			JMenuItem pict1 = new JMenuItem("mountains.jpg");
			JMenuItem pict2 = new JMenuItem("shanghai.jpg");
			JMenuItem pict3 = new JMenuItem("trees.jpg");
			JMenuItem pict4 = new JMenuItem("water.jpg");

			PictureMenuHandler pmh = new PictureMenuHandler();		
			pict1.addActionListener(pmh);
			pict2.addActionListener(pmh);	
			pict3.addActionListener(pmh);
			pict4.addActionListener(pmh);
	
			picture.add( pict1 );
			picture.add( pict2 );
			picture.add( pict3 );
			picture.add( pict4 );

			bar.add(picture);

			return bar;
		}
	
		
		// Write the Listener/Handler class for the menu
		public class PictureMenuHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				String name = ""; //
				String command = evt.getActionCommand();
				if ( command.equals( "water.jpg" ) )
					name = "water";	
				else if ( command.equals( "yosemite.jpg" ) )
					name = "yosemite";	
				else if ( command.equals( "trees.jpg" ) )	
					name = "trees";
				else if ( command.equals( "shanghai.jpg" ) )	
					name = "shanghai";
				tAQuote.append("The picture " + name + " was selected.");
			}
		}
		
	
		// write the Listener/Handler class for the text field
		class TextFieldNorth extends JPanel implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				String name = "";
				String phrase = "";
				String command = evt.getActionCommand();
				if ( command.equals( "name typed in" ) )
					tAQuote.append("Welcome " + name);
			}
		}
		

		// write the Listener/Handler class for the slider
		class SliderListener implements ChangeListener
		{
			public void stateChanged(ChangeEvent evt)
			{
				String phrase = "";
				val = sSize.getValue();	// get the value of the slider
				//sSize.add(val);
				// Use width and height
				int maxWidth = 0;
				int maxHeight = 0;
				maxWidth = pp.getWidth() - 20;
				maxHeight = pp.getHeight() - 250;
				width = pp.getWidth()/4;
				height = pp.getHeight()/4;
				 
				tAQuote.setText("The size of the picture was changed by " + val);
				//sSize.add(val);
			}
		}
	
		// write Listener/Handler class for the JRadioButtons
		class RButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				String phrase = "";
				if ( command.equals( "Red" ) )
					setForeground(Color.RED);
				else if ( command.equals( "Blue" ) )
					setForeground(Color.BLUE);	
				else if ( command.equals( "Magenta" ) )	
					setForeground(Color.MAGENTA);
				setForeground(Color.CYAN);
				repaint();
			}
		}
	} 
}	// end class ControlPanel 
