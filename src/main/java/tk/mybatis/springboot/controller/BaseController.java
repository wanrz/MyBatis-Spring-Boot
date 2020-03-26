package tk.mybatis.springboot.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.service.IService;

import java.util.List;
/**
 * @description: TODO
 * @className: BaseController
 * @date 2020/3/26 11:02
 * @author 0914
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class BaseController<T> {

    protected abstract IService<T> getService();

    @RequestMapping
    public PageInfo<T> getAll(T t) {
        List<T> list = this.getService().select(t);
        return new PageInfo<T>(list);
    }

    @RequestMapping(value = "/add")
    public ModelMap add(T t) {
        ModelMap result = new ModelMap();
        this.getService().save(t);
        result.put("msg", "新增成功!");
        return result;
    }

    @RequestMapping(value = "/view/{id}")
    public T view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView();
        T t = this.getService().selectByKey(id);
        return t;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        this.getService().delete(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelMap update(@RequestBody T t) {
        ModelMap result = new ModelMap();
        this.getService().updateNotNull(t);
        result.put("msg", "更新成功!");
        return result;
    }

}
