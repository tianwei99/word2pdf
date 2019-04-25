package cn.word2pdf;

import cn.word2pdf.service.IConvert;
import cn.word2pdf.service.impl.LinuxConvert;
import cn.word2pdf.service.impl.MSConvert;

public class Application {
  public static void main(String[] args) {
    IConvert convert = new MSConvert();
    boolean result = convert.convert(args[0]);
    System.out.println(result);
  }
}
