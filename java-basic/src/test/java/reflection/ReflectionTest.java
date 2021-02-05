package reflection;

import org.junit.Test;
import model.Animal;
import model.Dog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 反射的测试用例
 *
 * @author BB
 * @create 2020/12/21
 */
public class ReflectionTest {

    @Test
    public void testFields() throws Exception {
        Animal dog = new Dog();
        // 获取所有public的field，包含父类的
        Field[] publicFields = dog.getClass().getFields();
        // Animal类中定义了一个public的field(id),Dog类中定义了一个public的field(color)
        assert publicFields.length == 2;

        // 获取当前类所有的field,包括：private, protected ,default, public, 但是不包含父类的field
        Field[] declaredFields = dog.getClass().getDeclaredFields();
        // Dog类中定义了一个public的field(color)，还定义了一个private的field(master)
        assert declaredFields.length == 2;

        // 根据传入的fieldName来获取某一个public的field, 包含父类的
        Field colorField = dog.getClass().getField("color");
        assert !Objects.isNull(colorField);
        Field idField = dog.getClass().getField("id");
        assert !Objects.isNull(idField);
        // 抛出NoSuchFieldException异常， 原因是age属性是从父类继承的，但是不是它不是public修饰的
//        Field ageField = dog.getClass().getField("age");

        // 根据传入的fieldName来获取当前类的某一个field, 但是不包含父类的
        Field masterField = dog.getClass().getDeclaredField("master");
        assert !Objects.isNull(masterField);
        // 抛出NoSuchFieldException异常，原因是id属性是从父类继承的
//        Field idField2 = dog.getClass().getDeclaredField("id");

    }

    @Test
    public void testMethods() throws Exception {
        Animal dog = new Dog();
        dog.setName("小狗狗");
        dog.setAge(1);

        // 获取所有public的methods，包含父类的, 以及父类的父类，一直到Object
        Method[] publicMethods = dog.getClass().getMethods();
        System.out.println("public的方法包含有：");
        Arrays.stream(publicMethods).forEach(m -> {
            System.out.println(m.getName());
        });

        // 获取当前类所有的method,包括：private, protected ,default, public, 但是不包含父类的method
        Method[] declaredMethods = dog.getClass().getDeclaredMethods();
        System.out.println("declared的方法包含有：");
        Arrays.stream(declaredMethods).forEach(m -> {
            System.out.println(m.getName());
        });

        System.out.println("根据methodName获取publicMethod实例：");
        Method publicMethod = dog.getClass().getMethod("getName");
        System.out.println(publicMethod.getName());

        System.out.println("publicMethod执行");
        System.out.println(publicMethod.invoke(dog));

        System.out.println("根据methodName获取declaredMethod实例：");
        // 抛出异常
//        Method declaredMethod = dog.getClass().getDeclaredMethod("getAge");
    }
}