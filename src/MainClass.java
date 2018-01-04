
public class MainClass {

	public static void main(String[] args) {
		
		String path = "D:\\Workspace\\EclipseWorkspace\\levelAdjustment-temaAVJ\\images\\test.jpg";
		ImageClass myImage = new ImageClass();
		myImage.readImage(path);
		//myImage.BRIGHTNESS(-100);
		//myImage.FILTER_GRAY();
		myImage.BLACK_WHITE(0,0,0,0,0,0);
		//myImage.RED(40);
		//myImage.CONTRAST(40);
		myImage.writeImage("D:\\Workspace\\EclipseWorkspace\\levelAdjustment-temaAVJ\\images\\test1.jpg");
	}

}
