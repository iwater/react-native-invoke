import com.wix.invoke.MethodInvocation;
import com.wix.invoke.exceptions.EmptyInvocationInstructionException;
import com.wix.invoke.types.Invocation;
import com.wix.invoke.types.Target;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by rotemm on 10/10/2016.
 */
public class MethodInvocationTest {
    @Test(expected = EmptyInvocationInstructionException.class)
    public void invokeEmptyInvocation() {
        assertThat(MethodInvocation.invoke(new Invocation(null, null, null))).isNull();
    }

    @Test
    public void invokeStaticStringValueOfInteger() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", 0);
        Object retVal = MethodInvocation.invoke(invocation);
        assertThat(retVal).isEqualTo("0");
    }

    @Test
    public void invokeStaticStringValueOfFloat() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", 1.0f);
        Object retVal = MethodInvocation.invoke(invocation);
        assertThat(retVal).isEqualTo("1.0");
    }

    @Test
    public void invokeStaticStringValeOfBoolean() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", true);
        Object retVal = MethodInvocation.invoke(invocation);
        assertThat(retVal).isEqualTo("true");
    }

    @Test
    public void invokeStaticStringValueOfChar() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", 'c');
        Object retVal = MethodInvocation.invoke(invocation);
        assertThat(retVal).isEqualTo("c");
    }

    @Test
    public void invokeStaticMathMin1_2() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.lang.Math"), "min", 1, 2);
        Object retVal = MethodInvocation.invoke(invocation);
        assertThat(retVal).isEqualTo(1);
    }

    @Test
    public void invokeStaticArraysBinarySearchFindExisting() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.util.Arrays"), "binarySearch", new Object[]{1, 2, 3, 4, 5}, 3);
        Object retVal = MethodInvocation.invoke(invocation);
        assertThat(retVal).isEqualTo(2);
    }

    @Test
    public void invokeStaticArraysBinarySearchFindInRange() {
        Invocation invocation = new Invocation(new Target(Target.Type.Class, "java.util.Arrays"), "binarySearch", new Object[]{1, 2, 3, 4, 5}, 0, 1, 3);
        Object retVal = MethodInvocation.invoke(invocation);
        assertThat(retVal).isEqualTo(-2);
    }

    @Test
    public void invokeMethodOnReturnValueOfStaticInvocation() {
        Invocation innerInvocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", 'c');
        Invocation outerInvocation = new Invocation(new Target(Target.Type.Invocation, innerInvocation), "length");
        Object outerRetVal = MethodInvocation.invoke(outerInvocation);
        assertThat(outerRetVal).isEqualTo(1);
    }


    @Test
    public void invokeMethodOnReturnValueOfStaticInvocationTwoTimes() {
        Invocation innerInvocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", 'c');
        Invocation intermediateInvocation = new Invocation(new Target(Target.Type.Invocation, innerInvocation), "concat", "c");
        Invocation outerInvocation = new Invocation(new Target(Target.Type.Invocation, intermediateInvocation), "length");
        Object outerRetVal = MethodInvocation.invoke(outerInvocation);
        assertThat(outerRetVal).isEqualTo(2);
    }

    @Test
    public void invokeMethodOnReturnValueOfStaticInvocationThreeTimes() {
        Invocation innerInvocation = new Invocation(new Target(Target.Type.Class, "java.lang.String"), "valueOf", 'c');
        Invocation intermediateInvocation1 = new Invocation(new Target(Target.Type.Invocation, innerInvocation), "concat", "c");
        Invocation intermediateInvocation2 = new Invocation(new Target(Target.Type.Invocation, intermediateInvocation1), "concat", "b");
        Invocation outerInvocation = new Invocation(new Target(Target.Type.Invocation, intermediateInvocation2), "length");
        Object outerRetVal = MethodInvocation.invoke(outerInvocation);
        assertThat(outerRetVal).isEqualTo(3);
    }

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