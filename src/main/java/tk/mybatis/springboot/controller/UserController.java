/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package tk.mybatis.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.User;
import tk.mybatis.springboot.service.UserService;

import java.util.List;

/**
 * @description: TODO
 * @className: UserController
 * @date 2020/3/26 10:14
 * @author 0914
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public PageInfo<User> getAll(User user) {
        if (user.getPage() != null && user.getRows() != null) {
            PageHelper.startPage(user.getPage(), user.getRows());
        }
        List<User> userList = userService.select(user);
        return new PageInfo<User>(userList);
    }

    @RequestMapping(value = "/add")
    public User add() {
        return new User();
    }

    @RequestMapping(value = "/view/{id}")
    public User view(@PathVariable String id) {
        ModelAndView result = new ModelAndView();
        User user = userService.selectByKey(id);
        return user;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable String id) {
        ModelMap result = new ModelMap();
        userService.delete(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(@RequestBody User user) {
        ModelMap result = new ModelMap();
        String msg = user.getId() == null ? "新增成功!" : "更新成功!";
        userService.save(user);
        result.put("user", user);
        result.put("msg", msg);
        return result;
    }
}
