package itecafelist;

import java.util.*;

public class Cashier {

  private List<ItemList> list;                        //商品リスト
  private List<Genre> genres;						  //ジャンルリスト
  private List<Receipt> rece = new ArrayList<>();     //レシートリスト
  private List<Receipt> receipt;                      //全体レシートリスト
  private String password = "caShier";                //パスワード
  private double tax = 1.08;                          //消費税
  private int sum;                                    //合計

  public Cashier(List<ItemList> lists, List<Receipt> re, List<Genre> genre) {
	this.list = lists;
	this.receipt = re;
	this.genres = genre;
  }

  public void ca() {      //レジシステム

	receiptAllDelete();

	this.sum = 0;
	int no = 0;

	while (true) {  //レジシステムをループ

	  showList();
	  System.out.println();

	  while (true) {     //正しく商品が指定されるまでループ

		System.out.print("商品番号　");   //商品指定
		String nam = new Scanner(System.in).next();

		if (nam.equals(this.password)) {    //パスワードでレジシステム終了

		  System.out.println("レジシステムを終了します");
		  return;

		}

		try {

		  no = Integer.parseInt(nam) - 1;

		  if (no >= 0 && no < this.list.size()) {   //正しく商品が指定されたときループ抜ける

			break;

		  }

		} catch (NumberFormatException e) {
		}

	  }

	  boolean si = false;

	  if (this.list.get(no).getM() > 0 || this.list.get(no).getL() > 0) {

		si = true;

	  }

	  String s = "";

	  if (si) {       //サイズがあるとき

		while (si) {        //正しくサイズが指定されるまで

		  System.out.print("サイズ　");   //サイズ指定
		  s = new Scanner(System.in).nextLine();

		  switch (s) {

			case "s":   //Sサイズ
			case "S":
			  s = "S";
			  si = this.list.get(no).s();
			  break;

			case "m":   //Mサイズ
			case "M":
			  s = "M";
			  si = this.list.get(no).m();
			  break;

			case "l":   //Lサイズ
			case "L":
			  s = "L";
			  si = this.list.get(no).l();
			  break;

		  }

		}

	  }

	  int ko = 0;

	  while (true) {  //数字が個数として入力されるまでループ

		System.out.print("数量　");                 //数量指定
		String k = new Scanner(System.in).next();

		try {

		  ko = Integer.parseInt(k);
		  break;

		} catch (NumberFormatException e) {
		}

	  }

	  //レシートの記入、表示
	  this.rece.add(new Receipt(this.list.get(no).getName() + " " + s, this.list.get(no).getPrice() + this.list.get(no).getSize(), ko));
	  showReceipt();

	  this.sum += (this.list.get(no).getPrice() + this.list.get(no).getSize()) * ko; //合計

	  while (true) {      //次の行動が正しく指定されるまでループ

		System.out.println("\n0:次の商品　1:前件取り消し　2:全件取り消し　3:会計"); //次の行動を指定
		String key = new Scanner(System.in).next();
		int k;

		if (key.equals(this.password)) {

		  System.out.println("レジシステムを終了します");
		  return;

		}

		try {

		  k = Integer.parseInt(key);

		  switch (k) {

			case 1:     //レシート前件削除
			  receiptDelete();
			  break;

			case 2:     //レシート全件削除
			  receiptAllDelete();
			  break;

			case 3:     //会計
			  accounting();

			  System.out.println("\n0:次へ");
			  String ne = new Scanner(System.in).next();

			  if (ne.equals(this.password)) {     //パスワードでレジシステム終了

				return;

			  }
			  receiptAllDelete();
			  break;

		  }
		  if (k >= 0 && k <= 3 && k != 1) {

			break;

		  }

		} catch (NumberFormatException e) {
		}

	  }

	}

  }

