package com.pb.task.manager.service;

import com.pb.task.manager.model.FormData;
import com.pb.task.manager.model.TaskData;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * Created by stas on 16.01.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:test-context.xml"} )
public class ActivitiServiceTest {

    @Autowired
    private ActivitiService service;

    @Test
    public void testMain() {
        String taskId = createOneTask();

        List<TaskData> taskDataList = service.findAll();
        System.out.println("taskId: " + taskId);
        System.out.println("taskDataList.size(): " + taskDataList.size());
    }

    @Test
    public void testShowTask() {
        String taskId = createOneTask();
        TaskData taskData = service.getTaskData(taskId);
        System.out.println(String.format("Id: %s, activitiDynamicId: %s, params: %s", taskData.getId(), taskData.getActivitiDynamicId(), taskData.getParams()));

    }

    private String createOneTask() {
        FormData formData = new FormData();
        formData.setMap(new HashMap<String, String>(){{
            put("name", "someName");
            put("description", "someDescription");
            put("author", "someAuthor");
        }});
        return service.submitForm(formData);
    }


//    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
//    public ModelAndView showTaskData(@PathVariable("id") String id) {
//        ModelAndView mav = new ModelAndView("process/details");
//        mav.addObject("taskData", service.getTaskData(id));
//        mav.addObject("isWritable", service.checkUserAccess(id));
//        return mav;
//    }
}
