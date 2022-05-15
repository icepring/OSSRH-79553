package tym;

import com.tymdoc.service.translator.impl.GoogleTranslator;
import org.junit.Test;

/**
 * @author icepring
 * @date 15/05/2022
 */
public class GoogleTranslatorTest {

    @Test
    public void translate() {
        GoogleTranslator translator = new GoogleTranslator();
        System.out.println(translator.translateEn2Ja("Hello"));
        System.out.println(translator.ch2En("你好"));
    }
}