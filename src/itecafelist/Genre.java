package itecafelist;

public class Genre {

  private int g;              //商品分類番号
  private String name;        //商品分類名

  public Genre(int ge, String na) {
	this.g = ge;
	this.name = na;
  }

  public int getG() {
	return g;
  }

  public void setG(int ge) {
	this.g = ge;
  }

  public String getName() {
	return name;
  }

  public void setName(String na) {
	this.name = na;
  }

}
