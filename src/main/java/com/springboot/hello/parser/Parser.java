package com.springboot.hello.parser;

public interface Parser<T> {
    T parse(String str);
}
