import org.junit.Test;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chapa on 17-6-6.
 */
public class AspectJTest {
    @Test
    public void test(){
        exec("export JAVA_HOME=/usr/local/jdk8;mvn -f pom-ajc.xml clean test -Dtest=AnnotationAopTest");
    }

    public static List<String> exec(String cmd) {
        List<String> list = new ArrayList<String>(150);
        try {
            String[] cmdA = { "/bin/sh", "-c", cmd };
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Exception:"+e.getMessage());
        }
        return list;
    }
}
