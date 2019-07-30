
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jinmakazuhiro
 */
class ItemKago {
    private ArrayList<Item> list = new ArrayList<Item>();   // 商品
    private int[] count = new int[100];  // 数量

    public ArrayList<Item> getList() {
        return list;
    }

    public void setCount(int count) {
        this.count[list.size()] = count;
    }
    
    // 商品をカゴに入れる
    public void inputKago( Item item, int count ){
        boolean flg = false;
        if( list.isEmpty() == true ){
            this.list.add( item );
            this.count[list.size()-1] = count;            
            flg = true; 
        }
        for( int i = 0; i < list.size(); i++ ){
            if( list.get(i).equals(item) == true && flg == false ){
                this.count[i] += count;
                if( this.count[i] <= 0 ){
                    this.count[i] = 0;
                }
                flg = true;
            }
        }
        if( flg == false ){
            this.list.add( item );
            this.count[list.size()-1] = count;
        }
        
    }
    
    // カゴの商品一覧を出力する
    public void dispKago(){
        for( int i = 0 ; i < list.size(); i++ ){
            System.out.println( i+1 + ":" + list.get( i ).getName() + "/" + count[i] + "個" );
        }
    }
    
    public void clearKago(){
        this.list.clear();
        for( int i = 0; i < this.count.length; i++ ){
            this.count[i] =0;
        }
    }
    
}
