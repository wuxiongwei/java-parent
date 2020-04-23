package com.wuxiongwei.common.lang.execution;

import org.testng.annotations.Test;

/**
 * <p>
 * Copyright: Copyright (c) 2020/4/13  11:34 上午
 * <p>
 * Company: 苏州渠成易销网络科技有限公司
 * <p>
 *
 * @author xiongwei.wu@successchannel.com
 * @version 1.0.0
 */
public class CommandLineTest {
    @Test
    public void testEscapeSpaces() throws Exception {
        CommandLine cmd = new CommandLine().add("ls", "-l", "/Users/mac/Documents/codes/github/open/java-parent");
        final String[] line = new ShellFactory.StandardLinuxShell().createShellCommand(cmd);
        for (String s : line) {
            System.out.println(s);
        }
    }
}
