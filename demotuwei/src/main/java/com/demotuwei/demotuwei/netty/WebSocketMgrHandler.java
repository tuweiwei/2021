package com.demotuwei.demotuwei.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * xxxxxx
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketMgrHandler extends SimpleChannelInboundHandler<Object> {
    public static Map<String, ChannelHandlerContext> map = new ConcurrentHashMap<>();

    private WebSocketServerHandshaker handshaker;

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    public static synchronized void sendMessageToRomMachine(String message) {
//        map.forEach((k, v) -> {
//            if (k.startsWith(UserRoleConstant.ROM_MACHINE_USER)) {
//                Channel channel = v.channel();
//                if (null != channel && channel.isActive() && channel.isOpen()) {
//                    TextWebSocketFrame tws = new TextWebSocketFrame(message);
//                    channel.writeAndFlush(tws);
//                }
//            }
//        });
    }

    public static synchronized void sendMessage(ChannelHandlerContext ctx, String message) {
        TextWebSocketFrame tws = new TextWebSocketFrame(message);
        ctx.channel().writeAndFlush(tws);
    }

    public static synchronized void sendMessageByUserIds(List<Long> userIds, Object message) {
//        map.forEach((k, v) -> {
//            try {
//                if (k.startsWith(UserRoleConstant.PC_USER)) {
//                    String userIdStr = k.substring(UserRoleConstant.PC_USER.length(), k.length());
//                    Long userId = Long.valueOf(userIdStr);
//                    if (userIds.contains(userId)) {
//                        Channel channel = v.channel();
//                        if (null != channel && channel.isActive() && channel.isOpen()) {
//                            String msg = JSON.toJSONString(message);
//                            TextWebSocketFrame tws = new TextWebSocketFrame(msg);
//                            channel.writeAndFlush(tws);
//                            log.info("向客户端" + k + "发送消息" + msg);
//                        }
//                    }
//                }
//            } catch (NumberFormatException e) {
//
//            } catch (Exception e) {
//
//            }
//        });
    }

    public static synchronized int sendMessageByClientid(String message, String clientId) {
        if (null != map.get(clientId)) {
            Channel channel = map.get(clientId).channel();
            if (null != channel && channel.isActive() && channel.isOpen()) {
                log.info("clientid :{" + clientId + "}  send websocket message :{}", message);
                TextWebSocketFrame tws = new TextWebSocketFrame(message);
                channel.writeAndFlush(tws);
                return 1;
            }
            return 0;
        }
        return 0;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.decoderResult().isSuccess() || (!"websocket".equalsIgnoreCase(req.headers().get("Upgrade").toString()))) {
            sendHttpResponse(ctx, req,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                getWebSocketUrl(), null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof CloseWebSocketFrame) {
            log.error("frame instanceof CloseWebSocketFrame");
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }

        if (frame instanceof PingWebSocketFrame) {
            log.error("frame instanceof PingWebSocketFrame");
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        if (frame instanceof TextWebSocketFrame) {
            String message = ((TextWebSocketFrame) frame).text();
            //doMessage(ctx, message);
            return;
        }

        if (frame instanceof BinaryWebSocketFrame) {
            log.error("frame instanceof BinaryWebSocketFrame");
            ByteBuf buf = frame.content();
            byte[] msgByte = new byte[buf.capacity()];
            for (int i = 0; i < buf.capacity(); i++) {
                byte b = buf.getByte(i);
                msgByte[i] = buf.getByte(i);
            }
            try {
                String message = new String(msgByte, "UTF-8");
                log.info("message after convert:" + message);
                //doMessage(ctx, message);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("channelActive：" + ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("channelInactive：" + ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("websocket exceptionCaught", cause);
        cause.printStackTrace();
        ctx.close();
    }

    private String getWebSocketUrl() {
        String url = "";
        return "ws://" + url;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (o instanceof FullHttpRequest) {
            handleHttpRequest(channelHandlerContext, ((FullHttpRequest) o));
        } else if (o instanceof WebSocketFrame) {


            handlerWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }
}