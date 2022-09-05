package com.example.apartmentmanagement.service.impl;

import com.example.apartmentmanagement.entity.LoginUser;
import com.example.apartmentmanagement.entity.ResponseResult;
import com.example.apartmentmanagement.entity.User;
import com.example.apartmentmanagement.service.LoginService;
import com.example.apartmentmanagement.utils.JwtUtil;
import com.example.apartmentmanagement.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //authentication进行用户认证  am会调用userdetails
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证没通过给出提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登入失败");
        }
        //通过 生成jwt  jwt存入ResponseResult返回
        LoginUser loginUser =(LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //把完整用户信息存入redis
        redisCache.setCacheObject("login:"+userid, loginUser);

        return new ResponseResult(200,"登入成功", map);
    }

    @Override
    public ResponseResult logout() {
        //获取sercuritycontextholder用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        long userid = loginUser.getUser().getUserId();

        //删除redis中的值

        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"注销成功");
    }
}
