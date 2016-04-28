import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.runtech.sms.SMSSenderImpl;
import com.runtech.util.SMSSender;


public class Test {

    public static void main(String[] args) {

        try {
//            // Grab the Scheduler instance from the Factory 
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//            // and start it off
//            scheduler.start();
//
//            scheduler.shutdown();
            

    		if(args.length==1){
    			String filePath = args[0];
    			File file = new File(filePath);
    			List<String> readLines = FileUtils.readLines(file,"GBK");
    			SMSSender smsSender = new SMSSenderImpl();
    			for (String line : readLines) {
					String[] data = line.split(",");
					if(data.length==2){
						String mobileString = data[0];
						String content = "尊敬的"+data[1] + "你好，缤果开放预订雷波最好的脐橙仅9.9元/斤，雷波县金沙村金沙江岸老鸦岩果园直供，预计周五发货 b-guo.com";
						smsSender.send(mobileString, content, new Date());
					}
					Thread.sleep(10000);
				}
    		}

        } catch (Exception se) {
            se.printStackTrace();
        }
    }
}