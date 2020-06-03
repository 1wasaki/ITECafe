package itecafelist;

public class Receipt {

  private String name;        //商品名
  private int price;          //値段
  private int ko;             //個数

  public Receipt(String na, int p, int ko) {
	this.name = na;
	this.price = p;
	this.ko = ko;
  }

  public String getName() {
	return name;
  }

  public int getPrice() {
	return price;
  }

  public int getKo() {
	return ko;
  }
}
