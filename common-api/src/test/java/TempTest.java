import com.jackson.common.api.utils.L;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by: Jackson
 */
public class TempTest {


    @Test
    public void show(){
        String canonicalName = String.class.getCanonicalName();

        L.i(canonicalName);
    }

}
