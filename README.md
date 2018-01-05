# imageLevelAdjustment

This is an attempt to make an application which blend some Java programming that may help you:
  - Convert image to grayscale
  - Adjust Brightness of an image
  - Adjust Contrast of an image
  - Adjust Black & White of an image
  - Create Java GUI
    - Browse button
    - Save button
    - Contrast button
    - B & W button


# ImageClass.java

## Attributes:
```java
private BufferedImage image     = null;     //  Stores the image
private BufferedImage imageCopy = null;     //  Stores a copy of the image
private BufferedImage grayImage = null;     //  Used for B & W function
private File f = null;                      //  Stores the image file path
```
## Read image
```java
public void readImage(String path) {
	try {
	    this.f = new File(path);
	    this.image = ImageIO.read(this.f);
  	} catch(IOException e) {
      		System.out.println("Error: " + e);
    	}
}
```
## Write image
```java
public void writeImage(String newPath) {
	try{
    		this.f = new File(newPath);  
    		ImageIO.write(this.image, "jpg", this.f);
    		System.out.println("Writing complete.");
  	} catch(IOException e) {
      		System.out.println("Error: " + e);
    	}
}
```
* **"Try"** and **"catch"** are keywords that represent the handling of exceptions due to data or coding errors during program execution. A try block is the block of code in which exceptions occur. A catch block catches and handles try block exceptions.
* **IOException** signals that an I/O exception of some sort has occurred.
## Getting and setting RGB components of a pixel
* **getRGB(x, y)** method of **BufferedImage** returns an int of 32 bits in which:
  - 0-7 represents value of BLUE
  - 8-15 represents value of GREEN
  - 16-23 represents value of RED
  - 24-31 represents value of ALPHA
  
So, to take the value of a component we have to right shift through the bits and then convert it to decimal using & operator.

```
0xff(hex) = 11111111(2) = 255(10)
```

```java
int p = this.image.getRGB(x, y);

int A = (p>>24)&0xff;
int R = (p>>16)&0xff;
int G = (p>>8)&0xff;
int B = p&0xff;

p = (A << 24) | (R << 16) | (G << 8) | B;
this.image.setRGB(x, y, p);
```
## Brightness algorithm
  To adjust the brightness of an image all you have to do is add the desired change in brightness to each pixel RGB component. Also, if the new value exceeds 255 it will remain 255 and if the new value is below 0 it will take the value of 0.
  
```java
public void BRIGHTNESS(int B) {
	// Get image width and height
	int width = this.imageCopy.getWidth();
	int height = this.imageCopy.getHeight();

	for(int y = 0; y < height; y++) {
		for(int x = 0; x < width; x++) {

			int p = this.imageCopy.getRGB(x, y);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;

			//Add brightness
			r = this.Truncate((int) (r + B));
			g = this.Truncate((int) (g + B));
			b = this.Truncate((int) (b + B));

			p = (r << 16) | (g << 8) | b;
			this.image.setRGB(x, y, p);
		}
	}
}

private int Truncate(int value) {
	if(value < 0) return 0;
	if(value > 255) return 255;
	return value;
}
```

## Grayscale algorithm
A very simple method is to change the value of each pixel with the average of RGB components.

```L = (R + G + B) / 3```

Another method is to use the following equation:

```L = 0.299 * R + 0.587 * G + 0.114 * B```

```java
public void FILTER_GRAY() {
	// Get image width and height
	int width = this.imageCopy.getWidth();
	int height = this.imageCopy.getHeight();

	for(int y = 0; y < height; y++) {
		for(int x = 0; x < width; x++){

			int p = this.imageCopy.getRGB(x, y);

			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;

			//	Find average for (x, y) pixel
			int avg = (r+g+b)/3;

			p = (avg << 16) | (avg << 8) | avg;
			this.image.setRGB(x, y, p);
		}
	}
}
```
## Contrast algorithm
The first step is to calculate a contrast corection factor:

```F = (259*(C + 255)) / (255*(259 - C))```

- F -> contrast corection factor
- C -> desired level of contrast

The next step is to perform the actual contrast adjustment itself.

