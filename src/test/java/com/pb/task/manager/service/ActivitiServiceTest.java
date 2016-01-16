package com.pb.task.manager.service;

import com.pb.task.manager.model.FormData;
import com.pb.task.manager.model.TaskData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stas on 16.01.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:test-context.xml"} )
public class ActivitiServiceTest {

    @Autowired
    private ActivitiService service;

    @Test
    public void testSearch() {
        createTask();

        List<TaskData> taskDataList = service.findAll();
        System.out.println("taskDataList.size(): " + taskDataList.size());
    }

    private void createTask() {
        FormData formData = new FormData();
        formData.setMap(new HashMap<String, String>(){{
            put("name", "someName");
            put("description", "someDescription");
            put("author", "someAuthor");
        }});
        service.submitForm(formData);
    }


//    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
//    public ModelAndView showTaskData(@PathVariable("id") String id) {
//        ModelAndView mav = new ModelAndView("process/details");
//        mav.addObject("taskData", service.getTaskData(id));
//        mav.addObject("isWritable", service.checkUserAccess(id));
//        return mav;
//    }
}
