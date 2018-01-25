package com.kasakad.fileio.kasakaidfileio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {KasakaidfileioApplication.class, TestConfig.class})
@ActiveProfiles("test")
@Slf4j
public abstract class AbstractBaseTest {

    @Autowired
    protected ApplicationContext context;

    protected ApplicationContext testConfigApplication;

    // 実行順序がよくわからないのでサブクラスからコールする
//    @Before
    public void setUp() {
//        testConfigApplication = new AnnotationConfigApplicationContext(TestConfig.class);
        // gradlew で実行すると NoSuchBeanFoundException
//        myResource = testConfigApplication.getBean(MyResource.class);
    }

    // アノテーションを @TestComponent -> @Component に変えたので Injection ができる
    // @TestComponent はなんら意味のないアノテーション
    @Autowired
    @Rule
    //public にしないと怒られる
    public MyResource myResource;
}

