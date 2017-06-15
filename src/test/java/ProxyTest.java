import com.chapa.service.ITestService;
import com.chapa.service.TestServiceImpl;
import com.chapa.service.TestServiceWithoutInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    /**
     * 不通过,spring 容器加载的类 无法实现aop
     */
    @Test
    public void testWithOutContianer(){
        System.out.println("###############################################");
        testService=new TestServiceImpl();
        testService.hhh();
    }

    /**
     * 通过,spring 容器加载的类 正常实现aop
     */
    @Test
    public void testWithContianer(){
        System.out.println("###############################################");
        testService.hhh();
    }

    @Test
    public void testProxyWithClass(){
        System.out.println("###############################################");
        testServiceWithoutInterface.hhh();
    }
}
