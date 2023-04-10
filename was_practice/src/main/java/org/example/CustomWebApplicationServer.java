package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class CustomWebApplicationServer {
    private final int port; // port 값
    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);
    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)) { // 해당하는 포트로 서버를 띄운다.
            logger.info("[CustomWebApplicationServer] started port.", port);

            Socket clientSocket; // 클라이언트 소켓을 대기로 한다.
            logger.info("[CustomWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) { // 클라이언트 소켓이 null이 아니면
                logger.info("[CustomWebApplicationServer] client connected!"); // 클라이언트가 연결되었다는 의미이다.
            }

        }
    }
}
