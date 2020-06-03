package itecafelist;

public class ItemList {

  private int genre;             //商品番号
  private String name;        //商品名
  private String menuName;        //商品名
  private int price;          //料金
  private int size = 0;         //サイズごとの追加料金
  private int m = 0;            //Mサイズの料金
  private int l = 0;            //Lサイズの料金

  public ItemList(int g, String na, int p) {   //M、Lサイズのない商品
	this.genre = g;
	this.name = na;
	this.price = p;
  }

  public ItemList(int g, String na, int p, int m) { //Mサイズのある商品
	this(g, na, p);
	this.m = m;
  }

  public ItemList(int g, String na, int p, int m, int l) {   //Lサイズのある商品
	this(g, na, p, m);
	this.l = l;
  }

  public boolean s() { //Sサイズの時の追加料金
	this.size = 0;
	return false;
  }

  public boolean m() { //Mサイズの時の追加料金

	if (this.m != 0) {

	  this.size = this.m - this.price;
	  return false;

	}

	return true;
  }

  public boolean l() { //Lサイズの時の追加料金

	if (this.l != 0) {

	  this.size = this.l - this.price;
	  return false;

	}

	return true;
  }

  public int getGenre() {
	return this.genre;
  }

  public String getName() {
	return this.name;
  }

  public String getMenuName() {
	this.menuName = this.name;

	if (this.m > 0 || this.l > 0) {

	  this.menuName += " S";

	}

	if (this.m > 0) {

	  this.menuName += " M";

	}

	if (this.l > 0) {

	  this.menuName += " L";

	}

	return this.menuName;
  }

  public int getPrice() {
	return this.price;
  }

  public int getSize() {
	return this.size;
  }

  public int getM() {
	return this.m;
  }

  public int getL() {
	return this.l;
  }
}
