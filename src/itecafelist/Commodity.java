package itecafelist;

import java.util.*;

public class Commodity {

  private List<ItemList> list = new ArrayList<>();
  private List<Genre> genres = new ArrayList<>();
  private String password = "meNu";

  public Commodity() {
	this.list.add(new ItemList(1, "ホットコーヒー", 280, 330));
	this.list.add(new ItemList(1, "紅茶", 260, 310));
	this.list.add(new ItemList(1, "アイスコーヒー", 200, 250, 300));
	this.list.add(new ItemList(1, "アイスティー", 260, 310));
	this.list.add(new ItemList(2, "ショートケーキ", 400));
	this.list.add(new ItemList(2, "チーズケーキ", 400));
	this.list.add(new ItemList(2, "チョコレートケーキ", 450));
	this.list.add(new ItemList(2, "チョコバナナパフェ", 390));
	this.list.add(new ItemList(2, "イチゴパフェ", 390));
	this.list.add(new ItemList(3, "ミートパスタ", 650));
	this.list.add(new ItemList(3, "ミックスピザ", 700));
	this.list.add(new ItemList(4, "クロワッサン", 180));
	this.list.add(new ItemList(4, "トーストサンド", 200));
	this.list.add(new ItemList(4, "フレンチトースト", 210));

	this.genres.add(new Genre(1, "ドリンク"));
	this.genres.add(new Genre(2, "スイーツ"));
	this.genres.add(new Genre(3, "軽食"));
	this.genres.add(new Genre(4, "パン"));
  }

  public List<ItemList> getList() {
	return this.list;
  }

  public List<Genre> getGenres() {
	return genres;
  }

  public void select() {

	System.out.println("\nメニューパスワードが必要です");
	String pa = new Scanner(System.in).next();

	if (!pa.equals(password)) {

	  System.out.println("パスワードが違います");
	  return;

	}

	while (true) {

	  System.out.println("\n0:戻る　1:追加　2:削除　3:変更　4:リスト");
	  String se = new Scanner(System.in).next();
	  int s;

	  try {

		s = Integer.parseInt(se);

		switch (s) {

		  case 0:     //戻る
			return;

		  case 1:     //追加
			add();
			break;

		  case 2:     //削除
			delete();
			break;

		  case 3:     //変更
			set();
			break;

		  case 4:     //リスト
			showList();
			break;

		  default:
			System.out.println();
			break;

		}

	  } catch (NumberFormatException e) {
	  }

	}

  }

  private void add() {        //商品の追加

	System.out.print("\n商品分類　");

	for (Genre ge : this.genres) {

	  System.out.print(ge.getG() + ":" + ge.getName() + " ");

	}

	System.out.println((this.genres.size() + 1) + ":新規");
	String genre = new Scanner(System.in).next();
	int g;

	try {

	  g = Integer.parseInt(genre);

	  if (g > 0) {

		if (g > this.genres.size()) {

		  g = this.genres.size() + 1;
		  System.out.println("新しい商品分類名を入力してください");
		  String genreName = new Scanner(System.in).next();
		  this.genres.add(new Genre(g, genreName));

		}

		System.out.println("商品名　");
		String na = new Scanner(System.in).next();

		System.out.println("値段　");
		String price = new Scanner(System.in).next();
		int p;

		try {

		  p = Integer.parseInt(price);

		  if (p >= 0) {

			System.out.println("Mサイズの値段　0:Mサイズなし");
			String mi = new Scanner(System.in).next();
			int m;

			try {

			  m = Integer.parseInt(mi);

			  if (m > p || m == 0) {

				System.out.println("Lサイズの値段　0:Lサイズなし");
				String la = new Scanner(System.in).next();
				int l;

				try {

				  l = Integer.parseInt(la);

				  if (l > p || l == 0) {

					this.list.add(new ItemList(g, na, p, m, l));
					System.out.println("商品「" + na + "」を追加しました");

				  } else {
					System.out.println("商品を追加せず終了しました");
				  }

				} catch (NumberFormatException e) {
				  System.out.println("商品を追加せず終了しました");
				}

			  } else {
				System.out.println("商品を追加せず終了しました");
			  }

			} catch (NumberFormatException e) {
			  System.out.println("商品を追加せず終了しました");
			}

		  } else {
			System.out.println("商品を追加せず終了しました");
		  }

		} catch (NumberFormatException e) {
		  System.out.println("商品を追加せず終了しました");
		}

	  } else {
		System.out.println("商品を追加せず終了しました");
	  }

	} catch (NumberFormatException e) {
	  System.out.println("商品を追加せず終了しました");
	}

	sort();

  }

