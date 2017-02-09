package ua.com.bankfreedom.inerfaces;

/**
 * Created by Администратор on 08.12.16.
 */
public class test {
    public static void main(String[] args) {
        char [] ch=null;

        for(int max=999*999;max>=100*100;max--){
            ch=(""+max).toCharArray();
            if(ch.length==6){
                if((ch[0]==ch[5])&&(ch[1]==ch[4]&&ch[2]==ch[3])){
                    for(int i=999;i>=100;i--){
                        if(max%i==0) {System.out.print(max+"="+i+"*"+max/i); return;}
                        if((max/i)>999) break;
                    }
                }
            }
        }

    }
}