```
R' = F(R - 128) + 128
G' = F(G - 128) + 128
B' = F(B - 128) + 128
```

So the code for this is:

```java
public void CONTRAST(int C) {
	// Contrast correction factor
	float factor = (float)(259 *(C + 255)) / (255 * (259 - C));
		
	// Get image width and height
	int width = this.imageCopy.getWidth();
	int height = this.imageCopy.getHeight();

	for(int y = 0; y < height; y++) {	
		for(int x = 0; x < width; x++){

			int p = this.imageCopy.getRGB(x, y);

			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;

			//	Calculate contrast correction
			r = this.Truncate((int) (factor * (r - 128) + 128));
			g = this.Truncate((int) (factor * (g - 128) + 128));
			b = this.Truncate((int) (factor * (b - 128) + 128));

			p = (r << 16) | (g << 8) | b;
			this.image.setRGB(x, y, p);
		}
	}
}
```
# Black & White algorithm
For this one I couldn't find documentation, so if you have something too add please feel free to add. The idea is to convert the image to B & W and then adjust the brightness of a certain color palette.
There are six palettes:
- ![#ff0000](https://placehold.it/15/ff0000/000000?text=+)`(RED)`
- ![#ffff00](https://placehold.it/15/ffff00/000000?text=+)`(YELLOW)`
- ![#00ff00](https://placehold.it/15/00ff00/000000?text=+)`(GREEN)`
- ![#00ffff](https://placehold.it/15/00ffff/000000?text=+)`(CYAN)`
- ![#0000ff](https://placehold.it/15/0000ff/000000?text=+)`(BLUE)`
- ![#ff00ff](https://placehold.it/15/ff00ff/000000?text=+)`(MAGENTA)`

To separate the RGB I used 3 if statements:
```
//	RED
if(r > g && r > b) {
	...
}
// GREEN
if(g > r && g > b) {
	...
}
// BLUE
if(b > r && b > g) {
	...
} 
```

To separate the CMY I converted the RGB to CMYK and then I used 3 if as above

#### RGB to CMYK
```java
float cyan 	= 1.0f - r / 255.0f;
float magenta 	= 1.0f - g / 255.0f;
float yellow 	= 1.0f - b / 255.0f;
float k = Math.min(cyan, Math.min(magenta, yellow));

if(k >= 1.0f) {
	cyan 	= 0;
	magenta = 0;
	yellow 	= 0;
} else {
	float s = 1.0f - k;
	cyan 	= (cyan - k) / s;
	magenta = magentam - k) / s;
	yellow 	= (yellow - k) / s;
}				
```

```
// CYAN
if(cyan > yellow && cyan > magenta) {
	...
}
// MAGENTA 
if(magenta > yellow && magenta > cyan) {
	...
}
// YELLOW
if(yellow > cyan && yellow > magenta) {
	...
}
```

#### RGB to HSV
```
float HSV[] = new float[3];
Color.RGBtoHSB(r, g, b, HSV);

HSV[0] ---> Hue
HSV[1] ---> Saturation
HSV[2] ---> Brigthness
```

#### Add brightness to a certain color palette
For this we pass the amount of brightness to six variables: `RED`, `YELLOW`, `GREEN`, `CYAN`, `BLUE` and `MAGENTA` and then adjust it for a certain pixel according to the amount of **saturation**, **brightness** and the amount of desired **color**.

If you see something like ```HSV[1] * HSV[1]``` that's to decrease the brightness (RED for example) of **white** palette, because the saturation of this palette is between 0% - 20% (0 - 0.2) and also ```HSV[2] * HSV[2]``` is used to decrease the brightness (GREEN for example) of **black** palette, because the brightness of this palette is between 0% - 20% (0 - 0.2).

The following equation are not very smart but that's what I came up with:

```
RED 	= (int) (8 * RED * HSV[1] * HSV[2] * HSV[2] * (magenta - cyan));
GREEN 	= (int) (40 * GREEN * HSV[1] * HSV[2] * (yellow - magenta) * (cyan - magenta));
BLUE 	= (int) (70 * BLUE * HSV[1]  * HSV[2] * (b/255.0 - g/255.0) * (b/255.0 - g/255.0));
CYAN 	= (int) (10 * CYAN * HSV[1] * HSV[2] * (cyan - magenta) * g/255.0);
YELLOW 	= (int) (3 * YELLOW * HSV[1] * HSV[2] * (yellow - magenta) * r/255.0);
```

I don't mention MAGENTA because I couldn't find something that works. I also want to mention that we work with the colored image when we compute the six brightness values and then apply the brightness on the grayscaled image. So we have an imageCopy and a grayImage.

The algorithm is:

```java
public void BLACK_WHITE(int RED, int YELLOW, int GREEN, int CYAN, int BLUE, int MAGENTA) {
		
	this.FILTER_GRAY_OF(this.grayImage);			// Convert given image to grayscale
	int RED_COPY = RED;
	int YELLOW_COPY = YELLOW;
	int GREEN_COPY = GREEN;
	int CYAN_COPY = CYAN;
	int BLUE_COPY = BLUE;
	int MAGENTA_COPY = MAGENTA;

	// 	Get image width and height
	int width = this.imageCopy.getWidth();
	int height = this.imageCopy.getHeight();

	for(int y = 0; y < height; y++) {
		for(int x = 0; x < width; x++){

			RED = RED_COPY;
			YELLOW = YELLOW_COPY;
			GREEN = GREEN_COPY;
			CYAN = CYAN_COPY;
			BLUE = BLUE_COPY;
			MAGENTA = MAGENTA_COPY;

			int p = this.imageCopy.getRGB(x, y);
			
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			
			float cyan = 1.0f - r / 255.0f;
			float magenta = 1.0f - g / 255.0f;
			float yellow = 1.0f - b / 255.0f;
			float k = Math.min(cyan, Math.min(magenta, yellow));
			
			if(k >= 1.0f) {
				cyan = 0;
				magenta = 0;
				yellow = 0;
			} else {
				float s = 1.0f - k;
				cyan 	= (cyan - k) / s;
				magenta = (magenta - k) / s;
				yellow 	= (yellow - k) / s;
			}

			float HSV[] = new float[3];
			Color.RGBtoHSB(r, g, b, HSV);
			
			//	Compute brightness
			RED = (int) (8 * RED * HSV[1] * HSV[2] * HSV[2] * (m - c));
			YELLOW = (int) (3 * YELLOW * HSV[1] * HSV[2] * (yy - m) * r/255.0);
			GREEN = (int) (40 * GREEN * HSV[1] * HSV[2] * (yy - m) * (c - m));
			CYAN = (int) (10 * CYAN * HSV[1] * HSV[2] * (c - m) * g/255.0);
			BLUE = (int) (70 * BLUE * HSV[1]  * HSV[2] * (b/255.0 - g/255.0) * (b/255.0 - g/255.0));
			MAGENTA = (int) (10 * MAGENTA * HSV[1] * HSV[1] * HSV[2] * HSV[2] * (k*k));

			//	RED
			if(r > g && r > b) {
				this.BRIGHTNESS_AT(RED, this.grayImage, x, y);
			}
			// 	YELLOW
			if(yy > c && yy > m) {
				this.BRIGHTNESS_AT(YELLOW, this.grayImage, x, y);
			}
			// 	GREEN
			if(g > r && g > b) {
				this.BRIGHTNESS_AT(GREEN, this.grayImage, x, y);
			}
			// 	CYAN
			if(c > yy && c > m) {
				this.BRIGHTNESS_AT(CYAN, this.grayImage, x, y);
			}
			// 	BLUE
			if(b > r && b > g) {
				this.BRIGHTNESS_AT(BLUE, this.grayImage, x, y);
			} 
			// 	MAGENTA 
			if(m > yy && m > c) {
				this.BRIGHTNESS_AT(MAGENTA, this.grayImage, x, y);
			}
		}
	}
	
	// this.BRIGHTNESS_AT(int, BufferedImage, int, int) -> adds brightness to a specific pixel of the image

	this.image = this.grayImage;
}
```

# LevelAdjustment.java
It is the GUI of the application and here I will highlight how to make an **open file button** and **save button**

#### Browse button

```java
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
```

#### Save button

```java
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
```

