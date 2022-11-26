import com.example.springunity.SpringUnityApplication;
import com.example.springunity.util.LocationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 行政区域工具测试类
 *
 * @author zx
 * @since 2022-10-15
 */
@SpringBootTest(classes = SpringUnityApplication.class)
public class LocationUtilTest {
    LocationUtil locationUtil = LocationUtil.getInstance();

    @Test
    public void test1() {
        List<String> cityList = locationUtil.getCities("中国", "北京");
        for (String city : cityList) {
            System.out.println(city);
        }
    }

    @Test
    public void test2() {
        List<String> provinceList = locationUtil.getProvinces("中国");
        int count = 0;
        for (String province : provinceList) {
            count++;
            System.out.print(province + " " + "\n");
        }
        System.out.println("总" + count + "个");
    }

    @Test
    public void test3() {
        List<String> countyList = locationUtil.getCounty("中国", "浙江", "杭州");
        System.out.println(countyList);
    }
}
