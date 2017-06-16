import com.chapa.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by chapa on 17-6-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:aop.xml"})
public class ProxyTest {

    @Autowired
    TestServiceWithoutInterface  testServiceWithoutInterface;

    @Autowired
    ITestService testService;

    @Autowired
    SingletonBean singletonBean;

    @Test
    public void  isProxyed() {
        System.out.println("###############################################");
        System.out.println("是否是代理对象:"+AopUtils.isAopProxy(testService));
        System.out.println("是否是CGLIB方式的代理对象:"+AopUtils.isCglibProxy(testService));
        System.out.println("是否是JDK动态代理方式的代理对象:"+AopUtils.isJdkDynamicProxy(testService));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    /**
     * 不通过,spring 容器加载的类 无法实现aop
     */
    @Test
    public void testWithOutContianer(){
        System.out.println("###############################################");
        testService=new TestServiceImpl();
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
    public void testCallSelf(){
        System.out.println("###############################################");
        testService.hcur();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    @Test
    public void testProxyWithClass(){
        System.out.println("###############################################");
        testServiceWithoutInterface.hhh();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Test
    public void testDeclareParents(){
        System.out.println("###############################################");
        ((IIntroduce)testService).sss();
        testService.hhh();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Test
    public void testScopeProxy() throws Exception {
        System.out.println("###############################################");
        singletonBean.getTestService().hprintTime();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    @Test
    public void testScopeProxy2() throws Exception {
        System.out.println("###############################################");
        singletonBean.getTestService().hprintTime();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

}
