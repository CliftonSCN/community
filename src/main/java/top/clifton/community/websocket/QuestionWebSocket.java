package top.clifton.community.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * @author Clifton
 * @create 2020/2/20 - 15:56
 */
@ServerEndpoint("/questionWebSocket/{userId}/{questionId}")
@Component
public class QuestionWebSocket
{

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //    private static ConcurrentHashMap<String, QuestionWebSocket> webSocketMap = new ConcurrentHashMap<>();
    //<问题id, <用户id, webSocket对象>>
    private static ConcurrentHashMap<String, Map<String, QuestionWebSocket>> webSocketMap = new ConcurrentHashMap<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String userId = "";
    private String questionId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("userId")String userId,
                       @PathParam("questionId")String questionId,
                       Session session) {
        this.userId = userId;
        this.questionId = questionId;
        this.session = session;
        if (!webSocketMap.contains(questionId)){
            webSocketMap.put(questionId, new HashMap<>());
        }
        webSocketMap.get(questionId).put(userId, this);
        addOnlineCount(); // 在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (!userId.equals("") && !questionId.equals("")){
            webSocketMap.get(questionId).remove(userId); // 从map中删除
            subOnlineCount(); // 在线数减1
            System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
//        String toUserId = message.split(",")[1];
//        String message = message.split(",")[0];
    }

    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        // this.session.getAsyncRemote().sendText(message);
    }

    /*public static void systemSendMessageToUser(String userId, String message){
        QuestionWebSocket questionWebSocket = QuestionWebSocket.webSocketMap.get(userId);
        if (questionWebSocket != null){
            try {
                questionWebSocket.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * 群发自定义消息给同一问题连接
     */
    public static void sendInfo(String questionId, String message) {
        Map<String, QuestionWebSocket> webSocketMap = QuestionWebSocket.webSocketMap.get(questionId);
        webSocketMap.forEach((userId, webSocket) -> {
            try {
                webSocket.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        QuestionWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        QuestionWebSocket.onlineCount--;
    }

}
