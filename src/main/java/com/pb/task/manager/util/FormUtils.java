package com.pb.task.manager.util;

import com.pb.task.manager.model.FormFieldData;
import org.activiti.engine.form.FormProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by stas on 21.01.16.
 */
@Component
@Scope("singleton")
public class FormUtils {

    public static List<FormFieldData> convertTo(List<FormProperty> formPropertyList){
        List<FormFieldData> formFieldDataList = new ArrayList<>();
        for(FormProperty formProperty: formPropertyList){
            FormFieldData formFieldData = new FormFieldData();
            formFieldData.setId(formProperty.getId());
            formFieldData.setName(formProperty.getName());
            formFieldData.setRequired(formProperty.isRequired());
            formFieldData.setReadable(formProperty.isReadable());
            formFieldData.setWritable(formProperty.isWritable());
            formFieldData.setType(formProperty.getType().getName());
            formFieldData.setValue(formProperty.getValue());
            if("enum".equalsIgnoreCase(formFieldData.getType())){
                formFieldData.setSelectValues(new ArrayList<>(((Map<String,String>)formProperty.getType().getInformation("values")).values()));
            }
            formFieldDataList.add(formFieldData);
        }
        return formFieldDataList;
    }
}
