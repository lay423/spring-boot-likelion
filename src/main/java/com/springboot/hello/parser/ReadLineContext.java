package com.springboot.hello.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadLineContext<T> {
    private Parser<T> parser;

    public ReadLineContext(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readByLine(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "euc-kr"));
        String str;
        reader.readLine();
        while ((str = reader.readLine()) != null) {
            try {
                result.add(parser.parse(str));
            } catch (Exception e) {
                System.out.printf("파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:%s", str);
            }
        }
        reader.close();
        return result;
    }

}
