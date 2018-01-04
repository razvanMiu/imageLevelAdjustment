import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LevelAdjustment {

	private JFrame frame;
	private ImageClass img = new ImageClass();
	private static float maxW = 1572;
	private static float maxH = 894;
	private int W;
	private int H;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelAdjustment window = new LevelAdjustment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LevelAdjustment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		JLabel label = new JLabel("");
		JSlider contrast = new JSlider();
		contrast.setMajorTickSpacing(25);
		contrast.setSnapToTicks(true);
		contrast.setPaintTicks(true);
		contrast.setPaintLabels(true);
		contrast.setOrientation(SwingConstants.VERTICAL);
		JLabel lblBlackWhite = new JLabel("Black & White Level Adjustment");
		int thickSpacing = 100;
		
		/***********CONTRAST SLIDER*************/
		
		contrast.setValue(0);
		contrast.setMinimum(-100);
		contrast.setBounds(1776, 626, 78, 209);
		frame.getContentPane().add(contrast);
		
		/***********FRAME & LABEL*************/
		
		frame.setBounds(100, 100, 1051, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		label.setBounds(27, 33, (int)maxW, (int)maxH);
		frame.getContentPane().add(label);
		
		
		/***********B&W LABEL*************/
		lblBlackWhite.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBlackWhite.setBounds(1621, 33, 252, 26);
		frame.getContentPane().add(lblBlackWhite);
		
		/***********COLORS LABELS*************/
		JLabel lblRed = new JLabel("Red");
		lblRed.setBackground(Color.RED);
		lblRed.setBounds(1621, 129, 32, 16);
		frame.getContentPane().add(lblRed);
		
		JLabel lblYellow = new JLabel("Yellow");
		lblYellow.setForeground(Color.BLACK);
		lblYellow.setBackground(Color.YELLOW);
		lblYellow.setBounds(1621, 195, 37, 16);
		frame.getContentPane().add(lblYellow);
		
		JLabel lblGreen = new JLabel("Green");
		lblGreen.setBounds(1621, 258, 37, 16);
		frame.getContentPane().add(lblGreen);
		
		JLabel lblCyan = new JLabel("Cyan");
		lblCyan.setBounds(1621, 325, 28, 16);
		frame.getContentPane().add(lblCyan);
		
		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setBounds(1621, 387, 32, 16);
		frame.getContentPane().add(lblBlue);
		
		JLabel lblMagenta = new JLabel("Magenta");
		lblMagenta.setBounds(1621, 454, 49, 16);
		frame.getContentPane().add(lblMagenta);
		
		
		/***********COLORS SLIDERS*************/
		JSlider RED = new JSlider();
		RED.setSnapToTicks(true);
		RED.setMinorTickSpacing(50);
		RED.setPaintTicks(true);
		RED.setPaintLabels(true);
		RED.setToolTipText("");
		RED.setValue(0);
		RED.setMinimum(-300);
		RED.setMaximum(300);
		RED.setForeground(Color.BLACK);
		RED.setBackground(SystemColor.menu);
		RED.setBounds(1673, 129, 200, 53);
		RED.setMajorTickSpacing(thickSpacing);
		frame.getContentPane().add(RED);
		
		
		JSlider YELLOW = new JSlider();
		YELLOW.setForeground(Color.BLACK);
		YELLOW.setSnapToTicks(true);
		YELLOW.setMinorTickSpacing(50);
		YELLOW.setPaintTicks(true);
		YELLOW.setPaintLabels(true);
		YELLOW.setValue(0);
		YELLOW.setMinimum(-300);
		YELLOW.setMaximum(300);
		YELLOW.setBounds(1673, 195, 200, 53);
		YELLOW.setMajorTickSpacing(thickSpacing);
		frame.getContentPane().add(YELLOW);
		
		JSlider GREEN = new JSlider();
		GREEN.setForeground(Color.BLACK);
		GREEN.setSnapToTicks(true);
		GREEN.setMinorTickSpacing(50);
		GREEN.setPaintTicks(true);
		GREEN.setPaintLabels(true);
		GREEN.setValue(0);
		GREEN.setMinimum(-300);
		GREEN.setMaximum(300);
		GREEN.setBounds(1673, 258, 200, 53);
		GREEN.setMajorTickSpacing(thickSpacing);
		frame.getContentPane().add(GREEN);
		
		JSlider CYAN = new JSlider();
		CYAN.setForeground(Color.BLACK);
		CYAN.setSnapToTicks(true);
		CYAN.setMinorTickSpacing(50);
		CYAN.setPaintTicks(true);
		CYAN.setPaintLabels(true);
		CYAN.setValue(0);
		CYAN.setMinimum(-300);
		CYAN.setMaximum(300);
		CYAN.setBounds(1673, 325, 200, 53);
		CYAN.setMajorTickSpacing(thickSpacing);
		frame.getContentPane().add(CYAN);
		
		JSlider BLUE = new JSlider();
		BLUE.setForeground(Color.BLACK);
		BLUE.setSnapToTicks(true);
		BLUE.setMinorTickSpacing(50);
		BLUE.setPaintLabels(true);
		BLUE.setPaintTicks(true);
		BLUE.setValue(0);
		BLUE.setMinimum(-300);
		BLUE.setMaximum(300);
		BLUE.setBounds(1673, 387, 200, 53);
		BLUE.setMajorTickSpacing(thickSpacing);
		frame.getContentPane().add(BLUE);
		
		JSlider MAGENTA = new JSlider();
		MAGENTA.setForeground(Color.BLACK);
		MAGENTA.setSnapToTicks(true);
		MAGENTA.setMinorTickSpacing(50);
		MAGENTA.setPaintLabels(true);
		MAGENTA.setPaintTicks(true);
		MAGENTA.setValue(0);
		MAGENTA.setMinimum(-300);
		MAGENTA.setMaximum(300);
		MAGENTA.setBounds(1673, 454, 200, 53);
		MAGENTA.setMajorTickSpacing(thickSpacing);
		frame.getContentPane().add(MAGENTA);
		
		
		
		/***********OPEN FILE BUTTON*************/
		JButton btnBrowse = new JButton("Open Image");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File("D:\\Workspace\\EclipseWorkspace\\levelAdjustment-temaAVJ\\images"));
				//filter the files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
			  	file.addChoosableFileFilter(filter);
			  	int result = file.showOpenDialog(null);
			  	//if the user click on save in Jfilechooser
			  	if(result == JFileChooser.APPROVE_OPTION){
			  		File selectedFile = file.getSelectedFile();
			  		String path = selectedFile.getAbsolutePath();
			  		img.readImage(path);
			  		label.setIcon(ResizeImage(label));
			  	}
			  	//if the user click on save in Jfilechooser	
			  	else if(result == JFileChooser.CANCEL_OPTION){
			  		System.out.println("No File Select");
			  	}
			}
		});
		btnBrowse.setBounds(27, 940, 121, 25);
		frame.getContentPane().add(btnBrowse);
		
		/***********SAVE FILE BUTTON*************/
		JButton btnSave = new JButton("Save Image");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File("D:\\Workspace\\EclipseWorkspace\\levelAdjustment-temaAVJ\\images"));
				int result = file.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
			  		File fileName = file.getSelectedFile();
			  		String path = fileName.getAbsolutePath();
			  		img.writeImage(path);
			  		label.setIcon(ResizeImage(label));
			  	}
			  	//if the user click on save in Jfilechooser	
			  	else if(result == JFileChooser.CANCEL_OPTION){
			  		System.out.println("No File Select");
			  	}
			}
		});
		btnSave.setBounds(160, 940, 121, 25);
		frame.getContentPane().add(btnSave);
		
		
		/***********CONTRAST BUTTON*************/
		JButton btnContrast = new JButton("Change contrast");
		btnContrast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				img.CONTRAST(contrast.getValue());
				
				label.setIcon(Resize(label));
			}
		});
		btnContrast.setBounds(1621, 626, 131, 25);
		frame.getContentPane().add(btnContrast);
			
	
		/***********B&W BUTTON*************/
		JButton btnBlackWhite = new JButton("Black & White");
		btnBlackWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				img.BLACK_WHITE(RED.getValue(), YELLOW.getValue(), GREEN.getValue(), CYAN.getValue(), BLUE.getValue(), MAGENTA.getValue());
				
				label.setIcon(Resize(label));
			}
		});
		btnBlackWhite.setBounds(1621, 588, 131, 25);
		frame.getContentPane().add(btnBlackWhite);
		
	}
	

    // Methode to resize imageIcon with the same size of a Jlabel
   public ImageIcon ResizeImage(JLabel label) {
	   
	   int imgW = img.getImage().getWidth();
	   int imgH = img.getImage().getHeight();
	   float P = 0;
	   
	   while(imgW > maxW || imgH > maxH) {
		   if(imgW > maxW) {		   
			   P = (float) (maxW / imgW);			   
			   imgW = (int) maxW;
			   imgH = (int) (imgH * P);
		   }
		   
		   if(imgH > maxH) {
			   P = (float) (maxH / imgH);
			   imgH = (int) maxH;
			   imgW = (int) (imgW * P);
			   System.out.printf("P = %.2f ", P);
		   }
		  
	   }
	   W = imgW;
	   H = imgH;
	   
	   Image newImg = img.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
	   ImageIcon image = new ImageIcon(newImg);
	    
	   return image;
   }
   
   public ImageIcon Resize(JLabel label) {
	   
	   Image newImg = img.getImage().getScaledInstance(W, H, Image.SCALE_SMOOTH);
	   ImageIcon image = new ImageIcon(newImg);
	    
	   return image;
   }
  
}