  private void delete() {     //商品の削除

	showList();         //商品の表示

	System.out.println("削除する商品　0:戻る");
	String de = new Scanner(System.in).next();
	int d;

	try {

	  d = Integer.parseInt(de) - 1;

	  if (d >= 0 && d < this.list.size()) {

		System.out.println("商品「" + this.list.get(d).getName() + "」を削除しますか？　0:削除");
		de = new Scanner(System.in).next();
		int del;

		try {

		  del = Integer.parseInt(de);

		  if (del == 0) {

			System.out.println("商品「" + this.list.get(d).getName() + "」を削除しました");
			this.list.remove(d);

		  } else {
			System.out.println("商品を削除せずに終了しました");
		  }

		} catch (NumberFormatException e) {
		  System.out.println("商品を削除せずに終了しました");
		}

	  } else {
		System.out.println("商品を削除せずに終了しました");
	  }

	} catch (NumberFormatException e) {
	  System.out.println("商品を削除せずに終了しました");
	}

	sort();

  }

  private void set() {        //商品の変更

	showList();

	System.out.println("変更する商品　0:戻る");
	String se = new Scanner(System.in).next();
	int s;

	try {

	  s = Integer.parseInt(se) - 1;

	  if (s >= 0 && s < this.list.size()) {

		int g = this.list.get(s).getGenre();
		String na = this.list.get(s).getName();
		int p = this.list.get(s).getPrice();
		int m = this.list.get(s).getM();
		int l = this.list.get(s).getL();

		boolean loop = true;

		while (loop) {      //変更を決定するまでループ

		  //商品の情報表示
		  System.out.print("\n商品分類 " + g + ":" + this.genres.get(g - 1).getName() + " 商品名 " + na + " 値段 " + p);

		  if (m > 0) {

			System.out.print(" Mサイズのときの値段 " + m);

		  }

		  if (l > 0) {

			System.out.print(" Lサイズのときの値段 " + l);

		  }

		  System.out.println("\n");

		  System.out.println("何を変更しますか？　0:決定　1:商品分類　2:商品名　3:値段　4:Mサイズの値段　5:Lサイズの値段");
		  String set = new Scanner(System.in).next();
		  System.out.println();
		  int sets;

		  try {

			sets = Integer.parseInt(set);

			switch (sets) {

			  case 0:     //決定
				loop = false;
				break;

			  case 1:     //商品分類の変更
				System.out.print("\n商品分類　");
				for (Genre ge : this.genres) {
				  System.out.print(ge.getG() + ":" + ge.getName() + " ");
				}
				System.out.println((this.genres.size() + 1) + ":新規");
				String genre = new Scanner(System.in).next();

				try {

				  int ge = Integer.parseInt(genre);

				  if (ge > 0) {

					g = ge;

					if (ge >= this.genres.size()) {

					  g = this.genres.size() + 1;
					  System.out.println("新しい商品分類名を入力してください");
					  String genreName = new Scanner(System.in).next();
					  this.genres.add(new Genre(g, genreName));

					}

				  } else {
					System.out.println("変更せず終了しました");
				  }

				} catch (NumberFormatException e) {
				  System.out.println("変更せず終了しました");
				}
				break;

			  case 2:     //商品名の変更
				System.out.println("商品名　");
				na = new Scanner(System.in).next();
				break;

			  case 3:     //商品の値段の変更
				System.out.println("値段　");
				String price = new Scanner(System.in).next();

				try {

				  p = Integer.parseInt(price);

				} catch (NumberFormatException e) {
				  System.out.println("変更せず終了しました");
				}
				break;

			  case 4:     //Mサイズの値段の変更
				System.out.println("Mサイズの値段　0:Mサイズなし");
				String mi = new Scanner(System.in).next();

				try {

				  m = Integer.parseInt(mi);

				} catch (NumberFormatException e) {
				  System.out.println("変更せず終了しました");
				}
				break;

			  case 5:     //Lサイズの値段の変更
				System.out.println("Lサイズの値段　0:Lサイズなし");
				String la = new Scanner(System.in).next();
				try {

				  l = Integer.parseInt(la);

				} catch (NumberFormatException e) {
				  System.out.println("変更せず終了しました");
				}
				break;

			}

		  } catch (NumberFormatException e) {
		  }

		}

		this.list.set(s, new ItemList(g, na, p, m, l));
		sort();
		System.out.println("商品「" + na + "」を変更しました");

	  } else {
		System.out.println("商品を変更せずに終了しました");
	  }

	} catch (NumberFormatException e) {
	  System.out.println("商品を変更せずに終了しました");
	}

	sort();

  }

  public void sort() {        //商品リストの並べ替え

	Collections.sort(this.list, (ItemList ItemFirst, ItemList ItemSecond)
			-> Integer.compare(ItemFirst.getGenre(), ItemSecond.getGenre()));

  }

  public void showList() {        //商品リストの表示

	System.out.println();
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

	System.out.println("\n");

  }

  public String getPassword() {
	return password;
  }

  public void setPassword(String password) {
	this.password = password;
  }

}
