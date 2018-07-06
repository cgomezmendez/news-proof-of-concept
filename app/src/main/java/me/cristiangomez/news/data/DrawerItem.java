package me.cristiangomez.news.data;

public class DrawerItem {
    private String id;
    private int title;
    private int icon;

    public DrawerItem() {
    }

    public DrawerItem(String id, int title, int icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
