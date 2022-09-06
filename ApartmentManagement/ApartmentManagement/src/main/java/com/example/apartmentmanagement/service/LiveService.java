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
        System.out.println(live);
        int ret = 0;
        Live live1 = new Live();
        int canInsert = 0;//0 可以添加 1 不可添加
        //选择床位数大于总的床位数也不可以添加
        int total = dormService.findTotal(live.getDormId());
        if(live.getBedId()>total){
            canInsert++;
        }
        //床位有人就不可以添加
        live1.setDormId(live.getDormId());
        live1.setBedId(live.getBedId());
        List<Live> lives = liveMapper.selectLive(live1);
        if(lives.size()>0){ //改床位有人 不可添加
            System.out.println("enter1");
            canInsert++;
        }
        //用户已经有了也不能添加
        Live live2 = new Live();
        live2.setStuId(live.getStuId());
        List<Live> lives1 = liveMapper.selectLive(live2);
        if(lives1.size()>0){//用户已经住了宿舍，不能添加
            System.out.println("enter2");
            canInsert++;
        }
        if(canInsert == 0){
            ret = liveMapper.insertLive(live);
            dormService.reduceRemainder(live.getDormId());
        }
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
        int canUpdate = 0;//0 可以update 1 不可update
        //选择床位数大于总的床位数也不可以添加
        int total = dormService.findTotal(live.getDormId());
        if(live.getBedId()>total){
            canUpdate++;
        }
        Live live1 = new Live();
        //床位有人就不可以添加
        live1.setDormId(live.getDormId());
        live1.setBedId(live.getBedId());
        List<Live> lives = liveMapper.selectLive(live1);
        if(lives.size()>0){ //改床位有人 不可添加
            System.out.println("enter1");
            canUpdate++;
        }
        if(canUpdate ==0){
            ret = liveMapper.updateLive(live);
        }
        return ret;
    }
}
