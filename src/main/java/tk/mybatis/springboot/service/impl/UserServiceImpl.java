package tk.mybatis.springboot.service.impl;

import org.springframework.stereotype.Service;
import tk.mybatis.springboot.model.Country;
import tk.mybatis.springboot.model.User;
import tk.mybatis.springboot.model.UserInfo;
import tk.mybatis.springboot.service.IService;
import tk.mybatis.springboot.service.UserService;
@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {
}
