package cn.word2pdf.service;

import cn.word2pdf.exceptions.FileExtentionForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstraceConvert implements IConvert {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstraceConvert.class);

  private static final String OFFICE_HOME_KEY = "OFFICE_HOME";
  private static final String WORD_EXTENTIONS_KEY = "WORD_EXTENDS";

  private static final String DEFAULT_WORD_EXTENTIONS = ".doc,.docx,.xls,.xlsx,.ppt,.pptx";
  private static String officeHome = null;
  private static String wordExtentions = null;

  static {
    AbstraceConvert.officeHome = getProperty(OFFICE_HOME_KEY);

    String wordExtends = getProperty(WORD_EXTENTIONS_KEY);
    if (wordExtends == null || "".equals(wordExtends)) {
      AbstraceConvert.wordExtentions = DEFAULT_WORD_EXTENTIONS;
    } else {
      AbstraceConvert.wordExtentions = wordExtends;
    }

  }

  @Override
  public boolean convert(String sourceFile) {
    String outputFilePath = null;
    try {
      outputFilePath = this.getOutputFilePath(sourceFile);
      return convert(sourceFile, outputFilePath);
    } catch (FileExtentionForbiddenException e) {
      LOGGER.error("file extention forbidden:{}", e);
    }
    return false;
  }

  private static String getProperty(String key) {
    String value = System.getenv(key);
    if (value == null || "".equals(value)) {
      value = System.getProperty(key);
    }
    return value;
  }

  /**
   * 通过环境变量获取OFFICE的安装路径
   *
   * @return OFFICE安装路径
   */
  protected String getOfficeHome() {
    return AbstraceConvert.officeHome;
  }

  /**
   * 获取相同的文件路径
   *
   * @param inputFilePath
   * @return
   * @throws FileExtentionForbiddenException
   */
  protected String getOutputFilePath(String inputFilePath) throws FileExtentionForbiddenException {
    String extend = inputFilePath.substring(inputFilePath.lastIndexOf("."), inputFilePath.length());

    if (AbstraceConvert.wordExtentions.indexOf(extend) != -1) {
      return inputFilePath.replace(extend, ".pdf");
    }
    throw new FileExtentionForbiddenException(String.format("file extention:%s", extend));
  }
}
