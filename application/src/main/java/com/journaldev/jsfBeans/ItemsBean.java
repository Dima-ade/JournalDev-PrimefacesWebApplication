package com.journaldev.jsfBeans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ItemsBean {
    private static final Logger logger = LoggerFactory.getLogger(ItemsBean.class);

    private List<String> items = new ArrayList<>();
    private String selectedItem;


    public ItemsBean() {
        logger.info("=====================ItemsBean===========================");
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