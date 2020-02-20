package top.clifton.community.service;

import top.clifton.community.pojo.Notification;

import java.util.List;

/**
 * @author Clifton
 * @create 2020/2/14 - 19:59
 */
public interface NotificationService {
    List<Notification> getCurrentUserNotifications(Integer accountId);

    int selectUnReadCount(Integer accountId);

    Notification selectById(int id);

    void UpdateStatus(int id);
}
