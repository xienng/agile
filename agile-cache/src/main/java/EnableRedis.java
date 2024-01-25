import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月25日 14:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(RedisConfig.class)
public @interface EnableRedis {
}
