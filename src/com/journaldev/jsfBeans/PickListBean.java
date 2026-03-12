package com.journaldev.jsfBeans;

import com.journaldev.data.Employee;
import com.journaldev.data.Student;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@RequestScoped
public class PickListBean {
    private DualListModel<Student> listModel;

    public PickListBean() {
        init();
    }

    @PostConstruct
    public void init() {
        //initial unselected list
        List<Student> sourceList = Arrays.asList(
                new Student("1", "Jim", "IT"),
                new Student("2", "Sara", "Sale"),
                new Student("3", "Tom", "Admin"),
                new Student("4", "Diana", "IT")
        );

        //initial selected list
        List<Student> destinationList = new ArrayList<>();
        destinationList.add(new Student("5", "Jessica", "Sale"));

        listModel = new DualListModel<>(new ArrayList<>(sourceList), destinationList);
    }

    public DualListModel<Student> getListModel() {
        return listModel;
    }

    public void setListModel(DualListModel<Student> listModel) {
        this.listModel = listModel;
    }
}