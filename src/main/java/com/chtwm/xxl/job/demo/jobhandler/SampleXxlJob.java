package com.chtwm.xxl.job.demo.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Date: 2021/3/11
 * Time: 14:41
 *
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author: yangkai
 * EMail: yangkai01@chtwm.com
 */
@Slf4j
@Component
public class SampleXxlJob {

    /**
     * 简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        log.info("XXL-JOB, Hello World.");
        XxlJobHelper.log("XXL-JOB, Hello World.");
        try {
            for (int i = 0; i < 5; i++) {
                log.info("beat at:" + i);
                XxlJobHelper.log("beat at:" + i);
                TimeUnit.SECONDS.sleep(2);
            }
            // default success
            XxlJobHelper.handleSuccess("XXL-JOB, Hello World.");
        } catch (Exception e) {
            log.error("", e);
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail();
        }
    }
}
