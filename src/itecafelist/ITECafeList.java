package itecafelist;

import java.util.*;

public class ITECafeList {

  private static String password = "caFe";

  public static void main(String[] args) {

	Commodity com = new Commodity();
	List<ItemList> list = com.getList();
	List<Genre> ge = com.getGenres();

	List<Receipt> receipt = new ArrayList<>();

	Cashier ca = new Cashier(list, receipt, ge);

	int a = 0;

	while (true) {

	  System.out.println("システムパスワードを入力してください");
	  String pp = new Scanner(System.in).next();

	  if (!pp.equals(ITECafeList.password)) {

		System.out.println("パスワードが違います");
		a++;

		if (a > 3) {

		  System.out.println("システムを終了します");
		  System.exit(1);

		}

	  } else {
		break;
	  }

	}

	while (true) {      //パスワードが打ち込まれるまでループ

	  System.out.println("\n1:レジシステム　2:メニュー管理　3:パスワード変更　4:消費税の変更");
	  String select = new Scanner(System.in).next();
	  int s;

	  if (select.equals(ITECafeList.password)) {        //パスワードでシステム終了

		System.out.println("\nシステムを終了します\n");
		System.exit(0);

	  }

	  try {

		s = Integer.parseInt(select);

		switch (s) {

		  case 1:     //レジシステム
			ca.ca();
			break;

		  case 2:     //商品管理
			com.select();
			break;

		  case 3:     //パスワード変更
			password(ca, com);
			break;

		  case 4:     //税金変更
			setTax(ca);
			break;

		}

	  } catch (NumberFormatException e) {
	  }

	}

  }

  public static void password(Cashier ca, Commodity com) {

	System.out.println("\nシステムパスワードが必要です");
	String pass = new Scanner(System.in).next();

	if (!pass.equals(ITECafeList.password)) {

	  System.out.println("パスワードが違います");
	  return;

	}

	System.out.println("\nどのパスワードを変更しますか？ 1:レジシステム 2:メニュー管理 3:システム");
	String select = new Scanner(System.in).next();

	try {

	  int s = Integer.parseInt(select);

	  switch (s) {

		case 1:
		  System.out.println("\n現在のレジシステムパスワードを入力してください");
		  pass = new Scanner(System.in).next();

		  if (pass.equals(ca.getPassword())) {
			System.out.println("\n新しいレジシステムパスワードを入力してください");
			String password1 = new Scanner(System.in).next();
			System.out.println("確認のため、もう1度 新しいレジシステムパスワードを入力してください");
			String password2 = new Scanner(System.in).next();

			if (password2.equals(password1)) {

			  ca.setPassword(password1);
			  System.out.println("パスワードを変更しました");

			} else {
			  System.out.println("パスワードが一致しませんでした\nパスワードを変更せず終了します");
			}

		  } else {
			System.out.println("パスワードが違います");
		  }

		  break;

		case 2:
		  System.out.println("\n現在のメニューパスワードを入力してください");
		  pass = new Scanner(System.in).next();

		  if (pass.equals(com.getPassword())) {

			System.out.println("\n新しいメニューパスワードを入力してください");
			String password1 = new Scanner(System.in).next();
			System.out.println("確認のため、もう1度 新しいメニューパスワードを入力してください");
			String password2 = new Scanner(System.in).next();

			if (password2.equals(password1)) {

			  com.setPassword(password1);
			  System.out.println("パスワードを変更しました");

			} else {
			  System.out.println("パスワードが一致しませんでした\nパスワードを変更せず終了します");
			}

		  } else {
			System.out.println("パスワードが違います");
		  }

		  break;

		case 3:
		  System.out.println("\n現在のシステムパスワードを入力してください");
		  pass = new Scanner(System.in).next();

		  if (pass.equals(ITECafeList.password)) {

			System.out.println("\n新しいシステムパスワードを入力してください");
			String password1 = new Scanner(System.in).next();
			System.out.println("確認のため、もう1度 新しいシステムパスワードを入力してください");
			String password2 = new Scanner(System.in).next();

			if (password2.equals(password1)) {

			  ITECafeList.password = password1;
			  System.out.println("パスワードを変更しました");

			} else {
			  System.out.println("パスワードが一致しませんでした\nパスワードを変更せず終了します");
			}

		  } else {
			System.out.println("パスワードが違います");
		  }

		  break;

	  }

	} catch (NumberFormatException e) {
	}

  }

  public static void setTax(Cashier ca) {

	System.out.println("\nシステムパスワードが必要です");
	String pass = new Scanner(System.in).next();

	if (!pass.equals(ITECafeList.password)) {

	  System.out.println("パスワードが違います");
	  return;

	}

	double tax = ca.getTax();

	System.out.println("\n現在の消費税 " + tax);
	System.out.print("変更後の消費税 ");
	String ta = new Scanner(System.in).next();

	try {

	  tax = Double.parseDouble(ta);
	  ca.setTax(tax);

	} catch (NumberFormatException e) {
	  System.out.println("消費税を変更せず終了しました");
	}

  }

}
