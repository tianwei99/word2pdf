package cn.word2pdf.service.impl;

import cn.word2pdf.service.AbstraceConvert;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MSConvert extends AbstraceConvert {

  private static final int WD_FORMAT_PDF = 17;// PDF 格式
  private static final Logger LOGGER = LoggerFactory.getLogger(MSConvert.class);

  @Override
  public boolean convert(String sourceFile, String targetFile) {
    ActiveXComponent app = null;
    Dispatch doc = null;
    try {
      app = new ActiveXComponent("Word.Application");
      app.setProperty("Visible", new Variant(false));
      Dispatch docs = app.getProperty("Documents").toDispatch();

      doc = Dispatch.call(docs, "Open", sourceFile).toDispatch();
      File tofile = new File(targetFile);
      if (tofile.exists()) {
        tofile.delete();
      }
      Dispatch.call(doc, "SaveAs", targetFile, WD_FORMAT_PDF);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error("convert error:{}", e);
    } finally {
      try {
        Dispatch.call(doc, "Close", false);
        if (app != null)
          app.invoke("Quit", new Variant[]{});


        //结束后关闭进程
        ComThread.Release();
      } catch (Exception e) {
        LOGGER.error("close error:{}", e);
      }
    }
    return false;
  }
}