  public void showList() {    //商品リストの表示

	System.out.println("\n■■■■ITE Cafe システム■■■■");

	String ss = "";
	int s = 0;
	int g = this.list.get(0).getGenre();

	for (int j = 0; j < this.genres.size(); j++) {

	  if (this.genres.get(j).getG() == g) {

		System.out.println("【" + this.genres.get(j).getName() + "】");

	  }

	}

	for (int i = 0; i < this.list.size(); i++) {

	  if (g != this.list.get(i).getGenre()) {

		if (s != 0) {

		  System.out.println();
		  ss = "";
		  s = 0;

		}

		g = this.list.get(i).getGenre();

		for (int j = 0; j < this.genres.size(); j++) {

		  if (this.genres.get(j).getG() == g) {

			System.out.println("\n【" + this.genres.get(j).getName() + "】");

		  }

		}

	  }

	  System.out.print(String.format("%3d", (i + 1)) + "　" + this.list.get(i).getMenuName() + "\t\t");
	  ss += String.format("%3d", (i + 1)) + "　" + this.list.get(i).getMenuName() + "\t\t";
	  s += ss.length();

	  if (s >= 70) {

		ss = "";
		s = 0;
		System.out.println();

	  }

	}

	System.out.println();

  }

  public void showReceipt() {     //レシートの表示

	System.out.println();

	for (int i = 0; i < this.rece.size(); i++) {

	  System.out.print(this.rece.get(i).getName() + " " + this.rece.get(i).getPrice() + "円 " + this.rece.get(i).getKo() + "点　　\t");

	  if ((i + 1) % 4 == 0) {

		System.out.println();

	  }

	}

	System.out.println();

  }

  private void receiptAllDelete() {   //レシート全件削除

	this.sum = 0;

	for (int d = this.rece.size() - 1; d >= 0; d--) {

	  this.rece.remove(d);

	}

  }

  private void receiptDelete() {      //レシート前件削除

	int d = this.rece.size() - 1;

	if (d >= 0) {

	  this.sum -= this.rece.get(d).getPrice() * this.rece.get(d).getKo();
	  this.rece.remove(d);

	}

	showReceipt();

  }

  private void accounting() {     //会計

	if (this.sum != 0) {     //会計が必要なとき

	  System.out.println();

	  while (true) {      //チケットが正しく指定されるまでループ

		System.out.print("チケット　1:値引き　2:割引き　3:なし　"); //値引きチケットの確認
		String tic = new Scanner(System.in).next();
		int ticket;

		try {

		  ticket = Integer.parseInt(tic);

		  if (ticket == 1) {                                  //値引きチケットがある場合

			while (true) {

			  System.out.print("値引き金額　");                 //値引き金額指定
			  String t = new Scanner(System.in).next();
			  int ti;

			  try {

				ti = Integer.parseInt(t);
				this.sum -= ti;

				if (this.sum < 0) {

				  this.sum = 0;

				}

				break;

			  } catch (NumberFormatException e) {
			  }

			}
			break;

		  } else if (ticket == 2) {                                  //割引きチケットがある場合

			while (true) {

			  System.out.print("割引き　");                 //割引き金額指定
			  String t = new Scanner(System.in).next();
			  int ti;

			  try {

				ti = Integer.parseInt(t);
				this.sum -= sum / 10 * ti;

				if (this.sum < 0) {

				  this.sum = 0;

				}

				break;

			  } catch (NumberFormatException e) {
			  }

			}
			break;

		  }

		  if (ticket == 3) {

			break;

		  }

		} catch (NumberFormatException e) {
		}

	  }

	  this.sum *= this.tax;       //消費税

	  System.out.println("\n\n合計　" + this.sum);
	  int money = 0;

	  if (sum != 0) {

		while (true) {      //合計以上お支払いされるまでループ

		  System.out.print("お支払い　");
		  String m = new Scanner(System.in).next();

		  try {

			money = Integer.parseInt(m);

			if (money >= this.sum) {

			  break;

			}

		  } catch (NumberFormatException e) {
		  }

		}

		System.out.println("お釣り　" + (money - this.sum));

	  }

	}

  }

  public String getPassword() {
	return password;
  }

  public void setPassword(String password) {
	this.password = password;
  }

  public double getTax() {
	return this.tax;
  }

  public void setTax(double tax) {
	this.tax = tax;
  }

}
