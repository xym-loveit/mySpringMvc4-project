package com.xym.spring4.messageconverter;

import com.xym.spring4.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 消息转换器HttpMessageConverter是用来处理request和response里面的数据的。Spring为我们内置了大量的HttpMessageConverter，例如，MappingJackson2HttpMessageConverter,StringHttpMessageConverter等。下面演示自定义的HttpMessageConverter，并注册这个HttpMessageConverter到Spring MVC
 *
 * @author xym
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    public MyMessageConverter() {
        super(new MediaType("application", "xym", Charset.forName("UTF-8")));
    }


    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /**
     * 处理request,字符串转对象
     *
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        String body = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] split = body.split("-");

        return new DemoObj(Long.parseLong(split[0]), split[1]);
    }

    /**
     * 处理response，对象转字符串
     *
     * @param demoObj
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
//        httpOutputMessage.getHeaders().setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
        String out = "hello:" + demoObj.getId() + "-" + demoObj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
