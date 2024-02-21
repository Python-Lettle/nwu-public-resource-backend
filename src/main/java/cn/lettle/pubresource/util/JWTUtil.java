/**
 * Author: Lettle
 * Date: 2024/2/21
 * Time: 19:10
 * Description: 使用 JSON Web Token (JWT) 生成用户登录时的 Token
 *              用于鉴定用户是否登录，放置在请求头
 */

package cn.lettle.pubresource.util;

import cn.lettle.pubresource.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil
{

    /**
     * 生成token  header.payload.singature
     */
    private static final String SING = "Lettle";

    public static String getToken(Map<String, String> map) {

        Calendar instance = Calendar.getInstance();
        // 默认7天过期
        instance.add(Calendar.DATE, 7);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        // payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));  // sign
        return token;
    }

    /**
     * 验证token  合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 获取用户 Token 所携带的信息
     * @param token
     * @return User类, 仅含有 uid 及 username
     */
    public static User getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        User user = new User();
        user.setUid(verify.getClaim("uid").asInt());
        user.setName(verify.getClaim("username").asString());
        return user;
    }
}

