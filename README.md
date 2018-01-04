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
    ImageIO.write(image, "jpg", this.f);
    System.out.println("Writing complete.");
  } catch(IOException e) {
      System.out.println("Error: " + e);
    }
}
```
* **"Try"** and **"catch"** are keywords that represent the handling of exceptions due to data or coding errors during program execution. A try block is the block of code in which exceptions occur. A catch block catches and handles try block exceptions.
* **IOException** signals that an I/O exception of some sort has occurred.

## Brightness algorithm
* To adjust the brightness of an image all you have to do is add the desired change in brightness to each pixel RGB components
