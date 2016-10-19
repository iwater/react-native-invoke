import com.wix.invoke.parser.JsonParser;
import com.wix.invoke.types.Invocation;
import com.wix.invoke.types.Target;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by rotemm on 13/10/2016.
 */
public class JsonParserTest {

    @Test
    public void targetClassStaticMethodNoParams() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.lang.System"), "lineSeparator");
        assertThat(parse("targetClassStaticMethodNoParams.json")).isEqualToComparingFieldByFieldRecursively(invocation);
    }

    @Test
    public void parseTargetClassStaticMethodOneParam() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", 1.0f);
        assertThat(parse("targetClassStaticMethodOneParam.json")).isEqualToComparingFieldByFieldRecursively(invocation);
    }

    @Test
    public void targetInvocationMethodOfClassStaticMethodOneParam() {
        Invocation innerInvocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", 1.0f);
        Invocation outerInvocation = new Invocation(new Target(Target.Type.Invocation, innerInvocation), "length");
        assertThat(parse("targetInvocationMethodOfClassStaticMethodOneParam.json")).isEqualToComparingFieldByFieldRecursively(outerInvocation);
    }

    public Invocation parse(String filePath) {
        String jsonData = TestUtils.jsonFileToString(filePath);
        return JsonParser.parse(jsonData, Invocation.class);
    }
}
