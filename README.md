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

Another method is to use the following 

```L = 0.299 * R + 0.587 * G + 0.114 * B```
