package demo;

import org.noear.nami.annotation.NamiMapping;
import org.noear.solon.annotation.Component;
import org.noear.solon.cloud.tracing.Spans;

/**
 * @author noear 2022/5/7 created
 */
@Component
public class DemoService {
    @NamiMapping("hello")
    public String hello() {
        Spans.active(span -> span.setTag("订单", 12));
        //或
        Spans.active().setTag("订单", 12);

        return "hello world";
    }
}
