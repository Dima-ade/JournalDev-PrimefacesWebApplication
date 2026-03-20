package com.journaldev.jsfBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ItemsBean {
    private List<String> items = new ArrayList<>();
    private String selectedItem;


    public ItemsBean() {
        postInit();
    }

    @PostConstruct
    public void postInit() {
        items.add("Watch");
        items.add("Bottle");
        items.add("Book");
        items.add("Radio");
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<String> getItems() {
        return items;
    }
}