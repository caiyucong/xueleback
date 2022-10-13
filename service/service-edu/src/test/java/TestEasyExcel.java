import com.alibaba.excel.EasyExcel;
import com.caiyucong.eduservice.domain.Subject;
import com.caiyucong.eduservice.service.SubjectService;
import com.caiyucong.eduservice.service.impl.SubjectServiceImpl;
import lombok.var;
import org.junit.Test;

import java.util.ArrayList;

public class TestEasyExcel {

    @Test
    public void test() {
        String fileName = "E:/test.xlsx";
        var demoDataArrayList = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            var demoData = new DemoData();
            demoData.setSno(i);
            demoData.setName("小明");
            demoDataArrayList.add((demoData));
        }
        EasyExcel.write(fileName, DemoData.class).sheet().doWrite(demoDataArrayList);
    }

}
