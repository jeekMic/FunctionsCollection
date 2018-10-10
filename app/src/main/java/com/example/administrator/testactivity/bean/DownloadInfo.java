package com.example.administrator.testactivity.bean;

public class DownloadInfo {
    public static final long TOTAL_ERROR = -1;//获取进度失败
    private String url;
    private long total;
    private long progress;
    private String filename;

    public DownloadInfo(String url) {
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
    public void setFilename(String filename){
        this.filename = filename;
    }
    public void setTotal(long total){
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public String getFilename() {
        return filename;
    }
}
