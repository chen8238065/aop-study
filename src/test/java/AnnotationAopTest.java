import com.chapa.annotation.service.ITestService;
import com.chapa.annotation.service.TestServiceImpl;
import com.chapa.annotation.service.TestServiceWithoutInterface;
import com.chapa.annotation.service.IIntroduce;
import org.aspectj.lang.annotation.DeclareParents;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by chapa on 17-6-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:aop-annotation.xml"})
public class AnnotationAopTest {

    /**
    *（这个注解属于J2EE的），默认安照名称进行装配，名称可以通过name属性进行指定，
     *如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，如果注解写在setter方法上默认取属性名进行装配。
     * 当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
     */
    @Resource
    TestServiceWithoutInterface serviceWithoutInterface;

    /**
     * 默认按类型装配（这个注解是属spring的），默认情况下必须要求依赖对象必须存在，如果要允许null 值，可以设置它的required属性为false，如：@Autowired(required=false) ，如果我们想使用名称装配可以结合 @Qualifie
     * 按类型 可能 不 唯一, 如果不唯一，在结果集里，寻找name为 testService 的bean。因为bean的name有唯一性 找不到 就 报错了
     */
    @Autowired
    ITestService testService;

    @Test
    public void testSpringConfig(){
        System.out.println("###############################################");
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:aop-annotation.xml");
        System.out.println(ctx.getBean("testServiceImpl"));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    /**
     * 不通过,spring 容器加载的类 无法实现aop
     */
    @Test
    public void testWithOutContianer(){
        System.out.println("###############################################");
        testService =new TestServiceImpl();
        testService.hhh();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    /**
     * 通过,spring 容器加载的类 正常实现aop
     */
    @Test
    public void testWithContianer(){
        System.out.println("###############################################");
        testService.hhh();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Test
    public void testProxyWithClass(){
        System.out.println("###############################################");
        serviceWithoutInterface.hhh();
    }
    @Test
    public void testDeclareParents(){
        System.out.println("###############################################");
        ((IIntroduce)testService).sss();
        testService.hhh();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Test
    public void testArgs(){
        System.out.println("###############################################");
        testService.say("chapa","nice to meet you!");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
