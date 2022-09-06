package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.dao.LiveMapper;
import com.example.apartmentmanagement.dao.StudentMapper;
import com.example.apartmentmanagement.entity.Live;
import com.example.apartmentmanagement.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LiveService {

    @Autowired
    private LiveMapper liveMapper;

    @Autowired
    private StudentMapper studentMapper;

    //通过dormId得到用户及其床位号
    public List<Map<String, Object>> getStudentBedIdInDorm(String dormId){

        //获得live数组
        List<Live> lives = liveMapper.selectLiveByDorm(dormId);

        List<Map<String, Object>> ret = new ArrayList<>();

        if(lives.size() != 0){
            for (Live life : lives) {
                Map<String, Object> mp = new HashMap<>();
                String stuId = life.getStuId();
                Student student = studentMapper.selectById(stuId);
                System.out.println("student:" + student);
                System.out.println("======");
                System.out.println("bedId" + life.getBedId());
                mp.put("student", student);
                mp.put("bedId", life.getBedId());
                ret.add(mp);
            }
        }
        return ret;
    }

    public int insertLive(Live live){
        int ret = 0;
        Live live1 = new Live();
        live1.setDormId(live.getDormId());
        live1.setBedId(live.getBedId());
        List<Live> lives = liveMapper.selectLive(live1);
        System.out.println(lives);
        if(lives.size() == 0){
            ret = liveMapper.insertLive(live);
        }
        return ret;
    }

    public int deleteLive(String[] stuId){
        int ret = 0;
        for (String s : stuId) {
             ret += liveMapper.deleteLive(s);
        }
        return ret;
    }

    public int updateLive(Live live){
        int ret = 0;
        ret = liveMapper.updateLive(live);
        return ret;
    }
}
