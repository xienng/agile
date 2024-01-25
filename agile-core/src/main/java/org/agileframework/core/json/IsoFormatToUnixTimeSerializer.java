package org.agileframework.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.agileframework.core.utils.TimeUtils;

import java.io.IOException;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月23日 13:57
 */
public class IsoFormatToUnixTimeSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {
        gen.writeNumber(TimeUtils.unixTimeWithISO(value));
    }
}
