package tym;

import com.tymdoc.service.translator.impl.BaiduTranslator;
import org.junit.Test;

/**
 * @author icepring
 * @date 15/05/2022
 */
public class BaiduTranslatorTest {

    @Test
    public void translate() {
        BaiduTranslator translator = new BaiduTranslator();
        System.out.println(translator.en2Ch("Hello"));
        System.out.println(translator.en2Ch("你好"));
    }
}