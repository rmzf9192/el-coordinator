package com.xxl.job.executor.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Roman.Zhang
 * @date 2020/5/8
 * @description: 命令者
 */
@RequiredArgsConstructor
@Data
public class XxlJobInvoker {
//    XxlJonCommand xxlJonCommand;

    public void action(){
      XxlJobCommand.flag =true;
    }
}
