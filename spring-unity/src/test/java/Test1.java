import com.example.page.PageInfo;
import com.example.springunity.SpringUnityApplication;
import com.example.springunity.controller.UnityController;
import com.example.springunity.controller.vo.UserInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zx
 * @since 2024-03-18
 */
@SpringBootTest(classes = SpringUnityApplication.class)
public class Test1
{
    @Test
    public void test1() throws InterruptedException
    {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    // 向队列中添加元素，如果队列已满则阻塞等待
                    queue.put(i);
                    System.out.println("生产者添加元素：" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        // 创建消费者线程
        Thread consumer = new Thread(() -> {
            try {
                int count = 0;
                while (true) {

                    // 从队列中取出元素，如果队列为空则阻塞等待
                    int element = queue.take();
                    System.out.println("消费者取出元素：" + element);
                    ++count;
                    if (count == 10) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        // 启动线程
        producer.start();
        Thread.sleep(3000);
        consumer.start();

        // 等待线程结束
        producer.join();
        consumer.join();

        producer.interrupt();
        consumer.interrupt();
    }

    @Test
    public void test2() throws InterruptedException
    {
        List<Integer> list = new ArrayList<>();

        list.add(1);
    }

    @Test
    public void test3() {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNum);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
    }
}
