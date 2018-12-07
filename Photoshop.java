import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.image.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class Photoshop extends JComponent {
    public BufferedImage img;
    public BufferedImage orig;

    public Photoshop(String fileName) {
      File input = new File(fileName);
      try{img = ImageIO.read(input); orig=deepCopy(img);} catch (IOException e){System.out.println(e);}
    }

    static BufferedImage deepCopy(BufferedImage bi) {
       ColorModel cm = bi.getColorModel();
       boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
       WritableRaster raster = bi.copyData(null);
       return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
     }

	  @Override
  	public void paintComponent(Graphics graphics) {
        graphics.drawImage(img, orig.getWidth()/2 - img.getWidth()/2, orig.getHeight()/2 - img.getHeight()/2, this);
  	}

    public void blackAndWhite() {
        for(int y = 0; y < img.getHeight(); y++) {
            for(int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int a = (rgb>>24)&0xff;
                int r = (rgb>>16)&0xff;
                int g = (rgb>>8)&0xff;
                int b = rgb&0xff;
                int avg = (r+g+b)/3;
                int newrgb = (a<<24) | (avg<<16) | (avg<<8) | avg;
                img.setRGB(x, y, newrgb);
                repaint();
            }
            try{Thread.sleep(10);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
        }
    }

    public void increaseBrightness() {
      for(int y = 0; y < img.getHeight(); y++) {
          for(int x = 0; x < img.getWidth(); x++) {
              int rgb = img.getRGB(x, y);
              int a = (rgb>>24)&0xff;
              int r = (rgb>>16)&0xff; r = Math.min(255, r + (int)(r * .5));
              int g = (rgb>>8)&0xff; g = Math.min(255, g + (int)(g * .5));
              int b = rgb&0xff; b = Math.min(255, b + (int)(b * .5));
              int newrgb = (a<<24) | (r<<16) | (g<<8) | b;
              img.setRGB(x, y, newrgb);
              repaint();
          }
          try{Thread.sleep(10);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      }
    }

    public void decreaseBrightness() {
      for(int y = img.getHeight() - 1; y >= 0; y--) {
          for(int x = img.getWidth() - 1; x >= 0; x--) {
              int rgb = img.getRGB(x, y);
              int a = (rgb>>24)&0xff;
              int r = (rgb>>16)&0xff; r = Math.max(0, r - (int)(r * .5));
              int g = (rgb>>8)&0xff; g = Math.max(0, g - (int)(g * .5));
              int b = rgb&0xff; b = Math.max(0, b - (int)(b * .5));
              int newrgb = (a<<24) | (r<<16) | (g<<8) | b;
              img.setRGB(x, y, newrgb);
              repaint();
          }
          try{Thread.sleep(10);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      }
    }

    public void warmer() {
      for(int x = 0; x < img.getWidth(); x++) {
          for(int y = 0; y < img.getHeight(); y++) {
              int rgb = img.getRGB(x, y);
              int a = (rgb>>24)&0xff;
              int r = (rgb>>16)&0xff; r = Math.min(255, r + 20);
              int g = (rgb>>8)&0xff;
              int b = rgb&0xff; b = Math.max(0, b - 20);
              int newrgb = (a<<24) | (r<<16) | (g<<8) | b;
              img.setRGB(x, y, newrgb);
              repaint();
          }
          try{Thread.sleep(10);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      }
    }

    public void colder() {
      for(int x = img.getWidth() - 1; x >= 0; x--) {
          for(int y = 0; y < img.getHeight(); y++) {
              int rgb = img.getRGB(x, y);
              int a = (rgb>>24)&0xff;
              int r = (rgb>>16)&0xff; r = Math.max(0, r - 20);
              int g = (rgb>>8)&0xff;
              int b = rgb&0xff; b = Math.min(255, b + 20);
              int newrgb = (a<<24) | (r<<16) | (g<<8) | b;
              img.setRGB(x, y, newrgb);
              repaint();
          }
          try{Thread.sleep(10);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      }
    }

    public void inverse() {
      for(int y = 0; y < img.getHeight(); y++) {
          for(int x = 0; x < img.getWidth(); x++) {
              int rgb = img.getRGB(x, y);
              int a = (rgb>>24)&0xff;
              int r = (rgb>>16)&0xff; r = 255-r;
              int g = (rgb>>8)&0xff; g = 255-g;
              int b = rgb&0xff; b = 255-b;
              int newrgb = (a<<24) | (r<<16) | (g<<8) | b;
              img.setRGB(x, y, newrgb);
              repaint();
          }
          try{Thread.sleep(10);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      }
    }

    public void sunsetMode() {
      for(int y = 0; y < img.getHeight(); y++) {
          for(int x = 0; x < img.getWidth(); x++) {
              int rgb = img.getRGB(x, y);
              int a = (rgb>>24)&0xff;
              int r = (rgb>>16)&0xff;
              int g = (rgb>>8)&0xff;
              int b = rgb&0xff;

              r = Math.min(255,(int) (r * 1.5));
              g = g - (int)(g * .25);
              b = b - (int)(b * .15);

              int newrgb = (a<<24) | (r<<16) | (g<<8) | b;
              img.setRGB(x, y, newrgb);
              repaint();
          }
          try{Thread.sleep(10);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      }
    }

    public void painting() {
      for(int x = 0; x < img.getWidth(); x++) {
          for(int y = 0; y < img.getHeight(); y++) {
              int rgb = img.getRGB(x, y);
              int a = (rgb>>24)&0xff - 55;
              int r = (rgb>>16)&0xff - 55;
              int g = (rgb>>8)&0xff - 55;
              int b = rgb&0xff;
              int newrgb = (a<<24) | (r<<16) | (g<<8) | b;
              img.setRGB(x, y, newrgb);
              repaint();
          }
          try{Thread.sleep(10);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
      }
    }

    public void cropImage() {
      Scanner scan = new Scanner(System.in);
      System.out.println("\nPlease enter cropping values in pixels.");
      System.out.println("X and Y mark the coordinate of your new image's top left corner.");
      System.out.println("Width and height mark the width and height from this coordinate for your new image.");

      System.out.print("X: ");
      int x = scan.nextInt();
      System.out.print("Y: ");
      int y = scan.nextInt();
      System.out.print("WIDTH: ");
      int w = scan.nextInt();
      System.out.print("HEIGHT: ");
      int h = scan.nextInt();

      if(x + w > img.getWidth() || y + h > img.getHeight()) {
          while(x + w > img.getWidth() || y + h > img.getHeight()){
            System.out.println("Your crop goes out of bounds. Please stay within the image's frame.");
            System.out.print("X: ");
            x = scan.nextInt();
            System.out.print("Y: ");
            y = scan.nextInt();
            System.out.print("WIDTH: ");
            w = scan.nextInt();
            System.out.print("HEIGHT: ");
            h = scan.nextInt();
          }
      }
      img = img.getSubimage(x, y, w, h);
      repaint();
    }

    public void export() {
      Scanner scan = new Scanner(System.in);
      System.out.print("\nPlease enter your new image's name: ");
      String fileName = scan.next() + ".jpg";

      File outputfile = new File(fileName);
      try{ImageIO.write(img, "jpg", outputfile);} catch (IOException e) {System.out.println(e);};

    }

    public void resetImg() {
      img = deepCopy(orig);
      repaint();
    }

    public static void main(String[] args) {
        //get image the user will be editing
        Scanner scanner = new Scanner(System.in);
        System.out.println("~~WELCOME TO PHOTOSHOP~~");
        System.out.print("Please enter the image you'd like to edit: ");
        String fileName = scanner.nextLine();

        //create frame for graphics
        Photoshop editing = new Photoshop(fileName);
        JFrame canvas = new JFrame("Photoshop");
        canvas.add(editing);

        //set frame settings
        canvas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        canvas.setSize(editing.img.getWidth(), editing.img.getHeight());
        canvas.setVisible(true);

        System.out.println("\nINSTRUCTIONS:");
        System.out.println("1.) Type a command then press RETURN.");
        System.out.println("2.) Your available commands are: 'BRIGHTER', 'DARKER', 'COOLER', 'WARMER', 'GRAYSCALE', 'PAINTING', 'SUNSET', 'INVERSE', 'CROP', 'RESET', 'EXPORT', and 'NEW'.");
        System.out.println("3.) Enter 'HELP' at any time to repeat the instructions. Enter 'QUIT' to quit");

        String input = "";
        while(!(input.equals("QUIT"))) {
            System.out.print(">> ");
            input = scanner.next();

            if(input.equals("BRIGHTER")) {
                editing.increaseBrightness();
            } else if (input.equals("DARKER")) {
                editing.decreaseBrightness();
            } else if (input.equals("COOLER")) {
                editing.colder();
            } else if (input.equals("WARMER")) {
                editing.warmer();
            } else if (input.equals("GRAYSCALE")) {
                editing.blackAndWhite();
            } else if (input.equals("PAINTING")) {
                editing.painting();
            } else if (input.equals("SUNSET")) {
                editing.sunsetMode();
            } else if (input.equals("INVERSE")) {
                editing.inverse();
            } else if (input.equals("CROP")) {
                editing.cropImage();
            } else if (input.equals("RESET")) {
                editing.resetImg();
            } else if (input.equals("NEW")) {
                canvas.setVisible(false);
                canvas.dispose();

                System.out.print("Please enter the image you'd like to edit: ");
                fileName = scanner.next();
                editing = new Photoshop(fileName);
                canvas = new JFrame("Photoshop");
                canvas.add(editing);

                canvas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                canvas.setSize(editing.img.getWidth(), editing.img.getHeight());
                canvas.setVisible(true);
            } else if (input.equals("EXPORT")) {
                editing.export();
            } else if (input.equals("HELP")) {
                System.out.println("\nINSTRUCTIONS:");
                System.out.println("1.) Type a command then press RETURN.");
                System.out.println("2.) Your available commands are: 'BRIGHTER', 'DARKER', 'COOLER', 'WARMER', 'GRAYSCALE', 'PAINTING', 'SUNSET', 'INVERSE', 'CROP', 'RESET', 'EXPORT', and 'NEW'.");
                System.out.println("3.) Enter 'HELP' at any time to repeat the instructions. Enter 'QUIT' to quit");
            } else if (input.equals("QUIT")) {
                //Do nothing, loop will exit
            } else {
                System.out.println("ERROR: Unknown command. Please try again or type 'HELP'.");
            }
        }
        canvas.setVisible(false);
        canvas.dispose();
    }
}
