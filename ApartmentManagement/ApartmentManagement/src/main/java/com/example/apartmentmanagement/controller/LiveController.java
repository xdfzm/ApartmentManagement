package com.example.apartmentmanagement.controller;

import com.example.apartmentmanagement.entity.Live;
import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.service.LiveService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/live")
public class LiveController {

    @Autowired
    private LiveService liveService;

    @Autowired
    private Gson gson = new Gson();

//    @GetMapping("/get")
//    public String getStudentBedIdByDorm(String dormId){
//        System.out.println(dormId);
//
//
//        ResultVo resultVo = new ResultVo<>();
//        List<Map<String, Object>> studentBedIdInDorm = liveService.getStudentBedIdInDorm(dormId);
//
//        if(studentBedIdInDorm.size() != 0){
//            resultVo.setCode(200);
//            resultVo.setMsg("查询成功");
//            resultVo.setData(studentBedIdInDorm);
//        }else{
//            resultVo.setCode(500);
//            resultVo.setMsg("没有你想要的结果");
//        }
//        return gson.toJson(resultVo);
//    }

    @GetMapping("/get")
    public String getStudentBedIdByDorm(String dormId){
        System.out.println(dormId);


        ResultVo resultVo = new ResultVo<>();
        List<Map<String, Object>> studentBedIdInDorm = liveService.getStudentBedIdInDorm(dormId);

        if(studentBedIdInDorm.size() != 0){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(studentBedIdInDorm);
        }else{
            resultVo.setCode(500);
            resultVo.setMsg("没有你想要的结果");
        }
        return gson.toJson(resultVo);
    }
    @PostMapping("/add")
    public String addLive(@RequestBody Live live){
        ResultVo resultVo = new ResultVo<>();
        int isInsert = liveService.insertLive(live);

        if(isInsert != 0){
            resultVo.setCode(200);
            resultVo.setMsg("添加成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("添加失败");
        }
        return gson.toJson(resultVo);
    }

    @PostMapping("/delete")
    public String deleteLive(@RequestBody String[] stuId){
        int isDelete = 0;
        isDelete = liveService.deleteLive(stuId);

        ResultVo resultVo = new ResultVo<>();

        if(isDelete >= stuId.length){
            resultVo.setCode(200);
            resultVo.setMsg("删除成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("删除失败");
        }
        return gson.toJson(resultVo);
    }

    @PostMapping("/update")
    public String updateStudent(@RequestBody Live live){
        ResultVo resultVo = new ResultVo<>();
        int isUpdate = liveService.updateLive(live);

        if(isUpdate != 0){
            resultVo.setCode(200);
            resultVo.setMsg("修改成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("修改失败");
        }
        return gson.toJson(resultVo);
    }
}

