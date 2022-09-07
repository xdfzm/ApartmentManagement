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

    @Autowired
    private DormService dormService;

    //通过dormId得到用户及其床位号
    public List<Map<String, Object>> getStudentBedIdInDorm(String dormId){

        //获得live数组
        List<Live> lives = liveMapper.selectLiveByDorm(dormId);

        List<Map<String, Object>> ret = new ArrayList<>();

        //获取宿舍总数量
        int total = dormService.findTotal(dormId);

        //使用list来判断某个床位是否被占用
        List<Integer> list = new ArrayList<>();
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
                list.add(life.getBedId());
                ret.add(mp);
            }
        }
        //增加那些空缺的床位
        for (int i = 1; i <= total; i++) {
            if(!list.contains(i)){
                Map<String, Object> mp = new HashMap<>();
                mp.put("student", null);
                mp.put("bedId", i);
                ret.add(mp);
            }
        }
        return ret;
    }

    public int insertLive(Live live){
        int ret = 0;
        ret = liveMapper.insertLive(live);
        dormService.reduceRemainder(live.getDormId());
        return ret;
    }

    public int deleteLive(String[] stuId){
        int ret = 0;
        for (String s : stuId) {

            //将这个用户住的宿舍的remainder+1
            Live live = new Live();
            live.setStuId(s);
            List<Live> lives = liveMapper.selectLive(live);
            if(lives.size()>0){
                String dormId = lives.get(0).getDormId();
                dormService.addRemainder(dormId);
            }
            //删除
            ret += liveMapper.deleteLive(s);
        }
        return ret;
    }

    public int updateLive(Live live){
        int ret = 0;

        //原来寝室remainder+1
        String s = liveMapper.selectDormIdByStuId(live.getStuId());
        dormService.addRemainder(s);

        //后来寝室remainder-1
        dormService.reduceRemainder(live.getDormId());
        ret = liveMapper.updateLive(live);
        return ret;
    }

    public List<Live> selectLive(Live live){
        return liveMapper.selectLive(live);
    }

    public String selectDormIdByStuId(String stuId){
        return liveMapper.selectDormIdByStuId(stuId);
    }
}
