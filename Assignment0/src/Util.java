import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

//	Courtesy of:
//	http://www.java2s.com/Code/Java/File-Input-Output/Compressobjectanddecompress.htm

public class Util {
  public static byte[] compress(Object data) {
    if (data == null) {
      return null;
    }
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      GZIPOutputStream gout = new GZIPOutputStream(baos);
      ObjectOutputStream oos = new ObjectOutputStream(gout);
      oos.writeObject(data);
      oos.flush();
      gout.finish();
      return baos.toByteArray();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


    public static Object decompress(byte[] data)
    {
      if (data == null) {
        return null;
      }
      try {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        GZIPInputStream gin = new GZIPInputStream(bais);
        ObjectInputStream ois = new ObjectInputStream(gin);
        return ois.readObject();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }
}