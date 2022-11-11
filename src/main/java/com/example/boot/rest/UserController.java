package com.example.boot.rest;

import com.example.boot.entity.Result;
import com.example.boot.entity.TbUser;
import com.example.boot.service.UserService;
import com.example.boot.untils.ExcelData;
import com.example.boot.untils.ExportExcelUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller
 *
 * @author ltk
 * @date 2019/11/24
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  /**
   * 查询所有用户
   *
   * @return List<TbUser> @MethodName allUserList
   */
  @GetMapping("/allUserList")
  public List<TbUser> allUserList() {
    return userService.queryAllUser();
  }

  /**
   * 根据用户名查询用户
   *
   * @return List<TbUser> @MethodName queryByUserName
   */
  @PostMapping("/queryByUserName")
  public List<TbUser> queryUserByName(@RequestBody @Valid TbUser user) {
    return userService.queryUserByName(user.getUserName());
  }

  /**
   * 修改用户
   *
   * @return String @MethodName update
   */
  @PostMapping("/update")
  public Result update(@RequestBody @Valid TbUser user) {
    return userService.update(user);
  }

  /** excel download @MethodName excel */
  @GetMapping(value = "/excel")
  public void excel(HttpServletResponse response) throws Exception {
    ExcelData data = new ExcelData();
    data.setName("hello");
    List<String> titles = new ArrayList();
    titles.add("姓名");
    titles.add("性别");
    titles.add("年龄");
    titles.add("手机号");
    data.setTitles(titles);
    List<List<Object>> rows = new ArrayList();
    List<Object> row1 = new ArrayList();
    row1.add("张三");
    row1.add("男");
    row1.add("23");
    row1.add("18612341234");
    List<Object> row2 = new ArrayList();
    row2.add("李四");
    row2.add("女");
    row2.add("24");
    row2.add("15312341234");
    rows.add(row1);
    rows.add(row2);
    data.setRows(rows);
    ExportExcelUtils.exportExcel(response, "联系人表.xlsx", data);
  }

  public String emailTemplate() throws Exception {
    // 创建配置对象
    Configuration configuration = new Configuration(Configuration.getVersion());
    // 设置默认生成文件编码
    configuration.setDefaultEncoding("utf-8");
    // 设置模板路径
    configuration.setClassForTemplateLoading(this.getClass(), "/templates");
    String htmlStr;
    Template template = configuration.getTemplate("/test.html");
    Map map = new HashMap(3);
    map.put("title", "标题");
    htmlStr = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
    return htmlStr;
  }

  public static void main(String[] args) throws Exception {
    UserController userController = new UserController();
    System.out.println(userController.emailTemplate());
  }
}
