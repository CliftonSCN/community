package top.clifton.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import top.clifton.community.dto.AccessTokenDTO;
import top.clifton.community.dto.GithubUser;

import java.io.IOException;

/**
 * @author Clifton
 * @create 2020/2/1 - 15:50
 */
@Component
public class GithubProvider
{

    /**
     * 获取accessToken
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String responseString = response.body().string();
            String[] split = responseString.split("&");
            String[] split1 = split[0].split("=");
            return split1[1];
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求获取用户信息
     * @param accessToken
     * @return
     */
    public GithubUser getUserInfo(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.github.com/user?access_token="+accessToken).build();

        try (Response response = client.newCall(request).execute()) {
            String responseString = response.body().string();
            return JSON.parseObject(responseString, GithubUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
