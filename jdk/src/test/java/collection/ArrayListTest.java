package collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList是基于数组实现的列表
 */
@Slf4j
public class ArrayListTest {

    private List list;

    @Before
    public void before() {
        list = new ArrayList();
    }

    @Test
    public void testAdd() {
        list.add("Hello");
    }

    public void testRemove(){
        // 通过将数组元素移位来覆盖要删除的那个值
        list.remove(0);
    }

}
