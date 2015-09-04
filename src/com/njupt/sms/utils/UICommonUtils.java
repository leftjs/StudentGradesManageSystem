package com.njupt.sms.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jason on 9/2/15.
 */
public class UICommonUtils {

    public static void makeFrameToCenter(JFrame frame) {
        // 居中
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension thissize=frame.getSize();

        if(thissize.width>screensize.width){
            thissize.width=screensize.width;
        }
        if(thissize.height>screensize.height){
            thissize.height=screensize.height;
        }
        frame.setSize(1000, 700);
        frame.setLocation((screensize.width - thissize.width) / 2, (screensize.height - thissize.height) / 2);

    }
}
