package cn.word2pdf.service.impl;

import cn.word2pdf.service.AbstraceConvert;
import org.jodconverter.LocalConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class LinuxConvert extends AbstraceConvert {
  private static final Logger LOGGER = LoggerFactory.getLogger(LinuxConvert.class);

  public boolean convert(String sourceFile, String targetFile) {
    LocalOfficeManager officeManager = null;
    try {
      officeManager = LocalOfficeManager.builder()
          .officeHome(super.getOfficeHome())
          .build();
      officeManager.start();
      LocalConverter.make(officeManager).convert(new File(sourceFile)).to(new File(targetFile)).execute();
      return true;
    } catch (Exception e) {
      LOGGER.error("convert error:{}", e);
    } finally {
      OfficeUtils.stopQuietly(officeManager);
    }
    return false;
  }
}
