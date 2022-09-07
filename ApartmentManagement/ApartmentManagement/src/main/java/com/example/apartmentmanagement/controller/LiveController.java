package com.example.apartmentmanagement.controller;

import com.example.apartmentmanagement.entity.Live;
import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.service.DormService;
import com.example.apartmentmanagement.service.LiveService;
import com.example.apartmentmanagement.service.StudentService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/live")
public class LiveController {

    @Autowired
    private LiveService liveService;

    @Autowired
    private Gson gson = new Gson();

    @Autowired
    private DormService dormService;

    @Autowired
    private StudentService studentService;

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
        int canAdd = 0;// 0可以添加 1不可以添加
        //不可以加入异性宿舍
        String dormType = dormService.findDormType(live.getDormId());
        System.out.println("dormType"+dormType);
        String studentSex = studentService.getStudentSex(live.getStuId());
        System.out.println("studentSex"+studentSex);
        System.out.println("="+dormType==studentSex);
        System.out.println("equals"+dormType.equals(studentSex));
        if(!dormType.equals(studentSex)){
            canAdd++;
            resultVo.setCode(500);
            resultVo.setMsg("无法选择异性宿舍");
            return gson.toJson(resultVo);
        }
        //判断有无该用户
        Student student = studentService.findStudentById(live.getStuId());
        if(Objects.isNull(student)){
            canAdd++;
            resultVo.setCode(500);
            resultVo.setMsg("该学生不存在");
            return gson.toJson(resultVo);
        }
        //选择床位数大于总的床位数也不可以添加
        int total = dormService.findTotal(live.getDormId());
        if(live.getBedId()>total || live.getBedId()<=0){
            canAdd++;
            resultVo.setCode(500);
            resultVo.setMsg("该宿舍没有该床号");
            return gson.toJson(resultVo);
        }
        //判断用户是否已住宿
        //用户已经有了也不能添加
        Live liveStudent = new Live();
        liveStudent.setStuId(live.getStuId());
        List<Live> livesStudent = liveService.selectLive(liveStudent);
        if(livesStudent.size()>0){//用户已经住了宿舍，不能添加
            canAdd++;
            resultVo.setCode(500);
            resultVo.setMsg("该学生已入住");
            return gson.toJson(resultVo);
        }
        //判断改床位是否有人
        //床位有人就不可以添加
        Live liveDormAndBed = new Live();
        liveDormAndBed.setDormId(live.getDormId());
        liveDormAndBed.setBedId(live.getBedId());
        List<Live> livesDormAndBed = liveService.selectLive(liveDormAndBed);
        if(livesDormAndBed.size()>0){ //改床位有人 不可添加
            canAdd++;
            resultVo.setCode(500);
            resultVo.setMsg("该床位有人");
            return gson.toJson(resultVo);
        }

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

        if(isDelete == stuId.length){
            resultVo.setCode(200);
            resultVo.setMsg("删除成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("未能全部删除");
        }
        return gson.toJson(resultVo);
    }

    @PostMapping("/update")
    public String updateStudent(@RequestBody Live live){
        ResultVo resultVo = new ResultVo<>();
        int canUpdate = 0;//0 可以update 1 不可update
        //不可以加入异性宿舍
        String dormType = dormService.findDormType(live.getDormId());
        String studentSex = studentService.getStudentSex(live.getStuId());
        if(!dormType.equals(studentSex)){
            canUpdate++;
            resultVo.setCode(500);
            resultVo.setMsg("无法选择异性宿舍");
            return gson.toJson(resultVo);
        }
        //用户不存在不可以修改
        Student student = studentService.findStudentById(live.getStuId());
        if(Objects.isNull(student)){
            canUpdate++;
            resultVo.setCode(500);
            resultVo.setMsg("没有该学生");
            return gson.toJson(resultVo);
        }
        //选择床位数大于总的床位数也不可以修改
        int total = dormService.findTotal(live.getDormId());
        if(live.getBedId()>total || live.getBedId()<=0){
            canUpdate++;
            resultVo.setCode(500);
            resultVo.setMsg("没有该床位");
            return gson.toJson(resultVo);
        }
        //床位有人
        Live liveDormAndBed = new Live();
        liveDormAndBed.setDormId(live.getDormId());
        liveDormAndBed.setBedId(live.getBedId());
        List<Live> livesDormAndBed = liveService.selectLive(liveDormAndBed);
        if(livesDormAndBed.size()>0){ //改床位有人 不可添加
            canUpdate++;
            resultVo.setCode(500);
            resultVo.setMsg("该床位有人");
            return gson.toJson(resultVo);
        }

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

