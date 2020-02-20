package top.clifton.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.clifton.community.dao.NotificationMapper;
import top.clifton.community.pojo.Notification;
import top.clifton.community.pojo.NotificationExample;
import top.clifton.community.service.NotificationService;

import java.util.List;

/**
 * @author Clifton
 * @create 2020/2/14 - 19:59
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public List<Notification> getCurrentUserNotifications(Integer accountId) {
        NotificationExample notificationExample = new NotificationExample();
        NotificationExample.Criteria criteria = notificationExample.createCriteria();
        criteria.andReceiverEqualTo(accountId);
        return notificationMapper.selectByExample(notificationExample);
    }

    @Override
    public int selectUnReadCount(Integer accountId) {
        NotificationExample notificationExample = new NotificationExample();
        NotificationExample.Criteria criteria = notificationExample.createCriteria();
        criteria.andReceiverEqualTo(accountId);
        criteria.andStatusEqualTo(0);
        return (int) notificationMapper.countByExample(notificationExample);
    }

    @Override
    public Notification selectById(int id) {
        return notificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void UpdateStatus(int id) {
        Notification notification = new Notification();
        notification.setId(id);
        notification.setStatus(1);
        notificationMapper.updateByPrimaryKeySelective(notification);
    }
}
