package com.han.test3;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebFilter(urlPatterns = {"/*"})
public class DownloadCounterFilter implements Filter {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Properties downloadLog;
    private File logFile;
    public void init(FilterConfig filterConfig) throws ServletException {
        String appPath = filterConfig.getServletContext().getRealPath("/");
        logFile = new File(appPath, "downloadLog.txt");
        if (!logFile.exists()){
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        downloadLog = new Properties();
        try {
            downloadLog.load(new FileReader(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String requestUri = request.getRequestURI();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String value = downloadLog.getProperty(requestUri);
                if (value == null){
                    downloadLog.setProperty(requestUri, "1");
                }
                else {
                    int count = Integer.parseInt(value);
                    downloadLog.setProperty(requestUri, String.valueOf(++count));
                }
                try {
                    downloadLog.store(new FileWriter(logFile),"");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        executorService.shutdown();
    }
}
