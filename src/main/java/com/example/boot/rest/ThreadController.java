package com.example.boot.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 线程测试controller
 *
 * @author ltk
 */
public class ThreadController {

  public static void main(String[] args) throws IOException, ParseException {
    A a = new A();
    Thread tha = new Thread(a);
    B b = new B();
    Thread thb = new Thread(b);
    tha.start();
    thb.start();
    System.out.println(Thread.currentThread().getName());
    System.out.println(Thread.activeCount());

    byte[] bytes = Files.readAllBytes(Paths.get("c://1.jpg"));
    String base64Str = Base64.getEncoder().encodeToString(bytes);
    System.out.println(base64Str);

    // 时间类
    String dateStr = "1970-01-02 00:00:00";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = sdf.parse(dateStr);
    System.out.println(date + "==>" + date.getTime());

    Date dateLong = new Date();
    Locale locale = Locale.KOREA;
    DateFormat dateFormat =
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
    System.out.println(dateFormat.format(dateLong) + "==>" + dateLong.getTime());

    System.out.println(TimeZone.getDefault().getRawOffset());
  }

  static class A implements Runnable {
    @Override
    public void run() {
      System.out.println("线程A开始。。。" + Thread.currentThread().getName());
    }
  }

  static class B implements Runnable {
    @Override
    public void run() {
      System.out.println("线程B开始。。。" + Thread.currentThread().getName());
    }
  }
}
