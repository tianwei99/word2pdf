package cn.word2pdf.service;

public interface IConvert {
  boolean convert(String sourceFile);

  boolean convert(String sourceFile, String targetFile);
}
