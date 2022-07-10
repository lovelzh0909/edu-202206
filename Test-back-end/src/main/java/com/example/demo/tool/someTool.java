package com.example.demo.tool;

import java.util.Arrays;
import java.util.List;

public class someTool {
    public List<String> stringToList(String strs){
        String str[] = strs.split(",");
        return Arrays.asList(str);
   }
}
