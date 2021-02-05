package collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;

import java.util.List;

@Slf4j
public class ArrayListTest {

    private List list;

    @BeforeClass
    public static void beforeClass(){
        log.info("beforeClass...");
    }

    @Before
    public void before() {
        log.info("before...");
    }

    @After
    public void after(){
        log.info("after...");
    }

    @AfterClass
    public static void afterClass(){
        log.info("afterClass...");
    }

    @Test
    public void testAdd() {
        log.info("testAdd");
    }

}
