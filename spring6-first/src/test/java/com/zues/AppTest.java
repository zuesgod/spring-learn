package com.zues;

import com.zues.config.UserFactoryBean;
import com.zues.entity.Student;
import com.zues.entity.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    private Logger log = LoggerFactory.getLogger(AppTest.class);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    /**
     * 测试spring的一个bean管理
     * 1.根据bean的ID获取
     * 2.根据类型获取
     */
    public void testHelloWorld() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans.xml");
        //根据ID获取
//        Student student1 = (HelloWorld) applicationContext.getBean("student1");
        //根据类型获取
        Student student1 = applicationContext.getBean(Student.class);
        student1.sayHello();
        log.info("执行成功！");
    }


    /**
     * 测试DI依赖注入
     */
    public void testDI() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans.xml");
        //测试Setter方法
        Student student1 = applicationContext.getBean("student1", Student.class);
        System.out.println("student1.getName() = " + student1.getName());
        System.out.println("student1.getId() = " + student1.getId());
        System.out.println("student1.getClazz() = " + student1.getClazz());
        for (String hobby : student1.getHobbies()) {
            System.out.println("hobby = " + hobby);
        }
        log.info("helloWorld1执行成功！");

        //测试有参构造
//        Student student2 = applicationContext.getBean("student2", Student.class);
//        System.out.println("student2.getName() = " + student2.getName());
//        System.out.println("student2.getId() = " + student2.getId());
//        for (String hobby : student2.getHobbies()) {
//            System.out.println("hobby = " + hobby);
//        }
//        log.info("helloWorld2执行成功！");
    }

    /**
     * 测试读取数据源文件
     */
    public void testDataSource() {
        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans.xml");
            DataSource dataSource = applicationContext.getBean(DataSource.class);
            Connection connection = dataSource.getConnection();
            log.debug("connection = " + connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试bean的生命周期
     */
    public void testLifeCycle() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-userbean.xml");
        User user = applicationContext.getBean(User.class);
        System.out.println("user.getId() = " + user.getId());
        log.debug("生命周期：4、通过IOC容器获取bean并使用");
        applicationContext.close();
    }

    /**
     * 测试UserFactoryBean
     */
    public void testUserFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-userbean.xml");
        User userFactoryBean = (User) applicationContext.getBean("userFactoryBean");
        System.out.println("userFactoryBean" + userFactoryBean);
    }
}
