
import java.io.File;
public class BaseCard extends Object {
protected int rank;
protected String suit;
protected boolean faceUp;
private int x;
private int y;
private final int WIDTH = 50;
private final int HEIGHT = 70;
protected static processing.core.PApplet processing;
private processing.core.PImage cardImage;
private static processing.core.PImage cardBack;
public BaseCard(int rank,String suit) {
   if(processing == null) {
    throw new IllegalStateException("Processing environment has not been sey");
  }
   this.faceUp = false;
  this.rank = rank;
  this.suit = suit;
   cardImage = processing.loadImage("images" + File.separator + rank + "_of_" + suit.toLowerCase()
  + ".png");
   //cardBack = processing.loadImage("images" + File.separator + rank + "_of_" + suit.toLowerCase()+ "back.png");
}
 public static void setProcessing(processing.core.PApplet processing) {
  BaseCard.processing = processing; //chat gpt helped me create this line!!
}
  public int getRank() {
  if(this.rank == 13 && "diamonds".equalsIgnoreCase(this.suit)) {
    return -1;
  }
   return this.rank;
}
  public void setFaceUp(boolean faceUp) {
  if(faceUp == true) {
    this.faceUp = true;
  }else {
    this.faceUp = false;
  }
}
  public String toString() {
  String cardValue = String.valueOf(this.rank);
  String faceOfCard = this.suit;
  String combination = faceOfCard + " " + cardValue;
    
  return combination;
}
  public void draw(int xPosition, int yPosition) {
  this.x = xPosition;
  this.y = yPosition;
   processing.fill(255);
  processing.rect(xPosition, yPosition, WIDTH, HEIGHT);
   if(faceUp == true) {
    processing.image(cardImage, xPosition, yPosition, WIDTH, HEIGHT);
  }else {
    processing.image(cardBack, xPosition, yPosition, WIDTH, HEIGHT);
  }
 
}
  public boolean isMouseOver() {
   int width = this.WIDTH;
   int height = this.HEIGHT;
   int mouseX = processing.mouseX;
   int mouseY = processing.mouseY;
   //TA JR helped me step by step on what to do below
  if((this.x - width) < mouseX && mouseX < (this.x + width) &&
      (this.y - height) < mouseY && mouseY < (this.y + height)) {
    return true;
  }else {
    return false;
  }
 }
}
