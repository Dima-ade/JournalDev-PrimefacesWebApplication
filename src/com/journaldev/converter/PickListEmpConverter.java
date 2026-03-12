package com.journaldev.converter;

import com.journaldev.data.Student;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "stuConverter")
public class PickListEmpConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent comp, String value) {
        DualListModel<Student> model = (DualListModel<Student>) ((PickList) comp).getValue();
        for (Student student : model.getSource()) {
            if (student.getId().equals(value)) {
                return student;
            }
        }
        for (Student student : model.getTarget()) {
            if (student.getId().equals(value)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent comp, Object value) {
        return ((Student) value).getId();
    }
}