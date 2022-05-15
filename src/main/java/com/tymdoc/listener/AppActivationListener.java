package com.tymdoc.listener;

import com.intellij.icons.AllIcons.General;
import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.wm.IdeFrame;
import com.tymdoc.view.inner.SupportView;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.net.URI;

/**
 * 应用程序激活监听器
 *
 * @author icepring
 * @date 2022/03/13
 */
public class AppActivationListener implements ApplicationActivationListener {
    private static final Logger LOGGER = Logger.getInstance(AppActivationListener.class);

    /** 上一次通知时间 */
    private volatile long lastNoticeTime = 0L;
    /** 通知时间间隔 */
    private static final long INTERVAL = 24 * 60 * 60 * 1000L;

    @Override
    public synchronized void applicationActivated(@NotNull IdeFrame ideFrame) {
        if (System.currentTimeMillis() - lastNoticeTime < INTERVAL) {
            return;
        }
        NotificationGroup group = new NotificationGroup("Tym Javadoc", NotificationDisplayType.STICKY_BALLOON, true, null,
            General.AddJdk);
        Notification notification = group.createNotification(
            "如果你注意到了这条消息，那你就浪费了两秒时间",
            NotificationType.INFORMATION);

        notification.notify(null);
        lastNoticeTime = System.currentTimeMillis();
    }

    @Override
    public void applicationDeactivated(@NotNull IdeFrame ideFrame) {
        applicationActivated(ideFrame);
    }
}