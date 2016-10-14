import com.wix.invoke.MethodInvocation;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by rotemm on 13/10/2016.
 */
public class JsonToInvocationTest {

    @Test
    public void fromJsonTargetClassStaticMethodNoParams() {
        assertThat(jsonToInvocation("targetClassStaticMethodNoParams.json")).isEqualTo(System.lineSeparator());
    }

    @Test
    public void fromJsonTargetClassStaticMethodOneParam() {
        assertThat(jsonToInvocation("targetClassStaticMethodOneParam.json")).isEqualTo("1.0");
    }

    @Test
    public void fromJsonTargetInvocationMethodOfClassStaticMethodOneParam() {
        assertThat(jsonToInvocation("targetInvocationMethodOfClassStaticMethodOneParam.json")).isEqualTo(3);
    }

    public Object jsonToInvocation(String filePath) {
        String jsonData = TestUtils.jsonFileToString(filePath);
        return MethodInvocation.invoke(jsonData);
    }
}
