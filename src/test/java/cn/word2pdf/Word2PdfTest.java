package cn.word2pdf;

import cn.word2pdf.service.IConvert;
import cn.word2pdf.service.impl.LinuxConvert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Word2PdfTest {

  @Before
  public void setUp(){
    //System.setProperty("OFFICE_HOME","/Applications/LibreOffice.app/Contents/");
  }
  @Test
  public void testWord2Pdf(){
    IConvert convert = new LinuxConvert();
    String file = "/Users/xuwei/Downloads/表15-员工转正会签单(徐维).xlsx";
    boolean result = convert.convert(file);
    Assert.assertTrue(result);
  }
}
