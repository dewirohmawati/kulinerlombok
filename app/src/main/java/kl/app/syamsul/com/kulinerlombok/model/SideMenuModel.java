package kl.app.syamsul.com.kulinerlombok.model;

/**
 * Created by syamsul on 7/30/15.
 */
public class SideMenuModel {

    private int icon;
    private String text;

    public SideMenuModel(int icon, String text){
        setIcon(icon);
        setText(text);
    }

    public SideMenuModel(String s) {
        setText(s);
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    public void setText(String text){
        this.text = text;
    }

    public int getIcon(){
        return this.icon;
    }

    public String getText(){
        return this.text;
    }
}
