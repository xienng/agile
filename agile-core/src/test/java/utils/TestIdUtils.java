package utils;

import org.agileframework.core.utils.IdUtils;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月23日 11:41
 */
public class TestIdUtils {


    public static void main(String[] args) {

        System.out.println(IdUtils.id(System.currentTimeMillis() + "12345", 2345));

    }
}
