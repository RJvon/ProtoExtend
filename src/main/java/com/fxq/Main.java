package com.fxq;

import com.fxq.annotation.GetDoubleMethod;
import com.fxq.annotation.PutDoubleMethod;
import com.fxq.annotation.PutStringMethod;
import com.fxq.lombok.*;
import com.fxq.utils.Tools;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Tools.initialize();
        PutStringMethodClassOperate putStringMethodClassOperate = new PutStringMethodClassOperate();
        putStringMethodClassOperate.doClassOperate();
        PutIntMethodClassOperate putIntMethodClassOperate = new PutIntMethodClassOperate();
        putIntMethodClassOperate.doClassOperate();
        PutDoubleMethodClassOperate putDoubleMethodClassOperate = new PutDoubleMethodClassOperate();
        putDoubleMethodClassOperate.doClassOperate();
        GetDoubleMethodClassOperate getDoubleMethodClassOperate = new GetDoubleMethodClassOperate();
        getDoubleMethodClassOperate.doClassOperate();
        GetStringMethodClassOperate getStringMethodClassOperate = new GetStringMethodClassOperate();
        getStringMethodClassOperate.doClassOperate();
        GetIntMethodClassOperate getIntMethodClassOperate = new GetIntMethodClassOperate();
        getIntMethodClassOperate.doClassOperate();
        IsNullMethodClassOperate isNullMethodClassOperate = new IsNullMethodClassOperate();
        isNullMethodClassOperate.doClassOperate();
    }


}
